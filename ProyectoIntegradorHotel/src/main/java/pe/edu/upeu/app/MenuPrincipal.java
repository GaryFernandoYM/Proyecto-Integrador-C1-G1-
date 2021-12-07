package pe.edu.upeu.app;

import pe.edu.upeu.dao.ProductosDao;

import pe.edu.upeu.utils.LeerTeclado;

public class MenuPrincipal {
   LeerTeclado lt=new LeerTeclado();
   ProductosDao prodDao;

      public void menuOpciones() {
          int opcionesA=0;
            System.out.println("-----------BIENVENIDO AL SISTEMA--------------------");
            String msg="\nElija una opcion"+ 
            "\n1=Registrar una habitacion"+
            "\n2= Listar"
            ;
         
            opcionesA=lt.leer(0, msg);  
            while(opcionesA!=0){
                switch(opcionesA) {
                  case 1:{
                     prodDao=new ProductosDao(); 
                     prodDao.crearProductos(); 
                     } break;
                  
                case 2:  break;
                  
                  default: System.out.println("La opcion ingresada no existe!");
                }   
              System.out.println("-------------------------"); //15.511.210.043.330.985.984.000.000        
              opcionesA=lt.leer(0,msg);        
            }
      }
}