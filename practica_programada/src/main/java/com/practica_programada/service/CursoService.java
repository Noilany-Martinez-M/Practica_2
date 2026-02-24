/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica_programada.service;
import com.practica_programada.domain.Curso;
import com.practica_programada.repository.CursoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Maytan
 */

@Service
public class CursoService {
    
    @Autowired
    private final CursoRepository cursoRepository;
    
    public List<Curso> getCursos(){
        return cursoRepository.findAll();
    }
    
    public Curso getCurso(Integer id){
        return cursoRepository.findById(id).orElse(null);
    }
    
    
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Transactional
    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Transactional
    public void delete(Integer id) {
        cursoRepository.deleteById(id);
    }
}
