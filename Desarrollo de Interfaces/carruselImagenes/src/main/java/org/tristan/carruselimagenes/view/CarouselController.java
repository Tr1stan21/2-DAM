package org.tristan.carruselimagenes.view;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.List;
import java.util.Objects;

public class CarouselController {
    @FXML
    private ImageView imageView;
    @FXML
    private StackPane stackPane;

    private int index = 0;

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

        // Clip para que la imagen no se salga visualmente del StackPane
        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(stackPane.widthProperty());
        clip.heightProperty().bind(stackPane.heightProperty());
        stackPane.setClip(clip);

    }

    public void next() {

        animateSlide(1);
    }

    public void previous() {
        animateSlide(-1);
    }

    private void animateSlide(final int direction) {
        final double width = stackPane.getWidth();

        // Mover hacia fuera
        TranslateTransition out = new TranslateTransition(Duration.millis(200), imageView);
        out.setFromX(0);
        out.setToX(direction * width);

        // Fade-out
        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), imageView);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        // Cuando termina la salida:
        out.setOnFinished(_ -> {
            fadeOut.stop();
            imageView.setOpacity(0.0);

            index = index + direction;
            imageView.setImage(loadImage());

            imageView.setTranslateX(direction * width);

            // Mover hacia dentro
            TranslateTransition in = new TranslateTransition(Duration.millis(200), imageView);
            in.setFromX(-direction * width);
            in.setToX(0);

            // Fade-in
            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), imageView);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);

            // Lanzar entrada + fade-in
            in.play();
            fadeIn.play();
        });

        // Lanzar salida + fade-out simultáneo
        out.play();
        fadeOut.play();
    }
}

