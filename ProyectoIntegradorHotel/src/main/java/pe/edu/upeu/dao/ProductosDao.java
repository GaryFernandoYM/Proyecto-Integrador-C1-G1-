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

    String tipoHab="\nIndividual\nDoble\nTriple\nCuadruple\nTwin\nMatrimonial\nFamiliar\nSuit\n";

    public Object[][] crearProductos() {
        leerArch=new LeerArchivo(TABLA_PRODUCTOS);
        prodTO=new ProductosTO();
        prodTO.setIdProductos(generarId(leerArch, 0, "P", 1));
        prodTO.setNhabitaciones(leertecla.leer("", "Imgrese el numero de la habitacion"));
        prodTO.setTipo(leertecla.leer("", "\nIngrese el tipo de habitacion"+tipoHab));
        prodTO.setPrecio(leertecla.leer(0, "ingrese el precio base de la habitacion"));
        prodTO.setCamas(leertecla.leer(0, "ingrese la cantidad de camas"));
        prodTO.setUnidad(leertecla.leer("", "Ingrese unidad de medida"));
        leerArch=new LeerArchivo(TABLA_PRODUCTOS);
        return agregarContenido(leerArch, prodTO);
    }

    public void imprimirproductos() {
        leerArch=new LeerArchivo(TABLA_PRODUCTOS);
        Object[][] data= listarContenido(leerArch);
       util.pintarLine('H', 26);
       util.pintarTextHeadBody('H', 3, "ID,N°Habitacion,Tipo,Precio");
       System.out.println("");        
       util.pintarLine('H', 26);
       String dataPrint="";
       for (int i = 0; i < data.length; i++) {            
            dataPrint=data[i][0]+","+data[i][1]+","+data[i][2]+","+data[i][3];
            util.pintarTextHeadBody('B', 3, dataPrint);   
       }
       util.pintarLine('H', 26);
       System.out.println();
    }
    
}
