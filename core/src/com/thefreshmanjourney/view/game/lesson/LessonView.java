package com.thefreshmanjourney.view.game.lesson;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.ScreenUtils;
import com.thefreshmanjourney.view.game.character.CharacterView;
import com.thefreshmanjourney.view.game.map.GameMapView;
import com.thefreshmanjourney.view.game.map.MapBlockView;
import com.thefreshmanjourney.view.game.powerups.PowerUpView;
import com.thefreshmanjourney.view.game.upperbar.GameUpperBarView;
import com.thefreshmanjourney.view.game.upperbar.UpperBarView;
import com.thefreshmanjourney.view.game.weapon.BombView;
import com.thefreshmanjourney.view.types.MapBlockTextureType;
import com.thefreshmanjourney.view.game.endgame.EndGameWindow;
import com.thefreshmanjourney.view.types.WeaponTextureAtlasType;

/**
 * This class represents the lesson view.
 */
public class LessonView {

    /**
     * A sprite batch.
     */
    private SpriteBatch spriteBatch;

    /**
     * A camera.
     */
    private OrthographicCamera camera;

    /**
     * The character view of the player.
     */
    private CharacterView player;

    /**
     * The active bombs view.
     */
    private ObjectSet<BombView> activeBombs;

    /**
     * The character views of the enemies.
     */
    private Array<CharacterView> enemies;

    /**
     * The power ups views.
     */
    private Array<PowerUpView> powerUps;

    /**
     * The game map view.
     */
    private GameMapView gameMapView;

    /**
     * A upper bar.
     */
    private UpperBarView upperBar;

    /**
     * The end game window.
     */
    private EndGameWindow endGameWindow;

    /**
     * The lesson width.
     */
    private final int width;

    /**
     * The lesson height.
     */
    private final int height;

    /**
     * A shaper render for debug.
     */
    private ShapeRenderer debugRenderer;


    /**
     * Creates a lesson view.
     *
     * @param gameMapView The game map view
     * @param width The lesson width
     * @param height The lesson height
     * @param player The character view of the player
     * @param activeBombs The active bombs view
     * @param enemies The character views of the enemies
     * @param powerUps The power ups views
     */
    public LessonView(GameMapView gameMapView, int width, int height, CharacterView player, ObjectSet<BombView> activeBombs, Array<CharacterView> enemies, Array<PowerUpView> powerUps) {
        spriteBatch = new SpriteBatch();

        upperBar=new GameUpperBarView(0f,height-1);

        endGameWindow=new EndGameWindow();

        this.gameMapView = gameMapView;
        this.width = width;
        this.height = height;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, this.width, this.height);

        this.player = player;
        this.activeBombs = activeBombs;
        this.enemies = enemies;
        this.powerUps = powerUps;

