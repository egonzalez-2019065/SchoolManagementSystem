package org.example;

import org.example.curso.Curso;
import org.example.estudiante.Estudiante;
import org.example.excepciones.EstudianteNoInscrito;
import org.example.excepciones.EstudianteYaInscrito;
import org.example.gestorAcademico.GestorAcademico;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Main {
    public static void main(String[] args) {
        // Creando la instancia de los estudiantes
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Estudiante estudianteUno;
        Estudiante estudianteDos;
        try {
            estudianteUno = new Estudiante(1, "Jorge", "González",
                   sdf.parse("2005-07-29"), Estudiante.Estado.INACTIVO);
            estudianteDos = new Estudiante(2, "Mario", "García",
                    sdf.parse("2003-02-10"), Estudiante.Estado.INACTIVO);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // Creando la instancia para los cursos
        Curso mate = new Curso(1, "Matemática",
                "Curso de matemática", 100, 1 );
        Curso lenguaje = new Curso(2, "Lenguaje",
                "Curso de Lenguaje", 100, 1 );

        // Matriculando los estudiantes
        GestorAcademico gestorAcademico = new GestorAcademico();
        gestorAcademico.matricularEstudiante(estudianteUno);
        gestorAcademico.matricularEstudiante(estudianteDos);
        gestorAcademico.matricularEstudiante(estudianteUno);

        // Creando cursos
        gestorAcademico.agregarCurso(mate);
        gestorAcademico.agregarCurso(lenguaje);

        // Mostrando los datos recabados
        System.out.println("-------------- Estudiantes matriculados --------------");
        gestorAcademico.mostrarEstudiantes();
        System.out.println("-------------- Cursos creados --------------");
        gestorAcademico.mostrarCurso();

        // Inscribiendo alumnos a cursos
        System.out.println("-------------- Alumnos asignados a cursos --------------");
        try {
            gestorAcademico.inscribirEstudiante(estudianteUno, 1);
            gestorAcademico.inscribirEstudiante(estudianteUno, 2);
            gestorAcademico.inscribirEstudiante(estudianteDos, 1);
            gestorAcademico.inscribirEstudiante(estudianteDos, 2);
        } catch (EstudianteYaInscrito e) {
            System.out.println(e.getMessage());
        }

        // Desinscribiendo alumnos a cursos
        System.out.println("-------------- Alumnos desasignados a cursos --------------");
        try {
            gestorAcademico.desinscribirEstudiante(estudianteUno.getId(), 1);
        } catch (EstudianteNoInscrito e) {
            System.out.println(e.getMessage());
        }

        System.out.println("-------------- Alumnos y sus cursos --------------");
        // Estudiantes y sus cursos
        gestorAcademico.mostrarEstudiantesYCursos();


    }
}