package pe.edu.upc.examenfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.examenfinal.entities.Estudiante;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    
    // Buscar estudiante por email
    Estudiante findByEmail(String email);
    
    // Listar estudiantes por nombre
    List<Estudiante> findByNombreContaining(String nombre);
    
    // Query personalizada: Estudiantes con más inscripciones
    @Query("SELECT e FROM Estudiante e WHERE SIZE(e.inscripciones) > :cantidad")
    List<Estudiante> findEstudiantesConMasInscripciones(int cantidad);
}
