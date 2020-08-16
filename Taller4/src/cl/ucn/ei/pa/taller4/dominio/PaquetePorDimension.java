package cl.ucn.ei.pa.taller4.dominio;

import cl.ucn.ei.pa.taller4.logica.Paquete;

public class PaquetePorDimension extends Paquete {
    private double largo;
    private double alto;
    private double ancho;

    public PaquetePorDimension(String codigo, double largo, double alto, double ancho) {
        super(codigo);
        this.largo = largo;
        this.alto = alto;
        this.ancho = ancho;
    }

    public double getLargo() {
        return this.largo;
    }

    public double getAlto() {
        return this.alto;
    }

    public double getAncho() {
        return this.ancho;
    }

    @Override
    public double obtenerValor() {
        return (largo * ancho * alto * 50);
    }

}