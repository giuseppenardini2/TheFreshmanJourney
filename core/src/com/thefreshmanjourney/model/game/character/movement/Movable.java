package com.thefreshmanjourney.model.game.character.movement;

/**
 * This interface represents a game object that can move.
 */
public interface Movable {

    /**
     * Moves the game object in the indicated direction.
     *
     * @param direction The movement direction
     * @param movement The movement units
     */
    void move(Direction direction, float movement);

}
