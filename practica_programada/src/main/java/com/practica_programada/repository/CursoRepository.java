
package com.practica_programada.repository;
import com.practica_programada.domain.Curso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Maytan
 */
public interface CursoRepository extends JpaRepository<Curso, Integer>{
    
    
}
