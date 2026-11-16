package com.thefreshmanjourney.controller.game.powerups;

import com.badlogic.gdx.utils.Array;
import com.thefreshmanjourney.controller.game.course.CourseController;
import com.thefreshmanjourney.controller.music.SoundManager;
import com.thefreshmanjourney.model.game.character.player.Player;
import com.thefreshmanjourney.model.game.map.GameMap;
import com.thefreshmanjourney.model.game.powerups.PowerUp;
import com.thefreshmanjourney.view.game.character.CharacterView;
import com.thefreshmanjourney.view.game.powerups.PowerUpView;
import com.thefreshmanjourney.view.types.PowerUpTextureType;
import com.thefreshmanjourney.view.utils.ViewUtils;

/**
 * This class represents a power ups controller
 */
public class PowerUpController {

    /**
     * A course controller.
     */
    private CourseController courseController;


    /**
     * Creates a power up controller.
     *
     * @param courseController The course controller
     */
    public PowerUpController(CourseController courseController) {
        this.courseController = courseController;
    }

    /**
     * Manages power ups behaviour.
     */
    public void managePowerUps(){
        managePowerUpCollisions();
    }

    /**
     * Manages power ups collisions.
     */
    private void managePowerUpCollisions(){
        Array<PowerUpView> powerUpsView= courseController.getLessonView().getPowerUps();
        Player playerModel= courseController.getCourseModel().getCurrentLesson().getGameMap().getPlayer();
        CharacterView playerView= courseController.getLessonView().getPlayer();
        PowerUpView powerUpViewToDelete=null;
        for(PowerUpView powerUpView: powerUpsView){
            if(playerView.getBoundingBox().overlaps(powerUpView.getBoundingBox())){
                PowerUp powerUpModel= courseController.getCourseModel().getCurrentLesson().getGameMap().findPowerUp(powerUpView.getX(), ViewUtils.adjustYCoordinate(powerUpView.getY(), GameMap.HEIGHT));
                if(!powerUpModel.isUsed()){
                    playerModel.addPowerUp(powerUpModel);
                    powerUpModel.setUsed();
                    powerUpView.setVisible(false);
                    powerUpViewToDelete=powerUpView;
                    SoundManager.getInstance().playSound(powerUpView);
                }
            }
        }
        powerUpsView.removeValue(powerUpViewToDelete, false);
    }


    /**
     * Returns the power up texture type corresponding to enemy model.
     *
     * @param powerUpModel The power up model
     * @return The power up texture type
     */
    public static PowerUpTextureType getPowerUpTextureType(PowerUp powerUpModel){
        switch(powerUpModel.getClass().getSimpleName()){
            case "BrokenHeart":
                return PowerUpTextureType.BROKEN_HEART;
            case "JunkFood":
                return PowerUpTextureType.JUNK_FOOK;
            case "EnergyBar":
                return PowerUpTextureType.ENERGY_BAR;
            case "EnergyDrink":
                return PowerUpTextureType.ENERGY_DRINK;
            case "MoreCoffee":
                return PowerUpTextureType.MORE_COFEE;
            case "MoreHeart":
                return PowerUpTextureType.MORE_HEART;
            case "Shield":
                return PowerUpTextureType.SHIELD;
            case "Pillow":
                return PowerUpTextureType.PILLOW;
            default:
                return null;
        }
    }

}
