package sample;
import static sample.ColorOrNothing.WHITE;
import static sample.ColorOrNothing.BLACK;

public class Tile {
    private ColorOrNothing color;
    private boolean queen;
    private boolean mustAttack;
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


    public Tile(ColorOrNothing color) {
        this.color = color;
        this.queen = false;
        this.mustAttack = false;
        this.light = false;
    }

    public Tile(ColorOrNothing color, boolean queen, boolean mustAttack, boolean light) {
        this.color = color;
        this.queen = queen;
        this.mustAttack = mustAttack;
        this.light = light;
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

    public void setMustAttack(boolean mustAttack) {
        this.mustAttack = mustAttack;
    }

    public boolean getMustAttack() { return mustAttack;}

    public boolean getLight() {
        return light;
    }

    public void setLight(boolean light) {
        this.light = light;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile obj = (Tile) o;
        return color == obj.color && queen == obj.queen && mustAttack == obj.mustAttack && light == obj.light;
    }
}
