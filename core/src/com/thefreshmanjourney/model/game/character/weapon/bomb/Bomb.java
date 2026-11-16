package com.thefreshmanjourney.model.game.character.weapon.bomb;

import com.badlogic.gdx.utils.TimeUtils;
import com.thefreshmanjourney.model.game.character.weapon.Weapon;

/**
 * This class represents a bomb.
 */
public class Bomb extends Weapon {

    /**
     * The bomb launcher that manages this bomb.
     */
    private BombLauncher bombLauncher;

    /**
     * The time before this bomb explodes after it has been dropped.
     */
    private long explosionTime;

    /**
     * The time this bomb has been dropped.
     */
    private long dropTime;

    /**
     * A boolean flag indicating if this bomb has been defused.
     */
    private boolean defused;

    /**
     * A boolean flag indicating if this bomb has exploded.
     */
    private boolean exploded;

    /**
     * Creates a bomb.
     *
     * @param x The x coordinate of this bomb
     * @param y The y coordinate of this bomb
     * @param damage The bomb damage
     * @param damageArea The bomb damage area
     * @param explosionTime The bomb explosion time
     * @param dropTime The bomb drop time
     * @param bombLauncher The bomb launcher
     */
    public Bomb(float x, float y, int damage, int damageArea, long explosionTime, long dropTime, BombLauncher bombLauncher) {
        super(x, y, damage, damageArea);
        this.explosionTime=explosionTime;
        this.dropTime=dropTime;
        this.bombLauncher=bombLauncher;
        defused =false;
        exploded=false;
    }

    /**
     * Called when this bomb has been dropped
     */
    public void dropped(){
        if(dropTime+explosionTime<=TimeUtils.nanoTime()){
            exploded=true;
        }
    }


    /**
     * Defuses this bomb.
     */
    public void defuse(){
        defused =true;
    }

    /**
     * Returns if the bomb has been defused.
     *
     * @return If the bomb has been defused
     */
    public boolean isDefused(){
        return defused;
    }

    /**
     * Returns if the bomb has exploded.
     *
     * @return If the bomb has exploded
     */
    public boolean isExploded() {
        return exploded;
    }
}
