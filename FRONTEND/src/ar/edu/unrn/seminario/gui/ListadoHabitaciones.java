package ar.edu.unrn.seminario.gui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class ListadoHabitaciones extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable table;

    public ListadoHabitaciones() {
        // Configurar el JFrame
        setTitle("Lista de Habitaciones");
        setSize(555, 400); // Establece el tamaño del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        getContentPane().setLayout(null); // Establecer el layout como nulo para usar coordenadas absolutas

        // Crear el JScrollPane y JTable
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 39, 516, 133);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {
                {1, Boolean.TRUE, 8, Boolean.TRUE, ""},
                {2, null, 3, Boolean.TRUE, null},
                {3, Boolean.TRUE, 5, Boolean.TRUE, null},
                {4, Boolean.TRUE, 5, null, "25/12/2024"},
                {5, null, 4, null, "25/01/2025"},
                {6, Boolean.TRUE, 4, null, "12/12/2024"},
                {7, Boolean.TRUE, "3", Boolean.TRUE, null},
                {8, Boolean.TRUE, "7", Boolean.FALSE, "indefinido"},
                {9, null, "4", Boolean.TRUE, null},
            },
            new String[] {
                "Numero de Habitacion", "Cama Matrim", "Cant Camas", "Disponible", "no disponible Hasta"
            }
        ) {
            private static final long serialVersionUID = 1L;
            Class[] columnTypes = new Class[] {
                Object.class, Boolean.class, Object.class, Boolean.class, Object.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        table.getColumnModel().getColumn(0).setPreferredWidth(124);
        table.getColumnModel().getColumn(4).setPreferredWidth(127);
        scrollPane.setViewportView(table);
        
        JButton btnActivarHabitacion = new JButton("Activar");
        btnActivarHabitacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, 
                        "¿Está seguro de que desea activar esta habitación?", 
                        "Confirmación", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE);
                
                if (response == JOptionPane.YES_OPTION) {
                    // Lógica para activar la habitación
                    JOptionPane.showMessageDialog(null, "Habitación activada.");
                }
            }
        });
        btnActivarHabitacion.setBounds(10, 206, 85, 21);
        getContentPane().add(btnActivarHabitacion);
        
        JButton btnDesactivar = new JButton("Desactivar");
        btnDesactivar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Solicitar fecha al usuario
                JPanel panel = new JPanel();
                panel.add(new JLabel("Ingrese fecha de desactivación (dd/mm/aaaa):"));
                JTextField txtFecha = new JTextField(10);
                panel.add(txtFecha);
                
                int option = JOptionPane.showConfirmDialog(null, panel, 
                        "Ingrese fecha de desactivación", 
                        JOptionPane.OK_CANCEL_OPTION, 
                        JOptionPane.PLAIN_MESSAGE);
                
                if (option == JOptionPane.OK_OPTION) {
                    String fecha = txtFecha.getText();
                    
                    int response = JOptionPane.showConfirmDialog(null, 
                            "¿Está seguro de que desea desactivar esta habitación hasta el " + fecha + "?", 
                            "Confirmación", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.QUESTION_MESSAGE);
                    
                    if (response == JOptionPane.YES_OPTION) {
                        // Lógica para desactivar la habitación
                        JOptionPane.showMessageDialog(null, "Habitación desactivada hasta " + fecha + ".");
                    }
                }
            }
        });
        btnDesactivar.setBounds(123, 206, 85, 21);
        getContentPane().add(btnDesactivar);
        
        JButton btnEditarHabitacion = new JButton("Editar Habitacion");
        btnEditarHabitacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para editar la habitación
                JOptionPane.showMessageDialog(null, "Funcionalidad de edición aún no implementada.");
            }
        });
        btnEditarHabitacion.setBounds(276, 206, 109, 21);
        getContentPane().add(btnEditarHabitacion);
        
        JButton btnEliminarHabitacion = new JButton("Eliminar Habitacion");
        btnEliminarHabitacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para eliminar la habitación
                JOptionPane.showMessageDialog(null, "Funcionalidad de eliminación aún no implementada.");
            }
        });
        btnEliminarHabitacion.setBounds(395, 206, 131, 21);
        getContentPane().add(btnEliminarHabitacion);
        
        JButton btnSalir = new JButton("SALIR");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, 
                        "¿Está seguro de que desea salir?", 
                        "Confirmación", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE);
                
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        btnSalir.setBounds(441, 332, 85, 21);
        getContentPane().add(btnSalir);
    }

    public static void main(String[] args) {
        // Crear e iniciar el JFrame
        ListadoHabitaciones frame = new ListadoHabitaciones();
        frame.setVisible(true);
    }
}
