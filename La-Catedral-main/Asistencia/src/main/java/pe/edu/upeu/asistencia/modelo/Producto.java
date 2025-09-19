package pe.edu.upeu.asistencia.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Producto {
    private String codigoProducto;
    private String nombre;
    private int cantidad;
    private String unidadDeMedida;
}
