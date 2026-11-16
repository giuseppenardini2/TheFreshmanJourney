package com.thefreshmanjourney.view.types;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * This enums represents the types of character texture atlases.
 */
public enum CharacterTextureAtlasType {

    /**
     * The singleton player texture atlas.
     */
    PLAYER(new TextureAtlas(Gdx.files.internal("img/character/player/player-atlas.atlas")), "player"),

    /**
     * The singleton hacker texture atlas.
     */
    HACKER(new TextureAtlas(Gdx.files.internal("img/character/hacker/hacker-atlas.atlas")), "hacker"),

    /**
     * The singleton geek texture atlas.
     */
    GEEK(new TextureAtlas(Gdx.files.internal("img/character/geek/geek-atlas.atlas")), "geek"),

    /**
     * The singleton homebody texture atlas.
     */
    HOMEBODY(new TextureAtlas(Gdx.files.internal("img/character/homebody/homebody-atlas.atlas")), "homebody"),

    /**
     * The singleton gamer texture atlas.
     */
    GAMER(new TextureAtlas(Gdx.files.internal("img/character/gamer/gamer-atlas.atlas")), "gamer"),

    /**
     * The singleton algorithm texture atlas.
     */
    ALGORITHM(new TextureAtlas(Gdx.files.internal("img/character/algorithm/algorithm-atlas.atlas")), "algorithm"),

    /**
     * The singleton database texture atlas.
     */
    DATABASE(new TextureAtlas(Gdx.files.internal("img/character/database/database-atlas.atlas")), "database");

    /**
     * The character texture atlas.
     */
    private TextureAtlas textureAtlas;

    /**
     * The character texture region name.
     */
    private String textureRegionName;

    /**
     * Creates a character texture atlas type.
     *
     * @param textureAtlas The character texture atlas
     * @param textureRegionName The character texture region name
     */
    CharacterTextureAtlasType(TextureAtlas textureAtlas, String textureRegionName){
        this.textureAtlas=textureAtlas;
        this.textureRegionName = textureRegionName;
    }

    /**
     * Returns the character texture atlas.
     *
     * @return The character texture atlas
     */
    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    /**
     * Returns the character texture region name.
     *
     * @return The character texture region name
     */
    public String getTextureRegionName(){
        return textureRegionName;
    }

}
