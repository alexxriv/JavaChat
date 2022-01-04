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
public class ProxyPaquete implements Paquete, Serializable{

    Paquete paquete;
    
    public ProxyPaquete(Paquete paquete){
        this.paquete = paquete;
    }
    
    @Override
    public String getNickname() {
        return paquete.getNickname();
    }

    @Override
    public String getIP() {
        return paquete.getIP();
    }

    @Override
    public String getMensaje() {
        return paquete.getMensaje();
    }

    @Override
    public int getPort() {
        return paquete.getPort();
    }

    @Override
    public DefaultListModel getListaUsuarios() {
        return paquete.getListaUsuarios();
    }
    @Override
    public String getTipo(){
        return paquete.getTipo();
    }

    @Override
    public void notificar(DefaultListModel lista) {
        paquete.notificar(lista);
    }
    
    @Override
    public void setMensaje(String mensaje) {
        paquete.setMensaje(mensaje);
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
