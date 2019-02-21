package mx.uady.jpademo.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * ProfesoresRequest
 */
public class ProfesoresRequest {

    @NotBlank
    @Size(min =3, max = 255)
    private String nombre;

    private int horas;

    /**
     * @return the horas
     */
    public int getHoras() {
        return horas;
    }
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * @param horas the horas to set
     */
    public void setHoras(int horas) {
        this.horas = horas;
    }/**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}