package com.github.palmeidaprog.checklisting.data;

/*
* Check Listing
* @author Paulo R. Almeida Filho
* http://www.github.com/palmeidaprog/check-listing
* @email palmeidaprogramming@gmail.com
*/

/*Handles write and read ToDoData objects*/

import com.github.palmeidaprog.checklisting.main.Settings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;

public class DataIO {

    /*read from obj file and create a list
   * @return List with the objects read from the file*/
    public static ObservableList<ToDoData> readObjList() {
        ObservableList<ToDoData> list = FXCollections.observableArrayList();
        File objFile = new File(Settings.getInstance().getConfigDir() +
                "/todo.ser");

        if(!objFile.exists()) {
            createObjFile();
        }
        else {
            // readFile
            try (ObjectInputStream objIS = new ObjectInputStream(new FileInputStream(objFile))) {
                while (true) { // infinite loop
                    ToDoData obj = new ToDoData((ToDoData) objIS.readObject());
                    list.add(obj);
                }
            } catch(EOFException e) {
                // do nothing (standard loop exit)
            } catch(IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /*@param list List of objs to be written*/
    public static void updateObjFile(ObservableList<ToDoData> list) {
        File objFile = new File(Settings.getInstance().getConfigDir() +
                "/profiles.ser");

        // create file if it doesn't exist
        if(!objFile.exists()) {
            createObjFile();
        }

        try(ObjectOutputStream objOS = new ObjectOutputStream(new FileOutputStream(objFile))) {
            for(ToDoData p : list) {
                objOS.writeObject(p);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // creates profiles.ser
    private static void createObjFile() {
        try { // todo: dialog error "couldn't create file/dir"
            if(!Settings.getInstance().getConfigDir().mkdirs()) {
                throw new IOException("Couldn't create the directory");
            }
            if(!new File(Settings.getInstance().getConfigDir() + "/profiles.ser").createNewFile()) {
                throw new IOException("Couldn't create the file");
            }
        } catch(IOException e) {
            //e.printStackTrace();
        }
    }
}
