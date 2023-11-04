package io;

import functions.ArrayTabulatedFunction;
import functions.TabulatedFunction;

import java.io.*;

public class ArrayTabulatedFunctionJsonSerialization {

    public static void main(String[] args) {

        try(FileWriter writer = new FileWriter("output/serializedArrayFunctionsJson.json")) {

            BufferedWriter bufWriter = new BufferedWriter(writer);

            double[] arrX = {1.3, 3.5, 10};
            double[] arrY = {5.13, -7.32, 1.73};
            ArrayTabulatedFunction func = new ArrayTabulatedFunction(arrX, arrY);

            FunctionsIO.serializeJson(bufWriter, func);

            FileReader reader = new FileReader("output/serializedArrayFunctionsJson.json");
            BufferedReader bufReader = new BufferedReader(reader);
            TabulatedFunction tabFunc = FunctionsIO.deserializeXml(bufReader);
            System.out.println(tabFunc.toString());
            reader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
