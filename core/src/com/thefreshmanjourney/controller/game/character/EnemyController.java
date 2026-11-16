package com.thefreshmanjourney.controller.game.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;
import com.thefreshmanjourney.controller.game.course.CourseController;
import com.thefreshmanjourney.controller.music.SoundManager;
import com.thefreshmanjourney.model.game.character.enemy.Enemy;
import com.thefreshmanjourney.model.game.character.enemy.Hacker;
import com.thefreshmanjourney.model.game.character.player.Player;
import com.thefreshmanjourney.model.game.character.weapon.bomb.Bomb;
import com.thefreshmanjourney.model.game.character.weapon.bomb.BombLauncher;
import com.thefreshmanjourney.model.game.map.GameMap;
import com.thefreshmanjourney.view.game.character.CharacterView;
import com.thefreshmanjourney.view.game.character.CharacterViewState;
import com.thefreshmanjourney.view.game.map.MapBlockView;
import com.thefreshmanjourney.view.game.weapon.BombView;
import com.thefreshmanjourney.view.types.CharacterTextureAtlasType;
import com.thefreshmanjourney.view.utils.ViewUtils;

/**
 * This class represents a enemy controller.
 */
public class EnemyController {

    /**
     * A course controller.
     */
    private CourseController courseController;

    /**
     * Creates a enemy controller.
     */
    public EnemyController(CourseController courseController){
        this.courseController = courseController;
    }


    /**
     * Returns the enemy texture type corresponding to enemy model.
     *
     * @param enemy The enemy model
     * @return The enemy texture type
     */
    public static CharacterTextureAtlasType getEnemyTextureType(Enemy enemy){
        switch(enemy.getClass().getSimpleName()){
            case "Hacker":
                return CharacterTextureAtlasType.HACKER;
            case "Geek":
                return CharacterTextureAtlasType.GEEK;
            case "Homebody":
                return CharacterTextureAtlasType.HOMEBODY;
            case "Gamer":
                return CharacterTextureAtlasType.GAMER;
            case "Algorithm":
                return CharacterTextureAtlasType.ALGORITHM;
            case "Database":
                return CharacterTextureAtlasType.DATABASE;
            default:
                return null;
        }
    }

    /**
     * Manages enemies behaviour.
     */
    public void manageEnemies(){
        Array<Enemy> enemiesModel= courseController.getCourseModel().getCurrentLesson().getGameMap().getEnemies();
        for(Enemy enemyModel:enemiesModel){
            CharacterView enemyView= courseController.getLessonView().findEnemy(enemyModel.getX(), enemyModel.getY());
            if(!enemyModel.isDead()){
                enemyModel.updateHarmful();
                manageEnemyMovement(enemyModel, enemyView);
                manageEnemyCollisionsWithBlocks(enemyModel, enemyView);
                manageEnemyCollisionsWithEnemies(enemyModel, enemyView);
                manageEnemyCollisionsWithPlayer(enemyModel, enemyView);
                manageEnemyCollisionsWithBombs(enemyModel, enemyView);
            }
            manageEnemyDeath(enemyModel, enemyView);
        }
    }

    /**
     * Manages enemy movement.
     *
     * @param enemyModel The enemy model
     * @param enemyView The enemy view
     */
    private void manageEnemyMovement(Enemy enemyModel, CharacterView enemyView){
        if(!enemyModel.isDead()){
            enemyModel.act(enemyModel.getSpeed()*Gdx.graphics.getDeltaTime());
            updateCharacterViewState(enemyModel, enemyView);
            enemyView.setPreviousX(enemyView.getX());
            enemyView.setPreviousY(enemyView.getY());
            enemyView.setXY(enemyModel.getX(),enemyModel.getY());
        }
    }

    /**
     * Updates enemy view state based on enemy model.
     *
     * @param enemyModel The enemy model
     * @param enemyView The enemy view
     */
    private void updateCharacterViewState(Enemy enemyModel, CharacterView enemyView){
        switch(enemyModel.getCurrentDirection()){
            case UP:
                enemyView.setState(CharacterViewState.UP);
                break;
            case DOWN:
                enemyView.setState(CharacterViewState.DOWN);
                break;
            case LEFT:
                enemyView.setState(CharacterViewState.LEFT);
                break;
            case RIGHT:
                enemyView.setState(CharacterViewState.RIGHT);
                break;
        }
    }

