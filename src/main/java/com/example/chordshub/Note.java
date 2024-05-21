package com.example.chordshub;

public class Note {
    private String note;
    private boolean verlaagd;
    private boolean verhoogd;

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
