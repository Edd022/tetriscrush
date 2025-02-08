package Control;

import Modelo.Tetrismodel;
import Vista.TetrisView;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

public class TetrisGame {
    public static void main(String[] args) {
        // Crear el modelo y la vista.
        Tetrismodel model = new Tetrismodel();
        TetrisView view = new TetrisView(model);
        
        // Configurar la ventana principal.
        JFrame frame = new JFrame("Tetris con Cuadritos de Colores");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // Configurar key bindings para movimientos sin delay:
        // Mover a la izquierda.
        view.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        view.getActionMap().put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.movePiece(-1, 0);
                view.repaint();
            }
        });
        // Mover a la derecha.
        view.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        view.getActionMap().put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.movePiece(1, 0);
                view.repaint();
            }
        });
        // Mover hacia abajo manualmente.
        view.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        view.getActionMap().put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.movePiece(0, 1);
                view.repaint();
            }
        });
        // Rotar la pieza.
        view.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "rotate");
        view.getActionMap().put("rotate", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.rotatePiece();
                view.repaint();
            }
        });
        
        // Timer para simular la caída automática de la pieza cada 500 ms.
        Timer timer = new Timer(500, (ActionEvent e) -> {
            if (!model.isGameOver()) {
                model.movePiece(0, 1);
                view.repaint();
            } else {
                // Si se alcanzó el game over, se muestra el mensaje y se detiene el Timer.
                ((Timer)e.getSource()).stop();
                JOptionPane.showMessageDialog(frame, "¡Perdiste!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        timer.start();
    }
}
