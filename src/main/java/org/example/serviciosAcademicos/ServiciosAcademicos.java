package org.example.serviciosAcademicos;

import org.example.curso.Curso;
import org.example.estudiante.Estudiante;
import org.example.excepciones.EstudianteNoInscrito;
import org.example.excepciones.EstudianteYaInscrito;

public interface ServiciosAcademicos {

    // Agrega un estudiante si aún no está matriculado
    public void matricularEstudiante(Estudiante estudiante);

    // Agrega un curso, si aún no está creado
    public void agregarCurso(Curso curso);

    // Inscribe a un estudiante a un curso
    public void inscribirEstudiante(Estudiante estudiante, int idCurso) throws EstudianteYaInscrito;

    // Desinscribe a un estudiante de un curso
    public void desinscribirEstudiante(int idEstudiante, int idCurso) throws EstudianteNoInscrito;

}
