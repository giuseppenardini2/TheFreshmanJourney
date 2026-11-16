package com.thefreshmanjourney.model.game.powerups;

import com.thefreshmanjourney.model.game.GameObject;
import com.thefreshmanjourney.model.game.character.player.Player;

/**
 * This class represents the power up in the game.
 */
public abstract class PowerUp extends GameObject {

    /**
     * A boolean flag indicating if this power up has been used.
     */
    private boolean used;


    /**
     * Creates a power up.
     *
     * @param x The x coordinate of this power up
     * @param y The y coordinate of this power up
     */
    public PowerUp(int x, int y) {
        super(x, y);
        this.used= false;
    }

    /**
     * Applies the power up effect to the player.
     *
     * @param player The player
     */
    public abstract void applyEffect(Player player);

    /**
     * Removes the power up effect from the player.
     *
     * @param player The player
     */
    public abstract void unapplyEffect(Player player);


    /**
     * Returns if this power up has been used.
     *
     * @return If this power up has been used
     */
    public boolean isUsed(){
        return used;
    }

    /**
     * Sets this power up used.
     */
    public void setUsed(){
        used=true;
    }

}
