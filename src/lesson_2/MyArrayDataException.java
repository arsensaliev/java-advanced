package lesson_2;

public class MyArrayDataException extends Exception {
    private String message;

    public MyArrayDataException(int i, int j) {
        this.message = "Ошибка переобразования на arr[" + i + "][" + j + "] элементе";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
