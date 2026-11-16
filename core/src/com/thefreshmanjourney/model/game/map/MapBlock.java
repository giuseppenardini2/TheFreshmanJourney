package com.thefreshmanjourney.model.game.map;

import com.thefreshmanjourney.model.game.GameObject;

/**
 * This class represents a block of the game map.
 */
public class MapBlock extends GameObject {

    /**
     * The map block type.
     */
    private MapBlockType type;

    /**
     * A boolean flag indicating if this map block is visible.
     */
    private boolean visible;


    /**
     * Creates a map block.
     *
     * @param x The x coordinate of the map block
     * @param y The y coordinate of the map block
     * @param type The map block type
     * @param visible If the block is visible
     */
    public MapBlock(int x, int y, MapBlockType type, boolean visible){
        super(x,y);
        this.type=type;
        this.visible=visible;
    }
    /**
     * Creates a map block that is visible.
     *
     * @param x The x coordinate of the map block
     * @param y The y coordinate of the map block
     * @param type The map block type
     */
    public MapBlock(int x, int y, MapBlockType type){
        super(x,y);
        this.type=type;
        this.visible=true;
    }


    /**
     * Returns the map block type.
     *
     * @return The map block type
     */
    public MapBlockType getType() {
        return type;
    }

    /**
     * Returns if this map block is visible.
     *
     * @return If this map block is visible
     */
    public boolean isVisible(){
        return visible;
    }

    /**
     * Sets this map block visible.
     *
     * @param visible If the map block is visible
     */
    public void setVisible(boolean visible) {
        this.visible=visible;
    }

}
