package org.example.excepciones;

public class EstudianteYaInscrito extends Exception{
    public EstudianteYaInscrito(String mensaje){
        super(mensaje);
    }
}
