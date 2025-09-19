package pe.edu.upeu.asistencia.repositorio;

import pe.edu.upeu.asistencia.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoRepository {
    protected List<Producto> productos = new ArrayList<>();

    public List<Producto> findAll() {
        productos.add(new Producto("PROD001", "Harina", 50, "kg"));
        productos.add(new Producto("PROD002", "Azucar", 25, "kg"));
        return productos;
    }
}
