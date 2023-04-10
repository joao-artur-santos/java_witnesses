package org.academiadecodigo.bootcamp.glasslight.gameObjects.player;

import org.academiadecodigo.bootcamp.glasslight.gameManager.Game;
import org.academiadecodigo.bootcamp.glasslight.Grid;
import org.academiadecodigo.bootcamp.glasslight.Main;
import org.academiadecodigo.bootcamp.glasslight.gameManager.GameSounds;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class Witnesses {

    private int row_pos;
    private int col_pos;
    private int direction;
    private int size;
    private Picture headPic;
    private Grid grid;

    public boolean isDead;

    private LinkedList<Node> list;

    Game game;

    public Witnesses(Grid grid, Game game) {
        this.grid = grid;
        this.game = game;
        row_pos = grid.getROWS() / 2;//Main.WIDTH / 2 + Main.PADDING;
        col_pos = grid.getCOLS() / 2; //Main.HEIGHT / 2 + Main.PADDING;
        //head = new Rectangle(row_pos * Grid.CELLSIZE + Main.PADDING, col_pos * Grid.CELLSIZE + Main.PADDING, Grid.CELLSIZE, Grid.CELLSIZE);
        headPic = new Picture(col_pos * Grid.CELLSIZE + Main.PADDING, row_pos * Grid.CELLSIZE + Main.PADDING, "zeze.png");


        direction = 5;
        size = 0;

        list = new LinkedList<>();
    }

    public void restartPlayer() {
        headPic.delete();
        headPic.translate((col_pos * Grid.CELLSIZE + Main.PADDING) - (headPic.getX()), (row_pos * Grid.CELLSIZE + Main.PADDING) - (headPic.getY()));
        headPic.draw();
        direction = 1;
        size = 0;
        isDead = false;

    }

    public void grow(String string) {
        GameSounds faces = new GameSounds("/faces.wav");
        faces.play(false);

        if (!list.isEmpty()) {
            Node iterator = null;
            for (Node o : list) {
                iterator = o;
            }
            list.add(new Node(iterator.col, iterator.row, iterator.direction, string));
            size++;
            return;
        }
        list.add(new Node(headPic.getX(), headPic.getY(), direction, string));
        size++;
    }

    public void remove() {
        GameSounds robot = new GameSounds("/robot.wav");
        robot.play(false);

        if (!list.isEmpty()) {
            list.getLast().nodeImage.delete();
            list.removeLast();
            size--;
            if (size < 0) {
                size = 0;
            }
        }

    }

    public void deathAnimation() {
        if (!list.isEmpty()) {
            try {
                list.getLast().nodeImage.delete();
                list.removeLast();
                GameSounds puff = new GameSounds("/snakeEater.wav");
                puff.play(false);
                Thread.sleep(60);
                deathAnimation();
                size = 0;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //Check Collision with itself and the borders of the game
    public void checkCollison() {

        //Collision with itself
        if (!list.isEmpty()) {
            for (Node node : list) {
                if (headPic.getX() == node.col &&
                        headPic.getY() == node.row) {
                    isDead = true;
                }
            }
        }


        //Collision with the borders of the grid
        //Transports to the opposite side of the side the snake enters
        if (headPic.getX() >= Main.WIDTH + Main.PADDING) {
            headPic.translate(-Main.WIDTH, 0);
        } else if (headPic.getX() < Main.PADDING) {
            headPic.translate(Main.WIDTH, 0);
        } else if (headPic.getY() >= Main.HEIGHT + Main.PADDING) {
            headPic.translate(0, -Main.HEIGHT);
        } else if (headPic.getY() < Main.PADDING) {
            headPic.translate(0, Main.HEIGHT);
        }
    }

    //Move player depending on the direction of the snake
    public void move() {
        int oldCol = headPic.getX();
        int oldRow = headPic.getY();
        int oldDir = direction;
        switch (direction) {
            case 0:
                headPic.translate(0, -Grid.CELLSIZE);
                break;
            case 1:
                headPic.translate(Grid.CELLSIZE, 0);
                break;
            case 2:
                headPic.translate(0, Grid.CELLSIZE);
                break;
            case 3:
                headPic.translate(-Grid.CELLSIZE, 0);
                break;
            default:
                headPic.translate(0,0);
                break;
        }
        //chance position of each node of the snake,
        // passing the position of the previous node with the next
        for (Node o : list) {
            int oldNodeCol = o.col;
            int oldNodeRow = o.row;
            int oldNodeDir = o.direction;

            o.nodeImage.translate(oldCol - o.col, oldRow - o.row);

            o.col = oldCol;
            o.row = oldRow;
            o.direction = oldDir;

            oldCol = oldNodeCol;
            oldRow = oldNodeRow;
            oldDir = oldNodeDir;
        }
    }


    //Setters
    public void setDirection(int direction) {
        this.direction = direction;
    }
    //Getters
    public Picture getHead() {
        return headPic;
    }

    public int getDiretion() {
        return direction;
    }

    public Grid getGrid() {
        return grid;
    }

    public int getSize() {
        return size;
    }

    public LinkedList<Node> getList() {
        return list;
    }

    public String fileDire(String file) {
        return file;
    }

    public class Node {

        private int col;
        private int row;
        private int direction;
        private Picture nodeImage;


        public Node(int col, int row, int direction, String file) {
            nodeDirectionSpawn(col, row, direction);
            this.direction = direction;
            //node = new Rectangle(this.col, this.row, Grid.CELLSIZE, Grid.CELLSIZE);
            nodeImage = new Picture(this.col, this.row, file);
            nodeImage.draw();
        }


        public void nodeDirectionSpawn(int col, int row, int direction) {
            switch (direction) {
                case 0:
                    this.row = row + Grid.CELLSIZE;
                    this.col = col;
                    break;
                case 1:
                    this.row = row;
                    this.col = col - Grid.CELLSIZE;
                    break;
                case 2:
                    this.row = row - Grid.CELLSIZE;
                    this.col = col;
                    break;
                case 3:
                    this.row = row;
                    this.col = col + Grid.CELLSIZE;
                    break;
            }
        }

        //Getters
        public Picture getNodeImage() {
            return nodeImage;
        }
    }
}