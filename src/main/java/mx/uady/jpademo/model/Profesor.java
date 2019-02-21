package mx.uady.jpademo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "profesores")
public class Profesor {

    @Id
    private Integer id;

    @Column
    private String nombre;

    @Column
    private int horas;

    @JsonManagedReference
    @OneToMany(mappedBy = "profesor")
    private List<Tutoria> tutorias;

    public Profesor() {
    }

    public Profesor(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * @return the horas
     */
    public int getHoras() {
        return horas;
    }
    /**
     * @param horas the horas to set
     */
    public void setHoras(int horas) {
        this.horas = horas;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    

    public Profesor id(Integer id) {
        this.id = id;
        return this;
    }

    public Profesor nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    /**
     * @param tutorias the tutorias to set
     */
    public void setTutorias(List<Tutoria> tutorias) {
        this.tutorias = tutorias;
    }

    public List<Tutoria> getTutorias(){
        return this.tutorias;
    }
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            "}";
    }

}