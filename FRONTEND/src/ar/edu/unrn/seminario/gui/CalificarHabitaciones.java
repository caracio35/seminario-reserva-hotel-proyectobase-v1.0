package ar.edu.unrn.seminario.gui;

import java.util.Locale;
import java.util.ResourceBundle;

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
import ar.edu.unrn.seminario.exception.CampoVacioExeption;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.EnterosEnCeroExeption;
import ar.edu.unrn.seminario.exception.PrecioCeroExeption;

@SuppressWarnings("unused")
public class CalificarHabitaciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private Locale idiomaSeleccionado;
	private ResourceBundle recursos;
	
	
	@SuppressWarnings("unchecked")
	public CalificarHabitaciones(IApi api , int idReserva, Locale idioma) {
		this.idiomaSeleccionado = idioma;
		this.recursos = ResourceBundle.getBundle("labels", idioma);
		
		setTitle(recursos.getString("calificarHabitacion.titulo") );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 255);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel(recursos.getString("calificarHabitacion.calificar"));
		lblNewLabel_1.setBounds(10, 11, 113, 14);
		contentPane.add(lblNewLabel_1);

		JFormattedTextField TextFieldComentario = new JFormattedTextField();
		TextFieldComentario.setBounds(10, 89, 410, 84);
		contentPane.add(TextFieldComentario);

		JLabel lblNewLabel_2 = new JLabel(recursos.getString("calificarHabitacion.comentario"));
		lblNewLabel_2.setBounds(10, 64, 113, 14);
		contentPane.add(lblNewLabel_2);

		JButton btnNewButton = new JButton(recursos.getString("calificarHabitacion.salir"));
		btnNewButton.addActionListener((e) -> {
			dispose();
			  try {
	                VerReservas misReservas = new VerReservas(api, idioma);
	                misReservas.setVisible(true);
	            } catch (CampoVacioExeption | EnterosEnCeroExeption | PrecioCeroExeption e1) {
	                JOptionPane.showMessageDialog(null, e1.getMessage());
	            }
		});
		btnNewButton.setBounds(222, 184, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton(recursos.getString("calificarHabitacion.generarCalificacion"));

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
				JOptionPane.showMessageDialog(contentPane, recursos.getString("calificarHabitacion.confirmacionCalificacion"));
				dispose();
				  try {
		                VerReservas misReservas = new VerReservas(api, idioma);
		                misReservas.setVisible(true);
		            } catch (CampoVacioExeption | EnterosEnCeroExeption | PrecioCeroExeption e1) {
		                JOptionPane.showMessageDialog(null, e1.getMessage());
		            }
			} catch (ConexionFallidaExeption e1) {
				JOptionPane.showConfirmDialog(null, e1.getMessage());
			}
		});
	}
}
