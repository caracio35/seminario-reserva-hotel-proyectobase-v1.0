package ar.edu.unrn.seminario.gui;

import java.awt.Component;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.CaracteristicaEspecialDTO;
import ar.edu.unrn.seminario.dto.HabitacionDTO;
import ar.edu.unrn.seminario.exception.CampoVacioExeption;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.DuplicadaExeption;
import ar.edu.unrn.seminario.exception.EnterosEnCeroExeption;
import ar.edu.unrn.seminario.exception.ErrorConsultaExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import ar.edu.unrn.seminario.exception.NumeroHabitacionExistenteException;
import ar.edu.unrn.seminario.exception.PrecioCeroExeption;

public class CargarHabitacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private IApi api;
	private JTable table;
	private DefaultTableModel modelo;
	private List<CaracteristicaEspecialDTO> caracteristicasEspeciales;
	private TextField textFieldNumeroHabitacion = new TextField();
	private TextField textFieldCamas = new TextField();
	private TextField textFieldDescripccion = new TextField();
	private TextField textFieldPrecioRegistrado = new TextField();
	private JScrollPane scrollPane = new JScrollPane();
	private ButtonGroup group = new ButtonGroup();
	private JRadioButton buttonDesabilitado = new JRadioButton("Desabilitado");
	private JRadioButton buttonHabilitado = new JRadioButton("Habilitado");
	private boolean modificar;

	public CargarHabitacion(IApi api, boolean modificar) {
		try {

			this.api = api;
			this.caracteristicasEspeciales = api.obtenerCaracteristica();
			this.modificar = modificar;
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 495, 420);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);

			JPanel panel = new JPanel();
			panel.setBounds(10, 10, 465, 363);
			contentPane.add(panel);
			panel.setLayout(null);

			textFieldNumeroHabitacion.setBounds(10, 39, 150, 21);
			panel.add(textFieldNumeroHabitacion);
			if (modificar == false) {
				textFieldNumeroHabitacion.setEditable(true);
			} else {
				textFieldNumeroHabitacion.setEditable(true);
			}
			textFieldCamas.setBounds(10, 215, 150, 21);
			panel.add(textFieldCamas);

			textFieldDescripccion.setBounds(10, 96, 150, 21);
			panel.add(textFieldDescripccion);

			textFieldPrecioRegistrado.setBounds(10, 150, 150, 21);
			panel.add(textFieldPrecioRegistrado);

			JLabel lblNewLabel = new JLabel("Estado De Habitacion");
			lblNewLabel.setBounds(203, 19, 151, 14);
			panel.add(lblNewLabel);

			buttonDesabilitado.setBounds(314, 37, 109, 23);
			panel.add(buttonDesabilitado);

			// setear el boton en true por defecto
			buttonHabilitado.setSelected(true);
			buttonHabilitado.setBounds(203, 39, 109, 23);
			panel.add(buttonHabilitado);

			group.add(buttonDesabilitado);
			group.add(buttonHabilitado);
			
			JButton btnSubirInformacion = new JButton("Subir Informacion");
			btnSubirInformacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					boolean habilitado = false;
					if (buttonHabilitado.isSelected()) {
						habilitado = true;
					} else if (buttonDesabilitado.isSelected()) {
						habilitado = false;
					}
					List<String> caracteristicasSeleccionadas = new ArrayList<>();
					for (int i = 0; i < table.getRowCount(); i++) {
						String nombreCaracteristica = (String) table.getValueAt(i, 0);
						Boolean estado = (Boolean) table.getValueAt(i, 1);
						if (estado != null && estado) {
							caracteristicasSeleccionadas.add(nombreCaracteristica);
						}
					}

					String[] caracteristicas = caracteristicasSeleccionadas.toArray(new String[0]);

					if (modificar == false) {
						try {
							
							api.darDeAltaHabitacion(Integer.parseInt(textFieldCamas.getText()),
									textFieldDescripccion.getText(),
									Double.parseDouble(textFieldPrecioRegistrado.getText()), habilitado,
									Integer.parseInt(textFieldNumeroHabitacion.getText()), caracteristicas);
							dispose();
							JOptionPane.showMessageDialog(null, "habitacion agregada con exito");
						} catch (NumeroHabitacionExistenteException | ConexionFallidaExeption | EnterosEnCeroExeption
								| CampoVacioExeption | PrecioCeroExeption | DuplicadaExeption e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());

						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null,
									"Revisar los campos Numero de habitacion Precio o Cant camas no puede ser campo vacio o no estar definido y tampoco puede ser una letra ");
						} catch (ErrorConsultaExeption e1) {
							// TODO Auto-generated catch block
							JOptionPane.showConfirmDialog(null, e1.getMessage());
						}
					} else {
						// Dentro del ActionListener del botón btnSubirInformacion
						if (modificar == true) {
							try {
								api.modificarHabitacion(Integer.parseInt(textFieldCamas.getText()),
										textFieldDescripccion.getText(),
										Double.parseDouble(textFieldPrecioRegistrado.getText()),
										habilitado,
										Integer.parseInt(textFieldNumeroHabitacion.getText()),
										caracteristicas);

								// Mostrar mensaje de éxito
								JOptionPane.showMessageDialog(null,
										"Habitación modificada exitosamente",
										"Éxito",
										JOptionPane.INFORMATION_MESSAGE);

								// Cerrar la ventana actual
								dispose();

								// Actualizar la lista de habitaciones
								// Opción 1: Actualizar la ventana principal
								((ListadoHabitaciones) getParent()).llenarTabla();

								// Opción 2: Si no tienes acceso directo a ListadoHabitaciones
								// puedes crear una nueva instancia
								// ListadoHabitaciones listado = new ListadoHabitaciones(api);
								// listado.setVisible(true);

							} catch (NumberFormatException | ConexionFallidaExeption | DuplicadaExeption
									| NumeroHabitacionExistenteException | CampoVacioExeption | EnterosEnCeroExeption
									| PrecioCeroExeption | ErrorDatosNoEncontradosExeption e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage());
							}
						}
					}
				}

				private List<CaracteristicaEspecialDTO> obtenerListaCaracteristicas() {
					List<CaracteristicaEspecialDTO> caracteristicas = new ArrayList<>();
					List<String> nombresCaracteristicas = new ArrayList<>();
					for (int i = 0; i < table.getRowCount(); i++) {
						String nombreCaracteristica = (String) table.getValueAt(i, 0);
						Boolean estado = (Boolean) table.getValueAt(i, 1);
						if (estado != null && estado) {
							nombresCaracteristicas.add(nombreCaracteristica);
						}
					}
					caracteristicas = api.obtenerCaracteristica(nombresCaracteristicas);
					return caracteristicas;
				}
			}

			);
			btnSubirInformacion.setBounds(160, 311, 144, 21);
			panel.add(btnSubirInformacion);

			JButton btnCargarImagen = new JButton("Cargar Imagen");
			btnCargarImagen.setBounds(10, 268, 116, 21);
			panel.add(btnCargarImagen);

			JLabel lblNumeroHabitacion = new JLabel("Numero Habitacion");
			lblNumeroHabitacion.setBounds(10, 20, 130, 13);
			panel.add(lblNumeroHabitacion);

			JLabel lblCamas = new JLabel("Camas");
			lblCamas.setBounds(10, 196, 45, 13);
			panel.add(lblCamas);

			JLabel lblDescripcion = new JLabel("Descripccion");
			lblDescripcion.setBounds(10, 77, 107, 13);
			panel.add(lblDescripcion);

			JLabel lblPrecioRegistrado = new JLabel("Precio Registrado");
			lblPrecioRegistrado.setBounds(10, 131, 106, 13);
			panel.add(lblPrecioRegistrado);

			JButton btnSalircancelar = new JButton("Salir");
			btnSalircancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					api.resetearMemoria();
					dispose();
				}
			});
			btnSalircancelar.setBounds(314, 311, 144, 21);
			panel.add(btnSalircancelar);

			scrollPane.setBounds(203, 69, 239, 207);
			panel.add(scrollPane);

			table = new JTable(new DefaultTableModel(new Object[][] {}, new String[] { "Habitación", "Seleccionar" }) {
				public boolean isCellEditable(int row, int column) {
					return column == 1; // Permitir editar solo la columna de los checkboxes
				}

				public Class<?> getColumnClass(int columnIndex) {
					if (columnIndex == 1) {
						return Boolean.class; // Hacer que la segunda columna sea de tipo Boolean
					}
					return String.class; // La primera columna es de tipo String para el nombre de la habitación
				}
			});
			this.cargarCaracteristicaEspecial();
			table.getColumnModel().getColumn(1).setCellRenderer(new CheckBoxRenderer());
			table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JCheckBox()));
			cargarHabitacionParaModificar();
		} catch (Exception e) {
			System.out.println("aca " + e.getStackTrace() + e.getMessage());
		}
	}

	private void cargarHabitacionParaModificar() {

		if (api.modificamosHabitacion()) {
			HabitacionDTO hDTO = api.dameLaHabitacion();
			textFieldNumeroHabitacion.setText(String.valueOf(hDTO.getNumHabitacion()));
			textFieldDescripccion.setText(hDTO.getDescripcion());
			textFieldPrecioRegistrado.setText(String.valueOf(hDTO.getPrecio()));
			textFieldCamas.setText(String.valueOf(hDTO.getCantidadDeCamas()));
			if (hDTO.isHabilitado())
				buttonHabilitado.setSelected(true);
			else
				buttonDesabilitado.setSelected(false);
			cargarCaracteristica(hDTO);
		}

	}

	private void cargarCaracteristicaEspecial() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		for (CaracteristicaEspecialDTO caracteristicaEspecialDTO : this.caracteristicasEspeciales) {
			Object[] fila = new Object[2];
			fila[0] = caracteristicaEspecialDTO.getNombre();
			modelo.addRow(fila);
		}
	}

	private void cargarCaracteristica(HabitacionDTO h) {
		List<CaracteristicaEspecialDTO> car = h.getCaracteristicasEspeciale();
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);

		for (CaracteristicaEspecialDTO caracteristicaEspecialDTO : this.caracteristicasEspeciales) {
			Object[] fila = new Object[2];
			fila[0] = caracteristicaEspecialDTO.getNombre();

			boolean activada = false;

			for (CaracteristicaEspecialDTO carHab : car) {
				if (carHab.getNombre().equals(caracteristicaEspecialDTO.getNombre())) {
					activada = true;
				}
			}

			fila[1] = activada;
			modelo.addRow(fila);
		}
	}

	private class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (value instanceof Boolean) {
				this.setSelected((Boolean) value);
			}
			return this;
		}
	}

}
