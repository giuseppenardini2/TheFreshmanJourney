package com.thefreshmanjourney.controller.game.course;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;
import com.thefreshmanjourney.controller.game.character.EnemyController;
import com.thefreshmanjourney.controller.game.character.PlayerController;
import com.thefreshmanjourney.controller.game.powerups.PowerUpController;
import com.thefreshmanjourney.controller.music.MusicManager;
import com.thefreshmanjourney.model.game.character.enemy.Enemy;
import com.thefreshmanjourney.model.game.character.player.Player;
import com.thefreshmanjourney.model.game.course.CourseType;
import com.thefreshmanjourney.model.game.course.Course;
import com.thefreshmanjourney.model.game.lesson.Lesson;
import com.thefreshmanjourney.model.game.map.GameMap;
import com.thefreshmanjourney.model.game.powerups.PowerUp;
import com.thefreshmanjourney.view.game.character.CharacterView;
import com.thefreshmanjourney.view.game.lesson.LessonView;
import com.thefreshmanjourney.view.game.map.GameMapView;
import com.thefreshmanjourney.view.game.powerups.PowerUpView;
import com.thefreshmanjourney.view.game.upperbar.GameUpperBarView;
import com.thefreshmanjourney.view.types.*;
import com.thefreshmanjourney.view.utils.ViewUtils;

/**
 * This class represents a course controller.
 */
public class CourseController implements Screen {

    /**
     * A lesson view.
     */
    private LessonView lessonView;

    /**
     * A course model.
     */
    private Course courseModel;

    /**
     * A player controller.
     */
    private PlayerController playerController;

    /**
     * A enemy controller.
     */
    private EnemyController enemyController;

    /**
     * A power up controller.
     */
    private PowerUpController powerUpController;

    /**
     * An instance of this course controller for singleton.
     */
    private static CourseController instance;


    /**
     * Creates a course controller.
     */
    private CourseController(){
        courseModel =new Course(CourseType.COMPUTER_SCIENCE.getLessonsAmount(), CourseType.COMPUTER_SCIENCE.getName());
        courseModel.loadActualLesson();
        enemyController=new EnemyController(this);
        powerUpController=new PowerUpController(this);
        lessonView=initializeLessonView(courseModel);
        playerController=new PlayerController(this);
    }

    /**
     * Returns this course controller instance.
     *
     * @return This course controller instance.
     */
    public static CourseController getInstance(){
        if(instance==null)
            instance=new CourseController();
        instance.setCourseModel(new Course(CourseType.COMPUTER_SCIENCE.getLessonsAmount(), CourseType.COMPUTER_SCIENCE.getName()));
        instance.courseModel.setCurrentLessonId(0);
        instance.courseModel.loadActualLesson();
        instance.lessonView= CourseController.initializeLessonView(instance.courseModel);
        instance.playerController.initialize();
        return instance;
    }

    /**
     * Returns the lesson view corresponding to the actual lesson model of the course model.
     *
     * @param courseModel The course model.
     * @return The lesson view corresponding to its model.
     */
    public static LessonView initializeLessonView(Course courseModel){
        GameMapView gameMapView=new GameMapView(courseModel.getCurrentLesson().getGameMap().getMap());

        Player playerModel= courseModel.getCurrentLesson().getGameMap().getPlayer();
        if(courseModel.getCurrentLessonId()==1)
            playerModel.initialize();
        CharacterView playerView=new CharacterView(playerModel.getX(), ViewUtils.adjustYCoordinate(playerModel.getY(), GameMap.HEIGHT), playerModel.getWidth(), playerModel.getHeight(), CharacterTextureAtlasType.PLAYER);
        playerModel.setXY(playerView.getX(), playerView.getY());
        courseModel.getCurrentLesson().addObserver(playerView);

        Array<PowerUp> powerUpsModel= courseModel.getCurrentLesson().getGameMap().getPowerUps();
        Array<PowerUpView> powerUpsView= new Array<>();
        for(PowerUp powerUpModel: powerUpsModel){
            powerUpsView.add(new PowerUpView(powerUpModel.getX()+0.25f, ViewUtils.adjustYCoordinate(powerUpModel.getY(), GameMap.HEIGHT), PowerUpController.getPowerUpTextureType(powerUpModel)));
        }

        Array<Enemy> enemiesModel= courseModel.getCurrentLesson().getGameMap().getEnemies();
        Array<CharacterView> enemiesView=new Array<>();
        for(Enemy enemyModel:enemiesModel) {
            enemyModel.setY(ViewUtils.adjustYCoordinate(enemyModel.getY(), GameMap.HEIGHT));
            CharacterView enemyView=new CharacterView(enemyModel.getX(), enemyModel.getY(), enemyModel.getWidth(), enemyModel.getHeight(), EnemyController.getEnemyTextureType(enemyModel));
            enemiesView.add(enemyView);
            courseModel.getCurrentLesson().addObserver(enemyView);
        }

        LessonView lessonView=new LessonView(gameMapView, GameMap.WIDTH, GameMap.HEIGHT, playerView, new ObjectSet<>(), enemiesView, powerUpsView);

        if(courseModel.getCurrentLessonId()==4 || courseModel.getCurrentLessonId()==8)
            MusicManager.getInstance().changeMusic(MusicType.BOSS_LEVEL_MUSIC);
        else
            MusicManager.getInstance().changeMusic(MusicType.NORMAL_LEVEL_MUSIC);

        return lessonView;
    }

