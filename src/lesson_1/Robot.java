package lesson_1;

public class Robot implements Action {
    private String name;
    private int maxDistanceRun;
    private int maxDistanceJump;


    public Robot(String name, int maxDistanceRun, int maxDistanceJump) {
        this.name = name;
        this.maxDistanceRun = maxDistanceRun;
        this.maxDistanceJump = maxDistanceJump;
    }

    public String getName() {
        return name;
    }

    public int getMaxDistanceRun() {
        return maxDistanceRun;
    }

    public int getMaxDistanceJump() {
        return maxDistanceJump;
    }

    @Override
    public boolean run(int distance) {

        if(distance <= 0){
            System.out.println("Некорректное число");
            return false;
        }

        if(distance > maxDistanceRun){
            System.out.println("Робот по имени " + name + " не смог пробежать заданную дистанцию!");
            return false;
        }

        System.out.println("Робот по имени " + name + " пробежал заданную дистанцию!");

        return true;
    }

    @Override
    public boolean jump(int distance) {
        if(distance <= 0){
            System.out.println("Некорректное число");
            return  false;
        }

        if(distance > maxDistanceJump){
            System.out.println("Робот по имени " + name + " не смог прыгнуть больше заданного расстояния!");
            return false;
        }

        System.out.println("Робот по имени " + name + " прыгнул заданное расстояние!");

        return true;
    }
}
