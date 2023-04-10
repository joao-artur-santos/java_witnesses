package org.academiadecodigo.bootcamp.glasslight.gameObjects.pickUpObjects;

import org.academiadecodigo.bootcamp.glasslight.gameObjects.player.Witnesses;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Robot extends PickUpsObjects {

    private Picture robot;
    //private Rectangle robot;

    private Color color;
    private int col;
    private int row;

    public Robot(Witnesses witnesses, int col, int row){
        super(witnesses);
        //color = Color.RED;
        this.col = col;
        this.row = row;
        robot = new Picture(this.col, this.row, "robot" + Integer.toString(((int)Math.ceil(Math.random() * 2))) + ".png");
        robot.draw();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Picture getRectangle(){
        return robot;
    }
}
