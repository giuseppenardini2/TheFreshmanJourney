package com.thefreshmanjourney.view.game.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.thefreshmanjourney.view.game.GameObjectView;
import com.thefreshmanjourney.view.types.SoundType;
import com.thefreshmanjourney.view.types.WeaponTextureAtlasType;

/**
 * This class represents the bomb view.
 */
public class BombView extends GameObjectView {

    /**
     * A boolean flag indicating if the explosion animation can occur.
     */
    private boolean explosionAnimationEnabled;

    /**
     * A boolean flag indicating if this bomb view can be removed from active bomb views in the current lesson view.
     */
    private boolean removable;

    /**
     * A boolean flag indicating if the bomb view collisions with other game object views has been handled.
     */
    private boolean collisionManaged;

    /**
     * The bomb view damage are.
     */
    private int damageArea;

    /**
     * An invisible rectangle used for real-time bomb view explosion collision detection.
     */
    private Rectangle explosionBoundingBox;

    /**
     * A texture atlas.
     */
    private TextureAtlas textureAtlas;

    /**
     * The texture atlas region name.
     */
    private String textureRegionName;

    /**
     * The center explosion animation.
     */
    private Animation<TextureRegion> centerExplosionAnimation;

    /**
     * The up explosion animation.
     */
    private Animation<TextureRegion> upExplosionAnimation;

    /**
     * The down explosion animation.
     */
    private Animation<TextureRegion> downExplosionAnimation;

    /**
     * The left explosion animation.
     */
    private Animation<TextureRegion> leftExplosionAnimation;

    /**
     * The right explosion animation.
     */
    private Animation<TextureRegion> rightExplosionAnimation;

    /**
     * The time passed since the animation started.
     */
    private float stateTime;

    /**
     * Creates a bomb view.
     *
     * @param x The x coordinate of this bomb view
     * @param y The y coordinate of this bomb view
     * @param damageArea The bomb view damage area
     * @param weaponTextureAtlasType The weapon view texture atlas type
     */
    public BombView(float x, float y, int damageArea, WeaponTextureAtlasType weaponTextureAtlasType){
        super(x, y,0.5f, 0.5f, SoundType.EXPLOSION);
        explosionAnimationEnabled =false;
        removable=false;
        collisionManaged=false;
        this.damageArea=damageArea;
        explosionBoundingBox=new Rectangle(x-width, y-width, damageArea+width, damageArea+width);
        textureAtlas=weaponTextureAtlasType.getTextureAtlas();
        textureRegionName=weaponTextureAtlasType.getTextureRegionName();
        centerExplosionAnimation=new Animation<>(0.099f, textureAtlas.findRegions(textureRegionName + "-center"), Animation.PlayMode.NORMAL);
        upExplosionAnimation = new Animation<>(0.099f, textureAtlas.findRegions(textureRegionName + "-up"), Animation.PlayMode.NORMAL);
        downExplosionAnimation = new Animation<>(0.099f, textureAtlas.findRegions(textureRegionName + "-down"), Animation.PlayMode.NORMAL);
        leftExplosionAnimation = new Animation<>(0.099f, textureAtlas.findRegions(textureRegionName + "-sx"), Animation.PlayMode.NORMAL);
        rightExplosionAnimation = new Animation<>(0.099f, textureAtlas.findRegions(textureRegionName + "-dx"), Animation.PlayMode.NORMAL);
        stateTime=0f;
    }

    /**
     * Draws this bomb view.
     *
     * @param spriteBatch The sprite batch
     */
    public void draw(SpriteBatch spriteBatch){
        if(!explosionAnimationEnabled)
            spriteBatch.draw(textureAtlas.findRegion(textureRegionName+"-quiet"), boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        else if(!centerExplosionAnimation.isAnimationFinished(stateTime)){
            stateTime+=Gdx.graphics.getDeltaTime();
            float distance=damageArea*width;
            spriteBatch.draw(centerExplosionAnimation.getKeyFrame(stateTime, true), boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
            spriteBatch.draw(upExplosionAnimation.getKeyFrame(stateTime, true), boundingBox.x, boundingBox.y+distance, boundingBox.width, boundingBox.height);
            spriteBatch.draw(downExplosionAnimation.getKeyFrame(stateTime, true), boundingBox.x, boundingBox.y-distance, boundingBox.width, boundingBox.height);
            spriteBatch.draw(leftExplosionAnimation.getKeyFrame(stateTime, true), boundingBox.x-distance, boundingBox.y, boundingBox.width, boundingBox.height);
            spriteBatch.draw(rightExplosionAnimation.getKeyFrame(stateTime, true), boundingBox.x+distance, boundingBox.y, boundingBox.width, boundingBox.height);
        }
        else
            removable=true;
    }

    /**
     * Returns if this bomb view can be removed from active bomb views in the current lesson view.
     *
     * @return If this bomb view can be removed from active bomb views in the current lesson view
     */
    public boolean isRemovable(){
        return removable;
    }

    /**
     * Returns if the bomb view collisions with other game object views has been handled.
     *
     * @return if the bomb view collisions with other game object views has been handled
     */
    public boolean isCollisionManaged(){
        return collisionManaged;
    }

    /**
     * Sets the bomb view collisions with other game object views has been handled.
     */
    public void setCollisionManaged(){
        collisionManaged=true;
    }

    /**
     * Sets the explosion animation can occur.
     */
    public void setExplosionAnimationEnabled(){
        explosionAnimationEnabled =true;
    }

    /**
     * Returns the explosion bounding box.
     *
     * @return The explosion bounding box
     */
    public Rectangle getExplosionBoundingBox() {
        return explosionBoundingBox;
    }

    /**
     * Returns the damage area.
     *
     * @return The damage area
     */
    public int getDamageArea() {
        return damageArea;
    }
}
