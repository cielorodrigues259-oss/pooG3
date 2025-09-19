package pe.edu.upeu.asistencia.servicio;

import org.springframework.stereotype.Service;
import pe.edu.upeu.asistencia.modelo.Empleado;
import pe.edu.upeu.asistencia.repositorio.EmpleadoRepository;
import java.util.List;

@Service
public class EmpleadoServicioImp extends EmpleadoRepository
        implements EmpleadoServicioI {
    @Override
    public void save(Empleado empleado) {
        empleados.add(empleado);
    }
    @Override
    public Empleado update(Empleado empleado, int index) {
        return empleados.set(index, empleado);
    }
    @Override
    public void delete(int index) {
        empleados.remove(index);
    }
    @Override
    public Empleado findById(int index) {
        return empleados.get(index);
    }

    @Override
    public List<Empleado> findAll(){
        if(empleados.isEmpty()){
            return super.findAll();
        }
        return empleados;
    }
}
