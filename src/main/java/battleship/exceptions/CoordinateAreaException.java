package battleship.exceptions;

public class CoordinateAreaException extends Exception{
    public CoordinateAreaException(){
    }

    @Override
    public String getMessage() {
        return "Вокруг корабля должна быть область шириной в одну клетку, в которой не может быть других кораблей";
    }
}
