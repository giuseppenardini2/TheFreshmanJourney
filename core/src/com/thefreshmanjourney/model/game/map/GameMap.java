package com.thefreshmanjourney.model.game.map;

import com.badlogic.gdx.utils.Array;
import com.thefreshmanjourney.model.game.character.enemy.Enemy;
import com.thefreshmanjourney.model.game.character.player.Player;
import com.thefreshmanjourney.model.game.powerups.PowerUp;

/**
 * This class represents the map of the game that contains the player, enemies and power ups.
 */
public class GameMap {

    /**
     * The map matrix.
     */
    private MapBlock[][] map;

    /**
     * The map width.
     */
    public static final int WIDTH = 15;

    /**
     * The map height.
     */
    public static final int HEIGHT = 13;

    /**
     * The player in this game map.
     */
    private Player player;

    /**
     * The enemies in this game map.
     */
    private Array<Enemy> enemies;

    /**
     * The power ups in this game map.
     */
    private Array<PowerUp> powerUps;


    /**
     * Creates a game map.
     */
    public GameMap(){
        map=new MapBlock[HEIGHT][WIDTH];
        enemies=new Array<>();
        powerUps=new Array<>();
        player=null;
    }

    /**
     * Return if the x coordinate is valid for this game map.
     *
     * @param x The x coordinate
     * @return If the x coordinate is valid
     */
    public static boolean checkX(int x){
        return x>=0 && x<WIDTH;
    }

    /**
     * Return if the y coordinate is valid for this game map.
     *
     * @param y The y coordinate
     * @return If the y coordinate is valid
     */
    public static boolean checkY(int y){
        return y>=0 && y<HEIGHT;
    }

    /**
     * Return if the x and y coordinates are valid for this game map.
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @return If the x and y coordinates are valid
     */
    public static boolean checkXY(int x, int y){
        return checkX(x) && checkY(y);
    }

    /**
     * Returns the enemy in this game map corresponding to the provided coordinates, otherwise null.
     *
     * @param x The x coordinate of the enemy
     * @param y The y coordinate of the enemy
     * @return The enemy to find, otherwise null
     */
    public Enemy findEnemy(float x, float y){
        for(Enemy enemy: enemies){
           if(enemy.getX()==x && enemy.getY()==y)
               return enemy;
        }
        return null;
    }

    /**
     * Returns the power up in this game map corresponding to the provided coordinates, otherwise null.
     *
     * @param x The x coordinate of the power up
     * @param y The y coordinate of the power up
     * @return The power up to find, otherwise null
     */
    public PowerUp findPowerUp(float x, float y){
        for(PowerUp powerUp:powerUps){
            if((int) powerUp.getX()==(int) x && (int) powerUp.getY()== (int) y)
                return powerUp;
        }
        return null;
    }

    /**
     * Returns the map matrix.
     *
     * @return The map matrix
     */
    public MapBlock[][] getMap() {
        return map;
    }

    /**
     * Sets the provided map block in this game map.
     *
     * @param newBlock The new map block
     */
    public void setBlock(MapBlock newBlock){
        map[(int) newBlock.getY()][(int) newBlock.getX()]=newBlock;
    }

    /**
     * Returns the map block corresponding to the provided coordinates.
     *
     * @param x The x coordinate of the map block
     * @param y The y coordinate of the map block
     * @return The map block
     */
    public MapBlock getBlock(int x, int y){
        return map[y][x];
    }

    /**
     *
     *
     * @param player
     */
    public void setPlayer(Player player) {
        this.player=player;
    }

    /**
     * Returns the player.
     *
     * @return The player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Adds the enemy in this game map.
     *
     * @param enemy The enemy to add
     */
    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    /**
     * Returns the enemies.
     *
     * @return The enemies
     */
    public Array<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Adds the power up in this game map.
     *
     * @param powerUp The power up to add
     */
    public void addPowerUp(PowerUp powerUp){
        powerUps.add(powerUp);
    }

    /**
     * Returns the power ups.
     *
     * @return The power ups
     */
    public Array<PowerUp> getPowerUps(){
        return powerUps;
    }

}