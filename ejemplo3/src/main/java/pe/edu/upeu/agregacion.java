package pe.edu.upeu.agregacion;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private List<Profesor> profesores;
    Departamento() {
        this.profesores = new ArrayList<>();
    }
    void agregarProfesor(Profesor profesor) {
        profesores.add(profesor);
    }
    public static void main(String[] args) {
        Departamento departamento = new Departamento();
    }
}