        this.debugRenderer = new ShapeRenderer();
    }

    /**
     * Called when the screen becomes the current screen for a Game.
     */
    public void show() {}

    /**
     * Called when the Application is resized. This can happen at any point during a non-paused state but will never happen before a call to create().
     *
     * @param width The new width in pixels
     * @param height The new height in pixels
     */
    public void resize(int width, int height) {
        camera.update();
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render
     */
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        spriteBatch.setProjectionMatrix(camera.combined);
        camera.update();

        spriteBatch.begin();

        for(MapBlockView[] row:gameMapView.getMap()) {
            for (MapBlockView mapBlockView:row) {
                drawBlock(mapBlockView);
            }
        }

        if(upperBar.isVisible())
            upperBar.draw(spriteBatch);

        if(player.isVisible())
            player.draw(spriteBatch);
        for(BombView bombView: activeBombs){
            bombView.draw(spriteBatch);
        }

        for(CharacterView enemy: enemies){
            if(enemy.isVisible())
                enemy.draw(spriteBatch);
        }
        spriteBatch.end();
    }

    /**
     * Draws the shape render for debug.
     *
     * @param spriteBatch The sprite batch
     */
    private void drawDebugRender(SpriteBatch spriteBatch){
        debugRenderer.setProjectionMatrix(camera.combined);
        debugRenderer.setAutoShapeType(true);
        debugRenderer.begin();
        debugRenderer.setColor(Color.RED);
        for(MapBlockView[] row : gameMapView.getMap()) {
            for (MapBlockView mapBlock : row) {
                debugRenderer.rect(mapBlock.getX(), mapBlock.getY(), mapBlock.getWidth(), mapBlock.getHeight());
            }
        }
        debugRenderer.setColor(Color.BLUE);
        for(PowerUpView powerUpView:powerUps){
            debugRenderer.rect(powerUpView.getX(), powerUpView.getY(), powerUpView.getWidth(), powerUpView.getHeight());
        }
        debugRenderer.setColor(Color.BLUE);
        if(player.isVisible())
            debugRenderer.rect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
        debugRenderer.setColor(Color.BLACK);
        for(BombView bombView: activeBombs){
            debugRenderer.rect(bombView.getX(), bombView.getY(), bombView.getWidth(), bombView.getHeight());
            debugRenderer.rect(bombView.getExplosionBoundingBox().x, bombView.getExplosionBoundingBox().y, bombView.getExplosionBoundingBox().width, bombView.getExplosionBoundingBox().height);
        }
        for(CharacterView enemy: enemies){
            if(enemy.isVisible())
                debugRenderer.rect(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
        }
        debugRenderer.end();
    }

    /**
     * Called when the screen should release all resources.
     */
    public void dispose() {
        spriteBatch.dispose();
        upperBar.dispose();
        player.dispose();
        for(CharacterView enemy:enemies)
            enemy.dispose();
    }

    /**
     * Shows the end game window.
     *
     * @param playerWon If the player has won
     */
    public void showEndGameWindow(boolean playerWon){
        endGameWindow.setPlayerWon(playerWon);
        endGameWindow.render();
    }

    /**
     * Draws the map block view.
     *
     * @param mapBlockView The map block view
     */
    private void drawBlock(MapBlockView mapBlockView){
        if(mapBlockView.getMapBlockTextureType().equals(MapBlockTextureType.DESK)){
            spriteBatch.draw(MapBlockTextureType.TILE.getTexture(), mapBlockView.getX(), mapBlockView.getY(), mapBlockView.getWidth(), mapBlockView.getHeight());
            spriteBatch.draw(mapBlockView.getTexture(), mapBlockView.getX(), mapBlockView.getY(), mapBlockView.getWidth(), mapBlockView.getHeight());
        }
        else if(mapBlockView.getMapBlockTextureType().equals(MapBlockTextureType.JUNK)){
            spriteBatch.draw(MapBlockTextureType.TILE.getTexture(), mapBlockView.getX(), mapBlockView.getY(), mapBlockView.getWidth(), mapBlockView.getHeight());
            int powerUpIndex=findPowerUp(mapBlockView.getX(), mapBlockView.getY());
            if(powerUpIndex!=-1 && powerUps.get(powerUpIndex).isVisible()){
                PowerUpView powerUp=powerUps.get(powerUpIndex);
                spriteBatch.draw(powerUp.getTexture(), powerUp.getX(), powerUp.getY(), powerUp.getWidth(), powerUp.getHeight());
            }
            if(mapBlockView.isVisible())
                spriteBatch.draw(mapBlockView.getTexture(), mapBlockView.getX(), mapBlockView.getY(), mapBlockView.getWidth(), mapBlockView.getHeight());
        }
        else if(mapBlockView.getMapBlockTextureType().equals(MapBlockTextureType.EXIT)){
            spriteBatch.draw(MapBlockTextureType.TILE.getTexture(), mapBlockView.getX(), mapBlockView.getY(), mapBlockView.getWidth(), mapBlockView.getHeight());
            spriteBatch.draw(mapBlockView.getTexture(), mapBlockView.getX()+0.25f, mapBlockView.getY(), mapBlockView.getWidth()-0.5f, mapBlockView.getHeight()-0.5f);
            if(mapBlockView.isVisible())
                spriteBatch.draw(MapBlockTextureType.JUNK.getTexture(), mapBlockView.getX(), mapBlockView.getY(), mapBlockView.getWidth(), mapBlockView.getHeight());
        }
        else
            spriteBatch.draw(mapBlockView.getTexture(), mapBlockView.getX(), mapBlockView.getY(), mapBlockView.getWidth(), mapBlockView.getHeight());
    }

    /**
     * Returns the power up view index in this game map corresponding to the provided coordinates, otherwise -1.
     *
     * @param x The x coordinate of the power up view
     * @param y The y coordinate of the power up view
     * @return The power up view to find, otherwise -1
     */
    private int findPowerUp(float x, float y){
        for(int i=0; i<powerUps.size; i++)
            if((int) powerUps.get(i).getX()== (int) x && (int) powerUps.get(i).getY()== (int) y)
                return i;
        return -1;
    }

    /**
     * Returns the active bomb view in this lesson view corresponding to the provided coordinates, otherwise null.
     *
     * @param x The x coordinate of the active bomb view
     * @param y The y coordinate of the active bomb view
     * @return The active bomb view to find, otherwise null
     */
    public BombView findBomb(float x, float y){
        for(BombView bombView:activeBombs)
            if(bombView.getX()==x && bombView.getY()==y)
                return bombView;
        return null;
    }

    /**
     * Returns the character view of the enemy in this lesson view corresponding to the provided coordinates, otherwise null.
     *
     * @param x The x coordinate of the enemy
     * @param y The y coordinate of the enemy
     * @return The character view of the enemy to find, otherwise null
     */
    public CharacterView findEnemy(float x, float y){
        for(CharacterView enemy:enemies)
            if(enemy.getX()==x && enemy.getY()==y)
                return enemy;
        return null;
    }

    /**
     * Returns the characters view that are located within the damage areas of the bomb.
     * @param bomb The bomb view
     * @return The characters view within the bomb damage area
     */
    public Array<CharacterView> getCharactersInBombArea(BombView bomb){
        Array<CharacterView> charactersInBombArea=new Array<>();
        if(player.getBoundingBox().overlaps(bomb.getExplosionBoundingBox()))
            charactersInBombArea.add(player);
        for(CharacterView enemy: enemies){
            if(enemy.getBoundingBox().overlaps(bomb.getExplosionBoundingBox()))
                charactersInBombArea.add(enemy);
        }
        return charactersInBombArea;
    }


    /**
     * Sets the game map view.
     *
     * @param gameMapView The game map view
     */
    public void setGameMapView(GameMapView gameMapView){
        this.gameMapView=gameMapView;
    }

    /**
     * Sets the power ups view.
     *
     * @param powerUps The power ups view
     */
    public void setPowerUps(Array<PowerUpView> powerUps) {
        this.powerUps=powerUps;
    }

    /**
     * Sets the character views of the enemies.
     *
     * @param enemies The character views of the enemies
     */
    public void setEnemies(Array<CharacterView> enemies) {
        this.enemies=enemies;
    }

    /**
     * Returns the camera.
     *
     * @return The camera
     */
    public OrthographicCamera getCamera(){
        return camera;
    }

    /**
     * Sets the character view of the player.
     *
     * @param player The character view of the player
     */
    public void setPlayer(CharacterView player){
        this.player=player;
    }

    /**
     * Returns the character view of the player.
     *
     * @return The character view of the player
     */
    public CharacterView getPlayer() {
        return player;
    }

    /**
     * Removes the active bomb view from active bombs views.
     *
     * @param bombView The active bomb view to remove
     */
    public void removeBombView(BombView bombView){
        activeBombs.remove(bombView);
    }

    /**
     * Creates and adds the active bomb view in the active bombs views if it doesn't exists and returns it, otherwise returns the existing active bomb view.
     *
     * @param x The x coordinate of the active bomb view
     * @param y The y coordinate of the active bomb view
     * @param damageArea The active bomb view damage area
     * @return The active bomb view
     */
    public BombView addBombView(float x, float y, int damageArea){
        BombView addedBomb=findBomb(x, y);
        if(addedBomb==null){
            addedBomb=new BombView(x, y, damageArea, WeaponTextureAtlasType.BOMB);
            activeBombs.add(addedBomb);
        }
        return addedBomb;
    }

    /**
     * Returns active bombs views.
     *
     * @return The active bombs views
     */
    public ObjectSet<BombView> getActiveBombs() {
        return activeBombs;
    }

    /**
     * Returns the character views of the enemies.
     *
     * @return The character views of the enemies
     */
    public Array<CharacterView> getEnemies() {
        return enemies;
    }

    /**
     * Returns the power ups views.
     *
     * @return The power ups views
     */
    public Array<PowerUpView> getPowerUps() {
        return powerUps;
    }

    /**
     * Returns the game map view.
     *
     * @return The game map view
     */
    public GameMapView getGameMapView() {
        return gameMapView;
    }

    /**
     * Returns the lesson view width.
     *
     * @return The lesson view width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the lesson view height.
     *
     * @return The lesson view height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the upper bar of this lesson view.
     *
     * @param upperBar The upper bar
     */
    public void setUpperBar(UpperBarView upperBar){
        this.upperBar=upperBar;
    }

    /**
     * Returns the lesson view upper bar.
     *
     * @return The lesson view upper bar
     */
    public UpperBarView getUpperBar() {
        return upperBar;
    }

}
