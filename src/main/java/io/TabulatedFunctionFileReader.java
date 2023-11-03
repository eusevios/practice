package io;

import functions.*;
import functions.LinkedListTabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

import java.io.*;

public class TabulatedFunctionFileReader {
    public static void main(String[] args){
        try(FileReader firstFReader = new FileReader("C:/Users/Иван/IdeaProjects/practice/input/function.txt");
            FileReader secondFReader = new FileReader("C:/Users/Иван/IdeaProjects/practice/input/function.txt")){
            BufferedReader firstBuffWriter = new BufferedReader(firstFReader);
            BufferedReader secondBuffWriter = new BufferedReader(secondFReader);
            ArrayTabulatedFunctionFactory arrFact = new ArrayTabulatedFunctionFactory();
            LinkedListTabulatedFunctionFactory llFact = new LinkedListTabulatedFunctionFactory();
            TabulatedFunction readedArrFunc;
            TabulatedFunction readedLLFunc;
            try {

                readedArrFunc = FunctionsIO.readTabulatedFunction(firstBuffWriter, arrFact);
                readedLLFunc = FunctionsIO.readTabulatedFunction(secondBuffWriter, llFact);

                System.out.println(readedArrFunc.toString());
                System.out.println(readedLLFunc.toString());

            }
            catch (IOException e){
                e.printStackTrace();
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
