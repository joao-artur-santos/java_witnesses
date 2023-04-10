package org.academiadecodigo.bootcamp.glasslight;

import org.academiadecodigo.bootcamp.glasslight.gameManager.Game;
import org.academiadecodigo.bootcamp.glasslight.gameObjects.player.Witnesses;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.awt.*;

public class Main {
    public static final int PADDING = 320;
    public static final int WIDTH = 1600;
    public static final int HEIGHT = 720;


    public static void main(String[] args) {

        Grid grid = new Grid(new Rectangle(PADDING, PADDING, WIDTH, HEIGHT));
        grid.getField().draw();

        Rectangle rect = new Rectangle(WIDTH + PADDING, 0, PADDING, PADDING * 2 + HEIGHT);
        rect.setColor(Color.WHITE);
        rect.fill();

        Rectangle rect2 = new Rectangle(WIDTH + PADDING, 0, PADDING, PADDING * 2 + HEIGHT);
        rect2.setColor(Color.WHITE);


        Game game = new Game();
        Witnesses witnesses = new Witnesses(grid, game);
        //witnesses.getHead().fill();

        game.init(witnesses);

    }
}