package local;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class GameController {

    private double progress = 1;
    private int rolled = 1;
    private int random = 1;

    @FXML
    Label gemsLabel;

    @FXML
    Label gemsPerSecondLabel;

    @FXML
    Label unExperiencedLabel;

    @FXML
    Label minerLabel;

    @FXML
    Label engineerLabel;

    @FXML
    Label analystLabel;

    @FXML
    Label robotLabel;

    @FXML
    Label unExperiencedPriceLabel;

    @FXML
    Label minerPriceLabel;

    @FXML
    Label engineerPriceLabel;

    @FXML
    Label analystPriceLabel;

    @FXML
    Label robotPriceLabel;

    @FXML
    ImageView gemImage;

    @FXML
    ProgressBar hp;

    @FXML
    Label hpLabel;

    private String randomGem() {
        while(random == rolled) {
            random = (int) (Math.random() * 7);
        }
        rolled = random;
        if (random == 0)
            return "BlueStone.png";
        if (random == 1)
            return "Diamond.png";
        if (random == 2)
            return "Emerald.png";
        if (random == 3)
            return "GoldBars.png";
        if (random == 4)
            return "Ruby.png";
        if (random == 5)
            return "Sapphire.png";
        if (random == 6)
            return "Topaz.png";
        else
            return null;
    }

    @FXML
    void pressGem() {
        GameInfo.gems += GameInfo.gemsPerClick;
        updateGems();
        GameInfo.health--;
        hpLabel.setText(GameInfo.health + " / 20");
        progress -= 0.05;
        hp.setProgress(progress);

        if (GameInfo.health < 1) {
            GameInfo.health = 20;
            hpLabel.setText(GameInfo.health + " / 20");
            progress = 1.0;
            hp.setProgress(progress);
            gemImage.setImage(new Image(randomGem()));
        }
    }

    @FXML
    void buyUnExperienced() {
        if (GameInfo.gems >= GameInfo.unExperiencedPrice && GameInfo.unExperienced < 100) {
            GameInfo.unExperienced++;
            unExperiencedLabel.setText(String.valueOf(GameInfo.unExperienced));
            GameInfo.gems -= GameInfo.unExperiencedPrice;
            GameInfo.unExperiencedPrice *= 1.2;
            unExperiencedPriceLabel.setText("Price: " + GameInfo.unExperiencedPrice);
            GameInfo.gemsPerSecond += 1.2;
        }
    }

    @FXML
    void buyMiner() {
        if (GameInfo.gems >= GameInfo.minerPrice && GameInfo.miner < 100) {
            GameInfo.miner++;
            minerLabel.setText(String.valueOf(GameInfo.miner));
            GameInfo.gems -= GameInfo.minerPrice;
            GameInfo.minerPrice *= 1.2;
            minerPriceLabel.setText("Price: " + GameInfo.minerPrice);
            GameInfo.gemsPerSecond += 3.8;
        }
    }

    @FXML
    void buyEngineer() {
        if (GameInfo.gems >= GameInfo.engineerPrice && GameInfo.engineer < 100) {
            GameInfo.engineer++;
            engineerLabel.setText(String.valueOf(GameInfo.engineer));
            GameInfo.gems -= GameInfo.engineerPrice;
            GameInfo.engineerPrice *= 1.2;
            engineerPriceLabel.setText("Price: " + GameInfo.engineerPrice);
            GameInfo.gemsPerSecond += 8.6;
        }
    }

    @FXML
    void buyAnalyst() {
        if (GameInfo.gems >= GameInfo.analystPrice && GameInfo.analyst < 100) {
            GameInfo.analyst++;
            analystLabel.setText(String.valueOf(GameInfo.analyst));
            GameInfo.gems -= GameInfo.analystPrice;
            GameInfo.analystPrice *= 1.2;
            analystPriceLabel.setText("Price: " + GameInfo.analystPrice);
            GameInfo.gemsPerSecond += 25.2;
        }
    }

    @FXML
    void buyRobot() {
        if (GameInfo.gems >= GameInfo.robotPrice && GameInfo.robot < 100) {
            GameInfo.robot++;
            robotLabel.setText(String.valueOf(GameInfo.robot));
            GameInfo.gems -= GameInfo.robotPrice;
            GameInfo.robotPrice *= 1.2;
            robotPriceLabel.setText("Price: " + GameInfo.robotPrice);
            GameInfo.gemsPerSecond += 63.2;
        }
    }

    @FXML
    void restart() {
        if(GameInfo.gems > 250000) {
            GameInfo.restarts++;
            GameInfo.unExperienced = 0;
            GameInfo.miner = 0;
            GameInfo.engineer = 0;
            GameInfo.analyst = 0;
            GameInfo.robot = 0;
            GameInfo.unExperiencedPrice = 200;
            GameInfo.minerPrice = 1000;
            GameInfo.engineerPrice = 5000;
            GameInfo.analystPrice = 20000;
            GameInfo.robotPrice = 50000;
            GameInfo.health = 20;
            GameInfo.gems = 0;
            GameInfo.gemsPerClick = 1;
            unExperiencedLabel.setText(String.valueOf(GameInfo.unExperienced));
            unExperiencedPriceLabel.setText("Price: " + GameInfo.unExperiencedPrice);
            minerLabel.setText(String.valueOf(GameInfo.miner));
            minerPriceLabel.setText("Price: " + GameInfo.minerPrice);
            engineerLabel.setText(String.valueOf(GameInfo.engineer));
            engineerPriceLabel.setText("Price: " + GameInfo.engineerPrice);
            analystLabel.setText(String.valueOf(GameInfo.analyst));
            analystPriceLabel.setText("Price: " + GameInfo.analystPrice);
            robotLabel.setText(String.valueOf(GameInfo.robot));
            robotPriceLabel.setText("Price: " + GameInfo.robotPrice);
            GameInfo.health = 20;
            hpLabel.setText(GameInfo.health + " / 20");
            progress = 1.0;
            hp.setProgress(progress);
            gemImage.setImage(new Image(randomGem()));
            GameInfo.recalculate();
        }
    }

    @FXML
    void cheat() {
        GameInfo.gems +=50000;
    }

    private void updateGems() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        gemsLabel.setText(df.format(GameInfo.gems));
        gemsPerSecondLabel.setText(df.format(GameInfo.gemsPerSecond));
    }

    public void initialize() {
        thread.start();
    }

    Thread thread = new Thread(() -> {
        while (true) {
            try {
                Platform.runLater(() -> {
                    updateGems();
                    GameInfo.gems += GameInfo.gemsPerSecond / 20;
                });
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    });
}
