/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author angel
 */
public class ClinicaModelo {

    private List<Paciente> pacientes;
    private List<Medico> medicos;
    private List<Cita> citas;

    public ClinicaModelo() {
        pacientes = new ArrayList<>();
        medicos = new ArrayList<>();
        citas = new ArrayList<>();
        cargarDatosDePrueba();
    }

    private void cargarDatosDePrueba() {
        pacientes.add(new Paciente("12345678901", "Ana", "Lopez Ramirez"));
        pacientes.add(new Paciente("98765432109", "Carlos", "Martinez Soto"));
        pacientes.add(new Paciente("11122233344", "Maria", "Garcia Perez"));

        medicos.add(new Medico("Dr. Juan Perez Torres", "Medicina General", "Consultorio 101",
                "Lunes, Miercoles, Viernes", "09:00 - 13:00"));
        medicos.add(new Medico("Dra. Laura Sanchez Ruiz", "Pediatria", "Consultorio 102",
                "Martes, Jueves", "10:00 - 14:00"));
        medicos.add(new Medico("Dr. Roberto Diaz Leon", "Cardiologia", "Consultorio 205",
                "Lunes, Martes, Jueves", "15:00 - 19:00"));
        medicos.add(new Medico("Dra. Sofia Mendoza Vega", "Dermatologia", "Consultorio 210",
                "Miercoles, Viernes", "08:00 - 12:00"));
    }

    public Paciente buscarPaciente(String nss) {
        for (Paciente p : pacientes) {
            if (p.getNss().equals(nss)) {
                return p;
            }
        }
        return null;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public boolean horarioOcupado(Medico m, String fecha, String hora) {
        for (Cita c : citas) {
            if (c.getMedico() == m && c.getFecha().equals(fecha) && c.getHora().equals(hora)) {
                return true;
            }
        }
        return false;
    }

    public Cita apartarCita(Paciente p, Medico m, String fecha, String hora) {
        fecha = fecha.trim();
        hora = hora.trim();
        if (!m.estaEnHorario(fecha, hora)) {
            throw new IllegalArgumentException("La fecha u hora esta fuera del horario del medico.\n"
                    + "Dias: " + m.getDiasConsulta() + "\nHoras: " + m.getHorasConsulta());
        }
        if (horarioOcupado(m, fecha, hora)) {
            throw new IllegalArgumentException("Ese horario ya esta ocupado. Elija otro.");
        }
        Cita cita = new Cita(fecha, hora, p, m);
        citas.add(cita);
        return cita;

    }

}
