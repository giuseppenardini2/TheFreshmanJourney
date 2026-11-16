package com.thefreshmanjourney.controller.editor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.thefreshmanjourney.controller.TheFreshmanJourneyController;
import com.thefreshmanjourney.controller.game.course.CourseController;
import com.thefreshmanjourney.model.game.character.enemy.*;
import com.thefreshmanjourney.model.game.map.MapBlockType;
import com.thefreshmanjourney.model.game.map.GameMap;
import com.thefreshmanjourney.model.game.map.MapBlock;
import com.thefreshmanjourney.model.game.powerups.*;
import com.thefreshmanjourney.view.types.ScreenType;
import com.thefreshmanjourney.view.utils.ViewUtils;

/**
 * This class represents a controller that manages editor user inputs.
 */
public class EditorCommandManager implements InputProcessor {

    /**
     * An instance of this editor controller for singleton.
     */
    private EditorController editorController;

    /**
     * Identifies a selected game object to add to editor map.
     */
    private String selectedGameObject;


    /**
     * Creates a editor command manager.
     *
     * @param editorController The editor controller
     */
    protected EditorCommandManager(EditorController editorController){
        this.editorController=editorController;
        selectedGameObject="";
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode){
            case Input.Keys.NUM_1:
                selectedGameObject="Desk";
                break;
            case Input.Keys.NUM_2:
                selectedGameObject="Junk";
                break;
            case Input.Keys.NUM_3:
                selectedGameObject="Exit";
                break;
            case Input.Keys.Q:
                selectedGameObject="Homebody";
                break;
            case Input.Keys.W:
                selectedGameObject="Geek";
                break;
            case Input.Keys.E:
                selectedGameObject="Gamer";
                break;
            case Input.Keys.R:
                selectedGameObject="Hacker";
                break;
            case Input.Keys.A:
                selectedGameObject="Shield";
                break;
            case Input.Keys.S:
                selectedGameObject="MoreCoffee";
                break;
            case Input.Keys.D:
                selectedGameObject="MoreHeart";
                break;
            case Input.Keys.F:
                selectedGameObject="JunkFood";
                break;
            case Input.Keys.G:
                selectedGameObject="EnergyDrink";
                break;
            case Input.Keys.H:
                selectedGameObject="EnergyBar";
                break;
            case Input.Keys.J:
                selectedGameObject="SleepyPillow";
                break;
            case Input.Keys.K:
                selectedGameObject="BrokenHeart";
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        OrthographicCamera camera=editorController.getEditorView().getLessonView().getCamera();
        Vector3 coordinates=camera.unproject(new Vector3(screenX, screenY, 0f));
        int x=Math.round(coordinates.x);
        int y=Math.round(coordinates.y);
        addGameObject(x, y);
        manageUpperBarActions(x, y);
        return true;
    }

