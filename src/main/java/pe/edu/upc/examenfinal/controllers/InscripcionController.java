package pe.edu.upc.examenfinal.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.examenfinal.dtos.InscripcionDTO;
import pe.edu.upc.examenfinal.entities.Inscripcion;
import pe.edu.upc.examenfinal.servicesinterfaces.IInscripcionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inscripciones")
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
public class InscripcionController {

    @Autowired
    private IInscripcionService inscripcionService;

    private ModelMapper modelMapper = new ModelMapper();

    // 1. GET - Listar todas las inscripciones
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROFESOR')")
    public List<InscripcionDTO> listarInscripciones() {
        return inscripcionService.list().stream()
                .map(insc -> modelMapper.map(insc, InscripcionDTO.class))
                .collect(Collectors.toList());
    }

    // 2. POST - Registrar nueva inscripción
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'PROFESOR')")
    public InscripcionDTO registrarInscripcion(@RequestBody InscripcionDTO inscripcionDTO) {
        Inscripcion inscripcion = modelMapper.map(inscripcionDTO, Inscripcion.class);
        Inscripcion saved = inscripcionService.insert(inscripcion);
        return modelMapper.map(saved, InscripcionDTO.class);
    }

    // 3. PUT - Actualizar inscripción
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROFESOR')")
    public InscripcionDTO actualizarInscripcion(@PathVariable Long id, @RequestBody InscripcionDTO inscripcionDTO) {
        inscripcionDTO.setIdInscripcion(id);
        Inscripcion inscripcion = modelMapper.map(inscripcionDTO, Inscripcion.class);
        Inscripcion updated = inscripcionService.update(inscripcion);
        return modelMapper.map(updated, InscripcionDTO.class);
    }

    // 4. DELETE - Eliminar inscripción
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROFESOR')")
    public void eliminarInscripcion(@PathVariable Long id) {
        inscripcionService.delete(id);
    }

    // 5. GET - Inscripciones por estudiante (Query personalizada)
    @GetMapping("/estudiante/{idEstudiante}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROFESOR')")
    public List<InscripcionDTO> inscripcionesPorEstudiante(@PathVariable Long idEstudiante) {
        return inscripcionService.findInscripcionesByEstudiante(idEstudiante).stream()
                .map(insc -> modelMapper.map(insc, InscripcionDTO.class))
                .collect(Collectors.toList());
    }

    // 6. GET - Inscripciones por curso (Query personalizada)
    @GetMapping("/curso/{idCurso}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROFESOR')")
    public List<InscripcionDTO> inscripcionesPorCurso(@PathVariable Long idCurso) {
        return inscripcionService.findInscripcionesByCurso(idCurso).stream()
                .map(insc -> modelMapper.map(insc, InscripcionDTO.class))
                .collect(Collectors.toList());
    }

    // 7. GET - Inscripciones por estado (Query personalizada)
    @GetMapping("/estado/{estado}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROFESOR')")
    public List<InscripcionDTO> inscripcionesPorEstado(@PathVariable String estado) {
        return inscripcionService.findByEstado(estado).stream()
                .map(insc -> modelMapper.map(insc, InscripcionDTO.class))
                .collect(Collectors.toList());
    }

    // 8. GET - Promedio de calificaciones por curso (Query personalizada)
    @GetMapping("/promedio-curso/{idCurso}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROFESOR')")
    public Double promedioCalificacionesCurso(@PathVariable Long idCurso) {
        return inscripcionService.findPromedioCalificacionesByCurso(idCurso);
    }

    // 9. GET - Inscripciones con calificación mínima (Query personalizada)
    @GetMapping("/calificacion-minima")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROFESOR')")
    public List<InscripcionDTO> inscripcionesConCalificacionMinima(@RequestParam(defaultValue = "10.5") Double calificacion) {
        return inscripcionService.findByCalificacionMinima(calificacion).stream()
                .map(insc -> modelMapper.map(insc, InscripcionDTO.class))
                .collect(Collectors.toList());
    }

    // 10. GET - Buscar inscripción por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'PROFESOR', 'ESTUDIANTE')")
    public InscripcionDTO buscarPorId(@PathVariable Long id) {
        Inscripcion inscripcion = inscripcionService.findById(id);
        return modelMapper.map(inscripcion, InscripcionDTO.class);
    }
}
