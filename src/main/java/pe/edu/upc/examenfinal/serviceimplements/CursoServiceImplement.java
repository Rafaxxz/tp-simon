package pe.edu.upc.examenfinal.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.examenfinal.entities.Curso;
import pe.edu.upc.examenfinal.repositories.CursoRepository;
import pe.edu.upc.examenfinal.servicesinterfaces.ICursoService;

import java.time.LocalDate;
import java.util.List;

@Service
public class CursoServiceImplement implements ICursoService {
    
    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public List<Curso> list() {
        return cursoRepository.findAll();
    }

    @Override
    public Curso insert(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public Curso update(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public void delete(Long id) {
        cursoRepository.deleteById(id);
    }

    @Override
    public Curso findById(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Curso> findByNombreCurso(String nombreCurso) {
        return cursoRepository.findByNombreCursoContaining(nombreCurso);
    }

    @Override
    public List<Curso> findCursosByProfesor(Long idProfesor) {
        return cursoRepository.findCursosByProfesor(idProfesor);
    }

    @Override
    public List<Curso> findCursosActivos(LocalDate fechaActual) {
        return cursoRepository.findCursosActivos(fechaActual);
    }

    @Override
    public List<Curso> findByCreditosMinimos(Integer creditos) {
        return cursoRepository.findByCreditosGreaterThanEqual(creditos);
    }
}
