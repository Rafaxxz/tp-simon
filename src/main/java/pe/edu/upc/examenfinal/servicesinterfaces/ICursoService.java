package pe.edu.upc.examenfinal.servicesinterfaces;

import pe.edu.upc.examenfinal.entities.Curso;

import java.time.LocalDate;
import java.util.List;

public interface ICursoService {
    List<Curso> list();
    Curso insert(Curso curso);
    Curso update(Curso curso);
    void delete(Long id);
    Curso findById(Long id);
    List<Curso> findByNombreCurso(String nombreCurso);
    List<Curso> findCursosByProfesor(Long idProfesor);
    List<Curso> findCursosActivos(LocalDate fechaActual);
    List<Curso> findByCreditosMinimos(Integer creditos);
}
