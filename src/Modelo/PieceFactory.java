package Modelo;
/*
 * @author danteeh
 */

import java.awt.Color;
import java.util.Random;

public class PieceFactory {
    private static final Color[] COLORS = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE};
    private static final Random random = new Random();

    public static Piece createPiece() {
        Piece piece = new Piece();
        // Crear una pieza con 4 cuadritos (como en Tetris clásico)
        for (int i = 0; i < 4; i++) {
            Color color = COLORS[random.nextInt(COLORS.length)];
            piece.addBlock(new Block(color, i, 0)); // Posición inicial
        }
        return piece;
    }
}