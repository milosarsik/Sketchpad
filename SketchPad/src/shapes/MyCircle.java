package shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

public class MyCircle extends MyShape {


    Circle circle = new Circle();

    private double centerX, centerY;
    private double endX, endY;

    private GraphicsContext graphicsContext;
    ColorPicker cpLine;
    ColorPicker cpFill;

    public boolean containsPoint(Point2D point){
        return circle.contains(point);
    }

    public void setCenterPoint(double centerX, double centerY){
        this.centerX = centerX;
        this.centerY = centerY;

        circle.setCenterX(centerX);
        circle.setCenterY(centerY);
    }
    public double getCenterX(){
        return circle.getCenterX();
    }

    public double getCenterY(){
        return circle.getCenterY();
    }

    public void setEndPoint(double endX, double endY){
        this.endX = endX;
        this.endY = endY;
    }

    public void setRadius(){
        circle.setRadius((Math.abs(endX - centerX) + Math.abs(endY - centerY)) / 2);
    }

    public double getRadius(){
        return circle.getRadius();
    }

    public void check(){
        if(centerX > endX) {
            circle.setCenterX(endX);
        }
        if(centerY > endY) {
            circle.setCenterY(endY);
        }
    }

    public void setColor(ColorPicker colorPicker){
        cpLine = colorPicker;
    }

    public void setFill(ColorPicker colorPicker){
        cpFill = colorPicker;
    }


    public void setGraphicsContext(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;
    }

    public void draw(){
        graphicsContext.setStroke(cpLine.getValue());
        graphicsContext.setFill(cpFill.getValue());

        graphicsContext.fillOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius());
        graphicsContext.strokeOval(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), circle.getRadius());

    }

}
