/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import DAO.ShapesDAO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import models.Circle;
import models.Ellipse;
import models.Line;
import models.Point;
import models.Rectangle;
import models.Square;
import com.google.gson.reflect.TypeToken;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Diaz
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    ShapesDAO shapes;
    MainPanel panel;
    int x,y,x2, y2;
    Color color_array[];
    FileDialog fd = new FileDialog(this, "Open", FileDialog.LOAD);  
    
    public MainWindow( ShapesDAO shapes ) {
        initComponents();
        this.shapes = shapes;  
        color_array = new Color[3];
        color_array[0] = Color.blue;
        color_array[1] = Color.red;
        color_array[2] = Color.yellow;
    }
    
    public void setPanel(){
    panel = new MainPanel(shapes);
    this.add(panel, BorderLayout.CENTER);
    this.setSize(1200,850);
    this.repaint();   
     
     this.ButtonAddImage.addActionListener(e -> this.imageLoad());
     
     this.ButtonRestored.addActionListener(e -> this.restoredJson());
    
    drawOnMouseEvent();
    }
    
    public void imageLoad()  
    {  
        fd.show(); 
        String pathImage = (fd.getDirectory() + fd.getFile());
        panel.setBackground(pathImage);
               
    }
    
    public void restoredJson()  
    {  
        fd.show();
        
        String pathFileJson = (fd.getDirectory() + fd.getFile());
        Gson gson = new Gson();
       
       
        try {
            JsonReader reader = new JsonReader(new FileReader(pathFileJson));
           // this.shapes = new ShapesDAO();
           try{
               JsonObject obj = new Gson().fromJson(reader, JsonObject.class );
               this.shapes.clear();
               
               Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
                for (Map.Entry<String, JsonElement> entry: entries) {
                    System.out.println();
                    String typeShape = entry.getKey();
                    switch(typeShape) {
                        case "lines":
                          JsonArray listLinesArray = entry.getValue().getAsJsonArray();
                          ArrayList<Line> listLines =  new Gson().fromJson(listLinesArray, new TypeToken<ArrayList<Line>>(){}.getType());
                          
                          for (Line line:listLines) {
                              this.shapes.addShape(line);
                          }
                          
                          break;
                        case "circles":
                          JsonArray listcirclesArray = entry.getValue().getAsJsonArray();
                          ArrayList<Circle> listCircles =  new Gson().fromJson(listcirclesArray, new TypeToken<ArrayList<Circle>>(){}.getType());
                          
                           for (Circle circle:listCircles) {
                              this.shapes.addShape(circle);
                           }
                          break;
                          case "ellipses":
                           JsonArray listEllipsesArray = entry.getValue().getAsJsonArray();
                           ArrayList<Ellipse> listEllipses =  new Gson().fromJson(listEllipsesArray, new TypeToken<ArrayList<Ellipse>>(){}.getType());
                           
                           for (Ellipse ellipse:listEllipses) {
                              this.shapes.addShape(ellipse);
                           }
                          break;
                          case "squares":
                           JsonArray listSquaresArray = entry.getValue().getAsJsonArray();
                           ArrayList<Square> listSquares =  new Gson().fromJson(listSquaresArray, new TypeToken<ArrayList<Square>>(){}.getType());
                           
                            for (Square square:listSquares) {
                              this.shapes.addShape(square);
                            }
                          break;
                           case "rectangles":
                           JsonArray listRectanglesArray = entry.getValue().getAsJsonArray();
                           ArrayList<Rectangle> listRectangles =  new Gson().fromJson(listRectanglesArray, new TypeToken<ArrayList<Rectangle>>(){}.getType());
                           
                           for (Rectangle rectangle:listRectangles) {
                              this.shapes.addShape(rectangle);
                            }
                          break;
                        default:
                      }                   
                }
                
                panel.repaint();
               
           }catch(Exception e){
               System.out.println(e);
           }
          
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        
        
       
       // ArrayList<Shape> shapesRestored = gson.fromJson("", ArrayList.class<Shape.class> );
        // ArrayList<Shape> shapesJson = new Gson().fromJson(reader, new ArrayList<Shape>().getClass());
       // ArrayList<Shape> shapesRestored = gson.fromJson("", ArrayList.class<Shape.class> );
        //System.out.println();
    } 
    
    private void drawOnMouseEvent()
    {
        MouseListener listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
              
            }

            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
                //if(ComboBoxShape.getSelectedItem().toString() ){}
                
                //Ellipse
                if(ComboBoxShape.getSelectedIndex()== 0 ){
                    Ellipse ellipse = new Ellipse(new Point(x, y), (x2-x), (y2-y), color_array[ComboBoxColor.getSelectedIndex()] );
                    shapes.addShape(ellipse);
                }
                
                
                //Circle
                if(ComboBoxShape.getSelectedIndex()== 1 ){
                    Circle circle = new Circle((x2-x)/2, new Point(x, y), color_array[ComboBoxColor.getSelectedIndex()] );
                    shapes.addShape(circle);
                }
                
                //Square
                if(ComboBoxShape.getSelectedIndex()== 2 ){
                    Square square = new Square(new Point(x, y), (x2-x),color_array[ComboBoxColor.getSelectedIndex()] );
                    shapes.addShape(square);
                }
                
                //Rectangle
                if(ComboBoxShape.getSelectedIndex()== 3 ){
                    Rectangle rectangle = new Rectangle(new Point(x, y), (x2-x), (y2-y), color_array[ComboBoxColor.getSelectedIndex()] );
                    shapes.addShape(rectangle);
                }
                
                //Line
                if (ComboBoxShape.getSelectedIndex()== 4) {
                    Line line = new Line(new Point(x,y), new Point(x2,y2), color_array[ComboBoxColor.getSelectedIndex()] );
                    shapes.addShape(line);
                }
                                
                panel.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
              
            }
        };
        
        this.panel.addMouseListener(listener);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ComboBoxShape = new javax.swing.JComboBox<>();
        ComboBoxColor = new javax.swing.JComboBox<>();
        ButtonAddImage = new javax.swing.JButton();
        ButtonSerialize = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextAreaSerialize = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        ButtonRestored = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ComboBoxShape.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ellipse", "Circle", "Square", "Rectangle", "Line" }));
        ComboBoxShape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxShapeActionPerformed(evt);
            }
        });

        ComboBoxColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blue", "Red", "Yellow" }));
        ComboBoxColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxColorActionPerformed(evt);
            }
        });

        ButtonAddImage.setText("Add Image");
        ButtonAddImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAddImageActionPerformed(evt);
            }
        });

        ButtonSerialize.setText("Serialize");
        ButtonSerialize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSerializeActionPerformed(evt);
            }
        });

        TextAreaSerialize.setColumns(20);
        TextAreaSerialize.setRows(5);
        jScrollPane1.setViewportView(TextAreaSerialize);

        jLabel1.setText("Shapes in Json:");

        ButtonRestored.setText("Restored ");
        ButtonRestored.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRestoredActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel2.setText("Restaure archivo Json ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(397, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonSerialize, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonAddImage, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(ComboBoxShape, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1)
                                    .addComponent(ButtonRestored, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(ComboBoxColor, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(16, 16, 16)))
                            .addGap(14, 14, 14)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(ComboBoxColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ComboBoxShape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(ButtonAddImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonSerialize)
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonRestored)
                .addGap(70, 70, 70))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBoxShapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxShapeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxShapeActionPerformed

    private void ComboBoxColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxColorActionPerformed

    private void ButtonAddImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAddImageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonAddImageActionPerformed

    private void ButtonSerializeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSerializeActionPerformed
        String json;
        Gson gson = new Gson();
        json = gson.toJson(shapes);
        System.out.println(json);
        TextAreaSerialize.setText(json);
    }//GEN-LAST:event_ButtonSerializeActionPerformed

    private void ButtonRestoredActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonRestoredActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonRestoredActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAddImage;
    private javax.swing.JButton ButtonRestored;
    private javax.swing.JButton ButtonSerialize;
    private javax.swing.JComboBox<String> ComboBoxColor;
    private javax.swing.JComboBox<String> ComboBoxShape;
    private javax.swing.JTextArea TextAreaSerialize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
