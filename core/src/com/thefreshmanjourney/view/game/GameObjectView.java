package com.thefreshmanjourney.view.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.thefreshmanjourney.view.types.SoundType;

/**
 * This class represents the view of a game object.
 */
public abstract class GameObjectView {

    /**
     * The width of this game object expressed in world units
     */
    protected final float width;

    /**
     * The height of this game object expressed in world units
     */
    protected final float height;

    /**
     * A boolean flag indicating if the game object view is visible.
     */
    protected boolean visible;

    /**
     * An invisible rectangle used for real-time collision detection.
     */
    protected Rectangle boundingBox;

    /**
     * The sound of this game object view.
     */
    private Sound sound;

    /**
     * A boolean flag indicating if the game object view sound has been played.
     */
    protected boolean soundPlayed;

    /**
     * Creates a game object view.
     *
     * @param x The x coordinate of this game object view
     * @param y The y coordinate of this game object view
     * @param width The width of this game object view
     * @param height The height of this game object view
     */
    protected GameObjectView(float x, float y, float width, float height){
        boundingBox=new Rectangle(x, y, width, height);
        this.width=width;
        this.height=height;
        visible=true;
        sound=null;
        soundPlayed=false;
    }
    /**
     * Creates a game object view with a sound.
     *
     * @param x The x coordinate of this game object view
     * @param y The y coordinate of this game object view
     * @param width The width of this game object view
     * @param height The height of this game object view
     * @param soundType The sound of this game object view
     */
    protected GameObjectView(float x, float y, float width, float height, SoundType soundType){
        boundingBox=new Rectangle(x, y, width, height);
        this.width=width;
        this.height=height;
        visible=true;
        sound=soundType.getSound();
        soundPlayed=false;
    }

    /**
     * Returns the width.
     *
     * @return The width
     */
    public float getWidth() {
        return width;
    }

    /**
     * Returns the width.
     *
     * @return The width
     */
    public float getHeight() {
        return height;
    }

    /**
     * Sets the x coordinate of this game object view.
     *
     * @param x The x coordinate
     */
    public void setX(float x){
        boundingBox.x=x;
    }

    /**
     * Increments the x coordinate of this game object view.
     *
     * @param value The incremental value
     */
    public void incrementX(float value){
        boundingBox.x+=value;
    }

    /**
     * Decrements the x coordinate of this game object view.
     *
     * @param value The decremental value
     */
    public void decrementX(float value){
        boundingBox.x-=value;
    }

    /**
     * Returns the x coordinate of this game object view.
     *
     * @return The x coordinate
     */
    public float getX(){
        return boundingBox.x;
    }

    /**
     * Sets the y coordinate of this game object view.
     *
     * @param y The y coordinate
     */
    public void setY(float y){
        boundingBox.y=y;
    }

    /**
     * Increments the y coordinate of this game object view.
     *
     * @param value The incremental value
     */
    public void incrementY(float value){
        boundingBox.y+=value;
    }

    /**
     * Decrements the y coordinate of this game object view.
     *
     * @param value The decremental value
     */
    public void decrementY(float value){
        boundingBox.y-=value;
    }

    /**
     * Returns the y coordinate of this game object view.
     *
     * @return The y coordinate
     */
    public float getY(){
        return boundingBox.y;
    }

    /**
     * Sets the x and y coordinates of this game object view.
     *
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public void setXY(float x, float y){
        boundingBox.x=x;
        boundingBox.y=y;
    }

    /**
     * Returns the bounding box.
     *
     * @return bounding box
     */
    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    /**
     * Returns if this game object view is visible.
     *
     * @return If this game object view is visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets if this game object view is visible.
     *
     * @param visible If this game object view is visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Returns the game object view sound.
     *
     * @return The game object view sound
     */
    public Sound getSound() {
        return sound;
    }

    /**
     * Returns if the game object view sound has been played.
     *
     * @return If the game object view sound has been played
     */
    public boolean isSoundPlayed() {
        return soundPlayed;
    }

    /**
     * Sets the game object view sound has been played.
     */
    public void setSoundPlayed(){
        soundPlayed=true;
    }
}
