package com.thefreshmanjourney.model.game.course;

/**
 * This enum represents the courses of the game.
 */
public enum CourseType {

    /**
     * The singleton computer science course.
     */
    COMPUTER_SCIENCE("Informatica", 8);

    /**
     * The course name.
     */
    private String name;

    /**
     * The course lessons amount.
     */
    private int lessonsAmount;


    /**
     * Creates a course type.
     *
     * @param name The course name.
     * @param lessonsAmount The course lessons amount
     */
    CourseType(String name, int lessonsAmount){
        this.name=name;
        this.lessonsAmount = lessonsAmount;
    }

    /**
     * Returns the course name.
     *
     * @return The course name
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the lessons amount
     *
     * @return The lessons amount
     */
    public int getLessonsAmount() {
        return lessonsAmount;
    }
}
