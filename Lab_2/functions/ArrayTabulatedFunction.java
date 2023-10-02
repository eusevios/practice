package functions;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable {

    protected int count;
    protected double[] arrayOfX;
    protected double[] arrayOfY;


    public int getCount(){ return count; }


    public double getX(int index){
        return arrayOfX[index];
    }


    public double getY(int index){
        return arrayOfY[index];
    }


    public void setY(int index, double value){
        arrayOfY[index] = value;
    }


    public int indexOfX(double x){

        int index = 0;

        while(index!=count){
            if(arrayOfX[index] == x) return index;
            index++;
        }

        return -1;

    }


    public int indexOfY(double y){

        int index = 0;

        while(index!=count){
            if(arrayOfY[index] == y) return index;
            index++;
        }

        return -1;

    }

    public double leftBound(){
        return arrayOfX[0];
    }


    public double rightBound() {
        return arrayOfX[count - 1];
    }


    public ArrayTabulatedFunction(double[] xValues, double[] yValues){
        count = xValues.length;

        arrayOfX = Arrays.copyOf(xValues, count);
        arrayOfY = Arrays.copyOf(yValues, count);

    }

    protected int floorIndexOfX(double x)
    {
        if(x>arrayOfX[count-1]) return count;

        if(x<arrayOfX[0]) return 0;

        int index = 1;
        while(arrayOfX[index] < x && index !=count) index++;
        return index-1;

    }


    private double interpolationFormula(int leftIndex, int rightIndex, double x){

        return arrayOfY[leftIndex]+((arrayOfY[rightIndex]-arrayOfY[leftIndex])/
                (arrayOfX[rightIndex]-arrayOfX[leftIndex])) * (x-arrayOfX[leftIndex]);

    }


    protected double extrapolateLeft(double x){

        if(count == 1) return arrayOfY[0];

        double newY = interpolationFormula(0,1, x);

        return newY;

    }


    protected double extrapolateRight(double x){

        if(count == 1) return arrayOfY[0];

        double newY =  interpolationFormula(count-2, count-1,x);

        return newY;

    }


    protected double interpolate(double x, int floorIndex){

        if(count == 1) return arrayOfY[0];

        double newY =  interpolationFormula(floorIndex,floorIndex+1,x);

        return newY;

    }


    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int countOfThisValues){

        arrayOfY = new double[countOfThisValues];
        arrayOfX = new double[countOfThisValues];
        count = countOfThisValues;

        arrayOfX[0] = xFrom; arrayOfY[0] = source.apply(xFrom);

        arrayOfX[countOfThisValues-1] = xTo;
        arrayOfY[countOfThisValues-1] = source.apply(xTo);

        double samplingFrequency = (xTo-xFrom)/
                               (countOfThisValues-1);
        double currentX = xFrom+samplingFrequency;

        int current_index = 1;
        while(current_index!=countOfThisValues-1){
            arrayOfX[current_index] = currentX;
            arrayOfY[current_index] = source.apply(currentX);
            ++current_index;
            currentX+=samplingFrequency;
        }

    }


    public double apply(double x){

        double result;

        if(x<getX(0)) result = this.extrapolateLeft(x);

        else if(x>getX(count-1))result = this.extrapolateRight(x);

        else {

            int indexOfX = indexOfX(x);

            if(indexOfX>0) result = this.getY(indexOfX);

            else result = this.interpolate(x, floorIndexOfX(x));

        }

        return result;

    }

    @Override
    public void insert(double x, double y) {


    }

    @Override
    public void remove(int index) {


    }
}
