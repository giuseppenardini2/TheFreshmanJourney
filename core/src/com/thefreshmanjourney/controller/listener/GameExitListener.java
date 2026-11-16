package com.thefreshmanjourney.controller.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

/**
 * This class represents a listener that closes the game when the event occurs.
 */
public class GameExitListener implements EventListener {

    @Override
    public boolean handle(Event event) {
        if(((Button) event.getListenerActor()).isPressed())
            Gdx.app.exit();
        return true;
    }

}
