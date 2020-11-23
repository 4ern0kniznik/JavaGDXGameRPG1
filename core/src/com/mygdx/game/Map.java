package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Map {
    public static final int CELLS_X = 16;
    public static final int CELLS_Y = 9;
    public static final int CELLS_SIZE = 80;

    private Texture textureGrass;
    private Texture textureWall;
    private byte [][] data;

    public Map() {
       data = new byte[CELLS_X][CELLS_Y];
       data [2][4] = 1; /// 0- проходимая ячейка, 1 - не проходимая ячейка///

        textureGrass = new Texture("grass.png");
        textureWall = new Texture("wall.png");
    }

    public boolean isCellPassable (Vector2 position){
        return data [(int) (position.x / CELLS_SIZE)][(int) (position.y / CELLS_SIZE)] == 0;
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < 16; i ++){
            for (int j = 0; j < 9; j++){
                batch.draw(textureGrass, i * 80, j * 80);
                if (data [i][j]  == 1) {
                    batch.draw(textureWall, i * 80, j * 80);

                }
            }
        }

    }
}
