package proyectomundial;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import proyectomundial.DAO.ResultadoDAO;
import proyectomundial.DAO.SeleccionDAO;
import proyectomundial.DAO.AuditoriaDAO;
import proyectomundial.model.Seleccion;

public class GUIManual extends JFrame {

    SeleccionDAO seleccionDAO = new SeleccionDAO();
    ResultadoDAO resultadoDAO = new ResultadoDAO();
    AuditoriaDAO auditoriaDAO = new AuditoriaDAO();

    // Matrix que permite almancenar la información de las selecciones futbol
    // cargadas
    public String[][] selecciones = null;

    // Matriz que permite almacenar los resultados de los partidos cargardos
    public String[][] resultados = null;

    // Matriz que permite almacenar los resultados cargados de auditoria
    public String[][] auditoria = null;

    // Elementos de bara Lateral
    private JPanel jPanelLeft;

    private JPanel jPanelIconFIFA;
    private JLabel iconFIFA;

    // Elementos de opciones de Menú
    private JPanel jPanelMenu;

    private JPanel jPanelMenuHome;
    private JLabel btnHome;

    private JPanel jPanelMenuSelecciones;
    private JLabel btnSelecciones;

    private JPanel jPanelMenuResultados;
    private JLabel btnResultados;

    private JPanel jPanelMenuDashboardSel;
    private JLabel btnDashboardSel;

    private JPanel jPanelMenuDashboardRes;
    private JLabel btnDashboardRes;

    private JPanel jPanelMenuAuditoria;
    private JLabel btnDashboardAud;

    // Elementos de panel de contenido
    private JPanel jPanelRight;
    private JPanel jPanelLabelTop;
    private JLabel jLabelTop;

    private JPanel jPanelMain;

    public GUIManual() {

        // Se inician los componentes gráficos
        initComponents();

        // Se configuran propiedades de nuestra Ventana
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        // Se llama la función home para que al momento de iniciar la aplicacoón, por
        // defecto se muestre el home
        accionHome();

    }

    private void initComponents() {

        // Inicializamos componentes del Menu Lateral
        jPanelLeft = new JPanel();

        jPanelIconFIFA = new JPanel();
        iconFIFA = new JLabel();
        jPanelMenu = new JPanel();

        jPanelMenuHome = new JPanel();
        btnHome = new JLabel();

        jPanelMenuSelecciones = new JPanel();
        btnSelecciones = new JLabel();

        jPanelMenuResultados = new JPanel();
        btnResultados = new JLabel();

        jPanelMenuDashboardSel = new JPanel();
        btnDashboardSel = new JLabel();

        jPanelMenuDashboardRes = new JPanel();
        btnDashboardRes = new JLabel();

        jPanelMenuAuditoria = new JPanel();
        btnDashboardAud = new JLabel();

        // Pinta el logo de la aplicación
        pintarLogo();

        // Pinta la opción de menú del Home
        pintarMenuHome();

        // Pinta la opción de Menú de las Selecciones
        pintarMenuSelecciones();

        // Pinta la opción de Menú de los resultados
        pintarMenuResultados();

        // Pinta la opción de Menú del dashboard de equipo
        pintarMenuDashboardSel();

        // Pinta la opción de Menú del dahboard de resultados
        pintarMenuDashboardRes();

        // Pintar la opción de Menú de auditoria
        pintarMenuAuditoria();

        // Pinta y ajuste diseño del contenedor del panel izquierdo
        pintarPanelIzquierdo();

        // Inicializa los componentes del panel derecho de los contenidos
        jPanelRight = new JPanel();
        jPanelLabelTop = new JPanel();
        jPanelMain = new JPanel();

        // Pinta la barra superrior de color azul claro, del panel de contenido
        pintarLabelTop();

        // Pinta y ajusta diseño del contenedor de contenidos
        pintarPanelDerecho();

        setTitle("Mundial");
        pack();
        setVisible(true);
    }

