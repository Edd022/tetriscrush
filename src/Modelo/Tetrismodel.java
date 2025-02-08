package Modelo;

import java.awt.Color;

public class Tetrismodel {
    private Color[][] board;      // Tablero del juego (filas x columnas)
    private Piece currentPiece;   // Pieza que se encuentra cayendo
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 18;
    private boolean gameOver = false;
    
    public Tetrismodel() {
        board = new Color[BOARD_HEIGHT][BOARD_WIDTH];
        currentPiece = PieceFactory.createPiece();
    }
    
    // Modificación importante: Permitir posiciones con Y negativa (zona de spawn)
    public boolean isValidPosition(Piece piece, int dx, int dy) {
        for (Block b : piece.getBlocks()) {
            int newX = b.getX() + dx;
            int newY = b.getY() + dy;
            // Verificar límites horizontales.
            if (newX < 0 || newX >= BOARD_WIDTH) return false;
            // Si la nueva Y es mayor o igual que la altura del tablero, es inválida.
            if (newY >= BOARD_HEIGHT) return false;
            // Si newY es menor que 0 (arriba del tablero) se permite,
            // pero si está dentro del tablero se verifica la colisión.
            if (newY >= 0 && board[newY][newX] != null) return false;
        }
        return true;
    }
    
    // Mueve la pieza actual; si no se puede mover hacia abajo, se bloquea y se genera una nueva pieza.
    public void movePiece(int dx, int dy) {
        if (gameOver) return;
        
        if (isValidPosition(currentPiece, dx, dy)) {
            currentPiece.move(dx, dy);
        } else {
            // Si se intenta mover hacia abajo y no es posible, bloqueamos la pieza.
            if (dy == 1) {
                lockPiece();
                clearLines();
                // Si no se ha terminado el juego, se genera una nueva pieza.
                if (!gameOver) {
                    currentPiece = PieceFactory.createPiece();
                    // Si la nueva pieza no puede ubicarse (por ejemplo, colisiona en el área visible), se termina el juego.
                    if (!isValidPosition(currentPiece, 0, 0)) {
                        gameOver = true;
                    }
                }
            }
            // Los movimientos horizontales inválidos se ignoran.
        }
    }
    
    // Rota la pieza actual; si la rotación genera colisión o sale de los límites, se revierte.
    public void rotatePiece() {
        if (gameOver) return;
        
        // Guardar las posiciones actuales de la pieza.
        java.util.List<int[]> oldPositions = new java.util.ArrayList<>();
        for (Block b : currentPiece.getBlocks()) {
            oldPositions.add(new int[]{b.getX(), b.getY()});
        }
        int oldRotationState = currentPiece.getRotationState();
        currentPiece.rotate();
        if (!isValidPosition(currentPiece, 0, 0)) {
            // Revertir la rotación.
            java.util.List<Block> blocks = currentPiece.getBlocks();
            for (int i = 0; i < blocks.size(); i++) {
                blocks.get(i).setX(oldPositions.get(i)[0]);
                blocks.get(i).setY(oldPositions.get(i)[1]);
            }
            currentPiece.setRotationState(oldRotationState);
        }
    }
    
    // "Bloquea" la pieza actual en el tablero, asignando a cada celda el color del bloque.
    // Si algún bloque que se va a fijar tiene Y negativa (es decir, aún está fuera del área visible),
    // se dispara el game over.
    private void lockPiece() {
        for (Block b : currentPiece.getBlocks()) {
            if (b.getY() < 0) {
                gameOver = true;
                return;
            }
            int x = b.getX();
            int y = b.getY();
            if (y >= 0 && y < BOARD_HEIGHT && x >= 0 && x < BOARD_WIDTH) {
                board[y][x] = b.getColor();
            }
        }
    }
    
    // Verifica cada fila del tablero y, si alguna está completa, la elimina y baja las filas superiores.
    private void clearLines() {
        for (int row = 0; row < BOARD_HEIGHT; row++) {
            boolean full = true;
            for (int col = 0; col < BOARD_WIDTH; col++) {
                if (board[row][col] == null) {
                    full = false;
                    break;
                }
            }
            if (full) {
                // Mover las filas superiores hacia abajo.
                for (int r = row; r > 0; r--) {
                    for (int col = 0; col < BOARD_WIDTH; col++) {
                        board[r][col] = board[r - 1][col];
                    }
                }
                // Limpiar la fila superior.
                for (int col = 0; col < BOARD_WIDTH; col++) {
                    board[0][col] = null;
                }
            }
        }
    }
    
    public Color[][] getBoard() {
        return board;
    }
    
    public Piece getCurrentPiece() {
        return currentPiece;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
}
