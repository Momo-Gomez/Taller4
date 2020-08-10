package cl.ucn.ei.pa.taller4.logica;

public class ListaPaquetes {
    private NodoPaquete first;
    private NodoPaquete last;
    public ListaPaquetes(){
        first=null;
        last=null;
    }
    public boolean isEmpety(){
        return first==null;
    }
    public void insertar(Paquete paquete){
        NodoPaquete nn = new NodoPaquete(paquete);
        if (isEmpety()){
            last=nn;
        }
        else{
            if(first==last){
                nn.setNext(first);
            }
            first.setPrevio(nn);
        }
       
        nn.setPrevio(last);// aprender nodos falta mucho
        last.setNext(nn);
        first=nn;
    }
}