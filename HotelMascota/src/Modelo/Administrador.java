package Modelo;

public class Administrador extends Personal {

    public Administrador(String nombre, String cedula, String edad, String telefono) {
        super(nombre, cedula, edad, telefono);
    }

    public void gestionarReservas() {
        System.out.println("Administrador " + getNombre() + " gestionando reservas.");
    }

    public void asignarCuidador() {
        System.out.println("Administrador " + getNombre() + " asignando cuidador.");
    }
}