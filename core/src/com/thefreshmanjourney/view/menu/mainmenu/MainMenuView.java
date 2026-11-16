package com.thefreshmanjourney.view.menu.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.thefreshmanjourney.controller.listener.ChangeScreenListener;
import com.thefreshmanjourney.controller.listener.GameExitListener;
import com.thefreshmanjourney.view.types.BackgroundType;
import com.thefreshmanjourney.view.types.ScreenType;
import com.thefreshmanjourney.view.types.SkinType;

/**
 * This class represents the main menu view.
 */
public class  MainMenuView {

    /**
     * A stage.
     */
    private Stage stage;

    /**
     * A table.
     */
    private Table table;

    /**
     * The game title label.
     */
    private Label titleLabel;

    /**
     * The play button.
     */
    private ImageTextButton playButton;

    /**
     * The editor button.
     */
    private ImageTextButton editorButton;

    /**
     * The settings button.
     */
    private ImageTextButton settingsButton;

    /**
     * The exit button.
     */
    private ImageTextButton exitButton;

    /**
     * The profile button.
     */
    private ImageButton profileButton;

    /**
     * Creates a main menu view.
     */
    public MainMenuView(){
        initialize();
    }

    /**
     * Initializes this main menu view.
     */
    private void initialize(){
        stage=new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        Skin skin=SkinType.UI_SKIN.getSkin();
        initializeButtons(skin);

        titleLabel=new Label("The Freshman Journey", skin, "title");

        initializeTable();
    }

    /**
     * Initializes the main menu view buttons.
     *
     * @param skin The buttons skin
     */
    private void initializeButtons(Skin skin){
        playButton=new ImageTextButton("Play", skin);
        playButton.addListener(new ChangeScreenListener(ScreenType.COURSES_MENU));
        editorButton=new ImageTextButton("Editor", skin);
        editorButton.addListener(new ChangeScreenListener(ScreenType.EDITOR));
        settingsButton=new ImageTextButton("Settings", skin);
        settingsButton.addListener(new ChangeScreenListener(ScreenType.SETTINGS));
        exitButton=new ImageTextButton("Exit", skin);
        exitButton.addListener(new GameExitListener());
        profileButton=new ImageButton(skin, "profile");
        profileButton.addListener(new ChangeScreenListener(ScreenType.PROFILE));
    }

    /**
     * Initializes the main menu table.
     */
    private void initializeTable(){
        table=new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.setBackground(BackgroundType.MENU_BACKGROUND.getBackground());

        table.row();
        table.add(titleLabel).expandX();
        table.row().pad(40, 0, 20, 0);
        table.add(playButton);
        table.row().pad(0, 0, 20, 0);
        table.add(editorButton);
        table.row().pad(0, 0, 20, 0);
        table.add(settingsButton);
        table.row().pad(0, 0, 50, 0);
        table.add(exitButton);
        table.row();
        table.add(profileButton).right().padRight(10);
    }

    /**
     * Called when the screen becomes the current screen for a Game.
     */
    public void show(){
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Called when the screen is resized. This can happen at any point during a non-paused state but will never happen before a call to create().
     *
     * @param width The new width in pixels
     * @param height The new height in pixels
     */
    public void resize(int width, int height){
        stage.getViewport().update(width, height, true);
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render
     */
    public void render(float delta){
        ScreenUtils.clear(0, 0, 0f, 1);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    public void dispose(){
        stage.dispose();
    }

}
