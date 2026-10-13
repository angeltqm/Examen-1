/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import controlador.ControladorCita;
import javax.swing.SwingUtilities;
import modelo.ClinicaModelo;
import vista.VistaRegistroCita;

/**
 *
 * @author angel
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClinicaModelo modelo = new ClinicaModelo();
            VistaRegistroCita vista = new VistaRegistroCita();
            new ControladorCita(vista, modelo);
            vista.setVisible(true);
        });
    }
}
