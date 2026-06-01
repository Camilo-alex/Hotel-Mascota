package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

    private List<Mascota> mascotas;
    private List<Reserva> reservas;

    public Cliente(String nombre, String cedula, String edad, String telefono) {
        super(nombre, cedula, edad, telefono);
        this.mascotas = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public List<Mascota> getMascotas() { return mascotas; }
    public List<Reserva> getReservas() { return reservas; }

    public void validarDueño() {
        System.out.println("Validando que " + getNombre() + " es dueño de sus mascotas.");
    }

    public void registrarMascota(Mascota mascota) {
        mascotas.add(mascota);
        System.out.println("Mascota " + mascota.getNombreMascota() + " registrada para " + getNombre());
    }

    public void hacerReserva(Reserva reserva) {
        reservas.add(reserva);
        System.out.println("Reserva registrada para " + getNombre());
    }

    public void verReserva() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
        } else {
            for (Reserva r : reservas) {
                r.mostrarReserva();
            }
        }
    }
}