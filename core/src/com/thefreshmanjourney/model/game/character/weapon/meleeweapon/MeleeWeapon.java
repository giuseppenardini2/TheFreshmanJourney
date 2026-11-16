package com.thefreshmanjourney.model.game.character.weapon.meleeweapon;

import com.thefreshmanjourney.model.game.character.weapon.Weapon;

/**
 * This class represents a melee weapon.
 */
public class MeleeWeapon extends Weapon {

    /**
     * Creates a melee weapon
     *
     * @param x The x coordinate of this melee weapon
     * @param y The y coordinate of this melee weapon
     * @param damage The melee weapon damage
     * @param damageArea The melee weapon damage
     */
    public MeleeWeapon(float x, float y, int damage, int damageArea) {
        super(x, y, damage, damageArea);
    }

}
