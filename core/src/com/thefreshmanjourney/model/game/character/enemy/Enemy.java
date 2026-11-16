package com.thefreshmanjourney.model.game.character.enemy;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.thefreshmanjourney.model.game.character.Character;
import com.thefreshmanjourney.model.game.character.weapon.Weapon;
import com.thefreshmanjourney.model.game.character.movement.Direction;
import com.thefreshmanjourney.model.profile.ProfileStatistics;

/**
 * This class represents a enemy in the game.
 */
public abstract class Enemy extends Character {

    /**
     * The enemy type.
     */
    private final EnemyType enemyType;

    /**
     * The current direction of this enemy.
     */
    protected Direction currentDirection;

    /**
     * The last time this enemy has attacked.
     */
    protected long lastAttackTime;

    /**
     * A boolean flag indicating if this enemy can cause damage.
     */
    private boolean harmful;

    /**
     * The score given to the player when this enemy dies.
     */
    private final int killScore;

    /**
     * A boolean flag indicating if the enemy score has been given.
     */
    private boolean killScoreGiven;


    /**
     * Creates a enemy.
     *
     * @param x The x coordinate of this enemy
     * @param y The y coordinate of this enemy
     * @param width The enemy width
     * @param height The enemy height
     * @param enemyType The enemy type
     * @param health The enemy health
     * @param speed The enemy speed
     * @param weapon The enemy's weapon
     * @param killScore The kill score
     */
    public Enemy(float x, float y, float width, float height, EnemyType enemyType, int health, float speed, Weapon weapon, int killScore) {
        super(x, y, width, height, health, speed, weapon);
        this.enemyType=enemyType;
        currentDirection =Direction.RIGHT;
        this.killScore=killScore;
        harmful=true;
        killScoreGiven =false;
    }

    /**
     * Initializes this enemy.
     */
    @Override
    public void initialize(){
        currentHealth =health;
        dead=false;
        harmful=true;
        killScoreGiven =false;
    }

    /**
     * Allows the enemy to behave in the game.
     *
     * @param movement The movement units
     */
    public abstract void act(float movement);

    @Override
    protected void die(){
        if(!dead){
            currentHealth =0;
            dead=true;
            ProfileStatistics.incrementDefeatedStudents();
        }
    }

    /**
     * Moves this enemy in the current direction.
     *
     * @param movement The movement units
     */
    protected void walk(float movement){
        super.move(currentDirection, movement);
    }

    /**
     * Changes the current direction of this enemy.
     */
    public void changeDirection(){
        Direction[] directions=Direction.values();
        Array<Direction> possibleDirections=new Array<>();
        for(Direction direction:directions)
            if(direction!=this.currentDirection)
                possibleDirections.add(direction);
        currentDirection =possibleDirections.random();

    }

    /**
     * Updates if this enemy is harmful.
     */
    public void updateHarmful(){
        if(TimeUtils.nanoTime() - lastAttackTime > 1200000000L){
            harmful=true;
            lastAttackTime=TimeUtils.nanoTime();
        }
    }


    /**
     * Returns the enemy current direction.
     *
     * @return The enemy current direction
     */
    public Direction getCurrentDirection() {
        return currentDirection;
    }

    /**
     * Returns if this enemy is a boss.
     *
     * @return If this enemy is a boss
     */
    public boolean isBoss(){
        return enemyType.equals(EnemyType.BOSS);
    }

    /**
     * Returns the enemy kill score.
     *
     * @return The enemy kill score
     */
    public int getKillScore(){
        return killScore;
    }

    /**
     * Returns if this enemy is harmful.
     *
     * @return If this enemy is harmful
     */
    public boolean isHarmful() {
        return harmful;
    }

    /**
     * Sets this enemy not harmful.
     */
    public void setNotHarmful() {
        this.harmful=false;
    }

    /**
     * Returns if the kill score has been given.
     *
     * @return If the kill score has been given
     */
    public boolean wasKillScoreGiven() {
        return killScoreGiven;
    }

    /**
     * Sets the kill score given.
     */
    public void setKillScoreGiven() {
        this.killScoreGiven =true;
    }

}
