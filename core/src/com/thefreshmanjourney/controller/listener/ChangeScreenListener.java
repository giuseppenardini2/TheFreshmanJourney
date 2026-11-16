package com.thefreshmanjourney.controller.listener;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.thefreshmanjourney.controller.TheFreshmanJourneyController;
import com.thefreshmanjourney.view.types.ScreenType;

/**
 * This class represents a listener that switches screen when the event occurs.
 */
public class ChangeScreenListener implements EventListener {

    /**
     * The screen to witch to switch to.
     *
     * @see ScreenType
     */
    private ScreenType destinationScreen;


    /**
     * Creates a change screen listener.
     *
     * @param destinationScreen The destination screen
     */
    public ChangeScreenListener(ScreenType destinationScreen){
        this.destinationScreen=destinationScreen;
    }


    @Override
    public boolean handle(Event event) {
        if(((Button) event.getListenerActor()).isPressed())
            TheFreshmanJourneyController.getInstance().changeScreen(destinationScreen);
        return true;
    }

}
