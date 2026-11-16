package com.thefreshmanjourney.controller.music;

import com.thefreshmanjourney.view.game.GameObjectView;
import com.thefreshmanjourney.view.types.SoundType;

/**
 * This class represents a controller that handles the sounds of the game.
 */
public class SoundManager {

    /**
     * An instance of this sound manager for singleton.
     */
    private static SoundManager instance;

    /**
     * The volume of the sound to handle.
     */
    private float volume;

    /**
     * Creates a sound manager.
     */
    private SoundManager(){
        volume=0.5f;
    }

    /**
     * Returns this sound manager instance.
     *
     * @return This sound manager instance
     */
    public static SoundManager getInstance() {
        if(instance==null)
            instance=new SoundManager();
        return instance;
    }

    /**
     * Plays the sound corresponding to the game object view.
     *
     * @param gameObjectView The game object view
     */
    public void playSound(GameObjectView gameObjectView){
        if(gameObjectView.getSound()!=null && !gameObjectView.isSoundPlayed()){
            gameObjectView.getSound().play(volume);
            gameObjectView.setSoundPlayed();
        }
    }
    /**
     * Plays the sound.
     *
     * @param soundType The sound type
     */
    public void playSound(SoundType soundType){
        soundType.getSound().play(volume);
    }

    /**
     * Sets the sounds volume.
     *
     * @param volume The sounds volumes
     */
    public void setVolume(float volume){
        this.volume=volume;
    }

}
