package ar.edu.unrn.seminario.gui;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CargarHabitacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public CargarHabitacion() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 416, 342);
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
		textFieldPrecioRegistrado.setBounds(203, 96, 150, 21);
		panel.add(textFieldPrecioRegistrado);

		TextField textFieldPrecioNoRegistrado = new TextField();
		textFieldPrecioNoRegistrado.setBounds(10, 150, 150, 21);
		panel.add(textFieldPrecioNoRegistrado);

		JButton btnSubirInformacion = new JButton("Subir Informacion");
		btnSubirInformacion.setBounds(136, 204, 144, 21);
		panel.add(btnSubirInformacion);

		JButton btnCargarImagen = new JButton("Cargar Imagen");
		btnCargarImagen.setBounds(10, 204, 116, 21);
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

		JLabel lblPrecioNoRegistrado = new JLabel("Precio No Registrado");
		lblPrecioNoRegistrado.setBounds(203, 77, 107, 13);
		panel.add(lblPrecioNoRegistrado);

		JButton btnSalircancelar = new JButton("salir/cancelar");
		btnSalircancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalircancelar.setBounds(272, 204, 144, 21);
		panel.add(btnSalircancelar);
	}
}
