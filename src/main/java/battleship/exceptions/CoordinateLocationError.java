package battleship.exceptions;

public class CoordinateLocationError extends Exception{
    public CoordinateLocationError(){

    }

    @Override
    public String getMessage() {
        return "Неверно введены координаты, они находится не рядом!";
    }
}
