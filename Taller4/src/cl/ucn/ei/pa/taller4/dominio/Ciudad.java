package cl.ucn.ei.pa.taller4.dominio;

import java.util.LinkedList;

public class Ciudad{ 
    private String nombreCiudad;
    private LinkedList<Cliente> clientes;

    public Ciudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
        clientes= new LinkedList<Cliente>();
    }

    public String getNombreCiudad() {
        return this.nombreCiudad;
    }

    public LinkedList<Cliente> getClientes() {
        return this.clientes;
    }
}