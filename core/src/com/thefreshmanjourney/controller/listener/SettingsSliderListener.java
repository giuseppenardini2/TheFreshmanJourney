package com.thefreshmanjourney.controller.listener;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.thefreshmanjourney.controller.menu.settingsmenu.SettingsMenuController;

/**
 * This class represents a listener that manages slider value when the event occurs.
 */
public class SettingsSliderListener implements EventListener {

    @Override
    public boolean handle(Event event) {
        if(event.getListenerActor().equals(SettingsMenuController.getInstance().getSettingsMenuView().getMusicVolumeSlider())) {
            SettingsMenuController.getInstance().getSettingsMenuModel().setMusicVolume(SettingsMenuController.getInstance().getSettingsMenuView().getMusicVolumeSlider().getValue());
        } else {
            SettingsMenuController.getInstance().getSettingsMenuModel().setSoundVolume(SettingsMenuController.getInstance().getSettingsMenuView().getSoundVolumeSlider().getValue());
        }
        return true;
    }

}
