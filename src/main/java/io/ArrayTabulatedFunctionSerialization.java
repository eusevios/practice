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

            FileInputStream fOut = new FileInputStream("C:/Users/Иван/IdeaProjects/practice/output/serializedarrayfunctions.bin");
            BufferedInputStream secondBuffOut = new BufferedInputStream(fOut);

            try {

                TabulatedFunction function1 = FunctionsIO.deserialize(secondBuffOut);
                System.out.println(function1.toString());

                TabulatedFunction function2 = FunctionsIO.deserialize(secondBuffOut);
                System.out.println(function2.toString());

                TabulatedFunction function3 = FunctionsIO.deserialize(secondBuffOut);
                System.out.println(function3.toString());


            }

            catch (ClassNotFoundException e){
                e.printStackTrace();
            }

            finally {
                fOut.close();
            }
        }

        catch (IOException e){

            e.printStackTrace();

        }
    }
}
