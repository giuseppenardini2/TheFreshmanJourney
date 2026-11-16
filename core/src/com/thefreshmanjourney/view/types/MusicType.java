package com.thefreshmanjourney.view.types;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * This enum represents the types of music.
 */
public enum MusicType {

    /**
     * The singleton menu music.
     */
    MENU_MUSIC(Gdx.audio.newMusic(Gdx.files.internal("audio/music/menu-music.mp3"))),

    /**
     * The singleton normal level music.
     */
    NORMAL_LEVEL_MUSIC(Gdx.audio.newMusic(Gdx.files.internal("audio/music/level-music.mp3"))),

    /**
     * The singleton boss level music.
     */
    BOSS_LEVEL_MUSIC(Gdx.audio.newMusic(Gdx.files.internal("audio/music/boss-music.mp3"))),

    /**
     * The singleton editor music.
     */
    EDITOR_MUSIC(Gdx.audio.newMusic(Gdx.files.internal("audio/music/editor-music.mp3")));

    /**
     * The music.
     */
    private Music music;

    /**
     * Creates a music type.
     *
     * @param music The music
     */
    MusicType(Music music){
        this.music=music;
    }

    /**
     * Returns the music.
     *
     * @return The music
     */
    public Music getMusic(){
        return music;
    }

}
