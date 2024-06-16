package com.example.chordshub.canvasDrawingClasses;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class NoteMarker {
    protected final GraphicsContext g;

    protected final int smallNoteCircleSize;
    protected final int noteCircleSize;
    protected final double keyWidth;

    protected final double[][] positionsOfNotes;

    public NoteMarker(Canvas canvas, int noteCircleSize, int smallNoteCircleSize, double[][] positionsOfNotes, double keyWidth) {
        this.g = canvas.getGraphicsContext2D();
        this.smallNoteCircleSize = smallNoteCircleSize;
        this.noteCircleSize = noteCircleSize;
        this.keyWidth = keyWidth;
        this.positionsOfNotes = positionsOfNotes;
    }

    public void markNote(int index) {
        g.setFill(Color.RED);
        drawNoteMarker(index);
    }

    protected abstract void drawNoteMarker(int index);
}
