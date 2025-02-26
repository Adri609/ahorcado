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
        imagen.setIcon(new ImageIcon("resources/ahorcado" + (0 + ahorcado.getIntentos()) + ".png")); // Actualiza la imagen del ahorcado
    }

    private Logica ahorcado;
    final private Font fuente = new Font("Segoe print", Font.BOLD, 18);
    private JLabel imagen;
    private JTextArea palabraSecreta, letrasUsadas, mensajeFinal;
    private JTextField ingresarLetra;

    public void iniciar(){
        setTitle("Ahorcado");
        setSize(500,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6,1));

        //Se pide la palabra oculta
        JPasswordField palabraOculta = new JPasswordField();
        JOptionPane.showConfirmDialog(null, palabraOculta, "ingresa la palabra a adivinar:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        String palabra = new String(palabraOculta.getPassword());
        ahorcado = new Logica(palabra);
        
        //Inicio todos los componentes gráficos (botones, etc...)
        imagen = new JLabel(new ImageIcon("resources/ahorcado0.png"));
        palabraSecreta = new JTextArea(ahorcado.getProgresoPalabra()); //Muestra el progreso inicial que sería la palabra ocultada
        palabraSecreta.setFont(fuente); //Le ponemos la fuente personalizada
        palabraSecreta.setEditable(false); //Configuramos el áread e texto para que no sea editable
        palabraSecreta.setAlignmentX(CENTER_ALIGNMENT); //Centramos el texto

        letrasUsadas = new JTextArea("Letras usadas:"); //Se muestran las letras utilizadas
        letrasUsadas.setFont(fuente);
        letrasUsadas.setEditable(false);
        letrasUsadas.setAlignmentX(CENTER_ALIGNMENT);

        mensajeFinal = new JTextArea(); //muestra mensaje de victoria o derrota
        mensajeFinal.setFont(fuente);
        mensajeFinal.setEditable(false);
        mensajeFinal.setAlignmentX(CENTER_ALIGNMENT);

        ingresarLetra = new JTextField(); //Campo donde se meten las letras
        ingresarLetra.setFont(fuente);
        ingresarLetra.setAlignmentX(CENTER_ALIGNMENT);

        JButton btnIngresar = new JButton("Ingresar"); //Botón para validar la palabra igresada
        btnIngresar.setFont(fuente);
        btnIngresar.setAlignmentX(CENTER_ALIGNMENT);

        JButton btnReiniciar = new JButton("Reiniciar"); //Botón para reiniciar la partida
        btnIngresar.setFont(fuente);
        btnIngresar.setAlignmentX(CENTER_ALIGNMENT);

        //Iniciamos la acción del botón Ingresar que validará las letras ingresadas
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = ingresarLetra.getText().toLowerCase(); //Obtenemos la letra ingreada y la pasamos a minúscula
                if (texto.length() == 1) { // Verifica que se haya ingresado una sola letra
                    char letra = texto.charAt(0); //Saca el primer carácter del texto
                    boolean acierto = ahorcado.probarLetra(letra); //Prueba la letra en la lógica
                    actualizarInterfaz(); //Se actualiza la interfaz con el estado actual del juego
                    if (ahorcado.partidaTerminada()) { //Verificamos si el juego ha terminado
                        if (ahorcado.victoria()) { //Verifica si hay victoria
                            mensajeFinal.setText("Felicidades, has ganado..."); //Si hay victoria mostramos el mensaje
                        }else { //si no ha ganado pues es que ha perdido...
                            mensajeFinal.setText("Jodete, la palabra era: " + ahorcado.getProgresoPalabra()+ " ¡Ja!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresa una sola letra."); // Muestra un mensaje de error
                }
                ingresarLetra.setText(""); // Limpia el campo de texto
            }
        });

        //Iniciamos la acción del botón Reiniciar para reiniciar el juego
        btnReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPasswordField palabraOculta = new JPasswordField(); //Campo para ingresar una nueva palabra
                int opcion = JOptionPane.showConfirmDialog(null, palabraOculta, "Ingresa la palabra a adivinar:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (opcion == JOptionPane.OK_OPTION) {
                    String palabra = new String(palabraOculta.getPassword()); //Obtenemos la nueva palabra
                    ahorcado.reiniciar(palabra);
                    actualizarInterfaz(); //Actualizamos la interfaz
                    mensajeFinal.setText(""); //Limpiamos el mensaje final
                }
            }
        });

        //Añadimos los componente creados anteriormente a la ventana:
        add(imagen);
        add(palabraSecreta);
        add(letrasUsadas);
        add(mensajeFinal);
        add(ingresarLetra);
        add(btnIngresar);
        add(btnReiniciar);

        setVisible(true); //Hacemos la ventana visible
    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Interfaz().iniciar(); // Inicia la interfaz
            }
        });
    }
}