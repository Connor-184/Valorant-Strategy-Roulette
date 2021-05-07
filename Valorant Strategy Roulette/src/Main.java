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
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * An interactive GUI that allows the user to play Strategy Roulette in Valorant.
 *
 * Version 2.0
 *
 * Known bugs:
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
                label[0].setText(getSplitStrat());
            } else {
                label[0].setText(getGenStrat());
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
                label[1].setText(getBindStrat());
            } else {
                label[1].setText(getGenStrat());
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
                label[2].setText(getAscentStrat());
            } else {
                label[2].setText(getGenStrat());
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
                label[3].setText(getHavenStrat());
            } else {
                label[3].setText(getGenStrat());
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
                label[4].setText(getIceboxStrat());
            } else {
                label[4].setText(getGenStrat());
            }
        });
        generateButtons[5].setOnAction(event -> {
            Image meme = getMeme(numMemes);
            ImageView imageView = new ImageView(meme);
            HBox hbox = new HBox(imageView);
            hbox.setAlignment(Pos.CENTER_RIGHT);
            hbox.setPadding(new Insets(0, 50, 0, 0));
            borderPanes[5].setRight(hbox);
            label[5].setText(getGenStrat());

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
    private static String getGenStrat() {
        final String[] strategies = {
                "Each teammate must buy every ability that they possibly can. Rush towards one of the sites, and use all of your abilities as fast as you can on the site. Then quickly retreat and plant the spike at a different site.",
                "Play the round normally, however, you cannot use any normal callouts. Preferably use callouts that don't exist on this map, callouts from other games/maps, or just very broadly describe what you are trying to callout.",
                "Elect a teammate to be the leader, your whole team must move in a line everywhere that they travel following the leader. No one can stray away from the line.",
                "Whoever is at the bottom of the scoreboard for your team is the only person allowed to use their mic this round. They must attempt to make callouts for all of their teammates.",
                "Everytime you kill an enemy, you must pick up their gun and use it for your next kill. It doesn't matter what gun they had or how hard it will be to pick up, you cannot get a kill with the exact same gun more than once.",
                "Everytime a teammate dies, they must constantly blow into their mic simulating a wind sound. They cannot stop until the round is over.",
                "Everyone on your team must set their game's master volume to 0.",
                "Every player that is alive must narrate every action that they make. Do this as a shoutcaster would while spectating a professional match. Everyone that is alive must participate but stop if they die.",
                "Everytime you encounter an enemy, you must do a 360 before you are allowed to shoot them.",
                "You must jump every time you turn or peak a corner.",
                "Everyone on your team must play the entire round crouched, you can not stand up at any time for any reason.",
                "Your team can only buy suppressed weapons and no one on the team is allowed to speak for the entire round.",
                "If there is an enemy on the other team with the same agent as you, you must ignore all other enemies until you have killed your doppelgänger.",
                "Elect one player on your team to give a motivational speech at the start of the round. No one is allowed to leave spawn until the speech is finished.",
                "You must hold the W key for the entire round and you are not allowed to walk.",
                "No one on your team is allowed to use any abilities for this round. You might as well just be playing CS:GO.",
                "Elect one teammate to be the 'President' who must carry the spike. The President is not allowed to have any guns or abilities, just armor. Your team must surround the president and protect them for the entire round.",
                "The first player to die must narrate the actions of all remaining teammates.",
                "Players who get a kill must switch to their knife for the remainder of the round.",
                "Top frag may only use a pistol. Bottom frag may only use a sniper rifle. No two players may carry a primary weapon from the same category.",
                "After every kill, players must stop to clap and cheer for 5 seconds in honor of the fine kill.",
                "Choose one player to be the only person allowed to buy abilities for this round.",
                "Everyone except for one player must use only the Sheriff for the round. Choose wisely.",
                "Choose a player, nobody is allowed to shoot until that player gets 2 kills",
                "Choose a player to narrate their every move until killed or the round is finished.",
                "The top-fragger must swap loadouts with the bottom-fragger",
                "Choose a player, they must buy and use all of their abilities (including ults if available) before anyone on the team can shoot",
                "The player with the least amount of kills must sing the alphabet twice. Nobody is allowed to leave spawn until they finish.",
                "The first to die will give the rest of the team instructions that must be followed for the rest of the round.",
                "The top fragger must hold/push a site on their own while everyone else holds/pushes the opposite site.",
                "Choose a player, they are the only person allowed to buy armor/abilities for this round.",
                "Everyone on the team must play the round with their face 6 inches from their monitors",
                "You can only use one hand at a time, you cannot move the mouse while pressing any keys and vice versa.",
                "Nobody on the team is allowed to stop sprinting. No walking or crouching",
                "After each kill you must praise a different country for that kill. Example: \"This was for MEXICO!!\"",
                "Choose one player. Players may only move when this player is singing",
                "Only players who are dead may use comms, nobody else is allowed to speak",
                "Everyone must set their sensitivity to max"

        };

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
    private static String getSplitStrat() {
        final String[] splitStrats = { "Nobody is allowed to use any ropes",
                "Your team must hold B site strictly from Heaven",
                ""
        };
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
    private static String getAscentStrat() {
        final String[] ascentStrats = {"The bomb may not be planted or defused until both doors are either closed or destroyed",
                "Nobody is allowed to use garage or A-Long to get to each site",
                "Once you physically step foot onto a site, you are not allowed to leave",
                "You must defend the market from any potential robbers, keep any enemies outside of market",
                "You must defend your brand new Lamborghini from the delinquents on the enemy team. Keep all enemies out of garage for the whole round."

        };
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
    private static String getBindStrat() {
        final String[] bindStrats = { "Everybody on the team must teleport at least once before anyone is allowed to shoot",
                "There must be one person inside of a teleporter at all times unless you are the last one alive",
                "Nobody on your team is allowed to use any teleporters this round",
                "Everytime you kill someone, you must use a teleporter before you can get another kill",
                "If you see an enemy use the teleporter, you must prioritize killing that enemy before killing anyone else"


        };
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
    private static String getHavenStrat() {
        final String[] havenStrats = { "Everyone on your team must sit in B site. Nobody is allowed to leave until either the spike is planted or there is 45 seconds left in the round",
                "Everyone must push C long with either a Marshall or and Operator.",
                "While there are 3 or more players alive, there must be at least one player on every site at all times.",
                "Convince the enemy team to have a knife fight at B site",
                "Nobody is allowed to set foot on B site. If you are defending and the bomb is planted at B you may enter B site only after every enemy is killed"

        };
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
    private static String getIceboxStrat() {
        final String[] iceboxStrats = { "Everytime you shoot or use an ability you must say \" Fuck icebox, all my homies hate icebox!\"",
                "When on A site, you can only kill enemies while you are attached to a rope",
                "You may only enter one single building. For example, If you enter A building cannot enter any other building like tube or kitchen",
                "You must travel though mid before entering any site. This includes tubes.",
                "Fuck Icebox just throw the round"

        };
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