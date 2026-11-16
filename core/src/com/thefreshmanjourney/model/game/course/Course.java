package com.thefreshmanjourney.model.game.course;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.thefreshmanjourney.model.game.GameObject;
import com.thefreshmanjourney.model.game.character.enemy.*;
import com.thefreshmanjourney.model.game.character.enemy.boss.Algorithm;
import com.thefreshmanjourney.model.game.character.enemy.boss.Database;
import com.thefreshmanjourney.model.game.character.player.Player;
import com.thefreshmanjourney.model.game.map.*;
import com.thefreshmanjourney.model.game.lesson.Lesson;
import com.thefreshmanjourney.model.game.powerups.*;
import com.thefreshmanjourney.model.profile.ProfileStatistics;

import java.io.*;
import java.util.Arrays;

/**
 * This class represents a course that manages the lessons.
 */
public class Course {

    /**
     * The course name.
     */
    private final String name;

    /**
     * The current lesson id.
     */
    private int currentLessonId;

    /**
     * The current lesson.
     */
    private Lesson currentLesson;

    /**
     * The lessons amount.
     */
    private final int lessonsAmount;

    /**
     * A boolean flag indicating if the course has been completed.
     */
    private boolean completed;


    /**
     * Creates a course.
     *
     * @param lessonsAmount The course lessons amount
     * @param name The course name
     */
    public Course(int lessonsAmount, String name) {
        this.lessonsAmount = lessonsAmount;
        this.name=name;
        completed=false;
        currentLessonId =0;
    }

    /**
     * Checks if the course is finished.
     */
    public void checkEndCourse(){
        if(!completed) {
        	setCompleted();
        }
    }

    /**
     * Uploads the actual lesson of this course from a file.
     *
     * @param lessonFile The lesson file
     */
    public void loadActualLesson(FileHandle lessonFile){
        int[][] integerLevelMatrix=loadMapMatrix(lessonFile);
        currentLesson = new Lesson();
        for(int y=0; y<integerLevelMatrix.length; y++)
            for(int x=0; x<integerLevelMatrix[y].length; x++)
                loadGameMapBlock(integerLevelMatrix[y][x], x, y);
    }
    /**
     * Uploads the actual lesson of this course.
     */
    public void loadActualLesson() {
        currentLessonId++;
        ProfileStatistics.incrementLessonsAttended();

        String levelFile = "files/map/" + "level" + currentLessonId + ".txt";
        loadActualLesson(Gdx.files.internal(levelFile));
    }

