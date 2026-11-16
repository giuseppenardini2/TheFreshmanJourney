package com.thefreshmanjourney.model.game;

/**
 * This class represents a game object.
 */
public abstract class GameObject {

    /**
     * The x coordinate of this game object.
     */
    protected float x;

    /**
     * The y coordinate of this game object.
     */
    protected float y;


    /**
     * Creates a game object.
     *
     * @param x The x coordinate of this game object
     * @param y The y coordinate of this game object
     */
    public GameObject(float x, float y){
        this.x=x;
        this.y=y;
    }

    /**
     * Returns the x coordinate of this game object
     *
     * @return The x coordinate of this game object
     */
    public float getX() {
        return x;
    }

    /**
     * Sets the x coordinate of this game object
     *
     * @param x The x coordinate of this game object
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Returns the y coordinate of this game object
     *
     * @return The y coordinate of this game object
     */
    public float getY() {
        return y;
    }

    /**
     * Sets the y coordinate of this game object
     *
     * @param y The x coordinate of this game object
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Sets the x and y coordinate of this game object
     *
     * @param x The x coordinate of this game object
     * @param y The y coordinate of this game object
     */
    public void setXY(float x, float y){
        this.x=x;
        this.y=y;
    }

}
