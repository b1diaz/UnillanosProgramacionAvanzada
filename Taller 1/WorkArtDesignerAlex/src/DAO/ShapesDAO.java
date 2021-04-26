/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import models.Circle;
import models.Ellipse;
import models.Line;
import models.Rectangle;
import models.Square;

/**
 *
 * @author Diaz
 */
public class ShapesDAO {
    ArrayList<Line> lines;
    ArrayList<Circle> circles;
    ArrayList<Ellipse> ellipses;
    ArrayList<Square> squares;
    ArrayList<Rectangle> rectangles;

    

    public ShapesDAO() {
        lines = new ArrayList();
        circles = new ArrayList();
        ellipses = new ArrayList();
        squares = new ArrayList();
        rectangles = new ArrayList();
    }
    
    
     public void clear(){
        lines = new ArrayList();
        circles = new ArrayList();
        ellipses = new ArrayList();
        squares = new ArrayList();
        rectangles = new ArrayList();
    }
    
    //Ellipse
    public void addShape(Ellipse ellipse){
        ellipses.add(ellipse);
    }
    
     public ArrayList<Ellipse> getEllipses(){
        return ellipses;
    }
    
    //Circle
    public void addShape(Circle circle){
        circles.add(circle);
    }
    
    public ArrayList<Circle> getCircles(){
     return circles;
    }
    
    //Square
    public void addShape(Square square){
        squares.add(square);
    }
    
    public ArrayList<Square> getSquares() {
        return squares;
    }
    
    //Rectangle
    public void addShape(Rectangle rectangle){
        rectangles.add(rectangle);
    }
    
     public ArrayList<Rectangle> getRectangles() {
        return rectangles;
    }
    
    //Line
    public void addShape(Line line){
        lines.add(line);
    }
    
    public ArrayList<Line> getLines(){
        return lines;
    }

}
