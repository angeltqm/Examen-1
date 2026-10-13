/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author angel
 */
public class Paciente {
    private String nss;
    private String nombre;
    private String apellidos;
    
    public Paciente(String nss, String nombre, String apellidos) {
        this.nss=nss;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getNss() {
        return nss;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
    
    public String getNombreCompleto(){
        return nombre + " "+ apellidos;
    }
}
