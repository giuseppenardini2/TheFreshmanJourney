package com.thefreshmanjourney.view.editor;

import com.thefreshmanjourney.view.game.lesson.LessonView;
import com.thefreshmanjourney.view.game.upperbar.EditorUpperBarView;
import com.thefreshmanjourney.view.game.upperbar.GameUpperBarView;

/**
 * This class represents the editor view.
 */
public class EditorView {

    /**
     * A lesson view.
     */
    private LessonView lessonView;

    /**
     * A game upper bar view.
     */
    private GameUpperBarView gameUpperBarView;

    /**
     * A editor upper bar view.
     */
    private EditorUpperBarView editorUpperBarView;


    /**
     * Creates a editor view.
     *
     * @param lessonView The lesson view
     */
    public EditorView(LessonView lessonView){
        this.lessonView=lessonView;
        gameUpperBarView=new GameUpperBarView(0f, lessonView.getHeight()-1);
        editorUpperBarView=new EditorUpperBarView(0f, lessonView.getHeight()-1);
    }

    /**
     * Called when the screen becomes the current screen for a Game.
     */
    public void show(){
        lessonView.show();
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render
     */
    public void render(float delta){
        lessonView.render(delta);
    }


    /**
     * Returns the lesson view.
     *
     * @return The lesson view
     */
    public LessonView getLessonView() {
        return lessonView;
    }

    /**
     * Sets the game upper bar view as upper bar of the lesson view
     */
    public void setGameUpperBarView(){
        lessonView.setUpperBar(gameUpperBarView);
    }

    /**
     * Sets the editor upper bar view as upper bar of the lesson view
     */
    public void setEditorUpperBarView(){
        lessonView.setUpperBar(editorUpperBarView);
    }

}
