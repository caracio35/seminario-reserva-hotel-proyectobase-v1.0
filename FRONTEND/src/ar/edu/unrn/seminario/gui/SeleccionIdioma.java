package ar.edu.unrn.seminario.gui;

import java.util.Locale;

import javax.swing.JOptionPane;

public class SeleccionIdioma {

    public static Locale mostrarDialogoIdioma() {
        Object[] opciones = { "Español", "English" }; 
        int seleccion = JOptionPane.showOptionDialog(
                null,
                "Seleccione un idioma / Select a language:",
                "Seleccionar idioma",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccion == 1) {
            return new Locale("en"); 
        }
        return new Locale("es"); 
    }
}
