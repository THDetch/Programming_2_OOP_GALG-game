package thd.game.utilities;
/*
import thd.gameView.GameView;
import thd.gameobjects.base.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
*/
class Sorter {
    public static void main(String[] args) {

    }

   /* private void naturalOrder(ArrayList<Position> positions) {

        Collections.sort(positions);
    }

    private void xOrder(ArrayList<Position> positions) {
        class CompareByX implements Comparator<Position> {
            @Override
            public int compare(Position position1, Position position2) {
                return Double.compare(position1.x, position2.x);
            }
        }
        Collections.sort(positions, new CompareByX());
        //positions.sort(new CompareByX());
    }

    private void yOrder(ArrayList<Position> positions) {
        Comparator<Position> comparator = new Comparator<>() {
            @Override
            public int compare(Position position1, Position position2) {
                return Double.compare(position1.y, position2.y);
            }
        };
        Collections.sort(positions, comparator);
    }

    private void centricOrder(ArrayList<Position> positions) {
        Position center = new Position(GameView.WIDTH / 2.0, GameView.HEIGHT / 2.0);
        Comparator<Position> comparator = (position1, position2) -> Double.compare(position1.distance(center), position2.distance(center));
        Collections.sort(positions, comparator);
    }

*/
}
