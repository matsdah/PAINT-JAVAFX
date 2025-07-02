package src.backend.model;

import javafx.scene.shape.StrokeLineCap;
import java.awt.*;

public class Border{
    private StrokeLineCap borderType;
    private Color fillBorderColor;
    private double lineWidth;

    Border(StrokeLineCap borderType, Color fillBorderColor, double lineWidth){
        this.borderType = borderType;
        this.fillBorderColor = fillBorderColor;
        this.lineWidth = lineWidth;
    }

    StrokeLineCap getBorderType(){
        return borderType;
    }

    Color getBorderColor(){
        return fillBorderColor;
    }

    double getLineWidth(){
        return lineWidth;
    }
}
