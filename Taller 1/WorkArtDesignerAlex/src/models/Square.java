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
public class Square extends Shape{
    
    int with;

    public Square(Point start,int with, Color color) {
        super(start, color);
        this.with = with;
    }

    public int getWith() {
        return with;
    }

    public void setWith(int with) {
        this.with = with;
    }
     
}
