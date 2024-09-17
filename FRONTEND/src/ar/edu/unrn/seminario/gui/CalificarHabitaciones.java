package ar.edu.unrn.seminario.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class CalificarHabitaciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public CalificarHabitaciones() {
		setTitle("Calificar Habitaciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Selecione Habitacion:");
		lblNewLabel.setBounds(10, 21, 113, 14);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 39, 154, 22);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Califique del 1 al 5:");
		lblNewLabel_1.setBounds(189, 21, 113, 14);
		contentPane.add(lblNewLabel_1);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(189, 40, 154, 20);
		contentPane.add(formattedTextField);
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setBounds(10, 109, 410, 123);
		contentPane.add(formattedTextField_1);
		
		JLabel lblNewLabel_2 = new JLabel("Comentario:");
		lblNewLabel_2.setBounds(10, 84, 113, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Cancelar ");
		btnNewButton.setBounds(232, 243, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Calificar");
		btnNewButton_1.setBounds(331, 243, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
