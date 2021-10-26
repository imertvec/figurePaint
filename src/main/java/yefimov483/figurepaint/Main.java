package yefimov483.figurepaint;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import yefimov483.figurepaint.GraphicsHelper.Drawer;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Lab 01 Efimov483");
        Controller controller = fxmlLoader.getController();

        //set actions on resized form
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            controller.canvas.setWidth(stage.getWidth() - 200);
            Drawer.invalidate(controller.canvas, controller.figs);
        });

        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            controller.canvas.setHeight(stage.getHeight() - 100);
            Drawer.invalidate(controller.canvas, controller.figs);
        });



        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}