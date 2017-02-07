package com.github.palmeidaprog.checklisting.main;

/*
* Check Listing
* @author Paulo R. Almeida Filho
* http://www.github.com/palmeidaprog/check-listing
* @email palmeidaprogramming@gmail.com
*/

/*Check Listing settings*/

import java.io.*;

public class Settings implements Serializable {
    private String password = null; // dummy password, just for testing purposes
    private byte[] slt;

    // Don't serialize these
    private transient File macFolder = new File(System.getProperty("user.home") +
            "/Library/Application Support/CheckListing");
    private transient File windowsFolder = new File(System.getProperty("user.home") +
            "/AppData/Roaming/CheckListing");
    private transient File linuxFolder = new File(System.getProperty("user.home") +
            "/.CheckListing");

    // Create a temporaty new instancer to update the main instance from
    // serialization
    public Settings(Settings s) {
        Settings.getInstance().updateSalt(s.getSalt());
        Settings.getInstance().updatePassword(s.getPassword());
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        password = pass;
        writeToFile();
    }

    public void setSalt(byte[] salt) {
        slt = salt;
    }

    public byte[] getSalt() {
        return slt;
    }

    //--Serializable implementation-------------------------------------------

    public void updatePassword(String pass) {
        password = pass;
    }

    public void updateSalt(byte[] s) {
        slt = s;
    }

    public void readFromFile() {
        File objFile = new File(Settings.getInstance().getConfigDir() +
                "/config.ser");

        if(!objFile.exists()) {
            createObjFile();
        }
        else {
            // readFile
            try (ObjectInputStream objIS = new ObjectInputStream(new FileInputStream(objFile))) {
                while (true) { // infinite loop
                    Settings obj = new Settings((Settings) objIS.readObject());
                }
            } catch(EOFException e) {
                // do nothing (standard loop exit)
            } catch(IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /*@param list List of objs to be written*/
    public void writeToFile() {
        File objFile = new File(Settings.getInstance().getConfigDir() +
                "/config.ser");

        // create file if it doesn't exist
        if(!objFile.exists()) {
            createObjFile();
        }

        // write Settings to file
        try(ObjectOutputStream objOS = new ObjectOutputStream(new FileOutputStream(objFile))) {
            objOS.writeObject(Settings.getInstance());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // creates profiles.ser
    private void createObjFile() {
        try { // todo: dialog error "couldn't create file/dir"
            if(!Settings.getInstance().getConfigDir().mkdirs()) {
                //throw new IOException("Couldn't create the directory");
            }
            if(!new File(Settings.getInstance().getConfigDir() + "/config.ser").createNewFile()) {
                throw new IOException("Couldn't create the config.set file");
            }
        } catch(IOException e) {
            //e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "password=" + password + "; slt=" + slt;
    }
}
