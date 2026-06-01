package Modelo;

public class Usuario {

    private String nombre;
    private String cedula;
    private String edad;
    private String telefono;

    public Usuario() {}

    public Usuario(String nombre, String cedula, String edad, String telefono) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.telefono = telefono;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getEdad() { return edad; }
    public void setEdad(String edad) { this.edad = edad; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre + " | Cedula: " + cedula + " | Edad: " + edad + " | Tel: " + telefono);
    }
}