package io;
import functions.*;
import java.io.*;
import java.nio.Buffer;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Locale;

import functions.factory.*;

import javax.swing.text.NumberFormatter;

final public class FunctionsIO {

    FunctionsIO(){
        throw new UnsupportedOperationException();
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function){
        PrintWriter prWriter = new PrintWriter(writer);
        prWriter.println(function.getCount());
        for (Point point: function){
            prWriter.printf("%f %f\n", point.x, point.y );
        }
        prWriter.flush();

    }
    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException{

        int count = 0;
            count = Integer.parseInt(reader.readLine());
            double[] xValues = new double[count];
            double[] yValues = new double[count];
            NumberFormat numbForm = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
            for(int i = 0; i<count; i++){
                String str = reader.readLine();
                String[] splitStr = str.split(" ");
                try {
                    xValues[i] = numbForm.parse(splitStr[0]).doubleValue();
                    yValues[i] = numbForm.parse(splitStr[1]).doubleValue();
                }
                catch (ParseException e){

                    throw new IOException(e);

                }

            }

        return factory.create(xValues,yValues);

    }

    public static void serialize(BufferedOutputStream stream, TabulatedFunction function) throws IOException{

        ObjectOutputStream outStream;

        outStream = new ObjectOutputStream(stream);
        outStream.writeObject(function);
        stream.flush();

    }

    public static void writeTabulatedFunction(BufferedOutputStream outputStream,
                                              TabulatedFunction function) throws IOException{
        DataOutputStream out = new DataOutputStream(outputStream);
        out.writeInt(function.getCount());
        for(Point point : function){
            out.writeDouble(point.x);
            out.writeDouble(point.y);
        }
        out.flush();
    }

    public static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream,
                                                          TabulatedFunctionFactory factory) throws IOException{
        DataInputStream in = new DataInputStream(inputStream);
        int size = in.readInt();
        double[] arrayOfX = new double[size];
        double[] arrayOfY = new double[size];

        for(int i = 0; i < size; i++){
            arrayOfX[i] = in.readDouble();
            arrayOfY[i] = in.readDouble();
        }
        return factory.create(arrayOfX,arrayOfY);
    }

    public static TabulatedFunction deserialize(BufferedInputStream stream) throws IOException, ClassNotFoundException{
        ObjectInputStream objIn = new ObjectInputStream(stream);
        return (TabulatedFunction)objIn.readObject();
    }
}
