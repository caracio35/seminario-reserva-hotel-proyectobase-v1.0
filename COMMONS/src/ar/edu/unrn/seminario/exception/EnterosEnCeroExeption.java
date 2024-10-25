package ar.edu.unrn.seminario.exception;

public class EnterosEnCeroExeption extends Exception {
    public EnterosEnCeroExeption(String mensaje) {
        super(mensaje);
    }

    public EnterosEnCeroExeption() {
        super("entero no puede ser cero o negativo");
    }

}
