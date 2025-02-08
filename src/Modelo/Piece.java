package Modelo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Piece {
    private List<Block> blocks;
    private int rotationState; // Estados: 0, 1, 2, 3
    private int pivotIndex;    // Índice del bloque pivote (el que se usa como referencia para la rotación)
    private String type;       // "I", "L", "C", "T" o "S"
    
    public Piece(String type) {
        blocks = new ArrayList<>();
        rotationState = 0;
        this.type = type;
        // Se asigna el pivote según el tipo:
        if (type.equals("I")) {
            pivotIndex = 2;
        } else if (type.equals("L")) {
            pivotIndex = 1;
        } else if (type.equals("C")) {
            pivotIndex = 0; // La esquina superior izquierda es el pivote
        } else if (type.equals("T")) {
            pivotIndex = 1; // El centro (segunda de tres verticales)
        } else if (type.equals("S")) {
            pivotIndex = 0; // El pivote es la primera casilla de la parte horizontal
        }
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
    
    // Métodos para obtener y modificar el estado de rotación
    public int getRotationState() {
        return rotationState;
    }
    
    public void setRotationState(int state) {
        this.rotationState = state;
    }
    
    public void rotate() {
        // Incrementamos el estado de rotación (se rota hacia la izquierda)
        rotationState = (rotationState + 1) % 4;
        
        if (type.equals("C")) {
            // Para la ficha C no se mueven las posiciones; se rotan los colores en sentido antihorario.
            // Su forma es un cuadrado 2x2 con bloques en:
            //  block0: (0,0) [pivote], block1: (1,0), block2: (0,1), block3: (1,1)
            // Se define la permutación: 
            //    color de block0 → block2, 
            //    block2 → block3, 
            //    block3 → block1, 
            //    block1 → block0.
            Color temp = blocks.get(0).getColor();
            blocks.get(0).setColor(blocks.get(1).getColor());
            blocks.get(1).setColor(blocks.get(3).getColor());
            blocks.get(3).setColor(blocks.get(2).getColor());
            blocks.get(2).setColor(temp);
        } else {
            // Para las otras fichas se reubican las posiciones de los bloques
            Block pivot = blocks.get(pivotIndex);
            int pivotX = pivot.getX();
            int pivotY = pivot.getY();
            
            int[][] offsets = new int[4][2];
            
            if (type.equals("I")) {
                // Ficha I
                switch (rotationState) {
                    case 0:
                        offsets = new int[][]{{-2, 0}, {-1, 0}, {0, 0}, {1, 0}};
                        break;
                    case 1:
                        offsets = new int[][]{{0, -2}, {0, -1}, {0, 0}, {0, 1}};
                        break;
                    case 2:
                        offsets = new int[][]{{-2, 0}, {-1, 0}, {0, 0}, {1, 0}};
                        break;
                    case 3:
                        offsets = new int[][]{{0, 1}, {0, 2}, {0, 0}, {0, -1}};
                        break;
                }
            } else if (type.equals("L")) {
                // Ficha L
                switch (rotationState) {
                    case 0:
                        // Estado 0: 3 casillas verticales y 1 a la derecha del bloque inferior.
                        offsets = new int[][] { {0, -1}, {0, 0}, {0, 1}, {1, 1} };
                        break;
                    case 1:
                        // Estado 1: bloque a la izquierda y a la derecha del pivote, y uno arriba del derecho.
                        offsets = new int[][] { {-1, 0}, {0, 0}, {1, 0}, {1, -1} };
                        break;
                    case 2:
                        // Estado 2: bloque arriba y abajo del pivote, y uno a la izquierda del bloque superior.
                        offsets = new int[][] { {-1, -1}, {0, 0}, {0, -1}, {0, 1} };
                        break;
                    case 3:
                        // Estado 3: bloque a la izquierda y a la derecha del pivote, y uno debajo del izquierdo.
                        offsets = new int[][] { {-1, 0}, {0, 0}, {1, 0}, {-1, 1} };
                        break;
                }
            } else if (type.equals("T")) {
                // Ficha T
                switch (rotationState) {
                    case 0:
                        // Estado 0: tres casillas verticales y una a la derecha del centro.
                        offsets = new int[][] { {0, -1}, {0, 0}, {0, 1}, {1, 0} };
                        break;
                    case 1:
                        // Estado 1: bloque a la izquierda y derecha del pivote, y uno arriba.
                        offsets = new int[][] { {-1, 0}, {0, 0}, {1, 0}, {0, -1} };
                        break;
                    case 2:
                        // Estado 2: bloque arriba y abajo del pivote, y uno a la izquierda.
                        offsets = new int[][] { {0, -1}, {0, 0}, {0, 1}, {-1, 0} };
                        break;
                    case 3:
                        // Estado 3: bloque a la izquierda y derecha del pivote, y uno debajo.
                        offsets = new int[][] { {-1, 0}, {0, 0}, {1, 0}, {0, 1} };
                        break;
                }
            } else if (type.equals("S")) {
                // Ficha S
                switch (rotationState) {
                    case 0:
                        // Estado 0: dos casillas horizontales (con el pivote en la primera),
                        // y debajo del pivote y a la izquierda de ésta.
                        offsets = new int[][] { {0, 0}, {1, 0}, {0, 1}, {-1, 1} };
                        break;
                    case 1:
                        // Estado 1: una casilla encima del pivote, una a la derecha y otra debajo de la de la derecha.
                        offsets = new int[][] { {0, 0}, {0, -1}, {1, 0}, {1, 1} };
                        break;
                    case 2:
                        // Estado 2: a la derecha del pivote, una debajo de esa y otra a la derecha de la última.
                        offsets = new int[][] { {0, 0}, {1, 0}, {1, 1}, {2, 1} };
                        break;
                    case 3:
                        // Estado 3: debajo del pivote, una a la derecha y otra encima de esta última.
                        offsets = new int[][] { {0, 0}, {0, 1}, {1, 0}, {1, -1} };
                        break;
                }
            }
            
            // Actualizamos la posición de cada bloque usando el pivote y el offset correspondiente.
            for (int i = 0; i < blocks.size(); i++) {
                Block b = blocks.get(i);
                b.setX(pivotX + offsets[i][0]);
                b.setY(pivotY + offsets[i][1]);
            }
        }
    }
    
    public String getType() {
        return type;
    }
}
