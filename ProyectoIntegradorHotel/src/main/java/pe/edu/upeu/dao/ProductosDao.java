package pe.edu.upeu.dao;

import pe.edu.upeu.data.AppCrud;
import pe.edu.upeu.modelo.ProductosTO;
import pe.edu.upeu.utils.LeerArchivo;
import pe.edu.upeu.utils.LeerTeclado;
import pe.edu.upeu.utils.UtilsX;

public class ProductosDao extends AppCrud{
    //objetos globales
    LeerTeclado leertecla=new LeerTeclado(); 
    UtilsX util=new UtilsX();
    //
    final  String TABLA_PRODUCTOS="Productos.txt";
    // variables globales
    LeerArchivo leerArch;
    ProductosTO prodTO;   

    String tipoHabitaciones="Individual\nDoble\nTriple\nCuadruple\nTwin\nMatrimonial\nFamiliar\nSuit\n";

    public Object[][] crearProductos() {
        leerArch=new LeerArchivo(TABLA_PRODUCTOS);
        prodTO=new ProductosTO();
        prodTO.setIdProductos(generarId(leerArch, 0, "P", 1));
        prodTO.setNhabitaciones(leertecla.leer("", "Imgrese el numero de la habitacion"));
        prodTO.setTipo(leertecla.leer("", "ingrese el tipo de habitacion ("+tipoHabitaciones+") "));
        prodTO.setPrecio(leertecla.leer(0, "ingrese el precio base de la habitacion"));
        prodTO.setCamas(leertecla.leer(0, "ingrese la cantidad de camas"));
        prodTO.setUnidad(leertecla.leer("", "Ingrese unidad de medida"));
        leerArch=new LeerArchivo(TABLA_PRODUCTOS);
        return agregarContenido(leerArch, prodTO);
        
        
    }
}
