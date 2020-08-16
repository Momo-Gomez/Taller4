package cl.ucn.ei.pa.taller4.logica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AppCorreoUcn {
    public static void main(String[] args) {
        ICorreoUcn app = new CorreoUcnImpl();
        lecturaCiudades(app);
        lecturaClientes(app);
        lecturaPaquetes(app);
        while (!menu(app)) {
        }
        actualizacionClientes(app);
        actualizacionPaquetes(app);
    }

    private static void lecturaCiudades(ICorreoUcn app) {
        FileReader fr;
        BufferedReader bf;
        String nombreCiudad;
        System.out.println("Leyendo archivos espere!.....");
        try {
            fr = new FileReader("ciudades.txt");// cambiar la direccion de los archivos por favor, es diferente en
                                                // VisualStudio
            bf = new BufferedReader(fr);
            nombreCiudad = bf.readLine();
            while (nombreCiudad != null) {
                app.ingresarCiudad(nombreCiudad);
                nombreCiudad = bf.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void lecturaClientes(ICorreoUcn app) {
        FileReader fr;
        BufferedReader bf;
        String linea;
        String[] partes;
        try {
            fr = new FileReader("clientes.txt");// cambiar la direccion de los archivos por favor, es diferente en
                                                // VisualStudio
            bf = new BufferedReader(fr);
            linea = bf.readLine();
            while (linea != null) {
                partes = linea.split(",");
                app.ingresarCliente(partes[0], partes[1], partes[2], partes[3]);
                linea = bf.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void lecturaPaquetes(ICorreoUcn app) {
        FileReader fr;
        BufferedReader bf;
        String linea;
        String[] partes;
        try {
            fr = new FileReader("envios.txt");// cambiar la direccion de los archivos por favor, es diferente en
                                              // VisualStudio
            bf = new BufferedReader(fr);
            linea = bf.readLine();
            while (linea != null) {
                partes = linea.split(",");
                if (partes[1].equalsIgnoreCase("D")) {
                    app.ingresarEnvioPorDimension(partes[0], partes[2], partes[3], Integer.parseInt(partes[4]),
                            Integer.parseInt(partes[5]), Integer.parseInt(partes[6]));
                } else {
                    app.ingresarEnvioPorPeso(partes[0], partes[2], partes[3], Integer.parseInt(partes[4]));
                }
                linea = bf.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean menu(ICorreoUcn app) {
        boolean exite = false;
        Scanner sc = new Scanner(System.in);
        System.out
                .print("Bienvenido!\nIngrese una opcion\n(1)Realizar Envio\n(2)Reporte Envios\n(3)Salir\nSu opción: ");
        switch (sc.nextLine()) {
            case "1":
                realizarEnvio(app);
                break;

            case "2":
                reportesEnvios(app);
                break;
            case "3":
                exite = true;
                break;
            default:
                System.out.println("Opción no valida, ingrese valores numericos entre 1-2");
                break;
        }
        return exite;
    }

    private static void reportesEnvios(ICorreoUcn app) {
        Scanner sc = new Scanner(System.in);
        System.out.print(
                "Ingrese una opcion para reportar\n(1)Envios por tipo\n(2)Envios por persona\n(3)Envios por ciudad\n(4)Volver atras\nSu opción: ");
        switch (sc.nextLine()) {
            case "1":
                System.out.print("Envios por dimensiones\n"+app.obtenerEnviosPorTipo("D"));
                System.out.print("Envios por peso\n"+app.obtenerEnviosPorTipo("P"));
                break;
            case "2":
                System.out.print("Ingrese el rut de la persona a buscar: ");
                String rut= sc.nextLine();
                System.out.print(app.obtenerEnviosPorPersona(rut));
                break;
            case "3":
             System.out.print(app.obtenerEnviosPorCiudad());
                break;
            case "4":
                break;
            default:
                System.out.println("Opción no valida, ingrese valores numericos entre 1-4");
                break;
        }
    }

    private static void realizarEnvio(ICorreoUcn app) {
        Scanner sc;
        String rutEmisor;
        String rutDestinatario;
        try {
            sc = new Scanner(System.in);
            System.out.print(
                    "Ingrese tipo de envio\n(1)Envio por Dimension\n(2)Envio por peso\n(3)Volver atras\nSu opción: ");
            switch (sc.nextLine()) {
                case "1":
                    double largo;
                    double alto;
                    double ancho;
                    System.out.print("Ingrese el rut del remitente: ");
                    rutEmisor = sc.nextLine();
                    System.out.print("Ingrese el rut del Destinatario: ");
                    rutDestinatario = sc.nextLine();
                    System.out.print("Ingrese el largo del paquete: ");
                    largo = sc.nextInt();
                    System.out.print("Ingrese el alto del paquete: ");
                    alto = sc.nextInt();
                    System.out.print("Ingrese el ancho del paquete: ");
                    ancho = sc.nextInt();
                    if (app.existeCliente(rutDestinatario) != null && app.existeCliente(rutEmisor) != null) {
                        app.ingresarEnvioPorDimension(app.obtenerCodigo(), rutEmisor, rutDestinatario, largo, alto,
                                ancho);
                        System.out.println("Paquete ingresado exitosamente!");
                    } else if (app.existeCliente(rutEmisor) == null && app.existeCliente(rutDestinatario) != null) {
                        System.out.print(
                                "Remitente no encontrado, por favor registre al remitente\nIngrese el nombre de la ciudad: ");
                        String nombreCiudad = sc.nextLine();
                        nombreCiudad = sc.nextLine();
                        System.out.print("Ingrese su nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Ingrese su apellido: ");
                        String apellido = sc.nextLine();
                        if (app.existeCiudad(nombreCiudad) != null) {
                            app.ingresarCliente(rutEmisor, nombre, apellido, nombreCiudad);
                            app.ingresarEnvioPorDimension(app.obtenerCodigo(), rutEmisor, rutDestinatario, largo, alto,
                                    ancho);
                            System.out.println("Paquete ingresado exitosamente!");
                        } else {
                            System.out.println("Cliente no ingresado, ciudad inexistente");
                        }
                    } else {
                        System.out.println("Paquete no ingresado, destinatario inexistente");
                    }
                    break;
                case "2":
                    double peso;
                    System.out.print("Ingrese el rut del remitente: ");
                    rutEmisor = sc.nextLine();
                    System.out.print("Ingrese el rut del Destinatario: ");
                    rutDestinatario = sc.nextLine();
                    System.out.print("Ingrese el peso del paquete en kilogramos (max 20 kg): ");
                    peso = sc.nextInt();
                    if (app.existeCliente(rutDestinatario) != null && app.existeCliente(rutEmisor) != null
                            && peso <= 20) {
                        app.ingresarEnvioPorPeso(app.obtenerCodigo(), rutEmisor, rutDestinatario, peso);
                        System.out.println("Paquete ingresado exitosamente!");
                    } else if (app.existeCliente(rutEmisor) == null && app.existeCliente(rutDestinatario) != null) {
                        System.out.print(
                                "Remitente no encontrado, por favor registre al remitente\nIngrese el nombre de la ciudad: ");
                        String nombreCiudad = sc.nextLine();
                        nombreCiudad = sc.nextLine();
                        System.out.print("Ingrese su nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Ingrese su apellido: ");
                        String apellido = sc.nextLine();
                        if (app.existeCiudad(nombreCiudad) != null) {
                            app.ingresarCliente(rutEmisor, nombre, apellido, nombreCiudad);
                            app.ingresarEnvioPorPeso(app.obtenerCodigo(), rutEmisor, rutDestinatario, peso);
                            System.out.println("Paquete ingresado exitosamente!");
                        } else {
                            System.out.println("Cliente no ingresado, ciudad inexistente");
                        }
                    } else if (app.existeCliente(rutDestinatario) != null && app.existeCliente(rutEmisor) != null
                            && peso > 20) {
                        System.out.println("Paquete no ingresado, peso excedido");
                    } else {
                        System.out.println("Paquete no ingresado, destinatario inexistente");
                    }
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Opción no valida, ingrese valores numericos entre 1-2");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Valores no validos, ingrese solo numeros");
        }
    }

    private static void actualizacionClientes(ICorreoUcn app) {
    }

    private static void actualizacionPaquetes(ICorreoUcn app) {
    }

}