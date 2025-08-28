package pe.edu.upeu.encapsulamiento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class trabajador {
    private String nombre;
    private int edad;
    private String apellido;
    private String area;
    private char genero;

    @Override
    public String toString(){
        return "tiene las siguientes caracteristicas : \n" +
                "nombre:"+nombre+"\n"+
                "apellido"+apellido+"\n"
                ;
    }
}
