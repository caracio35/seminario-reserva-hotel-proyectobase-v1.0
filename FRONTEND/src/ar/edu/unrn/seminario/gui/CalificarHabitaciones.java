package ar.edu.unrn.seminario.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("unused")
public class CalificarHabitaciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTable table;
	private JTextField textFieldIdReserva;

	@SuppressWarnings("unchecked")
	public CalificarHabitaciones() {

		setTitle("Calificar Habitaciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Califique del 1 al 5:");
		lblNewLabel_1.setBounds(10, 11, 113, 14);
		contentPane.add(lblNewLabel_1);

		JFormattedTextField TextFieldComentario = new JFormattedTextField();
		TextFieldComentario.setBounds(10, 153, 410, 67);
		contentPane.add(TextFieldComentario);

		JLabel lblNewLabel_2 = new JLabel("Comentario:");
		lblNewLabel_2.setBounds(10, 129, 113, 14);
		contentPane.add(lblNewLabel_2);

		JButton btnNewButton = new JButton("Cancelar ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(232, 243, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Calificar");

		btnNewButton_1.setBounds(331, 243, 89, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_1_1 = new JLabel("Id Reserva");
		lblNewLabel_1_1.setBounds(184, 11, 113, 14);
		contentPane.add(lblNewLabel_1_1);

		textFieldIdReserva = new JTextField();
		textFieldIdReserva.setBounds(184, 33, 62, 19);
		contentPane.add(textFieldIdReserva);
		textFieldIdReserva.setColumns(10);

		@SuppressWarnings("rawtypes")
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 32, 89, 21);
		contentPane.add(comboBox);
		comboBox.addItem(1);
		comboBox.addItem(2);
		comboBox.addItem(3);
		comboBox.addItem(4);
		comboBox.addItem(5);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.getSelectedIndex();
			}
		});

	}
}
