package com.thefreshmanjourney.view.game.upperbar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.thefreshmanjourney.view.game.GameObjectView;

/**
 * This class represents a upper bar view of the game.
 */
public abstract class UpperBarView extends GameObjectView {

    /**
     * The upper bar view texture.
     */
    protected Texture texture;

    /**
     * A font for upper bar view labels.
     */
    protected BitmapFont font;

    /**
     * Creates a upper bar view.
     *
     * @param x The x coordinate of this upper bar view
     * @param y The y coordinate of this upper bar view
     */
    public UpperBarView(float x, float y){
        super(x, y, 15f, 1f);
        texture=new Texture(Gdx.files.internal("img/bar/gamebar.png"));
        initializeFont();
    }

    /**
     * Initializes the upper bar view font.
     */
    private void initializeFont(){
        FreeTypeFontGenerator fontGenerator=new FreeTypeFontGenerator(Gdx.files.internal("font/KarmaSuture.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size=12;
        fontParameter.characters="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789: ";
        fontParameter.color= Color.WHITE;
        font=fontGenerator.generateFont(fontParameter);
        font.setUseIntegerPositions(false);
        font.getData().setScale(0.035f);
    }

    /**
     * Draws the upper bar view.
     *
     * @param spriteBatch The sprite batch
     */
    public abstract void draw(SpriteBatch spriteBatch);

    /**
     * Called when the screen should release all resources.
     */
    public void dispose(){
        font.dispose();
        texture.dispose();
    }

}
