package mx.uady.jpademo.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AlumnoRequest {

    @NotBlank
    @Size(min =3, max = 255)
    private String nombre;
/*
    @NotBlank
    @Size(min =8, max = 8)
    private String matricula;
*/  

    @NotBlank
    @Size(min =3, max = 3)
    private String licenciatura;

    private String password;

    public AlumnoRequest(){}


    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * @return the licenciatura
     */

     /**
      * @return the licenciatura
      */
     public String getLicenciatura() {
         return licenciatura;
     }/**
      * @param licenciatura the licenciatura to set
      */
     public void setLicenciatura(String licenciatura) {
         this.licenciatura = licenciatura;
     }

     /**
      * @return the password
      */
     public String getPassword() {
         return password;
     }
     /**
      * @param password the password to set
      */
     public void setPassword(String password) {
         this.password = password;
     }
}