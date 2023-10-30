package io;

import functions.*;
import functions.TabulatedFunction;
import operations.TabulatedDifferentialOperator;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args){
        try(FileOutputStream fileOutStream = new FileOutputStream("C:/Users/Иван/IdeaProjects/practice/output/serializedarrayfunctions.bin")){
            BufferedOutputStream buffOut = new BufferedOutputStream(fileOutStream);
            double[] arrX = {1,2,3};
            double[] arrY = {3.5, 4.2, -5.5};
            ArrayTabulatedFunction arrTabFunc = new ArrayTabulatedFunction(arrX, arrY);
            TabulatedDifferentialOperator oper = new TabulatedDifferentialOperator();
            TabulatedFunction firstDiff =  oper.derive(arrTabFunc);
            TabulatedFunction secondDiff = oper.derive(firstDiff);
            try{
                FunctionsIO.serialize(buffOut, arrTabFunc);
                FunctionsIO.serialize(buffOut, firstDiff);
                FunctionsIO.serialize(buffOut, secondDiff);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            buffOut.close();
            FileOutputStream fOut = new FileOutputStream("C:/Users/Иван/IdeaProjects/practice/output/serializedarrayfunctions.bin");
            BufferedOutputStream secondBuffOut = new BufferedOutputStream(fOut);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
