package ar.edu.unrn.seminario.main;

import java.awt.EventQueue;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.MemoryApi;
import ar.edu.unrn.seminario.gui.ListadoHabitaciones;
import ar.edu.unrn.seminario.gui.VentanaPrincipal;
import ar.edu.unrn.seminario.gui.VerReservas;

public class Main {

	public static void main(String[] args) {
		//hp
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					IApi api = new MemoryApi();
					VentanaPrincipal frame = new VentanaPrincipal(api);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
