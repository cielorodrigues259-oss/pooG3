package pe.edu.upeu.asistencia.repositorio;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import pe.edu.upeu.asistencia.dto.PersonaDto;
import pe.edu.upeu.asistencia.modelo.Empleado;
import pe.edu.upeu.asistencia.utils.ConsultaDNI;

import java.util.ArrayList;
import java.util.List;

public abstract class EmpleadoRepository extends ConsultaDNI {
   protected List<Empleado> empleados =new ArrayList<>();

   public List<Empleado> findAll(){
       empleados.add(new Empleado(
               new SimpleStringProperty("70102030"),
               new SimpleStringProperty("David"),
               new SimpleStringProperty("Perez"),
               new SimpleStringProperty("Cajero"),
               new SimpleBooleanProperty(true)
                ));
       return empleados;
   }

   public PersonaDto findByDni(String dni){
       return consultarDNI(dni);
   }


}
