package com.thefreshmanjourney.view.game.upperbar;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This class represents the upper bar view of a lesson.
 */
public class GameUpperBarView extends UpperBarView {

    /**
     * The player score.
     */
    private int score;

    /**
     * The player health.
     */
    private int health;

    /**
     * Creates a game upper bar view.
     *
     * @param x The x coordinate of this game upper bar view
     * @param y The y coordinate of this game upper bar view
     */
    public GameUpperBarView(float x, float y) {
        super(x, y);
        health=3;
        score=0;
    }

    @Override
    public void draw(SpriteBatch spriteBatch){
        spriteBatch.draw(texture, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        font.draw(spriteBatch, "Health: "+health, 4f, 12.32f + font.getCapHeight());
        font.draw(spriteBatch, "Score: "+score, 9f, 12.32f + font.getCapHeight());
    }


    /**
     * Sets the player score.
     *
     * @param score The new player score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Sets the player health.
     *
     * @param health The new player health
     */
    public void setHealth(int health) {
        this.health = health;
    }

}
