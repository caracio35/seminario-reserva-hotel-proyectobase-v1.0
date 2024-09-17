package ar.edu.unrn.seminario.gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class BúsquedaDeHabitaciones extends JFrame {

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

	/**
	 * Create the frame.
	 */
	public BúsquedaDeHabitaciones() {
		// Configurar el JFrame
		setTitle("Búsqueda de Habitaciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 450);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 349, 502, -331);
		getContentPane().add(panel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(136, 105, 376, 233);
		getContentPane().add(scrollPane);

		table_1 = new JTable();
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

		JComboBox<String> comboBox_2_1 = new JComboBox<>();
		comboBox_2_1.setBounds(10, 314, 62, 19);
		getContentPane().add(comboBox_2_1);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(136, 346, 85, 21);
		getContentPane().add(btnNewButton);
	}
}
