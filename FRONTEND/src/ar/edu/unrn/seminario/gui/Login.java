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
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldUsuario;
    private JPasswordField passwordField;
    private IApi api;
    private Locale idiomaSeleccionado;
    private ResourceBundle recursos;

    public Login(IApi api, Locale idioma) {
        this.api = api;
        this.idiomaSeleccionado = idioma;
        this.recursos = ResourceBundle.getBundle("labels", idioma);

        setTitle(recursos.getString("login.titulo"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 293, 379);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel(recursos.getString("login.titulo"));
        lblTitulo.setBounds(100, 11, 90, 14);
        contentPane.add(lblTitulo);

        JLabel lblUsuario = new JLabel(recursos.getString("login.usuario"));
        lblUsuario.setBounds(30, 41, 49, 14);
        contentPane.add(lblUsuario);

        textFieldUsuario = new JTextField();
        textFieldUsuario.setBounds(30, 67, 219, 20);
        contentPane.add(textFieldUsuario);
        textFieldUsuario.setColumns(10);

        JLabel lblPassword = new JLabel(recursos.getString("login.password"));
        lblPassword.setBounds(30, 98, 49, 14);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(30, 123, 219, 20);
        contentPane.add(passwordField);

        JButton btnBotonIniciar = new JButton(recursos.getString("login.iniciar"));
        btnBotonIniciar.addActionListener(e -> {
            try {
                boolean iniciado = api.iniciarSesion(textFieldUsuario.getText(), passwordField.getText());
                if (iniciado) {
                    JOptionPane.showMessageDialog(null, recursos.getString("login.usuarioIniciado"));
                    VentanaPrincipal v1 = new VentanaPrincipal(api , idiomaSeleccionado);
                    v1.setVisible(true);
                    Login.this.dispose();
                } else {
                    throw new ErrorDatosNoEncontradosExeption(recursos.getString("login.error"));
                }
            } catch (ConexionFallidaExeption | ErrorDatosNoEncontradosExeption e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
            }
        });
        btnBotonIniciar.setBounds(101, 176, 89, 23);
        contentPane.add(btnBotonIniciar);

        JButton btnBotonOlvideContraseña = new JButton(recursos.getString("login.olvidarContraseña"));
        btnBotonOlvideContraseña.addActionListener(e -> {});
        btnBotonOlvideContraseña.setBounds(74, 227, 137, 23);
        contentPane.add(btnBotonOlvideContraseña);

        JButton btnBotonCrearUsuario = new JButton(recursos.getString("login.crearUsuario"));
        btnBotonCrearUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	AltaUsuario alta = new AltaUsuario(api);
            	alta.setVisible(true);
                //JOptionPane.showMessageDialog(null, recursos.getString("login.faltaCrearUsuario"));
            }
        });
        btnBotonCrearUsuario.setBounds(10, 306, 117, 23);
        contentPane.add(btnBotonCrearUsuario);

        JButton btnBotonInvitado = new JButton(recursos.getString("login.iniciarInvitado"));
        btnBotonInvitado.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, recursos.getString("login.confirmarInvitado"),
                    recursos.getString("login.perdidaBeneficios"), JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (response == 0) {
                JOptionPane.showMessageDialog(null, recursos.getString("login.usuarioInvitado"));
                VentanaPrincipal v1 = new VentanaPrincipal(api , idiomaSeleccionado);
                v1.setVisible(true);
                Login.this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, recursos.getString("login.usuarioNoInvitado"));
            }
        });
        btnBotonInvitado.setBounds(136, 306, 143, 23);
        contentPane.add(btnBotonInvitado);
    }
}
