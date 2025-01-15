package battleship.exceptions;

public class CoordinateInputCount extends Exception{
    public CoordinateInputCount(){
    }

    @Override
    public String getMessage() {
        return "Будьте внимательнее количесто введенных координат не совпадает с размером корябля!";
    }
}
