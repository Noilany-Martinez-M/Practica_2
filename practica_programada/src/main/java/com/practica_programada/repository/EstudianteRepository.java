/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.practica_programada.repository;
import com.practica_programada.domain.Estudiante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Maytan
 */
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer>{
    public List<Estudiante> findByCursoIdCurso(Integer idcurso);
}
