package com.thefreshmanjourney.view.game.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import com.thefreshmanjourney.view.game.GameObjectView;
import com.thefreshmanjourney.view.types.CharacterTextureAtlasType;
import com.thefreshmanjourney.view.types.SoundType;

import java.util.Observable;
import java.util.Observer;

/**
 * This class represents the view of characters of the game.
 */
public class CharacterView extends GameObjectView implements Observer{

    /**
     * A character view texture atlas.
     *
     * @see TextureAtlas
     */
    private TextureAtlas textureAtlas;

    /**
     * The character view texture regions name.
     *
     * @see TextureRegion
     */
    private String textureRegionName;

    /**
     * The character view state.
     */
    private CharacterViewState state;

    /**
     * The animation when this character view moves up.
     */
    private Animation<TextureRegion> moveUpAnimation;

    /**
     * The animation when this character view moves down.
     */
    private Animation<TextureRegion> moveDownAnimation;

    /**
     * The animation when this character view moves left.
     */
    private Animation<TextureRegion> moveLeftAnimation;

    /**
     * The animation when this character view moves right.
     */
    private Animation<TextureRegion> moveRightAnimation;

    /**
     * The time when the dead animation starts.
     */
    private long deadAnimationStart;

    /**
     * The time passed since the animation started.
     */
    private float stateTime;

    /**
     * The previous x coordinate of this character view.
     */
    private float previousX;

    /**
     * The previous y coordinate of this character view.
     */
    private float previousY;

    /**
     * Creates a character view.
     *
     * @param x The x coordinate of this character view
     * @param y The y coordinate of this character view
     * @param width The character view width
     * @param height The character view height
     * @param characterTextureAtlasType The character view texture atlas type
     */
    public CharacterView(float x, float y, float width, float height, CharacterTextureAtlasType characterTextureAtlasType) {
        super(x, y, width, height, SoundType.CHARACTER_HURT);
        textureAtlas = characterTextureAtlasType.getTextureAtlas();
        textureRegionName = characterTextureAtlasType.getTextureRegionName();
        state = CharacterViewState.QUIET;
        moveUpAnimation = new Animation<>(0.075f, textureAtlas.findRegions(textureRegionName + "-up"), Animation.PlayMode.LOOP);
        moveDownAnimation = new Animation<>(0.075f, textureAtlas.findRegions(textureRegionName + "-down"), Animation.PlayMode.LOOP);
        moveLeftAnimation = new Animation<>(0.075f, textureAtlas.findRegions(textureRegionName + "-sx"), Animation.PlayMode.LOOP);
        moveRightAnimation = new Animation<>(0.075f, textureAtlas.findRegions(textureRegionName + "-dx"), Animation.PlayMode.LOOP);
        deadAnimationStart=0L;
        stateTime=0;
        previousX=x;
        previousY=y;
    }

    /**
     * Draws this character view.
     *
     * @param spriteBatch The sprite batch
     */
    public void draw(SpriteBatch spriteBatch){
        TextureRegion currentFrame=null;
        switch(state) {
            case QUIET:
                currentFrame=textureAtlas.findRegion(this.textureRegionName +"-quiet");
                break;
            case UP:
                stateTime+= Gdx.graphics.getDeltaTime();
                currentFrame=moveUpAnimation.getKeyFrame(stateTime, true);
                break;
            case DOWN:
                stateTime+= Gdx.graphics.getDeltaTime();
                currentFrame=moveDownAnimation.getKeyFrame(stateTime, true);
                break;
            case LEFT:
                stateTime+= Gdx.graphics.getDeltaTime();
                currentFrame=moveLeftAnimation.getKeyFrame(stateTime, true);
                break;
            case RIGHT:
                stateTime+= Gdx.graphics.getDeltaTime();
                currentFrame=moveRightAnimation.getKeyFrame(stateTime, true);
                break;
            case DEAD:
                currentFrame=textureAtlas.findRegion(this.textureRegionName +"-dead");
                break;
        }
        spriteBatch.draw(currentFrame, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        state=CharacterViewState.QUIET;
    }

    /**
     * Sets the x and y coordinates of this character to its previous coordinates.
     */
    public void turnToPreviousPosition(){
        boundingBox.x=previousX;
        boundingBox.y=previousY;
    }

    @Override
    public void update(Observable observable, Object arg) {
        if(arg!=null && ((Boolean)arg)==true)
            visible=false;
    }

    /**
     * Called when the screen should release all resources.
     */
    public void dispose(){
        textureAtlas.dispose();
    }


    /**
     * Sets the previous x.
     *
     * @param previousX The previous x value
     */
    public void setPreviousX(float previousX) {
        this.previousX = previousX;
    }

    /**
     * Sets the previous y.
     *
     * @param previousY The previous y value
     */
    public void setPreviousY(float previousY) {
        this.previousY = previousY;
    }

    /**
     * Sets the character view state.
     *
     * @param state The character view state
     */
    public void setState(CharacterViewState state){
        this.state=state;
        if(state.equals(CharacterViewState.DEAD) && (deadAnimationStart==0))
            deadAnimationStart=TimeUtils.nanoTime();
    }

    @Override
    public void setVisible(boolean visible){
        if(TimeUtils.nanoTime()-deadAnimationStart>1000000000L)
            this.visible=visible;
    }

}
