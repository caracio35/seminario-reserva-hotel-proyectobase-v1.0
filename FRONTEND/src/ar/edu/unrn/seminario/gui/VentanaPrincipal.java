package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.exception.CampoVacioExeption;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.EnterosEnCeroExeption;
import ar.edu.unrn.seminario.exception.PrecioCeroExeption;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;

	public VentanaPrincipal(IApi api, Locale seleccion) {

		ResourceBundle labels = ResourceBundle.getBundle("labels", seleccion);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnReservas = new JMenu(labels.getString("menu.reservas"));
		menuBar.add(mnReservas);

		JMenuItem mntmBuscarHabitacion = new JMenuItem(labels.getString("menuitem.buscar_habitacion"));
		mntmBuscarHabitacion.addActionListener(e -> {
			BusquedaDeHabitaciones busqueda = new BusquedaDeHabitaciones(api, seleccion);
			busqueda.setVisible(true);
		});
		mnReservas.add(mntmBuscarHabitacion);

		JMenuItem mntmMisReservas = new JMenuItem(labels.getString("menuitem.mis_reservas"));
		mntmMisReservas.addActionListener(e -> {
			try {
				VerReservas misReservas = new VerReservas(api, seleccion);
				misReservas.setVisible(true);
			} catch (CampoVacioExeption | EnterosEnCeroExeption | PrecioCeroExeption e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		});
		mnReservas.add(mntmMisReservas);

		JMenu usuarioMenu = new JMenu(labels.getString("menu.usuarios"));
		usuarioMenu.setVisible(api.esAdmin());
		menuBar.add(usuarioMenu);

		JMenuItem altaUsuarioMenuItem = new JMenuItem(labels.getString("menuitem.alta_usuario"));
		altaUsuarioMenuItem.addActionListener(arg0 -> {
			JOptionPane.showMessageDialog(null, labels.getString("login.aviso"));
//            AltaUsuario alta = new AltaUsuario(api);
//            alta.setLocationRelativeTo(null);
//            alta.setVisible(true);
		});
		usuarioMenu.add(altaUsuarioMenuItem);

		JMenuItem listadoUsuarioMenuItem = new JMenuItem(labels.getString("menuitem.listado_usuario"));
		listadoUsuarioMenuItem.addActionListener(arg0 -> {
			JOptionPane.showMessageDialog(null, labels.getString("login.aviso"));
		});
		usuarioMenu.add(listadoUsuarioMenuItem);

		JMenu mnHabitaciones = new JMenu(labels.getString("menu.habitaciones"));
		mnHabitaciones.setVisible(api.esAdmin());
		menuBar.add(mnHabitaciones);

		JMenuItem mntmCargarHabitacion = new JMenuItem(labels.getString("menuitem.cargar_habitacion"));
		mntmCargarHabitacion.addActionListener(e -> {
			CargarHabitacion cargaH = new CargarHabitacion(api, false, seleccion);
			api.modificarFalse();
			cargaH.setVisible(true);
		});
		mnHabitaciones.add(mntmCargarHabitacion);

		JMenuItem mntmListadoHabitaciones = new JMenuItem(labels.getString("menuitem.listado_habitaciones"));
		mntmListadoHabitaciones.addActionListener(e -> {
			try {
				ListadoHabitaciones listaHabitaciones = new ListadoHabitaciones(api, seleccion);
				listaHabitaciones.setVisible(true);
			} catch (ConexionFallidaExeption e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		});
		mnHabitaciones.add(mntmListadoHabitaciones);

		JMenu configuracionMenu = new JMenu(labels.getString("menu.configuracion"));
		menuBar.add(configuracionMenu);

		JMenuItem salirMenuItem = new JMenuItem(labels.getString("menuitem.salir"));
		salirMenuItem.addActionListener(e -> VentanaPrincipal.this.dispose());
		configuracionMenu.add(salirMenuItem);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
}
