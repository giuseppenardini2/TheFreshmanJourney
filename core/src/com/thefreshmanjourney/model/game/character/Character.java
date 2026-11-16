package com.thefreshmanjourney.model.game.character;

import com.thefreshmanjourney.model.game.GameObject;
import com.thefreshmanjourney.model.game.character.movement.Direction;
import com.thefreshmanjourney.model.game.character.weapon.Weapon;
import com.thefreshmanjourney.model.game.character.movement.Movable;
import com.thefreshmanjourney.model.game.map.*;

/**
 * This class represents a character in the game.
 */
public abstract class Character extends GameObject implements Movable {

    /**
     * The character width.
     */
    private final float width;

    /**
     * The character height.
     */
    private final float height;

    /**
     * The character health.
     */
    protected final int health;

    /**
     * The character current health.
     */
    protected int currentHealth;

    /**
     * The character speed.
     */
    private float speed;

    /**
     * The character's weapon.
     */
    private Weapon weapon;

    /**
     * A boolean flag indicating if character is dead.
     */
    protected boolean dead;


    /**
     * Creates a character.
     *
     * @param x The x coordinate of this character
     * @param y The y coordinate of this character
     * @param width The character width
     * @param height The character height
     * @param health The character health
     * @param speed The character speed
     * @param weapon The character's weapon
     */
    public Character(float x, float y, float width, float height, int health, float speed, Weapon weapon) {
        super(x, y);
        this.width=width;
        this.height=height;
        this.health=health;
        this.currentHealth =health;
        this.speed=speed;
        this.weapon=weapon;
        this.dead=false;
    }

    /**
     * Initializes this character.
     */
    public abstract void initialize();

    @Override
    public void move(Direction direction, float movement){
        switch(direction){
            case UP:
                y+=movement;
                break;
            case DOWN:
                y-=movement;
                break;
            case LEFT:
                x-=movement;
                break;
            case RIGHT:
                x+=movement;
                break;
        }
        this.weapon.setXY(x, y);
    }

    /**
     * Called when this character dies.
     */
    protected abstract void die();

    /**
     * Called when this character receive damage.
     *
     * @param damage The damage received
     */
    public void damaged(int damage){
        currentHealth -=damage;
        if(currentHealth <=0){
            die();
        }
    }

    /**
     * Returns if this character can walk on the map block.
     *
     * @param block The map block
     * @return The block is walkable
     */
    public boolean isWalkableBlock(MapBlock block){
        return block.getType().equals(MapBlockType.TILE) || (block.getType().equals(MapBlockType.JUNK) && !block.isVisible()) ||
                (block.getType().equals(MapBlockType.EXIT) && block.isVisible());
    }

    /**
     * Sets the x and y coordinates of this character.
     *
     * @param x The x coordinate
     * @param y The y coordinate
     */
    @Override
    public void setXY(float x, float y){
        this.x=x;
        this.y=y;
        weapon.setXY(x, y);
    }

    /**
     * Sets the x coordinate of this character.
     *
     * @param x The x coordinate
     */
    @Override
    public void setX(float x){
        this.x=x;
        weapon.setX(x);
    }

    /**
     * Sets the y coordinate of this character.
     *
     * @param y The y coordinate
     */
    @Override
    public void setY(float y){
        this.y=y;
        weapon.setY(y);
    }

    /**
     * Returns the character width.
     *
     * @return The character width
     */
    public float getWidth() {
        return width;
    }

    /**
     * Returns the character height.
     *
     * @return The character height
     */
    public float getHeight() {
        return height;
    }

    /**
     * Returns the character current health.
     *
     * @return the character current health
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Increments the actual health by the provided value.
     *
     * @param value The incremental value
     */
    public void incrementCurrentHealth(int value){
        currentHealth+=value;
    }

    /**
     * Decrements the actual health by the provided value.
     *
     * @param value The decremental value
     */
    public void decrementCurrentHealth(int value){
        currentHealth -=value;
        if(currentHealth <=0)
            die();
    }

    /**
     * Returns the character speed.
     *
     * @return The character speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Increments the speed by the provided value.
     *
     * @param value The incremental value
     */
    public void incrementSpeed(float value){
        speed+=value;
    }

    /**
     * Decrements the speed by the provided value.
     *
     * @param value The decremental value
     */
    public void decrementSpeed(float value){
        speed-=value;
    }

    /**
     * Returns the character's weapon.
     *
     * @return The character's weapon
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Returns if the character is dead.
     *
     * @return if the character is dead
     */
    public boolean isDead(){
        return dead;
    }

}
