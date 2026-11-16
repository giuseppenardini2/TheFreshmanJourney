package com.thefreshmanjourney.controller.menu.mainmenu;

import com.badlogic.gdx.Screen;
import com.thefreshmanjourney.controller.music.MusicManager;
import com.thefreshmanjourney.view.menu.mainmenu.MainMenuView;
import com.thefreshmanjourney.view.types.MusicType;

/**
 * This class represents the main menu controller.
 */
public class MainMenuController implements Screen {

    /**
     * A main menu view.
     */
    private MainMenuView mainMenuView;

    /**
     * A instance of this main menu controller for singleton.
     */
    private static MainMenuController instance;

    /**
     * Creates a main menu controller.
     */
    private MainMenuController(){
        mainMenuView=new MainMenuView();
    }

    /**
     * Returns this main menu controller instance.
     *
     * @return This main menu controller instance
     */
    public static MainMenuController getInstance(){
        if(instance==null)
            instance=new MainMenuController();
        return instance;
    }

    @Override
    public void show() {
        MusicManager.getInstance().changeMusic(MusicType.MENU_MUSIC);
        mainMenuView.show();
    }

    @Override
    public void render(float delta) {
        mainMenuView.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        mainMenuView.resize(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        mainMenuView.dispose();
    }
}
