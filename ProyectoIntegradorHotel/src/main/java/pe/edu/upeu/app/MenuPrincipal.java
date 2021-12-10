package pe.edu.upeu.app;

import pe.edu.upeu.dao.ProductosDao;
import pe.edu.upeu.dao.RegistroClienteDao;
import pe.edu.upeu.dao.UsuarioDao;
import pe.edu.upeu.utils.LeerTeclado;

public class MenuPrincipal {

   LeerTeclado lt=new LeerTeclado();
   ProductosDao prodDao;
   UsuarioDao uDao;

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
    int opcionesA=0;
    System.out.println("-----------------------Bienvenido al Sistema --------------------");
    String msg="\nEliga una Opcion"+ 
    "\n1=Registrar una habitacion"+
    "\n2=Listado del Registro"+
    "\n3=Cambiar Registro"+
    "\n4=Eliminar Registro"+
    "\n5=Crear Usuario"+
    "\n6=otras opciones"+
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
                
                
                  default: System.out.println("La opcion ingresada no existe!");
                }   
              System.out.println("-------------------------"); //15.511.210.043.330.985.984.000.000        
              opcionesA=lt.leer(0,msg);        
            }
      }
}