    private void pintarLogo() {
        jPanelIconFIFA.add(iconFIFA);
        jPanelIconFIFA.setOpaque(false);
        jPanelIconFIFA.setPreferredSize((new java.awt.Dimension(220, 80)));
        jPanelIconFIFA.setMaximumSize(jPanelIconFIFA.getPreferredSize());
        iconFIFA.setIcon(new ImageIcon(getClass().getResource("/resources/Easports_fifa_logo.svg.png")));
        jPanelLeft.add(jPanelIconFIFA, BorderLayout.LINE_START);

    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente la
     * opción de navegación del HOME
     * Define estilos, etiquetas, iconos que decoran la opción del Menú.
     * Esta opción de Menu permite mostrar la página de bienvenida de la aplicación
     */
    private void pintarMenuHome() {
        btnHome.setIcon(new ImageIcon(getClass().getResource("/resources/icons/home.png"))); // NOI18N
        btnHome.setText("Home");
        btnHome.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioHome = new JLabel();
        jPanelMenuHome.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuHome.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuHome.setLayout(new BorderLayout(15, 0));
        jPanelMenuHome.add(vacioHome, BorderLayout.WEST);
        jPanelMenuHome.add(btnHome, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuHome);

        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Home");
                accionHome();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hacer click sobre la opción de
     * navegación Home
     * Permite modificar la etiqueta de Navegación en Home, remover los elementos
     * que hay en
     * el panel de contenidos y agregar la imagen de inicio de la aplicación
     */
    private void accionHome() {
        jLabelTop.setText("Home");
        // jLabelTopDescription.setText("Bievenido al sistema de gestión de mundiales de
        // fútbol");

        jPanelMain.removeAll();
        JPanel homePanel = new JPanel();
        JLabel imageHome = new JLabel();

        imageHome.setIcon(new ImageIcon(getClass().getResource("/resources/home.jpg"))); // NOI18N
        // imageHome.setPreferredSize(new java.awt.Dimension(810, 465));
        homePanel.add(imageHome);

        auditoriaDAO.actualizarContador("Home");

        jPanelMain.add(homePanel, BorderLayout.CENTER);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente la
     * opción de navegación de SELECCIONES
     * Define estilos, etiquetas, iconos que decoran la opción del Menú.
     * Esta opción de Menu permite mostrar las selecciones de futbol cargadas en la
     * aplicación
     */
    private void pintarMenuSelecciones() {
        btnSelecciones.setIcon(new ImageIcon(getClass().getResource("/resources/icons/selecciones.png"))); // NOI18N
        btnSelecciones.setText("Selecciones");
        btnSelecciones.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioSelecciones = new JLabel();
        jPanelMenuSelecciones.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuSelecciones.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuSelecciones.setLayout(new BorderLayout(15, 0));
        jPanelMenuSelecciones.add(vacioSelecciones, BorderLayout.WEST);
        jPanelMenuSelecciones.add(btnSelecciones, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuSelecciones);

        btnSelecciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Selecciones");
                accionSelecciones();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de
     * navegación Selecciones
     * Permite ver la lista de selecciones que se encuentran cargadas en la
     * aplicación.
     * Si la lista de selecciones en vacía, muestra un botón que permite cargar un
     * archivo CSV
     * con la información de las selelecciones
     */
    private void accionSelecciones() {
        jLabelTop.setText("Selecciones");
        selecciones = seleccionDAO.getSeleccionesMatriz();

        auditoriaDAO.actualizarContador("Selecciones");

        // Si no hay selecciones cargadas, muestra el botón de carga de selecciones
        if (selecciones == null) {
            jPanelMain.removeAll();
            JPanel seleccionesPanel = new JPanel();

            JLabel notSelecciones = new JLabel();
            notSelecciones.setText("No hay selecciones cargadas, por favor cargue selecciones \n\n");
            seleccionesPanel.add(notSelecciones);

            JButton cargarFile = new JButton();
            cargarFile.setText("Seleccione el archivo");
            seleccionesPanel.add(cargarFile);
            cargarFile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    cargarFileSelecciones();
                }
            });

            jPanelMain.add(seleccionesPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        }
        // Si hay selecciones cargadas, llama el método que permite pintar la tabla de
        // selecciones
        else {
            pintarTablaSelecciones();
        }
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente la
     * opción de navegación de RESULTADOS
     * Define estilos, etiquetas, iconos que decoran la opción del Menú.
     * Esta opción de Menu permite mostrar los diferentes resultados de los partidos
     * de la fase de grupos de un mundial
     */
    private void pintarMenuResultados() {
        btnResultados.setIcon(new ImageIcon(getClass().getResource("/resources/icons/resultados.png"))); // NOI18N
        btnResultados.setText("Resultados");
        btnResultados.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioResultados = new JLabel();
        jPanelMenuResultados.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuResultados.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuResultados.setLayout(new BorderLayout(15, 0));
        jPanelMenuResultados.add(vacioResultados, BorderLayout.WEST);
        jPanelMenuResultados.add(btnResultados, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuResultados);

        btnResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Resultados");
                accionResultados();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de
     * navegación Resultados
     * Permite ver la lista de resultados que se encuentran cargadas en la
     * aplicación.
     * Si la lista de resultados en vacía, muestra un botón que permite cargar un
     * archivo CSV
     * con la información de los resultados
     */
    private void accionResultados() {
        jLabelTop.setText("Resultados");
        resultados = resultadoDAO.getResultadoMatriz();

        auditoriaDAO.actualizarContador("Resultados");

        // Si no hay resultados cargados, muestra el botón de carga de resultados
        if (resultados == null) {
            jPanelMain.removeAll();
            JPanel resultadosPanel = new JPanel();

            if (resultados == null) {

                JLabel notResultados = new JLabel();
                notResultados.setText("No hay resultados, por favor cargue resultados \n\n");
                resultadosPanel.add(notResultados);

                JButton cargarFile = new JButton();
                cargarFile.setText("Seleccione el archivo");
                resultadosPanel.add(cargarFile);
                cargarFile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        cargarFileResultados();
                    }
                });
            }

            jPanelMain.add(resultadosPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        }
        // Si hay ressultados cargados, llama el método que permite pintar la tabla de
        // resultados
        else {
            pintarTablaResultados();
        }
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente la
     * opción de navegación de Dashboard de Selecciones
     * Define estilos, etiquetas, iconos que decoran la opción del Menú.
     * Esta opción de Menu permite mostrar los diferentes datos que será extraidos
     * de la información de
     * las selecciones de futbol que fueron cargadas
     */
    private void pintarMenuDashboardSel() {
        btnDashboardSel.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_selecciones.png")));
        btnDashboardSel.setText("Dash Selecciones");
        btnDashboardSel.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardSelecciones = new JLabel();
        jPanelMenuDashboardSel.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuDashboardSel.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardSel.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardSel.add(vacioDashboardSelecciones, BorderLayout.WEST);
        jPanelMenuDashboardSel.add(btnDashboardSel, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardSel);

        btnDashboardSel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Dashboard Selecciones");
                accionDashboardSel();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE
     * Se debe módificar este método para poder calcular y pintar las diferentes
     * informaciones que son solicitadas
     * Revise el proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */

    /*
     * private void accionDashboardSel() {
     * jLabelTop.setText("Dash Selecciones");
     * JTextArea a = new JTextArea();
     * a.
     * setText("En esta sección, teniendo en cuenta los datos que fueron cargados en la matriz de selecciones \n"
     * + "se deben mostrar los siguientes datos:\n\n"
     * + "1. Total de selecciones Cargadas "+seleccionDAO.totalInt+"\n"
     * +
     * "2. Número de selecciones por continente (Se puede usar una tabla para pintar esto) \n"
     * + "3. Cantidad de nacionalidades diferentes de los directores técnicos \n"
     * + "4. Ranking de nacionalidades de directores técnicos \n\n"
     * +
     * "Utilice los diferentes componentes gráficos para construir un dashboard lo más estético posible"
     * );
     * 
     * jPanelMain.removeAll();
     * jPanelMain.add(a);
     * 
     * jPanelMain.repaint();
     * jPanelMain.revalidate();
     * }
     */
    private void accionDashboardSel() {
        jLabelTop.setText("Dash Selecciones");

        auditoriaDAO.actualizarContador("Dash Selecciones");

        selecciones = seleccionDAO.getSeleccionesMatriz();
        if (selecciones == null) {
            accionSelecciones();
        } else {
            String[] columnNames = { "ID", "Selección", "Continente", "DT", "Nacionalidad DT" };
            pintarTablaDashboardSel(columnNames);
        }
    }

    private void pintarTablaDashboardSel(String[] columnNames) {

        JTable table = new JTable(selecciones, columnNames);
        table.setRowHeight(20);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 10));

        JPanel panelSelecciones = new JPanel();
        panelSelecciones.setLayout(new GridLayout(1, 2, 30, 0));

        JLabel labelSelecciones = new JLabel();
        labelSelecciones.setText("Total de Selecciones cargadas: " + SeleccionDAO.totalInt);
        panelSelecciones.add(labelSelecciones);

        JButton verSelecciones = new JButton();
        verSelecciones.setText("Ir a Selecciones");
        verSelecciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                accionSelecciones();
            }
        });
        panelSelecciones.add(verSelecciones);

