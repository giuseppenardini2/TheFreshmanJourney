package com.thefreshmanjourney.view.game.endgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.thefreshmanjourney.controller.listener.ChangeScreenListener;
import com.thefreshmanjourney.view.types.ScreenType;
import com.thefreshmanjourney.view.types.SkinType;

/**
 * This class represents the end game window displayed when the player win or loose.
 */
public class EndGameWindow {

    /**
     * A boolean flag indicating if the player has won.
     */
    private boolean playerWon;

    /**
     * A stage.
     */
    private Stage stage;

    /**
     * The window.
     */
    private Window EndGameWindow;

    /**
     * The title label.
     */
    private Label titleLabel;

    /**
     * The home button.
     */
    private ImageButton homeButton;

    /**
     * Creates a end game window.
     */
    public EndGameWindow(){
        playerWon=false;
        stage=new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        initialize();
    }

    /**
     * Initializes this end game window.
     */
    private void initialize(){
        Skin skin= SkinType.UI_SKIN.getSkin();
        initializeButtons(skin);
        initializeLabels(skin);
        initializeEndGameWindow(skin);
    }

    /**
     * Initializes the end game window buttons
     */
    private void initializeButtons(Skin skin){
        homeButton=new ImageButton(skin, "home");
        homeButton.addListener(new ChangeScreenListener(ScreenType.MAIN_MENU));
    }

    /**
     * Initializes the end game window labels
     */
    private void initializeLabels(Skin skin){
        if(playerWon)
            titleLabel=new Label("LESSON COMPLETED!", skin, "title");
        else
            titleLabel=new Label("LESSON QUIT!", skin, "title");
    }

    /**
     * Initializes the end game window
     */
    private void initializeEndGameWindow(Skin skin){
        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("img/menu/transparentbackground.png")), 5, 5, 3, 5);
        NinePatchDrawable winOrLoseWindowNinePatchDrawable = new NinePatchDrawable(patch);

        EndGameWindow = new Window("", skin);
        stage.addActor(EndGameWindow);
        EndGameWindow.setFillParent(true);
        EndGameWindow.setBackground(winOrLoseWindowNinePatchDrawable);

        EndGameWindow.add(titleLabel);
        EndGameWindow.row().pad(80,0,0,0);
        EndGameWindow.add(homeButton);
    }

    /**
     * Called when the screen should render itself.
     */
    public void render(){
        Gdx.input.setInputProcessor(stage);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    /**
     * Updates the title label.
     */
    private void updateTitleLabel(){
        if(playerWon)
            titleLabel.setText("LESSON COMPLETED!");
        else
            titleLabel.setText("LESSON QUIT!");
    }

    /**
     * Sets if the player has won.
     *
     * @param playerWon If the player has won
     */
    public void setPlayerWon(boolean playerWon){
        if(this.playerWon!=playerWon){
            this.playerWon=playerWon;
            updateTitleLabel();
        }
    }

}
