package pe.edu.upeu.app;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import pe.edu.upeu.dao.ProductosDao;
import pe.edu.upeu.dao.ReservaDao;
import pe.edu.upeu.dao.UsuarioDao;
import pe.edu.upeu.utils.LeerTeclado;
import pe.edu.upeu.utils.UtilsX;

public class MenuPrincipal {
  UtilsX util=new UtilsX();
  

   LeerTeclado lt=new LeerTeclado();
   ProductosDao prodDao;
   UsuarioDao uDao;
   ReservaDao reDao;

   public void mainLogin() {
    uDao=new UsuarioDao();
    if (uDao.login()) {

      menuOpciones();

      
    }else{
      System.out.println("Intente Nuevamente!!");
      mainLogin();
    }
  }

   public void menuOpciones() {    
    util.clearConsole();
    Ansi color=new Ansi();       

    AnsiConsole.systemInstall();
         
    int opcionesA=0;
  
    System.out.println(color.bgBrightCyan().fgBlack().a("                       Bienvenido al Sistema                        ").reset());
    System.out.println("");
    String msg="\nElija una Opcion"+
    "\n"+
     
    "\n1 = Registrar una habitacion"+
    "\n2 = Listado del Registro"+
    "\n3 = Cambiar Registro"+
    "\n4 = Eliminar Registro"+
    "\n5 = Crear Usuario"+
    "\n6 = Realizar una Reserva"+
    "\n7 = Reportar Reservas"+
    "\n";
         
            opcionesA=lt.leer(0, msg);  
            while(opcionesA!=0){
                switch(opcionesA) {
                  case 1:{
                     prodDao=new ProductosDao(); 
                     prodDao.crearProductos(); 
                     } break;
                  
                case 2:{
                  prodDao=new ProductosDao();
                  prodDao.reportarproductos();
                }  break;
                case 3:{
                  prodDao=new ProductosDao();
                  prodDao.updateProducto();
                }  break;
                case 4:{
                  prodDao=new ProductosDao();
                  prodDao.deleteProducto();
                }  break;
                case 5:{
                  uDao=new UsuarioDao();
                  uDao.crearUsuario();
                }  break;
                case 6:{
                  reDao=new ReservaDao();
                  reDao.RegistrarReserva();
                }  break; 
                case 7:{
                  reDao=new ReservaDao();
                  reDao.reporteReservasFinal();
                }  break;   
                
                
                  default: System.out.println("La opcion ingresada no existe!");
                }   
              System.out.println("                    ");    
              opcionesA=lt.leer(0,msg);        
            }
      }
}