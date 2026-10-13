/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Cita;
import modelo.ClinicaModelo;
import modelo.Medico;
import modelo.Paciente;
import vista.VistaRegistroCita;

/**
 *
 * @author angel
 */
public class ControladorCita {

    private VistaRegistroCita vista;
    private ClinicaModelo modelo;
    private Paciente pacienteActual;
    private Medico medicoSeleccionado;

    public ControladorCita(VistaRegistroCita v, ClinicaModelo m) {
        this.vista = v;
        this.modelo = m;

        vista.getBtnBuscar().addActionListener(e -> buscarPaciente());
        vista.getListaMedicos().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                seleccionarMedico();
            }
        });
        vista.getBtnApartar().addActionListener(e -> apartarCita());
    }

    public void buscarPaciente() {
        String nss = vista.getNSS();
        if (nss.isEmpty()) {
            vista.mostrarError("Debe capturar el Numero de Seguridad Social.");
            return;
        }
        Paciente p = modelo.buscarPaciente(nss);
        if (p == null) {
            pacienteActual = null;
            vista.mostrarError("El NSS no esta registrado en el sistema.");
            return;
        }
        pacienteActual = p;
        medicoSeleccionado = null;
        vista.mostrarPaciente(p);
        vista.mostrarMedicos(modelo.getMedicos());
    }

    public void seleccionarMedico() {
        Medico m = vista.getMedicoSeleccionado();
        if (m == null) {
            return;
        }
        medicoSeleccionado = m;
        vista.mostrarDatosMedico(m);
    }

    public void apartarCita() {
        if (pacienteActual == null) {
            vista.mostrarError("Primero busque un paciente por su NSS.");
            return;
        }
        if (medicoSeleccionado == null) {
            vista.mostrarError("Seleccione un medico de la lista.");
            return;
        }
        String fecha = vista.getFecha();
        String hora = vista.getHora();
        if (fecha.isEmpty() || hora.isEmpty()) {
            vista.mostrarError("Capture la fecha y la hora de la cita.");
            return;
        }
        try {
            Cita cita = modelo.apartarCita(pacienteActual, medicoSeleccionado, fecha, hora);
            vista.mostrarCita(cita);
        } catch (IllegalArgumentException ex) {
            vista.mostrarError(ex.getMessage());
        }
    }

}
