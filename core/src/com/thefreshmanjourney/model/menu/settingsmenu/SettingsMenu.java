package com.thefreshmanjourney.model.menu.settingsmenu;

import com.badlogic.gdx.Preferences;
import com.thefreshmanjourney.model.types.PreferencesType;

public class SettingsMenu {

    /**
     * The file that memorizes the settings menu values.
     */
    private Preferences preferences;

    /**
     * The name of the music volume value in the file.
     */
    private static final String MUSIC_VOLUME="volume";

    /**
     * The name of the sound volume value in the file.
     */
    private static final String SOUND_VOLUME="sound";


    /**
     * Creates a setting menu.
     */
    public SettingsMenu(){
        preferences=PreferencesType.SETTINGS_PREFERENCES.getPreferences();
    }


    /**
     * Returns the music volume.
     *
     * @return The music volume
     */
    public float getMusicVolume(){
        return preferences.getFloat(MUSIC_VOLUME, 0.5f);
    }

    /**
     * Sets the music volume.
     *
     * @param volume The music volume value
     */
    public void setMusicVolume(float volume){
        preferences.putFloat(MUSIC_VOLUME, volume);
        preferences.flush();
    }

    /**
     * Returns the sound volume.
     *
     * @return The sound volume
     */
    public float getSoundVolume(){
        return preferences.getFloat(SOUND_VOLUME, 0.5f);
    }

    /**
     * Sets the sound volume
     *
     * @param volume The sound volume value
     */
    public void setSoundVolume(float volume){
        preferences.putFloat(SOUND_VOLUME, volume);
        preferences.flush();
    }

}
