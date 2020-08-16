package cl.ucn.ei.pa.taller4.logica;

public abstract class Paquete {
    private String codigo;

    public Paquete(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public abstract double obtenerValor();
}