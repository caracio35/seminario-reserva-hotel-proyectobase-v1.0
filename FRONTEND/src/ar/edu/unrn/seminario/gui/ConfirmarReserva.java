package ar.edu.unrn.seminario.gui;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class ConfirmarReserva extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldDNIPasaporte;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFieldUsuario;
	private JTextField textFieldFechaIngreso;
	private JTextField texFilFechaSalida;

	public ConfirmarReserva(String fechaInicio , String fechaFin , String usuario ) {
		
		System.out.println(fechaFin + fechaFin + usuario );
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 528, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 492, 398);
		contentPane.add(panel);
		panel.setLayout(null);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(10, 75, 137, 19);
		panel.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(10, 122, 137, 19);
		panel.add(textFieldApellido);
		textFieldApellido.setColumns(10);

		textFieldDNIPasaporte = new JTextField();
		textFieldDNIPasaporte.setBounds(10, 167, 137, 19);
		panel.add(textFieldDNIPasaporte);
		textFieldDNIPasaporte.setColumns(10);

		JComboBox comboBoxMetodoPago = new JComboBox();
		comboBoxMetodoPago.setBounds(10, 217, 137, 21);
		panel.add(comboBoxMetodoPago);

		JRadioButton rdbtnPrecioMinimo = new JRadioButton("Precio Minimo $");
		buttonGroup.add(rdbtnPrecioMinimo);
		rdbtnPrecioMinimo.setBounds(3, 355, 103, 21);
		panel.add(rdbtnPrecioMinimo);

		JRadioButton rdbtnPagoTotal = new JRadioButton("Pago Total $");
		buttonGroup.add(rdbtnPagoTotal);
		rdbtnPagoTotal.setBounds(108, 355, 103, 21);
		panel.add(rdbtnPagoTotal);

		JButton btnRealizarPago = new JButton("Realizar Pago");
		btnRealizarPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PagarReserva pagar=new PagarReserva();
				pagar.setVisible(true);
			}
		});
		btnRealizarPago.setBounds(312, 367, 117, 21);
		panel.add(btnRealizarPago);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(217, 367, 85, 21);
		panel.add(btnCancelar);

		JScrollPane scrollPane = new JScrollPane(); 
		scrollPane.setBounds(216, 72, 266, 252);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 60, 45, 13);
		panel.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 105, 58, 13);
		panel.add(lblApellido);

		JLabel lblDNIPasaporte = new JLabel("DNI o Pasaporte");
		lblDNIPasaporte.setBounds(10, 152, 96, 13);
		panel.add(lblDNIPasaporte);

		JLabel lblMetodoDePago = new JLabel("Metodo de Pago");
		lblMetodoDePago.setBounds(10, 197, 96, 13);
		panel.add(lblMetodoDePago);

		JLabel lblFechaIngreso = new JLabel("Fecha Ingreso");
		lblFechaIngreso.setBounds(10, 249, 96, 13);
		panel.add(lblFechaIngreso);

		JLabel lblFechaSalida = new JLabel("Fecha Salida");
		lblFechaSalida.setBounds(10, 299, 96, 13);
		panel.add(lblFechaSalida);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(10, 36, 173, 20);
		panel.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		textFieldUsuario.setText("brunohuaiquilican@hotmail.com");
		textFieldUsuario.setEditable(false);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(9, 22, 46, 14);
		panel.add(lblNewLabel);
		
		textFieldFechaIngreso = new JTextField();
		textFieldFechaIngreso.setBounds(10, 262, 137, 20);
		panel.add(textFieldFechaIngreso);
		textFieldFechaIngreso.setColumns(10);
		textFieldFechaIngreso.setText(fechaInicio);
		textFieldFechaIngreso.setEditable(false);
		
		texFilFechaSalida = new JTextField();
		texFilFechaSalida.setBounds(10, 315, 137, 20);
		panel.add(texFilFechaSalida);
		texFilFechaSalida.setColumns(10);
		texFilFechaSalida.setText(fechaFin);
		texFilFechaSalida.setEditable(false);
		
		
	}
}
