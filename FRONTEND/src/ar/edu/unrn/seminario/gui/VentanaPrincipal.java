package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.exception.CampoVacioExeption;
import ar.edu.unrn.seminario.exception.ConexionFallidaExeption;
import ar.edu.unrn.seminario.exception.EnterosEnCeroExeption;
import ar.edu.unrn.seminario.exception.PrecioCeroExeption;

public class VentanaPrincipal extends JFrame {

    private JPanel contentPane;

    public VentanaPrincipal(IApi api, Locale seleccion) {
        // Cargar archivo de propiedades con el idioma deseado
        ResourceBundle labels = ResourceBundle.getBundle("labels", seleccion); // Usar el idioma seleccionado

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Menú Usuarios
        JMenu usuarioMenu = new JMenu(labels.getString("menu.usuarios"));
        menuBar.add(usuarioMenu);

        JMenuItem altaUsuarioMenuItem = new JMenuItem(labels.getString("menuitem.alta_usuario"));
        altaUsuarioMenuItem.addActionListener(arg0 -> {
            AltaUsuario alta = new AltaUsuario(api);
            alta.setLocationRelativeTo(null);
            alta.setVisible(true);
        });
        usuarioMenu.add(altaUsuarioMenuItem);

        JMenuItem listadoUsuarioMenuItem = new JMenuItem(labels.getString("menuitem.listado_usuario"));
        listadoUsuarioMenuItem.addActionListener(arg0 -> {
            ListadoUsuario listado = new ListadoUsuario(api);
            listado.setLocationRelativeTo(null);
            listado.setVisible(true);
        });
        usuarioMenu.add(listadoUsuarioMenuItem);

        // Menú Habitaciones
        JMenu mnHabitaciones = new JMenu(labels.getString("menu.habitaciones"));
        menuBar.add(mnHabitaciones);

        JMenuItem mntmCargarHabitacion = new JMenuItem(labels.getString("menuitem.cargar_habitacion"));
        mntmCargarHabitacion.addActionListener(e -> {
            CargarHabitacion cargaH = new CargarHabitacion(api, false);
            api.modificarFalse();
            cargaH.setVisible(true);
        });
        mnHabitaciones.add(mntmCargarHabitacion);

        JMenuItem mntmListadoHabitaciones = new JMenuItem(labels.getString("menuitem.listado_habitaciones"));
        mntmListadoHabitaciones.addActionListener(e -> {
            try {
                ListadoHabitaciones listaHabitaciones = new ListadoHabitaciones(api);
                listaHabitaciones.setVisible(true);
            } catch (ConexionFallidaExeption e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
            }
        });
        mnHabitaciones.add(mntmListadoHabitaciones);

        // Menú Reservas
        JMenu mnReservas = new JMenu(labels.getString("menu.reservas"));
        menuBar.add(mnReservas);

        JMenuItem mntmBuscarHabitacion = new JMenuItem(labels.getString("menuitem.buscar_habitacion"));
        mntmBuscarHabitacion.addActionListener(e -> {
            BusquedaDeHabitaciones busqueda = new BusquedaDeHabitaciones(api);
            busqueda.setVisible(true);
        });
        mnReservas.add(mntmBuscarHabitacion);

        JMenuItem mntmMisReservas = new JMenuItem(labels.getString("menuitem.mis_reservas"));
        mntmMisReservas.addActionListener(e -> {
            try {
                VerReservas misReservas = new VerReservas(api);
                misReservas.setVisible(true);
            } catch (CampoVacioExeption | EnterosEnCeroExeption | PrecioCeroExeption e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
            }
        });
        mnReservas.add(mntmMisReservas);

        // Menú Configuración
        JMenu configuracionMenu = new JMenu(labels.getString("menu.configuracion"));
        menuBar.add(configuracionMenu);

        JMenuItem salirMenuItem = new JMenuItem(labels.getString("menuitem.salir"));
        salirMenuItem.addActionListener(e -> VentanaPrincipal.this.dispose());
        configuracionMenu.add(salirMenuItem);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
    }
}
