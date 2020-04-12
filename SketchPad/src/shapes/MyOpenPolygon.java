package shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

import java.util.ArrayList;

public class MyOpenPolygon extends MyShape{

    ArrayList<Double> polygonX = new ArrayList<Double>();
    ArrayList<Double> polygonY = new ArrayList<Double>();

    private GraphicsContext graphicsContext;
    ColorPicker cpLine;
    ColorPicker cpFill;

    public void addPoint(double x, double y){
        polygonX.add(x);
        polygonY.add(y);
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

        double[] polyX = new double[polygonX.size()];
        double[] polyY = new double[polygonY.size()];

        for(int i = 0; i < polygonX.size(); i++){
            polyX[i] = polygonX.get(i);
            polyY[i] = polygonY.get(i);
        }

        graphicsContext.strokePolyline(polyX, polyY, polyX.length);
    }
}
