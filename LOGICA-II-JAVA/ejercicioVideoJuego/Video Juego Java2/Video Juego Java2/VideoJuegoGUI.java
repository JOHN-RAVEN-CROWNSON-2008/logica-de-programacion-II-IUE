import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Interfaz Gráfica de Usuario (GUI) para el sistema de personajes de videojuego.
 * Construida con Java Swing (estándar nativo del JDK, sin librerías externas).
 */
public class VideoJuegoGUI extends JFrame {

    // Lista de personajes registrados
    private List<Personaje> personajes;

    // Componentes visuales
    private JPanel panelTarjetasPersonajes;
    private JTextArea areaRegistroCombate;
    private JComboBox<Personaje> comboAtacante;
    private JComboBox<Personaje> comboObjetivo;
    private JComboBox<Personaje> comboDanoDirecto;
    private JSpinner spinnerDano;

    public VideoJuegoGUI() {
        super("⚔️ Sistema de Administración de Personajes - Videojuego");
        this.personajes = new ArrayList<>();

        // Configurar el sistema de logging para que los métodos de Personaje
        // escriban simultáneamente en la consola y en el área de texto de la GUI
        Personaje.setLogger(mensaje -> {
            System.out.println(mensaje);
            if (areaRegistroCombate != null) {
                areaRegistroCombate.append(mensaje + "\n");
                areaRegistroCombate.setCaretPosition(areaRegistroCombate.getDocument().getLength());
            }
        });

        // Inicializar personajes por defecto
        inicializarPersonajes();

        // Configuración de la ventana principal
        configurarVentana();

        // Construir la interfaz
        construirInterfaz();

        // Actualizar datos iniciales
        actualizarTodo();
    }

    private void inicializarPersonajes() {
        personajes.add(new Personaje("Arthur (Guerrero)", 5, 120, 30));
        personajes.add(new Personaje("Morgana (Hechicera)", 6, 80, 45));
        personajes.add(new Personaje("Legolas (Arquero)", 4, 95, 25));
        personajes.add(new Personaje("Dragon de Fuego (Jefe)", 8, 200, 35));
    }

