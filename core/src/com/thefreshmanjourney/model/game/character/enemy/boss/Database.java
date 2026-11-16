package com.thefreshmanjourney.model.game.character.enemy.boss;

import com.thefreshmanjourney.model.game.character.weapon.meleeweapon.MeleeWeapon;

/**
 * This class represents a type of boss that moves slowly but has a lot of health.
 */
public class Database extends Boss {

    /**
     * Creates a database.
     *
     * @param x The x coordinate of this database
     * @param y The y coordinate of this database
     */
    public Database(float x, float y) {
        super(x, y, 4f, 4f, 20, 0.9f, new MeleeWeapon(x, y, 2, 1), 3000, 12);
    }

}
