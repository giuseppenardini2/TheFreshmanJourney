package com.thefreshmanjourney.model.profile;

import com.badlogic.gdx.Preferences;
import com.thefreshmanjourney.model.types.PreferencesType;

/**
 * This class represents the profile statistics.
 */
public class ProfileStatistics {

    /**
     * The file that memorizes the profile statistics values.
     */
    private static Preferences preferences=PreferencesType.PROFILE_PREFERENCES.getPreferences();

    /**
     * The name of the recorded exams in the file.
     */
    private static final String RECORDED_EXAMS="recordedExams";

    /**
     * The name of the available exams in the file.
     */
    private static final String AVAILABLE_EXAMS="availableExams";

    /**
     * The name of the total CFU in the file.
     */
    private static final String TOTAL_CFU="totalCFU";

    /**
     * The name of the degrees obtained in the file.
     */
    private static final String DEGREES_OBTAINED="degreesObtained";

    /**
     * The name of the lessons attended in the file.
     */
    private static final String LESSONS_ATTENDED="lessonsAttended";

    /**
     * The name of the lessons quit in the file.
     */
    private static final String LESSONS_QUIT="lessonsQuit";

    /**
     * The name of the lessons completed in the file.
     */
    private static final String LESSONS_COMPLETED="lessonsCompleted";

    /**
     * The name of the completed quit lessons ratio in the file.
     */
    private static final String COMPLETED_QUIT_LESSONS_RATIO ="completedQuitLessonsRatio";

    /**
     * The name of the defeated students in the file.
     */
    private static final String DEFEATED_STUDENTS="defeatedStudents";

    /**
     * The name of the deaths in the file.
     */
    private static final String DEATHS="deaths";

    /**
     * The name of the defeated students deaths ratio in the file.
     */
    private static final String DEFEATED_STUDENTS_DEATHS_RATIO="defeatedStudentsDeathsRatio";

    /**
     * Resets this profile statistics.
     */
    public static void reset() {
        preferences.putInteger(RECORDED_EXAMS, 0);
        preferences.putInteger(AVAILABLE_EXAMS, 2);
        preferences.putInteger(TOTAL_CFU, 0);
        preferences.putInteger(DEGREES_OBTAINED, 0);
        preferences.putInteger(LESSONS_ATTENDED, 0);
        preferences.putInteger(LESSONS_COMPLETED, 0);
        preferences.putInteger(LESSONS_QUIT, 0);
        preferences.putFloat(COMPLETED_QUIT_LESSONS_RATIO, 0f);
        preferences.putInteger(DEFEATED_STUDENTS, 0);
        preferences.putInteger(DEATHS, 0);
        preferences.putFloat(DEFEATED_STUDENTS_DEATHS_RATIO, 0f);
        preferences.flush();
    }


    /**
     *  Returns the recorded exams amount.
     *
     * @return The recorded exams amount
     */
    public static int getRecordedExams(){
        return preferences.getInteger(RECORDED_EXAMS, 0);
    }

    /**
     * Increments the recorded exams amount by 1.
     */
    public static void incrementRecordedExams(){
        preferences.putInteger(RECORDED_EXAMS, getRecordedExams()+1);
        preferences.flush();
    }

    /**
     * Returns the available exams amount.
     *
     * @return The available exams amount
     */
    public static int getAvailableExams(){
        return preferences.getInteger(AVAILABLE_EXAMS, 2);
    }

    /**
     * Increments the available exams amount by 1.
     */
    public static void incrementAvailableExams(){
        preferences.putInteger(AVAILABLE_EXAMS, getAvailableExams()+1);
        preferences.flush();
    }

    /**
     * Returns the total CFU amount.
     *
     * @return The total CFU amount
     */
    public static int getTotalCFU(){
        return preferences.getInteger(TOTAL_CFU, 0);
    }

    /**
     * Increments the total CFU amount by the provided value.
     *
     * @param cfu The incremental value
     */
    public static void incrementTotalCFU(int cfu){
        preferences.putInteger(TOTAL_CFU, getTotalCFU()+cfu);
        preferences.flush();
    }

