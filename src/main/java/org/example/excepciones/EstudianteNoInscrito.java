package org.example.excepciones;

public class EstudianteNoInscrito extends Exception {
    public EstudianteNoInscrito(String mensaje) {
        super(mensaje);
    }
}
