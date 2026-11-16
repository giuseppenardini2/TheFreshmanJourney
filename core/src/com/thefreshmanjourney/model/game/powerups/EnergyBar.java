package com.thefreshmanjourney.model.game.powerups;

import com.thefreshmanjourney.model.game.character.player.Player;

/**
 * This class represents a type of power up that increments the player score by 500.
 */
public class EnergyBar extends PowerUp{

    /**
     * Creates a energy bar.
     *
     * @param x The x coordinate of this energy bar
     * @param y The y coordinate of this energy bar
     */
    public EnergyBar(int x, int y) {
        super(x, y);
    }

    @Override
    public void applyEffect(Player player) {
        player.incrementScore(500);
    }

    @Override
    public void unapplyEffect(Player player) {}

}
