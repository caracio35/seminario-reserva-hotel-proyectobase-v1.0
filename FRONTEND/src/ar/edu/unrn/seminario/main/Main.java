package ar.edu.unrn.seminario.main;

import java.awt.EventQueue;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.PersistenceApi;
import ar.edu.unrn.seminario.gui.Login;

public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					IApi api = new PersistenceApi();
					// VentanaPrincipal frame = new VentanaPrincipal(api);
					// frame.setVisible(true);
					Login login = new Login(api);
					login.setVisible(true);

				} catch (Exception e) {
					System.out.println(
							"Problema en el funcionamiento de la ventana " + e.getStackTrace() + e.getMessage());
				}
			}
		});
	}

}
