package sample;

import static sample.ColorOrNothing.WHITE;
import static sample.ColorOrNothing.BLACK;
import static sample.ColorOrNothing.NOTHING;

public class GameBoard {
    static Tile[][] board = new Tile[8][8];
    private static boolean isWhiteTurn = false;
    private static boolean mustAttack = false;
    private static int oldX;
    private static int oldY;

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
        if (isAttack()) {
            if (mustAttack == board[oldX][oldY].getMustAttack()) {
                if (color == WHITE && isWhiteTurn && canAttackWhite(x, y)) {
                    if (board[oldX][oldY].getColor() == WHITE) {
                        cleanLight();
                    }
                    canAttackWhite(x, y);
                    oldX = x;
                    oldY = y;
                    board[x][y].setLight(true);
                }
                if (color == BLACK && !isWhiteTurn && canAttackBlack(x, y)) {
                    if (board[oldX][oldY].getColor() == BLACK) {
                        cleanLight();
                    }
                    canAttackBlack(x, y);
                    oldX = x;
                    oldY = y;
                    board[x][y].setLight(true);
                }
                if (color == NOTHING && board[x][y].getLight()) {
                    if (isWhiteTurn) {
                        board[x][y].setColor(WHITE);
                    } else {
                        board[x][y].setColor(BLACK);
                    }
                    if (board[oldX][oldY].getQueen()) {
                        board[oldX][oldY].setQueen(false);
                        board[x][y].setQueen(true);
                    }
                    board[oldX][oldY].setColor(NOTHING);
                    if (y < oldY) {
                        if (x < oldX) {
                            board[oldX - 1][oldY - 1].setColor(NOTHING);
                            board[oldX - 1][oldY - 1].setQueen(false);
                        } else {
                            board[oldX + 1][oldY - 1].setColor(NOTHING);
                            board[oldX + 1][oldY - 1].setQueen(false);
                        }
                    } else {
                        if (x < oldX) {
                            board[oldX - 1][oldY + 1].setColor(NOTHING);
                            board[oldX - 1][oldY + 1].setQueen(false);
                        } else {
                            board[oldX + 1][oldY + 1].setColor(NOTHING);
                            board[oldX + 1][oldY + 1].setQueen(false);
                        }
                    }
                    if (isWhiteTurn) {
                        if (canAttackWhite(x, y)) {
                            board[oldX][oldY].setMustAttack(false);
                            board[x][y].setMustAttack(true);
                            mustAttack = true;
                        } else {
                            board[oldX][oldY].setMustAttack(false);
                            mustAttack = false;
                            endOfBoard(x, y);
                            isWhiteTurn = !isWhiteTurn;
                        }
                    } else {
                        if (canAttackBlack(x, y)) {
                            board[oldX][oldY].setMustAttack(false);
                            board[x][y].setMustAttack(true);
                            mustAttack = true;
                        } else {
                            board[oldX][oldY].setMustAttack(false);
                            mustAttack = false;
                            endOfBoard(x, y);
                            isWhiteTurn = !isWhiteTurn;
                        }
                    }
                    oldX = x;
                    oldY = y;
                    cleanLight();
                }
            }
        } else {
            if (color == WHITE && isWhiteTurn) {
                oldX = x;
                oldY = y;
                cleanLight();
                board[x][y].setLight(true);
                if (canMove(x - 1, y - 1)) board[x - 1][y - 1].setLight(true);
                if (canMove(x - 1, y + 1)) board[x - 1][y + 1].setLight(true);
                if (board[x][y].getQueen()) {
                    if (canMove(x + 1, y - 1)) board[x + 1][y - 1].setLight(true);
                    if (canMove(x + 1, y + 1)) board[x + 1][y + 1].setLight(true);
                }

            }

            if (color == BLACK && !isWhiteTurn) {
                oldX = x;
                oldY = y;
                cleanLight();
                board[x][y].setLight(true);
                if (canMove(x + 1, y + 1)) board[x + 1][y + 1].setLight(true);
                if (canMove(x + 1, y - 1)) board[x + 1][y - 1].setLight(true);
                if (board[x][y].getQueen()) {
                    if (canMove(x - 1, y - 1)) board[x - 1][y - 1].setLight(true);
                    if (canMove(x - 1, y + 1)) board[x - 1][y + 1].setLight(true);
                }
            }

            if (color == NOTHING && board[x][y].getLight()) {
                if (isWhiteTurn) {
                    board[x][y].setColor(WHITE);
                } else {
                    board[x][y].setColor(BLACK);
                }
                if (board[oldX][oldY].getQueen()) {
                    board[oldX][oldY].setQueen(false);
                    board[x][y].setQueen(true);
                }
                board[oldX][oldY].setColor(NOTHING);
                cleanLight();
                endOfBoard(x, y);
                isWhiteTurn = !isWhiteTurn;
            }
        }
    }

    //очищаем доску от подсвеченных шашек и полей
    private static void cleanLight() {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if ((i + j) % 2 == 1) board[i][j].setLight(false);
            }
        }
    }

    //находится ли ячейка на доске
    private static boolean canMove(int x, int y) {
        if (x <= 7 && x >= 0 && y <= 7 && y >= 0) {
            return board[x][y].getColor() == NOTHING;
        }
        return false;
    }

    //проверка конца доски, выставляем дамку, если надо
    private static void endOfBoard(int x, int y) {
        if ((isWhiteTurn && x == 0) || (!isWhiteTurn && x == 7)) {
            board[x][y].setQueen(true);
        }
    }

    //может ли белая шашка рубить
    private static boolean canAttackWhite(int x, int y) {
        boolean result = false;
        if (canMove(x - 2, y - 2) && board[x - 1][y - 1].getColor() != WHITE && board[x - 1][y - 1].getColor() != NOTHING) {
            board[x - 2][y - 2].setLight(true);
            result = true;
        }
        if (canMove(x - 2, y + 2) && board[x - 1][y + 1].getColor() != WHITE && board[x - 1][y + 1].getColor() != NOTHING) {
            board[x - 2][y + 2].setLight(true);
            result = true;
        }
        if (board[x][y].getQueen()) {
            if (canMove(x + 2, y - 2) && board[x + 1][y - 1].getColor() != WHITE && board[x + 1][y - 1].getColor() != NOTHING) {
                board[x + 2][y - 2].setLight(true);
                result = true;
            }
            if (canMove(x + 2, y + 2) && board[x + 1][y + 1].getColor() != WHITE && board[x + 1][y + 1].getColor() != NOTHING) {
                board[x + 2][y + 2].setLight(true);
                result = true;
            }
        }
        return result;
    }

    //могут ли чёрная шашка рубить
    private static boolean canAttackBlack(int x, int y) {
        boolean result = false;
        if (canMove(x + 2, y - 2) && board[x + 1][y - 1].getColor() != BLACK && board[x + 1][y - 1].getColor() != NOTHING) {
            board[x + 2][y - 2].setLight(true);
            result = true;
        }
        if (canMove(x + 2, y + 2) && board[x + 1][y + 1].getColor() != BLACK && board[x + 1][y + 1].getColor() != NOTHING) {
            board[x + 2][y + 2].setLight(true);
            result = true;
        }
        if (board[x][y].getQueen()) {
            if (canMove(x - 2, y - 2) && board[x - 1][y - 1].getColor() != BLACK && board[x - 1][y - 1].getColor() != NOTHING) {
                board[x - 2][y - 2].setLight(true);
                result = true;
            }
            if (canMove(x - 2, y + 2) && board[x - 1][y + 1].getColor() != BLACK && board[x - 1][y + 1].getColor() != NOTHING) {
                board[x - 2][y + 2].setLight(true);
                result = true;
            }
        }
        return result;
    }

    //надо ли игроку сейчас бить
    private static boolean isAttack() {
        ColorOrNothing color;
        if (isWhiteTurn) {
            color = WHITE;
        } else {
            color = BLACK;
        }

        if (color == WHITE) {
            for (int i = 0; i <= 7; i++) {
                for (int j = 0; j <= 7; j++) {
                    if ((i + j) % 2 == 1 && board[i][j].getColor() == WHITE) {
                        if (canMove(i - 2, j - 2) && board[i - 2][j - 2].getColor() == NOTHING && board[i - 1][j - 1].getColor() == BLACK)
                            return true;
                        if (canMove(i - 2, j + 2) && board[i - 2][j + 2].getColor() == NOTHING && board[i - 1][j + 1].getColor() == BLACK)
                            return true;
                        if (board[i][j].getQueen()) {
                            if (canMove(i + 2, j - 2) && board[i + 2][j - 2].getColor() == NOTHING && board[i + 1][j - 1].getColor() == BLACK)
                                return true;
                            if (canMove(i + 2, j + 2) && board[i + 2][j + 2].getColor() == NOTHING && board[i + 1][j + 1].getColor() == BLACK)
                                return true;
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i <= 7; i++) {
                for (int j = 0; j <= 7; j++) {
                    if ((i + j) % 2 == 1 && board[i][j].getColor() == BLACK) {
                        if (canMove(i + 2, j - 2) && board[i + 2][j - 2].getColor() == NOTHING && board[i + 1][j - 1].getColor() == WHITE)
                            return true;
                        if (canMove(i + 2, j + 2) && board[i + 2][j + 2].getColor() == NOTHING && board[i + 1][j + 1].getColor() == WHITE)
                            return true;
                        if (board[i][j].getQueen()) {
                            if (canMove(i - 2, j - 2) && board[i - 2][j - 2].getColor() == NOTHING && board[i - 1][j - 1].getColor() == WHITE)
                                return true;
                            if (canMove(i - 2, j + 2) && board[i - 2][j + 2].getColor() == NOTHING && board[i - 1][j + 1].getColor() == WHITE)
                                return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}

