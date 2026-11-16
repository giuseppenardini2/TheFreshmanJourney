package com.thefreshmanjourney.view.types;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * This enums represents the types of skins.
 *
 * @see Skin
 */
public enum SkinType {

    /**
     * The singleton UI skin.
     */
    UI_SKIN(new Skin(Gdx.files.internal("skins/tfj-ui/tfj-ui.json"))),

    /**
     * The singleton avatar skin.
     */
    AVATAR_SKIN(new Skin(Gdx.files.internal("skins/tfj-avatar/tfj-avatar.json")));

    /**
     * The skin.
     */
    private Skin skin;

    /**
     * Creates a skin type.
     *
     * @param skin The skin
     */
    SkinType(Skin skin){
        this.skin=skin;
    }

    /**
     * Returns the skin.
     *
     * @return The skin
     */
    public Skin getSkin() {
        return skin;
    }

}
