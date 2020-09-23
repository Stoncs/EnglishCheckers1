package sample;
import static sample.ColorOrNothing.WHITE;
import static sample.ColorOrNothing.BLACK;
import static sample.ColorOrNothing.NOTHING;

public class Tile {
    private ColorOrNothing color;
    private boolean queen;
    private boolean attack;
    private boolean light;
    public int getKey() {
        if (color == WHITE) {
            if (queen) {
                if (light) {
                    return 6;
                } else {
                    return 5;
                }
            } else {
                if (light) {
                    return 2;
                } else {
                    return 1;
                }
            }
        }
        if (color == BLACK) {
            if (queen) {
                if (light) {
                    return 8;
                } else {
                    return 7;
                }
            } else {
                if (light) {
                    return 4;
                } else {
                    return 3;
                }
            }
        } else {
            if (light) {
                return 9;
            } else {
                return 0;
            }
        }
    }


    public Tile(ColorOrNothing color, boolean queen) {
        this.color = color;
        this.queen = queen;
        this.attack = false;
        this.light = false;
    }

    public void setQueen(boolean queen) {
        this.queen = queen;
    }

    public boolean getQueen() {
        return queen;
    }

    public void setColor(ColorOrNothing color) {
        this.color = color;
    }

    public ColorOrNothing getColor() {
        return color;
    }

    public void setAttack(boolean attack) {
        this.attack = attack;
    }

    public boolean getAttack() { return attack;}

    public boolean getLight() {
        return light;
    }

    public void setLight(boolean light) {
        this.light = light;
    }
}
