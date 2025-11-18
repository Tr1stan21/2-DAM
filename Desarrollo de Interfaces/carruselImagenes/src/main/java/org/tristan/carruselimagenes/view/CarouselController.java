package org.tristan.carruselimagenes.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CarouselController {
    @FXML
    private ImageView imageView;
    @FXML
    private StackPane stackPane;
    @FXML
    private Button next;
    @FXML
    private Button previous;

    private int index = 0;

    private final List<String> imagePaths = List.of(
            "1.jpg",
            "2.jpg",
            "3.jpg",
            "4.jpg",
            "5.jpg",
            "6.jfif"
            );

    private void show() {
        if(index < 0) index = (imagePaths.size()-1);
        if(index >= imagePaths.size()) index = (0);

        Image img = new Image(Objects.requireNonNull(getClass().getResource("/org/tristan/carruselimagenes/images/"+imagePaths.get(index))).toExternalForm());
        imageView.setImage(img);
    }

    public void initialize() {

        imageView.fitWidthProperty().bind(stackPane.widthProperty().multiply(0.9));
        imageView.fitHeightProperty().bind(stackPane.heightProperty().multiply(0.9));

        show();
    }

    public void next() {
        index+=1;
        show();
    }

    public void previous() {
        index-=1;
        show();
    }

}
