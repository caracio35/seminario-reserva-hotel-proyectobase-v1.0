package ar.edu.unrn.seminario.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.CaracteristicaEspecialDTO;
import ar.edu.unrn.seminario.dto.HabitacionDTO;

public class BusquedaDeHabitaciones extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JScrollPane scrollPane_1;
	private JLabel lblNewLabel_6;
	private JTable table;
	private JTable table_1;
	private JTextField textFieldHuespedes;
	private JButton btnCancelarsalir;
	private DefaultTableModel modelo;
	private IApi api;
	private String fechaReservaFin;
	private String fechaReservaInicio ; 

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public BusquedaDeHabitaciones(IApi api) {
		this.api = api;
		// Configurar el JFrame
		setTitle("Búsqueda de Habitaciones");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1001, 416);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 349, 502, -331);
		getContentPane().add(panel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(136, 53, 815, 277);
		getContentPane().add(scrollPane);

		modelo = new DefaultTableModel(new Object[][] {}, new String[] { "Camas", "Descripcion", "Precio", "Numero de Habitacion", "  Caracteristicas Especiales   " }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table_1 = new JTable(modelo);

		table_1.setShowGrid(false);

		scrollPane.setViewportView(table_1);
		this.cargarHabitaciones();

		scrollPane.setViewportView(table_1);

		textField = new JTextField();
		textField.setBounds(136, 23, 134, 19);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Fecha Ingreso");
		lblNewLabel.setBounds(10, 192, 85, 13);
		getContentPane().add(lblNewLabel);

		JLabel JcalederFechaSalida = new JLabel("Fecha Salida");
		JcalederFechaSalida.setBounds(10, 236, 96, 13);
		getContentPane().add(JcalederFechaSalida);

		lblNewLabel_2 = new JLabel("Total Huéspedes");
		lblNewLabel_2.setBounds(10, 280, 96, 13);
		getContentPane().add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Buscar");
		lblNewLabel_3.setBounds(136, 5, 45, 13);
		getContentPane().add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Descripción");
		lblNewLabel_4.setBounds(10, 5, 85, 13);
		getContentPane().add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("Calificación");
		lblNewLabel_5.setBounds(10, 105, 85, 13);
		getContentPane().add(lblNewLabel_5);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 28, 116, 71);
		getContentPane().add(scrollPane_1);

		table = new JTable();
		scrollPane_1.setViewportView(table);

		lblNewLabel_6 = new JLabel("Precio");
		lblNewLabel_6.setBounds(10, 149, 45, 13);
		getContentPane().add(lblNewLabel_6);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(10, 120, 96, 19);
		getContentPane().add(comboBox);

		JComboBox<String> comboBox_2 = new JComboBox<>();
		comboBox_2.setBounds(10, 161, 96, 19);
		getContentPane().add(comboBox_2);

		JButton btnReservar = new JButton("RESERVAR");
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfirmarReserva confirmacion = new ConfirmarReserva( fechaReservaInicio,fechaReservaFin , null );
				confirmacion.setVisible(true);
			}
		});
		btnReservar.setBounds(136, 346, 116, 21);
		getContentPane().add(btnReservar);

		textFieldHuespedes = new JTextField();
		textFieldHuespedes.setColumns(10);
		textFieldHuespedes.setBounds(10, 293, 96, 19);
		getContentPane().add(textFieldHuespedes);

		btnCancelarsalir = new JButton("CANCELAR/SALIR");
		btnCancelarsalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelarsalir.setBounds(599, 346, 116, 21);
		getContentPane().add(btnCancelarsalir);

		JDateChooser JcalenderFechaIngreso = new JDateChooser();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 0);
        JcalenderFechaIngreso.setMinSelectableDate(cal.getTime());
		JcalenderFechaIngreso.getDateEditor().addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
            	
                Date selectedDate = JcalenderFechaIngreso.getDate();
                if (selectedDate != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    fechaReservaInicio = sdf.format(selectedDate);
                    
                    System.out.println("Fecha seleccionada: " + fechaReservaFin);
                } 
            }
        });
		JcalenderFechaIngreso.setBounds(10, 204, 96, 20);
		getContentPane().add(JcalenderFechaIngreso);

		JDateChooser dateChooser_1 = new JDateChooser();
		Calendar caleder = Calendar.getInstance();
        caleder.add(Calendar.DAY_OF_MONTH, 0);
        JcalenderFechaIngreso.setMinSelectableDate(caleder.getTime());
        dateChooser_1.getDateEditor().addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                
                Date selectedDate = dateChooser_1.getDate();
                if (selectedDate != null) {
   
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    fechaReservaFin = sdf.format(selectedDate);
                    
                    System.out.println("Fecha seleccionada: " + fechaReservaFin);
                }
            }
        });
		dateChooser_1.setBounds(10, 249, 96, 20);
		getContentPane().add(dateChooser_1);
	}

	public void cargarHabitaciones() {
		List<HabitacionDTO> habitaciones = api.obtenerHabitacionesHabilitada();

		modelo.setRowCount(0);

		for (HabitacionDTO habitacion : habitaciones) {

			List<CaracteristicaEspecialDTO> caracteristicasList = habitacion.getCaracteristicasEspeciale();
			String caracteristicas = caracteristicasList.stream().map(CaracteristicaEspecialDTO::getNombre)
					.collect(Collectors.joining(", "));
			modelo.addRow(new Object[] { habitacion.getCantidadDeCamas(), habitacion.getDescripcion(),
					habitacion.getPrecio(), habitacion.getNumHabitacion(), caracteristicas });
		}
	}
}
