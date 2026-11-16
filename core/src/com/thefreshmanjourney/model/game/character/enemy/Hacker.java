package com.thefreshmanjourney.model.game.character.enemy;

import com.thefreshmanjourney.model.game.character.weapon.meleeweapon.MeleeWeapon;
import com.thefreshmanjourney.model.game.character.weapon.bomb.Bomb;

/**
 * This class represents a type of enemy that can defuse player's bomb and has sufficient health.
 */
public class Hacker extends Enemy{

    /**
     * Creates a hacker.
     *
     * @param x The x coordinate of this hacker
     * @param y The y coordinate of this hacker
     */
    public Hacker(float x, float y) {
        super(x, y, 0.5f, 0.9f, EnemyType.NORMAL, 2, 1.5f, new MeleeWeapon(x, y, 1, 0), 300);
    }

    @Override
    public void act(float movement) {
        walk(movement);
    }

    /**
     * Defuses the player's bomb.
     *
     * @param bomb The bomb to defuse
     */
    public void defuseBomb(Bomb bomb){
        bomb.defuse();
    }


}
