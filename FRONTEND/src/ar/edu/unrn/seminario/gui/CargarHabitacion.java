package ar.edu.unrn.seminario.gui;

import java.awt.Component;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import ar.edu.unrn.seminario.exception.EnterosEnCero;
import ar.edu.unrn.seminario.exception.PrecioCero;

public class CargarHabitacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private IApi api;
	private JTable table;
	private DefaultTableModel modelo;
	private List<CaracteristicaEspecialDTO> caracteristicasEspeciales;

	public CargarHabitacion(IApi api) {
		try {

			this.api = api;
			this.caracteristicasEspeciales = api.obtenerCaracteristica();
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 450, 420);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);

			JPanel panel = new JPanel();
			panel.setBounds(10, 10, 416, 363);
			contentPane.add(panel);
			panel.setLayout(null);

			TextField textFieldNumeroHabitacion = new TextField();
			textFieldNumeroHabitacion.setBounds(10, 39, 150, 21);
			panel.add(textFieldNumeroHabitacion);

			TextField textFieldCamas = new TextField();
			textFieldCamas.setBounds(203, 39, 150, 21);
			panel.add(textFieldCamas);

			TextField textFieldDescripccion = new TextField();
			textFieldDescripccion.setBounds(10, 96, 150, 21);
			panel.add(textFieldDescripccion);

			TextField textFieldPrecioRegistrado = new TextField();
			textFieldPrecioRegistrado.setBounds(10, 150, 150, 21);
			panel.add(textFieldPrecioRegistrado);

			JLabel lblNewLabel = new JLabel("Estado De Habitacion");
			lblNewLabel.setBounds(202, 76, 151, 14);
			panel.add(lblNewLabel);

			JRadioButton buttonDesabilitado = new JRadioButton("Desabilitado");
			buttonDesabilitado.setBounds(272, 96, 109, 23);
			panel.add(buttonDesabilitado);

			JRadioButton buttonHabilitado = new JRadioButton("Habilitado");
			buttonHabilitado.setBounds(188, 96, 109, 23);
			panel.add(buttonHabilitado);

			ButtonGroup group = new ButtonGroup();
			group.add(buttonDesabilitado);
			group.add(buttonHabilitado);

			JButton btnSubirInformacion = new JButton("Subir Informacion");
			btnSubirInformacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					;
					try {
						boolean habilitado = false;
						if (buttonHabilitado.isSelected()) {
							habilitado = true;
						} else if (buttonDesabilitado.isSelected()) {
							habilitado = false;
						}

						HabitacionDTO habitacionDTO = new HabitacionDTO(Integer.parseInt(textFieldCamas.getText()),
								textFieldDescripccion.getText(),
								Double.parseDouble(textFieldPrecioRegistrado.getText()),
								habilitado, Integer.parseInt(textFieldNumeroHabitacion.getText()), null);
						api.crearHabitacion(habitacionDTO);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Los campos no pueden ser cero o negativos");
					} catch (CampoVacioExeption e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					} catch (EnterosEnCero e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					} catch (PrecioCero e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				}
			}

			);
			btnSubirInformacion.setBounds(126, 287, 144, 21);
			panel.add(btnSubirInformacion);

			JButton btnCargarImagen = new JButton("Cargar Imagen");
			btnCargarImagen.setBounds(10, 287, 116, 21);
			panel.add(btnCargarImagen);

			JLabel lblNumeroHabitacion = new JLabel("Numero Habitacion");
			lblNumeroHabitacion.setBounds(10, 20, 130, 13);
			panel.add(lblNumeroHabitacion);

			JLabel lblCamas = new JLabel("Camas");
			lblCamas.setBounds(203, 20, 45, 13);
			panel.add(lblCamas);

			JLabel lblDescripcion = new JLabel("Descripccion");
			lblDescripcion.setBounds(10, 77, 107, 13);
			panel.add(lblDescripcion);

			JLabel lblPrecioRegistrado = new JLabel("Precio Registrado");
			lblPrecioRegistrado.setBounds(10, 131, 106, 13);
			panel.add(lblPrecioRegistrado);

			JButton btnSalircancelar = new JButton("salir/cancelar");
			btnSalircancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnSalircancelar.setBounds(272, 287, 144, 21);
			panel.add(btnSalircancelar);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(203, 150, 172, 123);
			panel.add(scrollPane);

			modelo = new DefaultTableModel(new Object[][] {}, new String[] { "Caracteristica", "Estado" });
			table = new JTable(modelo);
			scrollPane.setViewportView(table); // Establecer la vista de la tabla correctamente
			this.cargarCaracteristicaEspecial();
			table.getColumnModel().getColumn(1).setCellRenderer(new CheckBoxRenderer());
			table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JCheckBox()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al inicializar: " + e.getMessage());
		}
	}

	/**
	 * @param modelo
	 * @wbp.parser.constructor
	 */
	public CargarHabitacion(IApi api, HabitacionDTO habitacionDTO) {
		this.api = api;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 416, 363);
		contentPane.add(panel);
		panel.setLayout(null);

		TextField textFieldNumeroHabitacion = new TextField();
		textFieldNumeroHabitacion.setBounds(10, 39, 150, 21);
		panel.add(textFieldNumeroHabitacion);
		textFieldNumeroHabitacion.setText(String.valueOf(habitacionDTO.getNumHabitacion()));

		TextField textFieldCamas = new TextField();
		textFieldCamas.setBounds(203, 39, 150, 21);
		panel.add(textFieldCamas);
		textFieldCamas.setText(String.valueOf(habitacionDTO.getCantidadDeCamas()));

		TextField textFieldDescripccion = new TextField();
		textFieldDescripccion.setBounds(10, 96, 150, 21);
		panel.add(textFieldDescripccion);
		textFieldDescripccion.setText(habitacionDTO.getDescripcion());

		TextField textFieldPrecioRegistrado = new TextField();
		textFieldPrecioRegistrado.setBounds(10, 150, 150, 21);
		panel.add(textFieldPrecioRegistrado);
		textFieldPrecioRegistrado.setText(String.valueOf(habitacionDTO.getPrecio()));

		JLabel lblNewLabel = new JLabel("Estado De Habitacion");
		lblNewLabel.setBounds(202, 76, 151, 14);
		panel.add(lblNewLabel);

		JRadioButton buttonDesabilitado = new JRadioButton("Desabilitado");
		buttonDesabilitado.setBounds(272, 96, 109, 23);
		panel.add(buttonDesabilitado);

		JRadioButton buttonHabilitado = new JRadioButton("Habilitado");
		buttonHabilitado.setBounds(188, 96, 109, 23);
		panel.add(buttonHabilitado);

		ButtonGroup group = new ButtonGroup();
		group.add(buttonDesabilitado);
		group.add(buttonHabilitado);

		if (habitacionDTO.isHabilitado())
			buttonHabilitado.setSelected(true);
		else
			buttonDesabilitado.setSelected(false);

		JButton btnSubirInformacion = new JButton("Subir Informacion");
		btnSubirInformacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				;
				try {
					boolean habilitado = false;
					if (buttonHabilitado.isSelected()) {
						habilitado = true;
					} else if (buttonDesabilitado.isSelected()) {
						habilitado = false;
					}

					HabitacionDTO habitacionDTO = new HabitacionDTO(Integer.parseInt(textFieldCamas.getText()),
							textFieldDescripccion.getText(), Double.parseDouble(textFieldPrecioRegistrado.getText()),
							habilitado, Integer.parseInt(textFieldNumeroHabitacion.getText()), null);
					api.crearHabitacion(habitacionDTO);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Los campos no pueden ser cero o negativos");
				} catch (CampoVacioExeption e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (EnterosEnCero e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (PrecioCero e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		}

		);
		btnSubirInformacion.setBounds(126, 287, 144, 21);
		panel.add(btnSubirInformacion);

		JButton btnCargarImagen = new JButton("Cargar Imagen");
		btnCargarImagen.setBounds(10, 287, 116, 21);
		panel.add(btnCargarImagen);

		JLabel lblNumeroHabitacion = new JLabel("Numero Habitacion");
		lblNumeroHabitacion.setBounds(10, 20, 130, 13);
		panel.add(lblNumeroHabitacion);

		JLabel lblCamas = new JLabel("Camas");
		lblCamas.setBounds(203, 20, 45, 13);
		panel.add(lblCamas);

		JLabel lblDescripcion = new JLabel("Descripccion");
		lblDescripcion.setBounds(10, 77, 107, 13);
		panel.add(lblDescripcion);

		JLabel lblPrecioRegistrado = new JLabel("Precio Registrado");
		lblPrecioRegistrado.setBounds(10, 131, 106, 13);
		panel.add(lblPrecioRegistrado);

		JButton btnSalircancelar = new JButton("salir/cancelar");
		btnSalircancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalircancelar.setBounds(272, 287, 144, 21);
		panel.add(btnSalircancelar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(203, 150, 172, 123);
		panel.add(scrollPane);

		modelo = new DefaultTableModel(new Object[][] {}, new String[] { "Caracteristica", "Estado" });
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		this.cargarCaracteristicaEspecial();
		table.getColumnModel().getColumn(1).setCellRenderer(new CheckBoxRenderer());
		table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JCheckBox()));

	}

	private void cargarCaracteristicaEspecial() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		for (CaracteristicaEspecialDTO caracteristicaEspecialDTO : caracteristicasEspeciales) {
			Object[] fila = new Object[2];
			fila[0] = caracteristicaEspecialDTO.getNombre();
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
