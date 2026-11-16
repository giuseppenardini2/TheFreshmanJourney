package com.thefreshmanjourney.view.game.map;

import com.badlogic.gdx.utils.ObjectSet;
import com.thefreshmanjourney.model.game.map.MapBlock;
import com.thefreshmanjourney.view.types.MapBlockTextureType;
import com.thefreshmanjourney.view.utils.ViewUtils;

/**
 * This class represents a game map view.
 */
public class GameMapView {

    /**
     * The game map view matrix.
     */
    private MapBlockView[][] map;

    /**
     * The game map view height.
     */
    private final int height;

    /**
     * The game map view width.
     */
    private final int width;

    /**
     * Creates a game map view.
     *
     * @param mapModel The game map model matrix
     */
    public GameMapView(MapBlock[][] mapModel){
        height=mapModel.length;
        width=mapModel[0].length;
        map=new MapBlockView[height][width];
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                chooseBlock(mapModel[y][x], x, (int) ViewUtils.adjustYCoordinate((float) y, (float) height));
            }
        }
    }

    /**
     * Adds the game map block view corresponding to the game map block model at the provided coordinates.
     *
     * @param block The game map block model
     * @param x The x coordinate of the game map block view
     * @param y The y coordinate of the game map block view
     */
    private void chooseBlock(MapBlock block, int x, int y){
        MapBlockView blockView;
        switch(block.getType()){
            case WALL:
                blockView = new MapBlockView(x, y, MapBlockTextureType.WALL);
                map[y][x] = blockView;
                break;
            case DESK:
                blockView = new MapBlockView(x, y, MapBlockTextureType.DESK);
                map[y][x] = blockView;
                break;
            case JUNK:
                blockView = new MapBlockView(x, y, MapBlockTextureType.JUNK);
                map[y][x] = blockView;
                break;
            case TILE:
                blockView = new MapBlockView(x, y, MapBlockTextureType.TILE);
                map[y][x] = blockView;
                break;
            case EXIT:
                blockView = new MapBlockView(x, y, MapBlockTextureType.EXIT);
                map[y][x] = blockView;
        }
    }

    /**
     * Returns if the x and y coordinates are valid for this game map view.
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @return If the coordinates are valid
     */
    private boolean checkXY(int x, int y){
        return (x>=0 && x<width) && (y>=0 && y<height);
    }

    /**
     * Returns the game map blocks views in the provided area.
     *
     * @param x The x coordinate of the area center
     * @param y The y coordinate of the area center
     * @param radius The area radius
     * @return The game map blocks views in the area
     */
    private ObjectSet<MapBlockView> getBlocksInArea(int x, int y, int radius){
        ObjectSet<MapBlockView> blocks=new ObjectSet<>();
        for(int i=0; i<=radius; i++){
            if(checkXY(x+i, y))
                blocks.add(map[y][x+i]);
            if(checkXY(x-i, y))
                blocks.add(map[y][x-i]);
            if(checkXY(x, y+i))
                blocks.add(map[y+i][x]);
            if(checkXY(x, y-i))
                blocks.add(map[y-i][x]);
        }
        return blocks;
    }
    /**
     * Returns the game map blocks views in the provided area.
     *
     * @param x The x coordinate of the area center
     * @param y The y coordinate of the area center
     * @param radius The area radius
     * @return The game map blocks views in the area
     */
    public ObjectSet<MapBlockView> getBlocksInArea(float x, float y, int radius){
        ObjectSet<MapBlockView> blocksInArea=getBlocksInArea((int) Math.floor(x), (int) Math.floor(y), radius);
        blocksInArea.addAll(getBlocksInArea((int) Math.ceil(x), (int) Math.ceil(y), radius));
        return blocksInArea;
    }


    /**
     * Returns the game map view matrix.
     *
     * @return The game map view matrix
     */
    public MapBlockView[][] getMap() {
        return map;
    }

    /**
     * Returns the game map view height.
     *
     * @return The game map view height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the game map view width.
     *
     * @return The game map view width
     */
    public int getWidth() {
        return width;
    }

}
