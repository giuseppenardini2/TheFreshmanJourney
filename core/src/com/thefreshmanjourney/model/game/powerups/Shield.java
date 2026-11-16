package com.thefreshmanjourney.model.game.powerups;

import com.thefreshmanjourney.model.game.character.player.Player;

/**
 * This class represents a type of power up that allows the player to avoid taking damage from one hit.
 */
public class Shield extends PowerUp{

    /**
     * Creates a shield.
     *
     * @param x The x coordinate of this shield
     * @param y The y coordinate of this shield
     */
    public Shield(int x, int y){
        super(x, y);
    }

    @Override
    public void applyEffect(Player player) {
        player.setShield(true);
    }

    @Override
    public void unapplyEffect(Player player) {
        player.setShield(false);
    }

}
