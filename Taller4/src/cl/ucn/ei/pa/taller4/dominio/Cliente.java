package cl.ucn.ei.pa.taller4.dominio;

import cl.ucn.ei.pa.taller4.logica.ListaPaquetes;

public class Cliente {
    private String rut;
    private String nombre;
    private String apellido;
    private ListaPaquetes paquetesRecibidos;
    private ListaPaquetes paquetesEnviados;

    public Cliente(String rut, String nombre, String apellido) {
        this.rut=rut;
        this.nombre=nombre;
        this.apellido=apellido;
        paquetesRecibidos= new ListaPaquetes();
        paquetesRecibidos= new ListaPaquetes();
    }

    public String getRut() {
        return this.rut;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public ListaPaquetes getPaquetesRecibidos() {
        return this.paquetesRecibidos;
    }

    public void setPaquetesRecibidos(ListaPaquetes paquetesRecibidos) {
        this.paquetesRecibidos = paquetesRecibidos;
    }

    public ListaPaquetes getPaquetesEnviados() {
        return this.paquetesEnviados;
    }

    public void setPaquetesEnviados(ListaPaquetes paquetesEnviados) {
        this.paquetesEnviados = paquetesEnviados;
    }

}