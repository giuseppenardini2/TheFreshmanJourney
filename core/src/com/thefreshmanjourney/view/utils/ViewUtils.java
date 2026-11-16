package com.thefreshmanjourney.view.utils;

/**
 * This class contains methods for performing operations concerning the view.
 */
public class ViewUtils {

    /**
     * Returns the provided y coordinate adapted to OpenGL coordinates.
     *
     * @param y The y coordinate
     * @param height The height
     * @return The provided y coordinate adapted to OpenGL coordinates
     */
    public static float adjustYCoordinate(float y, float height){
        return height-y-1;
    }


    /**
     * Returns if the point is inside the triangle.
     * Let's assume that A, B, and C vertices are labeled in counterclockwise order, with A being the bottom-left vertex.
     *
     * @param x The x coordinate of the point
     * @param y The y coordinate of the point
     * @param xA The x coordinate of the A vertex
     * @param yA The y coordinate of the A vertex
     * @param xB The x coordinate of the B vertex
     * @param yB The y coordinate of the B vertex
     * @param xC The x coordinate of the C vertex
     * @param yC The y coordinate of the C vertex
     * @return If the point is inside the triangle
     */
    public static boolean isPointInTriangle(float x, float y, float xA, float yA, float xB, float yB, float xC, float yC){
        if(-(yB-yA)*(x-xB)+(xB-xA)*(y-yB)<0)
            return false;
        else if(-(yC-yB)*(x-xC)+(xC-xB)*(y-yC)<0)
            return false;
        else if(-(yA-yC)*(x-xA)+(xA-xC)*(y-yA)<0)
            return false;
        return true;
    }

}
