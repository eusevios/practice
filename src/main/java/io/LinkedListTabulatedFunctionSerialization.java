package io;

import functions.LinkedListTabulatedFunction;
import operations.TabulatedDifferentialOperator;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import java.io.IOException;

public class LinkedListTabulatedFunctionSerialization {

    public static void main(String[] args) {

        try(FileOutputStream out = new FileOutputStream("output/serializedlinkedlistfunctions.bin");){

            BufferedOutputStream bufOut = new BufferedOutputStream(out);

            double[] arrX = {3, 4, 6};
            double[] arrY = {5, 2, -2};
            LinkedListTabulatedFunction func = new LinkedListTabulatedFunction(arrX,arrY);

            TabulatedDifferentialOperator difOper = new TabulatedDifferentialOperator();
            FunctionsIO.serialize(bufOut, func);
            FunctionsIO.serialize(bufOut, difOper.derive(func));
            FunctionsIO.serialize(bufOut, difOper.derive(difOper.derive(func)));
        }
        catch(IOException e){
            e.printStackTrace();
        }

        try(FileInputStream in = new FileInputStream("input/serializedlinkedlistfunctions.bin");){

            BufferedInputStream bufIn = new BufferedInputStream(in);

            System.out.println(FunctionsIO.deserialize(bufIn).toString());
        }
        catch(IOException e1){
            e1.printStackTrace();
        }
        catch(ClassNotFoundException e2){
            e2.printStackTrace();
        }
    }
}
