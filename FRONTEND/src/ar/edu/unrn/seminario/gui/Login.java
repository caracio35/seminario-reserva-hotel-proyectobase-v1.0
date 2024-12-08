package ar.edu.unrn.seminario.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;
	private IApi api;

	public Login(IApi api) {
		this.api = api;
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

		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(30, 67, 219, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(30, 98, 49, 14);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(30, 123, 219, 20);
		contentPane.add(passwordField);

		JButton btnBotonIniciar = new JButton("Iniciar");
		btnBotonIniciar.addActionListener(e -> {
			try {
				boolean iniciado = api.iniciarSesion(textFieldUsuario.getText(), passwordField.getText());
				if (iniciado) {
					JOptionPane.showMessageDialog(null, "Usuario iniciado");
					VentanaPrincipal v1 = new VentanaPrincipal(api);
					v1.setVisible(true);
					Login.this.dispose();
				
					
				}
				else {
					throw new ErrorDatosNoEncontradosExeption("Usuario o contraseña incorrectos");}
				
				
			} catch (ConexionFallidaExeption | ErrorDatosNoEncontradosExeption e1) { 
				
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
	
		});
		btnBotonIniciar.setBounds(101, 176, 89, 23);
		contentPane.add(btnBotonIniciar);

		JButton btnBotonOlvideContraseña = new JButton("Olvide mi contraseña");
		btnBotonOlvideContraseña.addActionListener(e -> {
		});
		btnBotonOlvideContraseña.setBounds(74, 227, 137, 23);
		contentPane.add(btnBotonOlvideContraseña);

		JButton btnBotonCrearUsuario = new JButton("Crear Usuario");
		btnBotonCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "FALTA CREAR EL DAO PARA BUSCAR ROL");
				/*AltaUsuario aU = new AltaUsuario(api);
				aU.setVisible(true);
				Login.this.dispose();*/
			}
		});
		btnBotonCrearUsuario.setBounds(10, 306, 117, 23);
		contentPane.add(btnBotonCrearUsuario);

		JButton btnBotonInvitado = new JButton("Iniciar como invitado");
		btnBotonInvitado.addActionListener(e -> {
			int response = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea iniciar como invitado?",
					"Usted perdera los beneficios de estar registrado", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (response == 0) {
				JOptionPane.showMessageDialog(null, "Usuario iniciado como invitado");
				VentanaPrincipal v1 = new VentanaPrincipal(api);
				v1.setVisible(true);
				Login.this.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Usuario no iniciado como invitado");
			}

			/*
			 * if (response == JOptionPane.YES_OPTION) { // Lógica para activar la
			 * habitación JOptionPane.showMessageDialog(null, "Habitación activada."); }
			 */
		});
		btnBotonInvitado.setBounds(136, 306, 143, 23);
		contentPane.add(btnBotonInvitado);
	}
}
