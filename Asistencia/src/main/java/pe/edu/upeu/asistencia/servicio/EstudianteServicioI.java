package pe.edu.upeu.asistencia.servicio;

import pe.edu.upeu.asistencia.modelo.estudiante;

import java.util.List;

public interface EstudianteServicioI {

    void save(estudiante estudiante);

    List<estudiante> findAllEstudiantes();

    estudiante updateEstudiante(estudiante estudiante, int index);

    void delete(int index);

    estudiante findEstudianteById(int index);

}
