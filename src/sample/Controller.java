package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static sample.GameBoard.gameProcess;


public class Controller {
    @FXML
    GridPane gridPane;

    Map<Integer, Image> images = new HashMap<>();

    @FXML
    void cell1(MouseEvent event) {gameProcess(0, 1); updateUI();}
    @FXML
    void cell2(MouseEvent event) {gameProcess(0, 3); updateUI();}
    @FXML
    void cell3(MouseEvent event) {gameProcess(0, 5); updateUI();}
    @FXML
    void cell4(MouseEvent event) {gameProcess(0, 7); updateUI();}
    @FXML
    void cell5(MouseEvent event) {gameProcess(1, 0); updateUI();}
    @FXML
    void cell6(MouseEvent event) {gameProcess(1, 2); updateUI();}
    @FXML
    void cell7(MouseEvent event) {gameProcess(1, 4); updateUI();}
    @FXML
    void cell8(MouseEvent event) {gameProcess(1, 6); updateUI();}
    @FXML
    void cell9(MouseEvent event) {gameProcess(2, 1); updateUI();}
    @FXML
    void cell10(MouseEvent event) {gameProcess(2, 3); updateUI();}
    @FXML
    void cell11(MouseEvent event) {gameProcess(2, 5); updateUI();}
    @FXML
    void cell12(MouseEvent event) {gameProcess(2, 7); updateUI();}
    @FXML
    void cell13(MouseEvent event) {gameProcess(3, 0); updateUI();}
    @FXML
    void cell14(MouseEvent event) {gameProcess(3, 2); updateUI();}
    @FXML
    void cell15(MouseEvent event) {gameProcess(3, 4); updateUI();}
    @FXML
    void cell16(MouseEvent event) {gameProcess(3, 6); updateUI();}
    @FXML
    void cell17(MouseEvent event) {gameProcess(4, 1); updateUI();}
    @FXML
    void cell18(MouseEvent event) {gameProcess(4, 3); updateUI();}
    @FXML
    void cell19(MouseEvent event) {gameProcess(4, 5); updateUI();}
    @FXML
    void cell20(MouseEvent event) {gameProcess(4, 7); updateUI();}
    @FXML
    void cell21(MouseEvent event) {gameProcess(5, 0); updateUI();}
    @FXML
    void cell22(MouseEvent event) {gameProcess(5, 2); updateUI();}
    @FXML
    void cell23(MouseEvent event) {gameProcess(5, 4); updateUI();}
    @FXML
    void cell24(MouseEvent event) {gameProcess(5, 6); updateUI();}
    @FXML
    void cell25(MouseEvent event) {gameProcess(6, 1); updateUI();}
    @FXML
    void cell26(MouseEvent event) {gameProcess(6, 3); updateUI();}
    @FXML
    void cell27(MouseEvent event) {gameProcess(6, 5); updateUI();}
    @FXML
    void cell28(MouseEvent event) {gameProcess(6, 7); updateUI();}
    @FXML
    void cell29(MouseEvent event) {gameProcess(7, 0); updateUI();}
    @FXML
    void cell30(MouseEvent event) {gameProcess(7, 2); updateUI();}
    @FXML
    void cell31(MouseEvent event) {gameProcess(7, 4); updateUI();}
    @FXML
    void cell32(MouseEvent event) {gameProcess(7, 6); updateUI();}






    public void updateUI() {
        for(Node n : gridPane.getChildren()){
            ImageView img = (ImageView) n;
            int x = GridPane.getRowIndex(n);
            int y = GridPane.getColumnIndex(n);
            int key = GameBoard.board[GridPane.getRowIndex(n)][GridPane.getColumnIndex(n)].getKey();
            img.setImage(images.get(GameBoard.board[GridPane.getRowIndex(n)][GridPane.getColumnIndex(n)].getKey()));
        }
    }
    public Controller() {
        try {
            images.put(0, new Image(new FileInputStream("src\\images\\field.png")));
            images.put(1, new Image(new FileInputStream("src\\images\\whiteChecker.png")));
            images.put(2, new Image(new FileInputStream("src\\images\\whiteCheckerYellow.png")));
            images.put(3, new Image(new FileInputStream("src\\images\\blackChecker.png")));
            images.put(4, new Image(new FileInputStream("src\\images\\blackCheckerYellow.png")));
            images.put(5, new Image(new FileInputStream("src\\images\\whiteCheckerQ.png")));
            images.put(7, new Image(new FileInputStream("src\\images\\whiteCheckerQYellow.png")));
            images.put(6, new Image(new FileInputStream("src\\images\\blackCheckerQ.png")));
            images.put(8, new Image(new FileInputStream("src\\images\\blackCheckerQYellow.png")));
            images.put(9, new Image(new FileInputStream("src\\images\\yellow.png")));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}


