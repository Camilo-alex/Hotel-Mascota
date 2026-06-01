package Modelo;

public class Personal extends Usuario {

    public Personal(String nombre, String cedula, String edad, String telefono) {
        super(nombre, cedula, edad, telefono);
    }

    public void mostrarPerfil() {
        System.out.println("Nombre: " + getNombre() + " | Cedula: " + getCedula());
    }
}