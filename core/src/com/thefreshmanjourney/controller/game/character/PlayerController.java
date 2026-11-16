package com.thefreshmanjourney.controller.game.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;
import com.thefreshmanjourney.controller.TheFreshmanJourneyController;
import com.thefreshmanjourney.controller.game.course.CourseController;
import com.thefreshmanjourney.controller.music.SoundManager;
import com.thefreshmanjourney.model.game.character.enemy.Enemy;
import com.thefreshmanjourney.model.game.character.movement.Direction;
import com.thefreshmanjourney.model.game.character.player.Player;
import com.thefreshmanjourney.model.game.character.weapon.Weapon;
import com.thefreshmanjourney.model.game.character.weapon.bomb.Bomb;
import com.thefreshmanjourney.model.game.character.weapon.bomb.BombLauncher;
import com.thefreshmanjourney.model.game.map.MapBlockType;
import com.thefreshmanjourney.model.game.map.GameMap;
import com.thefreshmanjourney.model.game.map.MapBlock;
import com.thefreshmanjourney.view.game.character.CharacterView;
import com.thefreshmanjourney.view.game.character.CharacterViewState;
import com.thefreshmanjourney.view.game.map.MapBlockView;
import com.thefreshmanjourney.view.game.weapon.BombView;
import com.thefreshmanjourney.view.types.ScreenType;
import com.thefreshmanjourney.view.utils.ViewUtils;

/**
 * This class represents a player controller.
 */
public class PlayerController implements InputProcessor {

    /**
     * A course controller.
     */
    private final CourseController courseController;

    /**
     * A boolean flag indicating whether the player is currently moving upwards.
     */
    private boolean playerMovingUp;

    /**
     * A boolean flag indicating whether the player is currently moving downwards.
     */
    private boolean playerMovingDown;

    /**
     * A boolean flag indicating whether the player is currently moving left.
     */
    private boolean playerMovingLeft;

    /**
     * A boolean flag indicating whether the player is currently moving right.
     */
    private boolean playerMovingRight;


    /**
     * Creates a player controller.
     *
     * @param courseController The course controller
     */
    public PlayerController(CourseController courseController){
        this.courseController = courseController;
        initialize();
    }

    /**
     * Initializes this player controller.
     */
    public void initialize(){
        playerMovingUp=false;
        playerMovingDown=false;
        playerMovingLeft=false;
        playerMovingRight=false;
    }

    /**
     * Manages player behaviour.
     */
    public void managePlayer(){
        Player playerModel= courseController.getCourseModel().getCurrentLesson().getGameMap().getPlayer();
        playerModel.updateControllable();
        if(playerModel.isControllable() && !courseController.getCourseModel().getCurrentLesson().isFinished())
            manageMovements();
        managePlayerCollisionsWithBlocks();
        managePlayerBombs();
    }

    /**
     * Manages player collisions with blocks.
     */
    private void managePlayerCollisionsWithBlocks(){
        Player playerModel= courseController.getCourseModel().getCurrentLesson().getGameMap().getPlayer();
        CharacterView playerView= courseController.getLessonView().getPlayer();
        ObjectSet<MapBlockView> possibleCollisions= courseController.getLessonView().getGameMapView().getBlocksInArea(playerView.getX(), playerView.getY(), 1);
        GameMap gameMapModel= courseController.getCourseModel().getCurrentLesson().getGameMap();
        for(MapBlockView mapBlockView:possibleCollisions){
            if(playerView.getBoundingBox().overlaps(mapBlockView.getBoundingBox()) && (!gameMapModel.getPlayer().isWalkableBlock(gameMapModel.getBlock((int) mapBlockView.getX(), (int) ViewUtils.adjustYCoordinate(mapBlockView.getY(), GameMap.HEIGHT))))){
                reactToCollision(playerModel, playerView);
            }
        }
    }

    /**
     * Manages player's bombs behaviour.
     */
    private void managePlayerBombs(){
        BombLauncher bombLauncher=(BombLauncher) courseController.getCourseModel().getCurrentLesson().getGameMap().getPlayer().getWeapon();
        ObjectSet<Bomb> bombsModelToRemove=new ObjectSet<>(); // Memorize the exploded bombs to remove
        for(Bomb bombModel:bombLauncher.getActiveBombs()){
            bombModel.dropped();
            BombView bombView= courseController.getLessonView().addBombView(bombModel.getX(), bombModel.getY(), bombModel.getDamageArea());
            if(bombModel.isExploded() && !bombModel.isDefused()){ // Check if the bomb is exploded
                bombView.setExplosionAnimationEnabled();
                bombsModelToRemove.add(bombModel);
                if(!bombView.isCollisionManaged()){ // If the collision is not managed, manage it
                    manageBombCollisionsWithCharacters(bombModel, bombView);
                    manageBombCollisionsWithBlocks(bombModel, bombView);
                    bombView.setCollisionManaged();
                }
                SoundManager.getInstance().playSound(bombView);
                if(bombView.isRemovable()){ // If the bomb explodes, then destroy breakable blocks and hurt character
                    courseController.getLessonView().removeBombView(bombView);
                }
            }
            if(bombModel.isDefused()){
                bombsModelToRemove.add(bombModel);
                courseController.getLessonView().removeBombView(bombView);
            }
        }
        for(Bomb bombModel:bombsModelToRemove){ // Remove from the model bombs that are exploded or have been detonated
            if(courseController.getLessonView().findBomb(bombModel.getX(), bombModel.getY())==null)
                bombLauncher.getActiveBombs().remove(bombModel); // Before remove bomb from model, check if the correspondent bomb on the view has been removed
        }
    }

