package com.thefreshmanjourney.view.types;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * This enum represents the types of power ups views textures.
 */
public enum PowerUpTextureType {

    /**
     * The singleton broken heart texture.
     */
    BROKEN_HEART(new Texture(Gdx.files.internal("img/power-up/broken-heart.png"))),

    /**
     * The singleton junk food texture.
     */
    JUNK_FOOK(new Texture(Gdx.files.internal("img/power-up/junk-food.png"))),

    /**
     * The singleton energy bar texture.
     */
    ENERGY_BAR(new Texture(Gdx.files.internal("img/power-up/energy-bar.png"))),

    /**
     * The singleton energy drink texture.
     */
    ENERGY_DRINK(new Texture(Gdx.files.internal("img/power-up/energy-drink.png"))),

    /**
     * The singleton more coffee texture.
     */
    MORE_COFEE(new Texture(Gdx.files.internal("img/power-up/more-coffee.png"))),

    /**
     * The singleton more heart texture.
     */
    MORE_HEART(new Texture(Gdx.files.internal("img/power-up/more-heart.png"))),

    /**
     * The singleton shield texture.
     */
    SHIELD(new Texture(Gdx.files.internal("img/power-up/shield.png"))),

    /**
     * The singleton pillow texture.
     */
    PILLOW(new Texture(Gdx.files.internal("img/power-up/sleepy-pillow.png")));

    /**
     * The power up view texture.
     */
    private Texture texture;

    /**
     * Creates a power up view texture type.
     *
     * @param texture The power up view texture
     */
    PowerUpTextureType(Texture texture){
        this.texture=texture;
    }

    /**
     * Returns the power up view texture.
     *
     * @return The power up view texture
     */
    public Texture getTexture() {
        return texture;
    }

}
