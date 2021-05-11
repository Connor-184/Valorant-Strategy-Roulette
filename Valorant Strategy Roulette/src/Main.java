import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * An interactive GUI that allows the user to play Strategy Roulette in Valorant.
 *
 * Version 2.0
 *
 * There are currently no map-specific strategies for Breeze.
 *
 * @author Connor McNally
 */

public class Main extends Application {

    Stage window;

    final int height = 1000;
    final int width = 1600;
    int numMemes = 45;

    @Override
    public void start(Stage primaryStage) {

        // Sets root style
        String style = "-fx-background-image: url('Valorant/Background2.jpg');-fx-background-position: center center;" +
                "-fx-background-repeat: stretch;";

        // Creates the different scenes and sets the primary stage
        BorderPane root1 = new BorderPane();
        Scene scene1 = new Scene(root1, width, height);
        root1.setStyle("-fx-background-image: url('Valorant/Background.jpg');-fx-background-position: center center;" +
                "-fx-background-repeat: stretch;");

        BorderPane[] borderPanes = new BorderPane[6];
        Scene[] scenes = new Scene[6];
        for (int i = 0; i < borderPanes.length; i++) {
            borderPanes[i] = new BorderPane();
            scenes[i] = new Scene(borderPanes[i], width, height);
            borderPanes[i].setStyle(style);
        }


        // Root 1 TOP
        Label label1 = new Label("");
        label1.setStyle("-fx-font-size: 26; -fx-font-weight: BOLD;-fx-text-fill: White");
        VBox vbox1 = new VBox(label1);
        vbox1.setPadding(new Insets(100, 0, 10, 0));
        root1.setTop(vbox1);
        vbox1.setAlignment(Pos.TOP_CENTER);

        // Root 1 LEFT
        Button button1 = new Button();
        button1.setStyle("-fx-background-image: url('Valorant/Split.jpg');-fx-background-position: center center;" +
                "-fx-background-repeat: stretch;-fx-background-insets: 0, 0, 0, 0;");
        Button button2 = new Button();
        button2.setStyle("-fx-background-image: url('Valorant/Bind.jpg');-fx-background-position: center center;" +
                "-fx-background-repeat: stretch;-fx-background-insets: 0, 0, 0, 0;");
        button1.setMinSize(475, 210);
        button2.setMinSize(475, 210);
        Button button5 = new Button();
        button5.setStyle("-fx-background-image: url('Valorant/Icebox.jpg');-fx-background-position: center center;" +
                "-fx-background-repeat: stretch;-fx-background-insets: 0, 0, 0, 0;");
        button5.setMinSize(475, 210);
        VBox vbox2 = new VBox(50);
        vbox2.setPadding(new Insets(50, 0, 0, 30));
        vbox2.getChildren().addAll(button1, button2, button5);
        vbox2.setAlignment(Pos.TOP_LEFT);
        root1.setLeft(vbox2);


        // Root 1 RIGHT
        Button button3 = new Button();
        button3.setStyle("-fx-background-image: url('Valorant/Ascent.jpg');-fx-background-position: center center;" +
                "-fx-background-repeat: stretch;-fx-background-insets: 0, 0, 0, 0;");
        Button button4 = new Button();
        button4.setStyle("-fx-background-image: url('Valorant/Haven.jpg');-fx-background-position: center center;" +
                "-fx-background-repeat: stretch;-fx-background-insets: 0, 0, 0, 0;");
        button3.setMinSize(475, 210);
        button4.setMinSize(475, 210);
        Button button17 = new Button();
        button17.setMinSize(475, 210);
        button17.setStyle("-fx-background-image: url('Valorant/Breeze.jpg');-fx-background-position: center center;" +
                "-fx-background-repeat: stretch;-fx-background-insets: 0, 0, 0, 0;");
        VBox vbox3 = new VBox(50);
        vbox3.setPadding(new Insets(50, 30, 0, 0));
        vbox3.getChildren().addAll(button3, button4, button17);
        vbox3.setAlignment(Pos.TOP_RIGHT);
        root1.setRight(vbox3);

        // Github link
        ImageView github = new ImageView("Valorant/GitHub.png");
        Hyperlink hyperlink = new Hyperlink();
        hyperlink.setGraphic(github);
        hyperlink.setAlignment(Pos.CENTER);
        root1.setBottom(hyperlink);


        // "Generate Strategy" buttons
        Button[] generateButtons = new Button[scenes.length];
        for (int i = 0; i < generateButtons.length; i++) {
            generateButtons[i] = new Button("Generate Strategy");
            generateButtons[i].setMinSize(250, 75);
            generateButtons[i].setStyle("-fx-font-size: 26");
            HBox hbox = new HBox(generateButtons[i]);
            hbox.setPadding(new Insets(0, 0, 100, 0));
            hbox.setAlignment(Pos.BOTTOM_CENTER);
            borderPanes[i].setBottom(hbox);
        }

        // Strategy text
        Label[] label = new Label[scenes.length];
        for (int i = 0; i < label.length; i++) {
            label[i] = new Label("");
            label[i].setWrapText(true);
            label[i].setMaxWidth(500);
            label[i].setStyle("-fx-font-size: 30; -fx-font-weight: BOLD;-fx-text-fill: White");
            VBox labelBox = new VBox(label[i]);
            labelBox.setPadding(new Insets(0, 0, 0, 50));
            labelBox.setAlignment(Pos.CENTER_LEFT);
            borderPanes[i].setLeft(labelBox);
        }

        // Main Menu buttons
        Button[] mainMenuButtons = new Button[scenes.length];
        for (int i = 0; i < scenes.length; i++) {
            mainMenuButtons[i] = new Button("Main Menu");
            mainMenuButtons[i].setStyle("-fx-font-size: 24; -fx-font-weight: BOLD;-fx-text-fill: Black 0");
            HBox hbox = new HBox(mainMenuButtons[i]);
            hbox.setPadding(new Insets(20, 0, 0, 20));
            hbox.setAlignment(Pos.TOP_LEFT);
            borderPanes[i].setTop(hbox);
        }

        // Event handler for hyperlink
        hyperlink.setOnAction(event -> {
            if(Desktop.isDesktopSupported())
            {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/Connor-184/Valorant-Strategy-Roulette"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });


        // Button handlers for scene changes
        button1.setOnAction(event -> window.setScene(scenes[0]));
        button2.setOnAction(event -> window.setScene(scenes[1]));
        button3.setOnAction(event -> window.setScene(scenes[2]));
        button4.setOnAction(event -> window.setScene(scenes[3]));
        button5.setOnAction(event -> window.setScene(scenes[4]));
        button17.setOnAction(event -> window.setScene(scenes[5]));
        for (Button mainMenuButton : mainMenuButtons) {
            mainMenuButton.setOnAction(event -> window.setScene(scene1));
        }


        // Button handlers for generating strats/memes
        generateButtons[0].setOnAction(event -> {
            Image meme = getMeme(numMemes);
            ImageView imageView = new ImageView(meme);
            HBox hbox = new HBox(imageView);
            hbox.setAlignment(Pos.CENTER_RIGHT);
            hbox.setPadding(new Insets(0, 50, 0, 0));
            borderPanes[0].setRight(hbox);
            if (isMS()) {
                try {
                    label[0].setText(getSplitStrat());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    label[0].setText(getGenStrat());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        });
        generateButtons[1].setOnAction(event -> {
            Image meme = getMeme(numMemes);
            ImageView imageView = new ImageView(meme);
            HBox hbox = new HBox(imageView);
            hbox.setAlignment(Pos.CENTER_RIGHT);
            hbox.setPadding(new Insets(0, 50, 0, 0));
            borderPanes[1].setRight(hbox);
            if (isMS()) {
                try {
                    label[1].setText(getBindStrat());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    label[1].setText(getGenStrat());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        generateButtons[2].setOnAction(event -> {
            Image meme = getMeme(numMemes);
            ImageView imageView = new ImageView(meme);
            HBox hbox = new HBox(imageView);
            hbox.setAlignment(Pos.CENTER_RIGHT);
            hbox.setPadding(new Insets(0, 50, 0, 0));
            borderPanes[2].setRight(hbox);
            if (isMS()) {
                try {
                    label[2].setText(getAscentStrat());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    label[2].setText(getGenStrat());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        generateButtons[3].setOnAction(event -> {
            Image meme = getMeme(numMemes);
            ImageView imageView = new ImageView(meme);
            HBox hbox = new HBox(imageView);
            hbox.setAlignment(Pos.CENTER_RIGHT);
            hbox.setPadding(new Insets(0, 50, 0, 0));
            borderPanes[3].setRight(hbox);
            if (isMS()) {
                try {
                    label[3].setText(getHavenStrat());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    label[3].setText(getGenStrat());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        });
        generateButtons[4].setOnAction(event -> {
            Image meme = getMeme(numMemes);
            ImageView imageView = new ImageView(meme);
            HBox hbox = new HBox(imageView);
            hbox.setAlignment(Pos.CENTER_RIGHT);
            hbox.setPadding(new Insets(0, 50, 0, 0));
            borderPanes[4].setRight(hbox);
            if (isMS()) {
                try {
                    label[4].setText(getIceboxStrat());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    label[4].setText(getGenStrat());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        generateButtons[5].setOnAction(event -> {
            Image meme = getMeme(numMemes);
            ImageView imageView = new ImageView(meme);
            HBox hbox = new HBox(imageView);
            hbox.setAlignment(Pos.CENTER_RIGHT);
            hbox.setPadding(new Insets(0, 50, 0, 0));
            borderPanes[5].setRight(hbox);
            try {
                label[5].setText(getGenStrat());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        });

        window = primaryStage;
        window.setResizable(false);
        window.setScene(scene1);
        window.setTitle("Valorant Strategy Roulette v2");
        window.show();

    }

    /**
     * Returns a random general strategy.
     */
    private static String getGenStrat() throws FileNotFoundException {
        int counter = 0;
        File file = new File("GenStrats.txt");
        Scanner scan = new Scanner(file);
        Scanner scan2 = new Scanner(file);

        while (scan2.hasNextLine()) {
            scan2.nextLine();
            counter++;
        }

        String[] strategies = new String[counter - 1];
        for (int i = 0; i < strategies.length; i++) {
               strategies[i] = scan.nextLine();
           }

        int num = ThreadLocalRandom.current().nextInt(0, strategies.length + 1);

        if (num < strategies.length) {
            return strategies[num];
        } else {
            return strategies[strategies.length - 1];
        }
    }

    /**
     * Returns a random map-specific strategy for Split.
     */
    private static String getSplitStrat() throws FileNotFoundException {

        int counter = 0;
        File file = new File("Split Strats.txt");
        Scanner scan = new Scanner(file);
        Scanner scan2 = new Scanner(file);

        while (scan2.hasNextLine()) {
            scan2.nextLine();
            counter++;
        }

        String[] splitStrats = new String[counter - 1];
        for (int i = 0; i < splitStrats.length; i++) {
            splitStrats[i] = scan.nextLine();
        }

        int num = ThreadLocalRandom.current().nextInt(0, splitStrats.length + 1);
        if (num < splitStrats.length) {
            return splitStrats[num];
        } else {
            return splitStrats[splitStrats.length - 1];
        }
    }

    /**
     * Returns a random map-specific strategy for Ascent.
     */
    private static String getAscentStrat() throws FileNotFoundException {

        int counter = 0;
        File file = new File("Ascent Strats.txt");
        Scanner scan = new Scanner(file);
        Scanner scan2 = new Scanner(file);

        while (scan2.hasNextLine()) {
            scan2.nextLine();
            counter++;
        }

        String[] ascentStrats = new String[counter - 1];
        for (int i = 0; i < ascentStrats.length; i++) {
            ascentStrats[i] = scan.nextLine();
        }

        int num = ThreadLocalRandom.current().nextInt(0, ascentStrats.length + 1);
        if (num < ascentStrats.length) {
            return ascentStrats[num];
        } else {
            return ascentStrats[ascentStrats.length - 1];
        }
    }
    /**
     * Returns a random map-specific strategy for Bind.
     */
    private static String getBindStrat() throws FileNotFoundException {
        int counter = 0;
        File file = new File("Bind Strats.txt");
        Scanner scan = new Scanner(file);
        Scanner scan2 = new Scanner(file);

        while (scan2.hasNextLine()) {
            scan2.nextLine();
            counter++;
        }

        String[] bindStrats = new String[counter - 1];
        for (int i = 0; i < bindStrats.length; i++) {
            bindStrats[i] = scan.nextLine();
        }

        int num = ThreadLocalRandom.current().nextInt(0, bindStrats.length + 1);
        if (num < bindStrats.length) {
            return bindStrats[num];
        } else {
            return bindStrats[bindStrats.length - 1];
        }
    }
    /**
     * Returns a random map-specific strategy for Haven
     */
    private static String getHavenStrat() throws FileNotFoundException {
        int counter = 0;
        File file = new File("Haven Strats.txt");
        Scanner scan = new Scanner(file);
        Scanner scan2 = new Scanner(file);

        while (scan2.hasNextLine()) {
            scan2.nextLine();
            counter++;
        }

        String[] havenStrats = new String[counter - 1];
        for (int i = 0; i < havenStrats.length; i++) {
            havenStrats[i] = scan.nextLine();
        }

        int num = ThreadLocalRandom.current().nextInt(0, havenStrats.length + 1);
        if (num < havenStrats.length) {
            return havenStrats[num];
        } else {
            return havenStrats[havenStrats.length - 1];
        }
    }
    /**
     * Returns a random map-specific strategy for Icebox
     */
    private static String getIceboxStrat() throws FileNotFoundException {

        int counter = 0;
        File file = new File("Icebox Strats.txt");
        Scanner scan = new Scanner(file);
        Scanner scan2 = new Scanner(file);

        while (scan2.hasNextLine()) {
            scan2.nextLine();
            counter++;
        }

        String[] iceboxStrats = new String[counter - 1];
        for (int i = 0; i < iceboxStrats.length; i++) {
            iceboxStrats[i] = scan.nextLine();
        }


        int num = ThreadLocalRandom.current().nextInt(0, iceboxStrats.length + 1);
        if (num < iceboxStrats.length) {
            return iceboxStrats[num];
        } else {
            return iceboxStrats[iceboxStrats.length - 1];
        }
    }

    /**
     * Returns a random Valorant meme.
     */
    private static Image getMeme(int numMemes) {
        final Image[] memes = new Image[numMemes];
        for (int i = 1; i < numMemes; i++) {
            String imageGet = "Memes/Meme " + i + ".jpg";
            memes[i] = new Image(imageGet);
        }
        int num = ThreadLocalRandom.current().nextInt(1, numMemes);
        return memes[num];

    }

    public static boolean isMS() {
        int numCheck = ThreadLocalRandom.current().nextInt(1, 6);
        return numCheck == 5;
    }

    public static void main(String[] args) {
        launch(args);
    }

}