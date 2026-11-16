package com.thefreshmanjourney.view.menu.profile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.thefreshmanjourney.controller.listener.ChangeScreenListener;
import com.thefreshmanjourney.view.types.BackgroundType;
import com.thefreshmanjourney.view.types.ScreenType;
import com.thefreshmanjourney.view.types.SkinType;

/**
 * This class represents the profile menu view.
 */
public class ProfileMenuView {

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
     * The profile button.
     */
    private ImageButton profileButton;

    /**
     * The go back button.
     */
    private ImageButton arrowButton;

    /**
     * The nickname text field.
     */
    private TextField nicknameTextField;

    /**
     * The carrer label.
     */
    private Label careerLabel;

    /**
     * The profile label.
     */
    private Label profileLabel;

    /**
     * The record exams label.
     */
    private Label recordedExamsLabel;

    /**
     * The available exams label.
     */
    private Label availableExamsLabel;

    /**
     * The total CFU label.
     */
    private Label totalCFULabel;

    /**
     * The degrees obtained label.
     */
    private Label degreesObtainedLabel;

    /**
     * The lessons attended label.
     */
    private Label lessonsAttendedLabel;

    /**
     * The lessons quit label.
     */
    private Label lessonsQuitLabel;

    /**
     * The lessons completed label.
     */
    private Label lessonsCompletedLabel;

    /**
     * The quit completed lessons ratio label.
     */
    private Label quitCompletedLessonsRatioLabel;

    /**
     * The defeated students label.
     */
    private Label defeatedStudentsLabel;

    /**
     * The deaths label.
     */
    private Label deathsLabel;

    /**
     * The defeated students deaths ratio label
     */
    private Label defeatedStudentsDeathsRatioLabel;

    /**
     * A blank label.
     */
    private Label blankLabel;

    /**
     * Creates a profile menu view.
     */
    public ProfileMenuView(){
        initialize();
    }

    /**
     * Initializes this profile menu view.
     */
    private void initialize(){
        stage=new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Skin skin=SkinType.UI_SKIN.getSkin();
        initializeTextField(skin);
        initializeButtons(skin);
        initializeLabels(skin);
        initializeTable();
    }

    /**
     * Initializes the profile menu view text field.
     *
     * @param skin The text field skin
     */
    private void initializeTextField(Skin skin){
        nicknameTextField =new TextField("Student", skin);
        nicknameTextField.setAlignment(Align.center);
    }

    /**
     * Initializes the profile menu view buttons.
     *
     * @param skin The buttons skin
     */
    private void initializeButtons(Skin skin){
        Skin avatarSkin=SkinType.AVATAR_SKIN.getSkin();
        profileButton = new ImageButton(avatarSkin, "avatar1");
        arrowButton = new ImageButton(skin, "settingsarrow");
        arrowButton.addListener(new ChangeScreenListener(ScreenType.MAIN_MENU));
    }

    /**
     * Initializes the profile menu view labels.
     *
     * @param skin The labels skin
     */
    private void initializeLabels(Skin skin){
        profileLabel = new Label("Profile", skin, "big");
        careerLabel = new Label("Career", skin, "big");
        recordedExamsLabel = new Label("Recorded exams: ", skin );
        availableExamsLabel = new Label("Available exams: ", skin);
        totalCFULabel = new Label("Total CFU: ", skin);
        degreesObtainedLabel = new Label("Degrees obtained: ", skin);
        lessonsAttendedLabel = new Label("Lessons attended: ", skin);
        lessonsQuitLabel = new Label("Lessons quit: ", skin);
        lessonsCompletedLabel = new Label("Lessons Completed: ", skin);
        quitCompletedLessonsRatioLabel = new Label("Quit/Completed lesson ratio: ", skin);
        defeatedStudentsLabel = new Label("Defeated students", skin);
        deathsLabel = new Label("Deaths", skin);
        defeatedStudentsDeathsRatioLabel = new Label("Defeated students/Deaths ratio: ", skin);
        blankLabel = new Label(" ", skin, "default");
    }

