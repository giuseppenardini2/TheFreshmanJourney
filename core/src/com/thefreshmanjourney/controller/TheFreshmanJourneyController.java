package com.thefreshmanjourney.controller;

import com.badlogic.gdx.Game;
import com.thefreshmanjourney.controller.editor.EditorController;
import com.thefreshmanjourney.controller.game.course.CourseController;
import com.thefreshmanjourney.controller.menu.profile.ProfileMenuController;
import com.thefreshmanjourney.view.types.ScreenType;
import com.thefreshmanjourney.controller.menu.coursesmenu.CoursesMenuController;
import com.thefreshmanjourney.controller.menu.mainmenu.MainMenuController;
import com.thefreshmanjourney.controller.menu.settingsmenu.SettingsMenuController;
import com.thefreshmanjourney.controller.music.MusicManager;

/**
 * This class represents the main controller that handles all other controllers.
 */
public class TheFreshmanJourneyController extends Game {

	/**
	 * An instance of this main controller.
	 */
	private static TheFreshmanJourneyController instance;


	/**
	 * Creates a main controller.
	 */
	private TheFreshmanJourneyController(){}

	/**
	 * Returns this main controller instance.
	 *
	 * @return This main controller instance
	 */
	public static TheFreshmanJourneyController getInstance(){
		if(instance==null)
			instance=new TheFreshmanJourneyController();
		return instance;
	}

	@Override
	public void create() {
		changeScreen(ScreenType.MAIN_MENU);

		MusicManager musicManager=MusicManager.getInstance();
		musicManager.setVolume(SettingsMenuController.getInstance().getSettingsMenuModel().getMusicVolume());
		musicManager.playMusic();
	}

	@Override
	public void render(){
		super.render();
	}

	@Override
	public void dispose(){
		screen.dispose();
		MusicManager.getInstance().dispose();
	}

	/**
	 * Changes the current screen.
	 *
	 * @param screenType The screen type
	 */
	public void changeScreen(ScreenType screenType){
		switch(screenType){
			case MAIN_MENU:
				super.setScreen(MainMenuController.getInstance());
				break;
			case COURSES_MENU:
				super.setScreen(CoursesMenuController.getInstance());
				break;
			case SETTINGS:
				super.setScreen(SettingsMenuController.getInstance());
				break;
			case PROFILE:
				super.setScreen(ProfileMenuController.getInstance());
				break;
			case COURSE:
				super.setScreen(CourseController.getInstance());
				break;
			case EDITOR:
				super.setScreen(EditorController.getInstance());
				break;
		}
	}

}
