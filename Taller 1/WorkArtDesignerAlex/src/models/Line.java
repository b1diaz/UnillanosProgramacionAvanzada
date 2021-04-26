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
public class Line extends Shape {
    Point end;
    
    public Line(Point end, Point start, Color color) {
        super(start, color);
        this.end = end;
    }
    
    public Point getEnd(){
        return end;
    }
    
    public void setEnd(Point end){
        this.end = end;
    }
    
}
