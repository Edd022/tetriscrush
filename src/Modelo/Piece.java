package Modelo;
/*
 * @author danteeh
 */
import java.util.ArrayList;
import java.util.List;


//Patron composite para tratar todos los bloques como una pieza
public class Piece {
    private List<Block> blocks;

    public Piece() {
        blocks = new ArrayList<>();
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void move(int deltaX, int deltaY) {
        for (Block block : blocks) {
            block.setX(block.getX() + deltaX);
            block.setY(block.getY() + deltaY);
        }
    }

    public void rotate() {
        // Lógica de rotación (Agregar alguno)
    }
}