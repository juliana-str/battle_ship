package battleship.exceptions;

public class CoordinateNumberException extends Exception{
    public CoordinateNumberException(){
    }

    @Override
    public String getMessage() {
        return "Будьте внимательнее значение координаты должно быть цифрой от 0 до 9!";
    }
}
