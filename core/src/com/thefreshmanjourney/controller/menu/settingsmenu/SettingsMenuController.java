package com.thefreshmanjourney.controller.menu.settingsmenu;

import com.badlogic.gdx.Screen;
import com.thefreshmanjourney.controller.music.MusicManager;
import com.thefreshmanjourney.controller.music.SoundManager;
import com.thefreshmanjourney.model.menu.settingsmenu.SettingsMenu;
import com.thefreshmanjourney.view.menu.settingsmenu.SettingsMenuView;

/**
 * This class represents the settings menu controller.
 */
public class SettingsMenuController implements Screen {

    /**
     * A settings menu model.
     */
    private SettingsMenu settingsMenuModel;

    /**
     * A settings menu view.
     */
    private SettingsMenuView settingsMenuView;

    /**
     * A music manager.
     */
    private MusicManager musicManager;

    /**
     * A sound manager.
     */
    private SoundManager soundManager;

    /**
     * An instance of this settings menu controller for singleton.
     */
    private static SettingsMenuController instance;

    /**
     * Creates a settings menu controller.
     */
    private SettingsMenuController(){
        settingsMenuView=new SettingsMenuView();
        settingsMenuModel=new SettingsMenu();
        musicManager = MusicManager.getInstance();
        soundManager=SoundManager.getInstance();
    }

    /**
     * Returns this settings menu controller instance.
     *
     * @return This settings menu controller instance
     */
    public static SettingsMenuController getInstance(){
        if(instance==null)
            instance=new SettingsMenuController();
        return instance;
    }

    @Override
    public void show() {
        settingsMenuView.show();
    }

    @Override
    public void render(float delta) {
        musicManager.setVolume(settingsMenuModel.getMusicVolume());
        soundManager.setVolume(settingsMenuModel.getSoundVolume());
        settingsMenuView.getMusicVolumeSlider().setValue(settingsMenuModel.getMusicVolume());
        settingsMenuView.getSoundVolumeSlider().setValue(settingsMenuModel.getSoundVolume());
        settingsMenuView.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        settingsMenuView.resize(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        settingsMenuView.dispose();
    }

    /**
     * Returns the settings menu model.
     *
     * @return The settings menu model
     */
    public SettingsMenu getSettingsMenuModel(){
        return settingsMenuModel;
    }

    /**
     * Returns the settings menu view.
     *
     * @return The settings menu view
     */
    public SettingsMenuView getSettingsMenuView() {
        return settingsMenuView;
    }

}
