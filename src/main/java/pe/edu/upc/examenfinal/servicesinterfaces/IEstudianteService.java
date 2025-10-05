package pe.edu.upc.examenfinal.servicesinterfaces;

import pe.edu.upc.examenfinal.entities.Estudiante;

import java.util.List;

public interface IEstudianteService {
    List<Estudiante> list();
    Estudiante insert(Estudiante estudiante);
    Estudiante update(Estudiante estudiante);
    void delete(Long id);
    Estudiante findById(Long id);
    Estudiante findByEmail(String email);
    List<Estudiante> findByNombre(String nombre);
    List<Estudiante> findEstudiantesConMasInscripciones(int cantidad);
}
