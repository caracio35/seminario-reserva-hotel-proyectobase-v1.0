package ar.edu.unrn.seminario.gui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.HabitacionDTO;
import ar.edu.unrn.seminario.exception.CampoVacioExeption;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.EnterosEnCeroExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import ar.edu.unrn.seminario.exception.PrecioCeroExeption;

@SuppressWarnings("unused")
public class ConfirmarReserva extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldDNIPasaporte;
	private JTable tabla_Habitaciones;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFieldUsuario;
	private JTextField textFieldFechaIngreso;
	private JTextField texFilFechaSalida;
	private DefaultTableModel modelo;
	IApi api;
	private java.util.List<Integer> habitacionesSeleccionadas;
	private List<HabitacionDTO> habitaciones;
	private JTextField textFieldCantidaPersonas;

	@SuppressWarnings("unchecked")
	public ConfirmarReserva(String fechaInicio, String fechaFin, String usuario, IApi api,
			java.util.List<Integer> habitacionesSeleccionadas) {

		{
			this.api = api;
			habitaciones = new ArrayList<>();
			this.habitacionesSeleccionadas = habitacionesSeleccionadas;

			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 528, 499);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);

			JPanel panel = new JPanel();
			panel.setBounds(10, 10, 492, 439);
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

			@SuppressWarnings("rawtypes")
			JComboBox comboBoxMetodoPago = new JComboBox();
			// dos metodos de pago en el combobox mercado pago y tarjeta de credito
			comboBoxMetodoPago.setBounds(10, 217, 137, 21);
			comboBoxMetodoPago.addItem("MercadoPago");
			comboBoxMetodoPago.addItem("Tarjeta de Credito");
			panel.add(comboBoxMetodoPago);

			JRadioButton rdbtnPrecioMinimo = new JRadioButton("Precio Minimo $");
			buttonGroup.add(rdbtnPrecioMinimo);
			rdbtnPrecioMinimo.setBounds(10, 407, 103, 21);
			panel.add(rdbtnPrecioMinimo);

			JRadioButton rdbtnPagoTotal = new JRadioButton("Pago Total $");
			buttonGroup.add(rdbtnPagoTotal);
			rdbtnPagoTotal.setBounds(119, 407, 103, 21);
			panel.add(rdbtnPagoTotal);

			JButton btnRealizarPago = new JButton("Realizar Pago");
			btnRealizarPago.addActionListener(e -> {
				String metodoPago = (String) comboBoxMetodoPago.getSelectedItem();
				Random random = new Random();

				if ("Tarjeta de Credito".equals(metodoPago)) {
					// 30% de probabilidad de falla por monto insuficiente
					if (random.nextInt(100) < 30) {
						JOptionPane.showMessageDialog(contentPane,
								"El pago con Tarjeta de Crédito falló. Monto insuficiente.");
					} else {
						JOptionPane.showMessageDialog(contentPane, "Pago con Tarjeta de Crédito exitoso!");
						generarReserva();
					}
				} else if ("MercadoPago".equals(metodoPago)) {
					// Mostrar un JOptionPane con QR
					JOptionPane.showMessageDialog(this, "Mostrando QR para MercadoPago...");

					// Generar tiempo de visualización aleatorio entre 5 y 10 segundos

					int displayTime = random.nextInt(10) + 5;

					// Configurar un Timer para verificar el resultado del pago
					new Timer(displayTime, evt -> {
						// 30% de probabilidad de fallo del pago
						if (random.nextInt(100) < 30) {
							JOptionPane.showMessageDialog(contentPane,
									"El pago con MercadoPago falló. Intente nuevamente.");
						} else {
							JOptionPane.showMessageDialog(contentPane, "Pago con MercadoPago exitoso!");
							// Lógica para generar la reserva
							generarReserva();
						}
						((Timer) evt.getSource()).stop();
					}).start();
				}
			});

			btnRealizarPago.setBounds(365, 407, 117, 21);
			panel.add(btnRealizarPago);

			JButton btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(e -> {
				dispose();
			});
			btnCancelar.setBounds(270, 407, 85, 21);
			panel.add(btnCancelar);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(167, 36, 315, 288);
			panel.add(scrollPane);

			modelo = new DefaultTableModel(new Object[][] {},
					new String[] { "Camas", "Descripcion", "Precio", "Numero de Habitacion" });
			tabla_Habitaciones = new JTable(modelo);
			scrollPane.setViewportView(tabla_Habitaciones);
			try {

				for (Integer numHabitacion : habitacionesSeleccionadas) {
					habitaciones.add(api.buscarHabitacionDTOPorNumero(numHabitacion));

				}
				llenarTabla();
			} catch (ConexionFallidaExeption | ErrorDatosNoEncontradosExeption | CampoVacioExeption
					| EnterosEnCeroExeption | PrecioCeroExeption e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}

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
			textFieldUsuario.setBounds(10, 36, 137, 20);
			panel.add(textFieldUsuario);
			textFieldUsuario.setColumns(10);
			textFieldUsuario.setText("mariag");
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

			JLabel lblNewLabel_1 = new JLabel("Personas");
			lblNewLabel_1.setBounds(9, 346, 46, 14);
			panel.add(lblNewLabel_1);

			textFieldCantidaPersonas = new JTextField();
			textFieldCantidaPersonas.setBounds(10, 367, 137, 20);
			panel.add(textFieldCantidaPersonas);
			textFieldCantidaPersonas.setColumns(10);
		}
	}

	private void llenarTabla() {
		modelo.setRowCount(0);
		habitaciones.stream().sorted(Comparator.comparingInt(h -> h.getNumHabitacion())).forEach(habitacionDTO -> {
			Object[] fila = new Object[4];
			fila[0] = habitacionDTO.getCantidadDeCamas();
			fila[1] = habitacionDTO.getDescripcion();
			fila[2] = habitacionDTO.getPrecio();
			fila[3] = habitacionDTO.getNumHabitacion();

			modelo.addRow(fila);
		});
		tabla_Habitaciones.setModel(modelo);
	}

	private int[] obtenerNumerosDeHabitacionDesdeTabla() {
		int rowCount = modelo.getRowCount();
		int[] numerosHabitacion = new int[rowCount];

		for (int i = 0; i < rowCount; i++) {
			numerosHabitacion[i] = (int) modelo.getValueAt(i, 3);
		}

		return numerosHabitacion;
	}

	private String[] obtenerServiciosDesdeTabla() {
		int rowCount = modelo.getRowCount();
		String[] servicios = new String[rowCount];

		for (int i = 0; i < rowCount; i++) {
			// Obtener el servicio de la columna correspondiente (aquí usamos columna 1 para
			// "Descripción")
			servicios[i] = (String) modelo.getValueAt(i, 1);
		}

		return servicios;
	}

	// int[] habitacion, String usuario, String fechaInicio, String fechaFin,
	// String fechaReserva, int cantidadPersonas, String[] servicio, boolean
	// pagoMinimo
	private void generarReserva() {

		LocalDateTime fechaActual = LocalDateTime.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String fechaReserva = fechaActual.format(formato);
		int[] numerosHabitacion = obtenerNumerosDeHabitacionDesdeTabla();
		int cantidadPersonas = Integer.parseInt(textFieldCantidaPersonas.getText());
		String[] serviciosObtenido = { "Desayuno" };
		try {
			api.generarReserva(numerosHabitacion, textFieldUsuario.getText(), textFieldFechaIngreso.getText(),
					texFilFechaSalida.getText(), fechaReserva, cantidadPersonas, serviciosObtenido, true);
			dispose();
		} catch (ConexionFallidaExeption | ErrorDatosNoEncontradosExeption | CampoVacioExeption | EnterosEnCeroExeption
				| PrecioCeroExeption e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	private void reservarHabitacion(Object roomId) {
		// Implementa la lógica para reservar una habitación individual
		System.out.println("Habitación " + roomId + " reservada.");
	}
}
