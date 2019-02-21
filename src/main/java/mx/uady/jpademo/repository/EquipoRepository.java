package mx.uady.jpademo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.uady.jpademo.model.Equipo;

@Repository
public interface EquipoRepository extends CrudRepository<Equipo, Integer> {

}