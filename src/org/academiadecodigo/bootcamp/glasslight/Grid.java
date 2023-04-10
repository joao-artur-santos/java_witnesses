package org.academiadecodigo.bootcamp.glasslight;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Grid {
    public final int ROWS,COLS;
    public static final int CELLSIZE = 80;

    private Rectangle field;

    public Grid(Rectangle field) {
        this.field = field;
        ROWS = field.getHeight() / CELLSIZE;
        COLS = field.getWidth() / CELLSIZE;
    }

    //Getters
    public Rectangle getField() {
        return field;
    }

    public int getCOLS() {
        return COLS;
    }

    public int getROWS() {
        return ROWS;
    }
}

