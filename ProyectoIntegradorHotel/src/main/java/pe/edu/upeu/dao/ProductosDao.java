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
    final  String TABLA_PRODUCTOS="Registro de Habitacion .txt";
    // variables globales
    LeerArchivo leerArch;
    ProductosTO prodTO;   

    String tipoHab="\nIndividual\nDoble\nTriple\nCuadruple\nTwin\nMatrimonial\nFamiliar\nSuit\n";

    public Object[][] crearProductos() {
        util.clearConsole();
        
        leerArch=new LeerArchivo(TABLA_PRODUCTOS);
        prodTO=new ProductosTO();
        prodTO.setIdProductos(generarId(leerArch, 0, "P", 1));
        prodTO.setNhabitaciones(leertecla.leer("", "Ingrese el numero de la habitacion"));
        prodTO.setEstado(leertecla.leer("", "el estado de la Habitacion"));
        prodTO.setTipo(leertecla.leer("", "\nIngrese el tipo de habitacion"+tipoHab));
        prodTO.setPrecio(leertecla.leer(0, "ingrese el precio base de la habitacion"));
        prodTO.setCamas(leertecla.leer(0, "ingrese la cantidad de camas"));
        prodTO.setUnidad(leertecla.leer("", "Ingrese la unidad de medida"));
        leerArch=new LeerArchivo(TABLA_PRODUCTOS);
        return agregarContenido(leerArch, prodTO);
    }

    public void reportarproductos() {

        leerArch=new LeerArchivo(TABLA_PRODUCTOS);
        Object[][] data= listarContenido(leerArch);
       util.pintarLine('H', 33);
       util.pintarTextHeadBody('H', 3, "ID,N°Habitacion,Estado,Tipo,Precio");
       System.out.println("");        
       util.pintarLine('H', 33);
       String dataPrint="";
       
       for (int i = 0; i < data.length; i++) {            
            dataPrint=data[i][0]+","+data[i][1]+","+data[i][2]+","+data[i][3]+","+data[i][4]+data[i][5];
            util.pintarTextHeadBody('B', 3, dataPrint);   
       }
       util.pintarLine('H', 33);
       System.out.println();
    }
    public void reportarproductos(Object[][] data) {
       util.pintarLine('H', 33);
       util.pintarTextHeadBody('H', 3, "ID,N°Habitacion,Estado,Tipo,Precio");
       System.out.println("");        
       util.pintarLine('H', 33);
       String dataPrint="";
       
       for (int i = 0; i < data.length; i++) {            
            dataPrint=data[i][0]+","+data[i][1]+","+data[i][2]+","+data[i][3]+","+data[i][4]+data[i][5];
            util.pintarTextHeadBody('B', 3, dataPrint);   
       }
       util.pintarLine('H', 33);
       System.out.println();
    }

    public void updateProducto() {  
        util.clearConsole();
        reportarproductos();      
        String dataId=leertecla.leer("", "Ingrese el Id del Producto");        
        prodTO=new ProductosTO();
        prodTO.setNhabitaciones(leertecla.leer("", "Ingrese el nuevo numero de Habitacion"));
        prodTO.setEstado(leertecla.leer("", "Ingrese el Estado Actual"));
        prodTO.setPrecio(leertecla.leer(0.0, "Ingrese el nuevo precio"));
        
        leerArch=new LeerArchivo(TABLA_PRODUCTOS);
        Object[][] data= editarRegistro(leerArch, 0, dataId, prodTO);
        util.clearConsole();
        reportarproductos(data);
    }

    public void deleteProducto() {
        util.clearConsole();
        reportarproductos();  
        String dataId=leertecla.leer("", "Ingrese el Id del Producto"); 
        leerArch=new LeerArchivo(TABLA_PRODUCTOS);
        Object[][] data= eliminarRegistros(leerArch, 0, dataId);
        util.clearConsole();
        reportarproductos(data);
    }

   
    
}
