/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.Color;

/**
 *
 * @author Diaz
 */
public class Shape {
    
    Point start;
    Color color;

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public Shape(Point start, Color color) {
        this.start = start;
        this.color = color;
    }
  
}
