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

        //le ponemos acciones a los botones
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = ingresarLetra.getText().toLowerCase(); // Obtiene la letra ingresada y la convierte a minúscula
                if (texto.length() == 1) { // Verifica que se haya ingresado solo una letra
                    char letra = texto.charAt(0); // Obtiene el primer carácter del texto
                    boolean acierto = ahorcado.probarLetra(letra); // Prueba la letra en la lógica
                    actualizarInterfaz(); // Actualiza la interfaz con el nuevo estado del juego
                    if (ahorcado.partidaTerminada()) { // Verifica si el juego ha terminado
                        if (ahorcado.victoria()) { // Verifica si hay victoria
                            mensajeFinal.setText("Felicidades, has ganado...");
                        } else { // Si no, el jugador ha perdido
                            mensajeFinal.setText("Jodete, la palabra era: " + ahorcado.getProgresoPalabra()+ " ¡Ja!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresa una sola letra."); // Muestra un mensaje de error
                }
                ingresarLetra.setText(""); // Limpia el campo de texto
            }
        });
        
    }
}
