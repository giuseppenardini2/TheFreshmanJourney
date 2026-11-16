package com.thefreshmanjourney.controller.listener;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.thefreshmanjourney.controller.menu.settingsmenu.SettingsMenuController;

/**
 * This class represents a listener that resets the music or sound volume when the event occurs.
 */
public class ResetButtonListener implements EventListener {

    @Override
    public boolean handle(Event event) {
        if (((Button)event.getListenerActor()).isPressed()){
            if(event.getListenerActor().equals(SettingsMenuController.getInstance().getSettingsMenuView().getResetMusicButton())){
                SettingsMenuController.getInstance().getSettingsMenuModel().setMusicVolume(0.5f);
            }else{
                SettingsMenuController.getInstance().getSettingsMenuModel().setSoundVolume(0.5f);
            }
        }
        return true;
    }
}
