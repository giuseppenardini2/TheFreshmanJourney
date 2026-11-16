package com.thefreshmanjourney.view.types;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * This enums represents the types of sounds.
 */
public enum SoundType {

    /**
     * The explosion sound.
     */
    EXPLOSION(Gdx.audio.newSound(Gdx.files.internal("audio/sound/splash.ogg"))),

    /**
     * The power up sound.
     */
    POWER_UP(Gdx.audio.newSound(Gdx.files.internal("audio/sound/power-up.ogg"))),

    /**
     * The character hurt sound.
     */
    CHARACTER_HURT(Gdx.audio.newSound(Gdx.files.internal("audio/sound/character-dead.ogg")));

    /**
     * The sound.
     */
    private Sound sound;

    /**
     * Creates a sound type.
     *
     * @param sound The sound
     */
    SoundType(Sound sound){
        this.sound=sound;
    }

    /**
     * Returns the sound.
     *
     * @return The sound
     */
    public Sound getSound() {
        return sound;
    }

}
