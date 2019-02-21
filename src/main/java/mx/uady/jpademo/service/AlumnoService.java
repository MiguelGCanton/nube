package mx.uady.jpademo.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.jpademo.model.Alumno;
import mx.uady.jpademo.model.Equipo;
import mx.uady.jpademo.model.Tutoria;
import mx.uady.jpademo.model.Usuario;
import mx.uady.jpademo.repository.AlumnoRepository;
import mx.uady.jpademo.repository.EquipoRepository;
import mx.uady.jpademo.repository.UsuarioRepository;

@Service
public class AlumnoService {

    final Logger LOG = LoggerFactory.getLogger(AlumnoService.class);

    @Autowired
    private AlumnoRepository alumnoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EquipoRepository equipoRepository;

    public List<Alumno> alumnos() {
        List<Alumno> alumnos = new LinkedList<>();
        alumnoRepository.findAll().iterator().forEachRemaining(alumnos::add);
        return alumnos;
    }

    public Alumno saveAlumno(int id, String nombre, String licenciatura, String password) {
        Alumno alumno = new Alumno();
        if (!alumnoRepository.existsById(id)) {
            alumno.setNombre(nombre);
            alumno.setId(id);
            alumno.setLicenciatura(licenciatura);

            Usuario user = new Usuario();
            int userId = (id);
            user.setId(userId);
            user.setUsuario(nombre + "@correo.uady.mx");
            user.setPassword(password);
            alumno.setUsuarioId(user);
            usuarioRepository.save(user);

            alumno = alumnoRepository.save(alumno);
        }

        return alumno;
    }

    public void deleteAlumno(int id) {
        alumnoRepository.deleteById(id);
    }

    public void editAlumno(int id, String nombre, String licenciatura) {
        Optional optional = alumnoRepository.findById(id);

        Alumno alumno = (Alumno) optional.get();
        alumno.setNombre(nombre);
        alumno.setLicenciatura(licenciatura);
        alumnoRepository.save(alumno);

    }

    public void asignarEquipo(int idAlumno, int idEquipo) {
        Optional optional = alumnoRepository.findById(idAlumno);
        Optional optionalEquipo = equipoRepository.findById(idEquipo);
        Alumno alumno = (Alumno) optional.get();
        Equipo equipo = (Equipo) optionalEquipo.get();
        alumno.setEquipoId(equipo);
        alumnoRepository.save(alumno);
    }

    public void desasignarEquipo(int idEquipo) {
        List<Alumno> alumnos = new LinkedList<>();
        alumnoRepository.findAll().iterator().forEachRemaining(alumnos::add);

        Boolean exist = alumnos.stream().filter((usuario) -> {
            if (usuario.getEquipoId() != null) {
                if (usuario.getEquipoId().getId() == idEquipo) {
                    usuario.setEquipoId(null);
                    alumnoRepository.save(usuario);
                }
            }
            return true;
        }).count() > 0;
    }

    public void asignarTutoria(Tutoria tutoria, int idAlumno) {
        Optional optional = alumnoRepository.findById(idAlumno);
        Alumno alumno = (Alumno) optional.get();
        alumno.getTutorias().add(tutoria);
        alumnoRepository.save(alumno);
    }

    public void desasignarTutoria(Tutoria tutoria) {
        List<Alumno> alumnos = new LinkedList<>();
        alumnoRepository.findAll().iterator().forEachRemaining(alumnos::add);

        Boolean exist = alumnos.stream().filter((usuario) -> {
            if (usuario.getTutorias().size() > 0) {
                usuario.getTutorias().remove(tutoria);
            }
            return true;
        }).count() > 0;
    }

    /*
     * final Logger LOG = LoggerFactory.getLogger(AlumnoService.class);
     * 
     * @Autowired private AlumnoRepository alumnoRepository;
     * 
     * public List<Alumno> getAlumnos(){ return alumnoRepository.getAlumnos(); }
     * 
     * 
     * public Alumno getAlumno(String id){ LOG.debug("Obtener alumno por id {}",
     * id); Optional<Alumno> optional = getAlumno().stream().filter(alumno -> {
     * return alumno.getId().equalsIgnoreCase(id); }).findFirst();
     * 
     * if(!optional.isPresent()){ LOG.error("El alumno con id {} no existe", id);
     * throw new Exception("alumno"); } return optional.get(); }
     * 
     * public Alumno saveAlumno(String matricula, String nombre){ List<Alumno>
     * alumnos = getAlumnos();
     * 
     * boolean existeMatricula = alumnos.stream().filter((alumno) -> { return
     * alumno.getMatricula().equals(matricula); }).count() > 0;
     * 
     * if(existeMatricula){ //LOG.error("El alumno con id {} no existe", id); throw
     * new RecursoExistenteException("alumno"); }
     * 
     * return alumnoRepository.saveAlumno(matricula, nombre); }
     * 
     * public void eliminarAlumno(Alumno alumno){
     * alumnoRepository.eliminarAlumno(alumno); }
     * 
     * public Alumno editarAlumno(AlumnoRequest request){ Alumno alumno =
     * getAlumno(request.getMatricula()); alumnoRepository.eliminarAlumno(alumno);
     * 
     * return saveAlumno(request.getMatricula(), request.getNombre()); }
     */
}