package pe.edu.upc.examenfinal.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.examenfinal.entities.Estudiante;
import pe.edu.upc.examenfinal.repositories.EstudianteRepository;
import pe.edu.upc.examenfinal.servicesinterfaces.IEstudianteService;

import java.util.List;

@Service
public class EstudianteServiceImplement implements IEstudianteService {
    
    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<Estudiante> list() {
        return estudianteRepository.findAll();
    }

    @Override
    public Estudiante insert(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public Estudiante update(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public void delete(Long id) {
        estudianteRepository.deleteById(id);
    }

    @Override
    public Estudiante findById(Long id) {
        return estudianteRepository.findById(id).orElse(null);
    }

    @Override
    public Estudiante findByEmail(String email) {
        return estudianteRepository.findByEmail(email);
    }

    @Override
    public List<Estudiante> findByNombre(String nombre) {
        return estudianteRepository.findByNombreContaining(nombre);
    }

    @Override
    public List<Estudiante> findEstudiantesConMasInscripciones(int cantidad) {
        return estudianteRepository.findEstudiantesConMasInscripciones(cantidad);
    }
}
