package com.thefreshmanjourney.model.types;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * This enum represents the types of preferences.
 *
 * @see Preferences
 */
public enum PreferencesType {

    /**
     * The singleton settings preferences.
     */
    SETTINGS_PREFERENCES(Gdx.app.getPreferences("settings")),

    /**
     * The singleton profile preferences.
     */
    PROFILE_PREFERENCES(Gdx.app.getPreferences("profile"));

    /**
     * The preferences.
     */
    private Preferences preferences;

    /**
     * Creates a preferences type.
     *
     * @param preferences The preferences
     */
    PreferencesType(Preferences preferences){
        this.preferences = preferences;
    }

    /**
     * Returns the preferences.
     *
     * @return The preferences
     */
    public Preferences getPreferences() {
        return preferences;
    }

}
