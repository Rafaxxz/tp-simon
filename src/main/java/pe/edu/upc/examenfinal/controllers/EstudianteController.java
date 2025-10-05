package pe.edu.upc.examenfinal.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.examenfinal.dtos.EstudianteDTO;
import pe.edu.upc.examenfinal.entities.Estudiante;
import pe.edu.upc.examenfinal.servicesinterfaces.IEstudianteService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/estudiantes")
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
public class EstudianteController {

    @Autowired
    private IEstudianteService estudianteService;

    private ModelMapper modelMapper = new ModelMapper();

    // 1. GET - Listar todos los estudiantes
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'PROFESOR')")
    public List<EstudianteDTO> listarEstudiantes() {
        return estudianteService.list().stream()
                .map(est -> modelMapper.map(est, EstudianteDTO.class))
                .collect(Collectors.toList());
    }

    // 2. POST - Registrar nuevo estudiante
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public EstudianteDTO registrarEstudiante(@RequestBody EstudianteDTO estudianteDTO) {
        Estudiante estudiante = modelMapper.map(estudianteDTO, Estudiante.class);
        Estudiante saved = estudianteService.insert(estudiante);
        return modelMapper.map(saved, EstudianteDTO.class);
    }

    // 3. PUT - Actualizar estudiante
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public EstudianteDTO actualizarEstudiante(@PathVariable Long id, @RequestBody EstudianteDTO estudianteDTO) {
        estudianteDTO.setIdEstudiante(id);
        Estudiante estudiante = modelMapper.map(estudianteDTO, Estudiante.class);
        Estudiante updated = estudianteService.update(estudiante);
        return modelMapper.map(updated, EstudianteDTO.class);
    }

    // 4. DELETE - Eliminar estudiante
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void eliminarEstudiante(@PathVariable Long id) {
        estudianteService.delete(id);
    }

    // 5. GET - Buscar estudiante por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'PROFESOR')")
    public EstudianteDTO buscarPorId(@PathVariable Long id) {
        Estudiante estudiante = estudianteService.findById(id);
        return modelMapper.map(estudiante, EstudianteDTO.class);
    }

    // 6. GET - Buscar estudiantes por nombre (Query personalizada)
    @GetMapping("/buscar/nombre")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'PROFESOR')")
    public List<EstudianteDTO> buscarPorNombre(@RequestParam String nombre) {
        return estudianteService.findByNombre(nombre).stream()
                .map(est -> modelMapper.map(est, EstudianteDTO.class))
                .collect(Collectors.toList());
    }

    // 7. GET - Estudiantes con más inscripciones (Query personalizada)
    @GetMapping("/top-inscripciones")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<EstudianteDTO> estudiantesConMasInscripciones(@RequestParam(defaultValue = "2") int cantidad) {
        return estudianteService.findEstudiantesConMasInscripciones(cantidad).stream()
                .map(est -> modelMapper.map(est, EstudianteDTO.class))
                .collect(Collectors.toList());
    }
}
