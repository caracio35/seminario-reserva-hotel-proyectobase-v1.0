package ar.edu.unrn.seminario.gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.HabitacionDTO;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

public class BusquedaDeHabitaciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JScrollPane scrollPane_1;
	private JLabel lblNewLabel_6;
	private JTable table;
	private JTable table_1;
	private JTextField textFieldHuespedes;
	private JButton btnCancelarsalir;
	private DefaultTableModel modelo;
	private IApi api ; 
	/**
	 * Create the frame.
	 */
	public BusquedaDeHabitaciones(IApi api) {
		this.api = api ; 
		// Configurar el JFrame
		setTitle("Búsqueda de Habitaciones");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 450);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 349, 502, -331);
		getContentPane().add(panel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(136, 105, 376, 233);
		getContentPane().add(scrollPane);

		modelo = new DefaultTableModel(new Object[][] {}, new String[]{"Camas", "Descripcion", "Precio" , "Numero de Habitacion", "Caracteristicas" });
		table_1 = new JTable(modelo);
		scrollPane.setViewportView(table_1);
		this.cargarHabitaciones();
		
		
		scrollPane.setViewportView(table_1);

		textField = new JTextField();
		textField.setBounds(136, 23, 134, 19);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(10, 262, 96, 19);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(10, 218, 96, 19);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel = new JLabel("Fecha Inicio");
		lblNewLabel.setBounds(10, 197, 85, 13);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Fecha Fin");
		lblNewLabel_1.setBounds(10, 247, 45, 13);
		getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Total Huéspedes");
		lblNewLabel_2.setBounds(10, 291, 96, 13);
		getContentPane().add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Buscar");
		lblNewLabel_3.setBounds(136, 5, 45, 13);
		getContentPane().add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Descripción");
		lblNewLabel_4.setBounds(10, 5, 85, 13);
		getContentPane().add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("Calificación");
		lblNewLabel_5.setBounds(10, 105, 85, 13);
		getContentPane().add(lblNewLabel_5);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 28, 116, 71);
		getContentPane().add(scrollPane_1);

		table = new JTable();
		scrollPane_1.setViewportView(table);

		lblNewLabel_6 = new JLabel("Precio");
		lblNewLabel_6.setBounds(10, 149, 45, 13);
		getContentPane().add(lblNewLabel_6);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(10, 120, 62, 19);
		getContentPane().add(comboBox);

		JComboBox<String> comboBox_2 = new JComboBox<>();
		comboBox_2.setBounds(10, 172, 62, 19);
		getContentPane().add(comboBox_2);

		JButton btnReservar = new JButton("RESERVAR");
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmarReserva confirmacion=new ConfirmarReserva();
				confirmacion.setVisible(true);
			}
		});
		btnReservar.setBounds(136, 346, 116, 21);
		getContentPane().add(btnReservar);
		
		textFieldHuespedes = new JTextField();
		textFieldHuespedes.setColumns(10);
		textFieldHuespedes.setBounds(10, 314, 96, 19);
		getContentPane().add(textFieldHuespedes);
		
		btnCancelarsalir = new JButton("CANCELAR/SALIR");
		btnCancelarsalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelarsalir.setBounds(381, 346, 116, 21);
		getContentPane().add(btnCancelarsalir);
	}
	public void cargarHabitaciones() {
	    List<HabitacionDTO> habitaciones = api.obtenerHabitacionesHabilitada();

	    modelo.setRowCount(0);
	    
	    for (HabitacionDTO habitacion : habitaciones) {
	        modelo.addRow(new Object[]{
	            habitacion.getCantidadDeCamas(),
	            habitacion.getDescripcion(),
	            habitacion.getPrecio(),
	            habitacion.getNumHabitacion(),
	            habitacion.getCaracteristicasEspeciale()
	        });
	    }
	}
	
}

