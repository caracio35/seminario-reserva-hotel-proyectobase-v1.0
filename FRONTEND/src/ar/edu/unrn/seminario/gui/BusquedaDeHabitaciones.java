package ar.edu.unrn.seminario.gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.CaracteristicaEspecialDTO;
import ar.edu.unrn.seminario.dto.HabitacionDTO;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;

@SuppressWarnings("unused")
public class BusquedaDeHabitaciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_6;
	private JTable table_1;
	private JTextField textFieldHuespedes;
	private JButton btnCancelarsalir;
	private DefaultTableModel modelo;
	private IApi api;
	private String fechaReservaFin;
	private String fechaReservaInicio;
	private JTextField textFieldPrecio;
	private int precioMinimo = 0;
	private int camas = 0;
	private Locale idiomaSeleccionado;
	private ResourceBundle recursos;

	/**
	 * Create the frame.
	 */
	public BusquedaDeHabitaciones(IApi api, Locale idioma) {
		this.api = api;
		this.idiomaSeleccionado = idioma;
		this.recursos = ResourceBundle.getBundle("labels", idioma);

		// Configurar el JFrame
		setTitle(recursos.getString("busquedaHabitaciones.titulo"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1153, 416);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 349, 502, -331);
		getContentPane().add(panel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(189, 53, 938, 277);
		getContentPane().add(scrollPane);

		modelo = new DefaultTableModel(new Object[][] {},
				new String[] { recursos.getString("busquedaHabitaciones.camas"),
						recursos.getString("busquedaHabitaciones.descripcion"),
						recursos.getString("busquedaHabitaciones.precio"),
						recursos.getString("busquedaHabitaciones.numeroHabitacion"),
						recursos.getString("busquedaHabitaciones.caracteristicasEspeciales"),
						recursos.getString("busquedaHabitaciones.seleccionado") }) {
			public boolean isCellEditable(int row, int column) {
				return column == 5;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 5) {
					return Boolean.class;
				}
				return super.getColumnClass(columnIndex);
			}
		};

		table_1 = new JTable(modelo) {
			@Override
			public Class<?> getColumnClass(int column) {

				if (column == 5) {
					return Boolean.class;
				}
				return super.getColumnClass(column);
			}
		};

		scrollPane.setViewportView(table_1);
		table_1.setShowGrid(false);

		scrollPane.setViewportView(table_1);
		this.cargarHabitaciones();

		scrollPane.setViewportView(table_1);

		JLabel lblNewLabel = new JLabel(recursos.getString("busquedaHabitaciones.fechaIngreso"));
		lblNewLabel.setBounds(10, 96, 85, 13);
		getContentPane().add(lblNewLabel);

		JLabel JcalederFechaSalida = new JLabel(recursos.getString("busquedaHabitaciones.fechaSalida"));
		JcalederFechaSalida.setBounds(10, 163, 96, 13);
		getContentPane().add(JcalederFechaSalida);

		lblNewLabel_2 = new JLabel(recursos.getString("busquedaHabitaciones.camas"));
		lblNewLabel_2.setBounds(10, 230, 96, 13);
		getContentPane().add(lblNewLabel_2);

		lblNewLabel_6 = new JLabel(recursos.getString("busquedaHabitaciones.precio"));
		lblNewLabel_6.setBounds(10, 28, 140, 13);
		getContentPane().add(lblNewLabel_6);

		JButton btnReservar = new JButton(recursos.getString("busquedaHabitaciones.reservar"));
		btnReservar.addActionListener(e -> {
			List<Integer> habitacionesSeleccionadas = new ArrayList<>();
			for (int i = 0; i < table_1.getRowCount(); i++) {
				Boolean estado = (Boolean) table_1.getValueAt(i, 5);
				if (estado != null && estado) {
					Integer numeroHabitacion = (Integer) table_1.getValueAt(i, 3);
					habitacionesSeleccionadas.add(numeroHabitacion);
				}
			}

			if (habitacionesSeleccionadas.isEmpty()) {
				JOptionPane.showMessageDialog(null, recursos.getString("busquedaHabitaciones.seleccionarHabitacion"));
				return;
			}
			if (fechaReservaInicio == null || fechaReservaFin == null) {
				JOptionPane.showMessageDialog(null,recursos.getString("busquedaHabitaciones.seleccionarFechaSalidaIngreso"));
				return;
			}

			ConfirmarReserva confirmacion = new ConfirmarReserva(fechaReservaInicio, fechaReservaFin, api,
					habitacionesSeleccionadas, idioma);
			confirmacion.setVisible(true);
			dispose();
		});
		btnReservar.setBounds(189, 349, 116, 21);
		getContentPane().add(btnReservar);

		textFieldHuespedes = new JTextField();
		textFieldHuespedes.setColumns(10);
		textFieldHuespedes.setBounds(10, 253, 140, 34);
		getContentPane().add(textFieldHuespedes);

		btnCancelarsalir = new JButton(recursos.getString("reservas.salir"));
		btnCancelarsalir.addActionListener(e -> dispose());
		btnCancelarsalir.setBounds(1011, 349, 116, 21);
		getContentPane().add(btnCancelarsalir);

		JDateChooser JcalenderFechaIngreso = new JDateChooser();
		Calendar cal = Calendar.getInstance();
		JcalenderFechaIngreso.setMinSelectableDate(cal.getTime());
		JcalenderFechaIngreso.setBounds(10, 119, 140, 34);
		getContentPane().add(JcalenderFechaIngreso);

		JDateChooser JcalemderFechaSalida = new JDateChooser();
		JcalemderFechaSalida.getCalendarButton();
		JcalemderFechaSalida.setMinSelectableDate(cal.getTime());
		JcalemderFechaSalida.setBounds(10, 186, 140, 34);
		getContentPane().add(JcalemderFechaSalida);

		JcalenderFechaIngreso.getDateEditor().addPropertyChangeListener("date", e -> {
			Date selectedDate = JcalenderFechaIngreso.getDate();
			if (selectedDate != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				fechaReservaInicio = sdf.format(selectedDate);

				JcalemderFechaSalida.setMinSelectableDate(selectedDate);
			}
		});

		JcalemderFechaSalida.getDateEditor().addPropertyChangeListener("date", e -> {
			Date selectedDate = JcalemderFechaSalida.getDate();
			if (selectedDate != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				fechaReservaFin = sdf.format(selectedDate);

			}
		});

		textFieldPrecio = new JTextField();
		textFieldPrecio.setBounds(10, 52, 140, 34);
		getContentPane().add(textFieldPrecio);
		textFieldPrecio.setColumns(10);

		JButton btnBuscar = new JButton(recursos.getString("busquedaHabitaciones.buscar"));
		btnBuscar.setBounds(10, 309, 85, 21);
		getContentPane().add(btnBuscar);
		btnBuscar.addActionListener(e -> {
			if (!textFieldPrecio.getText().isEmpty()) {
				try{ 
					precioMinimo = Integer.parseInt(textFieldPrecio.getText());
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,recursos.getString("busquedaHabitaciones.buscar"));
				}
				
			}
			if (!textFieldHuespedes.getText().isEmpty()) {
				try{ 
					camas = Integer.parseInt(textFieldHuespedes.getText());
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,recursos.getString("busquedaHabitaciones.buscar"));
				}
			}
			try {
				List<HabitacionDTO> habitaciones = api.obtenerHabitacionesHabilitada();
				List<HabitacionDTO> filtrado = habitaciones.stream().filter(h -> h.getPrecio() >= precioMinimo)
						.filter(h -> h.getCantidadDeCamas() >= camas)
						.sorted(Comparator.comparingDouble(HabitacionDTO::getPrecio)).collect(Collectors.toList());

				cargarHabitacionesFiltradas(filtrado);
			} catch (ConexionFallidaExeption | ErrorDatosNoEncontradosExeption e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		});

	}

	public void cargarHabitaciones() {
		try {

			List<HabitacionDTO> habitaciones = api.obtenerHabitacionesHabilitada();

			modelo.setRowCount(0);

			habitaciones.stream().sorted(Comparator.comparingInt(HabitacionDTO::getNumHabitacion))
					.forEach(habitacion -> {
						String caracteristicas = habitacion.getCaracteristicasEspeciale().stream()
								.map(CaracteristicaEspecialDTO::getNombre).collect(Collectors.joining(", "));
						modelo.addRow(new Object[] { habitacion.getCantidadDeCamas(), habitacion.getDescripcion(),
								habitacion.getPrecio(), habitacion.getNumHabitacion(), caracteristicas, false });
					});
		} catch (ConexionFallidaExeption | ErrorDatosNoEncontradosExeption e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void cargarHabitacionesFiltradas(List<HabitacionDTO> habitaciones1) {

		modelo.setRowCount(0);

		habitaciones1.stream().forEach(habitacion -> {
			String caracteristicas = habitacion.getCaracteristicasEspeciale().stream()
					.map(CaracteristicaEspecialDTO::getNombre).collect(Collectors.joining(", "));
			modelo.addRow(new Object[] { habitacion.getCantidadDeCamas(), habitacion.getDescripcion(),
					habitacion.getPrecio(), habitacion.getNumHabitacion(), caracteristicas, false });
		});
	}
}
