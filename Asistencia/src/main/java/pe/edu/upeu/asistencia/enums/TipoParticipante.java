package pe.edu.upeu.asistencia.enums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TipoParticipante {
    Asistente("asistente"),
    ORGANIZADOR( "organizador"),
    PONENTE( "PONENTE");
    private String descripcion;

}
