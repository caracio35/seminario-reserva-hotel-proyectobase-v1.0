package ar.edu.unrn.seminario.gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;

@SuppressWarnings("unused")
public class CalificarHabitaciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	
	@SuppressWarnings("unchecked")
	public CalificarHabitaciones(IApi api , int idReserva ) {

		setTitle("Calificar Habitaciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 255);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Califique del 1 al 5:");
		lblNewLabel_1.setBounds(10, 11, 113, 14);
		contentPane.add(lblNewLabel_1);

		JFormattedTextField TextFieldComentario = new JFormattedTextField();
		TextFieldComentario.setBounds(10, 89, 410, 84);
		contentPane.add(TextFieldComentario);

		JLabel lblNewLabel_2 = new JLabel("Comentario:");
		lblNewLabel_2.setBounds(10, 64, 113, 14);
		contentPane.add(lblNewLabel_2);

		JButton btnNewButton = new JButton("Cancelar ");
		btnNewButton.addActionListener((e) -> {
			dispose();
		});
		btnNewButton.setBounds(222, 184, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Calificar");

		btnNewButton_1.setBounds(331, 184, 89, 23);
		contentPane.add(btnNewButton_1);

		@SuppressWarnings("rawtypes")
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 32, 113, 21);
		contentPane.add(comboBox);
		comboBox.addItem(1);
		comboBox.addItem(2);
		comboBox.addItem(3);
		comboBox.addItem(4);
		comboBox.addItem(5);
		btnNewButton_1.addActionListener(e -> {
			 
			
		    int calificacionSeleccionada = (int) comboBox.getSelectedItem();
		   
		    String comentario = TextFieldComentario.getText();
		    
		    try {
				api.generarCalificacionHabitacion(idReserva, calificacionSeleccionada, comentario);
				JOptionPane.showMessageDialog(contentPane, "Reserva calificada");
				dispose();
			} catch (ConexionFallidaExeption e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
}
