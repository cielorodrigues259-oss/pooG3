package pe.edu.upeu.asistencia.repositorio;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import pe.edu.upeu.asistencia.modelo.estudiante;

import java.util.ArrayList;
import java.util.List;

public class estudianteRepository {
    List<estudiante> estudiantes = new ArrayList<>();

    List<estudiante> findAllEstudiantes() {
        estudiantes.add(new estudiante(
                new SimpleStringProperty("Juan"),
                new SimpleBooleanProperty(true)));
        return estudiantes;
    }
}
