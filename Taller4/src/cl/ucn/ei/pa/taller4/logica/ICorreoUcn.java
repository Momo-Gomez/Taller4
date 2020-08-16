package cl.ucn.ei.pa.taller4.logica;

import cl.ucn.ei.pa.taller4.dominio.Ciudad;
import cl.ucn.ei.pa.taller4.dominio.Cliente;

public interface ICorreoUcn {
        public void ingresarCiudad(String nombreCiudad);

        public void ingresarCliente(String rut, String nombre, String apellido, String nombreCiudad);

        public void ingresarEnvioPorDimension(String codigo, String rutEmisor, String rutDestinatario, double largo,
                        double alto, double ancho);

        public void ingresarEnvioPorPeso(String codigo, String rutEmisor, String rutDestinatario, double peso);

        public String obtenerEnviosPorTipo(String tipo);

        public String obtenerEnviosPorPersona(String rut);

        public String obtenerEnviosPorCiudad();

        public void actualizarClientes();

        public void actualizarEnvios();

        public Ciudad existeCiudad(String nombreCiudad);

        public Cliente existeCliente(String rut);
        
        public void agregarPaquetesaListas(String rutEmisor, String RutDestinatario, Paquete p);
        
        public String obtenerDatosDeListasDePaquetes(ListaPaquetes paquetes);

        public String obtenerCodigo();
}