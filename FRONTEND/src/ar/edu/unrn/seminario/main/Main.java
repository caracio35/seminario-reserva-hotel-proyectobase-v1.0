package ar.edu.unrn.seminario.main;

import java.awt.EventQueue;

import ar.edu.unrn.seminario.api.PersistenceApi;

public class Main {

	public static void main(String[] args) {
		// hp
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					// IApi api = new MemoryApi();
					// VentanaPrincipal frame = new VentanaPrincipal(api);
					// frame.setVisible(true);
					PersistenceApi p = new PersistenceApi();
					p.crearCaracteristicaEspecial("pileta chica", "pileta chica", 100);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