    /**
     * Updates the lesson view according to the course model.
     *
     * @param courseModel The course model.
     * @param lessonView The lesson view to update.
     */
    public static void updateLessonView(Course courseModel, LessonView lessonView){
        Player playerModel= courseModel.getCurrentLesson().getGameMap().getPlayer();
        playerModel.initialize();
        playerModel.setXY(1f, 11f);
        CharacterView playerView=new CharacterView(playerModel.getX(), playerModel.getY(), playerModel.getWidth(), playerModel.getHeight(), CharacterTextureAtlasType.PLAYER);
        lessonView.setPlayer(playerView);

        lessonView.setGameMapView(new GameMapView(courseModel.getCurrentLesson().getGameMap().getMap()));

        Array<PowerUp> powerUpsModel= courseModel.getCurrentLesson().getGameMap().getPowerUps();
        Array<PowerUpView> powerUpsView=new Array<>();
        for(PowerUp powerUpModel: powerUpsModel){
            powerUpsView.add(new PowerUpView(powerUpModel.getX()+0.25f, ViewUtils.adjustYCoordinate(powerUpModel.getY(), GameMap.HEIGHT), PowerUpController.getPowerUpTextureType(powerUpModel)));
        }
        lessonView.setPowerUps(powerUpsView);

        courseModel.getCurrentLesson().deleteObservers();
        Array<Enemy> enemiesModel= courseModel.getCurrentLesson().getGameMap().getEnemies();
        Array<CharacterView> enemiesView=new Array<>();
        for(Enemy enemyModel:enemiesModel){
            enemyModel.initialize();
            CharacterView enemyView=new CharacterView(enemyModel.getX(), enemyModel.getY(), enemyModel.getWidth(), enemyModel.getHeight(), EnemyController.getEnemyTextureType(enemyModel));
            enemiesView.add(enemyView);
            courseModel.getCurrentLesson().addObserver(enemyView);
        }
        courseModel.getCurrentLesson().addObserver(lessonView.getPlayer());
        lessonView.setEnemies(enemiesView);
    }

    /**
     * Manages the actual lesson model and view of the course.
     */
    private void manageLesson(){
        Lesson actualLesson= courseModel.getCurrentLesson();
        actualLesson.checkEndLesson();
        if(actualLesson.isFinished()){
            if(actualLesson.hasPlayerWon() && courseModel.getCurrentLessonId()!= courseModel.getLessonsAmount()){
                courseModel.loadActualLesson();
                lessonView=initializeLessonView(courseModel);
            }
            else if(actualLesson.hasPlayerWon()){
                lessonView.showEndGameWindow(true);
            }
            else{
                lessonView.showEndGameWindow(false);
            }
        }
    }

    /**
     * Manages the course.
     */
    private void manageCourse(){
        courseModel.checkEndCourse();
    }

    /**
     * Updates the lesson upper bar.
     */
    private void updateUpperBar(){
        Player playerModel= courseModel.getCurrentLesson().getGameMap().getPlayer();
        ((GameUpperBarView) lessonView.getUpperBar()).setHealth(playerModel.getCurrentHealth());
        ((GameUpperBarView) lessonView.getUpperBar()).setScore(playerModel.getScore());
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(playerController);
        lessonView.show();
    }

    @Override
    public void render(float delta) {
        lessonView.render(delta);
        updateUpperBar();
        playerController.managePlayer();
        powerUpController.managePowerUps();
        enemyController.manageEnemies();
        manageLesson();
        manageCourse();
    }

    @Override
    public void resize(int width, int height) {
        lessonView.resize(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        lessonView.dispose();
    }


    
    /**
     * Returns the lesson view.
     *
     * @return The lesson view.
     */
    public LessonView getLessonView() {
        return lessonView;
    }

    /**
     * Sets the lesson view.
     *
     * @param lessonView The lesson view
     */
    public void setLessonView(LessonView lessonView){
        this.lessonView=lessonView;
    }

    /**
     * Returns the course model.
     *
     * @return The course model
     */
    public Course getCourseModel() {
        return courseModel;
    }

    /**
     * Sets the course model.
     *
     * @param courseModel The course model
     */
    public void setCourseModel(Course courseModel){
        this.courseModel = courseModel;
    }

    /**
     * Returns the player controller.
     *
     * @return The player controller
     */
    public PlayerController getPlayerController() {
        return playerController;
    }

    /**
     * Returns the enemy controller.
     *
     * @return The enemy controller.
     */
    public EnemyController getEnemyController() {
        return enemyController;
    }
}
