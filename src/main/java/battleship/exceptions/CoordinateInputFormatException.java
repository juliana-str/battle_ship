package battleship.exceptions;

public class CoordinateInputFormatException extends Exception{
    public CoordinateInputFormatException(){
    }

    @Override
    public String getMessage() {
        return "Неверный формат ввода, попробуйте еще раз!";
    }
}
