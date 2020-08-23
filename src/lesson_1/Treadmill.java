package lesson_1;

public class Treadmill implements Obstacles{
    private int distance;

    public Treadmill(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean competition(Action action) {
        return action.run(distance);
    }
}