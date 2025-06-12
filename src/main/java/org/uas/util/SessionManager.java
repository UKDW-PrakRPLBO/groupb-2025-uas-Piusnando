package org.uas.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SessionManager implements Serializable {
    private static final String SESSION_FILE = "session.ser";

    private static SessionManager instance;
    private boolean isLoggedIn = false;

    // Static method to get the singleton instance
    public static SessionManager getInstance() {
        if(instance == null){
            synchronized (SessionManager.class) {
                if(instance == null){
                    instance = new SessionManager();
                    instance.createSessionFile();
                }
            }
        }
        return instance;
    }

    // Method to check if the session file doesn't exist
    public void createSessionFile() {
        File file = new File(SESSION_FILE);
        if (!file.exists()) {
            saveSession();
        } else {
            loadSession();
        }
    }

    private void loadSession() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SESSION_FILE))) {
            SessionManager sessionManager = (SessionManager) ois.readObject();
            this.isLoggedIn = sessionManager.isLoggedIn;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveSession() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SESSION_FILE))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to check if user is logged in
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    // Method to simulate login
    public void login() {
        this.isLoggedIn = true;
        saveSession();
    }

    // Method to simulate logout
    public void logout() {
        this.isLoggedIn = false;
        saveSession();
    }
}
