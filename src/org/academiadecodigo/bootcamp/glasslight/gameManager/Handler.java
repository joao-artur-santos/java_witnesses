package org.academiadecodigo.bootcamp.glasslight.gameManager;


import org.academiadecodigo.bootcamp.glasslight.gameObjects.player.Witnesses;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Handler implements KeyboardHandler {

    private Witnesses player;
    private Keyboard keyboard;
    private Game game;

    public Handler(Witnesses player, Game game) {
        this.player = player;
        this.game = game;
        keyboard = new Keyboard(this);
        createKeyboardEvents();
    }

    public void createKeyboardEvents() {

        KeyboardEvent keyboardEventRight = new KeyboardEvent();
        keyboardEventRight.setKey(KeyboardEvent.KEY_RIGHT);
        keyboardEventRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventRight);

        KeyboardEvent keyboardEventLeft = new KeyboardEvent();
        keyboardEventLeft.setKey(KeyboardEvent.KEY_LEFT);
        keyboardEventLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventLeft);

        KeyboardEvent keyboardEventUp = new KeyboardEvent();
        keyboardEventUp.setKey(KeyboardEvent.KEY_UP);
        keyboardEventUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventUp);

        KeyboardEvent keyboardEventDown = new KeyboardEvent();
        keyboardEventDown.setKey(KeyboardEvent.KEY_DOWN);
        keyboardEventDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventDown);

        KeyboardEvent keyboardEventGrow = new KeyboardEvent();
        keyboardEventGrow.setKey(KeyboardEvent.KEY_R);
        keyboardEventGrow.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventGrow);

        KeyboardEvent keyboardEventUngrow = new KeyboardEvent();
        keyboardEventUngrow.setKey(KeyboardEvent.KEY_2);
        keyboardEventUngrow.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventUngrow);

        KeyboardEvent keyboardEventQuit = new KeyboardEvent();
        keyboardEventQuit.setKey(KeyboardEvent.KEY_ESC);
        keyboardEventQuit.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventQuit);

        KeyboardEvent keyboardEventStart = new KeyboardEvent();
        keyboardEventStart.setKey(KeyboardEvent.KEY_ENTER);
        keyboardEventStart.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventStart);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                if (player.getDiretion() != 3) {
                    player.setDirection(1);
                }
                break;
            case KeyboardEvent.KEY_LEFT:
                if (player.getDiretion() != 1) {
                    player.setDirection(3);


                }
                break;
            case KeyboardEvent.KEY_UP:
                if (player.getDiretion() != 2) {
                    player.setDirection(0);

                }
                break;
            case KeyboardEvent.KEY_DOWN:
                if (player.getDiretion() != 0) {
                    player.setDirection(2);
                }
                break;
            case KeyboardEvent.KEY_R:
                if (player.isDead) {
                    game.setNotEnd(false);
                }
                break;
            case KeyboardEvent.KEY_ESC:
                System.exit(0);
                break;
            case KeyboardEvent.KEY_ENTER:
                if (!game.isStartGame()) {
                    game.setStartGame(true);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
