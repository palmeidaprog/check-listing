package com.github.palmeidaprog.checklisting.main;

/*
* Check Listing
* @author Paulo R. Almeida Filho
* http://www.github.com/palmeidaprog/check-listing
* @email palmeidaprogramming@gmail.com
*/

/*Check Listing settings*/

import java.io.File;
import java.io.Serializable;

public class Settings implements Serializable {

    // Don't serialize these
    private transient File macFolder = new File(System.getProperty("user.home") +
            "/Library/Application Support/CheckListing");
    private transient File windowsFolder = new File(System.getProperty("user.home") +
            "/AppData/Roaming/CheckListing");
    private transient File linuxFolder = new File(System.getProperty("user.home") +
            "/.CheckListing");

    // Constructor for serialization
    public Settings(Settings s) {
        // todo: create from serial.
    }

    //--Singleton design----------------------------------------------------

    private static volatile Settings instance = null;
    private Settings() { }
    public synchronized static Settings getInstance() {
        if(instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    //------------------------------------------------------------------------

    // get folder to place configuration file
    public File getConfigDir() {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("windows")) {
            return windowsFolder;
        }
        else if(os.contains("mac")) {
            return macFolder;
        }
        else { // linux and others
            return linuxFolder; // return program root folder
        }
    }

    //--Serializable implementation-------------------------------------------

    // todo: create save and read objfile methods

    // todo: implement serialization once the class has all the variables
    @Override
    public String toString() {
        return "empty";
    }

}
