package io;

import functions.*;
import functions.TabulatedFunction;
import operations.TabulatedDifferentialOperator;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args){
        try(FileOutputStream fileOutStream = new FileOutputStream("output/serializedarrayfunctions.bin")){

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


            try(FileInputStream fOut = new FileInputStream("output/serializedarrayfunctions.bin");) {

                BufferedInputStream secondBuffOut = new BufferedInputStream(fOut);

                System.out.println(FunctionsIO.deserialize(secondBuffOut).toString());

                System.out.println(FunctionsIO.deserialize(secondBuffOut).toString());

                System.out.println(FunctionsIO.deserialize(secondBuffOut).toString());


            }

            catch (ClassNotFoundException e){
                e.printStackTrace();
            }

        }

        catch (IOException e){

            e.printStackTrace();

        }
    }
}
