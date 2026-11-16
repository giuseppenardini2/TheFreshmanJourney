package com.thefreshmanjourney.model.game.character.enemy;

import com.thefreshmanjourney.model.game.character.weapon.meleeweapon.MeleeWeapon;

/**
 * This class represents a type of enemy that moves fast but has low health.
 */
public class Homebody extends Enemy{

    /**
     * Creates a homebody.
     *
     * @param x The x coordinate of this homebody
     * @param y The y coordinate of this homebody
     */
    public Homebody(float x, float y) {
        super(x, y, 0.45f, 0.87f, EnemyType.NORMAL, 1, 1.5f, new MeleeWeapon(x, y, 1, 0), 200);
    }

    @Override
    public void act(float movement) {
        walk(movement);
    }

}
