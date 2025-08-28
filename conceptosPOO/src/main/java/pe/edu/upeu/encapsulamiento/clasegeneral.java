package pe.edu.upeu.encapsulamiento;

public class clasegeneral {
    public static  void main( String[] args ){
        persona p=new persona();
        //p.nombre= "daly";
        //p.edad=17;
        p.setNombre("daly");//encapsulamiento
        p.setEdad(27);//encapsulamiento
        p.apellido = "rodriguez";// no se aplicate encapsulamiento
        p.saludo();


        trabajador t=new trabajador();
        t.setNombre("daly");
        t.setApellido("rodriguez");
        t.setEdad(27);
        t.setArea("sistemas");
        t.setGenero('F');
        System.out.println(t);

    }
}
