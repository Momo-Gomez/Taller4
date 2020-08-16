package cl.ucn.ei.pa.taller4.logica;

public class ListaPaquetes {
    private NodoPaquete first;
    private NodoPaquete last;
    private int cantidad;

    public ListaPaquetes() {
        first = null;
        last = null;
        cantidad = 0;
    }

    public boolean isEmpety() {
        return first == null;
    }

    public void insertarPrimer(Paquete paquete) {
        NodoPaquete nn = new NodoPaquete(paquete);
        if (isEmpety()) {
            last = nn;
        } else {
            nn.setNext(first);
            first.setPrevio(nn);
        }
        nn.setPrevio(last);
        last.setNext(nn);
        first = nn;
        cantidad++;
    }

    public NodoPaquete getFirst() {
        return first;
    }

    public NodoPaquete getLast() {
        return last;
    }

    public void setFirst(NodoPaquete first) {
        this.first = first;
    }

    public void setLast(NodoPaquete last) {
        this.last = last;
    }

    public int getCantidad() {
        return cantidad;
    }

}