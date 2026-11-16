package com.thefreshmanjourney.model.game.character.enemy;

import com.thefreshmanjourney.model.game.character.weapon.meleeweapon.MeleeWeapon;

/**
 * This class represents a type of enemy that doesn't move but has a lot of health.
 */
public class Gamer extends Enemy {

    /**
     * Creates a gamer.
     *
     * @param x The x coordinates of this gamer
     * @param y The y coordinates of this gamer
     */
    public Gamer(float x, float y) {
        super(x, y, 1f, 1f, EnemyType.NORMAL, 3, 0f, new MeleeWeapon(x, y, 1, 0), 100);
    }

    @Override
    public void act(float movement){}

}
