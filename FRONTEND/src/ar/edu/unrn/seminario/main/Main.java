package ar.edu.unrn.seminario.main;

import java.awt.EventQueue;
import java.util.List;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.MemoryApi;
import ar.edu.unrn.seminario.api.PersistenceApi;
import ar.edu.unrn.seminario.dto.HabitacionDTO;
import ar.edu.unrn.seminario.gui.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		// hp
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					IApi api = new MemoryApi();
					VentanaPrincipal frame = new VentanaPrincipal(api);
					frame.setVisible(true);
					PersistenceApi p = new PersistenceApi();
					// p.crearCaracteristicaEspecial("enano mimoso", "Expeciencia unica", 1000);
					// p.eliminarCaracteristica("enano mimoso");
					// p.obtenerCaracteristica();
					// String[] car = {"buee"};
					// p.darDeAltaHabitacion(2, "LAAAAAA", 25000, true, 22, car);
					List<HabitacionDTO> ha = p.obtenerTodasLasHabitaciones();
					for (HabitacionDTO h : ha) {
						System.out.println(h.getNumHabitacion());

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
