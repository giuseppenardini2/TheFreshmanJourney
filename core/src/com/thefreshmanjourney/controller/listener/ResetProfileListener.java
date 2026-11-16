package com.thefreshmanjourney.controller.listener;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.thefreshmanjourney.controller.menu.settingsmenu.SettingsMenuController;
import com.thefreshmanjourney.model.profile.ProfileStatistics;

/**
 * This class represents a listener that resets the profile statistics when the event occurs.
 */
public class ResetProfileListener implements EventListener {

    @Override
    public boolean handle(Event event) {
        if (((Button)event.getListenerActor()).isPressed()) {
            SettingsMenuController.getInstance().getSettingsMenuView().showProfileResetDialog();
        }
        if (((Button)event.getListenerActor()).isPressed() && ((ImageTextButton)event.getListenerActor()).getText().toString().equals("Yes")){
            ProfileStatistics.reset();
            SettingsMenuController.getInstance().getSettingsMenuView().getResetProfileDialog().remove();
       }
        if (((Button)event.getListenerActor()).isPressed() && ((ImageTextButton)event.getListenerActor()).getText().toString().equals("No")) {
            SettingsMenuController.getInstance().getSettingsMenuView().getResetProfileDialog().remove();
        }
        return true;
    }
}
