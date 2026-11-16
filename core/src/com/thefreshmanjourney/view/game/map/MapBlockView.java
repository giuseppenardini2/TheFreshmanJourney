package com.thefreshmanjourney.view.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.thefreshmanjourney.view.game.GameObjectView;
import com.thefreshmanjourney.view.types.MapBlockTextureType;

/**
 * This class represents the game map block view.
 */
public class MapBlockView extends GameObjectView {

    /**
     * The game map block view texture type.
     */
    private MapBlockTextureType mapBlockTextureType;
    /**
     * A texture.
     */
    private Texture texture;

    /**
     * Creates a map block view.
     *
     * @param x The x coordinate of this map block view
     * @param y The y coordinate of this map block view
     * @param mapBlockTextureType The map block view texture type
     */
    public MapBlockView(int x, int y, MapBlockTextureType mapBlockTextureType) {
        super(x, y, 1f, 1f);
        this.mapBlockTextureType=mapBlockTextureType;
        texture=mapBlockTextureType.getTexture();
    }

    /**
     * Returns the map block view texture type.
     *
     * @return The map block view texture type
     */
    public MapBlockTextureType getMapBlockTextureType() {
        return mapBlockTextureType;
    }

    /**
     * Returns the map block view texture.
     *
     * @return The map block view texture
     */
    public Texture getTexture(){
        return texture;
    }

}