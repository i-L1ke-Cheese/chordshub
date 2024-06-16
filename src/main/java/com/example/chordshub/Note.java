package com.example.chordshub;

public class Note {
    private final String note;
    private final boolean verlaagd;
    private final boolean verhoogd;

    public Note(String note, boolean verhoogd, boolean verlaagd) {
        this.note = note;
        this.verhoogd = verhoogd;
        this.verlaagd = verlaagd;
    }

    public String getNote() {
        return note;
    }

    public boolean isVerlaagd() {
        return verlaagd;
    }
    public boolean isVerhoogd() {
        return verhoogd;
    }

    @Override
    public String toString() {
        return verhoogd ? note + "#" : verlaagd ? note + "b" : note;
    }
}
