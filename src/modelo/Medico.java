/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 *
 * @author angel
 */
public class Medico {

    private String nombreCompleto;
    private String especialidad;
    private String consultorio;
    private String diasConsulta;
    private String horasConsulta;

    public Medico(String nombreCompleto, String especialidad, String consultorio,
            String diasConsulta, String horasConsulta) {
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
        this.consultorio = consultorio;
        this.diasConsulta = diasConsulta;
        this.horasConsulta = horasConsulta;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public String getDiasConsulta() {
        return diasConsulta;
    }

    public String getHorasConsulta() {
        return horasConsulta;
    }

    public boolean estaEnHorario(String fecha, String hora) {
        LocalDate f;
        LocalTime h;

        try {
            f = LocalDate.parse(fecha.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            throw new IllegalArgumentException("La fecha debe tener el formato dd/MM/aaaa (ej. 20/10/2026).");
        }
        try {
            h = LocalTime.parse(hora.trim(), DateTimeFormatter.ofPattern("H:mm"));
        } catch (Exception e) {
            throw new IllegalArgumentException("La hora debe tener el formato HH:mm (ej. 10:30).");
        }

        String dia = normalizar(f.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("es-MX")));
        if (!normalizar(diasConsulta).contains(dia)) {
            return false;
        }

        String[] partes = horasConsulta.split("-");
        LocalTime inicio = LocalTime.parse(partes[0].trim(), DateTimeFormatter.ofPattern("H:mm"));
        LocalTime fin = LocalTime.parse(partes[1].trim(), DateTimeFormatter.ofPattern("H:mm"));
        return !h.isBefore(inicio) && h.isBefore(fin);
    }

    private String normalizar(String texto) {
        String s = Normalizer.normalize(texto, Normalizer.Form.NFD);
        return s.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

    }

    @Override
    public String toString() {
        return nombreCompleto + " - " + especialidad;// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    

}
