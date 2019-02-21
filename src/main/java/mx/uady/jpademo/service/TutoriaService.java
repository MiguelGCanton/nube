package mx.uady.jpademo.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.jpademo.model.Tutoria;
import mx.uady.jpademo.model.Tutoria.TutoriaId;
import mx.uady.jpademo.repository.TutoriaRepository;

@Service
public class TutoriaService {

    final Logger LOG = LoggerFactory.getLogger(TutoriaService.class);

    @Autowired
    private TutoriaRepository tutoriaRepository;

    public List<Tutoria> tutorias() {
        List<Tutoria> tutorias = new LinkedList<>();
        tutoriaRepository.findAll().iterator().forEachRemaining(tutorias::add);
        return tutorias;
    }

    public Tutoria saveTutoria(int idAlumno, int idMaestro, int horas) {
        Tutoria tutoria = new Tutoria();
        tutoria.setId(new TutoriaId());
        tutoria.getId().setAlumnoId(idAlumno);
        tutoria.getId().setProfesorId(idMaestro);
        tutoria.setHoras(horas);
        tutoriaRepository.save(tutoria);
        return tutoria;
    }

    public void deleteTutoria(int id) {
        tutoriaRepository.deleteById(id);
    }

    public void editTutoria(int id, int horas) {
        Optional optional = tutoriaRepository.findById(id);

        Tutoria Tutoria = (Tutoria) optional.get();
        Tutoria.setHoras(horas);
        tutoriaRepository.save(Tutoria);

    }
}