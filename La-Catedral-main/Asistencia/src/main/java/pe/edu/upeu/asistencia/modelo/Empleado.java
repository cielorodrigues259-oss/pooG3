package pe.edu.upeu.asistencia.modelo;

import jakarta.validation.constraints.NotBlank;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upeu.asistencia.componente.validacion.DNI;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Empleado {
    @NotBlank(message = "El DNI no puede estar vacío.")
    @DNI
    private StringProperty dni;
    @NotBlank(message = "El nombre no puede estar vacío.")
    private StringProperty nombre;
    @NotBlank(message = "Apellidos no puede estar vacío.")
    private StringProperty apellidos;
    private StringProperty puesto;
    private BooleanProperty estado = new SimpleBooleanProperty(true);
}
