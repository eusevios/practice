package ui;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TableRow;

public class TablePoint{

    Double x;

    Double y;

    public TablePoint() {

        this.x = 0.0;
        this.y = 0.0;


    }

    public double getX() {
        return x;
    }

    public  double getY(){
        return y;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

}