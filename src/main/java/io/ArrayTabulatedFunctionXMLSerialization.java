package io;

import functions.ArrayTabulatedFunction;
import functions.TabulatedFunction;

import java.io.*;

public class ArrayTabulatedFunctionXMLSerialization {
    public static void main(String[] args) {

        try (FileWriter fWrit = new FileWriter("output/serializedarrayfunctionsXML.xml")) {

            BufferedWriter buffRead = new BufferedWriter(fWrit);

            double[] arrX = {12, 22, 32};
            double[] arrY = {3.52, 4.22, -5.52};
            ArrayTabulatedFunction arrTabFunc = new ArrayTabulatedFunction(arrX, arrY);

            FunctionsIO.serializeXml(buffRead, arrTabFunc);

            FileReader fOut = new FileReader("output/serializedarrayfunctionsXML.xml");
            BufferedReader BuffRead = new BufferedReader(fOut);
            TabulatedFunction function1 = FunctionsIO.deserializeXml(BuffRead);
            System.out.println(function1.toString());
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}