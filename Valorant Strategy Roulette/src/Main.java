import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
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

        // Creates the different scenes and sets the primary stage
        BorderPane root1 = new BorderPane();
        Scene scene1 = new Scene(root1, width, height);

        BorderPane[] borderPanes = new BorderPane[6];
        Scene[] scenes = new Scene[6];
        for (int i = 0; i < borderPanes.length; i++) {
            borderPanes[i] = new BorderPane();
            scenes[i] = new Scene(borderPanes[i], width, height);
            borderPanes[i].setId("borderPanes" + i);
        }

        // Root 1 TOP
        Label label1 = new Label("");
        label1.setId("label1");
        VBox vbox1 = new VBox(label1);
        vbox1.setPadding(new Insets(100, 0, 10, 0));
        root1.setTop(vbox1);
        vbox1.setAlignment(Pos.TOP_CENTER);

        // Root 1 LEFT
        Button button1 = new Button();
        Button button2 = new Button();
        button1.setMinSize(475, 210);
        button2.setMinSize(475, 210);
        Button button5 = new Button();
        button5.setMinSize(475, 210);
        VBox vbox2 = new VBox(50);
        vbox2.setPadding(new Insets(50, 0, 0, 30));
        vbox2.getChildren().addAll(button1, button2, button5);
        vbox2.setAlignment(Pos.TOP_LEFT);
        root1.setLeft(vbox2);

        // Root 1 RIGHT
        Button button3 = new Button();
        Button button4 = new Button();
        button3.setMinSize(475, 210);
        button4.setMinSize(475, 210);
        Button button17 = new Button();
        button17.setMinSize(475, 210);
        VBox vbox3 = new VBox(50);
        vbox3.setPadding(new Insets(50, 30, 0, 0));
        vbox3.getChildren().addAll(button3, button4, button17);
        vbox3.setAlignment(Pos.TOP_RIGHT);
        root1.setRight(vbox3);

        DropShadow dropShadow = new DropShadow(25, Color.RED);

        // Adds drop shadows to buttons on hover
        button1.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> button1.setEffect(dropShadow));
        button1.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> button1.setEffect(null));
        button2.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> button2.setEffect(dropShadow));
        button2.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> button2.setEffect(null));
        button3.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> button3.setEffect(dropShadow));
        button3.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> button3.setEffect(null));
        button4.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> button4.setEffect(dropShadow));
        button4.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> button4.setEffect(null));
        button5.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> button5.setEffect(dropShadow));
        button5.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> button5.setEffect(null));
        button17.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> button17.setEffect(dropShadow));
        button17.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> button17.setEffect(null));

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
            VBox labelBox = new VBox(label[i]);
            labelBox.setPadding(new Insets(0, 0, 0, 50));
            labelBox.setAlignment(Pos.CENTER_LEFT);
            borderPanes[i].setLeft(labelBox);
        }

        // Main Menu buttons
        Button[] mainMenuButtons = new Button[scenes.length];
        for (int i = 0; i < scenes.length; i++) {
            mainMenuButtons[i] = new Button("Main Menu");
            HBox hbox = new HBox(mainMenuButtons[i]);
            hbox.setPadding(new Insets(20, 0, 0, 20));
            hbox.setAlignment(Pos.TOP_LEFT);
            borderPanes[i].setTop(hbox);
        }

        // Sets Ids and styles for each component
        root1.setId("root1");
        scene1.getStylesheets().add("Style.css");
        for (Scene scene : scenes) {
            scene.getStylesheets().add("Style.css");
        }
        label1.setId("label1");
        button1.setId("button1");
        button2.setId("button2");
        button3.setId("button3");
        button4.setId("button4");
        button5.setId("button5");
        button17.setId("button17");
        for (int i = 0; i < label.length; i++) {
            label[i].setId("stratText" + i);
        }
        for (int i = 0; i < generateButtons.length; i++) {
            generateButtons[i].setId("generateButtons" + i);
        }
        for (int i = 0; i < mainMenuButtons.length; i++) {
            mainMenuButtons[i].setId("mainMenuButtons" + i);
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    label[0].setText(getGenStrat());
                } catch (URISyntaxException | IOException e) {
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    label[1].setText(getGenStrat());
                } catch (URISyntaxException | IOException e) {
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    label[2].setText(getGenStrat());
                } catch (URISyntaxException | IOException e) {
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    label[3].setText(getGenStrat());
                } catch (URISyntaxException | IOException e) {
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    label[4].setText(getGenStrat());
                } catch (URISyntaxException | IOException e) {
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
            } catch (URISyntaxException | IOException e) {
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
    private static String getGenStrat() throws IOException, URISyntaxException {
        int counter = 0;
        BufferedReader txtReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Main.class.getResourceAsStream("/GenStrats.txt"))));
        BufferedReader txtReader2 = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Main.class.getResourceAsStream("/GenStrats.txt"))));

        while (txtReader2.readLine() != null) counter++;

        String[] strategies = new String[counter];
        for (int i = 0; i < counter; i++) {
            strategies[i] = txtReader.readLine();
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
    private static String getSplitStrat() throws IOException {

        int counter = 0;
        BufferedReader txtReader = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/Split Strats.txt")));
        BufferedReader txtReader2 = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/Split Strats.txt")));

        while (txtReader2.readLine() != null) counter++;

        String[] splitStrats = new String[counter];
        for (int i = 0; i < counter; i++) {
            splitStrats[i] = txtReader.readLine();
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
    private static String getAscentStrat() throws IOException {

        int counter = 0;
        BufferedReader txtReader = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/Ascent Strats.txt")));
        BufferedReader txtReader2 = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/Ascent Strats.txt")));

        while (txtReader2.readLine() != null) counter++;

        String[] ascentStrats = new String[counter];
        for (int i = 0; i < counter; i++) {
            ascentStrats[i] = txtReader.readLine();
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
    private static String getBindStrat() throws IOException {
        int counter = 0;
        BufferedReader txtReader = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/Bind Strats.txt")));
        BufferedReader txtReader2 = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/Bind Strats.txt")));

        while (txtReader2.readLine() != null) counter++;

        String[] bindStrats = new String[counter];
        for (int i = 0; i < counter; i++) {
            bindStrats[i] = txtReader.readLine();
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
    private static String getHavenStrat() throws IOException {
        int counter = 0;
        BufferedReader txtReader = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/Haven Strats.txt")));
        BufferedReader txtReader2 = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/Haven Strats.txt")));

        while (txtReader2.readLine() != null) counter++;

        String[] havenStrats = new String[counter];
        for (int i = 0; i < counter; i++) {
            havenStrats[i] = txtReader.readLine();
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
    private static String getIceboxStrat() throws IOException {

        int counter = 0;
        BufferedReader txtReader = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/Icebox Strats.txt")));
        BufferedReader txtReader2 = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/Icebox Strats.txt")));

        while (txtReader2.readLine() != null) counter++;

        String[] iceboxStrats = new String[counter];
        for (int i = 0; i < counter; i++) {
            iceboxStrats[i] = txtReader.readLine();
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

    /**
     * Returns a boolean with a 1 in 5 chance of being true.
     */
    public static boolean isMS() {
        int numCheck = ThreadLocalRandom.current().nextInt(1, 6);
        return numCheck == 5;
    }

    public static void main(String[] args) {
        launch(args);
    }

}