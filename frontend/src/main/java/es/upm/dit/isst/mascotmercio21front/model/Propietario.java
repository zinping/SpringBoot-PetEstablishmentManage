package es.upm.dit.isst.mascotmercio21front.model;

import javax.validation.constraints.Email;
import java.util.Date;

public class Propietario {

    private int id;
    private String nombre;
    private String apellidos;
    private Date fechanacimiento;
    @Email
    private String email;
    private String contraseña;

    public Propietario(){}

    public Propietario(int id, String nombre, String apellidos,Date fechanacimiento, String email, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechanacimiento = fechanacimiento;
        this.email = email;
        this.contraseña = contraseña;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento){
        this.fechanacimiento = fechanacimiento;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
