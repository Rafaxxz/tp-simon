package pe.edu.upc.examenfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.examenfinal.entities.Curso;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    
    // Buscar cursos por nombre
    List<Curso> findByNombreCursoContaining(String nombreCurso);
    
    // Buscar cursos por profesor
    @Query("SELECT c FROM Curso c WHERE c.profesor.idProfesor = :idProfesor")
    List<Curso> findCursosByProfesor(Long idProfesor);
    
    // Buscar cursos activos (fecha actual entre inicio y fin)
    @Query("SELECT c FROM Curso c WHERE c.fechaInicio <= :fechaActual AND c.fechaFin >= :fechaActual")
    List<Curso> findCursosActivos(LocalDate fechaActual);
    
    // Buscar cursos por créditos mínimos
    List<Curso> findByCreditosGreaterThanEqual(Integer creditos);
}
