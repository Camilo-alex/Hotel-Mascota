package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Mascota {

    private String nombreMascota;
    private String raza;
    private int edadPerruna;
    private List<Servicio> necesidadesEspeciales;

    public Mascota() {
        this.necesidadesEspeciales = new ArrayList<>();
    }

    public Mascota(String nombreMascota, String raza, int edadPerruna) {
        this.nombreMascota = nombreMascota;
        this.raza = raza;
        this.edadPerruna = edadPerruna;
        this.necesidadesEspeciales = new ArrayList<>();
    }

    public String getNombreMascota() { return nombreMascota; }
    public void setNombreMascota(String nombreMascota) { this.nombreMascota = nombreMascota; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public int getEdadPerruna() { return edadPerruna; }
    public void setEdadPerruna(int edadPerruna) { this.edadPerruna = edadPerruna; }

    public List<Servicio> getNecesidadesEspeciales() { return necesidadesEspeciales; }

    public void mostrarInfo() {
        System.out.println("Mascota: " + nombreMascota + " | Raza: " + raza + " | Edad: " + edadPerruna);
    }

    public void actualizarNecesidades(Servicio servicio) {
        necesidadesEspeciales.add(servicio);
        System.out.println("Necesidad agregada: " + servicio);
    }
}