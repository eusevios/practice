package functions;

public abstract class AbstractTabulatedFunction {
    private int count;
    public double[] arrayOfX;
    public double[] arrayOfY;

    int getCount(){
        return count;
    }

    int indexOfX(double x){
        int index = 0;
        while(index!=count) if(arrayOfX[index] == x) return index;
        return -1;
    }
    public int floorIndexOfX(double x){
        int index = 0;
        while(arrayOfX[index] > x && index !=count) index++;
        return index;
    }

    public double extrapolateLeft(double x){
        double newY = arrayOfY[0] + (arrayOfY[1] - arrayOfY[0])/
                (arrayOfX[1] - arrayOfX[0]) * (x - arrayOfX[0]);
        double[] newArrayOfX = new double[count+1];
        double[] newArrayOfY = new double[count+1];
        newArrayOfX[0] = x;
        newArrayOfY[0] = newY;
        int index = 1;
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

    public double extrapolateRight(double x){
        double newY =  arrayOfY[count-1] + (arrayOfY[count] - arrayOfY[count-1])/
                (arrayOfX[count] - arrayOfX[count-1]) * (x - arrayOfX[count-1]);
        double[] newArrayOfX = new double[count+1];
        double[] newArrayOfY = new double[count+1];
        newArrayOfX[count+1] = x;
        newArrayOfY[count+1] = newY;
        int index = 0;
        while(index!=count){
            newArrayOfX[index] = arrayOfX[index];
            newArrayOfY[index] = arrayOfY[index];
            ++index;
        }
        arrayOfX = newArrayOfX;
        arrayOfY = newArrayOfY;
        count++;
        return newY;
    }

    public double interpolate(double x, int floorIndex){
        double newY =  arrayOfY[floorIndex-1] + (arrayOfY[floorIndex] - arrayOfY[floorIndex-1])/
                (arrayOfX[floorIndex] - arrayOfX[floorIndex-1]) * (x - arrayOfX[floorIndex-1]);
        double[] newArrayOfX = new double[count+1];
        double[] newArrayOfY = new double[count+1];
        newArrayOfX[floorIndex] = x;
        newArrayOfY[floorIndex] = newY;
        int index = 0;
        while(index!=floorIndex){
            newArrayOfX[index] = arrayOfX[index];
            newArrayOfY[index] = arrayOfY[index];
            ++index;
        }
        ++index;
        while(index!=count+1){
            newArrayOfX[index] = arrayOfX[index];
            newArrayOfY[index] = arrayOfY[index];
            ++index;
        }
        arrayOfX = newArrayOfX;
        arrayOfY = newArrayOfY;
        count++;
        return newY;
    }
    double interpolate(double x, double leftX, double rightX, double leftY, double rightY){
        double newY =  leftY + (rightY - leftY)/
                (rightX - leftX) * (x - leftX);
        int floorIndex = floorIndexOfX(x);
        double[] newArrayOfX = new double[count+1];
        double[] newArrayOfY = new double[count+1];
        newArrayOfX[floorIndex] = x;
        newArrayOfY[floorIndex] = newY;
        int index = 0;
        while(index!=floorIndex){
            newArrayOfX[index] = arrayOfX[index];
            newArrayOfY[index] = arrayOfY[index];
            ++index;
        }
        ++index;
        while(index!=count+1){
            newArrayOfX[index] = arrayOfX[index];
            newArrayOfY[index] = arrayOfY[index];
            ++index;
        }
        arrayOfX = newArrayOfX;
        arrayOfY = newArrayOfY;
        count++;
        return newY;
    }
    double apply(double x){
        double result;
        if(x<arrayOfX[0]) result = this.extrapolateLeft(x);
        else if(x>arrayOfX[count])result = this.extrapolateRight(x);
        else{
            int indexOfX = indexOfX(x);
            if(x>0) result = this.arrayOfY[indexOfX];
            else result = this.interpolate(x, floorIndexOfX(x));
        }
        return result;
    }
}
