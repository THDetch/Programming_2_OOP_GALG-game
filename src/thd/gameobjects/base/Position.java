package thd.gameobjects.base;

import thd.gameView.GameView;

import java.util.Objects;

/**
 * the position in 960*540 window created by GameView class {@link GameView}.
 *
 * @see GameView
 */
public class Position implements Cloneable, Comparable<Position> {
    /**
     * the position on the X-axe.
     */
    public double x;
    /**
     * the position on the Y-axe.
     */
    public double y;

    /**
     * standard constructor to set the values.
     *
     * @param x set the position on the X-axe.
     * @param y set the position on the Y-axe.
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * default constructor without parameters to set the values at zero.
     */
    public Position() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * moving the position one pixel left.
     */
    public void left() {
        x = x - 1;
    }

    /**
     * moving the position times of pixels left.
     *
     * @param pixels the number of pixels that object should move it to left.
     */
    public void left(double pixels) {
        x = x - pixels;
    }

    /**
     * moving the position one pixel right.
     */
    public void right() {
        x = x + 1;
    }

    /**
     * moving the position times of pixels right.
     *
     * @param pixels the number of pixels that object should move it to right.
     */
    public void right(double pixels) {
        x = x + pixels;
    }

    /**
     * moving the position one pixel up.
     */
    public void up() {
        y = y - 1;
    }

    /**
     * moving the position times of pixels up.
     *
     * @param pixels the number of pixels that object should move it up.
     */
    public void up(double pixels) {
        y = y - pixels;
    }

    /**
     * moving the position one pixel down.
     */
    public void down() {
        y = y + 1;
    }

    /**
     * moving the position times of pixels down.
     *
     * @param pixels the number of pixels that object should move it down.
     */
    public void down(double pixels) {
        y = y + pixels;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {

        String output = "Position (" + x + ", " + y + ")";
        //String output = "Position (" + (int) Math.round(x) + ", " + (int) Math.round(y) + ")";
        return output;
    }

    /**
     * calculate the distance between two positions.
     *
     * @param other another Position to calculate the distance between.
     * @return double distance.
     */
    public double distance(Position other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Position other = (Position) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public Position clone() {
        Position newPosition = null;
        try {
            newPosition = (Position) super.clone();
        } catch (CloneNotSupportedException ignored) {
        }
        return newPosition;
    }

    /**
     * comparing to another position depending on the distance from zero.
     *
     * @param other another position to compare with.
     * @return Integer.
     */
    @Override
    public int compareTo(Position other) {
        return Double.compare(this.distance(new Position(0, 0)), other.distance(new Position(0, 0)));
    }
}