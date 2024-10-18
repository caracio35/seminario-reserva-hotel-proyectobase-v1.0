package ar.edu.unrn.seminario.main;

import java.awt.EventQueue;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.MemoryApi;
import ar.edu.unrn.seminario.gui.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		// hp
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					IApi api = new MemoryApi();
//					BDApi apiBdApi = new BDApi();
//					apiBdApi.crearCaracteristicaEspecial("Pileta chica", "pileta chica", 100.00);
//					apiBdApi.darDeAltaHabitacion(2, "tata", 1000.0, false, 13, null);
//					apiBdApi.darDeBajaHabitacion(2, null, 0);
					VentanaPrincipal frame = new VentanaPrincipal(api);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
