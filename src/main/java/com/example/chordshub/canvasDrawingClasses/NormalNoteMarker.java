package com.example.chordshub.canvasDrawingClasses;

import javafx.scene.canvas.Canvas;

public class NormalNoteMarker extends NoteMarker{
    public NormalNoteMarker(Canvas canvas, int noteCircleSize, int smallNoteCircleSize, double[][] positionsOfNotes, double keyWidth) {
        super(canvas, noteCircleSize, smallNoteCircleSize, positionsOfNotes, keyWidth);
    }

    @Override
    protected void drawNoteMarker(int index) {
        double[] position = positionsOfNotes[index];
        g.fillOval(position[0] - ((double) noteCircleSize / 2), position[1] - ((double) noteCircleSize / 2), noteCircleSize, noteCircleSize);
    }

}
