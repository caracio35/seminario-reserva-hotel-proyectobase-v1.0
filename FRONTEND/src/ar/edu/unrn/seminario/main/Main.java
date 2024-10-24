package ar.edu.unrn.seminario.main;

import java.awt.EventQueue;

import ar.edu.unrn.seminario.api.PersistenceApi;
import ar.edu.unrn.seminario.gui.VentanaPrincipal;
import ar.edu.unrn.seminario.api.*;

public class Main {

	public static void main(String[] args) {
		// hp
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					IApi api = new MemoryApi();
					VentanaPrincipal frame = new VentanaPrincipal(api);
					frame.setVisible(true);
					// PersistenceApi p = new PersistenceApi();
					// p.crearCaracteristicaEspecial("enano mimoso", "Expeciencia unica", 1000);
					// p.eliminarCaracteristica("enano mimoso");
					// p.obtenerCaracteristica();
					// String [] car= {"Balcon","Pileta"};
					// p.darDeAltaHabitacion(2, "LAAAAAA", 25000, true, 122, car);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
