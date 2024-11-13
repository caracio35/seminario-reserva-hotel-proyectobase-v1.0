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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 752, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 35, 718, 147);
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
        btnMostrarDetalles.setBounds(10, 200, 150, 25);
        btnMostrarDetalles.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                System.out.println("Ver detalles para la reserva en fila: " + selectedRow);
            } else {
                System.out.println("Por favor, selecciona una reserva.");
            }
        });
        contentPane.add(btnMostrarDetalles);

        JButton btnCancelarReserva = new JButton("Cancelar Reserva");
        btnCancelarReserva.setBounds(398, 200, 150, 25);
        btnCancelarReserva.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "¿Estás seguro de que deseas cancelar la reserva?",
                        "Confirmar Cancelación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.out.println("Reserva en fila " + selectedRow + " cancelada.");
                    ((DefaultTableModel) table.getModel()).removeRow(selectedRow);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona una reserva.");
            }
        });
        contentPane.add(btnCancelarReserva);

        // Botón "Modificar Fechas"
        JButton btnModificarFechas = new JButton("Modificar Fechas");
        btnModificarFechas.setBounds(207, 200, 150, 25);
        btnModificarFechas.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona una reserva.");
                return;
            }

            // Crear los componentes de fecha
            JDateChooser dateChooserDesde = new JDateChooser();
            JDateChooser dateChooserHasta = new JDateChooser();
            dateChooserDesde.setDateFormatString("yyyy-MM-dd");
            dateChooserHasta.setDateFormatString("yyyy-MM-dd");

            // Configurar el panel con GridBagLayout
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Añadir la etiqueta de "Fecha de Entrada"
            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(new javax.swing.JLabel("Fecha de Entrada:"), gbc);

            gbc.gridx = 1;
            panel.add(dateChooserDesde, gbc);

            // Añadir la etiqueta de "Fecha de Salida"
            gbc.gridx = 0;
            gbc.gridy = 1;
            panel.add(new javax.swing.JLabel("Fecha de Salida:"), gbc);

            gbc.gridx = 1;
            panel.add(dateChooserHasta, gbc);

            // Mostrar el panel en un JOptionPane
            int result = JOptionPane.showConfirmDialog(null, panel,
                    "Selecciona las nuevas fechas", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result != JOptionPane.OK_OPTION) {
                return;
            }

            Date nuevaDesde = dateChooserDesde.getDate();
            Date nuevaHasta = dateChooserHasta.getDate();

            if (nuevaDesde == null || nuevaHasta == null) {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona ambas fechas.");
                return;
            }

            if (!nuevaDesde.before(nuevaHasta)) {
                JOptionPane.showMessageDialog(null, "La fecha de entrada debe ser anterior a la fecha de salida.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null,
                    "¿Estás seguro de que deseas modificar las fechas?",
                    "Confirmar Modificación", JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            // Actualizar la tabla con las nuevas fechas
            table.setValueAt(new java.sql.Date(nuevaDesde.getTime()), selectedRow, 2);
            table.setValueAt(new java.sql.Date(nuevaHasta.getTime()), selectedRow, 4);
            System.out.println("Fechas modificadas para la reserva en fila: " + selectedRow);
        });
        contentPane.add(btnModificarFechas);

        // Botón "Calificar"
        JButton btnCalificar = new JButton("Calificar");
        btnCalificar.setBounds(578, 200, 150, 25);
        btnCalificar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Obtener el ID de la reserva de la columna correspondiente (por ejemplo, la
                // columna 0)
                int reservaId = (int) table.getValueAt(selectedRow, 0);
                CalificarHabitaciones calificar = new CalificarHabitaciones(api, reservaId);
                calificar.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona una reserva para calificar.");
            }
        });
        contentPane.add(btnCalificar);

        JButton btnSalir = new JButton("SALIR");
        btnSalir.setBounds(578, 240, 150, 25);
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