package test;

import org.junit.jupiter.api.Test;
import sample.ColorOrNothing;
import sample.Tile;

import static org.junit.jupiter.api.Assertions.*;
import static sample.ColorOrNothing.*;
import static sample.GameBoard.*;

class Tests {
    private void clear() {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if ((i + j) % 2 == 1) setChecker(i, j, NOTHING, false, false,false);
            }
        }
    }

    private void setChecker(int x, int y, ColorOrNothing color, boolean queen, boolean mustAttack, boolean light) {
        board[x][y].setColor(color);
        board[x][y].setQueen(queen);
        board[x][y].setMustAttack(mustAttack);
        board[x][y].setLight(light);
    }

    //простая ситуация
    @Test
    public void test1() {
        clear();
        isWhiteTurn = true;
        setChecker(6, 1, WHITE, true, false,false);
        setChecker(3,4, WHITE,false,false,false);
        setChecker(2,3, BLACK,false,false,false);
        setChecker(1,2, BLACK, false,false,false);
        setChecker(2, 5, BLACK, false,false,false);
        gameProcess(3, 4);
        gameProcess(1, 2);
        assertEquals(board[3][4], new Tile(WHITE, false, false, true));
        assertEquals(board[1][6], new Tile(NOTHING, false, false, true));
        assertEquals(board[1][2], new Tile(BLACK, false, false, false));
        gameProcess(6,1);
        assertEquals(board[6][1], new Tile(WHITE, true, false, false));
        assertEquals(board[3][4], new Tile(WHITE, false, false, true));
        gameProcess(1, 6);
        assertEquals(board[1][2], new Tile(BLACK, false, false, false));
        assertEquals(board[3][4], new Tile(NOTHING, false, false, false));
        assertEquals(board[1][6], new Tile(WHITE, false, false, false));
        assertEquals(board[2][5], new Tile(NOTHING, false, false, false));
    }

    //ситуация, когда игрок побил один раз и должен продолжать рубить этой же шашкой
    //когда чёрная шашка стала дамкой, передаём ход белым, даже если могли продолжать рубить
    @Test
    public void test2() {
        clear();
        isWhiteTurn = false;
        setChecker(3,2, BLACK,false,false,false);
        setChecker(0,7, BLACK,false,false,false);
        setChecker(1,6, WHITE, false,false,false);
        setChecker(4, 3, WHITE, false,false,false);
        setChecker(6, 1, WHITE, false,false,false);
        setChecker(6, 3, WHITE, false,false,false);
        setChecker(6, 5, WHITE, false,false,false);
        gameProcess(3, 2);
        assertEquals(board[3][2], new Tile(BLACK, false, false, true));
        assertEquals(board[5][4], new Tile(NOTHING, false, false, true));
        assertEquals(board[5][0], new Tile(NOTHING, false, false, false));
        assertEquals(board[4][3], new Tile(WHITE, false, false, false));
        gameProcess(0, 7);
        assertEquals(board[3][2], new Tile(BLACK, false, false, false));
        assertEquals(board[5][4], new Tile(NOTHING, false, false, false));
        assertEquals(board[0][7], new Tile(BLACK, false, false, true));
        assertEquals(board[2][5], new Tile(NOTHING, false, false, true));
        gameProcess(3, 2);
        gameProcess(5, 4);
        assertEquals(board[3][2], new Tile(NOTHING, false, false, false));
        assertEquals(board[5][4], new Tile(BLACK, false, true, false));
        assertEquals(board[4][3], new Tile(NOTHING, false, false, false));
        gameProcess(0, 7);
        assertEquals(board[0][7], new Tile(BLACK, false, false, false));
        assertEquals(board[2][5], new Tile(NOTHING, false, false, false));
        gameProcess(5, 4);
        assertEquals(board[5][4], new Tile(BLACK, false, true, true));
        assertEquals(board[7][2], new Tile(NOTHING, false, false, true));
        gameProcess(7, 2);
        assertEquals(board[7][2], new Tile(BLACK, true, false, false));
        assertTrue(isWhiteTurn);
    }
}