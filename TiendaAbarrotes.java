
import java.util.*;

public class TiendaAbarrotes {

    static Scanner sc = new Scanner(System.in);

    static int idTrabajador = 1;
    static int idProveedor = 1;
    static int idProducto = 1;
    static int idMovimiento = 1;

    static Map<Integer, Trabajador> trabajadores = new LinkedHashMap<>();
    static Map<Integer, Proveedor> proveedores = new LinkedHashMap<>();
    static Map<Integer, Producto> productos = new LinkedHashMap<>();

    static ArrayList<String> historial = new ArrayList<>();

    // CLASE TRABAJADOR
    static class Trabajador {
        int id;
        String nombre, puesto, telefono;

        Trabajador(int id, String nombre,
                   String puesto, String telefono) {
            this.id = id;
            this.nombre = nombre;
            this.puesto = puesto;
            this.telefono = telefono;
        }

        public String toString() {
            return "ID: " + id +
                   " | Nombre: " + nombre +
                   " | Puesto: " + puesto +
                   " | Telefono: " + telefono;
        }
    }

    // CLASE PROVEEDOR
    static class Proveedor {
        int id;
        String nombre, telefono, direccion;

        Proveedor(int id, String nombre,
                  String telefono, String direccion) {
            this.id = id;
            this.nombre = nombre;
            this.telefono = telefono;
            this.direccion = direccion;
        }

        public String toString() {
            return "ID: " + id +
                   " | Nombre: " + nombre +
                   " | Telefono: " + telefono +
                   " | Direccion: " + direccion;
        }
    }

    // CLASE PRODUCTO
    static class Producto {
        int id;
        String nombre;
        double precio;
        int cantidad;

        Producto(int id, String nombre,
                 double precio, int cantidad) {
            this.id = id;
            this.nombre = nombre;
            this.precio = precio;
            this.cantidad = cantidad;
        }

        public String toString() {
            return "ID: " + id +
                   " | Producto: " + nombre +
                   " | Precio: $" + precio +
                   " | Existencias: " + cantidad;
        }
    }

