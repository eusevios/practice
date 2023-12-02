package io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.Point;
import functions.TabulatedFunction;
import functions.factory.TabulatedFunctionFactory;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;


final public class FunctionsIO {

    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        PrintWriter prWriter = new PrintWriter(writer);
        prWriter.println(function.getCount());
        for (Point point : function) {
            prWriter.printf("%f %f\n", point.x, point.y);
        }
        prWriter.flush();

    }

    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {

        int count = 0;

        try {
            count = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        double[] xValues = new double[count];
        double[] yValues = new double[count];

        NumberFormat numbForm = NumberFormat.getInstance(Locale.forLanguageTag("ru"));

        for (int i = 0; i < count; i++) {

            String str = null;

            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] splitStr = str.split(" ");

            try {

                xValues[i] = numbForm.parse(splitStr[0]).doubleValue();
                yValues[i] = numbForm.parse(splitStr[1]).doubleValue();

            } catch (ParseException e) {

                throw new IOException(e);

            }

        }

        return factory.create(xValues, yValues);

    }

    public static void serialize(BufferedOutputStream stream, TabulatedFunction function) throws IOException {

        ObjectOutputStream outStream;

        outStream = new ObjectOutputStream(stream);
        outStream.writeObject(function);
        stream.flush();

    }

    public static void writeTabulatedFunction(BufferedOutputStream outputStream,
                                              TabulatedFunction function) throws IOException {
        DataOutputStream out = new DataOutputStream(outputStream);
        out.writeInt(function.getCount());
        for (Point point : function) {
            out.writeDouble(point.x);
            out.writeDouble(point.y);
        }
        out.flush();
    }

    public static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) throws IOException {
        DataInputStream in = new DataInputStream(inputStream);
        int size = in.readInt();
        double[] arrayOfX = new double[size];
        double[] arrayOfY = new double[size];

        for (int i = 0; i < size; i++) {
            arrayOfX[i] = in.readDouble();
            arrayOfY[i] = in.readDouble();
        }
        return factory.create(arrayOfX, arrayOfY);
    }

    public static TabulatedFunction deserialize(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        ObjectInputStream objIn = new ObjectInputStream(stream);
        return (TabulatedFunction) objIn.readObject();
    }

    public static void serializeXml(BufferedWriter writer, TabulatedFunction function) throws IOException {
        XStream xStream = new XStream();
        writer.write(xStream.toXML(function));
        writer.flush();
    }

    public static TabulatedFunction deserializeXml(BufferedReader reader) {
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        TabulatedFunction func = (TabulatedFunction) xStream.fromXML(reader);
        return func;
    }

    public static void serializeJson(BufferedWriter writer, TabulatedFunction function) throws IOException {
        ObjectMapper objMap = new ObjectMapper();
        writer.write(objMap.writeValueAsString(function));
        writer.flush();
    }

    public static TabulatedFunction deserializeJson(BufferedReader reader) throws IOException {
        ObjectMapper objMap = new ObjectMapper();
        return (objMap.readerFor(TabulatedFunction.class)).readValue(reader);
    }
}
