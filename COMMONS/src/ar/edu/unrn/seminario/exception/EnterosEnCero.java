package ar.edu.unrn.seminario.exception;

public class EnterosEnCero extends Exception {
    public EnterosEnCero(String mensaje) {
        super(mensaje);
    }

    public EnterosEnCero() {
        super("Los campos no pueden ser cero o negativos ");
    }

}
