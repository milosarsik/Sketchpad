package shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Line;

public class MyLine extends MyShape {

    private Line line = new Line();

    private GraphicsContext graphicsContext;
    private ColorPicker cpLine;

    public void myLine(){}

    public void myLine(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;
    }

    public void setStartPoint(double x, double y){
        line.setStartX(x);
        line.setStartY(y);
    }

    public void setEndPoint(double x, double y){
        line.setEndX(x);
        line.setEndY(y);
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

    public void setGraphicsContext(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;
    }

    public void setColor(ColorPicker colorPicker){
        cpLine = colorPicker;
    }

    public void draw() {
        graphicsContext.setStroke(cpLine.getValue());
        graphicsContext.strokeLine(getStartX(), getStartY(), getEndX(), getEndY());
    }
}
