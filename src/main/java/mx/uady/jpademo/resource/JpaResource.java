package mx.uady.jpademo.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mx.uady.jpademo.model.Alumno;
import mx.uady.jpademo.model.Equipo;
import mx.uady.jpademo.model.Profesor;
import mx.uady.jpademo.model.Tutoria;
import mx.uady.jpademo.repository.AlumnoRepository;
import mx.uady.jpademo.repository.ProfesorRepository;
import mx.uady.jpademo.request.AlumnoRequest;
import mx.uady.jpademo.request.EquipoRequest;
import mx.uady.jpademo.request.ProfesoresRequest;
import mx.uady.jpademo.request.TutoriaRequest;
import mx.uady.jpademo.request.UsuarioRequest;
import mx.uady.jpademo.service.AlumnoService;
import mx.uady.jpademo.service.EquipoService;
import mx.uady.jpademo.service.ProfesorService;
import mx.uady.jpademo.service.TutoriaService;
import mx.uady.jpademo.service.UsuarioService;

@RestController
public class JpaResource {

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private TutoriaService tutoriaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/alumnos")
    public List<Alumno> alumnos() {
        List<Alumno> alumnos = alumnoService.alumnos();
        return alumnos;
    }

    @PostMapping("/alumnos")
    public String alumnosSet(@RequestBody AlumnoRequest request) throws URISyntaxException {

        Alumno alumno = alumnoService.saveAlumno(request.getId(), request.getNombre(), request.getLicenciatura(),
                request.getPassword());
        // alumnoRepository.save(alumno);
        URI location = new URI("/alumnos" + alumno.getId());
        ResponseEntity<Alumno> response = ResponseEntity.created(location).body(alumno);
        return alumno.toString();
    }

    @DeleteMapping("/alumno/{id}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable String id) {
        alumnoService.deleteAlumno(Integer.parseInt(id));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/alumno/edit/{id}")
    public ResponseEntity<Void> editarAlumno(@PathVariable String id, @RequestBody AlumnoRequest request) {
        String nombre = request.getNombre();
        String licenciatura = request.getLicenciatura();

        alumnoService.editAlumno(Integer.parseInt(id), nombre, licenciatura);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/profesores")
    public List<Profesor> profesores() {
        List<Profesor> profesores = profesorService.profesores();
        return profesores;
    }

    @PostMapping("/profesores")
    public String profesoresSet(@RequestBody ProfesoresRequest request) throws URISyntaxException {

        Profesor profesor = profesorService.saveProfesor(request.getNombre(), request.getHoras(), request.getId());
        // alumnoRepository.save(alumno);
        URI location = new URI("/profesor" + profesor.getId());
        ResponseEntity<Profesor> response = ResponseEntity.created(location).body(profesor);
        return profesor.toString();
    }

    @DeleteMapping("/profesor/{id}")
    public ResponseEntity<Void> eliminarProfesor(@PathVariable String id) {
        profesorService.deleteAlumno(Integer.parseInt(id));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/profesor/edit/{id}")
    public ResponseEntity<Void> editarProfesor(@PathVariable String id, @RequestBody ProfesoresRequest request) {
        String nombre = request.getNombre();
        int horas = request.getHoras();

        profesorService.editProfesor(Integer.parseInt(id), nombre, horas);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/equipos")
    public List<Equipo> equipos() {
        List<Equipo> equipos = equipoService.equipos();
        return equipos;
    }

    @PostMapping("/equipos")
    public String equiposSet(@RequestBody EquipoRequest request) throws URISyntaxException {

        Equipo equipo = equipoService.saveEquipo(request.getId(), request.getModelo());
        // alumnoRepository.save(alumno);
        URI location = new URI("/profesor" + equipo.getId());
        ResponseEntity<Equipo> response = ResponseEntity.created(location).body(equipo);
        return equipo.toString();
    }

    @DeleteMapping("/equipos/{id}")
    public ResponseEntity<Void> eliminarEquipo(@PathVariable String id) {
        alumnoService.desasignarEquipo(Integer.valueOf(id));
        equipoService.deleteEquipo(Integer.parseInt(id));

        return ResponseEntity.ok().build();
    }

    @PutMapping("/equipos/edit/{id}")
    public ResponseEntity<Void> editarProfesor(@PathVariable String id, @RequestBody EquipoRequest request) {

        equipoService.editEquipo(Integer.valueOf(id), request.getModelo());

        return ResponseEntity.ok().build();
    }

    @PutMapping("/alumnos/{idAlumno}/asignarEquipo/{idEquipo}")
    public ResponseEntity<Void> asignarEquipo(@PathVariable String idAlumno, @PathVariable String idEquipo) {
        alumnoService.asignarEquipo(Integer.valueOf(idAlumno), Integer.valueOf(idEquipo));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tutorias")
    public List<Tutoria> tutorias() {

        List<Tutoria> tutorias = tutoriaService.tutorias();
        return tutorias;
    }

    @PostMapping("/tutorias")
    public String tutoriasSet(@RequestBody TutoriaRequest request) throws URISyntaxException {

        Tutoria tutoria = tutoriaService.saveTutoria(request.getId_alumno(), request.getId_maestro(),
                request.getHoras());
        profesorService.asignarTutoria(tutoria, request.getId_maestro());
        alumnoService.asignarTutoria(tutoria, request.getId_alumno());
        // alumnoRepository.save(alumno);
        URI location = new URI("/profesor" + tutoria.getId());
        ResponseEntity<Tutoria> response = ResponseEntity.created(location).body(tutoria);
        return tutoria.toString();
    }

    @PostMapping("/login")
    public Boolean log(@RequestBody UsuarioRequest request) {
        String nombre = request.getNombre();
        String contrasenia = request.getContrasenia();
        return usuarioService.login(nombre, contrasenia);
    }
    /*
     * @DeleteMapping("/equipos/{id}") public ResponseEntity<Void>
     * eliminarTutoria(@PathVariable String id) {
     * equipoService.deleteEquipo(Integer.parseInt(id)); return
     * ResponseEntity.ok().build(); }
     * 
     * @PutMapping("/equipos/edit/{id}") public ResponseEntity<Void>
     * editarProfesor(@PathVariable String id, @RequestBody EquipoRequest request) {
     * 
     * equipoService.editEquipo(Integer.valueOf(id), request.getModelo());
     * 
     * return ResponseEntity.ok().build(); }
     */

    /*
     * @DeleteMapping("/profesores/{id}") public ResponseEntity<Void>
     * eliminarProfesor(@PathVariable String id){
     * 
     * Alumno alumno = alumnoRepository.getAlumno(id);
     * alumnoRepository.eliminarAlumno(alumno); return ResponseEntity.ok().build();
     * }
     */

}