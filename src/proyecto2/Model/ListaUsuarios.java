/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.DefaultListModel;

/**
 *
 * @author kvothe
 */
public class ListaUsuarios implements Serializable{

    Map<String, ProxyPaquete> lista = new HashMap<>();

    public ListaUsuarios() {
    }

    ;
    
    public Iterator<ProxyPaquete> crearIteradorIP() {
        return lista.values().iterator();
    }

    public Iterator<String> crearIteradorNombre() {
        return lista.keySet().iterator();
    }

    public String agregarUsuario(ProxyPaquete paquete) {

        String nombre = paquete.getNickname();
        String nombreFinal = nombre;
        int contador = 1;

        //Cambiar nickname si ya existe
        while (lista.containsKey(nombre)) {
            nombre = nombreFinal;
            nombre = nombre + String.valueOf(contador);
            contador++;
        }

        lista.put(nombre,paquete);
        return nombre;
    }
    

    public Boolean eliminarUsuario(String nombre) {

        if (lista.containsKey(nombre)) {
            lista.remove(nombre);
            return true;
        }
        return false;
    }

    public ProxyPaquete getPaquete(String nombre) {
        return lista.get(nombre);
    }

    public boolean contains(String nombre) {
      
        return lista.containsKey(nombre);
    }
    
    public int size(){
        return lista.size();
    }
    
    public DefaultListModel listaInterfaz(){
        Iterator<String> it = crearIteradorNombre();
        DefaultListModel nombres = new DefaultListModel();
        while(it.hasNext()){
            nombres.addElement(it.next());
        }
        return nombres;
    }
    
    
}
