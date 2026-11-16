package com.thefreshmanjourney.model.game.powerups;

import com.thefreshmanjourney.model.game.character.player.Player;
import com.thefreshmanjourney.model.game.character.weapon.bomb.BombLauncher;

/**
 * This class represents a type of power up that increments the player coffee by 1.
 */
public class MoreCoffee extends PowerUp{

    /**
     * Creates a more coffee.
     * @param x The x coordinate of this more coffee
     * @param y The y coordinate of this more coffee
     */
    public MoreCoffee(int x, int y) {
        super(x, y);
    }

    @Override
    public void applyEffect(Player player) {
        ((BombLauncher)(player.getWeapon())).incrementBombsAmount(1);
    }

    @Override
    public void unapplyEffect(Player player) {
        ((BombLauncher)(player.getWeapon())).decrementBombsAmount(1);
    }

}
