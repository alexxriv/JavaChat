/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2.Model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author kvothe
 */
public class PaqueteLista implements Paquete, Serializable{
    
    private DefaultListModel listaUsuarios;
    private final String tipo ="LISTA";
    private String nickname, ip, mensaje;
    private int puerto;
    
    public PaqueteLista(ProxyPaquete paquete){
            this.nickname = paquete.getNickname();
            this.ip = paquete.getIP();
            this.puerto = paquete.getPort();
            listaUsuarios = new DefaultListModel();
    }
    
    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public String getIP() {
        return ip;
    }

    @Override
    public String getMensaje() {
        return mensaje;
    }

    @Override
    public int getPort() {
        return puerto;
    }

    @Override
    public DefaultListModel getListaUsuarios() {
        return listaUsuarios;
    }
    @Override
    public String getTipo(){
        return tipo;
    }

    @Override
    public void notificar(DefaultListModel lista) {
        this.listaUsuarios = lista;
    }

    @Override
    public void setMensaje(String mensaje) {
        this.mensaje= "Usuario nuevo: "+ mensaje +"\n";
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
