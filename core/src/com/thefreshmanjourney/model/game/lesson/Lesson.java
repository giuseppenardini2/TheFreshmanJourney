package com.thefreshmanjourney.model.game.lesson;

import com.thefreshmanjourney.model.game.map.GameMap;
import com.thefreshmanjourney.model.profile.ProfileStatistics;

import java.util.Observable;

/**
 * This class represents a level of the game.
 */
public class Lesson extends Observable {

    /**
     * The lesson game map.
     */
    private GameMap gameMap;

    /**
     * A boolean flag indicating if the lesson is finished.
     */
    private boolean finished;

    /**
     * A boolean flag indicating if player has completed the lesson.
     */
    private boolean completed;


    /**
     * Creates a lesson.
     */
    public Lesson(){
        gameMap=new GameMap();
        finished=false;
        completed=false;
    }

    /**
     * Initializes this lesson.
     */
    public void initializeLesson(){
        finished=false;
        completed=false;
    }

    /**
     * Checks if this lesson is finished.
     */
    public void checkEndLesson(){
        if(completed || gameMap.getPlayer().isDead())
            setFinished();
    }

    /**
     * Returns if the player has won.
     *
     * @return If the player has won.
     */
    public boolean hasPlayerWon(){
        return completed && !gameMap.getPlayer().isDead();
    }


    /**
     * Sets this lesson finished.
     */
    private void setFinished(){
        finished=true;
        super.setChanged();
        super.notifyObservers(finished);
    }

    /**
     * Returns if this lesson is finished.
     *
     * @return If this lesson is finished
     */
    public boolean isFinished(){
        return finished;
    }

    /**
     * Sets this lesson completed.
     */
    public void setCompleted(){
        if(!completed){
            completed=true;
            ProfileStatistics.incrementLessonsCompleted();
        }
    }

    /**
     * Returns the lesson game map.
     *
     * @return The lesson game map
     */
    public GameMap getGameMap() {
        return gameMap;
    }

}
