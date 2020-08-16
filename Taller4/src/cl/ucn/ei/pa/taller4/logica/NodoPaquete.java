package cl.ucn.ei.pa.taller4.logica;

public class NodoPaquete {
    private Paquete paquete;
    private NodoPaquete next;
    private NodoPaquete previo;

    public NodoPaquete(Paquete paquete) {
        this.paquete = paquete;
        next = null;
        previo = null;
    }

    public Paquete getPaquete() {
        return this.paquete;
    }

    public NodoPaquete getNext() {
        return this.next;
    }

    public void setNext(NodoPaquete next) {
        this.next = next;
    }

    public NodoPaquete getPrevio() {
        return this.previo;
    }

    public void setPrevio(NodoPaquete previo) {
        this.previo = previo;
    }

}