    /**
     * Returns a integer matrix map read from the provided file.
     *
     * @param matrixFile The file of the integer matrix map
     * @return The matrix map
     */
    private static int[][] loadMapMatrix(FileHandle matrixFile){
        int[][] matrix=null;
        try(BufferedReader bufferedReader=new BufferedReader(matrixFile.reader())){
            matrix=bufferedReader.lines()
                    .map(line -> Arrays.stream(line.split("\\s+"))
                            .mapToInt(Integer::parseInt)
                            .toArray())
                    .toArray(int[][]::new);
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return matrix;
    }

    /**
     * Saves the actual lesson to the provided file.
     *
     * @param destinationFile The file in which to save the lesson
     */
    public void saveActualLesson(FileHandle destinationFile){
        int[][] integerMatrixMap=new int[GameMap.HEIGHT][GameMap.WIDTH];
        GameMap gameMap= currentLesson.getGameMap();
        saveGameMapBlock(gameMap.getPlayer(), integerMatrixMap);
        for(Enemy enemy:gameMap.getEnemies()){
            saveGameMapBlock(enemy, integerMatrixMap);
        }
        for(PowerUp powerUp: gameMap.getPowerUps()){
            saveGameMapBlock(powerUp, integerMatrixMap);
        }
        for(MapBlock[] row:gameMap.getMap()){
            for(MapBlock mapBlock:row){
                saveGameMapBlock(mapBlock, integerMatrixMap);
            }
        }
        saveMapMatrix(integerMatrixMap, destinationFile);
    }

    /**
     * Saves the integer matrix map to the provided file.
     *
     * @param integerMatrixMap The integer matrix map to save
     * @param destinationFile The file in which to save the integer matrix map
     */
    private static void saveMapMatrix(int[][] integerMatrixMap, FileHandle destinationFile){
        try(BufferedWriter writer = new BufferedWriter(destinationFile.writer(false))) {
            Arrays.stream(integerMatrixMap)
                    .map(row -> Arrays.stream(row)
                            .mapToObj(String::valueOf)
                            .reduce((s1, s2) -> s1 + " " + s2)
                            .orElse(""))
                    .forEach(rowString -> {
                        try {
                            writer.write(rowString);
                            writer.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds the block corresponding to the provided number in the game map at the provided coordinates.
     *
     * @param number The block number
     * @param x The x coordinate of the game map block
     * @param y The y coordinate of the game map block
     */
    private void loadGameMapBlock(int number, int x, int y) {
        switch(number){
            case 1:
                currentLesson.getGameMap().setBlock(new MapBlock(x, y, MapBlockType.DESK));
                break;
            case 2:
                currentLesson.getGameMap().setBlock(new MapBlock(x, y, MapBlockType.TILE));
                break;
            case 3:
                currentLesson.getGameMap().setBlock(new MapBlock(x, y, MapBlockType.JUNK));
                break;
            case 4:
                currentLesson.getGameMap().setBlock(new MapBlock(x, y, MapBlockType.EXIT, false));
                break;
            case 5:
                currentLesson.getGameMap().setBlock(new MapBlock(x, y, MapBlockType.WALL));
                break;
            case 10:
                currentLesson.getGameMap().setBlock(new MapBlock(x, y, MapBlockType.TILE));
                currentLesson.getGameMap().setPlayer(Player.getInstance(x, y));
                break;
            case 12:
                currentLesson.getGameMap().setBlock(new MapBlock(x, y, MapBlockType.TILE));
                currentLesson.getGameMap().addEnemy(new Homebody(x, y));
                break;
            case 13:
                currentLesson.getGameMap().setBlock(new MapBlock(x, y, MapBlockType.TILE));
                currentLesson.getGameMap().addEnemy(new Geek(x, y));
                break;
            case 14:
                currentLesson.getGameMap().setBlock(new MapBlock(x, y, MapBlockType.TILE));
                currentLesson.getGameMap().addEnemy(new Gamer(x, y));
                break;
            case 15:
                currentLesson.getGameMap().setBlock(new MapBlock(x, y, MapBlockType.TILE));
                currentLesson.getGameMap().addEnemy(new Hacker(x, y));
                break;
            case 16:
                currentLesson.getGameMap().setBlock(new MapBlock(x, y, MapBlockType.TILE));
                currentLesson.getGameMap().addEnemy(new Algorithm(x, y));
                break;
            case 17:
                currentLesson.getGameMap().setBlock(new MapBlock(x, y, MapBlockType.TILE));
                currentLesson.getGameMap().addEnemy(new Database(x, y));
                break;
            case 20:
                currentLesson.getGameMap().setBlock(new MapBlock(x, y, MapBlockType.JUNK));
                currentLesson.getGameMap().addPowerUp(new Shield(x, y));
                break;
            case 21:
                currentLesson.getGameMap().setBlock(new MapBlock(x, y, MapBlockType.JUNK));
                currentLesson.getGameMap().addPowerUp(new MoreCoffee(x, y));
                break;
            case 22:
                currentLesson.getGameMap().setBlock(new MapBlock(x, y, MapBlockType.JUNK));
                currentLesson.getGameMap().addPowerUp(new MoreHeart(x, y));
                break;
            case 23:
                currentLesson.getGameMap().setBlock(new MapBlock(x, y, MapBlockType.JUNK));
                currentLesson.getGameMap().addPowerUp(new JunkFood(x, y));
                break;
            case 24:
                currentLesson.getGameMap().setBlock(new MapBlock(x, y, MapBlockType.JUNK));
                currentLesson.getGameMap().addPowerUp(new EnergyDrink(x, y));
                break;
            case 25:
                currentLesson.getGameMap().setBlock(new MapBlock(x, y, MapBlockType.JUNK));
                currentLesson.getGameMap().addPowerUp(new EnergyBar(x, y));
                break;
            case 26:
                currentLesson.getGameMap().setBlock(new MapBlock(x, y, MapBlockType.JUNK));
                currentLesson.getGameMap().addPowerUp(new Pillow(x, y));
                break;
            case 27:
                currentLesson.getGameMap().setBlock(new MapBlock(x, y, MapBlockType.JUNK));
                currentLesson.getGameMap().addPowerUp(new BrokenHeart(x, y));
                break;
        }
    }

    /**
     * Adds the game map block to the integer matrix map.
     *
     * @param gameObject The game object.
     * @param integerMatrixMap The integer matrix map.
     */
    private void saveGameMapBlock(GameObject gameObject, int[][] integerMatrixMap){
        int x=(int) gameObject.getX();
        int y=(int) gameObject.getY();
        if(integerMatrixMap[y][x]==0){
            if(gameObject.getClass().getSimpleName().equals("MapBlock")){
                switch(((MapBlock)gameObject).getType()){
                    case DESK:
                        integerMatrixMap[y][x]=1;
                        break;
                    case TILE:
                        integerMatrixMap[y][x]=2;
                        break;
                    case JUNK:
                        integerMatrixMap[y][x]=3;
                        break;
                    case EXIT:
                        integerMatrixMap[y][x]=4;
                        break;
                    case WALL:
                        integerMatrixMap[y][x]=5;
                        break;
                }
            }
            switch(gameObject.getClass().getSimpleName()){
                case "Player":
                    integerMatrixMap[y][x]=10;
                    break;
                case "Homebody":
                    integerMatrixMap[y][x]=12;
                    break;
                case "Geek":
                    integerMatrixMap[y][x]=13;
                    break;
                case "Gamer":
                    integerMatrixMap[y][x]=14;
                    break;
                case "Hacker":
                    integerMatrixMap[y][x]=15;
                    break;
                case "Algorithm":
                    integerMatrixMap[y][x]=16;
                    break;
                case "Database":
                    integerMatrixMap[y][x]=17;
                    break;
                case "Shield":
                    integerMatrixMap[y][x]=20;
                    break;
                case "MoreCoffee":
                    integerMatrixMap[y][x]=21;
                    break;
                case "MoreHearth":
                    integerMatrixMap[y][x]=22;
                    break;
                case "JunkFood":
                    integerMatrixMap[y][x]=23;
                    break;
                case "EnergyDrink":
                    integerMatrixMap[y][x]=24;
                    break;
                case "EnergyBar":
                    integerMatrixMap[y][x]=25;
                    break;
                case "SleepyPillow":
                    integerMatrixMap[y][x]=26;
                    break;
                case "BrokenHeart":
                    integerMatrixMap[y][x]=27;
                    break;
            }
        }
    }


    /**
     * Returns the current lesson id.
     *
     * @return The current lesson id
     */
    public int getCurrentLessonId() {
        return currentLessonId;
    }

    /**
     * Sets the current lesson id.
     *
     * @param currentLessonId The current lesson id
     */
    public void setCurrentLessonId(int currentLessonId) {
        this.currentLessonId=currentLessonId;
    }

    /**
     * Returns the lesson amount.
     *
     * @return The lesson amount
     */
    public int getLessonsAmount() {
        return lessonsAmount;
    }

    /**
     * Returns if this course is completed.
     *
     * @return If this course is completed
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Sets this course completed.
     */
    private void setCompleted() {
        if(!completed){
            completed=true;
            ProfileStatistics.incrementDegreesObtained();
        }
    }

    /**
     * Returns the course name.
     *
     * @return The course name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current lesson.
     *
     * @return The current lesson
     */
    public Lesson getCurrentLesson(){
        return currentLesson;
    }

}