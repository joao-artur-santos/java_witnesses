package org.academiadecodigo.bootcamp.glasslight.gameManager;

import org.academiadecodigo.bootcamp.glasslight.Grid;
import org.academiadecodigo.bootcamp.glasslight.Main;
import org.academiadecodigo.bootcamp.glasslight.gameObjects.pickUpObjects.PickUpsObjects;
import org.academiadecodigo.bootcamp.glasslight.gameObjects.player.Witnesses;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.io.IOException;

public class Game {

    private SaveLoadScore saver;
    private Handler handler;
    private PickUpsObjects gameObjects;
    private boolean notEnd = true;
    private boolean startGame = false;

    private GameSounds startMusic;
    Picture gameOverScreen;
    Picture gameOverScreenButton;

    private Witnesses witnesses;
    public void init(Witnesses witnesses) {

        this.witnesses = witnesses;
        handler = new Handler(witnesses, this);
        gameObjects = new PickUpsObjects(witnesses);
        saver = new SaveLoadScore(witnesses);

        //Draw title screen plus start to play text
        Picture startScreen = new Picture(0,0, "startImage.png");
        Picture startScreenText = new Picture(-700,-250, "startToPlay.png");
        startScreenText.grow(-450,-300);
        startScreen.draw();

        //Music of the game
        startMusic = new GameSounds("/START.wav");


        //start music starts to play
        startMusic.play(true);



        try { //waiting for game to start
            while(!startGame){
                startScreenText.draw();

                Thread.sleep(1000);
                startScreenText.delete();
                Thread.sleep(200);
                //System.out.println("waiting start");
            }
            //System.out.println("start game");
            startScreen.delete();
            startMusic.stop();
            Picture gamefield = new Picture(0,0,"relvinhaMesmoFinal.png");
            gamefield.draw();

            gameFlow(witnesses);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void gameFlow(Witnesses witnesses) throws InterruptedException {

        witnesses.setDirection(5);
        int highScore;
        Text highScoreText;

        try {
            highScore = saver.loadScore();
            highScoreText = new Text(1560, 225,highScore + "");
            highScoreText.grow(25,20);
            highScoreText.setColor(Color.DARK_GRAY);

            highScoreText.draw();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int steps = 0;
        GameSounds gameMusic = new GameSounds("/GAME.wav");
        Text score;

        witnesses.getHead().draw();

        gameMusic.play(true);
        score = new Text(695,225,witnesses.getSize() + "");
        score.grow(20,20);
        score.setColor(Color.DARK_GRAY);

        while (!witnesses.isDead) {

            score.delete();
            score.setText(witnesses.getSize() + "");
            score.draw();

            if(steps == 100){
                gameObjects.deleteObjects();
                gameObjects.setCount(0);
                steps = 0;
            }

            spawnGameObjects();

            Thread.sleep(200);
            witnesses.move();
            steps++;
            witnesses.checkCollison();

            gameObjects.checkPickUp();

            //Atualiza o score na tela

        }

        highScoreText.delete();
        if(highScore < witnesses.getSize()){
            try {
                highScore = witnesses.getSize();
                saver.saveScore(highScore);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        gameMusic.stop();
        witnesses.deathAnimation();
        score.delete();
        highScoreText.delete();
        witnesses.setDirection(5);
        GameSounds gameOverMusic = new GameSounds("/GameOver.wav");
        gameOverMusic.play(true);

        gameOverScreen = new Picture(0,0, "GAMEOVER.png");
        gameOverScreen.draw();
        gameOverScreenButton = new Picture(1200, 850, "gameOverButtons.png");


        while(notEnd){
            //game over imagem
            //musica do joão
            gameOverScreenButton.draw();
            Thread.sleep(1000);
            gameOverScreenButton.delete();
            Thread.sleep(200);


            //System.out.println("waiting");
        }
        gameOverMusic.stop();

        gameOverScreen.delete();

        //Thread.sleep(200);
        witnesses.restartPlayer();
        notEnd = true;
        gameObjects.deleteObjects();
        gameObjects.setCount(0);
        gameFlow(witnesses);
    }

    public void setNotEnd(boolean notEnd) {
        this.notEnd = notEnd;
    }

    public void setStartGame(boolean startGame) {
        this.startGame = startGame;
    }

    public boolean isStartGame() {
        return startGame;
    }

    public void spawnGameObjects(){
        int gameObjectsToSpawn;

        if (witnesses.getSize() < (Main.WIDTH / Grid.CELLSIZE) * (Main.HEIGHT / Grid.CELLSIZE) / 2){
            gameObjectsToSpawn = 5;
        } else if (((Main.WIDTH / Grid.CELLSIZE) * (Main.HEIGHT / Grid.CELLSIZE) / 2) <= witnesses.getSize() &&
                witnesses.getSize() < (((Main.WIDTH / Grid.CELLSIZE) * (Main.HEIGHT / Grid.CELLSIZE) / 2) + ((Main.WIDTH / Grid.CELLSIZE) * (Main.HEIGHT / Grid.CELLSIZE) / 4))) {
            gameObjectsToSpawn = 3;
        } else {
            gameObjectsToSpawn = 1;
        }

        if (gameObjects.getCount() < gameObjectsToSpawn){
            for (int i = gameObjects.getCount(); i < gameObjectsToSpawn; i++){
                gameObjects.generate();
            }
        }
    }
}