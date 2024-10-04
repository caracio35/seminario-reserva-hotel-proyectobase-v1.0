package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.MemoryApi;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;

	// andres gei
	public VentanaPrincipal(IApi api) {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu usuarioMenu = new JMenu("Usuarios");
		menuBar.add(usuarioMenu);

		JMenuItem altaUsuarioMenuItem = new JMenuItem("Alta/Modificación");
		altaUsuarioMenuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				AltaUsuario alta = new AltaUsuario(api);
				alta.setLocationRelativeTo(null);
				alta.setVisible(true);
			}

		});
		usuarioMenu.add(altaUsuarioMenuItem);

		JMenuItem listadoUsuarioMenuItem = new JMenuItem("Listado");
		listadoUsuarioMenuItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				ListadoUsuario listado = new ListadoUsuario(api);
				listado.setLocationRelativeTo(null);
				listado.setVisible(true);
			}

		});
		usuarioMenu.add(listadoUsuarioMenuItem);

		JMenu mnHabitaciones = new JMenu("Habitaciones");
		menuBar.add(mnHabitaciones);

		JMenuItem mntmCarcarHabitacion = new JMenuItem("Cargar Habitacion");
		mntmCarcarHabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CargarHabitacion cargaH = new CargarHabitacion(api);
				cargaH.setVisible(true);
			}
		});
		mnHabitaciones.add(mntmCarcarHabitacion);

		JMenuItem mntmListadoHabitaciones = new JMenuItem("Listado Habitaciones");
		mntmListadoHabitaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoHabitaciones listaHabitaciones = new ListadoHabitaciones(null);
				listaHabitaciones.setVisible(true);
			}
		});
		mntmListadoHabitaciones.setSelected(true);
		mnHabitaciones.add(mntmListadoHabitaciones);

		JMenu mnReservas = new JMenu("Reservas");
		menuBar.add(mnReservas);

		JMenuItem mntmBuscarhabitacion = new JMenuItem("buscarHabitacion");
		mntmBuscarhabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BusquedaDeHabitaciones busqueda = new BusquedaDeHabitaciones(api);
				busqueda.setVisible(true);
			}
		});
		mnReservas.add(mntmBuscarhabitacion);

		JMenuItem mntmListadoHabitaciones_1 = new JMenuItem("Mis Reservas");
		mntmListadoHabitaciones_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerReservas misReservas = new VerReservas();
				misReservas.setVisible(true);
			}
		});
		mntmListadoHabitaciones_1.setSelected(true);
		mnReservas.add(mntmListadoHabitaciones_1);

		JMenu configuracionMenu = new JMenu("Configuración");
		menuBar.add(configuracionMenu);

		JMenuItem salirMenuItem = new JMenuItem("Salir");
		salirMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal.this.dispose();
			}
		});
		configuracionMenu.add(salirMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
