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
import mx.uady.jpademo.model.Profesor;
import mx.uady.jpademo.repository.AlumnoRepository;
import mx.uady.jpademo.repository.ProfesorRepository;
import mx.uady.jpademo.request.AlumnoRequest;
import mx.uady.jpademo.request.ProfesoresRequest;
import mx.uady.jpademo.service.AlumnoService;
import mx.uady.jpademo.service.ProfesorService;




@RestController
public class JpaResource {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private ProfesorService profesorService;

    @GetMapping("/alumnos")
    public List<Alumno> alumnos() {
        List<Alumno> alumnos = new LinkedList<>();
        alumnoRepository.findAll().iterator().forEachRemaining(alumnos::add);
        return alumnos;
    }

    @GetMapping("/profesores")
    public List<Profesor> profesores() {
        List<Profesor> profesores = new LinkedList<>();
        profesorRepository.findAll().iterator().forEachRemaining(profesores::add);
        return profesores;
    }

    @PostMapping("/alumnos")
    public String alumnosSet(@RequestBody AlumnoRequest request) throws URISyntaxException {
    
    
        Alumno alumno = alumnoService.saveAlumno( request.getNombre(), request.getLicenciatura(), request.getPassword() );
        //alumnoRepository.save(alumno);
        URI location = new URI("/alumnos" + alumno.getId());
        ResponseEntity<Alumno> response = ResponseEntity.created(location).body(alumno);
        return alumno.toString();
    }


    @DeleteMapping("/alumno/{id}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable String id){
        alumnoService.deleteAlumno(Integer.parseInt(id));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/alumno/edit/{id}")
    public ResponseEntity<Void> editarAlumno(@PathVariable String id, @RequestBody AlumnoRequest request){
        String nombre = request.getNombre();
        String licenciatura = request.getLicenciatura();
        
        alumnoService.editAlumno(Integer.parseInt(id), nombre, licenciatura);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/profesores")
    public String profesoresSet(@RequestBody ProfesoresRequest request) throws URISyntaxException {
    
    
        Profesor profesor = profesorService.saveProfesor(request.getNombre(), request.getHoras());
        //alumnoRepository.save(alumno);
        URI location = new URI("/profesor" + profesor.getId());
        ResponseEntity<Profesor> response = ResponseEntity.created(location).body(profesor);
        return profesor.toString();
    }

    @DeleteMapping("/profesor/{id}")
    public ResponseEntity<Void> eliminarProfesor(@PathVariable String id){
        profesorService.deleteAlumno(Integer.parseInt(id));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/profesor/edit/{id}")
    public ResponseEntity<Void> editarProfesor(@PathVariable String id, @RequestBody ProfesoresRequest request){
        String nombre = request.getNombre();
        int horas = request.getHoras();
        
        profesorService.editProfesor(Integer.parseInt(id), nombre, horas);

        return ResponseEntity.ok().build();
    }


/*
    @DeleteMapping("/profesores/{id}")
    public ResponseEntity<Void> eliminarProfesor(@PathVariable String id){
        
        Alumno alumno = alumnoRepository.getAlumno(id);
        alumnoRepository.eliminarAlumno(alumno);
        return ResponseEntity.ok().build();
    }*/

    

}