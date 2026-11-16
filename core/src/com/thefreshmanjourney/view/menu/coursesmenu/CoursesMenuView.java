package com.thefreshmanjourney.view.menu.coursesmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.thefreshmanjourney.controller.listener.ChangeScreenListener;
import com.thefreshmanjourney.view.types.BackgroundType;
import com.thefreshmanjourney.view.types.ScreenType;
import com.thefreshmanjourney.model.game.course.Course;
import com.thefreshmanjourney.view.types.SkinType;

/**
 * This class represents the courses menu view.
 */
public class CoursesMenuView {

    /**
     * A stage.
     */
    private Stage stage;

    /**
     * The course buttons.
     */
    private Array<ImageTextButton> listButton;

    /**
     * A button to go back.
     */
    private ImageButton goBack;

    /**
     * A blank label.
     */
    private Label blankLabel;

    /**
     * A table.
     */
    private Table table;

    /**
     * A inner table.
     */
    private Table innerTable;


    /**
     * Creates a courses menu view.
     *
     * @param courses The courses model list
     */
    public CoursesMenuView(Array<Course> courses) {
        initialize(courses);
    }

    /**
     * Initializes this course menu view.
     *
     * @param courses The courses model list
     */
    private void initialize(Array<Course> courses){
        stage=new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Skin skin= SkinType.UI_SKIN.getSkin();
        initializeButtons(courses,skin);
        initializeLabels(skin);
        initializeTables();
    }

    /**
     * Initializes the courses menu view labels.
     *
     * @param skin The labels skin
     */
    private void initializeLabels(Skin skin){
        blankLabel = new Label(" ", skin, "default");
    }

    /**
     * Initializes the courses menu view labels.
     *
     * @param courses The courses model list
     * @param skin The labels skin
     */
    private void initializeButtons(Array<Course> courses, Skin skin){

        goBack = new ImageButton(skin,"settingsarrow");
        goBack.addListener(new ChangeScreenListener(ScreenType.MAIN_MENU));

        listButton= new Array<>();
        for (Course course : courses ) {
            ImageTextButton button = new ImageTextButton(course.getName().toUpperCase(), skin,"course");
            button.addListener(new ChangeScreenListener(ScreenType.COURSE));
            button.getLabelCell().grow().padRight(70);
            listButton.add(button);
        }
    }

    /**
     * Initializes the courses menu view tables.
     */
    private void initializeTables(){
        table=new Table();
        table.setFillParent(true);
        table.setBackground(BackgroundType.MENU_BACKGROUND.getBackground());
        stage.addActor(table);

        table.add().grow();
        table.row();
        table.add().width(Value.percentWidth(0.23f, table));
        table.add(goBack).top().right().padRight(17).width(Value.percentWidth(0.07f, table));

        innerTable = new Table();

        for(int i = 0; i< listButton.size; i++){
            innerTable.add();
            innerTable.add(listButton.get(i)).center().expand();
            innerTable.row().pad(40,0,20,0);
        }

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
        ScreenUtils.clear(0, 0, 0f, 1);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    /**
     * Called when the screen should release all resources.
     */
    public void dispose(){
        stage.dispose();
    }

}
