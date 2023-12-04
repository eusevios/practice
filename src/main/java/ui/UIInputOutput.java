package ui;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import io.ArrayTabulatedFunctionXMLSerialization;
import io.FunctionsIO;
import javafx.scene.control.Tab;
import javafx.stage.FileChooser;

import java.io.*;

public class UIInputOutput {

    static public void save(TabulatedFunction function) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Bin files", "*.bin"),  new FileChooser.ExtensionFilter("XML files", "*.xml"));
        if(function.getClass() == ArrayTabulatedFunction.class) fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));
        File file = fileChooser.showSaveDialog(null);

        if(file!=null) {

            String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);

            switch (extension) {
                case "bin":
                    FileOutputStream outputStream = new FileOutputStream(file);
                    BufferedOutputStream bufferedInputStream = new BufferedOutputStream(outputStream);
                    FunctionsIO.serialize(bufferedInputStream, function);
                    break;
                case "json":
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    FunctionsIO.serializeJson(writer, function);
                    break;
                case "xml":
                    BufferedWriter writer1 = new BufferedWriter(new FileWriter(file));
                    FunctionsIO.serializeXml(writer1, function);
                    break;
            }

        }

    }

    static public TabulatedFunction load() throws IOException, ClassNotFoundException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Bin files", "*.bin"), new FileChooser.ExtensionFilter("XML files", "*.xml"));
        if(Settings.getInstance().getFactory().getClass() == ArrayTabulatedFunctionFactory.class) fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));
        File file = fileChooser.showOpenDialog(null);

        TabulatedFunction function = null;

        if(file!=null) {

            String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);

            switch (extension) {
                case "bin":
                    FileInputStream inputStream = new FileInputStream(file);
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                    function = FunctionsIO.deserialize(bufferedInputStream);
                    break;
                case "json":
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    function = FunctionsIO.deserializeJson(reader);
                    break;
                case "xml":
                    BufferedReader reader1 = new BufferedReader(new FileReader(file));
                    function = FunctionsIO.deserializeXml(reader1);
                    break;

            }
        }

        return function;

    }

}
