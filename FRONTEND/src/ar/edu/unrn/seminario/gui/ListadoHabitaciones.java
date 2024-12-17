package ar.edu.unrn.seminario.gui;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.HabitacionDTO;
import ar.edu.unrn.seminario.exception.CampoVacioExeption;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.EnterosEnCeroExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import ar.edu.unrn.seminario.exception.PrecioCeroExeption;

@SuppressWarnings("unused")
public class ListadoHabitaciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private ArrayList<HabitacionDTO> habitacionDTOs;
	private DefaultTableModel model;
	private IApi api;
	private String fechaFormateada;
	private int numHabitacionSelected;
	private JTextField textField;
	private Locale idiomaSeleccionado;
	private ResourceBundle recursos;

	public ListadoHabitaciones(IApi api, Locale idioma) throws ConexionFallidaExeption {
		this.idiomaSeleccionado = idioma;
		this.recursos = ResourceBundle.getBundle("labels", idioma);
		this.api = api;

		setBackground(SystemColor.textHighlight);
		getContentPane().setBackground(new Color(240, 240, 240));
		getContentPane().setForeground(new Color(255, 255, 255));
		setForeground(new Color(0, 0, 102));
		setTitle(recursos.getString("listaHabitaciones.titulo"));
		setSize(996, 298); // Establece el tamaño del JFrame
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		getContentPane().setLayout(null); // Establecer el layout como nulo para usar coordenadas absolutas

		// Crear el JScrollPane y JTable
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(110, 53, 763, 152);
		getContentPane().add(scrollPane);

		// Crear el modelo de la tabla
		model = new DefaultTableModel(new Object[][] {},
				new String[] { recursos.getString("listaHabitaciones.numeroHabitacion"),
						recursos.getString("listaHabitaciones.camaMatrimonial"),
						recursos.getString("listaHabitaciones.cantCamas"),
						recursos.getString("listaHabitaciones.disponible"),
						recursos.getString("listaHabitaciones.noDisponibleHasta") }) {

			private static final long serialVersionUID = 1L;

			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Object.class, Boolean.class, Object.class, Boolean.class,
					Object.class };

			public boolean isCellEditable(int row, int column) {
				// no te deja modificar las celdas
				return false;

			}

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};

		// Crear la tabla utilizando el modelo corregido
		// Añadir un MouseListener para detectar los clics en la tabla

		table = new JTable(model);

		this.llenarTabla();
		scrollPane.setViewportView(table);
		table.getTableHeader().setBackground(new Color(52, 73, 94)); // #34495E
		table.getTableHeader().setForeground(Color.WHITE);
		JButton btnActivarHabitacion = new JButton(recursos.getString("listaHabitaciones.activar"));
		btnActivarHabitacion.setForeground(new Color(255, 255, 255));
		btnActivarHabitacion.setBackground(new Color(90, 155, 213));
		btnActivarHabitacion.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, recursos.getString("listaHabitaciones.seleccionHabitacion"));
				return;
			}

			int response = JOptionPane.showConfirmDialog(null, recursos.getString("listaHabitaciones.confirmacion"),
					"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (response != JOptionPane.YES_OPTION) {
				return;
			}

			try {
				int numHabitacion = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
				api.activarHabitacion(numHabitacion);

				// Actualizar la tabla
				table.setValueAt(Boolean.TRUE, selectedRow, 3); // Activar la habitación
				table.setValueAt("", selectedRow, 4); // Limpiar la fecha de desactivación
				llenarTabla();

				JOptionPane.showMessageDialog(null, recursos.getString("listaHabitaciones.avisoActivacion"));

			} catch (ConexionFallidaExeption | ErrorDatosNoEncontradosExeption | CampoVacioExeption
					| EnterosEnCeroExeption | PrecioCeroExeption e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		});
		btnActivarHabitacion.setBounds(7, 66, 99, 21);
		getContentPane().add(btnActivarHabitacion);

		JButton btnDesactivar = new JButton(recursos.getString("listaHabitaciones.desactivar"));
		btnDesactivar.setForeground(new Color(255, 255, 255));
		btnDesactivar.setBackground(new Color(90, 155, 213));
		btnDesactivar.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, recursos.getString("listaHabitaciones.seleccionHabitacion"));
				return;
			}

			// Crear el panel con JDateChooser
			JPanel panel = new JPanel();
			panel.add(new JLabel(recursos.getString("listaHabitaciones.avisoFecha")));

			// Crear JDateChooser
			JDateChooser dateChooser = new JDateChooser();
			dateChooser.setDateFormatString("yyyy/MM/dd");
			panel.add(dateChooser);

			int option = JOptionPane.showConfirmDialog(null, panel, recursos.getString("listaHabitaciones.avisoFecha"),
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

			if (option != JOptionPane.OK_OPTION) {
				return;
			}

			java.util.Date fecha = dateChooser.getDate();
			if (fecha == null) {
				JOptionPane.showMessageDialog(null, recursos.getString("listaHabitaciones.avisoFecha"));
				return;
			}

			int numHabitacionSelected = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
			String fechaFormateada = new SimpleDateFormat("yyyy/MM/dd").format(fecha);

			int response = JOptionPane.showConfirmDialog(null,
					recursos.getString("listaHabitaciones.avisoDesactivacion") + fechaFormateada + "?", "Confirmación",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (response != JOptionPane.YES_OPTION) {
				return;
			}

			try {
				// Actualizar la tabla y la base de datos
				table.setValueAt(fechaFormateada, selectedRow, 4);
				table.setValueAt(Boolean.FALSE, selectedRow, 3);
				api.desactivarHabitacion(numHabitacionSelected, fechaFormateada);
				llenarTabla();

				JOptionPane.showMessageDialog(null,
						recursos.getString("listaHabitaciones.avisoDesac") + fechaFormateada + ".");

			} catch (ConexionFallidaExeption | ErrorDatosNoEncontradosExeption | CampoVacioExeption
					| EnterosEnCeroExeption | PrecioCeroExeption e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		});
		btnDesactivar.setBounds(7, 143, 99, 21);
		getContentPane().add(btnDesactivar);

		JButton btnEditarHabitacion = new JButton(recursos.getString("listaHabitaciones.editar"));
		btnEditarHabitacion.setForeground(new Color(255, 255, 255));
		btnEditarHabitacion.setBackground(new Color(90, 155, 213));
		btnEditarHabitacion.addActionListener(e -> {
			// Lógica para editar la habitación
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				int columnaSeleccionada = 0;
				// Obtener el valor de la celda en la fila y columna seleccionada
				Object valor = table.getValueAt(selectedRow, columnaSeleccionada);
				int numero = ((Number) valor).intValue();
				try {
					api.habitacionAModificar(numero);
					CargarHabitacion modificarHabitacion = new CargarHabitacion(api, true, idioma);
					modificarHabitacion.setVisible(true);
				} catch (ConexionFallidaExeption | ErrorDatosNoEncontradosExeption | CampoVacioExeption
						| EnterosEnCeroExeption | PrecioCeroExeption e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			} else {
				JOptionPane.showMessageDialog(null, recursos.getString("listaHabitaciones.seleccionHabitacion"));
			}

		});
		btnEditarHabitacion.setBounds(874, 66, 99, 21);
		getContentPane().add(btnEditarHabitacion);

		JButton btnEliminarHabitacion = new JButton(recursos.getString("listaHabitaciones.eliminar"));
		btnEliminarHabitacion.setForeground(new Color(255, 255, 255));
		btnEliminarHabitacion.setBackground(new Color(90, 155, 213));
		btnEliminarHabitacion.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(null, recursos.getString("listaHabitaciones.seleccionHabitacion"));
				return;
			}

			int response = JOptionPane.showConfirmDialog(null, recursos.getString("listaHabitaciones.avisoEliminacion"),
					"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (response != JOptionPane.YES_OPTION) {
				return;
			}

			try {
				// Obtener y eliminar la habitación
				numHabitacionSelected = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
				((DefaultTableModel) table.getModel()).removeRow(selectedRow);
				api.eliminarHabitacion(numHabitacionSelected);

				JOptionPane.showMessageDialog(null,
						recursos.getString("listaHabitaciones.avisoConfirmacionEliminacion"));

			} catch (ConexionFallidaExeption | ErrorDatosNoEncontradosExeption e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		});
		btnEliminarHabitacion.setBounds(874, 143, 99, 21);
		getContentPane().add(btnEliminarHabitacion);

		JButton btnSalir = new JButton(recursos.getString("listaHabitaciones.salir"));
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setBackground(new Color(231, 76, 60));
		btnSalir.addActionListener(e -> {
			int response = JOptionPane.showConfirmDialog(null, recursos.getString("listaHabitacionPreguntaSalir"),
					"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (response == JOptionPane.YES_OPTION) {
				dispose();
			}
		});
		btnSalir.setBounds(883, 232, 85, 21);
		getContentPane().add(btnSalir);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			private int numeroHabitacion;

			public void keyReleased(KeyEvent e) {

				search();
			}

			private void search() {
				numeroHabitacion = 0;
				String text = textField.getText();

				if (!text.isEmpty()) {
					try {
						numeroHabitacion = Integer.parseInt(textField.getText());
						List<HabitacionDTO> habitaciones = api.obtenerHabitacionesHabilitada();
						// aplicando filtro de precio minimo con stream
						List<HabitacionDTO> filtrado = habitaciones.stream()
								.filter(h -> h.getNumHabitacion() >= numeroHabitacion).collect(Collectors.toList());

						cargarHabitacionesFiltradas(filtrado);

					} catch (ConexionFallidaExeption | ErrorDatosNoEncontradosExeption e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Revisar los buscar habitacion tiene que ser un numero ");
					}
				} else {
					llenarTabla();
				}
			}
		});

		textField.setBounds(110, 25, 139, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel(recursos.getString("listaHabitaciones.buscar"));
		lblNewLabel.setBounds(110, 11, 122, 14);
		getContentPane().add(lblNewLabel);
	}

	public void llenarTabla() {
		List<HabitacionDTO> habitaciones;
		try {
			habitaciones = api.obtenerTodasLasHabitaciones();
			model.setRowCount(0);
			habitaciones.stream().sorted(Comparator.comparingInt(h -> h.getNumHabitacion())).forEach(habitacionDTO -> {
				Object[] fila = new Object[5];
				fila[0] = habitacionDTO.getNumHabitacion();
				fila[2] = habitacionDTO.getCantidadDeCamas();
				fila[3] = habitacionDTO.isHabilitado();
				if (!habitacionDTO.isHabilitado()) {
					fila[4] = habitacionDTO.getFechaHastaCuandoEstaDesactivado() != null
							? habitacionDTO.getFechaHastaCuandoEstaDesactivado()
							: "Indefinido";
				}
				model.addRow(fila);
			});
			table.setModel(model);

		} catch (ConexionFallidaExeption | ErrorDatosNoEncontradosExeption e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	public void cargarHabitacionesFiltradas(List<HabitacionDTO> habitaciones1) {

		model.setRowCount(0);
		habitaciones1.stream().sorted(Comparator.comparingInt(h -> h.getNumHabitacion())).forEach(habitacionDTO -> {
			Object[] fila = new Object[5];
			fila[0] = habitacionDTO.getNumHabitacion();
			fila[2] = habitacionDTO.getCantidadDeCamas();
			fila[3] = habitacionDTO.isHabilitado();
			if (!habitacionDTO.isHabilitado()) {
				fila[4] = habitacionDTO.getFechaHastaCuandoEstaDesactivado() != null
						? habitacionDTO.getFechaHastaCuandoEstaDesactivado()
						: "Indefinido";
			}
			model.addRow(fila);
		});
		table.setModel(model);
	}
}
