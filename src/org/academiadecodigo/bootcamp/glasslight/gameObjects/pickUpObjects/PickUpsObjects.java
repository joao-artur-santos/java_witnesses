package org.academiadecodigo.bootcamp.glasslight.gameObjects.pickUpObjects;

import org.academiadecodigo.bootcamp.glasslight.*;
import org.academiadecodigo.bootcamp.glasslight.gameObjects.player.Witnesses;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class PickUpsObjects {
    private int row;
    private int col;
    private int count;

    private Grid grid;
    private LinkedList<PickUpsObjects> list;
    private Witnesses witnesses;
    public PickUpsObjects(Witnesses witnesses){
        this.witnesses = witnesses;
        grid = witnesses.getGrid();
        count = 0;
        list = new LinkedList<>();
    }

    public void generate(){
        checkPosition();
        generatePickUpType();
    }

    private void checkPosition() {
        int row_pos = (int) (Math.random() * grid.getROWS());// + Main.PADDING;
        int col_pos = (int) (Math.random() * grid.getCOLS());// + Main.PADDING;

        this.row = row_pos * Grid.CELLSIZE + Main.PADDING;
        this.col = col_pos * Grid.CELLSIZE + Main.PADDING;


        //Verify if it doesnt spawn on top of the snake body
        for (Witnesses.Node o : witnesses.getList()) {
            if (o.getNodeImage().getX() == col &&
                    o.getNodeImage().getY() == row) {
                //System.out.println("new pos1");
                checkPosition();
                return;
            }
        }
        //Verify if it doesnt spawn on top of the snake head
        if (witnesses.getHead().getX() == col &&
                witnesses.getHead().getY() == row) {
            //System.out.println("new pos2");
            checkPosition();
            return;
        }

        //Verify if it doesnt spawn on top of other game object
        if(!list.isEmpty()){
            for (PickUpsObjects objects : this.list){
                //System.out.println(objects.getRectangle().getX() + " " + objects.getRectangle().getY());
                if(objects.getRectangle().getX() == col &&
                        objects.getRectangle().getY() == row) {
                    //System.out.println("new pos3");
                    checkPosition();
                    return;
                }
            }
        }

    }

    private void generatePickUpType(){
        if ((int)(Math.random() * 10) >= 7){ //Bad pick up
            list.add(new Robot(witnesses, col, row));
        } else { //Good pick up
            list.add(new Faces(witnesses, col, row));
        }
        count++;
    }

    public void checkPickUp(){
        for (PickUpsObjects object : list){
            //System.out.println(object.getClass().getSimpleName());
            //System.out.println(object.col + " " + object.row);
            if (object.getCol() == witnesses.getHead().getX() &&
                    object.getRow() == witnesses.getHead().getY()){

                if(object.getClass().getSimpleName().equals("Robot")){
                    witnesses.remove();
                    object.getRectangle().delete();
                    list.remove(object);
                    count--;
                    break;

                } else if (object.getClass().getSimpleName().equals("Faces")) {
                    witnesses.grow(((Faces)object).getFileNameofthis());
                    //witnesses.fileDire(((Faces)object).ge)
                    object.getRectangle().delete();
                    list.remove(object);
                    count--;
                    break;
                }

                //System.out.println("oof");
            }
        }
    }

    public void deleteObjects(){
        for (PickUpsObjects objects : list){
            objects.getRectangle().delete();
            list.remove(objects);
            break;
        }
        if (!list.isEmpty()){
            deleteObjects();
        }
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Picture getRectangle(){ return null;}
}
