package Control;
/*
 * @author danteeh
 */
import Modelo.Tetrismodel;
import Vista.TetrisView;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisController implements KeyListener {
    private Tetrismodel model;
    private TetrisView view;

    public TetrisController(Tetrismodel model, TetrisView view) {//inyeccion de dependencias
        this.model = model;
        this.view = view;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                model.movePiece(-1, 0); // Mover a la izquierda
                break;
            case KeyEvent.VK_RIGHT:
                model.movePiece(1, 0); // Mover a la derecha
                break;
            case KeyEvent.VK_DOWN:
                model.movePiece(0, 1); // Mover hacia abajo
                break;
            case KeyEvent.VK_UP:
                model.rotatePiece(); // Rotar la pieza
                break;
        }
        view.repaint(); // Actualizar la vista
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
