package Modelo;
/*
 * @author danteeh
 */
import java.util.ArrayList;
import java.util.List;

public class Tetrismodel {
    private int[][] board; // Tablero de juego
    private Piece currentPiece; // Pieza actual
    public Tetrismodel() {
        board = new int[18][10]; // Tablero de 20 filas y 10 columnas
        currentPiece = PieceFactory.createPiece(); // Crear una pieza inicial
    }

    // Método para mover la pieza
    public void movePiece(int deltaX, int deltaY) {
        currentPiece.move(deltaX, deltaY);
    }

    // Método para rotar la pieza
    public void rotatePiece() {
        currentPiece.rotate();
    }

    // Método para actualizar el tablero agreguelo alguno
    public void updateBoard() {
        // Lógica para actualizar el tablero (colisiones, líneas completas, etc.)
    }

    // Getters para la vista
    public int[][] getBoard() {
        return board;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }
}