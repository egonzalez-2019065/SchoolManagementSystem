package org.example.estudiante;

import org.example.persona.Persona;

import java.util.Date;

public class Estudiante extends Persona {

    private Estado estado;


    public enum Estado {
        MATRICULADO, INACTIVO, GRADUADO;
    }

    public Estudiante() {
        super();
    }

    public Estudiante(int id, String nombre, String apellido, Date fechaDeNacimiento, Estado estado) {
        super(id, nombre, apellido, fechaDeNacimiento);
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