    /**
     * Initializes the profile menu view table.
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

        innerTable.add(profileLabel).expand().center().colspan(2).padLeft(7);
        innerTable.row().pad(0, 0, 0, 0);
        innerTable.add(profileButton).expand().center().padLeft(7);
        innerTable.add(nicknameTextField).fillX().left().padRight(170);
        innerTable.row().pad(0, 0, 0, 0);
        innerTable.add(careerLabel).expand().left().colspan(2).padLeft(7);
        innerTable.row().pad(0, 0, 0, 0);
        innerTable.add(recordedExamsLabel).expand().left().padLeft(7);
        innerTable.add(lessonsCompletedLabel).expand().left();
        innerTable.row().pad(0, 0, 0, 0);
        innerTable.add(availableExamsLabel).expand().left().padLeft(7);
        innerTable.add(quitCompletedLessonsRatioLabel).expand().left();
        innerTable.row().pad(0, 0, 0, 0);
        innerTable.add(totalCFULabel).expand().left().padLeft(7);
        innerTable.add(defeatedStudentsLabel).expand().left();
        innerTable.row().pad(0, 0, 0, 0);
        innerTable.add(degreesObtainedLabel).expand().left().padLeft(7);
        innerTable.add(deathsLabel).expand().left();
        innerTable.row().pad(0, 0, 0, 0);
        innerTable.add(lessonsAttendedLabel).expand().left().padLeft(7);
        innerTable.add(defeatedStudentsDeathsRatioLabel).expand().left();
        innerTable.row().pad(0, 0, 0, 0);
        innerTable.add(lessonsQuitLabel).expand().left().padLeft(7);

        table.add(innerTable).width(Value.percentWidth(0.40f, table)).grow();
        table.add(blankLabel).width(Value.percentWidth(0.30f, table)).grow();
        table.row();
        table.add(blankLabel).colspan(3).grow();
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
     * Sets the nickname text field.
     *
     * @param nickname The new nickname.
     */
    public void setNicknameTextFieldText(String nickname){
        nicknameTextField.setText(nickname);
    }

    /**
     * Returns the text of the nickname text field.
     *
     * @return The text of the nickname text field
     */
    public String getNicknameTextFieldText(){
        return nicknameTextField.getText();
    }

    /**
     * Sets the recorded exams label.
     *
     * @param value The new value
     */
    public void setRecordedExamsLabelValue(int value) {
        recordedExamsLabel.setText("Recorded exams: "+value);
    }

    /**
     * Sets the available exams label.
     *
     * @param value The new value
     */
    public void setAvailableExamsLabelValue(int value) {
        availableExamsLabel.setText("Available exams: "+value);
    }

    /**
     * Sets the total CFU label.
     *
     * @param value The new value
     */
    public void setTotalCFULabelValue(int value) {
        totalCFULabel.setText("Total CFU: "+value);
    }

    /**
     * Sets the degrees obtained label.
     *
     * @param value The new value
     */
    public void setDegreesObtainedLabelValue(int value) {
        degreesObtainedLabel.setText("Degrees obtained: "+value);
    }

    /**
     * Sets the lessons attended label.
     *
     * @param value The new value
     */
    public void setLessonsAttendedLabelValue(int value) {
        lessonsAttendedLabel.setText("Lessons attended: "+value);
    }

    /**
     * Sets the lessons quit label.
     *
     * @param value The new value
     */
    public void setLessonsQuitLabelValue(int value) {
        lessonsQuitLabel.setText("Lessons quit: "+value);
    }

    /**
     * Sets the lessons completed label.
     *
     * @param value The new value
     */
    public void setLessonsCompletedLabelValue(int value) {
        lessonsCompletedLabel.setText("Lessons completed: "+value);
    }

    /**
     * Sets the quit completed lessons ratio label.
     *
     * @param value The new value
     */
    public void setQuitCompletedLessonsRatioLabelValue(float value) {
        quitCompletedLessonsRatioLabel.setText("Quit/Completed lesson ratio: "+value);
    }

    /**
     * Sets the defeated students label.
     *
     * @param value The new value
     */
    public void setDefeatedStudentsLabelValue(int value) {
        defeatedStudentsLabel.setText("Defeated students: "+value);
    }

    /**
     * Sets the deaths label.
     *
     * @param value The new value
     */
    public void setDeathsLabelValue(int value) {
        deathsLabel.setText("Deaths: "+value);
    }

    /**
     * Sets the defeated students deaths ratio label.
     *
     * @param value The new value
     */
    public void setDefeatedStudentsDeathsRatioLabelValue(float value) {
        defeatedStudentsDeathsRatioLabel.setText("Defeated students/Deaths ratio: "+value);
    }

}
