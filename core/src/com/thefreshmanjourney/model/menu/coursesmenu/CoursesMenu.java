package com.thefreshmanjourney.model.menu.coursesmenu;

import com.badlogic.gdx.utils.Array;
import com.thefreshmanjourney.model.game.course.CourseType;
import com.thefreshmanjourney.model.game.course.Course;

/**
 * This class represents the courses menu that manages the courses of the game.
 */
public class CoursesMenu {

    /**
     * The courses.
     */
    private Array<Course> courses;


    /**
     * Creates a courses menu.
     */
    public CoursesMenu() {
        initialize();
    }

    /**
     * Initializes this courses menu.
     */
    private void initialize(){
        courses = new Array<>();
        for(CourseType courseEnum: CourseType.values()) {
            Course course =new Course(courseEnum.getLessonsAmount(), courseEnum.getName());
            courses.add(course);
        }
    }

    /**
     * Returns the courses.
     * @return The courses
     */
    public Array<Course> getCourses() {
        return courses;
    }

}