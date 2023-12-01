package ui;

public class TablePoint {

    Double x;

    Double y;

    public TablePoint() {
        this.x = null;
        this.y = null;
    }

    public TablePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

}