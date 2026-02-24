
package com.practica_programada.controller;
import com.practica_programada.domain.Curso;
import com.practica_programada.service.CursoService;
import jakarta.validation.Valid;
import java.util.Locale;
import java.util.Optional;
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
@RequestMapping("/curso")
public class CursoController {
    
    private final CursoService cursoService;
    private final MessageSource messageSource;

    public CursoController(CursoService cursoService, MessageSource messageSource) {
        this.cursoService = cursoService;
        this.messageSource = messageSource;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var cursos = cursoService.getCursos();
        model.addAttribute("cursos", cursos);
        model.addAttribute("totalCursos", cursos.size());
        
        model.addAttribute("curso", new Curso());
        
        return "curso/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Curso curso, RedirectAttributes redirectAttributes) {
        cursoService.save(curso);
        redirectAttributes.addFlashAttribute("todoOk",messageSource.getMessage("mensaje.actualizado", null, Locale.getDefault()));
        return "redirect:/curso/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer idCurso, RedirectAttributes redirectAttributes) {

        String titulo = "todoOk";
        String detalle = "mensaje.eliminado";

        try {
            cursoService.delete(idCurso);
        } catch (IllegalArgumentException e) {
            titulo = "error";
            detalle = "curso.error01";
        } catch (IllegalStateException e) {
            titulo = "error";
            detalle = "curso.error02";
        } catch (Exception e) {
            titulo = "error";
            detalle = "curso.error03";
        }

        redirectAttributes.addFlashAttribute(titulo,messageSource.getMessage(detalle, null, Locale.getDefault()));

        return "redirect:/curso/listado";
    }

    @GetMapping("/modificar/{idCurso}")
    public String modificar(@PathVariable Integer idCurso,Model model,RedirectAttributes redirectAttributes) {

        Curso curso = cursoService.getCurso(idCurso);

        if (curso == null) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage("curso.error01", null, Locale.getDefault()));
            return "redirect:/curso/listado";
        }

        model.addAttribute("curso", curso);
        return "curso/modifica";
    }
}
