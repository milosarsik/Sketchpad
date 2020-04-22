package shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

import java.util.ArrayList;

public class MyOpenPolygon extends MyShape{
    private GraphicsContext graphicsContext;
    private ColorPicker cpLine;
    private ColorPicker cpFill;

    private ArrayList<Double> polygonX = new ArrayList<Double>();
    private ArrayList<Double> polygonY = new ArrayList<Double>();

    public MyOpenPolygon(){}

    public void setGraphicsContext(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;
    }

    public void setColor(ColorPicker colorPicker){
        cpLine = colorPicker;
    }

    public void setFill(ColorPicker colorPicker){
        cpFill = colorPicker;
    }

    public void addPoint(double x, double y){
        polygonX.add(x);
        polygonY.add(y);
    }

    // This wont really work for a polygon unless you click the edges
    public boolean containsPoint(Point2D point){
        for(int i = 0; i < polygonX.size(); i++){
            if(polygonX.get(i) == point.getX() && polygonY.get(i) == point.getY()){
                return true;
            }
        }

        return false;
    }

    public ArrayList<Double> getAllXValues(){
        return polygonX;
    }

    public ArrayList<Double> getAllYValues(){
        return polygonY;
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

        double[] polyX = new double[polygonX.size()];
        double[] polyY = new double[polygonY.size()];

        for(int i = 0; i < polygonX.size(); i++){
            polyX[i] = polygonX.get(i);
            polyY[i] = polygonY.get(i);
        }

        graphicsContext.strokePolyline(polyX, polyY, polyX.length);
    }
}
