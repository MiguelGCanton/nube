package mx.uady.jpademo.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * ProfesoresRequest
 */
public class UsuarioRequest {

    @NotBlank
    @Size(min = 3, max = 255)
    private String nombre;

    @NotBlank
    @Size(min = 3, max = 255)
    private String contrasenia;

    /**
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
}