package io;

import functions.AbstractTabulatedFunction;
import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import operations.TabulatedDifferentialOperator;

import java.io.*;

public class TabulatedFunctionFileInputStream {

    public static void main(String[] args) {


        try(FileInputStream in1 = new FileInputStream("input/binaryfunction.bin");){

            BufferedInputStream buf_in1 = new BufferedInputStream(in1);
            ArrayTabulatedFunctionFactory arrFact = new ArrayTabulatedFunctionFactory();
            TabulatedFunction func1 = FunctionsIO.readTabulatedFunction(buf_in1, arrFact);

            System.out.println(func1.toString());
        }
        catch(IOException e){
            e.printStackTrace();
        }

        try{
            InputStreamReader inRead = new InputStreamReader(System.in);
            BufferedReader bufRead = new BufferedReader(inRead);

            System.out.println("Введите размер и значения функции\n");
            LinkedListTabulatedFunctionFactory listFact = new LinkedListTabulatedFunctionFactory();
            TabulatedFunction func2 = FunctionsIO.readTabulatedFunction(bufRead, listFact);

            LinkedListTabulatedFunctionFactory funcFact = new LinkedListTabulatedFunctionFactory();
            TabulatedDifferentialOperator difOper = new TabulatedDifferentialOperator();
            System.out.println(difOper.derive(func2).toString());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
