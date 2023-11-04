package io;

import functions.ArrayTabulatedFunction;
import functions.TabulatedFunction;
import operations.TabulatedDifferentialOperator;

import java.io.*;

public class ArrayTabulatedFunctionXMLSerialization {public static void main(String[] args){

    try(FileWriter fWrit = new FileWriter("output/serializedarrayfunctionsXML.xml", true)) {

        BufferedWriter buffOut = new BufferedWriter(fWrit);

        double[] arrX = {12, 22, 32};
        double[] arrY = {3.52, 4.22, -5.52};
        ArrayTabulatedFunction arrTabFunc = new ArrayTabulatedFunction(arrX, arrY);

        FunctionsIO.serializeXml(buffOut, arrTabFunc);

        FileReader fOut = new FileReader("output/serializedarrayfunctionsXML.xml");
        BufferedReader secondBuffOut = new BufferedReader(fOut);
        TabulatedFunction function1 = FunctionsIO.deserializeXml(secondBuffOut);
        System.out.println(function1.toString());
        fOut.close();
    }
    catch (IOException e){
        e.printStackTrace();
    }
}
}