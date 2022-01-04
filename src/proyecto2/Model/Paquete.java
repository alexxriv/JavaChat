/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2.Model;

import javax.swing.DefaultListModel;

/**
 *
 * @author kvothe
 */
public interface Paquete {
    public String getNickname();
    public String getIP();
    public String getMensaje();
    public void setMensaje(String mensaje);
    public int getPort();
    public DefaultListModel getListaUsuarios();
    public String getTipo();
    public void notificar(DefaultListModel lista);
    
    public void print();
}
