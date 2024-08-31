package org.example.gestorAcademico;

import org.example.curso.Curso;
import org.example.estudiante.Estudiante;
import org.example.excepciones.EstudianteNoInscrito;
import org.example.excepciones.EstudianteYaInscrito;
import org.example.serviciosAcademicos.ServiciosAcademicos;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class GestorAcademico implements ServiciosAcademicos {

    private List<Estudiante> estudiantes;
    private List<Curso> cursos;
    private HashMap<String, Estudiante> cursoEstudiante;

    // Constructor por defecto
    public GestorAcademico() {
        this.estudiantes = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.cursoEstudiante = new HashMap<>();
    }

    public GestorAcademico(List<Estudiante> estudiantes, List<Curso> cursos,
                           HashMap<String, Estudiante> cursoEstudiante) {
        this.estudiantes = estudiantes;
        this.cursos = cursos;
        this.cursoEstudiante = cursoEstudiante;
    }

    // Método que devuelve los estudiantes ya matriculados
    public void mostrarEstudiantes() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        for (Estudiante estudiante : estudiantes) {
            String fechaDeNacimiento = formato.format(estudiante.getFechaDeNacimiento());
            System.out.println( estudiante.getId() + " " + estudiante.getNombre() + " " + estudiante.getApellido() + " " + fechaDeNacimiento + " " + estudiante.getEstado());
        }
    }

    // Método que devuelve los cursos agregados
    public void mostrarCurso() {
        int contador = 0;
        for (Curso curso : cursos) {
            contador ++;
            System.out.println( contador + " " + curso.getNombre());
        }
    }

    // Método que muestra los estudiantes y los cursos en los que están matriculados
    public void mostrarEstudiantesYCursos() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        // Crear un HashMap para mapear estudiantes a cursos
        HashMap<Estudiante, List<Curso>> estudianteCursos = new HashMap<>();

        // Inicializar el HashMap
        for (Estudiante estudiante : estudiantes) {
            estudianteCursos.put(estudiante, new ArrayList<>());
        }

        // Asociar los cursos a los estudiantes
        for (String clave : cursoEstudiante.keySet()) {
            Estudiante estudiante = cursoEstudiante.get(clave);
            Curso curso = buscarCursoPorId(Integer.parseInt(clave.split("-")[1]));
            if (estudiante != null && curso != null) {
                estudianteCursos.get(estudiante).add(curso);
            }
        }

        // Mostrar los estudiantes y los cursos en los que están inscritos
        for (Estudiante estudiante : estudianteCursos.keySet()) {
            String fechaDeNacimiento = formato.format(estudiante.getFechaDeNacimiento());
            List<Curso> cursosInscritos = estudianteCursos.get(estudiante);

            System.out.println(estudiante.getId() + " " + estudiante.getNombre() + " " + estudiante.getApellido() + " " + fechaDeNacimiento + " " + estudiante.getEstado());
            if (cursosInscritos.isEmpty()) {
                System.out.println("No está inscrito en ningún curso.");
            } else {
                System.out.println("Cursos inscritos:");
                for (Curso curso : cursosInscritos) {
                    System.out.println("  - " + curso.getNombre());
                }
            }
            System.out.println();
        }
    }


    // Método que matricula a un estudiante
    @Override
    public void matricularEstudiante(Estudiante estudiante) {
        if (estudiantes.contains(estudiante)) {
            System.out.println("Estudiante: " + estudiante.getNombre() + " ya está matriculado");
        } else {
            estudiante.setEstado(Estudiante.Estado.MATRICULADO);
            estudiantes.add(estudiante);
        }
    }

    // Método que crea un curso
    @Override
    public void agregarCurso(Curso curso) {
        if (cursos.contains(curso)) {
            System.out.println("Curso: " + curso.getNombre() + " ya existe");
        } else {
            cursos.add(curso);
        }
    }
    // Busca un curso por ID
    private Curso buscarCursoPorId(int idCurso) {
        for (Curso curso : cursos) {
            if (curso.getId() == idCurso) {
                return curso;
            }
        }
        return null;
    }

    // Busca un estudiante por id
    private Estudiante buscarEstudiantePorId(int idEstudiante) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId() == idEstudiante) {
                return estudiante;
            }
        }
        return null;
    }

    @Override
    public void inscribirEstudiante(Estudiante estudiante, int idCurso) throws EstudianteYaInscrito {
        if(!estudiantes.contains(estudiante)) {
            throw new EstudianteYaInscrito("El estudiante: " + estudiante.getNombre() + " no está matriculado");
        }

        Curso curso = buscarCursoPorId(idCurso);
        if(curso == null) {
            System.out.println("Curso no encontado");
        }

        String clave = estudiante.getId() + "-" +  idCurso;

        if(cursoEstudiante.containsKey(clave)) {
            throw new EstudianteYaInscrito("El estudiante: " + estudiante.getNombre() + " ya está inscrito en el curso: " + curso.getNombre());
        }

        cursoEstudiante.put(clave, estudiante);
        System.out.println("El estudiante " + estudiante.getNombre() + " " + " fue inscrito en el curso: " + curso.getNombre());
    }

    @Override
    public void desinscribirEstudiante(int idEstudiante, int idCurso) throws EstudianteNoInscrito {
        // Busca un estudiante por su id
        Estudiante estudiante = buscarEstudiantePorId(idEstudiante);
        if(estudiante == null) {
            System.out.println("Estudiante no encontado");
        }

        // Busca un curso por su id
        Curso curso = buscarCursoPorId(idCurso);
        if(curso == null) {
            System.out.println("Curso no encontado");
        }

        String clave = idEstudiante + "-" + idCurso;
        if(!cursoEstudiante.containsKey(clave)) {
            throw new EstudianteNoInscrito("El estudiante no está inscrito en el curso");
        }

        cursoEstudiante.remove(clave);
        System.out.println("Estudiante: " + estudiante.getNombre() + " fue desinscrito del curso: " + curso.getNombre());
    }
}
