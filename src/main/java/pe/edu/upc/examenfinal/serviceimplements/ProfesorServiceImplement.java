package pe.edu.upc.examenfinal.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.examenfinal.entities.Profesor;
import pe.edu.upc.examenfinal.repositories.ProfesorRepository;
import pe.edu.upc.examenfinal.servicesinterfaces.IProfesorService;

import java.util.List;

@Service
public class ProfesorServiceImplement implements IProfesorService {
    
    @Autowired
    private ProfesorRepository profesorRepository;

    @Override
    public List<Profesor> list() {
        return profesorRepository.findAll();
    }

    @Override
    public Profesor insert(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @Override
    public Profesor update(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @Override
    public void delete(Long id) {
        profesorRepository.deleteById(id);
    }

    @Override
    public Profesor findById(Long id) {
        return profesorRepository.findById(id).orElse(null);
    }

    @Override
    public Profesor findByEmail(String email) {
        return profesorRepository.findByEmail(email);
    }

    @Override
    public List<Profesor> findByEspecialidad(String especialidad) {
        return profesorRepository.findByEspecialidad(especialidad);
    }

    @Override
    public List<Profesor> findProfesoresConMinimoCursos(int cantidad) {
        return profesorRepository.findProfesoresConMinimoCursos(cantidad);
    }
}
