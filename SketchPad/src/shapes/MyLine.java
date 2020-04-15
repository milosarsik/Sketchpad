package shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Line;

public class MyLine extends MyShape {
    private GraphicsContext graphicsContext;
    private ColorPicker cpLine;

    private Line line = new Line();

    public MyLine(){}

    public void setGraphicsContext(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;
    }

    public void setColor(ColorPicker colorPicker){
        cpLine = colorPicker;
    }

    public void setStartPoint(double x, double y){
        line.setStartX(x);
        line.setStartY(y);
    }

    public void setEndPoint(double x, double y){
        line.setEndX(x);
        line.setEndY(y);
    }

    public boolean containsPoint(Point2D point){
        return line.contains(point);
    }

    public double getStartX(){
        return line.getStartX();
    }

    public double getStartY(){
        return line.getStartY();
    }

    public double getEndX(){
        return line.getEndX();
    }

    public double getEndY(){
        return line.getEndY();
    }

    public ColorPicker getColor(){
        return cpLine;
    }

    public void draw() {
        graphicsContext.setStroke(getColor().getValue());
        graphicsContext.strokeLine(getStartX(), getStartY(), getEndX(), getEndY());
    }
}
