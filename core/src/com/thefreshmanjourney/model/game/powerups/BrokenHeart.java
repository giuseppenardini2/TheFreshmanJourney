package com.thefreshmanjourney.model.game.powerups;

import com.thefreshmanjourney.model.game.character.player.Player;

/**
 * This class represents a type of power up that decrements the player health by 1.
 */
public class BrokenHeart extends PowerUp{

    /**
     * Creates a broken heart.
     *
     * @param x The x coordinate of this broken heart
     * @param y The y coordinate of this broken heart
     */
    public BrokenHeart(int x, int y) {
        super(x, y);
    }

    @Override
    public void applyEffect(Player player) {
        player.decrementCurrentHealth(1);
    }

    @Override
    public void unapplyEffect(Player player) {}

}
