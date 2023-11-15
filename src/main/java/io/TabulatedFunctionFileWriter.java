package io;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileWriter {

    public static void main(String[] args) {
        try (FileWriter firstFWriter = new FileWriter("output/arrayfunction.txt");
             FileWriter secondFWriter = new FileWriter("output/linkedlistfunction.txt")) {

            BufferedWriter firstBuffWriter = new BufferedWriter(firstFWriter);
            BufferedWriter secondBuffWriter = new BufferedWriter(secondFWriter);

            double[] x = {1, 2, 3};
            double[] y = {4, 5, 6};

            ArrayTabulatedFunction arrayTabFunc = new ArrayTabulatedFunction(x, y);
            LinkedListTabulatedFunction linkedListTabFunc = new LinkedListTabulatedFunction(x, y);

            FunctionsIO.writeTabulatedFunction(firstBuffWriter, arrayTabFunc);
            FunctionsIO.writeTabulatedFunction(secondBuffWriter, linkedListTabFunc);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
