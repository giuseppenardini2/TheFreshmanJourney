package com.thefreshmanjourney.controller.menu.profile;

import com.badlogic.gdx.Screen;
import com.thefreshmanjourney.model.profile.Profile;
import com.thefreshmanjourney.model.profile.ProfileStatistics;
import com.thefreshmanjourney.view.menu.profile.ProfileMenuView;

/**
 * This class represents a profile menu controller.
 */
public class ProfileMenuController implements Screen {

    /**
     * A profile menu view.
     */
    private ProfileMenuView profileMenuView;

    /**
     * A profile model.
     */
    private Profile profileModel;

    /**
     * An instance of this profile menu controller.
     */
    private static ProfileMenuController instance;

    /**
     * Creates a profile menu controller.
     */
    private ProfileMenuController(){
        profileMenuView=new ProfileMenuView();
        profileModel=new Profile();
    }

    /**
     * Returns this profile menu controller instance.
     *
     * @return This profile menu controller instance.
     */
    public static ProfileMenuController getInstance(){
        if(instance==null)
            instance=new ProfileMenuController();
        return instance;
    }

    @Override
    public void show() {
        profileMenuView.show();

        profileMenuView.setNicknameTextFieldText(profileModel.getNickname());
        profileMenuView.setAvailableExamsLabelValue(ProfileStatistics.getAvailableExams());
        profileMenuView.setRecordedExamsLabelValue(ProfileStatistics.getRecordedExams());
        profileMenuView.setTotalCFULabelValue(ProfileStatistics.getTotalCFU());
        profileMenuView.setDegreesObtainedLabelValue(ProfileStatistics.getDegreesObtained());
        profileMenuView.setLessonsAttendedLabelValue(ProfileStatistics.getLessonsAttended());
        profileMenuView.setLessonsQuitLabelValue(ProfileStatistics.getLessonsQuit());
        profileMenuView.setLessonsCompletedLabelValue(ProfileStatistics.getLessonsCompleted());
        profileMenuView.setQuitCompletedLessonsRatioLabelValue(ProfileStatistics.getCompletedQuitLessonsRatio());
        profileMenuView.setDefeatedStudentsLabelValue(ProfileStatistics.getDefeatedStudents());
        profileMenuView.setDeathsLabelValue(ProfileStatistics.getDeaths());
        profileMenuView.setDefeatedStudentsDeathsRatioLabelValue(ProfileStatistics.getDefeatedStudentsDeathsRatio());
    }

    @Override
    public void render(float delta) {
        profileMenuView.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        profileMenuView.resize(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        profileModel.setNickname(profileMenuView.getNicknameTextFieldText());
    }

    @Override
    public void dispose() {
        profileMenuView.dispose();
    }

    /**
     * Returns the profile model.
     *
     * @return The profile model
     */
    public Profile getProfileModel(){
        return profileModel;
    }

    /**
     * Return the profile menu view.
     *
     * @return The profile menu view
     */
    public ProfileMenuView getProfileMenuView(){
        return profileMenuView;
    }

}