    /**
     * Manages bomb collisions with characters in explosion area.
     *
     * @param bombModel The bomb model
     * @param bombView The bomb view
     */
    private void manageBombCollisionsWithCharacters(Bomb bombModel, BombView bombView){
        Player playerModel= courseController.getCourseModel().getCurrentLesson().getGameMap().getPlayer();
        Array<CharacterView> charactersInBombArea = courseController.getLessonView().getCharactersInBombArea(bombView);
        for(CharacterView characterView:charactersInBombArea){
            if(characterView.getX()==playerModel.getX() && characterView.getY()==playerModel.getY()){
                hitPlayer(bombModel);
            }
            else {
                Enemy enemyModel= courseController.getCourseModel().getCurrentLesson().getGameMap().findEnemy(characterView.getX(), characterView.getY());
                bombModel.attack(enemyModel);
            }
        }
    }

    /**
     * Manages bomb collisions with blocks in explosion area.
     *
     * @param bombModel The bomb model
     * @param bombView The bomb view
     */
    private void manageBombCollisionsWithBlocks(Bomb bombModel, BombView bombView){
        ObjectSet<MapBlockView> possibleCollisions= courseController.getLessonView().getGameMapView().getBlocksInArea(bombView.getX(), bombView.getY(), bombView.getDamageArea());
        Rectangle explosionBoundingBox=bombView.getExplosionBoundingBox();
        for(MapBlockView blockView: possibleCollisions){
            MapBlock blockModel= courseController.getCourseModel().getCurrentLesson().getGameMap().getBlock((int) blockView.getX(), (int) ViewUtils.adjustYCoordinate(blockView.getY(), GameMap.HEIGHT));
            if(explosionBoundingBox.overlaps(blockView.getBoundingBox()) && blockModel.getType().equals(MapBlockType.JUNK) && blockModel.isVisible()){
                blockModel.setVisible(false);
                blockView.setVisible(false);
            }
            else if(explosionBoundingBox.overlaps(blockView.getBoundingBox()) && blockModel.getType().equals(MapBlockType.EXIT)){
                blockModel.setVisible(true);
                blockView.setVisible(false);
            }
        }
    }

    /**
     * Manages player hit by a weapon.
     *
     * @param weapon The weapon that hits the player.
     */
    public void hitPlayer(Weapon weapon){
        Player playerModel= courseController.getCourseModel().getCurrentLesson().getGameMap().getPlayer();
        weapon.attack(playerModel);
    }

    /**
     * Manages player after a collision.
     *
     * @param playerModel The player model
     * @param playerView The player view
     */
    public void reactToCollision(Player playerModel, CharacterView playerView){
        playerView.turnToPreviousPosition();
        playerModel.setXY(playerView.getX(), playerView.getY());
    }


    /**
     * Manages player movements.
     */
    private void manageMovements(){
        Player playerModel= courseController.getCourseModel().getCurrentLesson().getGameMap().getPlayer();
        float movement=playerModel.getSpeed()*Gdx.graphics.getDeltaTime();
        if(playerMovingDown)
            movePlayerDown(movement);
        if(playerMovingUp)
            movePlayerUp(movement);
        if(playerMovingLeft)
            movePlayerLeft(movement);
        if(playerMovingRight)
            movePlayerRight(movement);
    }

    /**
     * Moves player up.
     *
     * @param movement The movement units
     */
    private void movePlayerUp(float movement){
        Player playerModel= courseController.getCourseModel().getCurrentLesson().getGameMap().getPlayer();
        CharacterView playerView= courseController.getLessonView().getPlayer();
        playerView.setState(CharacterViewState.UP);
        playerView.setPreviousY(playerView.getY());
        playerView.incrementY(movement);
        playerModel.move(Direction.UP, movement);
    }

    /**
     * Moves player down.
     *
     * @param movement The movement units
     */
    private void movePlayerDown(float movement){
        Player playerModel= courseController.getCourseModel().getCurrentLesson().getGameMap().getPlayer();
        CharacterView playerView= courseController.getLessonView().getPlayer();
        playerView.setState(CharacterViewState.DOWN);
        playerView.setPreviousY(playerView.getY());
        playerView.decrementY(movement);
        playerModel.move(Direction.DOWN, movement);
    }

