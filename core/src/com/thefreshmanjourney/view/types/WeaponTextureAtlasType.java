package com.thefreshmanjourney.view.types;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * This enums represents the types of weapon texture atlases.
 */
public enum WeaponTextureAtlasType {

    /**
     * The singleton bomb texture atlas type.
     */
    BOMB(new TextureAtlas(Gdx.files.internal("img/coffee/coffee-atlas.atlas")), "coffee");

    /**
     * The weapon texture atlas.
     */
    private TextureAtlas textureAtlas;

    /**
     * The weapon texture region name.
     */
    private String textureRegionName;

    /**
     * Creates a weapon texture atlas type.
     *
     * @param textureAtlas The weapon texture atlas
     * @param textureRegionName
     */
    WeaponTextureAtlasType(TextureAtlas textureAtlas, String textureRegionName){
        this.textureAtlas=textureAtlas;
        this.textureRegionName = textureRegionName;
    }

    /**
     * Returns the weapon texture atlas.
     *
     * @return The weapon texture atlas.
     */
    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    /**
     * Returns the weapon texture region name.
     *
     * @return The weapon texture region name
     */
    public String getTextureRegionName(){
        return textureRegionName;
    }

}
