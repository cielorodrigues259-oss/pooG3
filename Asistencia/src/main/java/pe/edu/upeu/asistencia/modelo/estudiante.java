package  pe.edu.upeu.asistencia.modelo;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data

public class estudiante {
    private SimpleStringProperty Nombre;
    private SimpleBooleanProperty estado;
}


