package org.academiadecodigo.bootcamp.glasslight.gameManager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameSounds {
    private Clip clip;
    private URL soundURL;

    public GameSounds(String var1) {
        this.initClip(var1);
    }

    public void play(boolean var1) {
        if (var1) {
            this.clip.setFramePosition(0);
        }

        this.clip.start();
    }

    public void stop() {
        this.clip.stop();
    }

    public void close() {
        this.clip.close();
    }

    public void setLoop(int var1) {
        this.clip.loop(var1);
    }

    public void reOpen() {
        AudioInputStream var1;

        try {
            var1 = AudioSystem.getAudioInputStream(this.soundURL);
            this.clip.open(var1);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException var3) {
            System.out.println(var3.getMessage());
        }

    }

    private void initClip(String var1) {
        this.soundURL = GameSounds.class.getResource(var1);
        AudioInputStream var2 = null;

        try {
            if (this.soundURL == null) {
                File var3 = new File(var1);
                this.soundURL = var3.toURI().toURL();
            }

            var2 = AudioSystem.getAudioInputStream(this.soundURL);
            this.clip = AudioSystem.getClip();
            this.clip.open(var2);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException var4) {
            System.out.println(var4.getMessage());
        }

    }
}
