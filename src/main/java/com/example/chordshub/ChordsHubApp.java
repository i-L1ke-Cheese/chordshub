package com.example.chordshub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChordsHubApp {
    private final FileIOManager ioManager;
    private ArrayList<Chord> chords;

    public ChordsHubApp(FileIOManager ioManager) {
        this.ioManager = ioManager;
        this.chords = ioManager.getChordsFromFile();
    }

    public ArrayList<Chord> getChords() {
        this.chords = ioManager.getChordsFromFile();
        return this.chords;
    }

    public ArrayList<Chord> searchChords(String keyWord, ArrayList<Chord> chordsToSearch) {
        List<String> keyWords = Arrays.asList(keyWord.trim().split(" "));
        return chordsToSearch.stream()
                .filter(chord -> keyWords.stream()
                        .allMatch(word -> chord.toString().toLowerCase().contains(word.toLowerCase())))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Chord> searchChords(String keyWord) {
        return searchChords(keyWord, this.chords);
    }

    public ArrayList<Chord> getChordsWithType(String type, ArrayList<Chord> chordsToSearch) {
        ArrayList<Chord> chordsFound = new ArrayList<>();
        for(Chord c : chordsToSearch) {
            if(c.type.equalsIgnoreCase(type)) {
                chordsFound.add(c);
            }
        }
        return chordsFound;
    }

    public Chord getAkkoordFromName(String name) {
        for(Chord a : chords) {
            if(a.name.equalsIgnoreCase(name) || a.fullName.equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }

    public ArrayList<Chord> getSimilarChords(Chord akkoord) {
        ArrayList<Chord> chords = new ArrayList<>();
        int amountFound = 0;
        int maxAmount = 3;
        for(Chord a : this.chords) {
            if(a.type.equalsIgnoreCase(akkoord.type)) {
                if(!a.name.equalsIgnoreCase(akkoord.name)) {
                    chords.add(a);
                    amountFound++;
                }
            }
            if(amountFound >= maxAmount) {
                break;
            }
        }
        return chords;
    }

    public Chord getAkkoordFromTonen(String stringMetTonen) {
        String[] data = stringMetTonen.split(" ");
        ArrayList<Note> tonenLijst = new ArrayList<>();
        for(String i : data) {
            String[] chars = ioManager.splitStringToCharacters(i);
            tonenLijst.add(ioManager.getToonFromData(chars));
        }
        return getAkkoordFromTonen(tonenLijst);
    }

    public Chord getAkkoordFromTonen(ArrayList<Note> tonenVanAkkoord) {
        for(Chord a : chords) {
            if(a.hasNotes(tonenVanAkkoord)) {
                return a;
            }
        }
        return null;
    }
}
