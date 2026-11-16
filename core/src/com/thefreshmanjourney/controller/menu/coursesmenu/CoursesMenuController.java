package com.thefreshmanjourney.controller.menu.coursesmenu;

import com.badlogic.gdx.Screen;
import com.thefreshmanjourney.model.menu.coursesmenu.CoursesMenu;
import com.thefreshmanjourney.view.menu.coursesmenu.CoursesMenuView;

/**
 * This class represents the courses menu controller.
 */
public class CoursesMenuController implements Screen {

    /**
     * An instance of this courses menu controller for singleton.
     */
    private static CoursesMenuController instance;

    /**
     * A courses menu view.
     */
    private CoursesMenuView coursesMenuView;

    /**
     * A courses menu model.
     */
    private CoursesMenu coursesMenu;


    /**
     * Creates a courses menu controller.
     */
    private CoursesMenuController() {
        coursesMenu =new CoursesMenu();
        coursesMenuView =new CoursesMenuView(coursesMenu.getCourses());
    }

    /**
     * Returns this courses menu controller instance.
     * @return This courses menu controller instance
     */
    public static CoursesMenuController getInstance() {
        if(instance==null)
            instance = new CoursesMenuController();
        return instance;
    }

    @Override
    public void show() {
        coursesMenuView.show();
    }

    @Override
    public void render(float delta) {
        coursesMenuView.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        coursesMenuView.resize(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        coursesMenuView.dispose();
    }

    /**
     * Returns the courses menu model.
     *
     * @return The courses menu model
     */
    public CoursesMenu getCoursesMenuModel() {
        return coursesMenu;
    }

    /**
     * Returns the courses menu view.
     *
     * @return The courses menu view
     */
    public CoursesMenuView getCoursesMenuView() {
        return coursesMenuView;
    }

}
