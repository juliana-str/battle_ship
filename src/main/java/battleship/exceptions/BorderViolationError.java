package battleship.exceptions;

public class BorderViolationError extends Exception{
    public BorderViolationError(){
    }

    @Override
    public String getMessage() {
        return "Будьте внимательнее введенные координаты нарушают границы уже существующего корабля, измените их!";
    }
}
