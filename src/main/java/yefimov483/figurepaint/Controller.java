package yefimov483.figurepaint;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import org.w3c.dom.events.MouseEvent;
import yefimov483.figurepaint.GraphicsHelper.Drawer;
import yefimov483.figurepaint.Figures.CircleCustom;
import yefimov483.figurepaint.Figures.Figure;
import yefimov483.figurepaint.Figures.SquareCustom;
import yefimov483.figurepaint.Figures.TriangleCustom;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Controller{
    private String[] lst = new String[]{"CircleCustom", "SquareCustom", "TriangleCustom"};
    private Figure fig;
    public List<Figure> figs = new ArrayList<>();
    private String selectedName;

    @FXML
    public Canvas canvas;

    @FXML
    public ComboBox<String> comboBox;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private ListView<String> list;
    @FXML
    public BorderPane borderPane;

    @FXML
    protected void addAction() {
        //add figure on canvas
        switch (comboBox.getValue().toLowerCase()){
            case "circlecustom": fig = new CircleCustom(50, colorPicker.getValue(), canvas);
                break;
            case "squarecustom": fig = new SquareCustom(50, colorPicker.getValue(), canvas);
                break;
            case "trianglecustom": fig = new TriangleCustom(50, colorPicker.getValue(), canvas);
        }

        //add figs in list and their names in other list
        list.getItems().add(fig.name);
        figs.add(fig);
        Drawer.invalidate(canvas, figs);

        list.getSelectionModel().clearSelection();
    }

    @FXML
    protected void deleteAction(){
        //add dialog

        for(int i = 0; i < list.getItems().size(); i++){
            if(list.getSelectionModel().isSelected(i)) {
                list.getItems().remove(i);
                figs.remove(i);
                Drawer.invalidate(canvas, figs);
            }
        }

        list.getSelectionModel().clearSelection();
    }

    public void initialize() {

        //default setting for combo
        comboBox.setItems(FXCollections.observableList(Arrays.stream(lst).toList()));
        comboBox.getSelectionModel().selectFirst();

        //fill canvas with starting
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //figure clicked
        canvas.setOnMouseClicked(event -> {
            //selecting figs with shift
            if(event.isShiftDown()){
                list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                for(Figure x : figs){
                    if(x.isSelected(event.getX(), event.getY())){
                        x.selected = true;
                        list.getSelectionModel().select(figs.indexOf(x));
                        Drawer.invalidate(canvas, figs);
                    }
                }
            }
            else{
                //selecting figs without shift
                for(Figure x : figs){
                    if(x.isSelected(event.getX(), event.getY())){
                        x.selected = true;

                        //set current selected figure in list
                        selectedName = x.name;
                        for(int i = 0; i < list.getItems().size(); i++){
                            if(list.getItems().get(i) == selectedName){
                                list.getSelectionModel().select(i);
                                break;
                            }
                        }
                    }
                    else {
                        x.selected = false;
                    }
                }
                Drawer.invalidate(canvas, figs);
            }

        });

        //figure moved
        canvas.setOnMouseDragged(event ->{
            if(event.isShiftDown()){
                double dx = 0;
                double dy = 0;
                for(Figure x : figs){
                    if(x.isSelected(event.getX(), event.getY()) && x.selected){
                        dx = x.posX - event.getX();
                        dy = x.posY - event.getY();
                        x.posX = event.getX();
                        x.posY = event.getY();
                    }
                    if(!x.isSelected(event.getX(), event.getY()) && x.selected){
                        x.posX -= dx;
                        x.posY -= dy;
                    }

                }
                Drawer.invalidate(canvas, figs);

            }
            else {
                for(Figure x : figs){
                    //if cursor includes fig and fig was selected
                    if(x.isSelected(event.getX(), event.getY()) && x.name == selectedName){
                        x.posX = event.getX();
                        x.posY = event.getY();
                        selectedName = x.name;
                        Drawer.invalidate(canvas, figs);
                        break;
                    }
                }
            }

        });

        //change item from list
        list.setOnMouseClicked(event -> {
            list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            if(event.getClickCount() == 2){
                String figName = list.getSelectionModel().getSelectedItem();

                TextInputDialog dialog = new TextInputDialog(figName);
                dialog.setTitle("Changing name");
                dialog.setHeaderText("Enter new name for figure:");
                dialog.setContentText("Name:");
                Optional<String> result = dialog.showAndWait();

                result.ifPresent(name -> {
                    for(Figure x : figs){
                        if(x.name.equals(figName)){
                            x.name = name;
                            list.getItems().set(figs.indexOf(x), name);
                            break;
                        }
                    }
                });
            }

            for(Figure x : figs){
                if(x.name.equals(list.getSelectionModel().getSelectedItem())){
                    selectedName = x.name;
                    x.selected = true;
                }
                else {
                    x.selected = false;
                }
                Drawer.invalidate(canvas, figs);
            }
        });
    }
}