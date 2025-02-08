package Modelo;

import java.awt.Color;
import java.util.Random;

public class PieceFactory {
    private static final Color[] COLORS = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE};
    private static final Random random = new Random();
    // Para centrar la pieza en un tablero de 10 columnas, se desplaza 5 columnas.
    private static final int CENTER_OFFSET = 5;
    // Desplazamiento vertical para que la pieza se genere fuera del tablero (por encima)
    // De modo que la pieza inicie con algunas casillas con y negativo y vaya bajando.
    private static final int VERTICAL_OFFSET = -2;
    
    // Crea una pieza aleatoria entre I, L, C, T y S.
    public static Piece createPiece() {
        int typeIndex = random.nextInt(5); // 0 a 4
        switch (typeIndex) {
            case 0:
                return createIPiece();
            case 1:
                return createLPiece();
            case 2:
                return createCPiece();
            case 3:
                return createTPiece();
            case 4:
                return createSPiece();
            default:
                return createIPiece();
        }
    }
    
    // Ficha I
    public static Piece createIPiece() {
        Piece piece = new Piece("I");
        int[] offsets = {-2, -1, 0, 1};
        for (int i = 0; i < 4; i++) {
            Color color = COLORS[random.nextInt(COLORS.length)];
            piece.addBlock(new Block(color, offsets[i], 0));
        }
        // Centramos horizontalmente y la generamos fuera (por encima) del tablero.
        piece.move(CENTER_OFFSET, VERTICAL_OFFSET);
        return piece;
    }
    
    // Ficha L
    public static Piece createLPiece() {
        Piece piece = new Piece("L");
        int[][] offsets = { {0, -1}, {0, 0}, {0, 1}, {1, 1} };
        for (int i = 0; i < 4; i++) {
            Color color = COLORS[random.nextInt(COLORS.length)];
            piece.addBlock(new Block(color, offsets[i][0], offsets[i][1]));
        }
        piece.move(CENTER_OFFSET, VERTICAL_OFFSET);
        return piece;
    }
    
    // Ficha C (cuadrado 2x2)
    public static Piece createCPiece() {
        Piece piece = new Piece("C");
        int[][] offsets = { {0, 0}, {1, 0}, {0, 1}, {1, 1} };
        for (int i = 0; i < 4; i++) {
            Color color = COLORS[random.nextInt(COLORS.length)];
            piece.addBlock(new Block(color, offsets[i][0], offsets[i][1]));
        }
        piece.move(CENTER_OFFSET, VERTICAL_OFFSET);
        return piece;
    }
    
    // Ficha T
    public static Piece createTPiece() {
        Piece piece = new Piece("T");
        int[][] offsets = { {0, -1}, {0, 0}, {0, 1}, {1, 0} };
        for (int i = 0; i < 4; i++) {
            Color color = COLORS[random.nextInt(COLORS.length)];
            piece.addBlock(new Block(color, offsets[i][0], offsets[i][1]));
        }
        piece.move(CENTER_OFFSET, VERTICAL_OFFSET);
        return piece;
    }
    
    // Ficha S
    public static Piece createSPiece() {
        Piece piece = new Piece("S");
        int[][] offsets = { {0, 0}, {1, 0}, {0, 1}, {-1, 1} };
        for (int i = 0; i < 4; i++) {
            Color color = COLORS[random.nextInt(COLORS.length)];
            piece.addBlock(new Block(color, offsets[i][0], offsets[i][1]));
        }
        piece.move(CENTER_OFFSET, VERTICAL_OFFSET);
        return piece;
    }
}
