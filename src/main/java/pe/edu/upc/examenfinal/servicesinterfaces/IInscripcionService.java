package pe.edu.upc.examenfinal.servicesinterfaces;

import pe.edu.upc.examenfinal.entities.Inscripcion;

import java.util.List;

public interface IInscripcionService {
    List<Inscripcion> list();
    Inscripcion insert(Inscripcion inscripcion);
    Inscripcion update(Inscripcion inscripcion);
    void delete(Long id);
    Inscripcion findById(Long id);
    List<Inscripcion> findInscripcionesByEstudiante(Long idEstudiante);
    List<Inscripcion> findInscripcionesByCurso(Long idCurso);
    List<Inscripcion> findByEstado(String estado);
    List<Inscripcion> findByCalificacionMinima(Double calificacion);
    Double findPromedioCalificacionesByCurso(Long idCurso);
}
