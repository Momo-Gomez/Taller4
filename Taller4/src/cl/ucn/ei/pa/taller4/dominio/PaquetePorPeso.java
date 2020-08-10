package cl.ucn.ei.pa.taller4.dominio;

import cl.ucn.ei.pa.taller4.logica.Paquete;

public class PaquetePorPeso extends Paquete{
    private double peso;

    public PaquetePorPeso(String codigo,double peso) {
        super(codigo);
        this.peso = peso;
    }

    public double getPeso() {
        return this.peso;
    }

}