        form.add(panelSelecciones);

        JPanel panelDirectores = new JPanel();
        panelDirectores.setLayout(new GridLayout(1, 2, 30, 0));

        JLabel labelNacionalidad = new JLabel();
        labelNacionalidad.setText(
                "Total de nacionalidades diferentes de los DT: " + seleccionDAO.getNacionalidadDiferente().size());
        panelDirectores.add(labelNacionalidad);

        JButton verDirectores = new JButton();
        verDirectores.setText("Ver Ranking Nacionalidades DT");
        verDirectores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String[] newColumnNames = { "ID", "Nacionalidad DT", "Total" };
                selecciones = seleccionDAO.getRankingNacionalidadesDTMatriz();
                pintarTablaDashboardSel(newColumnNames);
            }
        });
        panelDirectores.add(verDirectores);

        form.add(panelDirectores);

        JPanel panelContinente = new JPanel();
        panelContinente.setLayout(new GridLayout(1, 2, 30, 0));

        JLabel labelContinente = new JLabel();
        labelContinente.setText("Seleccione un Continente");
        panelContinente.add(labelContinente);

        JComboBox<String> continentes = new JComboBox<String>();
        if (seleccionDAO.getContinenteDiferente() != null) {
            List<Seleccion> listContinente = seleccionDAO.getContinenteDiferente();
            continentes.addItem("");
            for (int i = 0; i < listContinente.size(); i++) {
                continentes.addItem(listContinente.get(i).getContinente());
            }
        }
        panelContinente.add(continentes);

        form.add(panelContinente);

        JButton verContinente = new JButton();
        verContinente.setText("Ver Selecciones del Continente seleccionado");
        verContinente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                selecciones = seleccionDAO.getSeleccionesMatriz((String) continentes.getSelectedItem());
                String[] columnNames = { "ID", "Selección", "Continente", "DT", "Nacionalidad DT" };
                pintarTablaDashboardSel(columnNames);
            }
        });

        form.add(verContinente);

        JPanel dashSeleccionesPanel = new JPanel();
        dashSeleccionesPanel.setLayout(new BoxLayout(dashSeleccionesPanel, BoxLayout.Y_AXIS));
        dashSeleccionesPanel.setPreferredSize((new java.awt.Dimension(610, 410)));
        dashSeleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        dashSeleccionesPanel.add(form);
        dashSeleccionesPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(dashSeleccionesPanel, BorderLayout.PAGE_START);

        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente la
     * opción de navegación de Dashboard de Resultados
     * Define estilos, etiquetas, iconos que decoran la opción del Menú.
     * Esta opción de Menu permite mostrar los diferentes datos que será extraidos
     * de la información de
     * los resultados de los partidos que fueron cargados
     */
    private void pintarMenuDashboardRes() {
        btnDashboardRes.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_resultados.png")));
        btnDashboardRes.setText("Dash Resultados");
        btnDashboardRes.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardResultados = new JLabel();
        jPanelMenuDashboardRes.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuDashboardRes.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardRes.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardRes.add(vacioDashboardResultados, BorderLayout.WEST);
        jPanelMenuDashboardRes.add(btnDashboardRes, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardRes);

        btnDashboardRes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Dashboard Resultados");
                accionDashboardRes();
            }
        });
    }

    private void pintarMenuAuditoria() {
        btnDashboardAud.setIcon(new ImageIcon(getClass().getResource("/resources/icons/home.png"))); // NOI18N
        btnDashboardAud.setText("Auditoria");
        btnDashboardAud.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioAuditoria = new JLabel();
        jPanelMenuAuditoria.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuAuditoria.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuAuditoria.setLayout(new BorderLayout(15, 0));
        jPanelMenuAuditoria.add(vacioAuditoria, BorderLayout.WEST);
        jPanelMenuAuditoria.add(btnDashboardAud, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuAuditoria);

        btnDashboardAud.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Auditoria");
                accionAuditoria();
            }
        });
    }

    private void accionAuditoria() {
        jLabelTop.setText("Auditoria");

        auditoria = auditoriaDAO.getAuditoriaMatriz();
        if (auditoria == null) {
            accionSelecciones();
        } else {
            pintarTablaAuditoria();
        }
    }

    private void pintarTablaAuditoria() {
        String[] columnNames = { "ID", "Pagina", "Contador" };
        JTable table = new JTable(auditoria, columnNames);
        table.setRowHeight(20);

        JPanel auditoriaPanel = new JPanel();
        auditoriaPanel.setLayout(new BoxLayout(auditoriaPanel, BoxLayout.Y_AXIS));
        auditoriaPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        auditoriaPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        // auditoriaPanel.add(form);
        auditoriaPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(auditoriaPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * TRABAJO DEL ESTUDIANTE
     * Se debe módificar este método para poder calcular y pintar las diferentes
     * informaciones que son solicitadas
     * Revise el proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    /*
     * private void accionDashboardRes() {
     * 
     * auditoriaDAO.actualizarContador("Dash Resultados");
     * 
     * JTextArea a = new JTextArea();
     * jLabelTop.setText("Dash Resultados");
     * a.
     * setText("En esta sección, teniendo en cuenta los datos que fueron cargados en la matriz de resultados \n"
     * + "se deben mostrar los siguientes datos:\n\n"
     * + "1. Número de partidos cargados \n"
     * + "2. Promedio de goles por partido \n"
     * + "3. Partido con más goles y partido con menos goles \n"
     * +
     * "  4. Número de partidos dónde hubo un ganador y número de partidos dónde hubo empate \n"
     * + "5. Selcción o selecciones con más goles y con menos goles \n"
     * + "6. Selección con más puntos y menos puntos \n"
     * + "7. Continente o continentes con más goles y menos goles \n"
     * +
     * "8. Clasificados por cada grupo (Clasifican los dos primeros equipos de cada grupo) \n\n"
     * +
     * "Utilice los diferentes componentes gráficos para construir un dashboard lo más estético posible"
     * );
     * 
     * jPanelMain.removeAll();
     * jPanelMain.add(a);
     * 
     * jPanelMain.repaint();
     * jPanelMain.revalidate();
     * }
     */

    private void accionDashboardRes() {
        jLabelTop.setText("Dash Resultados");

        auditoriaDAO.actualizarContador("Dash Resultados");

        resultados = resultadoDAO.getResultadoMatriz();
        if (resultados == null) {
            accionResultados();
        } else {
            pintarTablaDashboardRes();
        }
    }

    private void pintarTablaDashboardRes() {
        String[] columnNames = { "ID", "Grupo", "Local", "Visitante", "Continente L", "Continente V", "Goles L",
                "Goles V" };
        JTable table = new JTable(resultados, columnNames);
        table.setRowHeight(20);
        JPanel dashResultadosPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 10));

        JPanel panelResultados = new JPanel();
        panelResultados.setLayout(new GridLayout(1, 2, 30, 0));

        JLabel labelResultados = new JLabel();
        labelResultados.setText("<html><body>Total de Resultados cargados: " + ResultadoDAO.totalInt +
                "<br>Promedio de goles : " + resultadoDAO.promedioGoles + "</body></html>");
        panelResultados.add(labelResultados);

        JButton verResultados = new JButton();
        verResultados.setText("Ir a Resultados");
        verResultados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                accionResultados();
            }
        });
        panelResultados.add(verResultados);

        form.add(panelResultados);

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(1, 2, 30, 0));

        JLabel labelInfo1 = new JLabel();
        labelInfo1
                .setText("<html><body>Número partidos hubo un ganador: " + resultadoDAO.cantPartidosGanadorEmpate[0] +
                        "<br>Número partidos hubo empate: " + resultadoDAO.cantPartidosGanadorEmpate[1]
                        + "</body></html>");
        panelInfo.add(labelInfo1);

        JLabel labelInfo2 = new JLabel();
        labelInfo2
                .setText("<html><body>Selección con más puntos: " + resultadoDAO.seleccionPuntos[0] +
                        "<br>Selección con menos puntos: " + resultadoDAO.seleccionPuntos[1] + "</body></html>");
        panelInfo.add(labelInfo2);

        form.add(panelInfo);

        JPanel panelFiltro = new JPanel();
        panelFiltro.setLayout(new GridLayout(1, 2, 30, 0));

        JLabel labelFiltro = new JLabel();
        labelFiltro.setText("Seleccione un Filtro");
        panelFiltro.add(labelFiltro);

        JComboBox<String> filtro = new JComboBox<String>();
        filtro.addItem("");
        filtro.addItem("Partidos con más goles");
        filtro.addItem("Selcción o selecciones con más goles");
        filtro.addItem("Continente o continentes con más goles");
        filtro.addItem("Clasificados por cada grupo");

        panelFiltro.add(filtro);

        form.add(panelFiltro);

        JButton verFiltro = new JButton();
        verFiltro.setText("Aplicar filtro seleccionado");
        verFiltro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int idElement = filtro.getSelectedIndex();
                if (idElement == 0) {
                    dashResultadosPanel.remove(1);
                    JTable tabla = new JTable(resultados, columnNames);
                    tabla.setRowHeight(20);
                    JScrollPane scrollPanel = new JScrollPane(tabla);
                    dashResultadosPanel.add(scrollPanel);
                    jPanelMain.repaint();
                    jPanelMain.revalidate();
                } else if (idElement == 1) { // Partidos con más goles
                    dashResultadosPanel.remove(1);
                    JTable tabla = new JTable(resultadoDAO.getResultadoOrdenMayorGolesMatriz(), columnNames);
                    tabla.setRowHeight(20);
                    JScrollPane scrollPanel = new JScrollPane(tabla);
                    dashResultadosPanel.add(scrollPanel);
                    jPanelMain.repaint();
                    jPanelMain.revalidate();
                } else if (idElement == 2) { // Selcción o selecciones con más goles
                    dashResultadosPanel.remove(1);
                    String[] nameColumns = { "ID", "Selección", "Continente", "DT", "Nacionalidad DT", "Goles" };
                    JTable tabla = new JTable(resultadoDAO.getSeleccionOrdenMayorGolesMatriz(), nameColumns);
                    tabla.setRowHeight(20);
                    JScrollPane scrollPanel = new JScrollPane(tabla);
                    dashResultadosPanel.add(scrollPanel);
                    jPanelMain.repaint();
                    jPanelMain.revalidate();
                } else if (idElement == 3) { // Continente o continentes con más goles
                    dashResultadosPanel.remove(1);
                    String[] nameColumns = { "ID", "Continente", "Goles" };
                    JTable tabla = new JTable(resultadoDAO.getContinenteGoles(), nameColumns);
                    tabla.setRowHeight(20);
                    JScrollPane scrollPanel = new JScrollPane(tabla);
                    dashResultadosPanel.add(scrollPanel);
                    jPanelMain.repaint();
                    jPanelMain.revalidate();
                }
            }
        });
        form.add(verFiltro);

        dashResultadosPanel.setLayout(new BoxLayout(dashResultadosPanel, BoxLayout.Y_AXIS));
        dashResultadosPanel.setPreferredSize((new java.awt.Dimension(610, 410)));
        dashResultadosPanel.setMaximumSize(jPanelRight.getPreferredSize());

        dashResultadosPanel.add(form);
        dashResultadosPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(dashResultadosPanel, BorderLayout.PAGE_START);

        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que permite darle estilos y agregar los componentes gráficos del
     * contendor de la parte
     * izquierda de la interfaz, dónde se visulaiza el menú de navegaación
     */
    private void pintarPanelIzquierdo() {
        // Se elimina el color de fondo del panel del menú
        jPanelMenu.setOpaque(false);

        // Se agrega un border izquierdo, de color blanco para diferenciar el panel de
        // menú del panel de contenido
        jPanelLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.WHITE));

        // Se define un BoxLayot de manera vertical para los elementos del panel
        // izquierdo
        jPanelLeft.setLayout(new BoxLayout(jPanelLeft, BoxLayout.Y_AXIS));
        jPanelLeft.setBackground(new java.awt.Color(0, 24, 47));
        getContentPane().add(jPanelLeft, java.awt.BorderLayout.LINE_START);
        jPanelLeft.add(jPanelMenu);
        jPanelLeft.setPreferredSize((new java.awt.Dimension(220, 540)));
        jPanelLeft.setMaximumSize(jPanelLeft.getPreferredSize());
    }

    /**
     * Función que permite leer un archivo y procesar el contenido que tiene en cada
     * una de sus líneas
     * El contenido del archivo es procesado y cargado en la matriz de selecciones.
     * Una vez la información se carga
     * en la atriz, se hace un llamado a la función pintarTablaSelecciones() que se
     * encarga de pintar en la interfaz
     * una tabla con la información almacenada en la matriz de selecciones
     */
    public void cargarFileSelecciones() {

        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {

            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();

            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);

            // Permite que el sistema se salte la léctura de los encabzados del archivo CSV
            entrada.nextLine();

            // Se leen cada unas de las líneas del archivo
            while (entrada.hasNext()) {
                String line = entrada.nextLine();
                String[] columns = line.split(",");

                Seleccion seleccion = new Seleccion(columns[1], columns[2], columns[3], columns[4]);
                if (seleccionDAO.registrarSeleccion(seleccion)) {
                    System.out.println("Seleccion " + seleccion.getNombre() + " registrada");
                } else {
                    System.out.println("Error " + seleccion.getNombre());
                }
            }

            selecciones = seleccionDAO.getSeleccionesMatriz();
            pintarTablaSelecciones();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    /**
     * Función que se encarga de pinta la tabla con la información de las
     * selelceciones que fue cargada previamente
     * La tabla tiene definido un encabezado con las siguentes columnas:
     * {"ID","Selección", "Continente", "DT", "Nacionalidad DT"}
     * Columnas que se corresponden son la información que fue leida desde el
     * archivo csv
     */
    public void pintarTablaSelecciones() {

        String[] columnNames = { "ID", "Selección", "Continente", "DT", "Nacionalidad DT" };
        JTable table = new JTable(selecciones, columnNames);
        table.setRowHeight(20);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));

        JLabel label = new JLabel();
        label.setText("Busqueda de Equipos");
        form.add(label);

        JTextField field = new JTextField();
        form.add(field);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));

        JButton buscar = new JButton();
        buscar.setText("Buscar");
        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (!field.getText().equalsIgnoreCase("")) {
                    buscarSelecciones(field.getText());
                }
            }
        });
        panelBotones.add(buscar);

        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        limpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                selecciones = seleccionDAO.getSeleccionesMatriz();
                pintarTablaSelecciones();
            }
        });
        panelBotones.add(limpiar);
        form.add(panelBotones);

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    public void buscarSelecciones(String dato) {
        String[][] test = seleccionDAO.getSeleccionesMatriz(dato);
        if (test != null) {
            selecciones = test;
            pintarTablaSelecciones();
        } else {
            JOptionPane.showMessageDialog(jPanelMain, "Palabra desconocida.");
        }
    }

    /**
     * Función que tiene la lógica que permite leer un archivo CSV de resultados y
     * cargarlo
     * sobre la matriz resultados que se tiene definida cómo variable global.
     * Luego de cargar los datos en la matriz, se llama la función
     * pintarTablaResultados() que se encarga
     * de visulizar el contenido de la matriz en un componente gráfico de tabla
     */
    public void cargarFileResultados() {

        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {
            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();

            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);

            // Se define las dimensiones de la matriz de selecciones
            resultados = new String[48][7];
            entrada.nextLine();

            int i = 0;
            // Se iteran cada una de las líneas del archivo
            while (entrada.hasNext()) {
                System.out.println(i);
                String line = entrada.nextLine();
                String[] columns = line.split(",");

                for (int j = 0; j < columns.length; j++) {
                    resultados[i][j] = columns[j];
                }
                i++;
            }

            pintarTablaResultados();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    /**
     * Función que se encarga de pintar la tabla con la información de los
     * resultados que fue cargada previamente
     * La tabla tiene definido un encabezado con las siguentes columnas:
     * {"Grupo","Local", "Visitante", "Continente L", "Continente V", "Goles L",
     * "Goles V"}
     * Columnas que se corresponden son la información que fue leida desde el
     * archivo csv
     */
    public void pintarTablaResultados() {

        String[] columnNames = { "ID", "Grupo", "Local", "Visitante", "Continente L", "Continente V", "Goles L",
                "Goles V" };
        JTable table = new JTable(resultados, columnNames);
        table.setRowHeight(20);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));

        JLabel label = new JLabel();
        label.setText("Busqueda de Resultados");
        form.add(label);

        JTextField field = new JTextField();
        form.add(field);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));

        JButton buscar = new JButton();
        buscar.setText("Buscar");
        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (!field.getText().equalsIgnoreCase("")) {
                    buscarResultado(field.getText());
                }
            }
        });
        panelBotones.add(buscar);

        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        limpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resultados = resultadoDAO.getResultadoMatriz();
                pintarTablaResultados();
            }
        });
        panelBotones.add(limpiar);
        form.add(panelBotones);

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    public void buscarResultado(String dato) {
        String[][] test = resultadoDAO.getResultadoMatriz(dato);
        if (test != null) {
            resultados = test;
            pintarTablaResultados();
        } else {
            JOptionPane.showMessageDialog(jPanelMain, "Palabra desconocida.");
        }
    }

    /**
     * Función que permite darle estilos y agregar los componentes gráficos del
     * contendor de la parte
     * derecha de la interfaz, dónde se visulaiza de manera dinámica el contenido de
     * cada una de las funciones
     * que puede realizar el usuario sobre la aplicación.
     */
    private void pintarPanelDerecho() {

        // Define las dimensiones del panel
        jPanelMain.setPreferredSize((new java.awt.Dimension(620, 420)));
        jPanelMain.setMaximumSize(jPanelLabelTop.getPreferredSize());

        getContentPane().add(jPanelRight, java.awt.BorderLayout.CENTER);
        jPanelRight.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        jPanelRight.add(jPanelLabelTop, BorderLayout.LINE_START);
        jPanelRight.add(jPanelMain);
        jPanelRight.setPreferredSize((new java.awt.Dimension(620, 540)));
        jPanelRight.setMaximumSize(jPanelRight.getPreferredSize());
    }

    /**
     * Función que permite pinta la barra azul del contenedor de contenidos. Barra
     * azul que permite indicar
     * en que sección que se encuentra navegando el usuario.
     */
    private void pintarLabelTop() {
        jLabelTop = new JLabel();
        jLabelTop.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jLabelTop.setForeground(new java.awt.Color(241, 241, 241));
        jLabelTop.setText("Home");

        JLabel vacioTopLabel = new JLabel();
        jPanelLabelTop.setLayout(new BorderLayout(15, 0));
        jPanelLabelTop.add(vacioTopLabel, BorderLayout.WEST);
        jPanelLabelTop.setBackground(new java.awt.Color(18, 119, 217));
        jPanelLabelTop.add(jLabelTop, BorderLayout.CENTER);
        jPanelLabelTop.setPreferredSize((new java.awt.Dimension(620, 120)));
        jPanelLabelTop.setMaximumSize(jPanelLabelTop.getPreferredSize());
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIManual().setVisible(true);
            }
        });
    }
}