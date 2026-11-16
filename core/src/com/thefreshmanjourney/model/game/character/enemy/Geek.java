package com.thefreshmanjourney.model.game.character.enemy;

import com.thefreshmanjourney.model.game.character.weapon.meleeweapon.MeleeWeapon;
import com.thefreshmanjourney.model.game.map.MapBlockType;
import com.thefreshmanjourney.model.game.map.MapBlock;

/**
 * This class represents a type of enemy that moves even on the desks and has sufficient health.
 */
public class Geek extends Enemy {

    /**
     * Creates a geek.
     *
     * @param x The x coordinate of this geek
     * @param y The y coordinate of this geek
     */
    public Geek(float x, float y) {
        super(x, y, 0.6f, 0.9f, EnemyType.NORMAL, 2, 1.1f, new MeleeWeapon(x, y, 1, 0), 250);
    }

    @Override
    public void act(float movement){
        walk(movement);
    }

    @Override
    public boolean isWalkableBlock(MapBlock block){
        return super.isWalkableBlock(block) || block.getType().equals(MapBlockType.DESK);
    }

}
