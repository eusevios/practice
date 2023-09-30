package functions;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction{
    protected double[] arrayOfX;
    protected double[] arrayOfY;

    public int getCount(){
        return count;
    }

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
        double[] newArrayOfX = new double[count+1];
        double[] newArrayOfY = new double[count+1];
        newArrayOfX[0] = x;
        newArrayOfY[0] = newY;
        int index = 1;
        count++;
        while(index!=count){
            newArrayOfX[index] = arrayOfX[index-1];
            newArrayOfY[index] = arrayOfY[index-1];
            ++index;
        }
        arrayOfX = newArrayOfX;
        arrayOfY = newArrayOfY;
        return newY;
    }


    protected double extrapolateRight(double x){
        if(count == 1) return arrayOfY[0];
        double newY =  interpolationFormula(count-2, count-1,x);
        double[] newArrayOfX = new double[count+1];
        double[] newArrayOfY = new double[count+1];
        newArrayOfX[count] = x;
        newArrayOfY[count] = newY;
        int index = 0;
        while(index!=count-1){
            newArrayOfX[index] = arrayOfX[index];
            newArrayOfY[index] = arrayOfY[index];
            ++index;
        }
        arrayOfX = newArrayOfX;
        arrayOfY = newArrayOfY;
        count++;
        return newY;
    }


    protected double interpolate(double x, int floorIndex){
        if(count == 1) return arrayOfY[0];
        double newY =  interpolationFormula(floorIndex,floorIndex+1,x);
        double[] newArrayOfX = new double[count+1];
        double[] newArrayOfY = new double[count+1];
        newArrayOfX[floorIndex+1] = x;
        newArrayOfY[floorIndex+1] = newY;
        count++;
        int index = 0;
        while(index!=floorIndex+1){
            newArrayOfX[index] = arrayOfX[index];
            newArrayOfY[index] = arrayOfY[index];
            ++index;
        }
        ++index;
        while(index!=count){
            newArrayOfX[index] = arrayOfX[index-1];
            newArrayOfY[index] = arrayOfY[index-1];
            ++index;
        }
        arrayOfX = newArrayOfX;
        arrayOfY = newArrayOfY;
        return newY;
    }


    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY){
        if (leftX>rightX){
            double temp = rightX;
            rightX = leftX;
            leftX = temp;
        }
        if(count == 1) return arrayOfY[0];
        double newY =  leftY + (rightY - leftY)/
                (rightX - leftX) * (x - leftX);
        int floorIndex = floorIndexOfX(x);
        double[] newArrayOfX = new double[count+1];
        double[] newArrayOfY = new double[count+1];
        newArrayOfX[floorIndex+1] = x;
        newArrayOfY[floorIndex+1] = newY;
        int index = 0;
        while(index!=floorIndex+1){
            newArrayOfX[index] = arrayOfX[index];
            newArrayOfY[index] = arrayOfY[index];
            ++index;
        }
        ++index;
        while(index!=count+1){
            newArrayOfX[index] = arrayOfX[index-1];
            newArrayOfY[index] = arrayOfY[index-1];
            ++index;
        }
        arrayOfX = newArrayOfX;
        arrayOfY = newArrayOfY;
        count++;
        return newY;
    }


    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int countOfThisValues){
        arrayOfY = new double[countOfThisValues];
        arrayOfX = new double[countOfThisValues];
        count = countOfThisValues;
        arrayOfX[0] = xFrom; arrayOfY[0] = source.apply(xFrom);
        arrayOfX[countOfThisValues-1] = xTo; arrayOfY[countOfThisValues-1] = source.apply(xTo);
        double samplingFrequency = (xTo-xFrom)/(countOfThisValues-1);
        double currentX = xFrom+samplingFrequency;
        int current_index = 1;
        while(current_index!=countOfThisValues-1){
            arrayOfX[current_index] = currentX;
            arrayOfY[current_index] = source.apply(currentX);
            ++current_index;
            currentX+=samplingFrequency;
        }
    }


    public CompositeFunction andThen(MathFunction afterFunction){
        CompositeFunction newFunction = new CompositeFunction(afterFunction, this);
        return newFunction;
    }

}
