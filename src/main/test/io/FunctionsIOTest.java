package io;

import functions.ArrayTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class FunctionsIOTest {

    double[] arrX = {2.5,4.1,10.91};
    double[] arrY = {4,-5,0};
    ArrayTabulatedFunction tabFunc = new ArrayTabulatedFunction(arrX, arrY);

    @Test
    void writeTabulatedFunction() {
        try(FileWriter fileWriter = new FileWriter("temp/out.txt")){
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            FunctionsIO.writeTabulatedFunction(bufferedWriter, tabFunc);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    void readTabulatedFunction() {
        try(FileWriter fileWriter = new FileWriter("temp/func.txt"); FileReader fileReader = new FileReader("temp/func.txt")){

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            FunctionsIO.writeTabulatedFunction(bufferedWriter, tabFunc);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ArrayTabulatedFunctionFactory fact = new ArrayTabulatedFunctionFactory();
            TabulatedFunction func = FunctionsIO.readTabulatedFunction(bufferedReader, fact);
            for(int i = 0; i< func.getCount(); i++){
                assertEquals(arrX[i], func.getX(i));
                assertEquals(arrY[i], func.getY(i));
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    void serialize() {
        try(FileOutputStream fileOutStream = new FileOutputStream("temp/serializedarrayfunction.bin")) {

            BufferedOutputStream buffOut = new BufferedOutputStream(fileOutStream);

            ArrayTabulatedFunction arrTabFunc = new ArrayTabulatedFunction(arrX, arrY);

            FunctionsIO.serialize(buffOut, arrTabFunc);


        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    @Test
    void testWriteTabulatedFunction() {
    }

    @Test
    void testReadTabulatedFunction() {
    }

    @Test
    void deserialize() {
        try(FileOutputStream fileOutStream = new FileOutputStream( "temp/serializedarrayfunction.bin");
        FileInputStream fileInStream = new FileInputStream("temp/serializedarrayfunction.bin")) {

            BufferedOutputStream buffOut = new BufferedOutputStream(fileOutStream);

            ArrayTabulatedFunction arrTabFunc = new ArrayTabulatedFunction(arrX, arrY);

            FunctionsIO.serialize(buffOut, arrTabFunc);

            BufferedInputStream buffIn = new BufferedInputStream(fileInStream);
            try {
                TabulatedFunction func = FunctionsIO.deserialize(buffIn);
                for(int i = 0; i< func.getCount(); i++){
                    assertEquals(arrX[i], func.getX(i));
                    assertEquals(arrY[i], func.getY(i));
                }
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    void serializeXml() {
    }

    @Test
    void deserializeXml() {
    }

    @AfterAll
    public static void clearTemp(){
        for(File file : new File("temp").listFiles()){
            file.delete();
        }
    }

}