package org.tristan.carruselimagenes.view;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

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
    private boolean animating = false;

    private final List<String> imagePaths = List.of(
            "1.jpg",
            "2.jpg",
            "3.jpg",
            "4.jpg",
            "5.jpg",
            "6.jfif"
            );

    private Image loadImage  () {
        if(index < 0) index = (imagePaths.size()-1);
        if(index >= imagePaths.size()) index = (0);

        return new Image(Objects.requireNonNull(getClass().getResource("/org/tristan/carruselimagenes/images/"+imagePaths.get(index))).toExternalForm());
    }

    public void initialize() {

        imageView.fitWidthProperty().bind(stackPane.widthProperty().multiply(0.9));
        imageView.fitHeightProperty().bind(stackPane.heightProperty().multiply(0.9));
        imageView.setImage(loadImage());

    }

    public void next() {
        index++;
        if (animating) return;
        animate(true);   // true = hacia la derecha
    }

    public void previous() {
        index--;
        if (animating) return;
        animate(false);  // false = hacia la izquierda
    }

    private void animate(boolean forward) {

        animating = true;

        double width = stackPane.getWidth();

        // 1) Animación de salida (mueve la imagen actual)
        TranslateTransition out = new TranslateTransition(Duration.millis(300), imageView);
        out.setFromX(0);
        out.setToX(forward ? width : -width);

        out.setOnFinished(e -> {
            imageView.setImage(loadImage());

            // Colocar la imagen nueva fuera (lado opuesto)
            imageView.setTranslateX(forward ? -width : width);

            // 3) Animación de entrada
            TranslateTransition in = new TranslateTransition(Duration.millis(300), imageView);
            in.setFromX(forward ? -width : width);
            in.setToX(0);

            in.setOnFinished(ev -> animating = false);
            in.play();
        });

        out.play();
    }


}
