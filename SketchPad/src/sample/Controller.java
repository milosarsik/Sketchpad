package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import shapes.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Controller {
    int numDrawn = 0;
    int movingNum = 0;

    boolean cleared = false;

    private String mode, color;
    double x0, y0;
    double x1, y1;
    ArrayList<Double> polygonX = new ArrayList<Double>();
    ArrayList<Double> polygonY = new ArrayList<Double>();

    public GraphicsContext g;
    public ColorPicker cpLine = new ColorPicker(Color.BLACK);
    public ColorPicker cpFill = new ColorPicker(Color.BLACK);


    public MyScribble myScribble;
    public MyLine myLine;
    public MyRectangle myRectangle = new MyRectangle();
    public MyEllipse myEllipse = new MyEllipse();
    public MySquare mySquare = new MySquare();
    public Circle globalCircle = new Circle();


    Stack<MyShape> undoHistory = new Stack<>();
    Stack<MyShape> redoHistory = new Stack<>();

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem scribble;

    @FXML
    private MenuItem straightLine;

    @FXML
    private MenuItem rectangle;

    @FXML
    private MenuItem ellipse;

    @FXML
    private MenuItem square;

    @FXML
    private MenuItem circle;

    @FXML
    private MenuItem polygon;

    @FXML
    private MenuItem polygon1;

    @FXML
    private MenuItem black;

    @FXML
    private MenuItem blue;

    @FXML
    private MenuItem red;

    @FXML
    private MenuItem clear;

    @FXML
    private MenuItem select;

    @FXML
    private MenuItem select1;

    @FXML
    private MenuItem copyRecent;

    @FXML
    private Menu selectAndMove;

    @FXML
    private Menu options;

    @FXML
    private MenuItem undo;

    @FXML
    private MenuItem redo;

    @FXML
    private Canvas drawingCanvas;

    @FXML
    void copyRecent(ActionEvent event) {
//        Scribble temp = (Scribble) recent.pop();
//        g.beginPath();
//        g.lineTo(temp.x0, temp.y0);
//
//        double[] tempx = temp.getAllXValues();
//        double[] tempy = temp.getAllYValues();
//
//        for (int i = 0; i < tempx.length; i++){
//            g.lineTo(tempx[i], tempy[i]);
//            g.stroke();
//        }
//
//        g.lineTo(temp.x1, temp.y1);
//        g.stroke();
//        g.closePath();

    }

    /*
     * Start of setFill
     * */
    @FXML
    void setFillYes(ActionEvent event) {
        if(color.equals("black")){
            cpFill = new ColorPicker(Color.BLACK);
        }
        else if(color.equals("blue")){
            cpFill = new ColorPicker(Color.BLUE);
        }
        else if(color.equals("red")){
            cpFill = new ColorPicker(Color.RED);
        }
    }

    @FXML
    void setFillTransparent(ActionEvent event){
        cpFill = new ColorPicker(Color.TRANSPARENT);

    }

    /*
    * Start of setColor
    * */
    @FXML
    void setColorBlack(ActionEvent event) {
        color = "black";

        cpLine = new ColorPicker(Color.BLACK);
        cpFill = new ColorPicker(Color.BLACK);
    }

    @FXML
    void setColorBlue(ActionEvent event) {
        color = "blue";

        cpLine = new ColorPicker(Color.BLUE);
        cpFill = new ColorPicker(Color.BLUE);
    }

    @FXML
    void setColorRed(ActionEvent event) {
        color = "red";

        cpLine = new ColorPicker(Color.RED);
        cpFill = new ColorPicker(Color.RED);
    }
    /*
     * End of setColor
     * */

    /*
    * Start of setMode
    * */
    @FXML
    void setModeEllipse(ActionEvent event) {
        mode = "ellipse";
    }

    @FXML
    void setModeOpenPolygon(ActionEvent event) {
        mode = "open polygon";
    }

    @FXML
    void setModeClosedPolygon(ActionEvent event) {
        mode = "closed polygon";
    }

    @FXML
    void setModeRectangle(ActionEvent event) {
        mode = "rectangle";
    }

    @FXML
    void setModeScribble(ActionEvent event) {
        mode = "scribble";
    }

    @FXML
    void setModeSquare(ActionEvent event) {
        mode = "square";
    }

    @FXML
    void setModeStraightLine(ActionEvent event) {
        mode = "straight line";
    }

    @FXML
    void setModeCircle(ActionEvent event) {
        mode = "circle";
    }
    /*
    * End of setMode
    * */

    /*
    * Clear canvas
    * */
    @FXML
    void clearCanvas(ActionEvent event) {
        g = drawingCanvas.getGraphicsContext2D();
        g.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());

        undoHistory.push(new ClearAction());
    }







    /*
    * Start of draw event
    * */
    @FXML
    void startDraw(MouseEvent event) {
        g = drawingCanvas.getGraphicsContext2D();
        g.setStroke(cpLine.getValue());
        x0 = event.getX();
        y0 = event.getY();

        if(mode.equals("scribble")){
            g.setStroke(cpLine.getValue());
            g.beginPath();
            g.lineTo(event.getX(), event.getY());

            myScribble = new MyScribble();

            myScribble.setGraphicsContext(drawingCanvas.getGraphicsContext2D());
            myScribble.setColor(cpLine);
            myScribble.setStartPoint(event.getX(), event.getY());
        }
        else if(mode.equals("straight line")){
            g.setStroke(cpLine.getValue());

            myLine = new MyLine();

            myLine.setGraphicsContext(g);
            myLine.setColor(cpLine);
            myLine.setStartPoint(event.getX(), event.getY());
        }
        else if(mode.equals("rectangle")){
            g.setStroke(cpLine.getValue());
            g.setFill(cpFill.getValue());

            myRectangle = new MyRectangle();

            myRectangle.setGraphicsContext(g);
            myRectangle.setColor(cpLine);
            myRectangle.setFill(cpFill);
            myRectangle.setStartPoint(event.getX(), event.getY());
        }
        else if(mode.equals("ellipse")){
            g.setStroke(cpLine.getValue());
            g.setFill(cpFill.getValue());

            myEllipse = new MyEllipse();

            myEllipse.setGraphicsContext(g);
            myEllipse.setColor(cpLine);
            myEllipse.setFill(cpFill);
            myEllipse.setCenterPoint(event.getX(), event.getY());
        }
        else if(mode.equals("square")){
            g.setStroke(cpLine.getValue());
            g.setFill(cpFill.getValue());

            mySquare = new MySquare();
            mySquare.setGraphicsContext(g);
            mySquare.setColor(cpLine);
            mySquare.setFill(cpFill);
            mySquare.setStartPoint(event.getX(), event.getY());
        }
        else if(mode.equals("circle")) {
            g.setStroke(cpLine.getValue());
            g.setFill(cpFill.getValue());
            globalCircle.setCenterX(x0);
            globalCircle.setCenterY(y0);
        }
        else if(mode.equals("open polygon") || mode.equals("closed polygon")){
            g.setStroke(cpLine.getValue());
            g.setFill(cpFill.getValue());
            polygonX.add(x0);
            polygonY.add(y0);

        }
    }





    /*
    * Start of drag event
    * */
    @FXML
    void drag(MouseEvent event) {
        if(mode.equals("scribble")){
            g.lineTo(event.getX(), event.getY());
            g.stroke();

            myScribble.addPoint(event.getX(), event.getY());
        }
        if(mode.equals("open polygon") || mode.equals("closed polygon")){
            polygonX.add(event.getX());
            polygonY.add(event.getY());

            try{
                Thread.sleep(250);
            }
            catch (Exception e){

            }
        }
    }
    /*
    * End of drag event
    * */


    @FXML
    void endDraw(MouseEvent event) {
        x1 = event.getX();
        y1 = event.getY();

        if(mode.equals("scribble")){
            g.lineTo(event.getX(), event.getY());
            g.stroke();

            myScribble.setEndPoint(event.getX(), event.getY());

            // Add to stack
            undoHistory.push(myScribble);
        }
        else if(mode.equals("straight line")){
            myLine.setEndPoint(event.getX(), event.getY());
            myLine.draw();

            // Add to stack
            undoHistory.push(myLine);
        }
        else if(mode.equals("rectangle")){
            myRectangle.setEndPoint(event.getX(), event.getY());
            myRectangle.setWidth();
            myRectangle.setHeight();
            myRectangle.check();

            myRectangle.draw();

            undoHistory.push(myRectangle);
        }
        else if(mode.equals("ellipse")){
            myEllipse.setEndPoint(event.getX(), event.getY());
            myEllipse.setRadius();
            myEllipse.check();

            myEllipse.draw();

            undoHistory.push(myEllipse);
        }
        else if(mode.equals("square")) {
            mySquare.setEndPoint(event.getX(), event.getY());
            mySquare.setWidth();
            mySquare.setHeight();
            mySquare.check();

            mySquare.draw();

            undoHistory.push(mySquare);

//            globalRectangle.setWidth(Math.abs(x1 - x0));
//            globalRectangle.setHeight(Math.abs(y1 - y0));
//
//            if (x0 > x1) {
//                globalRectangle.setX(x1);
//            }
//            if (y0 > y1) {
//                globalRectangle.setY(y1);
//            }
//
//            g.fillRect(globalRectangle.getX(), globalRectangle.getY(), globalRectangle.getWidth(), globalRectangle.getWidth());
//            g.strokeRect(globalRectangle.getX(), globalRectangle.getY(), globalRectangle.getWidth(), globalRectangle.getWidth());
        }
        else if(mode.equals("circle")) {
            globalCircle.setRadius((Math.abs(x1 - x0) + Math.abs(y1 - y0)) / 2);

            if(x0 > x1) {
                globalCircle.setCenterX(x1);
            }
            if(y0 > y1) {
                globalCircle.setCenterY(y1);
            }

            g.fillOval(globalCircle.getCenterX(), globalCircle.getCenterY(), globalCircle.getRadius(), globalCircle.getRadius());
            g.strokeOval(globalCircle.getCenterX(), globalCircle.getCenterY(), globalCircle.getRadius(), globalCircle.getRadius());
        }
        else if(mode.equals("open polygon")){
            polygonX.add(x1);
            polygonY.add(y1);

            double[] polyX = new double[polygonX.size()];
            double[] polyY = new double[polygonY.size()];

            for(int i = 0; i < polygonX.size(); i++){
                polyX[i] = polygonX.get(i);
                polyY[i] = polygonY.get(i);
            }

            polygonX.clear();
            polygonY.clear();

            g.strokePolyline(polyX, polyY, polyX.length);
        }
        else if(mode.equals("closed polygon")){
            polygonX.add(x1);
            polygonY.add(y1);

            double[] polyX = new double[polygonX.size()];
            double[] polyY = new double[polygonY.size()];

            for(int i = 0; i < polygonX.size(); i++){
                polyX[i] = polygonX.get(i);
                polyY[i] = polygonY.get(i);
            }

            polygonX.clear();
            polygonY.clear();

            g.strokePolygon(polyX, polyY, polyX.length);
            g.fillPolygon(polyX, polyY, polyX.length);
        }
    }

    @FXML
    void undo(ActionEvent event) {

        if(!undoHistory.empty()){
            MyShape removedShape = undoHistory.pop();
            redoHistory.push(removedShape);

            Stack<MyShape> tempStack = new Stack<>();

            g.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());

            Iterator iterator = undoHistory.iterator();
            while(iterator.hasNext()){
                tempStack.push((MyShape) iterator.next());
            }

            while(!tempStack.isEmpty()){
                MyShape tempShape = tempStack.pop();

                if(tempShape.getClass() == MyScribble.class){
                    MyScribble tempScribble = (MyScribble) tempShape;
                    tempScribble.draw();
                }
                else if(tempShape.getClass() == MyLine.class){
                    MyLine tempLine = (MyLine) tempShape;
                    tempLine.draw();
                }
                else if(tempShape.getClass() == MyRectangle.class){
                    MyRectangle tempRectangle = (MyRectangle) tempShape;
                    tempRectangle.draw();
                }
                else if(tempShape.getClass() == MyEllipse.class)
                {
                    MyEllipse tempEllipse = (MyEllipse) tempShape;
                    tempEllipse.draw();
                }
            }
        }
    }

    @FXML
    void redo(ActionEvent event) {
        if(!redoHistory.empty()){
            MyShape removedShape = redoHistory.pop();
            undoHistory.push(removedShape);

            Stack<MyShape> tempStack = new Stack<>();

            g.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());

            Iterator iterator = undoHistory.iterator();
            while(iterator.hasNext()){
                tempStack.push((MyShape) iterator.next());
            }

            while(!tempStack.isEmpty()){
                MyShape tempShape = tempStack.pop();

                if(tempShape.getClass() == MyScribble.class){
                    MyScribble tempScribble = (MyScribble) tempShape;
                    tempScribble.draw();
                }
                else if(tempShape.getClass() == MyLine.class){
                    MyLine tempLine = (MyLine) tempShape;
                    tempLine.draw();
                }
                else if(tempShape.getClass() == MyRectangle.class){
                    MyRectangle tempRectangle = (MyRectangle) tempShape;
                    tempRectangle.draw();
                }
                else if(tempShape.getClass() == MyEllipse.class)
                {
                    MyEllipse tempEllipse = (MyEllipse) tempShape;
                    tempEllipse.draw();
                }
            }
        }
    }
}

//        if(mode.equals("move")){
//            Scribble temp = (Scribble) allDrawn.get(movingNum - 1);
//            temp.move(x0, x1);
//            allDrawn.set(movingNum - 1, temp);
//
//            g.beginPath();
//            g.lineTo(temp.x0, temp.y0);
//
//            double[] tempx = temp.getAllXValues();
//            double[] tempy = temp.getAllYValues();
//
//            for (int i = 0; i < tempx.length; i++){
//                g.lineTo(tempx[i], tempy[i]);
//                g.stroke();
//            }
//
//            g.lineTo(temp.x1, temp.y1);
//            g.stroke();
//            g.closePath();
//        }