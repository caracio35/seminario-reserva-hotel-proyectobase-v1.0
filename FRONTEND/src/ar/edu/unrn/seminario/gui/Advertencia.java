package ar.edu.unrn.seminario.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Advertencia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Advertencia() {
		setTitle("ADVERTENCIA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 210);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblAdvertencia = new JLabel("ADVERTENCIA");
		lblAdvertencia.setBounds(153, 26, 96, 14);
		contentPane.add(lblAdvertencia);
		
		JLabel lblTextoAdvertencia = new JLabel("Al no registrarse, usted, pierde los beneficios que obtiene al estar registrado");
		lblTextoAdvertencia.setBounds(10, 51, 379, 43);
		contentPane.add(lblTextoAdvertencia);
		
		JButton btnBotonRegistrarse = new JButton("Registrarse");
		btnBotonRegistrarse.setBounds(24, 123, 151, 23);
		contentPane.add(btnBotonRegistrarse);
		
		JButton btnBotonInvitado = new JButton("Continuar como invitado");
		btnBotonInvitado.setBounds(225, 123, 151, 23);
		contentPane.add(btnBotonInvitado);
	}

}
