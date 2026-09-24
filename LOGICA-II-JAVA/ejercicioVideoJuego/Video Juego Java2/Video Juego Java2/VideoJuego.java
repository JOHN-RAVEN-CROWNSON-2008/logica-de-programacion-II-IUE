public class VideoJuego {
    public static void main(String[] args) {
        // 1. Crear diferentes personajes usando el constructor
        Personaje p1 = new Personaje("Guerrero", 1, 100, 25);
        Personaje p2 = new Personaje("Mago", 1, 80, 30);
        Personaje p3 = new Personaje("Arquero", 1, 90, 20);

        // 2. Mostrar la información inicial
        System.out.println("--- ESTADO INICIAL ---");
        p1.mostrarInformacion();
        p2.mostrarInformacion();
        p3.mostrarInformacion();

        // 3. Hacer que los personajes interactúen atacándose
        System.out.println("\n--- COMBATE ---");
        p1.atacar(p2); // Guerrero ataca a Mago
        p2.atacar(p1); // Mago contraataca a Guerrero
        p3.atacar(p2); // Arquero ataca a Mago
        p1.atacar(p2); // Guerrero remata a Mago

        // 4. Mostrar el estado final tras los ataques
        System.out.println("\n--- ESTADO FINAL ---");
        p1.mostrarInformacion();
        p2.mostrarInformacion();
        p3.mostrarInformacion();
    }
}

class Personaje {
    // Atributos solicitados
    String nombre;
    int nivel;
    int vida;
    int ataque;

    // Constructor básico
    public Personaje(String nombre, int nivel, int vida, int ataque) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.vida = vida;
        this.ataque = ataque;
    }

    // Método atacar: recibe por parámetro al personaje objetivo
    public void atacar(Personaje objetivo) {
        System.out.println(this.nombre + " ataca a " + objetivo.nombre + "!");
        objetivo.recibirDaño(this.ataque);
    }

    // Método recibir daño: descuenta vida y muestra el daño y la vida restante
    public void recibirDaño(int cantidad) {
        this.vida = this.vida - cantidad;
        
        // Evita que la vida quede en números negativos
        if (this.vida < 0) {
            this.vida = 0;
        }

        System.out.println(this.nombre + " recibio " + cantidad + " puntos de daño. Vida restante: " + this.vida);
    }

    // Método para mostrar los datos del personaje
    public void mostrarInformacion() {
        System.out.println("Personaje: " + this.nombre + " | Nivel: " + this.nivel + " | Vida: " + this.vida + " | Ataque: " + this.ataque);
    }
}