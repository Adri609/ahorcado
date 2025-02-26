package proyectoahorcado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Bogdan Adrián Tucaciuc
 */
public class interfaz extends javax.swing.JFrame {

    final private Font fuente = new Font("Segoe print", Font.BOLD, 18);
    JButton ingresar, reiniciar;
    private JLabel dibujo;
    private JTextArea palabraSecreta, letrasUsadas, mensajeFinal;
    private JTextPane ingresarLetra;

    public void iniciar(){
        setTitle("Ahorcado");
        setSize(500,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5,1));

        //Se pide la palabra oculta
        JPasswordField palabraOculta = new JPasswordField();
        JOptionPane.showConfirmDialog(null, palabraOculta, "ingresa la palabra a adivinar:", JOptionPane.OK_CANCEL_OPTION);
        


    }
}
