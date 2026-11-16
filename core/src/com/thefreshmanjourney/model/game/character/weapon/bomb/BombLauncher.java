package com.thefreshmanjourney.model.game.character.weapon.bomb;

import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.TimeUtils;
import com.thefreshmanjourney.model.game.character.weapon.Weapon;

/**
 * This class represents a bomb launcher that manages bombs.
 */
public class BombLauncher extends Weapon {

    /**
     * The bombs amount.
     */
    private int bombsAmount;

    /**
     * The active bombs in the game map.
     */
    private ObjectSet<Bomb> activeBombs;

    /**
     * The last time a bomb was dropped.
     */
    private long lastDropTime;


    /**
     * Creates a bomb launcher.
     *
     * @param x The x coordinate of this bomb launcher
     * @param y The y coordinate of this bomb launcher
     */
    public BombLauncher(float x, float y) {
        super(x, y, 0, 0);
        bombsAmount =2;
        activeBombs=new ObjectSet<>();
    }

    /**
     * Initializes this bomb launcher.
     */
    public void initializeBombLauncher(){
        bombsAmount =2;
        activeBombs=new ObjectSet<>();
    }

    /**
     * Drops a bomb.
     */
    public void dropTheCoffee(){
        if(activeBombs.size< bombsAmount && TimeUtils.nanoTime()-lastDropTime>90000000L){
            Bomb bomb=new Bomb(x, y, 1, 1, 1500000000L, TimeUtils.nanoTime() , this);
            if(isActiveBomb(bomb))
                bomb.setY(bomb.getY()+0.005f);
            bomb.dropped();
            activeBombs.add(bomb);
            lastDropTime=TimeUtils.nanoTime();
        }
    }

    /**
     * Returns if a bomb is an active bomb.
     *
     * @param bombToCheck The bomb to check
     * @return If the bomb is active
     */
    private boolean isActiveBomb(Bomb bombToCheck){
        for(Bomb bomb:activeBombs)
            if(bomb.getX()==bombToCheck.getX() && bomb.getY()==bombToCheck.getY())
                return true;
        return false;
    }

    /**
     * Returns the active bomb corresponding to the provided coordinates, otherwise null.
     *
     * @param x The x coordinate of the active bomb
     * @param y The y coordinate of the active bomb
     * @return The bomb to find, otherwise null
     */
    public Bomb findActiveBomb(float x, float y){
        for(Bomb bomb:activeBombs)
            if(bomb.getX()==x && bomb.getY()==y)
                return bomb;
        return null;
    }


    /**
     * Increments the bombs amount.
     *
     * @param value The incremental value
     */
    public void incrementBombsAmount(int value){
        bombsAmount+=value;
    }

    /**
     * Decrements the bombs amount.
     *
     * @param value The decremental value
     */
    public void decrementBombsAmount(int value){
        bombsAmount-=value;
    }

    /**
     * Returns the active bombs.
     *
     * @return The active bombs
     */
    public ObjectSet<Bomb> getActiveBombs(){
        return activeBombs;
    }

}
