package Control;

import Modelo.Tetrismodel;
import Vista.TetrisView;
import javax.swing.JFrame;


/**
 *
 * @author danteeh
 */
public class TetrisGame {
    
    public static void main(String[] args) {
        // Crear el modelo, la vista y el controlador
        Tetrismodel model = new Tetrismodel();
        TetrisView view = new TetrisView(model);
        TetrisController controller = new TetrisController(model, view);

        // Configurar la ventana principal
        JFrame frame = new JFrame("Tetris con Cuadritos de Colores");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Agregar el controlador como KeyListener
        frame.addKeyListener(controller);
        view.setFocusable(true);
    }
    
}
