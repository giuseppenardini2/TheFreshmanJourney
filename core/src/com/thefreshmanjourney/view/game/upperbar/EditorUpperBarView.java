package com.thefreshmanjourney.view.game.upperbar;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This class represents the editor upper bar view.
 */
public class EditorUpperBarView extends UpperBarView {


    /**
     * Creates a editor upper bar view.
     *
     * @param x The x coordinate of this editor upper bar view
     * @param y The y coordinate of this editor upper bar view
     */
    public EditorUpperBarView(float x, float y) {
        super(x, y);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        font.draw(spriteBatch, "Back", 2f, 12.32f + font.getCapHeight());
        font.draw(spriteBatch, "Save", 5f, 12.32f + font.getCapHeight());
        font.draw(spriteBatch, "Load", 7f, 12.32f + font.getCapHeight());
        font.draw(spriteBatch, "Play", 9f, 12.32f + font.getCapHeight());
        font.draw(spriteBatch, "Reset", 12f, 12.32f + font.getCapHeight());
    }

}
