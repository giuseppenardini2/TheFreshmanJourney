package com.thefreshmanjourney.view.menu.settingsmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.thefreshmanjourney.controller.listener.ChangeScreenListener;
import com.thefreshmanjourney.controller.listener.ResetButtonListener;
import com.thefreshmanjourney.controller.listener.ResetProfileListener;
import com.thefreshmanjourney.controller.listener.SettingsSliderListener;
import com.thefreshmanjourney.view.types.BackgroundType;
import com.thefreshmanjourney.view.types.ScreenType;
import com.thefreshmanjourney.view.types.SkinType;

/**
 * This class represents the settings menu view.
 */
public class SettingsMenuView {

    /**
     * A stage.
     */
    private Stage stage;

    /**
     * A table.
     */
    private Table table;

    /**
     * A inner table.
     */
    private Table innerTable;

    /**
     * The reset profile dialog.
     */
    private Dialog resetProfileDialog;

    /**
     * The go back button.
     */
    private ImageButton arrowButton;

    /**
     * The settings button.
     */
    private ImageButton settingsButton;

    /**
     * The reset sound button.
     */
    private ImageButton resetSoundButton;

    /**
     * The reset music button.
     */
    private ImageButton resetMusicButton;

    /**
     * The controls button.
     */
    private ImageTextButton controlsButton;

    /**
     * The reset profile button.
     */
    private ImageTextButton resetProfileButton;

    /**
     * The information button.
     */
    private ImageTextButton informationButton;

    /**
     * The credits button.
     */
    private ImageTextButton creditsButton;

    /**
     * The sound volume label.
     */
    private Label soundVolumeLabel;

    /**
     * The music volume label.
     */
    private Label musicVolumeLabel;

    /**
     * A blank label.
     */
    private Label blankLabel;

    /**
     * The sound volume slider.
     */
    private Slider soundVolumeSlider;

    /**
     * The music volume slider.
     */
    private Slider musicVolumeSlider;


    /**
     * Creates a settings menu view.
     */
    public SettingsMenuView() {initialize();}

    /**
     * Initializes this settings menu view.
     */
    private void initialize(){
        stage=new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        Skin skin= SkinType.UI_SKIN.getSkin();
        initializeButtons(skin);
        initializeLabels(skin);
        initializeTable();
        initializeDialog(skin);
    }

    /**
     * Initializes the settings menu view buttons.
     *
     * @param skin The buttons skin
     */
    private void initializeButtons(Skin skin){
        arrowButton = new ImageButton(skin, "settingsarrow");
        arrowButton.addListener(new ChangeScreenListener(ScreenType.MAIN_MENU));

        settingsButton = new ImageButton(skin, "settingsicon");
        resetSoundButton = new ImageButton(skin, "reset");
        resetSoundButton.addListener(new ResetButtonListener());
        resetMusicButton = new ImageButton(skin, "reset");
        resetMusicButton.addListener(new ResetButtonListener());

        controlsButton = new ImageTextButton("Controls", skin, "rectangularButton");
        resetProfileButton = new ImageTextButton("Reset profile", skin, "warningButton");
        resetProfileButton.addListener(new ResetProfileListener());
        informationButton = new ImageTextButton("Informations", skin, "rectangularButton");
        creditsButton = new ImageTextButton("Credits", skin, "rectangularButton");

        soundVolumeSlider = new Slider(0f, 1f, 0.1f,false, skin);
        soundVolumeSlider.addListener(new SettingsSliderListener());

        musicVolumeSlider = new Slider(0f, 1f, 0.1f,false, skin);
        musicVolumeSlider.addListener(new SettingsSliderListener());
    }

    /**
     * Initializes the settings menu view labels.
     *
     * @param skin The labels skin
     */
    private void initializeLabels(Skin skin){
        soundVolumeLabel = new Label("Sound Volume", skin, "big");
        musicVolumeLabel = new Label("Music Volume", skin, "big");
        blankLabel = new Label(" ", skin, "default");
    }