    /**
     * Manages enemy collisions with blocks.
     *
     * @param enemyModel The enemy model
     * @param enemyView The enemy view
     */
    private void manageEnemyCollisionsWithBlocks(Enemy enemyModel, CharacterView enemyView){
        ObjectSet<MapBlockView> possibleCollisions= courseController.getLessonView().getGameMapView().getBlocksInArea(enemyView.getX(), enemyView.getY(), (int) Math.ceil(enemyView.getHeight()));
        GameMap gameMapModel= courseController.getCourseModel().getCurrentLesson().getGameMap();
        for(MapBlockView mapBlockView:possibleCollisions){
            if(enemyView.getBoundingBox().overlaps(mapBlockView.getBoundingBox()) && (!enemyModel.isWalkableBlock(gameMapModel.getBlock((int) mapBlockView.getX(), (int) ViewUtils.adjustYCoordinate(mapBlockView.getY(), GameMap.HEIGHT))))){
                reactToCollision(enemyModel, enemyView);
            }
        }
    }

    /**
     * Manages enemy collisions with enemies.
     *
     * @param enemyModel The enemy model
     * @param enemyView The enemy view
     */
    private void manageEnemyCollisionsWithEnemies(Enemy enemyModel, CharacterView enemyView){
        for(CharacterView otherEnemyView: courseController.getLessonView().getEnemies()){
            if((otherEnemyView.getX()!=enemyView.getX() || otherEnemyView.getY()!=enemyView.getY()) && otherEnemyView.getBoundingBox().overlaps(enemyView.getBoundingBox())){
                reactToCollision(enemyModel, enemyView);
            }
        }
    }

    /**
     * Manages enemy collisions with player.
     *
     * @param enemyModel The enemy model
     * @param enemyView The enemy view
     */
    private void manageEnemyCollisionsWithPlayer(Enemy enemyModel, CharacterView enemyView){
        Player playerModel= courseController.getCourseModel().getCurrentLesson().getGameMap().getPlayer();
        CharacterView playerView= courseController.getLessonView().getPlayer();
        if(playerView.getBoundingBox().overlaps(enemyView.getBoundingBox())){
            if(enemyModel.isHarmful()) {
                courseController.getPlayerController().hitPlayer(enemyModel.getWeapon());
                playerModel.setNotControllable();
            }
            courseController.getPlayerController().reactToCollision(playerModel, playerView);
            reactToCollision(enemyModel, enemyView);
            enemyModel.setNotHarmful();
        }
    }

    /**
     * Manages enemy collisions with bombs.
     *
     * @param enemyModel The enemy model
     * @param enemyView The enemy view
     */
    private void manageEnemyCollisionsWithBombs(Enemy enemyModel, CharacterView enemyView){
        if(enemyModel.getClass().getSimpleName().equals("Hacker")){
            for(BombView bombView: courseController.getLessonView().getActiveBombs()){
                if(bombView.getBoundingBox().overlaps(enemyView.getBoundingBox())){
                    Bomb bombModel=((BombLauncher) courseController.getCourseModel().getCurrentLesson().getGameMap().getPlayer().getWeapon()).findActiveBomb(bombView.getX(), bombView.getY());
                    ((Hacker)enemyModel).defuseBomb(bombModel);
                }
            }
        }
    }

    /**
     * Manages enemy after a collision.
     *
     * @param enemyModel The enemy model
     * @param enemyView The enemy view
     */
    public void reactToCollision(Enemy enemyModel, CharacterView enemyView){
        enemyView.turnToPreviousPosition();
        enemyModel.setXY(enemyView.getX(), enemyView.getY());
        enemyModel.changeDirection();
    }

    /**
     * Manages enemy when dies.
     *
     * @param enemyModel The enemy model
     * @param enemyView The enemy view
     */
    private void manageEnemyDeath(Enemy enemyModel, CharacterView enemyView){
        if(enemyModel.isDead()){
            enemyView.setState(CharacterViewState.DEAD);
            enemyView.setVisible(false);
            SoundManager.getInstance().playSound(enemyView);
            if(!enemyModel.wasKillScoreGiven()) {
                courseController.getCourseModel().getCurrentLesson().getGameMap().getPlayer().incrementScore(enemyModel.getKillScore());
                enemyModel.setKillScoreGiven();
            }
        }
    }

}
