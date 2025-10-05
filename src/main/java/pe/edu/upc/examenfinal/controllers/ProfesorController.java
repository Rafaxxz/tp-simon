package pe.edu.upc.examenfinal.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.examenfinal.dtos.ProfesorDTO;
import pe.edu.upc.examenfinal.entities.Profesor;
import pe.edu.upc.examenfinal.servicesinterfaces.IProfesorService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/profesores")
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
public class ProfesorController {

    @Autowired
    private IProfesorService profesorService;

    private ModelMapper modelMapper = new ModelMapper();

    // 1. GET - Listar todos los profesores
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'PROFESOR')")
    public List<ProfesorDTO> listarProfesores() {
        return profesorService.list().stream()
                .map(prof -> modelMapper.map(prof, ProfesorDTO.class))
                .collect(Collectors.toList());
    }

    // 2. POST - Registrar nuevo profesor
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ProfesorDTO registrarProfesor(@RequestBody ProfesorDTO profesorDTO) {
        Profesor profesor = modelMapper.map(profesorDTO, Profesor.class);
        Profesor saved = profesorService.insert(profesor);
        return modelMapper.map(saved, ProfesorDTO.class);
    }

    // 3. PUT - Actualizar profesor
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROFESOR')")
    public ProfesorDTO actualizarProfesor(@PathVariable Long id, @RequestBody ProfesorDTO profesorDTO) {
        profesorDTO.setIdProfesor(id);
        Profesor profesor = modelMapper.map(profesorDTO, Profesor.class);
        Profesor updated = profesorService.update(profesor);
        return modelMapper.map(updated, ProfesorDTO.class);
    }

    // 4. DELETE - Eliminar profesor
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void eliminarProfesor(@PathVariable Long id) {
        profesorService.delete(id);
    }

    // 5. GET - Buscar por especialidad (Query personalizada)
    @GetMapping("/especialidad/{especialidad}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'PROFESOR')")
    public List<ProfesorDTO> buscarPorEspecialidad(@PathVariable String especialidad) {
        return profesorService.findByEspecialidad(especialidad).stream()
                .map(prof -> modelMapper.map(prof, ProfesorDTO.class))
                .collect(Collectors.toList());
    }

    // 6. GET - Profesores con mínimo de cursos (Query personalizada)
    @GetMapping("/min-cursos")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<ProfesorDTO> profesoresConMinimoCursos(@RequestParam(defaultValue = "1") int cantidad) {
        return profesorService.findProfesoresConMinimoCursos(cantidad).stream()
                .map(prof -> modelMapper.map(prof, ProfesorDTO.class))
                .collect(Collectors.toList());
    }
}
