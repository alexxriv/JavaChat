/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2.Model;

import java.io.Serializable;
import javax.swing.DefaultListModel;

/**
 *
 * @author kvothe
 */
public class PaqueteMensaje implements Paquete, Serializable{
    private final String nickname;
    private final String ip;
    private final String mensaje;
    private final int port;
    private final String tipo = "MENSAJE";
    
    public PaqueteMensaje(String nickname, String ip, String mensaje, int port){
        this.nickname = nickname;
        this.ip = ip;
        this.mensaje = mensaje;
        this.port= port;
    }
    
    public PaqueteMensaje(String nickname, String mensaje){
        this.nickname = nickname;
        this.ip = "";
        this.mensaje = mensaje;
        this.port= 0;
    }
    
    @Override
    public String getNickname(){
        return nickname; 
    }
    
    @Override
    public String getIP(){
        return ip;
    }
    
    @Override
    public String getMensaje(){
        return mensaje;
    }  
    
    @Override
    public int getPort(){
        return port;
    }

    @Override
    public DefaultListModel getListaUsuarios() {
        return null;
    }
    @Override
    public String getTipo(){
        return tipo;
    }

    @Override
    public void notificar(DefaultListModel lista) {
    }
    
    @Override
    public void setMensaje(String mensaje) {
        System.out.println("No es posible cambiar mensaje");
    }

    @Override
    public void print() {
        System.out.println(
        "\n{"+
                "\n\tTipo: "+ getTipo()+
                "\n\tNickname: " + getNickname()+
                "\n\tIp: " + getIP()+
                "\n\tmensaje:" + getMensaje()+"\n}"
                
        );
    }
}