    // MENU PRINCIPAL
    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("\n================================");
            System.out.println("     TIENDA DE ABARROTES");
            System.out.println("================================");
            System.out.println("1. Gestionar trabajadores");
            System.out.println("2. Gestionar proveedores");
            System.out.println("3. Registrar productos");
            System.out.println("4. Entrada de mercancia");
            System.out.println("5. Salida de mercancia");
            System.out.println("6. Consultar inventario");
            System.out.println("7. Historial de movimientos");
            System.out.println("8. Eliminar producto");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    menuTrabajadores();
                    break;
                case 2:
                    menuProveedores();
                    break;
                case 3:
                    registrarProducto();
                    break;
                case 4:
                    entradaMercancia();
                    break;
                case 5:
                    salidaMercancia();
                    break;
                case 6:
                    consultarInventario();
                    break;
                case 7:
                    mostrarHistorial();
                    break;
                case 8:
                    eliminarProducto();
                    break;
                case 9:
                    System.out.println("Sistema finalizado.");
                    break;
                default:
                    System.out.println("Opcion incorrecta.");
            }

        } while (opcion != 9);
    }

    // GESTION DE TRABAJADORES
    static void menuTrabajadores() {

        System.out.println("\n--- TRABAJADORES ---");
        System.out.println("1. Registrar trabajador");
        System.out.println("2. Consultar trabajadores");
        System.out.println("3. Eliminar trabajador");
        System.out.print("Opcion: ");

        int op = leerEntero();

        switch (op) {
            case 1:
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();

                System.out.print("Puesto: ");
                String puesto = sc.nextLine();

                System.out.print("Telefono: ");
                String telefono = sc.nextLine();

                int id = idTrabajador++;

                trabajadores.put(id,
                    new Trabajador(id, nombre, puesto, telefono));

                System.out.println("Trabajador registrado. ID: " + id);
                break;

            case 2:
                for (Trabajador t : trabajadores.values()) {
                    System.out.println(t);
                }
                break;

            case 3:
                System.out.print("ID del trabajador: ");
                int eliminar = leerEntero();

                if (trabajadores.remove(eliminar) != null) {
                    System.out.println("Trabajador eliminado.");
                } else {
                    System.out.println("ID no encontrado.");
                }
                break;

            default:
                System.out.println("Opcion incorrecta.");
        }
    }

    // GESTION DE PROVEEDORES
    static void menuProveedores() {

        System.out.println("\n--- PROVEEDORES ---");
        System.out.println("1. Registrar proveedor");
        System.out.println("2. Consultar proveedores");
        System.out.print("Opcion: ");

        int op = leerEntero();

        if (op == 1) {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Telefono: ");
            String telefono = sc.nextLine();

            System.out.print("Direccion: ");
            String direccion = sc.nextLine();

            int id = idProveedor++;

            proveedores.put(id,
                new Proveedor(id, nombre, telefono, direccion));

            System.out.println("Proveedor registrado. ID: " + id);

        } else if (op == 2) {

            for (Proveedor p : proveedores.values()) {
                System.out.println(p);
            }

        } else {
            System.out.println("Opcion incorrecta.");
        }
    }

    // REGISTRAR PRODUCTOS
    static void registrarProducto() {

        System.out.println("\n--- REGISTRO DE PRODUCTOS ---");

        System.out.print("Nombre del producto: ");
        String nombre = sc.nextLine();

        System.out.print("Precio: ");
        double precio = leerDouble();

        System.out.print("Cantidad inicial: ");
        int cantidad = leerEntero();

        if (precio < 0 || cantidad < 0) {
            System.out.println("No se permiten valores negativos.");
            return;
        }

        int id = idProducto++;

        productos.put(id,
            new Producto(id, nombre, precio, cantidad));

        System.out.println("Producto registrado. ID: " + id);
    }

    // ENTRADA DE MERCANCIA
    static void entradaMercancia() {

        System.out.println("\n--- ENTRADA DE MERCANCIA ---");

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        System.out.print("ID del producto: ");
        int id = leerEntero();

        Producto p = productos.get(id);

        if (p == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.print("ID del proveedor: ");
        int idProv = leerEntero();

        if (!proveedores.containsKey(idProv)) {
            System.out.println("Proveedor no encontrado.");
            return;
        }

        System.out.print("Cantidad que ingresa: ");
        int cantidad = leerEntero();

        if (cantidad <= 0) {
            System.out.println("Cantidad no valida.");
            return;
        }

        p.cantidad += cantidad;

        historial.add("Movimiento " + idMovimiento++ +
            " | ENTRADA | Producto: " + p.nombre +
            " | Cantidad: " + cantidad +
            " | Proveedor ID: " + idProv);

        System.out.println("Entrada registrada correctamente.");
        System.out.println("Existencias actuales: " + p.cantidad);
    }

    // SALIDA DE MERCANCIA
    static void salidaMercancia() {

        System.out.println("\n--- SALIDA DE MERCANCIA ---");

        System.out.print("ID del producto: ");
        int id = leerEntero();

        Producto p = productos.get(id);

        if (p == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.print("Cantidad que sale: ");
        int cantidad = leerEntero();

        if (cantidad <= 0) {
            System.out.println("Cantidad no valida.");
            return;
        }

        if (cantidad > p.cantidad) {
            System.out.println("No hay suficiente mercancia.");
            return;
        }

        p.cantidad -= cantidad;

        historial.add("Movimiento " + idMovimiento++ +
            " | SALIDA | Producto: " + p.nombre +
            " | Cantidad: " + cantidad);

        System.out.println("Salida registrada correctamente.");
        System.out.println("Existencias actuales: " + p.cantidad);
    }

    // CONSULTAR INVENTARIO
    static void consultarInventario() {

        System.out.println("\n--- INVENTARIO GENERAL ---");

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        for (Producto p : productos.values()) {
            System.out.println(p);
        }
    }

    // ELIMINAR PRODUCTO
    static void eliminarProducto() {

        System.out.print("ID del producto a eliminar: ");
        int id = leerEntero();

        if (productos.remove(id) != null) {
            System.out.println("Producto eliminado.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    // HISTORIAL DE MOVIMIENTOS
    static void mostrarHistorial() {

        System.out.println("\n--- HISTORIAL DE MOVIMIENTOS ---");

        if (historial.isEmpty()) {
            System.out.println("No existen movimientos.");
            return;
        }

        for (String movimiento : historial) {
            System.out.println(movimiento);
        }
    }

    // LECTURA SEGURA DE NUMEROS ENTEROS
    static int leerEntero() {
        while (true) {
            try {
                int numero = Integer.parseInt(sc.nextLine());
                return numero;
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero entero valido: ");
            }
        }
    }

    // LECTURA SEGURA DE DECIMALES
    static double leerDouble() {
        while (true) {
            try {
                double numero = Double.parseDouble(sc.nextLine());
                return numero;
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un precio valido: ");
            }
        }
    }
}
