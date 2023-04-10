package org.academiadecodigo.bootcamp.glasslight.gameObjects.pickUpObjects;

import org.academiadecodigo.bootcamp.glasslight.gameObjects.player.Witnesses;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Faces extends PickUpsObjects {

    private int col;
    private int row;
    private Picture face;


    public enum Face {
        FACE1("2.png"),
        FACE2("3.png"),
        FACE3("4.png"),
        FACE4("5.png"),
        FACE5("6.png"),
        FACE6("7.png"),
        FACE7("8.png"),
        FACE8("9.png"),
        FACE9("10.png"),
        FACE10("11.png"),
        FACE11("12.png"),
        FACE12("13.png"),
        FACE13("14.png"),
        FACE14("15.png"),
        FACE15("16.png"),
        FACE16("17.png"),
        FACE17("18.png"),
        FACE18("19.png"),
        FACE19("20.png"),
        FACE20("21.png"),
        FACE21("22.png"),
        FACE22("23.png"),
        FACE23("24.png"),
        FACE24("25.png"),
        FACE25("26.png"),
        FACE26("27.png"),
        FACE27("28.png"),
        FACE28("29.png"),
        FACE29("30.png"),
        FACE30("31.png"),
        FACE31("32.png"),
        FACE32("33.png"),
        FACE33("34.png");


        private String fileName;

        Face(String fileName) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return fileName;
        }
    }

    private String fileNameofthis;


    public Faces(Witnesses witnesses, int col, int row) {
        super(witnesses);
        this.col = col;
        this.row = row;
        face = new Picture(this.col, this.row, getRandomFace());
        face.draw();

    }

    public String getRandomFace() {
        Face randomFace = Face.values()[((int) Math.floor(Math.random() * Face.values().length))];
        this.fileNameofthis = randomFace.getFileName();

        return fileNameofthis;
    }

    public Picture getNewPic() {
        return new Picture(this.col, this.row, getRandomFace());
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Picture getRectangle() {
        return face;
    }

    public String getFileNameofthis() {
        return fileNameofthis;
    }
}
