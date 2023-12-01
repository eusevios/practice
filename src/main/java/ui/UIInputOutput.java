package ui;

import functions.ArrayTabulatedFunction;
import functions.TabulatedFunction;
import io.FunctionsIO;
import javafx.scene.control.Tab;
import javafx.stage.FileChooser;

import java.io.*;

public class UIInputOutput {

    static public void saveBin(TabulatedFunction function) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Bin files", "*.bin"), new FileChooser.ExtensionFilter("JSON files", "*.json"));

        File file = fileChooser.showSaveDialog(null);

        String extension = file.getName().substring(file.getName().lastIndexOf(".")+1);
        System.out.println(extension);

        switch (extension){
            case "bin":
                FileOutputStream outputStream = new FileOutputStream(file);
                BufferedOutputStream bufferedInputStream = new BufferedOutputStream(outputStream);
                FunctionsIO.serialize(bufferedInputStream, function);
                break;
            case "json":
                BufferedWriter writer = new BufferedWriter( new FileWriter(file));
                FunctionsIO.serializeJson(writer, (ArrayTabulatedFunction) function);

        }

        FileOutputStream outputStream = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

        FunctionsIO.serialize(bufferedOutputStream, function);

    }

    static public TabulatedFunction loadBin() throws IOException, ClassNotFoundException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Bin files", "*.bin"), new FileChooser.ExtensionFilter("JSON files", "*.json"));

        File file = fileChooser.showOpenDialog(null);

        String extension = file.getName().substring(file.getName().lastIndexOf(".")+1);

        TabulatedFunction function = null;
        switch (extension){
            case "bin":
                FileInputStream inputStream = new FileInputStream(file);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                function = FunctionsIO.deserialize(bufferedInputStream);
                break;
            case "xml":
                BufferedReader reader = new BufferedReader( new FileReader(file));
                function = FunctionsIO.deserializeXml(reader);

        }

        return function;





    }

}
