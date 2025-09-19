package pe.edu.upeu.asistencia.servicio;

import pe.edu.upeu.asistencia.dto.PersonaDto;
import pe.edu.upeu.asistencia.modelo.Empleado;

import java.util.List;

public interface EmpleadoServicioI {
    void save(Empleado empleado); //C
    List<Empleado> findAll(); //R
    Empleado update(Empleado empleado, int index); //U
    void delete(int index); //D

    Empleado findById(int index); //B
    PersonaDto findByDni(String dni);

}
