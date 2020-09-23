package sample;
import static sample.ColorOrNothing.WHITE;
import static sample.ColorOrNothing.BLACK;
import static sample.ColorOrNothing.NOTHING;

public class GameBoard {
    static Tile[][] board = new Tile[8][8];
    private static boolean isWhiteTurn = true;
    private static boolean clickedOnChecker = false;
    private static int checkerX;
    private static int checkerY;

    static {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 7; j++) {
                if ((i + j) % 2 == 1) board[i][j] = new Tile(BLACK, false);
            }
        }
        for (int i = 3; i <= 4; i++) {
            for (int j = 0; j <= 7; j++) {
                board[i][j] = new Tile(NOTHING, false);
            }
        }
        for (int i = 5; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if ((i + j) % 2 == 1) board[i][j] = new Tile(WHITE, false);
            }
        }
    }

    public static void gameProcess(int x, int y) {
        ColorOrNothing color = board[x][y].getColor();
        if (isAttack(color)) {
            cleanLight();
            if (color == WHITE && isWhiteTurn && canAttackWhite(x, y, WHITE)) {
                checkerX = x;
                checkerY = y;
                board[x][y].setLight(true);
                if (canMove(x - 2, y - 2)) board[x - 2][y - 2].setLight(true);
                if (canMove(x - 2, y + 2)) board[x - 2][y + 2].setLight(true);
                //if (isAttack(WHITE) && board[x][y].getAttack())
            }
        } else {
            if (color == WHITE && isWhiteTurn) {
                checkerX = x;
                checkerY = y;
                cleanLight();
                board[x][y].setLight(true);
                if (canMove(x - 1, y - 1)) board[x - 1][y - 1].setLight(true);
                if (canMove(x - 1, y + 1)) board[x - 1][y + 1].setLight(true);
                //if (isAttack(WHITE) && board[x][y].getAttack())
            }

            if (color == BLACK && !isWhiteTurn) {
                checkerX = x;
                checkerY = y;
                cleanLight();
                board[x][y].setLight(true);
                if (canMove(x + 1, y + 1)) board[x + 1][y + 1].setLight(true);
                if (canMove(x + 1, y - 1)) board[x + 1][y - 1].setLight(true);
                //if (isAttack(WHITE) && board[x][y].getAttack())
            }

            if (color == NOTHING && board[x][y].getLight()) {
                if (isWhiteTurn) {
                    board[x][y].setColor(WHITE);
                    board[checkerX][checkerY].setColor(NOTHING);
                    cleanLight();
                    isWhiteTurn = false;
                } else {
                    board[x][y].setColor(BLACK);
                    board[checkerX][checkerY].setColor(NOTHING);
                    cleanLight();
                    isWhiteTurn = true;
                }
            }
        }
    }

    private static void cleanLight() {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if ((i + j) % 2 == 1) board[i][j].setLight(false);
            }
        }
    }

    private static boolean canMove(int x, int y) {
        if (x <= 7 && x >= 0 && y <= 7 && y >= 0) {
            return board[x][y].getColor() == NOTHING;
        }
        return false;
    }

    private static boolean canAttackWhite(int x, int y, ColorOrNothing color) {
        boolean result = false;
        if (canMove(x - 2, y - 2) && board[x - 1][y - 1].getColor() != color && board[x - 1][y - 1].getColor() != NOTHING) {
            board[x - 2][y - 2].setLight(true);
            result = true;
        }
        if (canMove(x - 2, y + 2) && board[x - 1][y + 1].getColor() != color && board[x - 1][y + 1].getColor() != NOTHING) {
            board[x - 2][y + 2].setLight(true);
            result = true;
        }
        return result;
    }
    //пока что только проверяет, нужно ли бить
    private static boolean isAttack(ColorOrNothing color) {
        if (color == NOTHING) return false;
        boolean result = false;
        int j = 0;
        for (int i = 1; i <= 5; i = i + 2) {
            if (diagonalRight(i, j, color)) return true;
        }
        int i = 0;
        for (j = 1; j <= 5; j = j + 2) {
            if (diagonalRight(i, j, color)) return true;
        }
        j = 7;
        for (i = 0; i <= 5; i = i + 2) {
            if (diagonalLeft(i, j, color)) return true;
        }
        i = 0;
        for (j = 5; j >= 2; j = j - 2) {
            if (diagonalLeft(i, j, color)) return true;
        }
        return false;
    }

    //вспомогательный метод для проверки диагонали слева направо
    private static boolean diagonalRight(int i, int j, ColorOrNothing color) {
        ColorOrNothing one;
        ColorOrNothing two;
        ColorOrNothing three;
        int y = j;
        for (int x = i; x + 2 <= 7 && y + 2 <= 7; x++, y++) {
            one = board[x][y].getColor();
            two = board[x + 1][y + 1].getColor();
            three = board[x + 2][y + 2].getColor();
            if (one == color && two != color && two != NOTHING && three == NOTHING) return true;
            if (one == NOTHING && two != color && two != NOTHING && three == color) return true;
        }
        return false;
    }

    //вспомогательный метод для проверки диагоналей справа налево
    private static boolean diagonalLeft(int i, int j, ColorOrNothing color) {
        ColorOrNothing one;
        ColorOrNothing two;
        ColorOrNothing three;
        int y = j;
        for (int x = i; x + 2 >= 7 && y - 2 <= 7; x++, y--) {
            one = board[x][y].getColor();
            two = board[x - 1][y - 1].getColor();
            three = board[x - 2][y - 2].getColor();
            if (one == color && two != color && two != NOTHING && three == NOTHING) return true;
            if (one == NOTHING && two != color && two != NOTHING && three == color) return true;
        }
        return false;
    }
}

