package app;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class HomeController {

    private Thread thread;

    @FXML
    private Text text;

    @FXML
    private Button button;

    @FXML
    private Button stopButton;

    @FXML
    private void initialize() {
        stopButton.setDisable(true);
    }

    @FXML
    private void handleButton() {
        stopButton.setDisable(false);
        text.setText("You clicked the button!");
        button.setText("You clicked me");
        thread = new Thread(new Background(this));
        thread.start();
    }

    @FXML
    private void stop() {
        thread.interrupt();
        stopButton.setDisable(true);
    }

    public void updateClock(int time) {
        Platform.runLater(() -> text.setText(Integer.toString(time)));
    }

    private class Background extends Task<Void> {

        private HomeController controller;

        public Background(HomeController controller) {
            this.controller = controller;
        }

        @Override
        protected Void call() throws Exception {
            new Clock(controller).go();
            return null;
        }

        @Override
        protected void done() {
            Platform.runLater(() -> {
                text.setText("Clock is done");
                stopButton.setDisable(true);
                button.setText("Button");
            });
        }
    }
}
