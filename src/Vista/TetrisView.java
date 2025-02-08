package Vista;

import Modelo.Block;
import Modelo.Piece;
import Modelo.Tetrismodel;
import javax.swing.*;
import java.awt.*;

public class TetrisView extends JPanel {
    private Tetrismodel model;
    
    public TetrisView(Tetrismodel model) {
        this.model = model;
        setPreferredSize(new Dimension(300, 540)); // 10 columnas x 30 px y 18 filas x 30 px
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawPiece(g);
    }
    
    // Dibuja los bloques fijos del tablero.
    private void drawBoard(Graphics g) {
        Color[][] board = model.getBoard();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != null) {
                    g.setColor(board[row][col]);
                    g.fillRect(col * 30, row * 30, 30, 30);
                    g.setColor(Color.BLACK);
                    g.drawRect(col * 30, row * 30, 30, 30);
                }
            }
        }
    }
    
    // Dibuja la pieza que está cayendo.
    private void drawPiece(Graphics g) {
        Piece piece = model.getCurrentPiece();
        for (Block block : piece.getBlocks()) {
            int x = block.getX() * 30;
            int y = block.getY() * 30;
            g.setColor(block.getColor());
            g.fillRect(x, y, 30, 30);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, 30, 30);
        }
    }
}
