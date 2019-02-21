package mx.uady.jpademo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.jpademo.model.Profesor;
import mx.uady.jpademo.repository.ProfesorRepository;

/**
 * ProfesorService
 */

@Service
public class ProfesorService {

    final Logger LOG = LoggerFactory.getLogger(AlumnoService.class);

    @Autowired
    private ProfesorRepository profesorRepository;

    public Profesor saveProfesor(String nombre, int horas){
        Profesor profesor = new Profesor();
        profesor.setNombre(nombre);
        profesor.setId((int) (profesorRepository.count())+1);
        profesor.setHoras(horas);

        profesor = profesorRepository.save(profesor); 
        return profesor;
    }

    public void deleteAlumno(int id){
       profesorRepository.deleteById(id);
    }

    public void editProfesor(int id, String nombre, int horas){
        Optional optional = profesorRepository.findById(id);

        Profesor profesor = (Profesor) optional.get();
        
        profesor.setNombre(nombre);
        profesor.setHoras(horas);
        profesorRepository.save(profesor);
        
    }
}