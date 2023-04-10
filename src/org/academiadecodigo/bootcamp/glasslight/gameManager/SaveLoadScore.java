package org.academiadecodigo.bootcamp.glasslight.gameManager;

import org.academiadecodigo.bootcamp.glasslight.gameObjects.player.Witnesses;

import java.io.*;
import java.util.Properties;

public class SaveLoadScore {

    private final String SCORE = "highscore.txt";

    private final File file = new File(createFolder(), SCORE);

    {
        try {
            if (!file.exists()) {
                new PrintWriter(file);
                saveScore(0);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final Witnesses witnesses;

    public SaveLoadScore(Witnesses witnesses) {
        this.witnesses = witnesses;
    }

    public void saveScore(int score) throws IOException {

        // create a new file writer
        FileWriter writer = new FileWriter(file);

        // wrap the file writer using a buffered writer
        BufferedWriter bWriter = new BufferedWriter(writer);

        //add text to buffer
        bWriter.write(score + "");


        bWriter.flush(); // if the buffer is not full, flush will force disk write
        bWriter.close(); // auto-flush is done on close
    }

    public int loadScore() throws IOException {

        String line = "";
        String score = "";

        // create a new file reader
        FileReader reader = new FileReader(file);

        // wrap the file reader using a buffered reader
        BufferedReader bReader = new BufferedReader(reader);

        //verify if the file is not empty
        while ((line = bReader.readLine()) != null) {
            score = line;
            System.out.println(score);
        }

        bReader.close();
        return Integer.parseInt(score);

    }

    public File createFolder() {

        final File folder = new File(System.getProperty("user.dir"), "gamesaves");

        if (!folder.exists() && !folder.mkdirs()) {
            throw new RuntimeException("Failed to create save directory");
        }

        return folder;

    }
}


