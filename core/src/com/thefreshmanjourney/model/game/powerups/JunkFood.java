package com.thefreshmanjourney.model.game.powerups;

import com.thefreshmanjourney.model.game.character.player.Player;

/**
 * This class represents a type of power up that decrements the player score by 500.
 */
public class JunkFood extends PowerUp{

    /**
     * Creates a junk food.
     *
     * @param x The x coordinate of this junk food
     * @param y The y coordinate of this junk food
     */
    public JunkFood(int x, int y) {
        super(x, y);
    }

    @Override
    public void applyEffect(Player player) {
       player.decrementScore(500);
    }

    @Override
    public void unapplyEffect(Player player) {}

}
