/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica_programada.controller;
import com.practica_programada.domain.Curso;
import com.practica_programada.domain.Estudiante;
import com.practica_programada.service.CursoService;
import com.practica_programada.service.EstudianteService;
import jakarta.validation.Valid;
import java.util.Locale;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Maytan
 */
@Controller
@RequestMapping("/estudiante")
public class EstudianteController {
    private final EstudianteService estudianteService;
    
    @Autowired
    private CursoService cursoService;
    private final MessageSource messageSource;

    public EstudianteController(EstudianteService estudianteService,CursoService cursoService,MessageSource messageSource) {
        this.estudianteService = estudianteService;
        this.cursoService = cursoService;
        this.messageSource = messageSource;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var estudiantes = estudianteService.getEstudiantes();
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("totalEstudiantes", estudiantes.size());

        var cursos = cursoService.getCursos();
        model.addAttribute("cursos", cursos);
        
        model.addAttribute("estudiante", new Estudiante());
        
        return "estudiante/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Estudiante estudiante,@RequestParam("idCurso") Integer idCurso,RedirectAttributes redirectAttributes) {

        Curso curso = cursoService.getCurso(idCurso);
        
        estudiante.setCurso(curso);

        estudianteService.save(estudiante);

        redirectAttributes.addFlashAttribute("exito", "Estudiante guardado");
        return "redirect:/estudiante/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer idEstudiante,RedirectAttributes redirectAttributes) {

        String titulo = "todoOk";
        String detalle = "mensaje.eliminado";

        try {
            estudianteService.delete(idEstudiante);
        } catch (IllegalArgumentException e) {
            titulo = "error";
            detalle = "estudiante.error01";
        } catch (IllegalStateException e) {
            titulo = "error";
            detalle = "estudiante.error02";
        } catch (Exception e) {
            titulo = "error";
            detalle = "estudiante.error03";
        }

        redirectAttributes.addFlashAttribute(titulo, messageSource.getMessage(detalle, null, Locale.getDefault()));

        return "redirect:/estudiante/listado";
    }

    @GetMapping("/modificar/{idEstudiante}")
    public String modificar(@PathVariable Integer idEstudiante,Model model,RedirectAttributes redirectAttributes) {

        Optional<Estudiante> estudianteOpt =
                estudianteService.getEstudiante(idEstudiante);

        if (estudianteOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error",messageSource.getMessage("estudiante.error01", null, Locale.getDefault()));
            return "redirect:/estudiante/listado";
        }

        model.addAttribute("estudiante", estudianteOpt.get());

        var cursos = cursoService.getCursos();
        model.addAttribute("cursos", cursos);

        return "estudiante/modifica";
    }
}
