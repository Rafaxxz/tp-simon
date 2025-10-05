package pe.edu.upc.examenfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.examenfinal.entities.Profesor;

import java.util.List;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    
    // Buscar profesor por email
    Profesor findByEmail(String email);
    
    // Buscar profesores por especialidad
    List<Profesor> findByEspecialidad(String especialidad);
    
    // Query personalizada: Profesores con más cursos
    @Query("SELECT p FROM Profesor p WHERE SIZE(p.cursos) >= :cantidad")
    List<Profesor> findProfesoresConMinimoCursos(int cantidad);
}
