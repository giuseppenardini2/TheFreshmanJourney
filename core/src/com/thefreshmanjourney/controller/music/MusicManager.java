package com.thefreshmanjourney.controller.music;

import com.badlogic.gdx.audio.Music;
import com.thefreshmanjourney.view.types.MusicType;


/**
 * This class represents a controller that handles the music of the game.
 */
public class MusicManager {

    /**
     * An instance of this music manager for singleton.
     */
    private static MusicManager instance;

    /**
     * The current music to handle.
     */
    private Music music;

    /**
     * Creates a music manager.
     */
    private MusicManager(){
        music=MusicType.MENU_MUSIC.getMusic();
        music.setVolume(0f);
        music.setLooping(true);
    }

    /**
     * Returns this music manager instance.
     *
     * @return This music manager instance.
     */
    public static MusicManager getInstance(){
        if(instance==null)
            instance=new MusicManager();
        return instance;
    }

    /**
     * Changes the current music.
     *
     * @param musicType The music type
     */
    public void changeMusic(MusicType musicType){
        if(!music.equals(musicType.getMusic())){
            music.stop();
            float oldVolume=music.getVolume();
            music=musicType.getMusic();
            music.setVolume(oldVolume);
            music.setLooping(true);
            music.play();
        }
    }

    /**
     * Plays the current music.
     */
    public void playMusic(){
        music.play();
    }

    /**
     * Stops the current music.
     */
    public void stopMusic(){
        music.stop();
    }

    /**
     * Disposes the music manager resources.
     */
    public void dispose(){
        music.dispose();
    }

    /**
     * Sets the current music volume.
     *
     * @param volume The music volume
     */
    public void setVolume(float volume){
        music.setVolume(volume);
    }

}
