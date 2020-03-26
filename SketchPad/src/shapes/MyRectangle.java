package shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Rectangle;

public class MyRectangle extends MyShape{
    public double startX, startY;
    public double endX, endY;

    Rectangle rectangle = new Rectangle();

    private GraphicsContext graphicsContext;
    private ColorPicker cpLine;
    private ColorPicker cpFill;

    public void MyRectangle(){
    }

    public void setStartPoint(double startX, double startY){
        this.startX = startX;
        this.startY = startY;

        rectangle.setX(startX);
        rectangle.setY(startY);
    }

    public void setEndPoint(double endX, double endY){
        this.endX = endX;
        this.endY = endY;
    }

    public double getX(){
        return rectangle.getX();
    }

    public double getY(){
        return rectangle.getY();
    }

    public void setWidth(){
        rectangle.setWidth(Math.abs((endX - startX)));
    }

    public void setHeight(){
        rectangle.setHeight(Math.abs((endY - startY)));
    }

    public double getWidth(){
        return rectangle.getWidth();
    }

    public double getHeight() {
        return rectangle.getHeight();
    }

    public void check(){
        if(getX() > endX) {
            rectangle.setX(endX);
        }
        if(getY() > endY) {
            rectangle.setY(endY);
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

        graphicsContext.fillRect(getX(), getY(), getWidth(), getHeight());
        graphicsContext.strokeRect(getX(), getY(), getWidth(), getHeight());

    }
}
