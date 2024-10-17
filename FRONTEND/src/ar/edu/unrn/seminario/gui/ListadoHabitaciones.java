package ar.edu.unrn.seminario.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.HabitacionDTO;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;

public class ListadoHabitaciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private ArrayList<HabitacionDTO> habitacionDTOs;
	private DefaultTableModel model;
	private IApi api;
	private String fechaFormateada;
	private int numHabitacionSelected;
	private JTextField textField;

	public ListadoHabitaciones(IApi api) {
		
		setBackground(SystemColor.textHighlight);
		getContentPane().setBackground(new Color(240, 240, 240));
		getContentPane().setForeground(new Color(255, 255, 255));
		setForeground(new Color(0, 0, 102));
		// Configurar el JFrame
		// this.habitacionDTOs = habitacionDTOs;
		this.api = api;
		setTitle("Lista de Habitaciones");
		setSize(996, 298); // Establece el tamaño del JFrame
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		getContentPane().setLayout(null); // Establecer el layout como nulo para usar coordenadas absolutas

		// Crear el JScrollPane y JTable
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(110, 53, 763, 152);
		getContentPane().add(scrollPane);

		// Crear el modelo de la tabla
		model = new DefaultTableModel(new Object[][] {}, new String[] { "Numero de Habitacion", "Cama Matrim",
				"Cant Camas", "Disponible", "No disponible Hasta" }) {

			private static final long serialVersionUID = 1L;

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
		table.getTableHeader().setBackground(new Color(52, 73, 94));  // #34495E
		table.getTableHeader().setForeground(Color.WHITE);
		JButton btnActivarHabitacion = new JButton("Activar ");
		btnActivarHabitacion.setForeground(new Color(255, 255, 255));
		btnActivarHabitacion.setBackground(new Color(90, 155, 213));
		btnActivarHabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) { // Verificar si se ha seleccionado una fila
					int response = JOptionPane.showConfirmDialog(null,
							"¿Está seguro de que desea activar esta habitación?", "Confirmación",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (response == JOptionPane.YES_OPTION) {
						// Actualizar la tabla
						table.setValueAt(Boolean.TRUE, selectedRow, 3); // Activar la habitación
						table.setValueAt("", selectedRow, 4); // Limpiar la fecha de desactivación

						// Lógica para activar la habitación
						JOptionPane.showMessageDialog(null, "Habitación activada.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione una habitación de la tabla.");
				}
			}
		});
		btnActivarHabitacion.setBounds(7, 66, 99, 21);
		getContentPane().add(btnActivarHabitacion);

		JButton btnDesactivar = new JButton("Desactivar ");
		btnDesactivar.setForeground(new Color(255, 255, 255));
		btnDesactivar.setBackground(new Color(90, 155, 213));
		btnDesactivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) { // Verificar si se ha seleccionado una fila
					// Crear el panel con JDateChooser
					JPanel panel = new JPanel();
					panel.add(new JLabel("Seleccione la fecha de desactivación:"));

					// Crear JDateChooser
					JDateChooser dateChooser = new JDateChooser();
					dateChooser.setDateFormatString("dd/MM/yyyy"); // Establecer el formato de la fecha
					panel.add(dateChooser);

					int option = JOptionPane.showConfirmDialog(null, panel, "Ingrese fecha de desactivación",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

					if (option == JOptionPane.OK_OPTION) {
						java.util.Date fecha = dateChooser.getDate();
						if (fecha != null) {
							// Formatear la fecha para mostrar
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							fechaFormateada = sdf.format(fecha);

							int response = JOptionPane.showConfirmDialog(null,
									"¿Está seguro de que desea desactivar esta habitación hasta el " + fechaFormateada
											+ "?",
									"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

							if (response == JOptionPane.YES_OPTION) {
								// Actualizar la tabla
								// table.setValueAt(fechaFormateada, selectedRow, 4);
								// table.setValueAt(Boolean.FALSE, selectedRow, 3); // Desactivar la habitación
								numHabitacionSelected = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
								api.darDeBajaHabitacion(numHabitacionSelected, fechaFormateada, selectedRow);
								llenarTabla();
								// Lógica para desactivar la habitación
								JOptionPane.showMessageDialog(null,
										"Habitación desactivada hasta " + fechaFormateada + ".");
							}

						} else {
							JOptionPane.showMessageDialog(null, "Por favor, seleccione una fecha.");
						}

					}
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione una habitación de la tabla.");
				}
			}
		});
		btnDesactivar.setBounds(7, 143, 99, 21);
		getContentPane().add(btnDesactivar);

		JButton btnEditarHabitacion = new JButton("Editar");
		btnEditarHabitacion.setForeground(new Color(255, 255, 255));
		btnEditarHabitacion.setBackground(new Color(90, 155, 213));
		btnEditarHabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Lógica para editar la habitación
				int selectedRow = table.getSelectedRow();
				int columnaSeleccionada = 0;
				// Obtener el valor de la celda en la fila y columna seleccionada
				Object valor = table.getValueAt(selectedRow, columnaSeleccionada);
				int numero = ((Number) valor).intValue();
				CargarHabitacion modificarHabitacion = new CargarHabitacion(api, numero);
				modificarHabitacion.setVisible(true);
			}
		});
		btnEditarHabitacion.setBounds(874, 66, 99, 21);
		getContentPane().add(btnEditarHabitacion);

		JButton btnEliminarHabitacion = new JButton("Eliminar ");
		btnEliminarHabitacion.setForeground(new Color(255, 255, 255));
		btnEliminarHabitacion.setBackground(new Color(90, 155, 213));
		btnEliminarHabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) { // Verificar si se ha seleccionado una fila
					int response = JOptionPane.showConfirmDialog(null,
							"¿Está seguro de que desea eliminar esta habitación?", "Confirmación",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (response == JOptionPane.YES_OPTION) {
						// Eliminar la fila de la tabla
						numHabitacionSelected = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
						((DefaultTableModel) table.getModel()).removeRow(selectedRow);
						// eliminar Habitacion
						api.eliminarHabitacion(numHabitacionSelected);
						// Lógica para eliminar la habitación
						JOptionPane.showMessageDialog(null, "Habitación eliminada.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione una habitación de la tabla.");
				}
			}
		});
		btnEliminarHabitacion.setBounds(874, 143, 99, 21);
		getContentPane().add(btnEliminarHabitacion);

		JButton btnSalir = new JButton("Salir");
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setBackground(new Color(231, 76, 60));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea salir?", "Confirmación",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (response == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		btnSalir.setBounds(883, 232, 85, 21);
		getContentPane().add(btnSalir);
		
		textField = new JTextField();
		textField.setBounds(110, 25, 139, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Buscar Habitacion");
		lblNewLabel.setBounds(110, 11, 122, 14);
		getContentPane().add(lblNewLabel);
	}

	public void llenarTabla() {

		List<HabitacionDTO> habitaciones = api.obtenerTodasLasHabitaciones();
		model.setRowCount(0);
		for (HabitacionDTO habitacionDTO : habitaciones) {
			Object[] fila = new Object[5];
			fila[0] = habitacionDTO.getNumHabitacion();
			fila[2] = habitacionDTO.getCantidadDeCamas();
			fila[3] = habitacionDTO.isHabilitado();
			if (!habitacionDTO.isHabilitado()) {
				System.out.println("Esta desahabilitado");
				if (habitacionDTO.getFechaHastaCuandoEstaDesactivado() != null) {
					fila[4] = habitacionDTO.getFechaHastaCuandoEstaDesactivado();
				} else {
					fila[4] = "Indefinido";
				}
			}

			model.addRow(fila);
		}
		table.setModel(model);
	}
}