    /**
     * Initializes the settings menu view table.
     */
    private void initializeTable(){
        table = new Table();
        table.setFillParent(true);
        table.setBackground(BackgroundType.MENU_BACKGROUND.getBackground());
        stage.addActor(table);

        table.add().grow();
        table.row();
        table.add().width(Value.percentWidth(0.23f, table));
        table.add(arrowButton).top().right().padRight(17).width(Value.percentWidth(0.07f, table));


        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("img/menu/settings-background-RoundlightBlue.png")), 10, 10, 10, 10);
        NinePatchDrawable innerNinePatchDrawable = new NinePatchDrawable(patch);
        innerTable = new Table();
        innerTable.setBackground(innerNinePatchDrawable);

        innerTable.add(settingsButton).left().expand().padLeft(7);
        innerTable.row().pad(0, 0, 0, 0);
        innerTable.add();
        innerTable.add(soundVolumeLabel).expand();
        innerTable.row().pad(0, 0, 10, 0);
        innerTable.add();
        innerTable.add(soundVolumeSlider).expand().prefWidth(200);
        innerTable.add(resetSoundButton).left().padLeft(7).expand();
        innerTable.row().pad(0, 0, 0, 0);
        innerTable.add();
        innerTable.add(musicVolumeLabel).expand();
        innerTable.row().pad(0, 0, 10, 0).padBottom(30);
        innerTable.add();
        innerTable.add(musicVolumeSlider).expand().prefWidth(200);
        innerTable.add(resetMusicButton).left().padLeft(7);
        innerTable.row().pad(0, 0, 10, 0).padBottom(20);
        innerTable.add();
        innerTable.add(controlsButton).padRight(7).expand();
        innerTable.add(resetProfileButton).padLeft(7).padRight(50);
        innerTable.row().pad(0, 0, 10, 0);
        innerTable.add();
        innerTable.add(informationButton).padRight(7).expand();
        innerTable.add(creditsButton).padLeft(7).padRight(50);

        table.add(innerTable).width(Value.percentWidth(0.40f, table)).grow();
        table.add(blankLabel).width(Value.percentWidth(0.30f, table)).grow();
        table.row();
        table.add(blankLabel).colspan(3).grow();
    }

    /**
     * Initializes the settings menu view dialog.
     *
     * @param skin The dialog skin
     */
    private void initializeDialog(Skin skin){
        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("img/menu/miniBackground.png")), 10, 10, 10, 10);
        NinePatchDrawable innerNinePatchDrawable = new NinePatchDrawable(patch);


        resetProfileDialog = new Dialog("Warning", skin, "dialog");
        resetProfileDialog.getTitleLabel().setAlignment(Align.center);
        resetProfileDialog.getTitleTable().padTop(5);
        resetProfileDialog.getTitleLabel().setFillParent(true);

        resetProfileDialog.background(innerNinePatchDrawable);
        resetProfileDialog.text(new Label("Are you sure you want to reset it?", skin, "default")).padTop(50);

        ImageTextButton yesButton = new ImageTextButton("Yes", skin, "smallWarningButton");
        yesButton.addListener(new ResetProfileListener());
        ImageTextButton noButton = new ImageTextButton("No", skin, "smallButton");
        noButton.addListener(new ResetProfileListener());

        resetProfileDialog.button(yesButton, true).button(noButton, false);
        resetProfileDialog.getButtonTable().padTop(10);
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
        ScreenUtils.clear(255, 255, 255, 0);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    /**
     * Called when the screen should release all resources.
     */
    public void dispose(){
        stage.dispose();
    }

    /**
     * Displays the profile reset dialog.
     */
    public void showProfileResetDialog() {
        resetProfileDialog.show(stage);
    }


    /**
     * Returns the music volume slider.
     *
     * @return The music volume slider
     */
    public Slider getMusicVolumeSlider() {
        return musicVolumeSlider;
    }

    /**
     * Returns the sound volume slider.
     *
     * @return The sound volume slider
     */
    public Slider getSoundVolumeSlider() {
        return soundVolumeSlider;
    }

    /**
     * Returns the reset music button.
     *
     * @return The reset music button
     */
    public ImageButton getResetMusicButton() {
        return resetMusicButton;
    }

    /**
     * Returns the reset profile dialog.
     *
     * @return The reset profile dialog
     */
    public Dialog getResetProfileDialog() {
        return resetProfileDialog;
    }

}
