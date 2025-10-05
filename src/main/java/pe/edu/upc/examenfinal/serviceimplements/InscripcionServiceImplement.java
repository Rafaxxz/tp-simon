package pe.edu.upc.examenfinal.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.examenfinal.entities.Inscripcion;
import pe.edu.upc.examenfinal.repositories.InscripcionRepository;
import pe.edu.upc.examenfinal.servicesinterfaces.IInscripcionService;

import java.util.List;

@Service
public class InscripcionServiceImplement implements IInscripcionService {
    
    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Override
    public List<Inscripcion> list() {
        return inscripcionRepository.findAll();
    }

    @Override
    public Inscripcion insert(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    @Override
    public Inscripcion update(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    @Override
    public void delete(Long id) {
        inscripcionRepository.deleteById(id);
    }

    @Override
    public Inscripcion findById(Long id) {
        return inscripcionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Inscripcion> findInscripcionesByEstudiante(Long idEstudiante) {
        return inscripcionRepository.findInscripcionesByEstudiante(idEstudiante);
    }

    @Override
    public List<Inscripcion> findInscripcionesByCurso(Long idCurso) {
        return inscripcionRepository.findInscripcionesByCurso(idCurso);
    }

    @Override
    public List<Inscripcion> findByEstado(String estado) {
        return inscripcionRepository.findByEstado(estado);
    }

    @Override
    public List<Inscripcion> findByCalificacionMinima(Double calificacion) {
        return inscripcionRepository.findByCalificacionGreaterThanEqual(calificacion);
    }

    @Override
    public Double findPromedioCalificacionesByCurso(Long idCurso) {
        return inscripcionRepository.findPromedioCalificacionesByCurso(idCurso);
    }
}