    /**
     * Moves player left.
     *
     * @param movement The movement units
     */
    private void movePlayerLeft(float movement){
        Player playerModel= courseController.getCourseModel().getCurrentLesson().getGameMap().getPlayer();
        CharacterView playerView= courseController.getLessonView().getPlayer();
        playerView.setState(CharacterViewState.LEFT);
        playerView.setPreviousX(playerView.getX());
        playerView.decrementX(movement);
        playerModel.move(Direction.LEFT, movement);
    }

    /**
     * Moves player right.
     *
     * @param movement The movement units
     */
    private void movePlayerRight(float movement){
        Player playerModel= courseController.getCourseModel().getCurrentLesson().getGameMap().getPlayer();
        CharacterView playerView= courseController.getLessonView().getPlayer();
        playerView.setState(CharacterViewState.RIGHT);
        playerView.setPreviousX(playerView.getX());
        playerView.incrementX(movement);
        playerModel.move(Direction.RIGHT, movement);
    }

    /**
     * Manages player interaction with exit.
     */
    private void interactWithExit(){
        CharacterView playerView= courseController.getLessonView().getPlayer();
        for(MapBlockView mapBlockView: courseController.getLessonView().getGameMapView().getBlocksInArea(playerView.getX(), playerView.getY(), 0)){
            MapBlock mapBlockModel= courseController.getCourseModel().getCurrentLesson().getGameMap().getBlock((int) mapBlockView.getX(), (int) ViewUtils.adjustYCoordinate(mapBlockView.getY(), GameMap.HEIGHT));
            Enemy enemy= courseController.getCourseModel().getCurrentLesson().getGameMap().getEnemies().get(0);
            if(mapBlockModel.getType().equals(MapBlockType.EXIT) && mapBlockModel.isVisible() || (enemy.isBoss() && enemy.isDead()))
                courseController.getCourseModel().getCurrentLesson().setCompleted();
        }
    }

    /**
     * Makes player drop a bomb.
     */
    private void dropTheCoffee(){
        Player playerModel= courseController.getCourseModel().getCurrentLesson().getGameMap().getPlayer();
        BombLauncher bombLauncher=(BombLauncher) playerModel.getWeapon();
        bombLauncher.dropTheCoffee();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case Input.Keys.W:
                playerMovingUp=true;
                playerMovingDown=false;
                break;
            case Input.Keys.S:
                playerMovingDown=true;
                playerMovingUp=false;
                break;
            case Input.Keys.A:
                playerMovingLeft=true;
                playerMovingRight=false;
                break;
            case Input.Keys.D:
                playerMovingRight=true;
                playerMovingLeft=false;
                break;

            case Input.Keys.SPACE:
                dropTheCoffee();
                break;

            case Input.Keys.E:
                interactWithExit();
                break;

            /* case Input.Keys.P: // Uncomment this section of code if you want to enable the “P” key to skip to the next level
                courseController.getCourseModel().getCurrentLesson().setCompleted();
                courseController.getCourseModel().getCurrentLesson().getGameMap().getPlayer().setNotControllable();
                break; */
            case Input.Keys.L:
            	TheFreshmanJourneyController.getInstance().changeScreen(ScreenType.MAIN_MENU);
            	break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode){
            case Input.Keys.W:
                playerMovingUp=false;
                break;
            case Input.Keys.S:
                playerMovingDown=false;
                break;
            case Input.Keys.A:
                playerMovingLeft=false;
                break;
            case Input.Keys.D:
                playerMovingRight=false;
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button==Input.Buttons.RIGHT) {
            dropTheCoffee();
        }
        else if(button==Input.Buttons.LEFT){
            Vector3 mouseCoordinates= courseController.getLessonView().getCamera().unproject(new Vector3(screenX, screenY, 0f));
            float playerX= courseController.getLessonView().getPlayer().getX();
            float playerY= courseController.getLessonView().getPlayer().getY();
            if(ViewUtils.isPointInTriangle(mouseCoordinates.x, mouseCoordinates.y, 0, 13, playerX, playerY, 15, 13))
                playerMovingUp=true;
            else if(ViewUtils.isPointInTriangle(mouseCoordinates.x, mouseCoordinates.y, playerX, playerY, 14, 0, 15, 13))
                playerMovingRight=true;
            else if(ViewUtils.isPointInTriangle(mouseCoordinates.x, mouseCoordinates.y, 0, 0, 14, 0, playerX, playerY))
                playerMovingDown=true;
            else if(ViewUtils.isPointInTriangle(mouseCoordinates.x, mouseCoordinates.y, 0, 0, playerX, playerY, 0, 13))
                playerMovingLeft=true;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(button==Input.Buttons.LEFT)
            initialize();
        return true;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
