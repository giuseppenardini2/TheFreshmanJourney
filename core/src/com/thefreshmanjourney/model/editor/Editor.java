package com.thefreshmanjourney.model.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.thefreshmanjourney.model.game.course.Course;

/**
 * This class represents a level editor that allows to create a customized lesson.
 *
 * @see com.thefreshmanjourney.model.game.lesson.Lesson
 */
public class Editor {

    /**
     * A course that manages the customizable lesson.
     */
    private Course course;

    /**
     * The file that contains the customized game map.
     */
    private static final FileHandle USER_EDITOR_MAP_FILE=Gdx.files.local("files/map/user-editor-map.txt");

    /**
     * The file that contains the default editor game map.
     */
    private static final FileHandle DEFAULT_EDITOR_MAP_FILE=Gdx.files.internal("files/map/default-editor-map.txt");


    /**
     * Creates a editor with one lesson and load the default editor game map.
     */
    public Editor(){
        course =new Course(1, "Editor");
        course.setCurrentLessonId(1);
        course.loadActualLesson(DEFAULT_EDITOR_MAP_FILE);
    }

    /**
     * Saves the editor lesson game map to a file.
     */
    public void save(){
        course.saveActualLesson(USER_EDITOR_MAP_FILE);
    }

    /**
     * Uploads the editor lesson game map from a file.
     */
    public void load(){
        if(USER_EDITOR_MAP_FILE.exists())
            course.loadActualLesson(USER_EDITOR_MAP_FILE);
        else
            course.loadActualLesson(DEFAULT_EDITOR_MAP_FILE);
    }

    /**
     * Resets the editor lesson game map.
     */
    public void reset(){
        course.loadActualLesson(DEFAULT_EDITOR_MAP_FILE);
        course.saveActualLesson(USER_EDITOR_MAP_FILE);
    }

    /**
     * Returns the editor course.
     *
     * @return The editor course
     */
    public Course getCourse() {
        return course;
    }

}
