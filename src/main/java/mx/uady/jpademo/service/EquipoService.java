package mx.uady.jpademo.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.jpademo.model.Equipo;
import mx.uady.jpademo.repository.EquipoRepository;

@Service
public class EquipoService {

    final Logger LOG = LoggerFactory.getLogger(EquipoService.class);

    @Autowired
    private EquipoRepository equipoRepository;

    public List<Equipo> equipos() {
        List<Equipo> equipos = new LinkedList<>();
        equipoRepository.findAll().iterator().forEachRemaining(equipos::add);
        return equipos;
    }

    public Equipo saveEquipo(int id, String modelo) {
        Equipo equipo = new Equipo();
        if (!equipoRepository.existsById(id)) {
            equipo.setId(id);
            equipo.setModelo(modelo);
            equipoRepository.save(equipo);
        }

        return equipo;
    }

    public void deleteEquipo(int id) {
        equipoRepository.deleteById(id);
    }

    public void editEquipo(int id, String modelo) {
        Optional optional = equipoRepository.findById(id);

        Equipo equipo = (Equipo) optional.get();
        equipo.setId(id);
        equipo.setModelo(modelo);
        equipoRepository.save(equipo);

    }
}