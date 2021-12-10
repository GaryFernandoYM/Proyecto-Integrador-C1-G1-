package pe.edu.upeu.dao;

import pe.edu.upeu.data.AppCrud;
import pe.edu.upeu.modelo.RegistroClienteTO;
import pe.edu.upeu.utils.LeerArchivo;
import pe.edu.upeu.utils.LeerTeclado;
import pe.edu.upeu.utils.UtilsX;

public class RegistroClienteDao extends AppCrud{
    //objetos globales
    LeerTeclado leertecla=new LeerTeclado(); 
    UtilsX util=new UtilsX();
    //
    final  String TABLA_PRODUCTOS="RegistroCliente.txt";
    // variables globales
    LeerArchivo leerArch;
   RegistroClienteTO regTO;   

    

    public void reportarProductos() {
        util.clearConsole();
        leerArch=new LeerArchivo(TABLA_PRODUCTOS);
       Object[][] data= listarContenido(leerArch);
       util.pintarLine('H', 26);
       util.pintarTextHeadBody('H', 3, "ID,DNI,Registro,Origen,Precio,");
       System.out.println("");        
       util.pintarLine('H', 26);
       String dataPrint="";
       for (int i = 0; i < data.length; i++) {            
            dataPrint=data[i][0]+","+data[i][1]+","+data[i][3]+","+data[i][5]+","+data[i][6];
            util.pintarTextHeadBody('B', 3, dataPrint);   
       }
       util.pintarLine('H', 26);
       System.out.println();
    }
}        