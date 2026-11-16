package com.thefreshmanjourney.model.game.character.weapon;

import com.badlogic.gdx.utils.Array;
import com.thefreshmanjourney.model.game.GameObject;
import com.thefreshmanjourney.model.game.character.Character;

/**
 * This class represents weapon in the game.
 */
public abstract class Weapon extends GameObject {

    /**
     * The weapon damage.
     */
    private int damage;

    /**
     * The weapon damage area.
     */
    private int damageArea;


    /**
     * Creates a weapon.
     *
     * @param x The x coordinate of this weapon
     * @param y The y coordinate of this weapon
     * @param damage The damage
     * @param damageArea The damage area
     */
    public Weapon(float x, float y, int damage, int damageArea){
        super(x, y);
        this.damage=damage;
        this.damageArea=damageArea;
    }

    /**
     * Attacks the character dealing damage to it.
     *
     * @param character The character to attack
     */
    public void attack(Character character){
        if(character!=null)
            character.damaged(damage);
    }
    /**
     * Attacks the characters dealing damage to them.
     *
     * @param characters The characters to attack
     */
    public void attack(Array<Character> characters){
        for(Character character:characters)
            character.damaged(damage);
    }

    /**
     * Returns the weapon damage.
     *
     * @return The weapon damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Returns the damage area.
     *
     * @return The damage area
     */
    public int getDamageArea() {
        return damageArea;
    }

}
