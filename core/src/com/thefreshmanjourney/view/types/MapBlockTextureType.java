package com.thefreshmanjourney.view.types;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * This enums represents the types of map blocks view textures.
 */
public enum MapBlockTextureType {

    /**
     * The singleton wall texture type.
     */
    WALL(new Texture(Gdx.files.internal("img/map-block/wall.png"))),

    /**
     * The singleton desk texture type.
     */
    DESK(new Texture(Gdx.files.internal("img/map-block/desk.png"))),

    /**
     * The singleton tile texture type.
     */
    TILE(new Texture(Gdx.files.internal("img/map-block/floor.png"))),

    /**
     * The singleton junk texture type.
     */
    JUNK(new Texture(Gdx.files.internal("img/map-block/junk.png"))),

    /**
     * The singleton exit texture type.
     */
    EXIT(new Texture(Gdx.files.internal("img/map-block/exit.png")));

    /**
     * The map block view texture.
     */
    private Texture texture;

    /**
     * Creates a map block view texture type.
     *
     * @param texture The map block view texture.
     */
    MapBlockTextureType(Texture texture){
        this.texture=texture;
    }

    /**
     * Returns the map block view texture.
     *
     * @return The map block view texture
     */
    public Texture getTexture() {
        return texture;
    }

}
