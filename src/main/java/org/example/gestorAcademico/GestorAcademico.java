package org.example.gestorAcademico;

import org.example.curso.Curso;
import org.example.estudiante.Estudiante;
import org.example.excepciones.EstudianteNoInscrito;
import org.example.excepciones.EstudianteYaInscrito;
import org.example.serviciosAcademicos.ServiciosAcademicos;

import java.util.HashMap;
import java.util.List;

public class GestorAcademico  {

    private List<Estudiante> estudiantes;

    private List<Curso> cursos;

    private HashMap<String, Estudiante> cursoEstudiante;

    public GestorAcademico() {

    }

    public GestorAcademico(List<Estudiante> estudiantes, List<Curso> cursos,
                           HashMap<String, Estudiante> cursoEstudiante) {
        this.estudiantes = estudiantes;
        this.cursos = cursos;
        this.cursoEstudiante = cursoEstudiante;
    }
}
