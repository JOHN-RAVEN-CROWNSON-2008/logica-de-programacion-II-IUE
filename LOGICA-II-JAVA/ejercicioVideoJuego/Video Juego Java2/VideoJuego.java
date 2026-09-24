import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VideoJuego {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Personaje> personajes = new ArrayList<>();

    public static void main(String[] args) {
        // Cargar personajes iniciales por defecto
        inicializarPersonajes();

        boolean salir = false;

        System.out.println("==========================================================");
        System.out.println("   BIENVENIDO AL SISTEMA INTERACTIVO DE VIDEOJUEGO");
        System.out.println("==========================================================");

        while (!salir) {
            System.out.println("\n----------------- MENU DE ACCIONES -----------------");
            System.out.println("1. Atacar a otro personaje    -> atacar(Personaje objetivo)");
            System.out.println("2. Aplicar dano directo       -> recibirDano(int cantidad)");
            System.out.println("3. Ver ficha de un personaje  -> mostrarInformacion()");
            System.out.println("4. Ver todos los personajes");
            System.out.println("5. Crear un nuevo personaje");
            System.out.println("6. Salir");
            System.out.print("Elige una opcion (1-6): ");

            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    ejecutarAtaque();
                    break;
                case "2":
                    ejecutarRecibirDano();
                    break;
                case "3":
                    ejecutarMostrarInformacion();
                    break;
                case "4":
                    mostrarTodosLosPersonajes();
                    break;
                case "5":
                    crearNuevoPersonaje();
                    break;
                case "6":
                    salir = true;
                    System.out.println("\n[!] Gracias por jugar. Fin del programa.");
                    break;
                default:
                    System.out.println("[!] Opcion no valida. Por favor ingresa un numero entre 1 y 6.");
            }
        }
    }

    // Personajes precargados para interactuar de inmediato
    private static void inicializarPersonajes() {
        personajes.add(new Personaje("Arthur (Guerrero)", 5, 120, 30));
        personajes.add(new Personaje("Morgana (Hechicera)", 6, 80, 45));
        personajes.add(new Personaje("Legolas (Arquero)", 4, 95, 25));
        personajes.add(new Personaje("Dragon de Fuego (Jefe)", 8, 200, 35));
    }

    // Muestra la lista resumida y numerada de personajes
    private static void listarPersonajes() {
        System.out.println("\n--- LISTA DE PERSONAJES ---");
        for (int i = 0; i < personajes.size(); i++) {
            Personaje p = personajes.get(i);
            String estado = p.estaVivo() ? p.getVida() + "/" + p.getVidaMaxima() + " HP" : "DERROTADO (0 HP)";
            System.out.printf("[%d] %-25s | Nivel: %-2d | Vida: %-16s | Ataque: %-2d%n",
                    (i + 1), p.getNombre(), p.getNivel(), estado, p.getAtaque());
        }
    }

    // Permite al usuario seleccionar un personaje de la lista de forma segura
    private static Personaje seleccionarPersonaje(String prompt) {
        if (personajes.isEmpty()) {
            System.out.println("[!] No hay personajes registrados.");
            return null;
        }

        while (true) {
            listarPersonajes();
            System.out.print(prompt + " (o 0 para cancelar): ");
            String input = scanner.nextLine().trim();

            try {
                int seleccion = Integer.parseInt(input);
                if (seleccion == 0) {
                    return null;
                }
                if (seleccion >= 1 && seleccion <= personajes.size()) {
                    return personajes.get(seleccion - 1);
                }
                System.out.println("[!] Numero fuera de rango. Selecciona entre 1 y " + personajes.size() + ".");
            } catch (NumberFormatException e) {
                System.out.println("[!] Entrada invalida. Debes ingresar el numero del personaje.");
            }
        }
    }

    // =========================================================================
    // ACCION 1: Invoca el método atacar(Personaje objetivo)
    // =========================================================================
    private static void ejecutarAtaque() {
        System.out.println("\n--- MODO ATAQUE ---");
        Personaje atacante = seleccionarPersonaje("Selecciona el numero del ATACANTE");
        if (atacante == null) {
            System.out.println("[*] Accion cancelada.");
            return;
        }

        Personaje objetivo = seleccionarPersonaje("Selecciona el numero del OBJETIVO a atacar");
        if (objetivo == null) {
            System.out.println("[*] Accion cancelada.");
            return;
        }

        // Llamada directa al método solicitado
        atacante.atacar(objetivo);
    }

    // =========================================================================
    // ACCION 2: Invoca el método recibirDano(int cantidad)
    // =========================================================================
    private static void ejecutarRecibirDano() {
        System.out.println("\n--- MODO RECIBIR DANO DIRECTO ---");
        Personaje p = seleccionarPersonaje("Selecciona el personaje que recibira el dano");
        if (p == null) {
            System.out.println("[*] Accion cancelada.");
            return;
        }

        int cantidad = leerEnteroPositivo("\nIngresa los puntos de dano a infligir: ");
        
        // Llamada directa al método solicitado
        p.recibirDano(cantidad);
    }

    // =========================================================================
    // ACCION 3: Invoca el método mostrarInformacion()
    // =========================================================================
    private static void ejecutarMostrarInformacion() {
        System.out.println("\n--- CONSULTAR FICHA DE PERSONAJE ---");
        Personaje p = seleccionarPersonaje("Selecciona el personaje a consultar");
        if (p == null) {
            System.out.println("[*] Accion cancelada.");
            return;
        }

        System.out.println();
        // Llamada directa al método solicitado
        p.mostrarInformacion();
    }

    // ACCION 4: Muestra todas las fichas
    private static void mostrarTodosLosPersonajes() {
        System.out.println("\n==================================================");
        System.out.println("         FICHAS DE TODOS LOS PERSONAJES           ");
        System.out.println("==================================================");
        for (Personaje p : personajes) {
            p.mostrarInformacion();
            System.out.println();
        }
    }

    // ACCION 5: Crear un personaje personalizado
    private static void crearNuevoPersonaje() {
        System.out.println("\n--- CREACION DE NUEVO PERSONAJE ---");
        System.out.print("Nombre del personaje: ");
        String nombre = scanner.nextLine().trim();
        while (nombre.isEmpty()) {
            System.out.print("[!] El nombre no puede estar vacio. Intenta de nuevo: ");
            nombre = scanner.nextLine().trim();
        }

        int nivel = leerEnteroPositivo("Nivel del personaje (ej. 1): ");
        int vida = leerEnteroPositivo("Puntos de vida (ej. 100): ");
        int ataque = leerEnteroPositivo("Puntos de ataque (ej. 25): ");

        Personaje nuevo = new Personaje(nombre, nivel, vida, ataque);
        personajes.add(nuevo);

        System.out.println("\n[OK] Personaje creado exitosamente:");
        nuevo.mostrarInformacion();
    }

    // Validación auxiliar para evitar caídas por texto en campos numéricos
    private static int leerEnteroPositivo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();
            try {
                int valor = Integer.parseInt(input);
                if (valor >= 0) {
                    return valor;
                }
                System.out.println("[!] El valor debe ser mayor o igual a 0.");
            } catch (NumberFormatException e) {
                System.out.println("[!] Entrada invalida. Debes ingresar un numero entero.");
            }
        }
    }
}

