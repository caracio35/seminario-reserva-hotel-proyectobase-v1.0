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

public class ListadoHabitaciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private ArrayList<HabitacionDTO> habitacionDTOs;
	private DefaultTableModel model;
	private IApi api;

	public ListadoHabitaciones(IApi api) {
		// Configurar el JFrame
		// this.habitacionDTOs = habitacionDTOs;
		this.api = api;
		setTitle("Lista de Habitaciones");
		setSize(555, 400); // Establece el tamaño del JFrame
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		getContentPane().setLayout(null); // Establecer el layout como nulo para usar coordenadas absolutas

		// Crear el JScrollPane y JTable
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 516, 133);
		getContentPane().add(scrollPane);

		// Crear el modelo de la tabla
		model = new DefaultTableModel(new Object[][] {},
				new String[] { "Numero de Habitacion", "Cama Matrim", "Cant Camas", "Disponible",
						"No disponible Hasta" }) {

			private static final long serialVersionUID = 1L;

			Class[] columnTypes = new Class[] { Object.class, Boolean.class, Object.class, Boolean.class,
					Object.class };

			public boolean isCellEditable(int row, int column) {
				// Ejemplo: Hacer que la columna 3 (Disponible) no sea editable
				return column != 3;
			}

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		// Crear la tabla utilizando el modelo corregido
		table = new JTable(model);
		this.llenarTabla();
		scrollPane.setViewportView(table);

		JButton btnActivarHabitacion = new JButton("Activar");
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
		btnActivarHabitacion.setBounds(10, 206, 85, 21);
		getContentPane().add(btnActivarHabitacion);

		JButton btnDesactivar = new JButton("Desactivar");
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
							String fechaFormateada = sdf.format(fecha);

							int response = JOptionPane.showConfirmDialog(null,
									"¿Está seguro de que desea desactivar esta habitación hasta el " + fechaFormateada
											+ "?",
									"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

							if (response == JOptionPane.YES_OPTION) {
								// Actualizar la tabla
								table.setValueAt(fechaFormateada, selectedRow, 4);
								table.setValueAt(Boolean.FALSE, selectedRow, 3); // Desactivar la habitación

								// Lógica para desactivar la habitación
								JOptionPane.showMessageDialog(null,
										"Habitación desactivada hasta " + fechaFormateada + ".");
							}
							int numHabitacionSelected = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
							api.darDeBajaHabitacion(numHabitacionSelected);
							llenarTabla();
						} else {
							JOptionPane.showMessageDialog(null, "Por favor, seleccione una fecha.");
						}

					}
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione una habitación de la tabla.");
				}
			}
		});
		btnDesactivar.setBounds(123, 206, 85, 21);
		getContentPane().add(btnDesactivar);

		JButton btnEditarHabitacion = new JButton("Editar Habitacion");
		btnEditarHabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Lógica para editar la habitación
				JOptionPane.showMessageDialog(null, "Funcionalidad de edición aún no implementada.");
			}
		});
		btnEditarHabitacion.setBounds(276, 206, 109, 21);
		getContentPane().add(btnEditarHabitacion);

		JButton btnEliminarHabitacion = new JButton("Eliminar Habitacion");
		btnEliminarHabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) { // Verificar si se ha seleccionado una fila
					int response = JOptionPane.showConfirmDialog(null,
							"¿Está seguro de que desea eliminar esta habitación?", "Confirmación",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (response == JOptionPane.YES_OPTION) {
						// Eliminar la fila de la tabla
						((DefaultTableModel) table.getModel()).removeRow(selectedRow);

						// Lógica para eliminar la habitación
						JOptionPane.showMessageDialog(null, "Habitación eliminada.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione una habitación de la tabla.");
				}
			}
		});
		btnEliminarHabitacion.setBounds(395, 206, 131, 21);
		getContentPane().add(btnEliminarHabitacion);

		JButton btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea salir?", "Confirmación",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (response == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		btnSalir.setBounds(441, 332, 85, 21);
		getContentPane().add(btnSalir);
	}

	public void llenarTabla() {

		List<HabitacionDTO> habitaciones = api.obtenerTodasLasHabitaciones();
		model.setRowCount(0);
		for (HabitacionDTO habitacionDTO : habitaciones) {
			Object[] fila = new Object[5];
			fila[0] = habitacionDTO.getNumHabitacion();
			fila[2] = habitacionDTO.getCantidadDeCamas();
			fila[3] = habitacionDTO.isHabilitado();
			model.addRow(fila);
		}
	}

}
