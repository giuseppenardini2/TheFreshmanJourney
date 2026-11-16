package com.thefreshmanjourney.controller.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.thefreshmanjourney.controller.TheFreshmanJourneyController;
import com.thefreshmanjourney.controller.game.course.CourseController;
import com.thefreshmanjourney.controller.music.MusicManager;
import com.thefreshmanjourney.model.editor.Editor;
import com.thefreshmanjourney.model.game.character.enemy.Enemy;
import com.thefreshmanjourney.model.game.map.GameMap;
import com.thefreshmanjourney.model.game.powerups.PowerUp;
import com.thefreshmanjourney.view.editor.EditorView;
import com.thefreshmanjourney.view.types.MusicType;
import com.thefreshmanjourney.view.utils.ViewUtils;

/**
 * This class represents a editor controller.
 */
public class EditorController implements Screen {

    /**
     * An instance of this editor controller for singleton.
     */
    private static EditorController instance;

    /**
     * An editor model.
     */
    private Editor editorModel;

    /**
     * An editor view.
     */
    private EditorView editorView;

    /**
     * An editor command manager.
     */
    private EditorCommandManager editorCommandManager;

    /**
     * A course controller.
     */
    private CourseController courseController;

    /**
     * A boolean flag indicating if user is editing the lesson in order to change the lesson upper bar.
     */
    private boolean editingMode;


    /**
     * Creates a editor controller.
     */
    private EditorController(){
        editorModel=new Editor();
        editorView=new EditorView(CourseController.initializeLessonView(editorModel.getCourse()));
        editorCommandManager=new EditorCommandManager(this);
        courseController = CourseController.getInstance();
        setEditingMode();
    }

    /**
     * Returns this editor controller instance.
     *
     * @return This editor controller instance
     */
    public static EditorController getInstance(){
        if(instance==null)
            instance=new EditorController();
        instance.setEditingMode();
        instance.editorModel.load();
        CourseController.updateLessonView(instance.editorModel.getCourse(), instance.getEditorView().getLessonView());
        return instance;
    }

    /**
     * Plays editor course.
     */
    public void playCourse() {
        GameMap gameMapModel=editorModel.getCourse().getCurrentLesson().getGameMap();
        gameMapModel.getPlayer().setXY(1f, 11f);
        gameMapModel.getPlayer().setY(ViewUtils.adjustYCoordinate(gameMapModel.getPlayer().getY(), GameMap.HEIGHT));
        gameMapModel.getPlayer().initialize();
        for(Enemy enemyModel:gameMapModel.getEnemies())
            enemyModel.setY(ViewUtils.adjustYCoordinate(enemyModel.getY(), GameMap.HEIGHT));
        for(PowerUp powerUpModel:gameMapModel.getPowerUps())
            powerUpModel.setY(ViewUtils.adjustYCoordinate(powerUpModel.getY(), GameMap.HEIGHT));

        editorModel.getCourse().getCurrentLesson().initializeLesson();
        courseController.setCourseModel(editorModel.getCourse());
        courseController.setLessonView(CourseController.initializeLessonView(courseController.getCourseModel()));
        setNotEditingMode();
        TheFreshmanJourneyController.getInstance().setScreen(courseController);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(editorCommandManager);
        MusicManager.getInstance().changeMusic(MusicType.EDITOR_MUSIC);
        editorView.show();
    }

    @Override
    public void render(float delta) {
        editorView.render(delta);
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}


    /**
     * Returns the editor model.
     *
     * @return The editor model
     */
    public Editor getEditorModel(){
        return editorModel;
    }

    /**
     * Returns the editor view.
     *
     * @return The editor view
     */
    public EditorView getEditorView(){
        return editorView;
    }

    /**
     * Sets editing mode true.
     */
    public void setEditingMode(){
        if(!editingMode){
            editorView.setEditorUpperBarView();
            editingMode=true;
        }
    }

    /**
     * Sets editing mode false.
     */
    public void setNotEditingMode(){
        if(editingMode){
            editorView.setGameUpperBarView();
            editingMode =false;
        }
    }

}
