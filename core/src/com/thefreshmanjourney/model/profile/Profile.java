package com.thefreshmanjourney.model.profile;

import com.badlogic.gdx.Preferences;
import com.thefreshmanjourney.model.types.PreferencesType;

/**
 * This class represents the user profile in the game.
 */
public class Profile {

    /**
     * The file that memorizes the user profile values.
     */
    private Preferences preferences;

    /**
     * The name of the nickname in the file.
     */
    private static final String NICKNAME="nickname";


    /**
     * Creates a profile.
     */
    public Profile(){
        preferences=PreferencesType.PROFILE_PREFERENCES.getPreferences();
    }


    /**
     * Returns the nickname.
     *
     * @return The nickname
     */
    public String getNickname() {
        return preferences.getString(NICKNAME, "Student");
    }

    /**
     * Sets the nickname.
     *
     * @param nickname The nickname
     */
    public void setNickname(String nickname) {
        preferences.putString(NICKNAME, nickname);
    }

}
