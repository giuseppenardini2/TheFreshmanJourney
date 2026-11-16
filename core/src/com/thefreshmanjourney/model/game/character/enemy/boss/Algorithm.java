package com.thefreshmanjourney.model.game.character.enemy.boss;

import com.thefreshmanjourney.model.game.character.weapon.meleeweapon.MeleeWeapon;

/**
 * This class represents a type of boss that moves fast and has sufficient health.
 */
public class Algorithm extends Boss {

    /**
     * Creates a algorithm.
     *
     * @param x The x coordinate of this algorithm
     * @param y The y coordinate of this algorithm
     */
    public Algorithm(float x, float y) {
        super(x, y, 2f, 2.5f, 10, 1.2f, new MeleeWeapon(x, y, 1, 2), 1500, 6);
    }

}
