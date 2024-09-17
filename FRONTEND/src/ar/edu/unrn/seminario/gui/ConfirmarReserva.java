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

public class ConfirmarReserva extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldDNIPasaporte;
	private JTextField textFieldFechaIngreso;
	private JTextField textFieldFechaSalida;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public ConfirmarReserva() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 416, 398);
		contentPane.add(panel);
		panel.setLayout(null);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(10, 57, 96, 19);
		panel.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(10, 101, 96, 19);
		panel.add(textFieldApellido);
		textFieldApellido.setColumns(10);

		textFieldDNIPasaporte = new JTextField();
		textFieldDNIPasaporte.setBounds(10, 146, 96, 19);
		panel.add(textFieldDNIPasaporte);
		textFieldDNIPasaporte.setColumns(10);

		JComboBox comboBoxMetodoPago = new JComboBox();
		comboBoxMetodoPago.setBounds(10, 200, 96, 21);
		panel.add(comboBoxMetodoPago);

		JRadioButton rdbtnPrecioMinimo = new JRadioButton("Precio Minimo $");
		buttonGroup.add(rdbtnPrecioMinimo);
		rdbtnPrecioMinimo.setBounds(0, 331, 103, 21);
		panel.add(rdbtnPrecioMinimo);

		JRadioButton rdbtnPagoTotal = new JRadioButton("Pago Total $");
		buttonGroup.add(rdbtnPagoTotal);
		rdbtnPagoTotal.setBounds(140, 331, 103, 21);
		panel.add(rdbtnPagoTotal);

		textFieldFechaIngreso = new JTextField();
		textFieldFechaIngreso.setBounds(10, 255, 96, 19);
		panel.add(textFieldFechaIngreso);
		textFieldFechaIngreso.setColumns(10);

		textFieldFechaSalida = new JTextField();
		textFieldFechaSalida.setBounds(10, 306, 96, 19);
		panel.add(textFieldFechaSalida);
		textFieldFechaSalida.setColumns(10);

		JButton btnRealizarPago = new JButton("Realizar Pago");
		btnRealizarPago.setBounds(289, 367, 117, 21);
		panel.add(btnRealizarPago);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(194, 367, 85, 21);
		panel.add(btnCancelar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(140, 160, 266, 154);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 34, 45, 13);
		panel.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 86, 45, 13);
		panel.add(lblApellido);

		JLabel lblDNIPasaporte = new JLabel("DNI/Pasaporte");
		lblDNIPasaporte.setBounds(10, 130, 45, 13);
		panel.add(lblDNIPasaporte);

		JLabel lblMetodoDePago = new JLabel("Metodo de Pago");
		lblMetodoDePago.setBounds(10, 177, 96, 13);
		panel.add(lblMetodoDePago);

		JLabel lblFechaIngreso = new JLabel("Fecha Ingreso");
		lblFechaIngreso.setBounds(10, 231, 96, 13);
		panel.add(lblFechaIngreso);

		JLabel lblFechaSalida = new JLabel("Fecha Salida");
		lblFechaSalida.setBounds(10, 284, 96, 13);
		panel.add(lblFechaSalida);
	}
}
