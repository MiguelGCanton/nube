package mx.uady.jpademo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import mx.uady.jpademo.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

}