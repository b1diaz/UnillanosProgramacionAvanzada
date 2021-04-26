/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import DAO.ShapesDAO;
import java.awt.Color;
import models.Circle;
import models.Line;
import models.Point;
import views.MainWindow;

/**
 *
 * @author Diaz
 */
public class MakerController {
    ShapesDAO shapes;
    MainWindow mainwindow;
    
    public MakerController(){
        shapes = new ShapesDAO();
       // shapes.addShape(new Circle(50,new Point(400,400),Color.red));
        //Line line = new Line(new Point(500, 500), new Point(700, 700), Color.green);
        //shapes.addShape(line);
        mainwindow = new MainWindow(shapes);
        mainwindow.setPanel();
    }
    
    public void start(){
     mainwindow.setVisible(true);
    }
    
}
