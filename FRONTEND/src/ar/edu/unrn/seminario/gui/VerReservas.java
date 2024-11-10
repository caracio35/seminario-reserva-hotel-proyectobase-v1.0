package ar.edu.unrn.seminario.gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Date;
import java.util.List;

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
import ar.edu.unrn.seminario.exception.EnterosEnCeroExeption;
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
                        "Num Habitaciones", "Check-in", "ingreso", "Check-out", "salida", "Mi Calificacion" , "Servicios" 
                }) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 1: // Check-in
                    case 3: // Check-out
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
        
        List<ReservaDTO> reservas = api.obtenerReserva();

        for (ReservaDTO reserva : reservas) {
            Object[] rowData = new Object[7]; 
            rowData[0] = reserva.getHabitacion();  // Número de habitaciones
            rowData[1] = reserva.isCheckIn();  // Check-in (Booleano)
            rowData[2] = reserva.getFechaDeInicio();  // Ingreso (fecha u otro valor)
            rowData[3] = reserva.isCheckOut();  // Check-out (Booleano)
            rowData[4] = reserva.getFechaDeSalida();  // Salida (fecha u otro valor)
            rowData[5] = reserva.getCalificacion();  // Mi Calificación
            rowData[6] = String.join(", ", reserva.getServicios());  // Servicios (suponiendo que es una lista de servicios)

            // Agregar la fila al modelo
            model.addRow(rowData);
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
                String[] ratings = { "1", "2", "3", "4", "5" };
                String rating = (String) JOptionPane.showInputDialog(null,
                        "Selecciona una calificación:", "Calificar Reserva",
                        JOptionPane.QUESTION_MESSAGE, null, ratings, "1");
                if (rating != null) {
                    int ratingValue = Integer.parseInt(rating);
                    // Actualizar la calificación en la tabla
                    table.setValueAt(ratingValue, selectedRow, 5); // Cambia la calificación
                    System.out.println(
                            "Calificación de la reserva en fila " + selectedRow + " cambiada a " + ratingValue);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona una reserva.");
            }
        });
        contentPane.add(btnCalificar);

        JButton btnSalir = new JButton("SALIR");
        btnSalir.setBounds(578, 240, 150, 25);
        btnSalir.addActionListener(e -> dispose());
        contentPane.add(btnSalir);
    }

}
