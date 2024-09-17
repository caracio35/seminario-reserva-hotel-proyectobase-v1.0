package ar.edu.unrn.seminario.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	public Login() {
		setTitle("Login Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 293, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblTitulo = new JLabel("Loguin Usuario");
		lblTitulo.setBounds(100, 11, 90, 14);
		contentPane.add(lblTitulo);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(30, 41, 49, 14);
		contentPane.add(lblUsuario);
		
		textField = new JTextField();
		textField.setBounds(30, 67, 219, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(30, 98, 49, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(30, 123, 219, 20);
		contentPane.add(passwordField);
		
		JButton btnBotonIniciar = new JButton("Iniciar");
		btnBotonIniciar.setBounds(101, 176, 89, 23);
		contentPane.add(btnBotonIniciar);
		
		JButton btnBotonOlvideContraseña = new JButton("Olvide mi contraseña");
		btnBotonOlvideContraseña.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBotonOlvideContraseña.setBounds(74, 227, 137, 23);
		contentPane.add(btnBotonOlvideContraseña);
		
		JButton btnBotonCrearUsuario = new JButton("Crear Usuario");
		btnBotonCrearUsuario.setBounds(10, 306, 117, 23);
		contentPane.add(btnBotonCrearUsuario);
		
		JButton btnBotonInvitado = new JButton("Iniciar como invitado");
		btnBotonInvitado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Advertencia a = new Advertencia();
				a.setVisible(true);
			}
		});
		btnBotonInvitado.setBounds(136, 306, 143, 23);
		contentPane.add(btnBotonInvitado);
	}
}