    /**
     * Returns the degrees obtained amount.
     *
     * @return The degrees obtained amount
     */
    public static int getDegreesObtained(){
        return preferences.getInteger(DEGREES_OBTAINED, 0);
    }

    /**
     * Increments the degrees obtained amount by 1.
     */
    public static void incrementDegreesObtained(){
        preferences.putInteger(DEGREES_OBTAINED, getDegreesObtained()+1);
        preferences.flush();
    }

    /**
     * Returns the lessons attended amount.
     *
     * @return The lessons attended amount
     */
    public static int getLessonsAttended(){
        return preferences.getInteger(LESSONS_ATTENDED, 0);
    }

    /**
     * Increments the lessons attended amount.
     */
    public static void incrementLessonsAttended(){
        preferences.putInteger(LESSONS_ATTENDED, getLessonsAttended()+1);
        preferences.flush();
    }

    /**
     * Returns the lessons quit amount.
     *
     * @return The lessons quit amount
     */
    public static int getLessonsQuit(){
        return preferences.getInteger(LESSONS_QUIT, 0);
    }

    /**
     * Increments the lessons quit amount by 1.
     */
    public static void incrementLessonsQuit(){
        preferences.putInteger(LESSONS_QUIT, getLessonsQuit()+1);
        preferences.flush();
    }

    /**
     * Returns the lessons completed amount.
     *
     * @return The lessons completed amount
     */
    public static int getLessonsCompleted(){
        return preferences.getInteger(LESSONS_COMPLETED, 0);
    }

    /**
     * Increments the lessons completed by 1.
     */
    public static void incrementLessonsCompleted(){
        preferences.putInteger(LESSONS_COMPLETED, getLessonsCompleted()+1);
        preferences.flush();
    }

    /**
     * Returns the completed quit lessons ratio.
     *
     * @return The completed quit lessons ratio
     */
    public static float getCompletedQuitLessonsRatio(){
        calculateCompletedQuitLessonsRatio();
        return preferences.getFloat(COMPLETED_QUIT_LESSONS_RATIO, 0f);
    }

    /**
     * Computes the completed quit lessons ratio.
     */
    public static void calculateCompletedQuitLessonsRatio(){
        preferences.putFloat(COMPLETED_QUIT_LESSONS_RATIO,  getLessonsQuit()!=0 ? Math.round((getLessonsCompleted()/getLessonsQuit())*100)/100 : 0);
        preferences.flush();
    }

    /**
     * Returns the defeated students amount.
     *
     * @return The defeated students amount
     */
    public static int getDefeatedStudents(){
        return preferences.getInteger(DEFEATED_STUDENTS, 0);
    }

    /**
     * Increments the defeated students amount by 1.
     */
    public static void incrementDefeatedStudents(){
        preferences.putInteger(DEFEATED_STUDENTS, getDefeatedStudents()+1);
        preferences.flush();
    }

    /**
     * Returns the deaths amount.
     *
     * @return The deaths amount
     */
    public static int getDeaths(){
        return preferences.getInteger(DEATHS, 0);
    }

    /**
     * Increments the deaths amount by 1.
     */
    public static void incrementDeaths(){
        preferences.putInteger(DEATHS, getDeaths()+1);
        preferences.flush();
    }

    /**
     * Returns the defeated students deaths ratio.
     *
     * @return The defeated students deaths ratio
     */
    public static float getDefeatedStudentsDeathsRatio(){
        calculateDefeatedStudentsDeathsRatio();
        return preferences.getFloat(DEFEATED_STUDENTS_DEATHS_RATIO, 0f);
    }

    /**
     * Computes the defeated students deaths ratio.
     */
    private static void calculateDefeatedStudentsDeathsRatio(){
        preferences.putFloat(DEFEATED_STUDENTS_DEATHS_RATIO, getDeaths()!=0 ? Math.round((getDefeatedStudents()/getDeaths())*100)/100 : 0);
        preferences.flush();
    }

}
