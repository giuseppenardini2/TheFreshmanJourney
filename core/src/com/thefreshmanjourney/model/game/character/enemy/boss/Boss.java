package com.thefreshmanjourney.model.game.character.enemy.boss;

import com.badlogic.gdx.utils.TimeUtils;
import com.thefreshmanjourney.model.game.character.enemy.Enemy;
import com.thefreshmanjourney.model.game.character.enemy.EnemyType;
import com.thefreshmanjourney.model.game.character.weapon.Weapon;
import com.thefreshmanjourney.model.game.map.MapBlockType;
import com.thefreshmanjourney.model.game.map.MapBlock;
import com.thefreshmanjourney.model.profile.ProfileStatistics;

/**
 * This class represents a boss in the game
 */
public abstract class Boss extends Enemy {

    /**
     * The last time this boss has changed direction
     */
    private long lastChangeDirectionTime;

    /**
     * The CFU given to player when this boss dies.
     */
    private final int cfu;

    /**
     * Creates a boss.
     *
     * @param x The x coordinate of this boss
     * @param y The y coordinate of this boss
     * @param width The boss width
     * @param height The boss height
     * @param health The boss health
     * @param speed The boss speed
     * @param weapon The boss' Weapon
     * @param killScore The boss kill score
     * @param cfu The boss CFU
     */
    public Boss(float x, float y, float width, float height, int health, float speed, Weapon weapon, int killScore, int cfu) {
        super(x, y, width, height, EnemyType.BOSS, health, speed, weapon, killScore);
        this.cfu=cfu;
    }

    @Override
    public void die(){
        if(!dead){
            currentHealth =0;
            dead=true;
            ProfileStatistics.incrementRecordedExams();
            ProfileStatistics.incrementTotalCFU(cfu);
        }
    }

    @Override
    public void act(float movement){
        walk(movement);
        if(TimeUtils.nanoTime() - lastChangeDirectionTime > 4000000000L){
            changeDirection();
            lastChangeDirectionTime=TimeUtils.nanoTime();
        }
    }

    @Override
    public boolean isWalkableBlock(MapBlock block){
        return !block.getType().equals(MapBlockType.WALL);
    }



}
