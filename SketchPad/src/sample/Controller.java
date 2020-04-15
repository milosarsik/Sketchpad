package sample;

import actions.ClearAction;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import shapes.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Controller {

    private String mode, color;

    public GraphicsContext g;
    public ColorPicker cpLine = new ColorPicker(Color.BLACK);
    public ColorPicker cpFill = new ColorPicker(Color.BLACK);

    public MyScribble myScribble;
    public MyLine myLine;
    public MyRectangle myRectangle;
    public MyEllipse myEllipse;
    public MySquare mySquare;
    public MyCircle myCircle;
    public MyOpenPolygon myOpenPolygon;
    public MyClosedPolygon myClosedPolygon;

    public MyScribble pasteScribble;
    public MyRectangle pasteRectangle;
    public MyLine pasteLine;
    public MyEllipse pasteEllipse;
    public MySquare pasteSquare;
    public MyCircle pasteCircle;
    public MyOpenPolygon pasteOpenPolygon;
    public MyClosedPolygon pasteClosedPolygon;

    Stack<MyShape> undoHistory = new Stack<>();
    Stack<MyShape> redoHistory = new Stack<>();

    Point2D deletePoint;
    Point2D copyPoint;
    Point2D pastePoint;

    MyShape copiedShape;

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
    private MenuItem gettingStartedMenuItem;

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

    /*
    * Save image
    * */
    @FXML
    void save(ActionEvent event){
        FileChooser savefile = new FileChooser();
        savefile.setTitle("Save File");

        File file = savefile.showSaveDialog(Main.stage);
        if (file != null) {
            try {
                WritableImage writableImage = new WritableImage(1000, 1000);
                drawingCanvas.snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) {
                System.out.println("Error!");
            }
        }
    }

    /*
    * Open image
    * */
    @FXML
    void open(ActionEvent event){
        FileChooser openFile = new FileChooser();
        openFile.setTitle("Open File");
        File file = openFile.showOpenDialog(Main.stage);
        if (file != null) {
            try {
                InputStream io = new FileInputStream(file);
                Image img = new Image(io);
                drawingCanvas.getGraphicsContext2D().drawImage(img, 0, 0);
            } catch (IOException ex) {
                System.out.println("Error!");
            }
        }
    }


    /*
    * Open getting started information page
    * */
    @FXML
    void openGettingStarted(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Getting Started");
        alert.setHeaderText("Welcome to Paint It!");

        alert.setContentText("This tutorial page will run you through how to use the Paint It! application. \n" +
                "\n The program contains the following modes: \n" +
                "- Scribble/Freehand Lines \n" +
                "- Straight Lines \n" +
                "- Rectangles \n" +
                "- Ellipses \n" +
                "- Squares \n" +
                "- Circles \n" +
                "- Open Polygons \n" +
                "- Closed Polygons \n" +
                "\n" +
                "Alongside offering a wide range of shapes to draw, " +
                "Paint It! gives you the option to choose a color for each " +
                "individual shape that you draw. \n" +
                "\n" +
                "The program contains the following colors: \n" +
                "- Black \n" +
                "- Blue \n" +
                "- Red \n" +
                "\n" +
                "The program contains other functions such as: \n" +
                "- Delete \n" +
                "\n" +
                "");

        // show the dialog
        alert.show();

    }

    @FXML
    void setModeCopy(ActionEvent event) {
        mode = "copy";
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
    * End of setFill
    * */

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

    @FXML
    void setModeDelete(ActionEvent event){
        mode = "delete";
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
    * Start of start draw event
    * */
    @FXML
    void startDraw(MouseEvent event) {
        g = drawingCanvas.getGraphicsContext2D();
        g.setStroke(cpLine.getValue());

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

            myCircle = new MyCircle();
            myCircle.setGraphicsContext(g);
            myCircle.setColor(cpLine);
            myCircle.setFill(cpFill);
            myCircle.setCenterPoint(event.getX(), event.getY());
        }
        else if(mode.equals("open polygon")){
            g.setStroke(cpLine.getValue());
            g.setFill(cpFill.getValue());

            myOpenPolygon = new MyOpenPolygon();

            myOpenPolygon.setGraphicsContext(g);
            myOpenPolygon.setColor(cpLine);
            myOpenPolygon.setFill(cpFill);
            myOpenPolygon.addPoint(event.getX(), event.getY());
        }
        else if(mode.equals("closed polygon")){
            g.setStroke(cpLine.getValue());
            g.setFill(cpFill.getValue());

            myClosedPolygon = new MyClosedPolygon();

            myClosedPolygon.setGraphicsContext(g);
            myClosedPolygon.setColor(cpLine);
            myClosedPolygon.setFill(cpFill);
            myClosedPolygon.addPoint(event.getX(), event.getY());
        }
        else if(mode.equals("delete")){
            deletePoint = new Point2D(event.getX(), event.getY());
        }
        else if(mode.equals("copy")){
            copyPoint = new Point2D(event.getX(), event.getY());
        }
    }
    /*
    * End of start draw event
    * */

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
        if(mode.equals("open polygon")){
            myOpenPolygon.addPoint(event.getX(), event.getY());

            try{
                Thread.sleep(250);
            }
            catch (Exception e){

            }
        }
        if(mode.equals("closed polygon")){
            myClosedPolygon.addPoint(event.getX(), event.getY());

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

    /*
    * Start of end draw event
    *
    * Bug #1:
    * */
    @FXML
    void endDraw(MouseEvent event) {

        if(mode.equals("scribble")){
            g.lineTo(event.getX(), event.getY());
            g.stroke();

            myScribble.setEndPoint(event.getX(), event.getY());

            undoHistory.push(myScribble);
        }
        else if(mode.equals("straight line")){
            myLine.setEndPoint(event.getX(), event.getY());

            myLine.draw();

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
        }
        else if(mode.equals("circle")) {
            myCircle.setEndPoint(event.getX(), event.getY());
            myCircle.setRadius();
            myCircle.check();

            myCircle.draw();

            undoHistory.push(myCircle);
        }
        else if(mode.equals("open polygon")){
            myOpenPolygon.addPoint(event.getX(), event.getY());
            myOpenPolygon.draw();

            undoHistory.push(myOpenPolygon);
        }
        else if(mode.equals("closed polygon")){
            myClosedPolygon.addPoint(event.getX(), event.getY());
            myClosedPolygon.draw();

            undoHistory.push(myClosedPolygon);
        }
        else if(mode.equals("delete")){
            delete(deletePoint);
        }
        else if(mode.equals("copy")) {
            pastePoint = new Point2D(event.getX(), event.getY());

            Stack<MyShape> tempUndo = new Stack<>();

            // Create a copy stack of undoHistory
            Iterator<MyShape> undoHistoryIterator = undoHistory.iterator();
            while (undoHistoryIterator.hasNext()) {
                tempUndo.push(undoHistoryIterator.next());
            }

            // Iterate through copy stack to find the shape, and save it
            while (!tempUndo.isEmpty()) {
                MyShape tempShape = tempUndo.pop();

                if (tempShape.containsPoint(copyPoint)) {

                    if(tempShape.getClass() == MyScribble.class){

                        System.out.println("Copying a scribble...");

                        MyScribble tempScribble = (MyScribble) tempShape;

                        g.setStroke(tempScribble.getStroke().getValue());

                        pasteScribble = new MyScribble();

                        pasteScribble.setGraphicsContext(g);
                        pasteScribble.setColor(cpLine);

                        pasteScribble.setStartPoint(pastePoint.getX(), pastePoint.getY());

                        double [] oldX = tempScribble.getAllXValues();
                        double [] oldY = tempScribble.getAllYValues();

                        double xDifference = pastePoint.getX() - tempScribble.getStartX();
                        double yDifference = pastePoint.getY() - tempScribble.getStartY();

                        for(int i = 0; i < oldX.length; i++){
                            pasteScribble.addPoint(oldX[i] + xDifference, oldY[i] + yDifference);
                        }

                        pasteScribble.setEndPoint(tempScribble.getEndX() + xDifference, tempScribble.getEndY() + yDifference);

                        pasteScribble.draw();

                        undoHistory.add(pasteScribble);
                    }
                    else if(tempShape.getClass() == MyLine.class){
                        System.out.println("Copying a line...");


                        MyLine tempLine = (MyLine) tempShape;

                        g.setStroke(tempLine.getStroke().getValue());

                        pasteLine = new MyLine();

                        pasteLine.setGraphicsContext(g);
                        pasteLine.setColor(tempLine.getStroke());

                        double xDifference = pastePoint.getX() - tempLine.getStartX();
                        double yDifference = pastePoint.getY() - tempLine.getStartY();

                        pasteLine.setStartPoint(pastePoint.getX(), pastePoint.getY());
                        pasteLine.setEndPoint(tempLine.getEndX() + xDifference, tempLine.getEndY() + yDifference);

                        pasteLine.draw();

                        undoHistory.add(pasteLine);

                    }
                    else if(tempShape.getClass() == MyRectangle.class){
                        System.out.println("Copying a rectangle...");
//COLOR WORKS HERE
                        MyRectangle tempRectangle = (MyRectangle) tempShape;

                        pasteRectangle = new MyRectangle();

                        pasteRectangle.setGraphicsContext(g);
                        pasteRectangle.setColor(tempRectangle.getStroke());
                        pasteRectangle.setFill(tempRectangle.getFill());

                        pasteRectangle.setStartPoint(pastePoint.getX(), pastePoint.getY());
                        pasteRectangle.setEndPoint(pastePoint.getX() + tempRectangle.getWidth(), pastePoint.getY() + tempRectangle.getHeight());
                        pasteRectangle.setWidth();
                        pasteRectangle.setHeight();
                        //pasteRectangle.check();
                        pasteRectangle.draw();

                        undoHistory.push(pasteRectangle);
                    }
                    else if(tempShape.getClass() == MyEllipse.class){
                        MyEllipse tempEllipse = (MyEllipse) tempShape;

                        g.setStroke(tempEllipse.getStroke().getValue());
                        g.setFill(tempEllipse.getFill().getValue());

                        pasteEllipse = new MyEllipse();

                        pasteEllipse.setGraphicsContext(g);
                        pasteEllipse.setColor(cpLine);
                        pasteEllipse.setFill(cpFill);
                        pasteEllipse.setCenterPoint(pastePoint.getX(), pastePoint.getY());
                        pasteEllipse.setEndPoint(pasteEllipse.getCenterX() + tempEllipse.getRadiusX(), pasteEllipse.getCenterY() + tempEllipse.getRadiusY());
                        pasteEllipse.setRadius();
                        //pasteEllipse.check();
                        pasteEllipse.draw();

                        undoHistory.push(pasteEllipse);
                    }
                    else if(tempShape.getClass() == MySquare.class){
                        System.out.println("Copying a square...");
//COLOR WORKS HERE
                        MySquare tempSquare = (MySquare) tempShape;

                        pasteSquare = new MySquare();

                        pasteSquare.setGraphicsContext(g);
                        pasteSquare.setColor(tempSquare.getStroke());
                        pasteSquare.setFill(tempSquare.getFill());

                        pasteSquare.setStartPoint(pastePoint.getX(), pastePoint.getY());
                        pasteSquare.setEndPoint(pastePoint.getX() + tempSquare.getWidth(), pastePoint.getY() + tempSquare.getHeight());
                        pasteSquare.setWidth();
                        pasteSquare.setHeight();
                        //pasteRectangle.check();
                        pasteSquare.draw();

                        undoHistory.push(pasteSquare);
                    }
                    else if(tempShape.getClass() == MyCircle.class){
                        System.out.println("Copying a circle...");

                        MyCircle tempCircle = (MyCircle) tempShape;

                        g.setStroke(tempCircle.getStroke().getValue());
                        g.setFill(tempCircle.getFill().getValue());

                        pasteCircle = new MyCircle();

                        pasteCircle.setGraphicsContext(g);
                        pasteCircle.setColor(tempCircle.getStroke());
                        pasteCircle.setFill(tempCircle.getFill());
                        pasteCircle.setCenterPoint(pastePoint.getX(), pastePoint.getY());
                        pasteCircle.setRadius(tempCircle.getRadius());
                        //pasteCircle.check();
                        pasteCircle.draw();

                        undoHistory.push(pasteCircle);
                    }
                    else if(tempShape.getClass() == MyOpenPolygon.class){

                        System.out.println("Copying an open polygon...");

                        MyOpenPolygon tempOpenPolygon = (MyOpenPolygon) tempShape;

                        g.setStroke(tempOpenPolygon.getStroke().getValue());

                        pasteOpenPolygon = new MyOpenPolygon();

                        pasteOpenPolygon.setGraphicsContext(g);
                        pasteOpenPolygon.setColor(tempOpenPolygon.getStroke());
                        pasteOpenPolygon.setFill(tempOpenPolygon.getFill());

                        ArrayList<Double> polygonX = tempOpenPolygon.getAllXValues();
                        ArrayList<Double> polygonY = tempOpenPolygon.getAllYValues();

                        double xDifference = pastePoint.getX() - polygonX.get(0);
                        double yDifference = pastePoint.getY() - polygonY.get(0);

                        for(int i = 0; i < polygonX.size(); i++){
                            pasteOpenPolygon.addPoint(polygonX.get(i) + xDifference, polygonY.get(i)+ yDifference);
                        }

                        pasteOpenPolygon.draw();

                        undoHistory.add(pasteOpenPolygon);
                    }
                    else if(tempShape.getClass() == MyClosedPolygon.class){

                        System.out.println("Copying a closed polygon...");

                        MyClosedPolygon tempClosedPolygon = (MyClosedPolygon) tempShape;

                        g.setStroke(tempClosedPolygon.getStroke().getValue());

                        pasteClosedPolygon = new MyClosedPolygon();

                        pasteClosedPolygon.setGraphicsContext(g);
                        pasteClosedPolygon.setColor(tempClosedPolygon.getStroke());
                        pasteClosedPolygon.setFill(tempClosedPolygon.getFill());

                        ArrayList<Double> polygonX = tempClosedPolygon.getAllXValues();
                        ArrayList<Double> polygonY = tempClosedPolygon.getAllYValues();

                        double xDifference = pastePoint.getX() - polygonX.get(0);
                        double yDifference = pastePoint.getY() - polygonY.get(0);

                        for(int i = 0; i < polygonX.size(); i++){
                            pasteClosedPolygon.addPoint(polygonX.get(i) + xDifference, polygonY.get(i)+ yDifference);
                        }

                        pasteClosedPolygon.draw();

                        undoHistory.add(pasteClosedPolygon);
                    }
                }
                else{
                    continue;
                }
            }

        }

    }
    /*
    * End of end draw event
    * */

    /*
    * Start of undo
    * */
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
                else if(tempShape.getClass() == MySquare.class)
                {
                    MySquare tempSquare = (MySquare) tempShape;
                    tempSquare.draw();
                }
                else if(tempShape.getClass() == MyCircle.class)
                {
                    MyCircle tempCircle = (MyCircle) tempShape;
                    tempCircle.draw();
                }
                else if(tempShape.getClass() == MyClosedPolygon.class)
                {
                    MyClosedPolygon tempClosePolygon = (MyClosedPolygon) tempShape;
                    tempClosePolygon.draw();
                }
                else if(tempShape.getClass() == MyOpenPolygon.class)
                {
                    MyOpenPolygon tempOpenPolygon = (MyOpenPolygon) tempShape;
                    tempOpenPolygon.draw();
                }
            }
        }
    }
    /*
    * End of undo
    * */

    /*
    * Start of redo
    * */
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
                else if(tempShape.getClass() == MySquare.class)
                {
                    MySquare tempSquare = (MySquare) tempShape;
                    tempSquare.draw();
                }
                else if(tempShape.getClass() == MyCircle.class)
                {
                    MyCircle tempCircle = (MyCircle) tempShape;
                    tempCircle.draw();
                }
                else if(tempShape.getClass() == MyClosedPolygon.class)
                {
                    MyClosedPolygon tempClosePolygon = (MyClosedPolygon) tempShape;
                    tempClosePolygon.draw();
                }
                else if(tempShape.getClass() == MyOpenPolygon.class)
                {
                    MyOpenPolygon tempOpenPolygon = (MyOpenPolygon) tempShape;
                    tempOpenPolygon.draw();
                }
            }
        }
    }
    /*
    * End of redo
    * */

    /*
     * Start of delete
     *
     * Bug #1: Undo and redo do not work with delete, you cannot undo or redo a delete action for now
     * Bug #2: Redraw after object delete may mess up the order in which they were drawn
     * */
    public void delete(Point2D point){
        Stack<MyShape> tempUndo = new Stack<>();

        // Create a copy stack of undoHistory
        Iterator<MyShape> undoHistoryIterator = undoHistory.iterator();
        while(undoHistoryIterator.hasNext()){
            tempUndo.push(undoHistoryIterator.next());
        }

        // Iterate through copy stack to find the shape, and push it on redo stack to save
        while(!tempUndo.isEmpty()){
            MyShape tempShape = tempUndo.pop();

            if(tempShape.containsPoint(point)) {
                redoHistory.push(tempShape);

                // Iterate through undoHistory stack and remove the deleted shape
                Iterator<MyShape> removalIterator = undoHistory.iterator();
                while(removalIterator.hasNext()){
                    if(removalIterator.next() == redoHistory.peek()){
                        removalIterator.remove();
                        break;
                    }
                    else{
                        continue;
                    }
                }

                // Redraw the new canvas without the shape we just deleted
                g.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());

                Stack<MyShape> drawStack = new Stack<>();

                Iterator iterator = undoHistory.iterator();
                while(iterator.hasNext()){
                    drawStack.push((MyShape) iterator.next());
                }

                while(!drawStack.isEmpty()){
                    tempShape = drawStack.pop();

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
                    else if(tempShape.getClass() == MySquare.class)
                    {
                        MySquare tempSquare = (MySquare) tempShape;
                        tempSquare.draw();
                    }
                    else if(tempShape.getClass() == MyCircle.class)
                    {
                        MyCircle tempCircle = (MyCircle) tempShape;
                        tempCircle.draw();
                    }
                    else if(tempShape.getClass() == MyClosedPolygon.class)
                    {
                        MyClosedPolygon tempClosePolygon = (MyClosedPolygon) tempShape;
                        tempClosePolygon.draw();
                    }
                    else if(tempShape.getClass() == MyOpenPolygon.class)
                    {
                        MyOpenPolygon tempOpenPolygon = (MyOpenPolygon) tempShape;
                        tempOpenPolygon.draw();
                    }
                }

                redoHistory.pop();

                break;
            }
            else{
                continue;
            }
        }
    }
    /*
     * End of delete
     * */
}