    /**
     * Adds a game object to the editor map.
     *
     * @param x The x coordinate of the game object
     * @param y The y coordinate of the game object
     */
    private void addGameObject(int x, int y) {
        GameMap gameMap=editorController.getEditorModel().getCourse().getCurrentLesson().getGameMap();
        if(gameMap.checkXY(x, y) && gameMap.getBlock(x, (int) ViewUtils.adjustYCoordinate(y, GameMap.HEIGHT)).getType().equals(MapBlockType.TILE) && (gameMap.getPlayer().getX()!=x || gameMap.getPlayer().getY()!=y)){
            switch(selectedGameObject){
                case "Desk":
                    gameMap.setBlock(new MapBlock(x, (int) ViewUtils.adjustYCoordinate(y, GameMap.HEIGHT), MapBlockType.DESK));
                    break;
                case "Junk":
                    gameMap.setBlock(new MapBlock(x, (int) ViewUtils.adjustYCoordinate(y, GameMap.HEIGHT), MapBlockType.JUNK));
                    break;
                case "Exit":
                    gameMap.setBlock(new MapBlock(x, (int) ViewUtils.adjustYCoordinate(y, GameMap.HEIGHT), MapBlockType.EXIT));
                    break;
                case "Homebody":
                    if(gameMap.findEnemy(x, y)==null)
                        gameMap.addEnemy(new Homebody(x, y));
                    break;
                case "Geek":
                    if(gameMap.findEnemy(x, y)==null)
                        gameMap.addEnemy(new Geek(x, y));
                    break;
                case "Gamer":
                    if(gameMap.findEnemy(x, y)==null)
                        gameMap.addEnemy(new Gamer(x, y));
                    break;
                case "Hacker":
                    if(gameMap.findEnemy(x, y)==null)
                        gameMap.addEnemy(new Hacker(x, y));
                    break;
                case "Shield":
                    if(gameMap.findPowerUp(x, y)==null)
                        gameMap.addPowerUp(new Shield(x, y));
                    gameMap.setBlock(new MapBlock(x, (int) ViewUtils.adjustYCoordinate(y, GameMap.HEIGHT), MapBlockType.JUNK));
                    break;
                case "MoreCoffee":
                    if(gameMap.findPowerUp(x, y)==null)
                        gameMap.addPowerUp(new MoreCoffee(x, y));
                    gameMap.setBlock(new MapBlock(x, (int) ViewUtils.adjustYCoordinate(y, GameMap.HEIGHT), MapBlockType.JUNK));
                    break;
                case "MoreHeart":
                    if(gameMap.findPowerUp(x, y)==null)
                        gameMap.addPowerUp(new MoreHeart(x, y));
                    gameMap.setBlock(new MapBlock(x, (int) ViewUtils.adjustYCoordinate(y, GameMap.HEIGHT), MapBlockType.JUNK));
                    break;
                case "JunkFood":
                    if(gameMap.findPowerUp(x, y)==null)
                        gameMap.addPowerUp(new JunkFood(x, y));
                    gameMap.setBlock(new MapBlock(x, (int) ViewUtils.adjustYCoordinate(y, GameMap.HEIGHT), MapBlockType.JUNK));
                    break;
                case "EnergyDrink":
                    if(gameMap.findPowerUp(x, y)==null)
                        gameMap.addPowerUp(new EnergyDrink(x, y));
                    gameMap.setBlock(new MapBlock(x, (int) ViewUtils.adjustYCoordinate(y, GameMap.HEIGHT), MapBlockType.JUNK));
                    break;
                case "EnergyBar":
                    if(gameMap.findPowerUp(x, y)==null)
                        gameMap.addPowerUp(new EnergyBar(x, y));
                    gameMap.setBlock(new MapBlock(x, (int) ViewUtils.adjustYCoordinate(y, GameMap.HEIGHT), MapBlockType.JUNK));
                    break;
                case "SleepyPillow":
                    if(gameMap.findPowerUp(x, y)==null)
                        gameMap.addPowerUp(new Pillow(x, y));
                    gameMap.setBlock(new MapBlock(x, (int) ViewUtils.adjustYCoordinate(y, GameMap.HEIGHT), MapBlockType.JUNK));
                    break;
                case "BrokenHeart":
                    if(gameMap.findPowerUp(x, y)==null)
                        gameMap.addPowerUp(new BrokenHeart(x, y));
                    gameMap.setBlock(new MapBlock(x, (int) ViewUtils.adjustYCoordinate(y, GameMap.HEIGHT), MapBlockType.JUNK));
                    break;
            }
            CourseController.updateLessonView(editorController.getEditorModel().getCourse(), editorController.getEditorView().getLessonView());
        }
    }

    /**
     * Manages editor upper bar actions (for example load map, save map, reset...).
     *
     * @param x The x coordinate of the game object
     * @param y The y coordinate of the game object
     */
    private void manageUpperBarActions(int x, int y) {
        if((x==3 || x==2) && (y==12 || y==13)){
            TheFreshmanJourneyController.getInstance().changeScreen(ScreenType.MAIN_MENU);
        }
        else if((x==5 || x==6) && (y==12 || y==13)){
            editorController.getEditorModel().save();
        }
        else if((x==7 || x==8) && (y==12 || y==13)){
            editorController.getEditorModel().load();
            CourseController.updateLessonView(editorController.getEditorModel().getCourse(), editorController.getEditorView().getLessonView());
        }
        else if((x==9 || x==10) && (y==12 || y==13)){
            editorController.playCourse();
        }
        else if((x==12 || x==13) && (y==12 || y==13)){
            editorController.getEditorModel().reset();
            CourseController.updateLessonView(editorController.getEditorModel().getCourse(), editorController.getEditorView().getLessonView());
        }
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
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
