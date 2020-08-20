package lesson_1;

public interface Action {
    boolean run(int distance);

    boolean jump(int distance);

    String getName();
}
