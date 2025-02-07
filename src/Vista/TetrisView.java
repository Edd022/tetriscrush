package Vista;
/*
 * @author danteeh
 */
import Modelo.Block;
import Modelo.Piece;
import Modelo.Tetrismodel;
import javax.swing.*;
import java.awt.*;

public class TetrisView extends JPanel {
    private Tetrismodel model;

    public TetrisView(Tetrismodel model) {
        this.model = model;
        setPreferredSize(new Dimension(300, 600)); // Tamaño del panel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawPiece(g);
    }

    private void drawBoard(Graphics g) {
        int[][] board = model.getBoard();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != 0) {
                    g.setColor(Color.BLUE);
                    g.fillRect(col * 30, row * 30, 30, 30); // Dibujar un bloque
                }
            }
        }
    }

    private void drawPiece(Graphics g) {
        Piece piece = model.getCurrentPiece();
        for (Block block : piece.getBlocks()) {
            g.setColor(block.getColor());
            g.fillRect(block.getX() * 30, block.getY() * 30, 30, 30); // Dibujar cada cuadrito
        }
    }
}