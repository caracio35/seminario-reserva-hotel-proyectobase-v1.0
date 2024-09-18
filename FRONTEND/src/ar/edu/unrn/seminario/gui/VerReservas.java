package ar.edu.unrn.seminario.gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame; 
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import javax.swing.table.TableCellRenderer;

public class VerReservas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    public VerReservas() {
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
            new Object[][] {
                {1, true, "2024-01-01", false, "2024-01-07", 5},
                {2, true, "2024-02-01", false, "2024-02-05", 4},
                {3, true, "2024-03-15", false, "2024-03-20", 3},
                {4, true, "2024-04-10", false, "2024-04-15", 2},
                {5, true, "2024-05-05", true, "2024-05-10", null},
                {6, false, "2024-06-20", false, "2024-06-25", 5},
                {7, true, "2024-07-01", false, "2024-07-05", null},
                {8, false, "2024-08-10", true, "2024-08-15", 3},
            },
            new String[] {
                "Num Habitacion", "Check-in", "ingreso", "Check-out", "salida", "Mi Calificacion"
            }
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // Especificar el tipo de las columnas
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
            if (selectedRow != -1) {
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
                if (result == JOptionPane.OK_OPTION) {
                    Date nuevaDesde = dateChooserDesde.getDate();
                    Date nuevaHasta = dateChooserHasta.getDate();

                    if (nuevaDesde != null && nuevaHasta != null) {
                        if (nuevaDesde.before(nuevaHasta)) {
                            int confirm = JOptionPane.showConfirmDialog(null,
                                    "¿Estás seguro de que deseas modificar las fechas?",
                                    "Confirmar Modificación", JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {
                                // Actualizar la tabla con las nuevas fechas
                                table.setValueAt(new java.sql.Date(nuevaDesde.getTime()), selectedRow, 2); // Cambia la fecha de entrada
                                table.setValueAt(new java.sql.Date(nuevaHasta.getTime()), selectedRow, 4); // Cambia la fecha de salida
                                System.out.println("Fechas modificadas para la reserva en fila: " + selectedRow);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "La fecha de entrada debe ser anterior a la fecha de salida.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor, selecciona ambas fechas.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona una reserva.");
            }
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
                    System.out.println("Calificación de la reserva en fila " + selectedRow + " cambiada a " + ratingValue);
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
