package pe.edu.upeu.dao;

import java.text.SimpleDateFormat;
import java.util.Date;



//import org.fusesource.jansi.Ansi;
//import org.fusesource.jansi.AnsiConsole;



import pe.edu.upeu.data.AppCrud;
import pe.edu.upeu.modelo.ClienteTO;
import pe.edu.upeu.modelo.ProductosTO;
import pe.edu.upeu.modelo.ReservaDetalleTO;
import pe.edu.upeu.modelo.ReservaTO;
import pe.edu.upeu.utils.LeerArchivo;
import pe.edu.upeu.utils.LeerTeclado;
import pe.edu.upeu.utils.UtilsX;

public class ReservaDao extends AppCrud{
    LeerTeclado leerTecla=new LeerTeclado();
    UtilsX util=new UtilsX();
    
    

    final String TABLA_PRODUCTOS="Registro de Habitacion .txt";
    final String TABLA_RESERVAS="Reservas.txt";
    final String TABLA_CLIENTE="Cliente.txt";
    final String TABLA_RENTA_DETALLE="Reserva detalle";

    LeerArchivo leerArch;

    ProductosTO prodTO;
    ReservaTO resTO;
    ClienteTO clienteTO;
    ReservaDetalleTO reservaDetalleTO;

    SimpleDateFormat formatofechahora = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    SimpleDateFormat formatofecha = new SimpleDateFormat("dd-MM-yyyy");

    public void RegistroReserva() { 
    }

    

    public ReservaTO crearVenta() {
    leerArch =new LeerArchivo(TABLA_RESERVAS);

    resTO=new ReservaTO();
    resTO.setIdReserva(generarId(leerArch, 0, "V", 1));
    String dnix=leerTecla.leer("", "Ingrese el Dni del Cliente");
    resTO.setDni(crearCliente(dnix));
        Date fecha=new Date();
     resTO.setFecha(formatofechahora.format(fecha));
     resTO.setSubtotal(0);
     resTO.setDescuento(0);
     resTO.setTotalimporte(0);
     
     leerArch=new LeerArchivo(TABLA_RESERVAS);
     agregarContenido(leerArch, resTO);

     leerArch=new LeerArchivo(TABLA_RESERVAS);
     if(buscarContenido(leerArch, 0, resTO.getIdReserva()).length==1){
         return resTO;
     }else{ 
         System.out.println("Intente nuevamente:");  //un metodo se llama asi mismo = recursividad         
         return crearVenta() ;
     } 
    

    }
    public String crearCliente(String dni) {
        leerArch=new LeerArchivo(TABLA_CLIENTE);
        Object[][] dataCli=null;
        dataCli=buscarContenido(leerArch, 0, dni);
        System.out.println("VEr:"+dataCli.length);
        if(dataCli==null || dataCli.length==0){
            if(dataCli.length==0){
                ClienteDao cDao=new ClienteDao();
                cDao.crearCliente(dni);                
            }
            return dni;
            
        }else{
            return dni;
        }       
    }
    public void mostrarProductos() {
        leerArch=new LeerArchivo(TABLA_PRODUCTOS);
        Object[][] data=listarContenido(leerArch);
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i][0]+"="+data[i][1]+","+data[i][3]+"(Precio:"+data[i][4]+" / Camas: "+ data[i][5]+") |\t");
        }
        System.out.println("\n");
    }
}
       
