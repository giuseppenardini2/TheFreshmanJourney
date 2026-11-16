package com.thefreshmanjourney.view.types;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * This enum represents the types of backgrounds.
 */
public enum BackgroundType {

    /**
     * The singleton menu background.
     */
    MENU_BACKGROUND(new TextureRegionDrawable(new Texture(Gdx.files.internal("img/menu/mainmenuscreen-background2.png"))));

    /**
     * The background.
     */
    private TextureRegionDrawable background;

    /**
     * Creates a background type.
     *
     * @param background The background
     */
    BackgroundType(TextureRegionDrawable background){
        this.background=background;
    }

    /**
     * Returns the background.
     *
     * @return The background
     */
    public TextureRegionDrawable getBackground(){
        return background;
    }

}
