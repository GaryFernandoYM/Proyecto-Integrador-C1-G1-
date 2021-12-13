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
     resTO.setOrigen(leerTecla.leer("","Ingrese el Origen"));
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
    public void reporteReservasFinal() {
        util.clearConsole();
        System.out.println("===================Registro Ventas==================");
        String fechaInit=leerTecla.leer("", "Ingrese F. Inicio (dd-MM-yyyy)");
        String fechaFinal=leerTecla.leer("", "Ingrese F. Final (dd-MM-yyyy)");
        leerArch=new LeerArchivo(TABLA_RESERVAS);
        Object[][] dataR=listarContenido(leerArch);
        int contadorRRF=0;
        try {
             //Para saber que cantidad de registros coinciden con el rango de fecha
             for (int i = 0; i < dataR.length; i++) {
                String[] fechaReserva=String.valueOf(dataR[i][2]).split(" ");
                Date fechaReservaX=formatofecha.parse(fechaReserva[0]);
                if(
                (fechaReservaX.after(formatofecha.parse(fechaInit)) || fechaInit.equals(fechaReserva[0])) && //depues de la fecha 
                (fechaReservaX.before(formatofecha.parse(fechaFinal)) || fechaFinal.equals(fechaReserva[0])) // antes de la fecha
                ){
                    contadorRRF++; //contador
                }
            } 
            //pasa los datos al valor de tipo ReservaTO R.fechas
            ReservaTO[] dataReal=new ReservaTO[contadorRRF];
            int indiceVector=0;
            for (int i = 0; i < dataR.length; i++) {
                String[] fechaReserva=String.valueOf(dataR[i][2]).split(" ");
                Date fechaReservaX=formatofecha.parse(fechaReserva[0]);
                if(
                (fechaReservaX.after(formatofecha.parse(fechaInit)) || fechaInit.equals(fechaReserva[0])) && //depues de la fecha 
                (fechaReservaX.before(formatofecha.parse(fechaFinal)) || fechaFinal.equals(fechaReserva[0])) // antes de la fecha
                ){
                   ReservaTO rTo=new ReservaTO();
                   rTo.setIdReserva(dataR[i][0].toString());
                   rTo.setDni(dataR[i][1].toString()); 
                   rTo.setFecha(dataR[i][2].toString()); 
                   rTo.setOrigen(dataR[i][3].toString()); 
                   rTo.setDescuento(Double.parseDouble(String.valueOf(dataR[i][5])));
                   rTo.setSubtotal(Double.parseDouble(String.valueOf(dataR[i][4])));
                   rTo.setTotalimporte(Double.parseDouble(String.valueOf(dataR[i][6])));
                   dataReal[indiceVector]=rTo;
                   indiceVector++;
                }
            }
            //Para imprimir las Reservas
            System.out.println("===================Reporte de Reserva "+fechaInit+" Y "+fechaFinal+"==================");
            util.pintarLine('H', 39);
            util.pintarTextHeadBody('H', 3, "ID,DNI Cliente,Origen,sub.total, Descuento,Imp.total");
            System.out.println("");
            double subtotalX=0,descuentoX=0, importeTX =0;
            util.pintarLine('H', 39);
            for (ReservaTO TOR : dataReal){
                String datax=TOR.getIdReserva()+","+TOR.getDni()+","+TOR.getOrigen()+","+TOR.getSubtotal()+","+
                TOR.getDescuento()+","+TOR.getTotalimporte();
                subtotalX+=TOR.getSubtotal(); 
                descuentoX+=TOR.getDescuento(); 
                importeTX+=TOR.getTotalimporte();

                util.pintarTextHeadBody('B', 3, datax);
 
            }
            util.pintarLine('H', 39);
            System.out.println("");
            System.out.println(" | Sub. Total:S/"+ subtotalX+" |Descuento:S/."+descuentoX+
            "|Imp. Total:S/."+importeTX);
            util.pintarLine('H', 40);   
       
            
            


        } catch (Exception e) {
            System.err.println("Error al Reportar!!"+e.getMessage());
        }

    }
}