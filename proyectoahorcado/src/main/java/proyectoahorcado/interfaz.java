package proyectoahorcado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Bogdan Adrián Tucaciuc
 */
public class Interfaz extends javax.swing.JFrame {

    // Método para actualizar la interfaz según el estado del juego
    private void actualizarInterfaz() {
        palabraSecreta.setText(ahorcado.getProgresoPalabra()); // Actualiza el progreso de la palabra
        letrasUsadas.setText("Letras usadas: " + ahorcado.getLetrasUsadas().toString()); // Actualiza las letras usadas
        imagen.setIcon(new ImageIcon("resources/ahorcado" + (6 - ahorcado.getIntentos()) + ".png")); // Actualiza la imagen del ahorcado
    }

    private Logica ahorcado;
    final private Font fuente = new Font("Segoe print", Font.BOLD, 18);
    private JLabel imagen;
    private JTextArea palabraSecreta, letrasUsadas, mensajeFinal;
    private JTextField ingresarLetra;

    public void iniciar() {
        setTitle("Ahorcado");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // Cambiar el layout principal a BorderLayout

        // Se pide la palabra oculta
        JPasswordField palabraOculta = new JPasswordField();
        JOptionPane.showConfirmDialog(null, palabraOculta, "Ingresa la palabra a adivinar:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        String palabra = new String(palabraOculta.getPassword());
        ahorcado = new Logica(palabra);

        // Panel para los campos de texto y botones (North)
        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new GridLayout(2, 2, 10, 10)); // GridLayout con 2 filas y 2 columnas
        palabraSecreta = new JTextArea(ahorcado.getProgresoPalabra());
        palabraSecreta.setFont(fuente);
        palabraSecreta.setEditable(false);
        palabraSecreta.setAlignmentX(CENTER_ALIGNMENT);

        letrasUsadas = new JTextArea("Letras usadas:");
        letrasUsadas.setFont(fuente);
        letrasUsadas.setEditable(false);
        letrasUsadas.setAlignmentX(CENTER_ALIGNMENT);

        ingresarLetra = new JTextField();
        ingresarLetra.setFont(fuente);
        ingresarLetra.setAlignmentX(CENTER_ALIGNMENT);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setFont(fuente);
        btnIngresar.setAlignmentX(CENTER_ALIGNMENT);

        panelCampos.add(palabraSecreta);
        panelCampos.add(letrasUsadas);
        panelCampos.add(ingresarLetra);
        panelCampos.add(btnIngresar);

        // Panel para el label de imagen (Center)
        JPanel panelImagen = new JPanel();
        imagen = new JLabel(new ImageIcon("resources/ahorcado0.png"));
        panelImagen.add(imagen);

        // Panel para el mensaje final (South)
        JPanel panelMensaje = new JPanel();
        mensajeFinal = new JTextArea();
        mensajeFinal.setFont(fuente);
        mensajeFinal.setEditable(false);
        mensajeFinal.setAlignmentX(CENTER_ALIGNMENT);
        panelMensaje.add(mensajeFinal);

        // Panel para el botón reiniciar (South)
        JButton btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.setFont(fuente);
        panelMensaje.add(btnReiniciar);

        // Acción del botón Ingresar
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = ingresarLetra.getText().toLowerCase(); // Obtener la letra ingresada y convertirla a minúscula
                if (texto.length() == 1) { // Verificar que se haya ingresado una sola letra
                    char letra = texto.charAt(0); // Obtener la letra
                    boolean acierto = ahorcado.probarLetra(letra); // Verificar la letra en la lógica
                    actualizarInterfaz(); // Actualizar la interfaz

                    if (ahorcado.partidaTerminada()) { // Verificar si el juego ha terminado
                        if (ahorcado.victoria()) { // Verificar si se ha ganado
                            mensajeFinal.setText("Felicidades, has ganado...");
                        } else { // Si no se ha ganado, es que se ha perdido
                            mensajeFinal.setText("Jodete, la palabra era: " + ahorcado.getPalabra() + " ¡Ja!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresa una sola letra."); // Mostrar mensaje de error
                }
                ingresarLetra.setText(""); // Limpiar el campo de texto
            }
        });

        // Acción del botón Reiniciar
        btnReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPasswordField palabraOculta = new JPasswordField(); // Campo para ingresar nueva palabra
                int opcion = JOptionPane.showConfirmDialog(null, palabraOculta, "Ingresa la palabra a adivinar:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (opcion == JOptionPane.OK_OPTION) {
                    String palabra = new String(palabraOculta.getPassword()); // Obtener la nueva palabra
                    ahorcado.reiniciar(palabra); // Reiniciar la lógica
                    actualizarInterfaz(); // Actualizar la interfaz
                    mensajeFinal.setText(""); // Limpiar mensaje final
                }
            }
        });

        // Añadir los paneles a la ventana
        add(panelCampos, BorderLayout.NORTH);
        add(panelImagen, BorderLayout.CENTER);
        add(panelMensaje, BorderLayout.SOUTH);

        setVisible(true); // Hacer visible la ventana
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Interfaz().iniciar(); // Iniciar la interfaz
            }
        });
    }
}