/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import DAO.ShapesDAO;
import java.awt.Graphics;
import models.Circle;
import models.Ellipse;
import models.Line;
import models.Rectangle;
import models.Square;

/**
 *
 * @author Diaz
 */
public class DrawHelper {
    Graphics graphics;

    public DrawHelper(Graphics graphics) {
        this.graphics = graphics;
    }
    public void draw(ShapesDAO shapes){
               
       for (Circle circle: shapes.getCircles()){
           graphics.setColor(circle.getColor());
           graphics.fillOval(circle.getStart().getX(), circle.getStart().getY(), circle.getRadius()*2 ,circle.getRadius()*2);                    
        }
       
       for (Line line: shapes.getLines()){
           graphics.setColor(line.getColor());
           graphics.drawLine(line.getStart().getX(), line.getStart().getY(),line.getEnd().getX(),line.getEnd().getY());                 
        }
       
       for (Ellipse ellipse: shapes.getEllipses()){
           graphics.setColor(ellipse.getColor());
           graphics.fillOval(ellipse.getStart().getX(), ellipse.getStart().getY(), ellipse.getWith() , ellipse.getHeight() );                 
        }     
       
       for ( Square square: shapes.getSquares()){
           graphics.setColor(square.getColor());
           graphics.fillRect(square.getStart().getX(), square.getStart().getY(), square.getWith() , square.getWith() );                 
        }
       
       for (Rectangle rectangle: shapes.getRectangles()){
           graphics.setColor(rectangle.getColor());
           graphics.fillRect(rectangle.getStart().getX(), rectangle.getStart().getY(), rectangle.getWith() , rectangle.getHeight());                 
        }
       
       
    }
    
}
