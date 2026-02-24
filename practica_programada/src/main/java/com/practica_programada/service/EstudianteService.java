
package com.practica_programada.service;
import com.practica_programada.domain.Estudiante;
import com.practica_programada.repository.CursoRepository;
import com.practica_programada.repository.EstudianteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Maytan
 */
@Service
public class EstudianteService {
    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;
    
     public EstudianteService(EstudianteRepository estudianteRepository,
                             CursoRepository cursoRepository) {
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
    }

    @Transactional(readOnly = true)
    public List<Estudiante> getEstudiantes() {
        return estudianteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Estudiante> getEstudiante(Integer id) {
        return estudianteRepository.findById(id);
    }

    @Transactional
    public Estudiante save(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Transactional
    public void delete(Integer id) {
        if (!estudianteRepository.existsById(id)) {
            throw new IllegalArgumentException("El estudiante no existe");
        }
        estudianteRepository.deleteById(id);
    }
}
