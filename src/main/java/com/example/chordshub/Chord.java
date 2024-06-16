package com.example.chordshub;

import java.util.ArrayList;

public class Chord {
    private final ArrayList<Note> notes;
    public final String name;
    public final String fullName;
    public String type;

    public Chord(ArrayList<Note> notes, String name, String fullName) {
        this.name = name;
        this.fullName = fullName;
        this.notes = notes;
        this.type = Character.toString(name.charAt(0));
    }

    public boolean hasNotes(ArrayList<Note> t) {
        if(t.size() != 3) {
            return false;
        }
        for (Note tItem : t) {
            boolean found = false;
            for (Note tonenItem : notes) {
                if (tItem.toString().equalsIgnoreCase(tonenItem.toString())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        if(this.type.equalsIgnoreCase("separator")) {
            return "######## " + this.name + " TYPE CHORDS ########";
        }

        StringBuilder notesString = new StringBuilder();
        for(Note note : notes) {
            notesString.append(", ").append(note.toString());
        }

        return this.name + " (" + this.fullName + ")" + notesString + ".";
    }
}
