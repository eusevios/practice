package io;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TabulatedFunctionFileOutputStream {

    public static void main(String[] args) {


        try (FileOutputStream out1 = new FileOutputStream("output/binaryfunction.bin");
             FileOutputStream out2 = new FileOutputStream("output/linkedlistfunction.bin");) {

            BufferedOutputStream buf_out1 = new BufferedOutputStream(out1);
            BufferedOutputStream buf_out2 = new BufferedOutputStream(out2);

            double[] arrX = {3, 4, 6};
            double[] arrY = {5, 2, -2};
            ArrayTabulatedFunction arrFunc = new ArrayTabulatedFunction(arrX, arrY);
            LinkedListTabulatedFunction listFunc = new LinkedListTabulatedFunction(arrX, arrY);

            FunctionsIO.writeTabulatedFunction(buf_out1, arrFunc);
            FunctionsIO.writeTabulatedFunction(buf_out2, listFunc);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
