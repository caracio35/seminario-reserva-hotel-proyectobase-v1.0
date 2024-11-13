package ar.edu.unrn.seminario.gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.ReservaDTO;
import ar.edu.unrn.seminario.exception.CampoVacioExeption;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.EnterosEnCeroExeption;
import ar.edu.unrn.seminario.exception.ErrorDatosNoEncontradosExeption;
import ar.edu.unrn.seminario.exception.PrecioCeroExeption;

import javax.swing.JCheckBox;
import javax.swing.table.TableCellRenderer;

public class VerReservas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    public VerReservas(IApi api) throws CampoVacioExeption, EnterosEnCeroExeption, PrecioCeroExeption {
    	setTitle("Reservas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1097, 447);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(182, 35, 889, 325);
        contentPane.add(scrollPane);

        // Definición del modelo de la tabla con tipos de columnas
        DefaultTableModel model = new DefaultTableModel(
                new Object[][] {},
                new String[] {
                        "ID Reserva", "Num Habitaciones", "Check-in", "ingreso", "Check-out", "salida",
                        "Mi Calificacion", "Servicios"
                }) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 2: // Check-in
                    case 4: // Check-out
                        return Boolean.class;
                    default:
                        return Object.class;
                }
            }

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        scrollPane.setViewportView(table);

        List<ReservaDTO> reservas;
        try {
            reservas = api.obtenerReserva();
            reservas.stream()
                    .sorted(Comparator.comparingInt(ReservaDTO::getId))
                    .forEach(reserva -> {
                        Object[] rowData = {
                                reserva.getId(),
                                numeroDehabitaciones(reserva.getHabitacion()),
                                reserva.isCheckIn(),
                                reserva.getFechaDeInicio(),
                                reserva.isCheckOut(),
                                reserva.getFechaDeSalida(),
                                calificacion(reserva),
                                String.join(", ", reserva.getServicios())
                        };
                        model.addRow(rowData);
                    });
        } catch (ConexionFallidaExeption | ErrorDatosNoEncontradosExeption | CampoVacioExeption | EnterosEnCeroExeption
                | PrecioCeroExeption e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        JButton btnMostrarDetalles = new JButton("Mostrar Detalles");
        btnMostrarDetalles.setBounds(0, 37, 150, 25);
        btnMostrarDetalles.addActionListener(e -> {
        	
        	JOptionPane.showMessageDialog(null, "Se Implementara el la proxima actulizacion");
        	
        });
        contentPane.add(btnMostrarDetalles);

        JButton btnCancelarReserva = new JButton("Cancelar Reserva");
        btnCancelarReserva.setBounds(0, 188, 150, 25);
        btnCancelarReserva.addActionListener(e -> {
        	JOptionPane.showMessageDialog(null, "Se Implementara el la proxima actulizacion");
        });
        contentPane.add(btnCancelarReserva);

        // Botón "Modificar Fechas"
        JButton btnModificarFechas = new JButton("Modificar Fechas");
        btnModificarFechas.setBounds(0, 115, 150, 25);
        btnModificarFechas.addActionListener(e -> {
        	JOptionPane.showMessageDialog(null, "Se Implementara el la proxima actulizacion");
        });
        contentPane.add(btnModificarFechas);

        // Botón "Calificar"
        JButton btnCalificar = new JButton("Calificar");
        btnCalificar.setBounds(0, 270, 150, 25);
        btnCalificar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
            	
                int reservaId = (int) table.getValueAt(selectedRow, 0);
                
                CalificarHabitaciones calificar = new CalificarHabitaciones(api, reservaId);
                calificar.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona una reserva para calificar.");
            }
        });
        contentPane.add(btnCalificar);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(921, 371, 150, 25);
        btnSalir.addActionListener(e -> dispose());
        contentPane.add(btnSalir);
    }

    private String calificacion(ReservaDTO reserva) {
        if (reserva.getCalificacion() == null) {
            return "No ha calificado";
        }
        return reserva.getCalificacion();
    }

    private String numeroDehabitaciones(int[] numHabitaciones) {
        // convertir los numeros de habitaciones a strings

        return Arrays.stream(numHabitaciones)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", "));
    }
}