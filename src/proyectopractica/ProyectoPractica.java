/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectopractica;

import javax.swing.JOptionPane;

/**
 *
 * @author rebecahurtado
 */
public class ProyectoPractica {

    /**
     * @param args the command line arguments
     */
    
    //1. Crear una variable global llamada alamacen, se crea static porque va a ejecutar en static
    static String[][] almacen = new String [25][3];
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        //3. Llenamos la variable
        inicializar();
        almacen = sembrar(almacen);
        //5. Llamamos el menu
        menu();
        
    }
     //2. Creamos un metodo para iniciar la matriz y cargar productos

    private static void inicializar(){
        for(int i=0;i<25;i++){
            for(int j=0;j<3;j++){
            almacen[i][j]="-1";
        }
        
        }
    
    } //Asigna valores en el arreglo
    private static String [][] sembrar(String[][]arreglo){
        String [][] arregloTrabajo = arreglo; 
        
        //Se agrega el primer producto 
        arregloTrabajo[0][0]="Tomate";
        arregloTrabajo[0][1] = "Kilo";
        arregloTrabajo[0][2] = "10";
       //Se agrega segundo producto 
        arregloTrabajo[1][0]="Queso";
        arregloTrabajo[1][1] = "Kilo";
        arregloTrabajo[1][2] = "5";
       //Se agrega tercer producto
        arregloTrabajo[2][0]="Chuleta";
        arregloTrabajo[2][1] = "Kilo";
        arregloTrabajo[2][2] = "20";
        //Cuarto Producto
        arregloTrabajo[3][0]="Pechuga Pollo";
        arregloTrabajo[3][1] = "Kilo";
        arregloTrabajo[3][2] = "0";
       
        return arregloTrabajo;
    }//Asigna valores en el arrglo
    
    //4. Creamos el menu
    
    private static void menu(){
        //6. Definimos como funciona el menu
        
        int action = -1;
        while (action!= 0){
        
        String opciones = "*****Menu Principal*****\n"
                
                + "1. Stock\n"
                + "2. Out of stock \n"
                + "3. Mostrar detalle de producto\n"
                + "4. Registar compras\n"
                + "5. Registrar Salida\n"
                + "6. Actualizar Producto\n"
                + "7. Eliminar Producto \n"
                + "8. Mostrar lista de productos \n"
                +  "0. Salir";
        System.out.println(opciones);
        
        action = Integer.parseInt(JOptionPane.showInputDialog(null, opciones));
        //Mostar el menu
        switch(action){
            case 1:
               JOptionPane.showMessageDialog(null,listarConExistencia());
                break;
            case 2: 
                JOptionPane.showMessageDialog(null,listarSinExistencia());
                break;
            case 3: 
                mostrarDetalleProducto();
                break;
             case 4: 
                registrarCompra();
                break;
             case 5: 
                registrarSalida();
                break;
             case 6:
                 actualizarProducto();
                 break;
             case 7:
                 borrarProducto();
                 break;
             case 8: 
                 listarTodos();
                 break;
             case 0:
                 JOptionPane.showMessageDialog(null,"Apagando Sistema...");
                 break;
                 
             default:     
                 JOptionPane.showMessageDialog(null,"Selecione una opcion del menu");
    
              
            
        }
        
    }
        
        JOptionPane.showInputDialog(null, "Sistema apagando...");
        
    
    }
    
    private static String listarConExistencia(){
        String salida ="";
        for(int i=0; i<25;i++){
            if(Double.parseDouble(almacen[i][2])>0){
                salida += (i+1)+"- "+almacen[i][0]+"   "+almacen[i][1]+"  "
                        +almacen[i][2]+"\n";
            }
        }
         if(salida.length()==0){
            salida ="No existe productos con existencia.";
        }
        return(salida);
    } //
    private static String listarSinExistencia(){
        String salida ="";
        for(int i=0; i<25;i++){
            if(Double.parseDouble(almacen[i][2])==0){
                salida += (i+1)+"- "+almacen[i][0]+"   "+almacen[i][1]+"  "
                        +almacen[i][2]+"\n";
            }
        }
        if(salida.length()==0){
            salida ="No existen productos sin existencias.";
        }
        return(salida);
    } 
    //Mostrar todos los productos
     private static String listarTodos(){
        String salida ="";
        for(int i=0; i<25;i++){
            if(Double.parseDouble(almacen[i][2])>=0){
                salida += (i+1)+"- "+almacen[i][0]+"\n";/*"   "+almacen[i][1]+"  "
                        +almacen[i][2]+"\n"*/
            }
        }
        if(salida.length()==0){
            salida ="No existen productos sin existencias.";
        }
        return(salida);
    } 
     
    private static void mostrarDetalleProducto(){
         int indice = -1;
         
         String mensaje = "Selecione el productos a mostrar:\n"+listarTodos();
         
         indice = (Integer.parseInt(JOptionPane.showInputDialog(null, mensaje)))-1;
        
        String  salida;
        
        if(revisarIndice(indice)){
            salida = detalleProducto(indice);
        }else{
        salida =" El producto no se encuentra registrado";
        } 
        
              
         JOptionPane.showMessageDialog(null, salida);
        
    }
    
    
    private static boolean revisarIndice(int indice){
        if(indice<0 || indice>24|| "-1".equals(almacen[indice][2])){
            return false;
        }else{
            return true;
        }
   
        
        
    }
    
    private static void registrarCompra(){
        int indice = -1;
        String mensaje = "Selecione el producto comprado:\n"+listarTodos();
         
        indice = (Integer.parseInt(JOptionPane.showInputDialog(null, mensaje)))-1;
       
        String  salida;
         if(revisarIndice(indice)){
            salida = detalleProducto(indice)
                    +"\n"
                    +"Digite la cantidad comprada: ";
            
            almacen[indice][2]= String.valueOf(Double.parseDouble(almacen[indice][2])+ 
                    Double.parseDouble(JOptionPane.showInputDialog(salida)));
                    
        }
        
    }
    
     private static void registrarSalida(){
        int indice = -1;
        String mensaje = "Selecione el productos que sale:\n"+listarTodos();
         
        indice = (Integer.parseInt(JOptionPane.showInputDialog(null, mensaje)))-1;
       
        String  salida;
         if(revisarIndice(indice)){
            salida = detalleProducto(indice)
                    +"\n"
                    +"Digite la cantidad extraida: ";
            
            double diferencia = Double.parseDouble(almacen[indice][2])- 
                    Double.parseDouble(JOptionPane.showInputDialog(salida));
            if(diferencia<0){
                JOptionPane.showMessageDialog(null,"Imposible registrar la salida\n"
                + "Cantidad extraida es superior a la existencia");
            }else{
                almacen[indice][2]= String.valueOf(Double.parseDouble(almacen[indice][2])- 
                    Double.parseDouble(JOptionPane.showInputDialog(salida)));
                JOptionPane.showMessageDialog(null, "Salida registrada");
            }
            
            
                    
        }
        
    }
     
     private static String detalleProducto(int pos){
        return "Productos: "+almacen[pos][0]+"\n"
                    +"Unidad: " +almacen[pos][1]+"\n"
                    +"Existencia: "+ almacen[pos][2];
    }
     private static void actualizarProducto(){
        int indice = -1;
        String mensaje = "Selecione el productos que desea actualizar:\n"+listarTodos();
         
        indice = (Integer.parseInt(JOptionPane.showInputDialog(null, mensaje)))-1;
       
        String  salida;
         if(revisarIndice(indice)){
             String datosLeidos;
            salida = detalleProducto(indice)
                    +"\n"
                    +"Digite la nueva descripcion: ";
            
            datosLeidos= JOptionPane.showInputDialog(null,salida);
            if(datosLeidos.length()>0){
                almacen[indice][0]=datosLeidos;
            }
            
            
            salida = detalleProducto(indice)
                    +"\n"
                    +"Digite la nueva unidad: ";
            
            datosLeidos= JOptionPane.showInputDialog(null,salida);
            if(datosLeidos.length()>0){
                almacen[indice][1]=datosLeidos;
            }
            
            salida = detalleProducto(indice)
                    +"\n"
                    +"Digite la nueva cantidad ";
            
            datosLeidos= JOptionPane.showInputDialog(null,salida);
            if(datosLeidos.length()>0){
                almacen[indice][2]=datosLeidos;
            }
            JOptionPane.showMessageDialog(null, "Producto actualizado con exito\n"
                    + "La nueva informacion es: \n"
                    + detalleProducto(indice));
            
           
            
            
                    
        }
        
    }
     
     private static void borrarProducto(){
         int indice = -1;
        String mensaje = "Selecione el productos que desea borrar:\n"+listarTodos();
         
        indice = (Integer.parseInt(JOptionPane.showInputDialog(null, mensaje)))-1;
       
        String  salida;
         if(revisarIndice(indice)){
            almacen[indice][2]="-1";
            salida ="El producto ha sido eliminado";
             
        }else{
             salida ="Indice no permitido";
         }
    }
     
     //registrar un producto nuevo
     
     private static void registrarProducto(){
         int pos = -1;
         for(int i=0; i<25;i++){
             if("-1".equals(almacen[i][2])){
                 pos = i;
                 break;
             }
         
         }
         
         if(pos==-1){
             JOptionPane.showMessageDialog(null, "No hay espacio disponible");
         }else{
             almacen[pos][0]=JOptionPane.showInputDialog(":Producto: ");
             almacen[pos][1]=JOptionPane.showInputDialog(":Unidad: ");
             almacen[pos][2]=String.valueOf(Double.parseDouble(JOptionPane.showInputDialog(":Cantidad: ")));
         }
         
         
     }
     
}
