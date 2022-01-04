
package proyecto2.Controller;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto2.Model.*;
import proyecto2.View.InterfazServidor;

//Controller
public class Servidor implements Runnable{
    
    //Lista de usuarios y su info
    private ListaUsuarios lista = new ListaUsuarios();
    
    private Paquete paqueteServidor; //Modelo
    private InterfazServidor interfaz; //vista
    private String nick;
    
    public Servidor(String nick){
        interfaz = new InterfazServidor();
        interfaz.setVisible(true);
        this.nick = nick;
        Thread recibirMensajes = new Thread(this);
        recibirMensajes.start();
    }
    
    public void run() {
        leerPaquetes();
    }
    
    public void leerPaquetes(){
        try {

            ServerSocket server = new ServerSocket(5000);
            while (true) {
             
                Socket socketS = server.accept();
                ObjectInputStream in = new ObjectInputStream(socketS.getInputStream());

                try {
                    ProxyPaquete paquete = (ProxyPaquete) in.readObject();
                    in.close();
                     socketS.close();
                    if (paquete.getTipo().equalsIgnoreCase("MENSAJE")) {
                        System.out.println("MENSAJE");
                        reenvioMensaje(paquete);
                    } else if(paquete.getTipo().equalsIgnoreCase("ALTA")){
                        System.out.println("ALTA");
                        altaUsuario(paquete);
                        
                    }
                    
                   

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(InterfazServidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(InterfazServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void altaUsuario(ProxyPaquete paquete) {
        System.out.println("Alta de usuario detectada");
        paquete = new ProxyPaquete(new PaqueteLista(paquete));
        lista.agregarUsuario(paquete);
        String nickNuevo = paquete.getNickname();
        notificarAll(nickNuevo);
        interfaz.getAcciones().append("Usuario: " + nickNuevo + " conectado desde Ip: " + paquete.getIP() + "\n");

    }

    public void reenvioMensaje(ProxyPaquete paquete) {
        try {
            
            String nick = paquete.getNickname();
            ProxyPaquete infoUsuario = lista.getPaquete(nick);
            String ip = infoUsuario.getIP();
            int puerto = infoUsuario.getPort();
            
            interfaz.getAcciones().append("mensje para: " + nick +" "+ paquete.getMensaje() + " " + ip +"\n");
            Socket reenvio = new Socket(ip, puerto);
            ObjectOutputStream out = new ObjectOutputStream(reenvio.getOutputStream());
            out.writeObject(paquete);
            out.close();
            reenvio.close();
            System.out.println("mensaje reenviado "+ puerto +" "+ ip);
            paquete.print();
        } catch (IOException ex) {
            Logger.getLogger(InterfazServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void notificarAll(String nickNuevo){
        Iterator<ProxyPaquete> it = lista.crearIteradorIP();
            while(it.hasNext()){
                ProxyPaquete aux = it.next();
                aux.setMensaje(nickNuevo);
                aux.notificar(lista.listaInterfaz());
                enviarLista(aux);
        }
    
    }
    
    public void enviarLista(ProxyPaquete paquete){
        try {
            Socket socket = new Socket(paquete.getIP(), paquete.getPort());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(paquete);
            out.close();
            socket.close();
             
        } catch (IOException ex) {
            System.out.println("Error al enviar lista"+ paquete.getIP()+ " "+ paquete.getPort());
        }
    }
   
    public static void main(String[] args){
        new Servidor("Servidor");
    }
    
    
}
