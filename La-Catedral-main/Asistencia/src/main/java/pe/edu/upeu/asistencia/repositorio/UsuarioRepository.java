package pe.edu.upeu.asistencia.repositorio;

import org.springframework.stereotype.Service;
import pe.edu.upeu.asistencia.modelo.Usuario;
import pe.edu.upeu.asistencia.enums.Perfil;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioRepository {
    List<Usuario> usuarios=new ArrayList<>();

    public List<Usuario> findAll(){
        usuarios.add(new Usuario("12345678", "admin", Perfil.ADMINISTRADOR));
        usuarios.add(new Usuario("87654321", "empleado", Perfil.EMPLEADO));
        return usuarios;
    }

    public Usuario findByDni(String dni){
        return usuarios.stream()
                .filter(u -> u.getDni().equals(dni))
                .findFirst()
                .orElse(null);
    }

}