    private void configurarVentana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 720);
        setMinimumSize(new Dimension(850, 600));
        setLocationRelativeTo(null); // Centrar en pantalla
    }

    private void construirInterfaz() {
        setLayout(new BorderLayout(10, 10));

        // 1. Encabezado superior
        JPanel panelEncabezado = new JPanel();
        panelEncabezado.setBackground(new Color(30, 41, 59));
        panelEncabezado.setBorder(new EmptyBorder(12, 15, 12, 15));
        JLabel lblTitulo = new JLabel("🎮 PANEL DE COMBATE Y GESTIÓN DE PERSONAJES");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        panelEncabezado.add(lblTitulo);
        add(panelEncabezado, BorderLayout.NORTH);

        // 2. Panel Central dividido: Izquierda (Personajes) y Derecha (Registro de Combate)
        JSplitPane splitCentral = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitCentral.setResizeWeight(0.55);
        splitCentral.setContinuousLayout(true);

        // Subpanel Izquierdo: Lista visual de personajes en tarjetas
        panelTarjetasPersonajes = new JPanel();
        panelTarjetasPersonajes.setLayout(new BoxLayout(panelTarjetasPersonajes, BoxLayout.Y_AXIS));
        panelTarjetasPersonajes.setBackground(new Color(241, 245, 249));
        JScrollPane scrollPersonajes = new JScrollPane(panelTarjetasPersonajes);
        scrollPersonajes.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225)),
                " ESTADO DE LOS PERSONAJES ",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13)
        ));
        splitCentral.setLeftComponent(scrollPersonajes);

        // Subpanel Derecho: Registro / Consola visual de combate
        JPanel panelLog = new JPanel(new BorderLayout());
        panelLog.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225)),
                " REGISTRO DE EVENTOS Y COMBATE ",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13)
        ));

        areaRegistroCombate = new JTextArea();
        areaRegistroCombate.setEditable(false);
        areaRegistroCombate.setFont(new Font("Consolas", Font.PLAIN, 13));
        areaRegistroCombate.setBackground(new Color(15, 23, 42)); // Fondo oscuro
        areaRegistroCombate.setForeground(new Color(226, 232, 240));
        areaRegistroCombate.setMargin(new Insets(8, 8, 8, 8));
        JScrollPane scrollLog = new JScrollPane(areaRegistroCombate);
        panelLog.add(scrollLog, BorderLayout.CENTER);

        JButton btnLimpiarLog = new JButton("Limpiar Registro");
        btnLimpiarLog.addActionListener(e -> areaRegistroCombate.setText(""));
        JPanel panelBotonLog = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotonLog.add(btnLimpiarLog);
        panelLog.add(panelBotonLog, BorderLayout.SOUTH);

        splitCentral.setRightComponent(panelLog);
        add(splitCentral, BorderLayout.CENTER);

        // 3. Panel Inferior: Controles y Acciones
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.Y_AXIS));
        panelInferior.setBorder(new EmptyBorder(8, 10, 10, 10));

        // Fila 1: Acción de Atacar (atacar(Personaje objetivo))
        JPanel filaAtaque = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        filaAtaque.setBorder(BorderFactory.createTitledBorder("⚔️ Acción: atacar(Personaje objetivo)"));

        comboAtacante = new JComboBox<>();
        comboObjetivo = new JComboBox<>();
        comboAtacante.setPreferredSize(new Dimension(200, 28));
        comboObjetivo.setPreferredSize(new Dimension(200, 28));

        JButton btnAtacar = new JButton("¡Ejecutar Ataque!");
        btnAtacar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAtacar.setBackground(new Color(220, 38, 38));
        btnAtacar.setForeground(Color.WHITE);
        btnAtacar.setFocusPainted(false);
        btnAtacar.addActionListener(e -> accionarAtaque());

        filaAtaque.add(new JLabel("Atacante:"));
        filaAtaque.add(comboAtacante);
        filaAtaque.add(new JLabel("Objetivo:"));
        filaAtaque.add(comboObjetivo);
        filaAtaque.add(btnAtacar);
        panelInferior.add(filaAtaque);

        // Fila 2: Acción de Recibir Daño (recibirDano(int cantidad)) y Otras Funciones
        JPanel filaOtrasAcciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        filaOtrasAcciones.setBorder(BorderFactory.createTitledBorder("🛡️ Acciones Directas y Utilidades"));

        comboDanoDirecto = new JComboBox<>();
        comboDanoDirecto.setPreferredSize(new Dimension(190, 28));
        spinnerDano = new JSpinner(new SpinnerNumberModel(20, 1, 999, 5));
        spinnerDano.setPreferredSize(new Dimension(65, 28));

        JButton btnAplicarDano = new JButton("Aplicar Daño");
        btnAplicarDano.setBackground(new Color(234, 88, 12));
        btnAplicarDano.setForeground(Color.WHITE);
        btnAplicarDano.setFocusPainted(false);
        btnAplicarDano.addActionListener(e -> accionarDanoDirecto());

        JButton btnInfo = new JButton("📜 Mostrar Info");
        btnInfo.addActionListener(e -> accionarMostrarInfo());

        JButton btnNuevo = new JButton("➕ Nuevo Personaje");
        btnNuevo.setBackground(new Color(22, 163, 74));
        btnNuevo.setForeground(Color.WHITE);
        btnNuevo.setFocusPainted(false);
        btnNuevo.addActionListener(e -> abrirDialogoNuevoPersonaje());

        JButton btnRestablecer = new JButton("🔄 Curar Todos");
        btnRestablecer.addActionListener(e -> restablecerSaludTodos());

        filaOtrasAcciones.add(new JLabel("Personaje:"));
        filaOtrasAcciones.add(comboDanoDirecto);
        filaOtrasAcciones.add(new JLabel("Daño:"));
        filaOtrasAcciones.add(spinnerDano);
        filaOtrasAcciones.add(btnAplicarDano);
        filaOtrasAcciones.add(Box.createHorizontalStrut(15));
        filaOtrasAcciones.add(btnInfo);
        filaOtrasAcciones.add(btnNuevo);
        filaOtrasAcciones.add(btnRestablecer);
        panelInferior.add(filaOtrasAcciones);

        add(panelInferior, BorderLayout.SOUTH);
    }

    /**
     * Actualiza las tarjetas visuales de los personajes y los ComboBoxes.
     */
    private void actualizarTodo() {
        panelTarjetasPersonajes.removeAll();

        for (Personaje p : personajes) {
            JPanel tarjeta = crearTarjetaPersonaje(p);
            panelTarjetasPersonajes.add(tarjeta);
            panelTarjetasPersonajes.add(Box.createRigidArea(new Dimension(0, 8)));
        }

        panelTarjetasPersonajes.revalidate();
        panelTarjetasPersonajes.repaint();

        actualizarComboBox(comboAtacante);
        actualizarComboBox(comboObjetivo);
        actualizarComboBox(comboDanoDirecto);

        if (comboObjetivo.getItemCount() > 1 && comboAtacante.getSelectedIndex() == 0) {
            comboObjetivo.setSelectedIndex(comboObjetivo.getItemCount() - 1);
        }
    }

    private void actualizarComboBox(JComboBox<Personaje> combo) {
        Personaje seleccionado = (Personaje) combo.getSelectedItem();
        combo.removeAllItems();
        for (Personaje p : personajes) {
            combo.addItem(p);
        }
        if (seleccionado != null && personajes.contains(seleccionado)) {
            combo.setSelectedItem(seleccionado);
        }
    }

    /**
     * Construye un componente visual tipo tarjeta para cada personaje.
     */
    private JPanel crearTarjetaPersonaje(Personaje p) {
        JPanel card = new JPanel(new BorderLayout(8, 8));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(p.estaVivo() ? new Color(148, 163, 184) : new Color(239, 68, 68), 1),
                new EmptyBorder(10, 12, 10, 12)
        ));
        card.setBackground(p.estaVivo() ? Color.WHITE : new Color(254, 242, 242));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

        // Cabecera: Nombre y Estado
        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);

        JLabel lblNombre = new JLabel((p.estaVivo() ? "🛡️ " : "💀 ") + p.getNombre());
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNombre.setForeground(p.estaVivo() ? new Color(15, 23, 42) : new Color(153, 27, 27));

        JLabel lblEstado = new JLabel(p.estaVivo() ? "🟢 En pie" : "🔴 Derrotado");
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblEstado.setForeground(p.estaVivo() ? new Color(22, 101, 52) : new Color(185, 28, 28));

        top.add(lblNombre, BorderLayout.WEST);
        top.add(lblEstado, BorderLayout.EAST);
        card.add(top, BorderLayout.NORTH);

        // Centro: Barra de vida (HP)
        JPanel centro = new JPanel(new BorderLayout(5, 5));
        centro.setOpaque(false);

        JProgressBar barraVida = new JProgressBar(0, p.getVidaMaxima());
        barraVida.setValue(p.getVida());
        barraVida.setStringPainted(true);
        barraVida.setString(p.getVida() + " / " + p.getVidaMaxima() + " HP");
        barraVida.setPreferredSize(new Dimension(200, 22));
        barraVida.setFont(new Font("Segoe UI", Font.BOLD, 11));

        double porcentaje = (double) p.getVida() / p.getVidaMaxima();
        if (porcentaje > 0.5) {
            barraVida.setForeground(new Color(34, 197, 94)); // Verde
        } else if (porcentaje > 0.2) {
            barraVida.setForeground(new Color(234, 179, 8)); // Amarillo
        } else {
            barraVida.setForeground(new Color(239, 68, 68)); // Rojo
        }

        centro.add(barraVida, BorderLayout.CENTER);
        card.add(centro, BorderLayout.CENTER);

        // Pie: Nivel y Ataque
        JPanel pie = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        pie.setOpaque(false);
        JLabel lblNivel = new JLabel("⭐ Nivel: " + p.getNivel());
        JLabel lblAtaque = new JLabel("⚔️ Ataque: " + p.getAtaque());
        lblNivel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblAtaque.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        pie.add(lblNivel);
        pie.add(lblAtaque);
        card.add(pie, BorderLayout.SOUTH);

        return card;
    }

    // =========================================================================
    // Manejo de eventos de los botones
    // =========================================================================

    private void accionarAtaque() {
        Personaje atacante = (Personaje) comboAtacante.getSelectedItem();
        Personaje objetivo = (Personaje) comboObjetivo.getSelectedItem();

        if (atacante == null || objetivo == null) {
            JOptionPane.showMessageDialog(this, "Por favor selecciona un atacante y un objetivo.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Llamada al método original atacar(Personaje objetivo)
        atacante.atacar(objetivo);

        // Refrescar tarjetas visuales
        actualizarTodo();
    }

    private void accionarDanoDirecto() {
        Personaje p = (Personaje) comboDanoDirecto.getSelectedItem();
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un personaje.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int cantidad = (int) spinnerDano.getValue();
        // Llamada al método original recibirDano(int cantidad)
        p.recibirDano(cantidad);

        // Refrescar tarjetas visuales
        actualizarTodo();
    }

    private void accionarMostrarInfo() {
        Personaje p = (Personaje) comboDanoDirecto.getSelectedItem();
        if (p == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un personaje.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Llamada al método original mostrarInformacion()
        p.mostrarInformacion();
    }

    private void restablecerSaludTodos() {
        for (Personaje p : personajes) {
            p.setVida(p.getVidaMaxima());
        }
        Personaje.log("\n[✨] Se ha restaurado la salud de todos los personajes a su valor máximo.");
        actualizarTodo();
    }

    private void abrirDialogoNuevoPersonaje() {
        JTextField txtNombre = new JTextField();
        JSpinner spinNivel = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        JSpinner spinVida = new JSpinner(new SpinnerNumberModel(100, 1, 9999, 10));
        JSpinner spinAtaque = new JSpinner(new SpinnerNumberModel(25, 0, 999, 5));

        JPanel panelForm = new JPanel(new GridLayout(4, 2, 8, 8));
        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(txtNombre);
        panelForm.add(new JLabel("Nivel:"));
        panelForm.add(spinNivel);
        panelForm.add(new JLabel("Vida inicial:"));
        panelForm.add(spinVida);
        panelForm.add(new JLabel("Puntos de Ataque:"));
        panelForm.add(spinAtaque);

        int result = JOptionPane.showConfirmDialog(this, panelForm, "Crear Nuevo Personaje",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String nombre = txtNombre.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int nivel = (int) spinNivel.getValue();
            int vida = (int) spinVida.getValue();
            int ataque = (int) spinAtaque.getValue();

            Personaje nuevo = new Personaje(nombre, nivel, vida, ataque);
            personajes.add(nuevo);
            Personaje.log("\n[➕] Nuevo personaje creado: " + nuevo.getNombre() + " (Nivel " + nivel + ", " + vida + " HP, " + ataque + " ATK)");
            actualizarTodo();
        }
    }

    public static void main(String[] args) {
        // Look & Feel nativo del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        // Ejecución en el hilo gráfico de Swing
        SwingUtilities.invokeLater(() -> {
            VideoJuegoGUI ventana = new VideoJuegoGUI();
            ventana.setVisible(true);
        });
    }
}

/**
 * Clase Personaje compatible tanto con la GUI como con la consola.
 */
class Personaje {
    private String nombre;
    private int nivel;
    private int vida;
    private int ataque;
    private int vidaMaxima;

    // Logger configurable para enviar mensajes a consola y/o GUI
    private static Consumer<String> logger = System.out::println;

    public static void setLogger(Consumer<String> nuevoLogger) {
        if (nuevoLogger != null) {
            logger = nuevoLogger;
        }
    }

    public static void log(String mensaje) {
        logger.accept(mensaje);
    }

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
            log("\n❌ [!] " + this.nombre + " no puede atacar porque ha sido derrotado.");
            return;
        }

        if (objetivo == null) {
            log("\n⚠️ [!] No hay un objetivo válido al que atacar.");
            return;
        }

        if (objetivo == this) {
            log("\n⚠️ [!] " + this.nombre + " no puede atacarse a sí mismo.");
            return;
        }

        if (!objetivo.estaVivo()) {
            log("\n⚠️ [!] " + this.nombre + " intentó atacar a " + objetivo.getNombre() + ", pero ya ha sido derrotado.");
            return;
        }

        log("\n⚔️ >> [ATAQUE] " + this.nombre + " (Nivel " + this.nivel + ") ataca ferozmente a " + objetivo.getNombre() + "!");
        
        // Aplica el daño al objetivo
        objetivo.recibirDano(this.ataque);
    }

    // Método recibirDano: descuenta vida y muestra detalles
    public void recibirDano(int cantidad) {
        if (cantidad < 0) {
            cantidad = 0;
        }

        int vidaAnterior = this.vida;
        this.vida = Math.max(0, this.vida - cantidad);
        int danoRecibido = vidaAnterior - this.vida;

        log("   🛡️ -> " + this.nombre + " recibe " + danoRecibido + " puntos de daño.");
        log("   ❤️ -> Vida restante de " + this.nombre + ": " + this.vida + "/" + this.vidaMaxima + " HP.");

        if (this.vida == 0) {
            log("   💀 -> [DERROTADO] ¡" + this.nombre + " ha caído en combate!");
        }
    }

    // Método mostrarInformacion: despliega la ficha con estadísticas
    public void mostrarInformacion() {
        String estado = estaVivo() ? "Activo (En pie)" : "Incapacitado (Derrotado)";
        log("\n+--------------------------------------------------+");
        log(String.format("| %-48s |", "PERSONAJE: " + this.nombre));
        log("+--------------------------------------------------+");
        log(String.format("| Nivel:         %-33d |", this.nivel));
        log(String.format("| Vida:          %-33s |", this.vida + " / " + this.vidaMaxima + " HP"));
        log(String.format("| Ataque:        %-33d |", this.ataque));
        log(String.format("| Estado:        %-33s |", estado));
        log("+--------------------------------------------------+");
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    @Override
    public String toString() {
        return this.nombre + " (" + this.vida + "/" + this.vidaMaxima + " HP)";
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