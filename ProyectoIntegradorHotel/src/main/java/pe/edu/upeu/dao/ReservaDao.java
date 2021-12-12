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

    public void RegistrarReserva() { 
        //util.clearConsole();
        double SubtotalXX=0;
        double TotalimporteXX=0;
        double descuentoXX=0;

        ReservaTO reservaTO=crearReserva();
        String opcion="SI";
        if(reservaTO!=null){
            util.clearConsole();
            System.out.println("Agregar Dias adicionales");
            do {
                ReservaDetalleTO dataRD=DiasExtrareserva(reservaTO);
                SubtotalXX+=dataRD.getPrecio();
                opcion=leerTecla.leer("", "¿Desea disponer otra avitacion? SI/NO");
            } while (opcion.toUpperCase().equals("SI"));//toUpperCase Convirte a mayuscula
            
            //Actulizar xd
            if (leerTecla.leer("SI", "Desea Aplicar Descuento? SI/NO").toUpperCase().equals("SI")) {
                
            }else {
                descuentoXX=0;
            }
            TotalimporteXX=SubtotalXX-descuentoXX;
            reservaTO.setDescuento(descuentoXX);
            reservaTO.setSubtotal(SubtotalXX);
            reservaTO.setTotalimporte(TotalimporteXX); 
            leerArch=new LeerArchivo(TABLA_RESERVAS);
            editarRegistro(leerArch, 0, reservaTO.getIdReserva(), reservaTO);

        }
        
    }
    public ReservaTO crearReserva() {
        
        leerArch =new LeerArchivo(TABLA_RESERVAS);

    resTO=new ReservaTO();
    resTO.setIdReserva(generarId(leerArch, 0, "R", 1));
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
         return crearReserva() ;
     }    
    }
    public ReservaDetalleTO DiasExtrareserva(ReservaTO reseR) {
        mostrarProductos();
        reservaDetalleTO=new ReservaDetalleTO();
        leerArch=new LeerArchivo(TABLA_RENTA_DETALLE);
        reservaDetalleTO.setIdDetRent(generarId(leerArch, 0, "DR", 2));
        reservaDetalleTO.setIdReserva(reseR.getIdReserva());
        reservaDetalleTO.setIdProd(leerTecla.leer("", "ingrese el ID de la Habitacion"));
        leerArch=new LeerArchivo(TABLA_PRODUCTOS);
        Object[][] prodData=buscarContenido(leerArch, 0, reservaDetalleTO.getIdProd());
        if (prodData!=null) {
            double precioX=Double.parseDouble(String.valueOf(prodData[0][4]));
            
        reservaDetalleTO.setPrecio(precioX);
        }
        //reservaDetalleTO.setPrecio(leerTecla.leer(0, "Ingrese el precio"));
        //reservaDetalleTO.setDNI(leerTecla.leer("", "Ingrese el DNI otra vez por favor"));
      
        leerArch=new LeerArchivo(TABLA_RENTA_DETALLE);


        return reservaDetalleTO;
        
    }
    public String crearCliente(String dni) {
        leerArch=new LeerArchivo(TABLA_CLIENTE);       
        Object[][] dataCli=null;
        dataCli=buscarContenido(leerArch,0, dni);
        System.out.println("Vista Previa:"+dataCli.length);
        leerArch=new LeerArchivo(TABLA_PRODUCTOS);

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
            System.out.print(data[i][0]+"="+data[i][1]+
            "(Precio:"+data[i][4]+" / Estado: "+ data[i][2]+" / Cantidad de Camas: "+ data[i][5]+") |\t");
        }
        System.out.println("\n");
    }
}