package com.JetLag.game.engine.graphics.sprites.grid;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Grid. Handles the grid shown on the screen
 */
public class Grid {

    LinkedList<GridHorizontalLine> gridH;
    LinkedList<GridVerticalLine> gridV;

    OrthographicCamera cam;
    float margin = 3; //How many lines outside of view
    float spacing = 150;
    float thick = 15;
    float x;
    float y;

    public Grid(OrthographicCamera cam){
        this.cam = cam;
        this.x = cam.position.x - (cam.viewportWidth / 2) - margin*spacing;
        this.y = cam.position.y - (cam.viewportHeight / 2) - margin*spacing;
        gridH = new LinkedList<GridHorizontalLine>();
        gridV = new LinkedList<GridVerticalLine>();

        for( float i = cam.position.y + (cam.viewportHeight/2) + margin*spacing; i>= (cam.position.y - (cam.viewportHeight/2)) - margin*spacing; i -= spacing){
            gridH.add(new GridHorizontalLine(cam, thick, new Vector3(x, i, 0), margin,spacing));
        }

        for (float j = cam.position.x - (cam.viewportWidth/2) - margin*spacing; j <= cam.position.x + (cam.viewportWidth/2) + margin*spacing; j += spacing){
            gridV.add(new GridVerticalLine(cam, thick, new Vector3(j, y, 0),margin,spacing));
        }
    }

    public void update(){
        for (GridHorizontalLine l : gridH){
            l.update();
        }
        for (GridVerticalLine l : gridV){
            l.update();
        }

        // If the lowest horizontal line is off the screen and margin
        if(gridH.getLast().getPosition().y < (cam.position.y - (cam.viewportHeight/2)) - margin*spacing ){
            gridH.addFirst(new GridHorizontalLine(cam,thick,new Vector3(x,gridH.getFirst().position.y + spacing,0),margin,spacing));
            gridH.removeLast();
        }
        // It the highest horizontal line is off the screen and margin
         else if (gridH.getFirst().getPosition().y > (cam.position.y + (cam.viewportHeight/2)) + margin*spacing ) {
            gridH.addLast(new GridHorizontalLine(cam,thick,new Vector3(x,gridH.getLast().position.y - spacing,0),margin,spacing));
            gridH.removeFirst();
        }

        if (gridV.getLast().getPosition().x >= cam.position.x + cam.viewportWidth/2 + margin*spacing){
            gridV.addFirst(new GridVerticalLine(cam,thick,new Vector3(gridV.getFirst().position.x-spacing,y,0),margin,spacing));
            gridV.removeLast();
        }

        else if ( gridV.getFirst().getPosition().x <= cam.position.x - cam.viewportWidth/2 - margin*spacing ){
            gridV.addLast(new GridVerticalLine(cam,thick,new Vector3(gridV.getLast().position.x + spacing,y,0),margin,spacing));
            gridV.removeFirst();
        }
    }

    public void render(ShapeRenderer sr){
        for ( GridLine l : gridH ){
            l.render(sr);
        }
        for ( GridLine l : gridV ){
            l.render(sr);
        }
    }
}
