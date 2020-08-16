package cl.ucn.ei.pa.taller4.logica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import cl.ucn.ei.pa.taller4.dominio.Ciudad;
import cl.ucn.ei.pa.taller4.dominio.Cliente;
import cl.ucn.ei.pa.taller4.dominio.PaquetePorDimension;
import cl.ucn.ei.pa.taller4.dominio.PaquetePorPeso;

public class CorreoUcnImpl implements ICorreoUcn {
    private ListaPaquetes listaPaquetes;
    private LinkedList<Cliente> clientes;
    private ArrayList<Ciudad> ciudades;
    

    public CorreoUcnImpl() {
        listaPaquetes = new ListaPaquetes();
        clientes = new LinkedList<Cliente>();
        ciudades = new ArrayList<Ciudad>();
    }


    @Override
    public void ingresarCiudad(String nombreCiudad) {
        if (existeCiudad(nombreCiudad) == null) { // si no esta la ciudad (null) la crea y la agrega
            ciudades.add(new Ciudad(nombreCiudad));
        }
    }

    @Override
    public void ingresarCliente(String rut, String nombre, String apellido, String nombreCiudad) {
        Ciudad ciudad = existeCiudad(nombreCiudad);
        if (ciudad != null) { // verifica que este la ciudad y si no esta la ciudad pasa del cliente
            if (existeCliente(rut) == null) { // verifica que este el cliente si no esta lo crea
                Cliente c = new Cliente(rut, nombre, apellido);
                ciudad.getClientes().add(c);// lo agrega a la lista particular de la ciudad
                clientes.add(c);// lo agrega a la lista general de clientes
            }
        }
    }

    @Override
    public void ingresarEnvioPorDimension(String codigo, String rutEmisor, String rutDestinatario, double largo,
            double alto, double ancho) {
        PaquetePorDimension p = new PaquetePorDimension(codigo, largo, alto, ancho);// crea el paquete
        agregarPaquetesaListas(rutEmisor, rutDestinatario, p);// lo agrega a las listas;
    }

    @Override
    public void ingresarEnvioPorPeso(String codigo, String rutEmisor, String rutDestinatario, double peso) {
        PaquetePorPeso p = new PaquetePorPeso(codigo, peso);// crea el paquete
        agregarPaquetesaListas(rutEmisor, rutDestinatario, p);// lo agrega a la listas
    }

    @Override
    public String obtenerEnviosPorTipo(String tipo) {
        String mensaje = "";
        NodoPaquete current = listaPaquetes.getFirst();
        do {
            if (tipo.equalsIgnoreCase("D") && current.getPaquete() instanceof PaquetePorDimension) {
                PaquetePorDimension p = (PaquetePorDimension) current.getPaquete();
                mensaje += "Codigo: " + p.getCodigo() + "\tPrecio: " + p.obtenerValor() + "\n";
            } else if (tipo.equalsIgnoreCase("P") && current.getPaquete() instanceof PaquetePorPeso) {
                PaquetePorPeso p = (PaquetePorPeso) current.getPaquete();
                mensaje += "Codigo: " + p.getCodigo() + "\tPrecio: " + p.obtenerValor() + "\n";
            }
            current = current.getNext();
        } while (current != listaPaquetes.getFirst());
        return mensaje;
    }

    @Override
    public String obtenerEnviosPorPersona(String rut) {
        String mensaje = "";
        Cliente cliente = existeCliente(rut);
        if (cliente != null) {
            mensaje += "Paquetes Enviados\n" + obtenerDatosDeListasDePaquetes(cliente.getPaquetesEnviados()) + "\n";
            mensaje += "Paquetes Recibidos\n" + obtenerDatosDeListasDePaquetes(cliente.getPaquetesRecibidos()) + "\n";
        }
        return mensaje;
    }

    @Override
    public String obtenerEnviosPorCiudad() {
        String mensaje = "";
        for (Ciudad ciudad : ciudades) {
            mensaje += ciudad.getNombreCiudad() + "\n";
            for (Cliente cliente : ciudad.getClientes()) {
                mensaje += "Paquetes enviados: " + cliente.getPaquetesEnviados().getCantidad() + "\n";
                mensaje += "Paquetes recibidos: " + cliente.getPaquetesRecibidos().getCantidad()+ "\n";
            }
        }
        return mensaje;
    }

    @Override
    public void actualizarClientes() {
       

    }

    @Override
    public void actualizarEnvios() {
        // TODO Auto-generated method stub

    }

    @Override
    public Ciudad existeCiudad(String nombreCiudad) {
        Iterator<Ciudad> Iciudades = ciudades.iterator();
        while (Iciudades.hasNext()) {
            Ciudad ciudad = (Ciudad) Iciudades.next();
            if (ciudad.getNombreCiudad().equals(nombreCiudad)) {
                return ciudad;
            }
        }
        return null;
    }

    @Override
    public Cliente existeCliente(String rut) {
        Iterator<Cliente> Iclientes = clientes.iterator();
        while (Iclientes.hasNext()) {
            Cliente cliente = (Cliente) Iclientes.next();
            if (cliente.getRut().equals(rut)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public void agregarPaquetesaListas(String rutEmisor, String rutDestinatario, Paquete p) {
        Cliente emisor = existeCliente(rutEmisor);
        Cliente receptor = existeCliente(rutDestinatario);
        if (emisor != null && receptor != null) { // verifica que este el emisor y el receptor
            emisor.getPaquetesEnviados().insertarPrimer(p);// agrega el paquete en enviados del emisor
            receptor.getPaquetesRecibidos().insertarPrimer(p);// agrega el paquete a recibidos del receptor
            listaPaquetes.insertarPrimer(p);// lo agrega a la lista general de paquetes;
       }
    }

    @Override
    public String obtenerDatosDeListasDePaquetes(ListaPaquetes paquetes) {
        String mensaje = "";
        NodoPaquete current = paquetes.getFirst();
        do {
            if (current.getPaquete() instanceof PaquetePorDimension) {
                PaquetePorDimension p = (PaquetePorDimension) current.getPaquete();
                mensaje += "Codigo: " + p.getCodigo() + "\tPrecio: " + p.obtenerValor() + "\n";
            } else if (current.getPaquete() instanceof PaquetePorPeso) {
                PaquetePorPeso p = (PaquetePorPeso) current.getPaquete();
                mensaje += "Codigo: " + p.getCodigo() + "\tPrecio: " + p.obtenerValor() + "\n";
            }
            current = current.getNext();
        } while (current != paquetes.getFirst());
        return mensaje;
    }

    @Override
    public String obtenerCodigo() {
        String cod="";
        cod+= (Integer.parseInt(listaPaquetes.getFirst().getPaquete().getCodigo())+1);
        return cod ;
    }
    
}