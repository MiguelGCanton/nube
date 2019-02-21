package mx.uady.jpademo.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.jpademo.model.Profesor;
import mx.uady.jpademo.repository.UsuarioRepository;
import mx.uady.jpademo.model.Usuario;

/**
 * ProfesorService
 */

@Service
public class UsuarioService {

    final Logger LOG = LoggerFactory.getLogger(AlumnoService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Boolean login(String nombre, String contrasenia) {
        /*
         * List<Usuario> list = usuarioRepository.findAll();
         * 
         */
        List<Usuario> usuarios = new LinkedList<>();
        usuarioRepository.findAll().iterator().forEachRemaining(usuarios::add);

        Boolean exist = usuarios.stream().filter((usuario) -> {
            System.out.println(usuario + " " + nombre + " " + contrasenia);
            return usuario.getUsuario().compareTo(nombre) == 0 && usuario.getPassword().compareTo(contrasenia) == 0;
        }).count() > 0;

        return exist;
    }
}