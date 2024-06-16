package com.example.chordshub;

import com.example.chordshub.canvasDrawingClasses.ChordsImageDrawer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public AnchorPane chordsInfoPane;
    @FXML
    public Label chordsInfoNameLabel;
    @FXML
    public Canvas chordsInfoCanvas;
    @FXML
    public Label vergelijkbareChordsLabel;
    @FXML
    public Label vergelijkbareChord1;
    @FXML
    public Label vergelijkbareChord2;
    @FXML
    public Label vergelijkbareChord3;
    @FXML
    private TextField searchbar;
    @FXML
    private ListView<Chord> chordsListView;

    private ChordsHubApp chordsHubApp;
    private ChordsImageDrawer imageDrawer;

    @FXML
    protected void onClearSearchButtonPressed() {
        resetSearchBar();
    }

    @FXML
    protected void onTonenButtonPressed() {
        Chord a = chordsHubApp.getAkkoordFromTonen(searchbar.getText());
        checkSearchedChordAndUpdateListView(a);
    }

    @FXML
    protected void onAkkoordButtonPressed() {
        Chord a = chordsHubApp.getAkkoordFromName(searchbar.getText());
        checkSearchedChordAndUpdateListView(a);
    }

    private void checkSearchedChordAndUpdateListView(Chord a) {
        if(a == null) {
            System.out.println("Akkoord not found");
        } else {
            ArrayList<Chord> b = new ArrayList<>();
            b.add(a);
            updateListView(b);
        }
    }

    @FXML
    protected void onSearchbarChanged() {
        chordsListView.getItems().clear();

        ArrayList<Chord> chordsSearched = chordsHubApp.searchChords(searchbar.getText());
        chordsSearched = addSeperators(chordsSearched);

        ObservableList<Chord> oListChordsSearched = FXCollections.observableArrayList(chordsSearched);
        chordsListView.getItems().addAll(oListChordsSearched);
    }

    @FXML
    protected void onListviewClicked() {
        Chord clickedItem = chordsListView.getSelectionModel().getSelectedItem();
        if(!clickedItem.type.equalsIgnoreCase("separator")) {
            this.chordsInfoNameLabel.setText(clickedItem.toString());
            resetCanvas();
            this.imageDrawer.markChord(clickedItem);
            updateVergelijkbareAkkoorden(chordsHubApp.getSimilarChords(clickedItem));
        }
    }

    private void updateVergelijkbareAkkoorden(ArrayList<Chord> vergelijkbareAkkoorden) {
        this.vergelijkbareChordsLabel.setText("Vergelijkbare Akkoorden:");
        for(int i = 0; i < vergelijkbareAkkoorden.size(); i++) {
            switch(i) {
                case 1:
                    this.vergelijkbareChord1.setText(vergelijkbareAkkoorden.get(i).toString());
                case 2:
                    this.vergelijkbareChord2.setText(vergelijkbareAkkoorden.get(i).toString());
                case 3:
                    this.vergelijkbareChord3.setText(vergelijkbareAkkoorden.get(i).toString());
            }
        }
    }

    private void resetCanvas() {
        this.imageDrawer.setBackgroundColor(Color.WHITE);
        this.imageDrawer.drawPianoKeys();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.chordsHubApp = new ChordsHubApp(new FileIOManager());
        this.imageDrawer = new ChordsImageDrawer(chordsInfoCanvas);

        resetSearchBar();
        resetCanvas();
    }

    private void resetSearchBar() {
        searchbar.setText("");
        updateListView(this.chordsHubApp.getChords());
    }

    private void updateListView(ArrayList<Chord> chordsForListView) {
        chordsListView.getItems().clear();
        ObservableList<Chord> oListChords = FXCollections.observableArrayList(addSeperators(chordsForListView));
        chordsListView.getItems().addAll(oListChords);
    }

    private ArrayList<Chord> addSeperators(ArrayList<Chord> chords) {
        ArrayList<Chord> newList = new ArrayList<>();
        String[] types = {"C", "D", "E", "F", "G", "A", "B"};
        for(String t : types) {
            ArrayList<Chord> chordsWithThisType = this.chordsHubApp.getChordsWithType(t, chords);
            if(!chordsWithThisType.isEmpty()) {
                Chord seperatorChord = new Chord(new ArrayList<>(), t, t);
                seperatorChord.type = "separator";
                newList.add(seperatorChord);
                newList.addAll(chordsWithThisType);
            }
        }
        return newList;
    }
}