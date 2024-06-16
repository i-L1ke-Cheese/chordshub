package com.example.chordshub.canvasDrawingClasses;

import com.example.chordshub.Chord;
import com.example.chordshub.Note;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class ChordsImageDrawer {
    private final Canvas canvas;
    private final GraphicsContext g;
    private final String[] notesOfKeys;
    private double[][] positionsOfNotes;

    private final int noteCircleSize = 20;

    private double keyWidth;

    public ChordsImageDrawer(Canvas canvas) {
        this.canvas = canvas;
        this.g = canvas.getGraphicsContext2D();
        this.notesOfKeys = new String[]{"F", "G", "A", "B", "C", "D", "E", "F", "G", "A", "B", "C", "D", "E"};
        this.keyWidth = 0;
    }

    public void drawRect(double x, double y, double w, double h, Color color) {
        g.setFill(color);
        g.fillRect(x, y, w, h);
    }

    public void setBackgroundColor(Color bgColor) {
        drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), bgColor);
    }

    public void drawText(double x, double y, String text, Color color) {
        g.setFill(color);
        g.setTextAlign(TextAlignment.CENTER);
        g.setTextBaseline(VPos.CENTER);
        g.setFont(new Font("Ebrima", 25));
        g.fillText(text, x, y);
    }

    public void drawPianoKeys() {
        int amountOfKeys = this.notesOfKeys.length;
        int padding = 10;
        int keyBorderThickness = 3;
        this.keyWidth = (canvas.getWidth() - (2 * padding)) / amountOfKeys;
        double darkKeyOffset = keyWidth * 0.7;
        double darkKeyWidth = keyWidth * 0.6;
        int keyHeight = 120;
        double darkKeyHeight = (double) keyHeight / 2;

        // draw white keys
        for(int i = 0; i < amountOfKeys; i++) {
            drawRect(i * keyWidth + padding, padding, keyWidth, keyHeight, Color.BLACK);

            double x = i * keyWidth + padding + keyBorderThickness;
            double y = padding + keyBorderThickness;
            double w = keyWidth - (keyBorderThickness * 2);
            if(i != (amountOfKeys - 1)) {
                w += keyBorderThickness;
            }
            double h = keyHeight - (keyBorderThickness * 2);

            drawRect(x, y, w, h, Color.WHITE);
            double textX = x + (keyWidth / 2) - ((double) padding / 2) + keyBorderThickness;
            double textY = y + keyHeight - 20;
            drawText(textX, textY, this.notesOfKeys[i], Color.GRAY );
            if(this.positionsOfNotes == null) {
                this.positionsOfNotes = new double[1][2];
                this.positionsOfNotes[0][0] = textX;
                this.positionsOfNotes[0][1] = textY - noteCircleSize;
            } else {
                this.positionsOfNotes = addPosition(positionsOfNotes, new double[]{textX, textY - noteCircleSize});
            }
        }
        // draw black keys
        for(int i = 0; i < amountOfKeys; i++) {
            if(i + 1 < amountOfKeys) {
                if(!(this.notesOfKeys[i].equalsIgnoreCase("B") && this.notesOfKeys[i + 1].equalsIgnoreCase("C"))) {
                    if(!(this.notesOfKeys[i].equalsIgnoreCase("E") && this.notesOfKeys[i + 1].equalsIgnoreCase("F"))) {
                        double x = (i * keyWidth + padding) + darkKeyOffset;
                        drawRect(x, padding, darkKeyWidth, darkKeyHeight, Color.BLACK);
                    }
                }
            }
        }
    }

    public void markChord(Chord chordToMark) {
        ArrayList<Note> notes = chordToMark.getNotes();
        int currentNoteIndex = 0;
        for(int i = 0; i < this.notesOfKeys.length; i++) {
            String currentNote = this.notesOfKeys[i];
            if(currentNote.equalsIgnoreCase(notes.get(currentNoteIndex).getNote())) {
                markNote(i, notes.get(currentNoteIndex).isVerlaagd(), notes.get(currentNoteIndex).isVerhoogd());
                currentNoteIndex += 1;
                if(currentNoteIndex >= notes.size()) {
                    break;
                }
            }
        }
    }

    public void markNote(int index, boolean verlaagd, boolean verhoogd) {
        g.setFill(Color.RED);
        NoteMarker marker;
        int smallNoteCircleSize = 13;
        if(verlaagd) {
            marker = new VerlaagdeNoteMarker(canvas, noteCircleSize, smallNoteCircleSize, positionsOfNotes, keyWidth);
        } else if (verhoogd) {
            marker = new VerhoogdeNoteMarker(canvas, noteCircleSize, smallNoteCircleSize, positionsOfNotes, keyWidth);
        } else {
            marker = new NormalNoteMarker(canvas, noteCircleSize, smallNoteCircleSize, positionsOfNotes, keyWidth);
        }
        marker.markNote(index);
    }

    private double[][] addPosition(double[][] originalArray, double[] arrayToAdd) {
        double[][] result = new double[originalArray.length + 1][];
        System.arraycopy(originalArray, 0, result, 0, originalArray.length);
        result[originalArray.length] = arrayToAdd;
        return result;
    }
}
