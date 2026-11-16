package com.thefreshmanjourney.model.game.character.player;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.thefreshmanjourney.model.game.character.Character;
import com.thefreshmanjourney.model.game.character.weapon.bomb.BombLauncher;
import com.thefreshmanjourney.model.game.powerups.PowerUp;
import com.thefreshmanjourney.model.profile.ProfileStatistics;

/**
 * This class represents the player in the game.
 */
public class Player extends Character {

    /**
     * An instance of player for singleton.
     */
    private static Player instance;

    /**
     * A boolean flag indicating if this player has the shield.
     */
    private boolean shield;

    /**
     * The score.
     */
    private int score;

    /**
     * An array of power ups.
     */
    private Array<PowerUp> powerUps;

    /**
     * A boolean flag indicating if the user can control this player.
     */
    private boolean controllable;

    /**
     * The last time this player has been not controllable.
     */
    private long lastControllableTime;


    /**
     * Creates a player.
     *
     * @param x The x coordinate of this player
     * @param y The y coordinate of this player
     */
    private Player(float x, float y) {
        super(x, y, 0.6f, 0.8f, 3, 2f, new BombLauncher(x, y));
        shield=false;
        score=0;
        powerUps=new Array<>();
        controllable=false;
        lastControllableTime=TimeUtils.nanoTime();
    }

    /**
     * Returns this player instance.
     *
     * @param x The x coordinate of this player
     * @param y The y coordinate of this player
     * @return This player instance
     */
    public static Player getInstance(float x, float y) {
        if(instance==null)
            instance=new Player(x, y);
        instance.setXY(x, y);
        return instance;
    }

    @Override
    public void initialize(){
        currentHealth =health;
        dead=false;
        shield=false;
        score=0;
        removePowerUps();
        controllable=false;
        lastControllableTime=TimeUtils.nanoTime();
        ((BombLauncher)getWeapon()).initializeBombLauncher();
    }

    @Override
    protected void die(){
        if(!dead){
            currentHealth =0;
            dead=true;
            ProfileStatistics.incrementDeaths();
            ProfileStatistics.incrementLessonsQuit();
        }
    }

    @Override
    public void damaged(int damage){
        if(shield)
            shield=false;
        else {
            super.damaged(damage);
            removePowerUps();
        }
    }

    /**
     * Updates if this player is controllable.
     */
    public void updateControllable(){
        if(TimeUtils.nanoTime()-lastControllableTime > 1000000000L){
            controllable=true;
            lastControllableTime= TimeUtils.nanoTime();
        }
    }


    /**
     * Returns if the character has the shield.
     *
     * @param shield if character has shield
     */
    public void setShield(boolean shield) {
        this.shield=shield;
    }

    /**
     * Returns the player score.
     *
     * @return The player score
     */
    public int getScore() {
        return score;
    }


    /**
     * Increments the character score by the provided value.
     *
     * @param value The incremental value
     */
    public void incrementScore(int value){
        score+=value;
    }

    /**
     * Decrements the character score by the provided value.
     *
     * @param value The decremental value
     */
    public void decrementScore(int value){
        score-=value;
        if(score<0)
            score=0;
    }

    /**
     * Adds the power up to power ups array.
     *
     * @param powerUp The power up to add
     */
    public void addPowerUp(PowerUp powerUp){
        powerUp.applyEffect(this);
        powerUps.add(powerUp);
    }

    /**
     * Removes all the power ups effects from this player.
     */
    private void removePowerUpsEffects(){
        for(PowerUp powerUp:powerUps)
            powerUp.unapplyEffect(this);
    }

    /**
     * Removes all the power ups from power up array.
     */
    public void removePowerUps(){
        removePowerUpsEffects();
        powerUps.clear();
    }

    /**
     * Returns if this character is controllable.
     *
     * @return If this character is controllable
     */
    public boolean isControllable() {
        return controllable;
    }

    /**
     * Sets this character not controllable.
     */
    public void setNotControllable() {
        this.controllable=false;
    }

}
