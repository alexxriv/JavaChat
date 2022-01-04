package proyecto2.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import proyecto2.Model.*;
import proyecto2.View.InterfazUsuario;

//Controller
public class Usuario implements ActionListener, Runnable {

    private Paquete paquete; //Modelo
    private InterfazUsuario interfaz; //vista
    private String nick;
    private JButton envio;

    public Usuario(String nick) {
        this.nick = nick;

        interfaz = new InterfazUsuario(nick);
        Thread recibirMensajes = new Thread(this);
        recibirMensajes.start();

        interfaz.getBotonEnvio().addActionListener(this);
        interfaz.getBotonCerrar().addActionListener(this);
        interfaz.setVisible(true);

    }

    public void mandarAlta(int puerto){
        Socket socket;

        try {
            paquete = new ProxyPaquete(new PaqueteAlta(nick, "127.0.0.1", puerto));
            socket = new Socket("127.0.0.1", 5000);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            
            //Servidor revibe paquete alta y el servidor llama notificarAll()
            out.writeObject(paquete);
            out.close();
            socket.close();
            System.out.println("Enviado:");
            paquete.print();

        } catch (IOException e) {
            System.out.println("Error al conecta por puerto " + puerto);
            System.out.println("Error en conexion: mandarAlta");


        }

    }

    public void mandarMensaje() {
        
        String nombre = interfaz.getLista().getSelectedValue();
        String mensaje = interfaz.getMensaje().getText();
        Socket socket;
        paquete = new ProxyPaquete(new PaqueteMensaje(nombre, mensaje));
        try {
            socket = new Socket("127.0.0.1", 5000);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(paquete);
            out.close();
            socket.close();
            System.out.println("Mensaje Enviado");
            paquete.print();
        } catch (IOException e) {
            System.out.println("Error en conexion: MandarMensaje()");

        }
    }
    

    public void leerPaquetes(ServerSocket server) {

        Socket socketS;
        try {
            socketS = server.accept();
            ObjectInputStream in = new ObjectInputStream(socketS.getInputStream());
            ProxyPaquete paquete = (ProxyPaquete) in.readObject();
            in.close();
            socketS.close();
            System.out.println("Recibido:");
            if (paquete.getTipo().equalsIgnoreCase("MENSAJE")) {
                
                String nick = paquete.getNickname();
                String mensaje = paquete.getMensaje();
                interfaz.getChat().append(nick + ": " + mensaje+"\n");
                System.out.println("Mensaje Recibido"+nick + ": " + mensaje+"\n");
                paquete.print();
            } else if (paquete.getTipo().equalsIgnoreCase("LISTA")) {
                
                interfaz.getLista().removeAll();
                interfaz.getLista().setModel(paquete.getListaUsuarios());
                interfaz.getChat().append(paquete.getMensaje());
                System.out.println("Lista recibida");
                paquete.print();
               
            }
        } catch (IOException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    

}

public int crearConeccion(){
        int puerto =5001;
        while(true){
            try {
                
                ServerSocket serverS = new ServerSocket(puerto);
                mandarAlta(puerto);
                System.out.println("CONEXION USUARIO, puerto: "+puerto);
                  
                while(true){
                  leerPaquetes(serverS);  
                }
               // return puerto;
            } catch (Exception ex) {
                System.out.println("Error en coneccion USUARIO, puerto: "+puerto);
               
                if(++puerto==5010){
                    return -1;
                }
            }
            
        }
    }

    @Override
        public void run() {
        crearConeccion();
    }

    @Override
        public void actionPerformed(ActionEvent e) {
       mandarMensaje();
    }

}
