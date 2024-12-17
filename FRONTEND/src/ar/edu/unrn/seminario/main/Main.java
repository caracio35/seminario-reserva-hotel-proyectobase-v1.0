package ar.edu.unrn.seminario.main;

import java.awt.EventQueue;
import java.util.Locale;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.PersistenceApi;
import ar.edu.unrn.seminario.gui.Login;
import ar.edu.unrn.seminario.gui.SeleccionIdioma;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Mostrar diálogo de selección de idioma
					Locale seleccion = SeleccionIdioma.mostrarDialogoIdioma();

					// Crear la API (simulación)
					IApi api = new PersistenceApi();

					// Configurar el idioma seleccionado
					// ResourceBundle labels = ResourceBundle.getBundle("labels", seleccion);

					// Mostrar ventana de login
					Login logi = new Login(api, seleccion);
					logi.setVisible(true);

				} catch (Exception e) {
					System.out.println(
							"Problema en el funcionamiento de la ventana " + e.getStackTrace() + e.getMessage());
				}
			}
		});
	}
}
