package com.example.chordshub;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIOManager {
    public final String PATH_AKKOORDEN = "akkoorden.txt";
    public final String SEPERATOR = ",";
    private static FileIOManager instance;

    public FileIOManager() {
        instance = this;
    }

    public static FileIOManager getInstance() {
        if(instance == null) {
            instance = new FileIOManager();
        }
        return instance;
    }

    public ArrayList<String> readFile(String path) {
        ArrayList<String> data = new ArrayList<>();

        File fileOBJ = new File(path);

        try {
            Scanner fileReader = new Scanner(fileOBJ);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                data.add(line);
            }

            fileReader.close();
        } catch(FileNotFoundException e) {
            System.out.println("Bestand niet gevonden");
        }
        return data;
    }

    public ArrayList<Chord> getChordsFromFile() {
        ArrayList<String> lines = readFile(PATH_AKKOORDEN);
        ArrayList<Chord> akkoordenFromFile = new ArrayList<>();
        for(String line : lines) {
            String[] data = line.split(SEPERATOR);
            String naam = data[0];
            String langereNaam = data[1];
            for (int i = 0; i < data.length - 2; i++) {
                data[i] = data[i + 2];
            }
            String[] newData = new String[data.length - 2];
            System.arraycopy(data, 0, newData, 0, newData.length);
            ArrayList<Note> tonen = new ArrayList<>();
            for(String i : newData) {
                String[] chars = splitStringToCharacters(i);
                tonen.add(getToonFromData(chars));
            }
            akkoordenFromFile.add(new Chord(tonen, naam, langereNaam));
        }
        return akkoordenFromFile;
    }

    public Note getToonFromData(String[] data) {
        if(data.length == 0) {
            return null;
        }
        String base = data[0];
        if(data.length == 1) {
            return new Note(base, false, false);
        } else if(data.length == 2) {
            if(data[1].equals("#")) {
                return new Note(base, true, false);
            } else if(data[1].equals("b")) {
                return new Note(base, false, true);
            }
        }
        return null;
    }

    public String[] splitStringToCharacters(String string) {
        char[] charArray = string.toCharArray();
        String[] stringArray = new String[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            stringArray[i] = String.valueOf(charArray[i]);
        }
        return stringArray;
    }
}
