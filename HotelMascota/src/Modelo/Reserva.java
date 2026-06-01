package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {

    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private int diasEstadia;
    private List<Servicio> serviciosADD;

    public Reserva() {
        this.serviciosADD = new ArrayList<>();
    }

    public Reserva(LocalDate fechaIngreso, int diasEstadia) {
        this.fechaIngreso = fechaIngreso;
        this.diasEstadia = diasEstadia;
        this.serviciosADD = new ArrayList<>();
        calcularFechaSalida();
    }

    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }

    public LocalDate getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDate fechaSalida) { this.fechaSalida = fechaSalida; }

    public int getDiasEstadia() { return diasEstadia; }
    public void setDiasEstadia(int diasEstadia) { this.diasEstadia = diasEstadia; }

    public List<Servicio> getServiciosAdd() { return serviciosADD; }

    public void calcularFechaSalida() {
        if (fechaIngreso != null) {
            this.fechaSalida = fechaIngreso.plusDays(diasEstadia);
        }
    }

    public void agregarServicio(Servicio servicio) {
        serviciosADD.add(servicio);
        System.out.println("Servicio agregado: " + servicio);
    }

    public void mostrarReserva() {
        System.out.println("Ingreso: " + fechaIngreso);
        System.out.println("Dias de estadia: " + diasEstadia);
        System.out.println("Salida: " + fechaSalida);
        System.out.println("Servicios: " + serviciosADD);
    }
}