package com.thefreshmanjourney.model.game.character.weapon.gun;

import com.thefreshmanjourney.model.game.character.weapon.Weapon;

/**
 * This class represents a gun.
 */
public class Gun extends Weapon {

    /**
     * The gun range.
     */
    private int range;

    /**
     * Creates a gun.
     *
     * @param x The x coordinate of this gun
     * @param y The y coordinate of this gun
     * @param damage The gun damage
     * @param range The gun range
     * @param damageArea The gun damage area
     */
    public Gun(float x, float y, int damage, int range, int damageArea) {
        super(x, y, damage, damageArea);
        this.range=range;
    }

    /**
     * Returns the gun range.
     *
     * @return The gun range
     */
    public int getRange() {
        return range;
    }

}
