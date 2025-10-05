package pe.edu.upc.examenfinal.servicesinterfaces;

import pe.edu.upc.examenfinal.entities.Profesor;

import java.util.List;

public interface IProfesorService {
    List<Profesor> list();
    Profesor insert(Profesor profesor);
    Profesor update(Profesor profesor);
    void delete(Long id);
    Profesor findById(Long id);
    Profesor findByEmail(String email);
    List<Profesor> findByEspecialidad(String especialidad);
    List<Profesor> findProfesoresConMinimoCursos(int cantidad);
}
