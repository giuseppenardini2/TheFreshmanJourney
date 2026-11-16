package com.thefreshmanjourney.model.game.powerups;

import com.thefreshmanjourney.model.game.character.player.Player;

/**
 * This class represents a type of power up that decrements the player speed.
 */
public class Pillow extends PowerUp{

    public Pillow(int x, int y) {
        super(x, y);
    }

    @Override
    public void applyEffect(Player player) {
        player.decrementSpeed(0.5f);
    }

    @Override
    public void unapplyEffect(Player player) {
        player.incrementSpeed(0.5f);
    }

}
