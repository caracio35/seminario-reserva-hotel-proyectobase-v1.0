package ar.edu.unrn.seminario.main;

import java.awt.EventQueue;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.PersistenceApi;
import ar.edu.unrn.seminario.gui.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		// hp
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					// IApi api = new MemoryApi();

					IApi api = new PersistenceApi();
					VentanaPrincipal frame = new VentanaPrincipal(api);
					frame.setVisible(true);
					// p.crearCaracteristicaEspecial("enano mimoso", "Expeciencia unica", 1000);
					// p.eliminarCaracteristica("enano mimoso");
					// p.obtenerCaracteristica();
					// String[] car = {"buee"};
					// p.darDeAltaHabitacion(2, "LAAAAAA", 25000, true, 22, car);
					// List<HabitacionDTO> ha = p.obtenerTodasLasHabitaciones();
					// for (HabitacionDTO h : ha) {
					// System.out.println(h.getNumHabitacion());
					//int[] habitaciones = {2};
					//String usuario = "carlosl";
					//String fechaInicio = "2042-11-01";
					//String fechaFin = "2020-11-05";
					//String fechaReserva = "2024-10-31";
					//int cantidadPersonas = 8;
					//String[] servicios = {"Desayuno", "Merienda"};
					//boolean pagoMinimo = true;
					//api.generarReserva(habitaciones, usuario, fechaInicio, fechaFin, fechaReserva,cantidadPersonas, servicios, pagoMinimo);
					// }
					// p.generarCalificacionHabitacion(35, 1, "Una mierda su habitacion");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
