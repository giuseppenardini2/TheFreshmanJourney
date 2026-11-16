package com.thefreshmanjourney.model.game.powerups;

import com.thefreshmanjourney.model.game.character.player.Player;

/**
 * This class represents a type of power up that increments the player score by 1000.
 */
public class EnergyDrink extends PowerUp{

    /**
     * Creates a energy drink.
     *
     * @param x The x coordinate of this energy drink
     * @param y The y coordinate of this energy drink
     */
    public EnergyDrink(int x, int y) {
        super(x, y);
    }

    @Override
    public void applyEffect(Player player) {
        player.incrementScore(1000);
    }

    @Override
    public void unapplyEffect(Player player) {}

}
