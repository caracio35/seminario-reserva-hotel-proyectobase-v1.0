package ar.edu.unrn.seminario.exception;

public class NumeroHabitacionExistenteException extends Exception {
    public NumeroHabitacionExistenteException(String mensaje) {
        super(mensaje);
    }

    public NumeroHabitacionExistenteException() {
        super("el numero de habitacion ya existe");
    }
}
