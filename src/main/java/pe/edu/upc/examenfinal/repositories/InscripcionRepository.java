package pe.edu.upc.examenfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.examenfinal.entities.Inscripcion;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    
    // Buscar inscripciones por estudiante
    @Query("SELECT i FROM Inscripcion i WHERE i.estudiante.idEstudiante = :idEstudiante")
    List<Inscripcion> findInscripcionesByEstudiante(Long idEstudiante);
    
    // Buscar inscripciones por curso
    @Query("SELECT i FROM Inscripcion i WHERE i.curso.idCurso = :idCurso")
    List<Inscripcion> findInscripcionesByCurso(Long idCurso);
    
    // Buscar inscripciones por estado
    List<Inscripcion> findByEstado(String estado);
    
    // Buscar inscripciones con calificación mayor o igual
    List<Inscripcion> findByCalificacionGreaterThanEqual(Double calificacion);
    
    // Query personalizada: Promedio de calificaciones por curso
    @Query("SELECT AVG(i.calificacion) FROM Inscripcion i WHERE i.curso.idCurso = :idCurso")
    Double findPromedioCalificacionesByCurso(Long idCurso);
}
