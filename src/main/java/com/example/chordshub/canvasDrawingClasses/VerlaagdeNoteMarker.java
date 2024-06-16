package com.example.chordshub.canvasDrawingClasses;

import javafx.scene.canvas.Canvas;

public class VerlaagdeNoteMarker extends NoteMarker{
    public VerlaagdeNoteMarker(Canvas canvas, int noteCircleSize, int smallNoteCircleSize, double[][] positionsOfNotes, double keyWidth) {
        super(canvas, noteCircleSize, smallNoteCircleSize, positionsOfNotes, keyWidth);
    }

    @Override
    protected void drawNoteMarker(int index) {
        double[] position = positionsOfNotes[index];
        double posx = position[0] - keyWidth / 2;
        double posy = position[1] - 50 + this.smallNoteCircleSize;
        g.fillOval(posx - ((double) smallNoteCircleSize / 2), posy - ((double) smallNoteCircleSize / 2), smallNoteCircleSize, smallNoteCircleSize);
    }

}
