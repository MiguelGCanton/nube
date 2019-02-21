package mx.uady.jpademo.request;

import javax.validation.constraints.NotBlank;

/**
 * TutoriaRequest
 */
public class TutoriaRequest {

    @NotBlank
    private int id_alumno;
    @NotBlank
    int id_maestro;
    @NotBlank
    int horas;

    public TutoriaRequest() {

    }

    /**
     * @return the horas
     */
    public int getHoras() {
        return horas;
    }

    /**
     * @return the id_alumno
     */
    public int getId_alumno() {
        return id_alumno;
    }

    /**
     * @return the id_maestro
     */
    public int getId_maestro() {
        return id_maestro;
    }

    /**
     * @param horas the horas to set
     */
    public void setHoras(int horas) {
        this.horas = horas;
    }

    /**
     * @param id_alumno the id_alumno to set
     */
    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    /**
     * @param id_maestro the id_maestro to set
     */
    public void setId_maestro(int id_maestro) {
        this.id_maestro = id_maestro;
    }
}