/**
 * Clase que representa a los personajes del videojuego.
 */
class Personaje {
    // Atributos solicitados
    private String nombre;
    private int nivel;
    private int vida;
    private int ataque;
    private int vidaMaxima;

    // Constructor
    public Personaje(String nombre, int nivel, int vida, int ataque) {
        this.nombre = nombre;
        this.nivel = (nivel > 0) ? nivel : 1;
        this.vidaMaxima = (vida > 0) ? vida : 1;
        this.vida = this.vidaMaxima;
        this.ataque = (ataque >= 0) ? ataque : 0;
    }

    // Método atacar: valida estado e inflige daño al objetivo
    public void atacar(Personaje objetivo) {
        if (!this.estaVivo()) {
            System.out.println("\n[!] " + this.nombre + " no puede atacar porque ha sido derrotado.");
            return;
        }

        if (objetivo == null) {
            System.out.println("\n[!] No hay un objetivo valido al que atacar.");
            return;
        }

        if (objetivo == this) {
            System.out.println("\n[!] " + this.nombre + " no puede atacarse a si mismo.");
            return;
        }

        if (!objetivo.estaVivo()) {
            System.out.println("\n[!] " + this.nombre + " intento atacar a " + objetivo.getNombre() + ", pero ya ha sido derrotado.");
            return;
        }

        System.out.println("\n>> [ATAQUE] " + this.nombre + " (Nivel " + this.nivel + ") ataca ferozmente a " + objetivo.getNombre() + "!");
        
        // Aplica el daño al objetivo
        objetivo.recibirDano(this.ataque);
    }

    // Método recibirDano: descuenta vida y muestra detalles en consola
    public void recibirDano(int cantidad) {
        if (cantidad < 0) {
            cantidad = 0;
        }

        int vidaAnterior = this.vida;
        this.vida = Math.max(0, this.vida - cantidad);
        int danoRecibido = vidaAnterior - this.vida;

        System.out.println("   -> " + this.nombre + " recibe " + danoRecibido + " puntos de dano.");
        System.out.println("   -> Vida restante de " + this.nombre + ": " + this.vida + "/" + this.vidaMaxima + " HP.");

        if (this.vida == 0) {
            System.out.println("   -> [DERROTADO] " + this.nombre + " ha caido en combate.");
        }
    }

    // Método mostrarInformacion: despliega la ficha con estadísticas
    public void mostrarInformacion() {
        String estado = estaVivo() ? "Activo (En pie)" : "Incapacitado (Derrotado)";
        System.out.println("+--------------------------------------------------+");
        System.out.printf("| %-48s |%n", "PERSONAJE: " + this.nombre);
        System.out.println("+--------------------------------------------------+");
        System.out.printf("| Nivel:         %-33d |%n", this.nivel);
        System.out.printf("| Vida:          %-33s |%n", this.vida + " / " + this.vidaMaxima + " HP");
        System.out.printf("| Ataque:        %-33d |%n", this.ataque);
        System.out.printf("| Estado:        %-33s |%n", estado);
        System.out.println("+--------------------------------------------------+");
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = Math.max(1, nivel); }
    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = Math.max(0, Math.min(vida, this.vidaMaxima)); }
    public int getVidaMaxima() { return vidaMaxima; }
    public void setVidaMaxima(int vidaMaxima) { this.vidaMaxima = (vidaMaxima > 0) ? vidaMaxima : 1; if (this.vida > this.vidaMaxima) this.vida = this.vidaMaxima; }
    public int getAtaque() { return ataque; }
    public void setAtaque(int ataque) { this.ataque = Math.max(0, ataque); }
}