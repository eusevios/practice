package io;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;

import java.io.*;

public class TabulatedFunctionFileWriter {

    public static void main(String args[]){
        try(FileWriter firstFWriter = new FileWriter("C:/Users/Иван/IdeaProjects/practice/output/arrayfunction.txt");
        FileWriter secondFWriter = new FileWriter("C:/Users/Иван/IdeaProjects/practice/output/linkedlistfunction.txt")){

            BufferedWriter firstBuffWriter = new BufferedWriter(firstFWriter);
            BufferedWriter secondBuffWriter = new BufferedWriter(secondFWriter);

            double[] x = {};
            double[] y = {};

            ArrayTabulatedFunction arrayTabFunc = new ArrayTabulatedFunction(x,y);
            LinkedListTabulatedFunction linedListTabFunc = new LinkedListTabulatedFunction(x,y);

            FunctionsIO.writeTabulatedFunction(firstBuffWriter, arrayTabFunc);
            FunctionsIO.writeTabulatedFunction(secondBuffWriter, linedListTabFunc);

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
