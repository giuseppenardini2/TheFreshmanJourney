package com.thefreshmanjourney.model.game.powerups;

import com.thefreshmanjourney.model.game.character.player.Player;

/**
 * This class represents a type of power up that increments the player health by 1.
 */
public class MoreHeart extends PowerUp{

    /**
     * Creates a more heart.
     * @param x The x coordinate of this more heart
     * @param y The y coordinate of this more heart
     */
    public MoreHeart(int x, int y) {
        super(x, y);
    }

    @Override
    public void applyEffect(Player player) {
        player.incrementCurrentHealth(1);
    }

    @Override
    public void unapplyEffect(Player player) {}

}
