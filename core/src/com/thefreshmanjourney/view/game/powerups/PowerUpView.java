package com.thefreshmanjourney.view.game.powerups;

import com.badlogic.gdx.graphics.Texture;
import com.thefreshmanjourney.view.game.GameObjectView;
import com.thefreshmanjourney.view.types.PowerUpTextureType;
import com.thefreshmanjourney.view.types.SoundType;

public class PowerUpView extends GameObjectView {

    /**
     * The power up view texture.
     */
    private Texture texture;

    /**
     * Creates a power up view.
     *
     * @param x The x coordinate of this power up view
     * @param y The y coordinate of this power up view
     * @param powerUpTextureType The power up view texture type
     */
    public PowerUpView(float x, float y, PowerUpTextureType powerUpTextureType) {
        super(x, y, 0.5f, 0.5f, SoundType.POWER_UP);
        texture=powerUpTextureType.getTexture();
    }

    /**
     * Returns the power up view texture.
     *
     * @return The power up view texture
     */
    public Texture getTexture(){
        return texture;
    }

}
