package shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Rectangle;

public class MySquare extends MyShape {
    private GraphicsContext graphicsContext;
    private ColorPicker cpLine;
    private ColorPicker cpFill;

    private double startX, startY, endX, endY;

    private Rectangle square = new Rectangle();

    public MySquare(){ }

    public void setGraphicsContext(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;
    }

    public void setColor(ColorPicker colorPicker){
        cpLine = colorPicker;
    }

    public void setFill(ColorPicker colorPicker){
        cpFill = colorPicker;
    }

    public void setStartPoint(double startX, double startY){
        this.startX = startX;
        this.startY = startY;

        square.setX(startX);
        square.setY(startY);
    }

    public void setEndPoint(double endX, double endY){
        this.endX = endX;
        this.endY = endY;
    }

    public void setWidth(){
        square.setWidth(Math.abs((endX - startX)));
    }

    public void setHeight(){
        square.setHeight(Math.abs((endY - startY)));
    }

    public boolean containsPoint(Point2D point){
        return square.contains(point);
    }

    public void check() {
        if (getX() > endX) {
            square.setX(endX);
        }
        if (getY() > endY) {
            square.setY(endY);
        }
    }

    public double getX(){
        return square.getX();
    }

    public double getY(){
        return square.getY();
    }

    public double getWidth(){
        return square.getWidth();
    }

    public double getHeight() {
        return square.getHeight();
    }

    public ColorPicker getColor(){
        return cpLine;
    }

    public ColorPicker getFill(){
        return cpFill;
    }

    public void draw(){
        graphicsContext.setStroke(cpLine.getValue());
        graphicsContext.setFill(cpFill.getValue());

        graphicsContext.fillRect(getX(), getY(), getWidth(), getWidth());
        graphicsContext.strokeRect(getX(), getY(), getWidth(), getWidth());
    }
}
