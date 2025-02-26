package proyectoahorcado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Bogdan Adrián Tucaciuc
 */
public class Interfaz extends javax.swing.JFrame {

    private Logica ahorcado;
    final private Font fuente = new Font("Segoe print", Font.BOLD, 18);
    private JLabel imagen;
    private JTextArea palabraSecreta, letrasUsadas, mensajeFinal;
    private JTextPane ingresarLetra;

    public void iniciar(){
        setTitle("Ahorcado");
        setSize(500,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5,1));

        //Se pide la palabra oculta
        JPasswordField palabraOculta = new JPasswordField();
        JOptionPane.showConfirmDialog(null, palabraOculta, "ingresa la palabra a adivinar:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        String palabra = new String(palabraOculta.getPassword());
        ahorcado = new Logica(palabra);
        
        //Inicio todos los componentes gráficos (botones, etc...)
        imagen = new JLabel(new ImageIcon("resources/ahorcado0.png"));
        palabraSecreta = new JTextArea(ahorcado.getProgresoPalabra()); //Muestra el progreso inicial que sería la palabra ocultada
        letrasUsadas = new JTextArea("Letras usadas:"); //Se muestran las letras utilizadas
        mensajeFinal = new JTextArea(); //muestra mensaje de victoria o derrota
        ingresarLetra = new JTextPane(); //Campo donde se meten las letras
        JButton btnIngresar = new JButton("Ingresar"); //Botón para validar la palabra igresada
        JButton btnReiniciar = new JButton("Reiniciar"); //Botón para reiniciar la partida
        
        //le ponemos acciones a los botones
        btnIngresar.addActionListener(e -> );
        
    }
}
