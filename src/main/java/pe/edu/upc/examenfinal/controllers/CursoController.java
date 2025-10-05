package pe.edu.upc.examenfinal.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.examenfinal.dtos.CursoDTO;
import pe.edu.upc.examenfinal.entities.Curso;
import pe.edu.upc.examenfinal.servicesinterfaces.ICursoService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cursos")
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
public class CursoController {

    @Autowired
    private ICursoService cursoService;

    private ModelMapper modelMapper = new ModelMapper();

    // 1. GET - Listar todos los cursos
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'PROFESOR')")
    public List<CursoDTO> listarCursos() {
        return cursoService.list().stream()
                .map(curso -> modelMapper.map(curso, CursoDTO.class))
                .collect(Collectors.toList());
    }

    // 2. POST - Registrar nuevo curso
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROFESOR')")
    public CursoDTO registrarCurso(@RequestBody CursoDTO cursoDTO) {
        Curso curso = modelMapper.map(cursoDTO, Curso.class);
        Curso saved = cursoService.insert(curso);
        return modelMapper.map(saved, CursoDTO.class);
    }

    // 3. PUT - Actualizar curso
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROFESOR')")
    public CursoDTO actualizarCurso(@PathVariable Long id, @RequestBody CursoDTO cursoDTO) {
        cursoDTO.setIdCurso(id);
        Curso curso = modelMapper.map(cursoDTO, Curso.class);
        Curso updated = cursoService.update(curso);
        return modelMapper.map(updated, CursoDTO.class);
    }

    // 4. DELETE - Eliminar curso
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROFESOR')")
    public void eliminarCurso(@PathVariable Long id) {
        cursoService.delete(id);
    }

    // 5. GET - Buscar cursos por profesor (Query personalizada)
    @GetMapping("/profesor/{idProfesor}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'PROFESOR')")
    public List<CursoDTO> buscarCursosPorProfesor(@PathVariable Long idProfesor) {
        return cursoService.findCursosByProfesor(idProfesor).stream()
                .map(curso -> modelMapper.map(curso, CursoDTO.class))
                .collect(Collectors.toList());
    }

    // 6. GET - Buscar cursos activos (Query personalizada)
    @GetMapping("/activos")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'PROFESOR')")
    public List<CursoDTO> cursosActivos() {
        return cursoService.findCursosActivos(LocalDate.now()).stream()
                .map(curso -> modelMapper.map(curso, CursoDTO.class))
                .collect(Collectors.toList());
    }

    // 7. GET - Buscar cursos por créditos mínimos (Query personalizada)
    @GetMapping("/creditos-minimos")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CursoDTO> cursosPorCreditosMinimos(@RequestParam(defaultValue = "3") Integer creditos) {
        return cursoService.findByCreditosMinimos(creditos).stream()
                .map(curso -> modelMapper.map(curso, CursoDTO.class))
                .collect(Collectors.toList());
    }
}
