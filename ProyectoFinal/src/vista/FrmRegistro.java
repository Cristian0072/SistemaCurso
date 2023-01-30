package vista;

import controlador.ControladorAlumno;
import controlador.ControladorAsignatura;
import controlador.ControladorCarrera;
import controlador.ControladorCiclo;
import controlador.ControladorDocente;
import controlador.ControladorMalla;
import controlador.ControladorMatricula;
import controlador.ControladorPeriodo;
import controlador.listas.excepciones.ListaNullException;
import controlador.listas.excepciones.PosicionNoEncontradaException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import vista.Utilidades.Utilidades;
import vista.modelo.ModeloTablaAlumno;
import vista.modelo.ModeloTablaAsignatura;
import vista.modelo.ModeloTablaCarrera;
import vista.modelo.ModeloTablaCiclo;
import vista.modelo.ModeloTablaDocente;
import vista.modelo.ModeloTablaMalla;
import vista.modelo.ModeloTablaMatricula;
import vista.modelo.ModeloTablaPeriodo;

/**
 * fecha: 21/01/2023
 *
 * @author: CEAS
 */
public class FrmRegistro extends javax.swing.JFrame {

    ControladorCarrera carrera = new ControladorCarrera();
    ModeloTablaCarrera mtc = new ModeloTablaCarrera();
    ControladorCiclo ciclo = new ControladorCiclo();
    ModeloTablaCiclo modeloTablaCiclo = new ModeloTablaCiclo();
    ControladorAsignatura asignatura = new ControladorAsignatura();
    ModeloTablaAsignatura mta = new ModeloTablaAsignatura();
    ControladorDocente docente = new ControladorDocente();
    ModeloTablaDocente mtd = new ModeloTablaDocente();
    ControladorPeriodo periodo = new ControladorPeriodo();
    ModeloTablaPeriodo mtp = new ModeloTablaPeriodo();
    ControladorMatricula matricula = new ControladorMatricula();
    ModeloTablaMatricula mtm = new ModeloTablaMatricula();
    ControladorAlumno alumno = new ControladorAlumno();
    ModeloTablaAlumno modeloTablaAlumno = new ModeloTablaAlumno();
    ControladorMalla malla = new ControladorMalla();
    ModeloTablaMalla modeloTablaMalla = new ModeloTablaMalla();

    public FrmRegistro() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * Método para guardar una carrera en un archivo json
     */
    private void guardarCarrera() {
        if (txtNombreCarrera.getText().trim().isEmpty() || cbxCiclos.getSelectedIndex() == -1 || cbxSeccion.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            Integer id = carrera.getCarrera().getId() + 1;
            carrera.getCarrera().setId(id);
            carrera.getCarrera().setCiclos(Utilidades.obtenerCiclos(cbxCiclos));
            carrera.getCarrera().setNombre(txtNombreCarrera.getText().trim());
            carrera.getCarrera().setSeccion(Utilidades.obtenerSeccion(cbxSeccion));
            carrera.getLista().insertar(carrera.getCarrera());
            if (carrera.guardar(carrera.getLista())) {
                JOptionPane.showMessageDialog(null, "Guardado correctamente", "OK", JOptionPane.OK_OPTION);
                cargarTablaCarrera();
                limpiarCarrera();
            }
        }
    }

    private void cargarTablaCarrera() {
        mtc.setLista(carrera.getLista());
        tblTablaCarrera.setModel(mtc);
        tblTablaCarrera.updateUI();
    }

    private void limpiarCarrera() {
        cbxCiclos.setSelectedIndex(-1);
        txtNombreCarrera.setText("");
        cbxSeccion.setSelectedIndex(-1);
        carrera.setCarrera(null);
    }

    /**
     * Método para guardar un ciclo en archivo json
     */
    private void guardarCiclo() {
        if (txtNombreCiclo.getText().trim().isEmpty() || cbxListaAsignaturas.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            Integer id = ciclo.getCiclo().getId() + 1;
            ciclo.getCiclo().setId(id);
            ciclo.getCiclo().setAsignaturas(Utilidades.obtenerAsignaturas(cbxListaAsignaturas));
            ciclo.getCiclo().setNombre(txtNombreCiclo.getText().trim());
            ciclo.getLista().insertar(ciclo.getCiclo());
            if (ciclo.guardar(ciclo.getLista())) {
                JOptionPane.showMessageDialog(null, "Guardado correctamente", "OK", JOptionPane.OK_OPTION);
                cargarTablaCiclo();
            }
        }
    }

    private void cargarTablaCiclo() {
        modeloTablaCiclo.setLista(ciclo.getLista());
        tblTablaCiclo.setModel(modeloTablaCiclo);
        tblTablaCiclo.updateUI();
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtNombreCiclo.setText("");
        cbxListaAsignaturas.setSelectedIndex(-1);
        ciclo.setCiclo(null);
    }

    /**
     * Método para guardar una asignatura en un archivo json
     */
    private void guardarAsignatura() {
        if (txtNombreAsignatura.getText().trim().isEmpty() || cbxParalelo.getSelectedIndex() == -1 || txtUnidad.getText().trim().isEmpty()
                || txtNroHoras.getText().trim().isEmpty() || cbxDocentes.getSelectedIndex() == -1 || cbxEstado.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            Integer id = asignatura.getAsignatura().getId() + 1;
            asignatura.getAsignatura().setId(id);
            asignatura.getAsignatura().setDocente(Utilidades.obtenerDocente(cbxDocentes));
            asignatura.getAsignatura().setEstadoAsignatura(Utilidades.obtenerEstado(cbxEstado));
            asignatura.getAsignatura().setNombreAsignatura(txtNombreAsignatura.getText().trim());
            asignatura.getAsignatura().setNumeroHoras(Integer.valueOf(txtNroHoras.getText().trim()));
            asignatura.getAsignatura().setParalelo(cbxParalelo.getSelectedItem().toString().charAt(0));
            asignatura.getAsignatura().setUnidad(txtUnidad.getText().trim());
            asignatura.getLista().insertar(asignatura.getAsignatura());
            if (asignatura.guardar(asignatura.getLista())) {
                JOptionPane.showMessageDialog(null, "Guardado correctamente", "OK", JOptionPane.OK_OPTION);
                cargarTablaAsignatura();
            }
        }
    }

    private void cargarTablaAsignatura() {
        mta.setLista(asignatura.getLista());
        tblTablaAsignatura.setModel(mta);
        tblTablaAsignatura.updateUI();
        limpiar();
    }

    private void limpiar() {
        txtNombreAsignatura.setText("");
        txtNroHoras.setText("");
        txtUnidad.setText("");
        cbxDocentes.setSelectedIndex(-1);
        cbxParalelo.setSelectedIndex(-1);
        cbxEstado.setSelectedIndex(-1);
        asignatura.setAsignatura(null);
        asignatura.setAsignatura(null);
    }

    /**
     * Método para guardar un nuevo docente en un archivo json
     */
    private void guardarDocente() {
        if (txtNombres.getText().trim().isEmpty() || txtApellidos.getText().trim().isEmpty() || cbxGenero.getSelectedIndex() == -1 || txtCedula.getText().trim().isEmpty()
                || txtCiudad.getText().trim().isEmpty() || txtDireccion.getText().trim().isEmpty() || txtTelefono.getText().trim().isEmpty() || date.getDate().toString().trim().isEmpty()
                || txtTitulo3.getText().trim().isEmpty() || txtTitulo4.getText().trim().isEmpty() || txtExpLaboral.getText().trim().isEmpty() || txtExpProfesional.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            int fila = docente.getLista().getTamanio() + 1;
            docente.getDocente().setIdDocente(fila);
            docente.getDocente().setApellidos(txtApellidos.getText().trim());
            docente.getDocente().setNombres(txtNombres.getText().trim());
            docente.getDocente().setAniosExpDocente(Integer.valueOf(txtExpProfesional.getText().trim()));
            docente.getDocente().setAniosExpLaboral(Integer.valueOf(txtExpLaboral.getText().trim()));
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
            docente.getDocente().setFechaNacimiento(formato.format(date));
            docente.getDocente().setCiudad(txtCiudad.getText().trim());
            docente.getDocente().setDireccion(txtDireccion.getText().trim());
            docente.getDocente().setGenero(Utilidades.obtenerGenero(cbxGenero));
            if (Utilidades.esCedulaValida(txtCedula.getText().trim())) {
                docente.getDocente().setIdentificacion(txtCedula.getText().trim());
            }
            docente.getDocente().setTelefono(txtTelefono.getText().trim());
            docente.getDocente().setTituloCuartoNivel(txtTitulo4.getText().trim());
            docente.getDocente().setTituloTercerNivel(txtTitulo3.getText().trim());
            docente.getLista().insertar(docente.getDocente());
            if (docente.guardar(docente.getLista())) {
                JOptionPane.showMessageDialog(null, "Guardado correctamente", "OK", JOptionPane.OK_OPTION);
                cargarTablaDocente();
                limpiarDocente();
            }
        }
    }

    private void cargarTablaDocente() {
        mtd.setLista(docente.getLista());
        tblDocente.setModel(mtd);
        tblDocente.updateUI();
    }

    private void limpiarDocente() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtTitulo3.setText("");
        txtTitulo4.setText("");
        date.setDate(new Date());
        txtCiudad.setText("");
        txtCedula.setText("");
        txtDireccion.setText("");
        cbxGenero.setSelectedIndex(-1);
        docente.setDocente(null);
    }

    /**
     * Método para guardar un periodo en un archio json
     */
    private void guardarPeriodo() {
        if (cbxMesFin.getSelectedIndex() == -1 || cbxMesInicio.getSelectedIndex() == -1 || cbxEspecificacion.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            int fila = periodo.getLista().getTamanio() + 1;
            periodo.getPeriodo().setIdPeriodo(fila);
            periodo.getPeriodo().setEspecificacion(Utilidades.obtenerEspecificacion(cbxEspecificacion));
            periodo.getPeriodo().setMesFin(Utilidades.obtenerMes(cbxMesFin));
            periodo.getPeriodo().setMesInicio(Utilidades.obtenerMes(cbxMesInicio));
            periodo.getLista().insertar(periodo.getPeriodo());
            if (periodo.guardar(periodo.getLista())) {
                JOptionPane.showMessageDialog(null, "Guardado correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
                cargarTablaPeriodo();
                limpiarPeriodo();
            }
        }
    }

    private void cargarTablaPeriodo() {
        mtp.setLista(periodo.getLista());
        tblTablaPeriodo.setModel(mtp);
        tblTablaPeriodo.updateUI();
    }

    private void limpiarPeriodo() {
        cbxMesFin.setSelectedIndex(-1);
        cbxMesInicio.setSelectedIndex(-1);
        cbxEspecificacion.setSelectedIndex(-1);
        periodo.setPeriodo(null);
    }

    /**
     * Método para guardar la matricula en una archivo json
     */
    private void guardarMatricula() {
        if (cbxPeriodo.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            int fila = matricula.getLista().getTamanio() + 1;
            matricula.getMatricula().setIdMatricula(fila);
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
            matricula.getMatricula().setFechaEmision(formato.format(new Date()));
            matricula.getMatricula().setPeriodo(Utilidades.obtenerPeriodo(cbxPeriodo));
            matricula.getLista().insertar(matricula.getMatricula());
            if (matricula.guardar(matricula.getLista())) {
                JOptionPane.showMessageDialog(null, "Guardado correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
                cargarTablaMatricula();
                limpiarMatricula();
            }
        }
    }

    private void cargarTablaMatricula() {
        mtm.setLista(matricula.getLista());
        tblTablaMatricula.setModel(mtm);
        tblTablaMatricula.updateUI();
    }

    private void limpiarMatricula() {
        cbxPeriodo.setSelectedIndex(-1);
        txtFechaEmision.setText("");
        cbxPeriodo.setSelectedIndex(-1);
        matricula.setMatricula(null);
    }

    /**
     * Método para guardar un alumno en una archivo json
     */
    private void guardarAlumno() {
        if (txtNombreAlumno.getText().trim().isEmpty() || txtApellidosAlumno.getText().trim().isEmpty() || cbxMatricula.getSelectedIndex() == -1 || txtIdentificacion.getText().trim().isEmpty()
                || cbxGeneroAlumno.getSelectedIndex() == -1 || txtDireccionAlumno.getText().trim().isEmpty() || txtNroTelefono.getText().trim().isEmpty() || txtCiudadEstudiante.getText().trim().isEmpty()
                || dateEstudiante.getDate().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            int fila = alumno.getLista().getTamanio() + 1;
            alumno.getAlumno().setIdAlumno(fila);
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
            alumno.getAlumno().setApellidos(txtApellidosAlumno.getText().trim());
            alumno.getAlumno().setCiudad(txtCiudadEstudiante.getText().trim());
            alumno.getAlumno().setDireccion(txtDireccionAlumno.getText().trim());
            alumno.getAlumno().setFechaNacimiento(formato.format(dateEstudiante));
            alumno.getAlumno().setGenero(Utilidades.obtenerGenero(cbxGeneroAlumno));
            if (Utilidades.esCedulaValida(txtIdentificacion.getText().trim())) {
                alumno.getAlumno().setIdentificacion(txtIdentificacion.getText().trim());
            }
            alumno.getAlumno().setMatricula(Utilidades.obtenerMatricula(cbxMatricula));
            alumno.getAlumno().setNombres(txtNombreAlumno.getText().trim());
            alumno.getAlumno().setTelefono(txtNroTelefono.getText().trim());
            alumno.getAlumno().setAsignatura(Utilidades.obtenerAsignatura(cbxAsignatura));
            if (alumno.guardar(alumno.getLista())) {
                JOptionPane.showMessageDialog(null, "Guardado correctamente", "OK", JOptionPane.OK_OPTION);
                cargarTablaAlumno();
                limpiarAlumno();
            }
        }
    }

    private void cargarTablaAlumno() {
        modeloTablaAlumno.setLista(alumno.getLista());
        tblTablaAlumno.setModel(modeloTablaAlumno);
        tblTablaAlumno.updateUI();
    }

    private void limpiarAlumno() {
        cbxPeriodo.setSelectedIndex(-1);
        txtApellidosAlumno.setText("");
        txtCiudadEstudiante.setText("");
        txtDireccionAlumno.setText("");
        cbxMatricula.setSelectedIndex(-1);
        cbxAsignatura.setSelectedIndex(-1);
        txtNombreAlumno.setText("");
        txtIdentificacion.setText("");
        cbxGeneroAlumno.setSelectedIndex(-1);
        dateEstudiante.setDate(null);
        alumno.setAlumno(null);
    }

    /**
     * Método para guardar una malla curricular en una archivo json
     */
    private void guardarMalla() {
        if (cbxListaCarreras.getSelectedIndex() == -1 || txtRegimen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            int fila = malla.getLista().getTamanio() + 1;
            malla.getMalla().setId(fila);
            malla.getMalla().setCarreras(Utilidades.obtenerCarreras(cbxListaCarreras));
            malla.getMalla().setRegimen(txtRegimen.getText().trim());
            malla.getLista().insertar(malla.getMalla());
            if (malla.guardar(malla.getLista())) {
                JOptionPane.showMessageDialog(null, "Guardado correctamente", "OK", JOptionPane.OK_OPTION);
                cargarTablaMalla();
                limpiarMalla();
            }
        }
    }

    private void cargarTablaMalla() {
        modeloTablaMalla.setLista(malla.getLista());
        tblTablaMalla.setModel(modeloTablaMalla);
        tblTablaMalla.updateUI();
    }

    private void limpiarMalla() {
        txtRegimen.setText("");
        cbxListaCarreras.setSelectedIndex(-1);
        malla.setMalla(null);
    }

    private void pintarBotones(JButton boton) {
        boton.setBackground(Color.YELLOW);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCiclo = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtNombreCiclo = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        cbxListaAsignaturas = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTablaCiclo = new javax.swing.JTable();
        btnAgregarCiclos = new javax.swing.JButton();
        panelPeriodo = new javax.swing.JPanel();
        cbxMesInicio = new javax.swing.JComboBox<>();
        cbxMesFin = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        cbxEspecificacion = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTablaPeriodo = new javax.swing.JTable();
        btnAgregarPeriodo = new javax.swing.JButton();
        panelMatricula = new javax.swing.JPanel();
        btnAgregarMatricula = new javax.swing.JButton();
        txtFechaEmision = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        cbxPeriodo = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblTablaMatricula = new javax.swing.JTable();
        panelAsignatura = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbxParalelo = new javax.swing.JComboBox<>();
        txtNroHoras = new javax.swing.JTextField();
        txtUnidad = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cbxDocentes = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cbxEstado = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtNombreAsignatura = new javax.swing.JTextField();
        btnAgregarAsignatura = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblTablaAsignatura = new javax.swing.JTable();
        panelMalla = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblTablaMalla = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        cbxListaCarreras = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        txtRegimen = new javax.swing.JTextField();
        btnAgregarMalla = new javax.swing.JButton();
        panelAlumno = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtApellidosAlumno = new javax.swing.JTextField();
        txtDireccionAlumno = new javax.swing.JTextField();
        btnAgregarAlumno = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtNombreAlumno = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        cbxMatricula = new javax.swing.JComboBox<>();
        txtIdentificacion = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtNroTelefono = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtCiudadEstudiante = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        dateEstudiante = new com.toedter.calendar.JDateChooser();
        jLabel40 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblTablaAlumno = new javax.swing.JTable();
        cbxGeneroAlumno = new javax.swing.JComboBox<>();
        btnActualizar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        cbxAsignatura = new javax.swing.JComboBox<>();
        panelCarrera = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombreCarrera = new javax.swing.JTextField();
        cbxSeccion = new javax.swing.JComboBox<>();
        btnCrearCarrera = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cbxCiclos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTablaCarrera = new javax.swing.JTable();
        panelDocente = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        cbxGenero = new javax.swing.JComboBox<>();
        btnAgreagarDocente = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDocente = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtCiudad = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        date = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtTitulo3 = new javax.swing.JTextField();
        txtTitulo4 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtExpLaboral = new javax.swing.JTextField();
        txtExpProfesional = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        btnMalla = new javax.swing.JButton();
        btnCarrera = new javax.swing.JButton();
        btnAlumno = new javax.swing.JButton();
        btnAsignatura = new javax.swing.JButton();
        btnCiclo = new javax.swing.JButton();
        btnMatricula = new javax.swing.JButton();
        btnPeriodo = new javax.swing.JButton();
        btnDocente = new javax.swing.JButton();
        panel = new javax.swing.JPanel();

        panelCiclo.setBackground(new java.awt.Color(255, 255, 51));

        jLabel16.setBackground(new java.awt.Color(0, 0, 0));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("LISTA DE ASIGNATURAS");

        txtNombreCiclo.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setBackground(new java.awt.Color(0, 0, 0));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("NOMBRE CICLO");

        cbxListaAsignaturas.setBackground(new java.awt.Color(255, 255, 255));
        cbxListaAsignaturas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbxListaAsignaturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxListaAsignaturasActionPerformed(evt);
            }
        });

        tblTablaCiclo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tblTablaCiclo);

        btnAgregarCiclos.setBackground(new java.awt.Color(153, 153, 153));
        btnAgregarCiclos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregarCiclos.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarCiclos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/crear.png"))); // NOI18N
        btnAgregarCiclos.setText("AGREGAR");
        btnAgregarCiclos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCiclosActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout panelCicloLayout = new org.jdesktop.layout.GroupLayout(panelCiclo);
        panelCiclo.setLayout(panelCicloLayout);
        panelCicloLayout.setHorizontalGroup(
            panelCicloLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelCicloLayout.createSequentialGroup()
                .add(20, 20, 20)
                .add(jLabel21)
                .add(76, 76, 76)
                .add(jLabel16))
            .add(panelCicloLayout.createSequentialGroup()
                .add(20, 20, 20)
                .add(txtNombreCiclo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(50, 50, 50)
                .add(cbxListaAsignaturas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 230, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(panelCicloLayout.createSequentialGroup()
                .add(130, 130, 130)
                .add(btnAgregarCiclos))
            .add(panelCicloLayout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 430, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        panelCicloLayout.setVerticalGroup(
            panelCicloLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelCicloLayout.createSequentialGroup()
                .add(20, 20, 20)
                .add(panelCicloLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel21)
                    .add(jLabel16))
                .add(panelCicloLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(txtNombreCiclo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(cbxListaAsignaturas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(20, 20, 20)
                .add(btnAgregarCiclos)
                .add(12, 12, 12)
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        panelPeriodo.setBackground(new java.awt.Color(255, 255, 51));

        cbxMesInicio.setBackground(new java.awt.Color(255, 255, 255));
        cbxMesInicio.setForeground(new java.awt.Color(0, 0, 0));

        cbxMesFin.setBackground(new java.awt.Color(255, 255, 255));
        cbxMesFin.setForeground(new java.awt.Color(0, 0, 0));

        jLabel26.setBackground(new java.awt.Color(0, 0, 0));
        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("ESPECIFICACION");

        jLabel27.setBackground(new java.awt.Color(0, 0, 0));
        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("MES INICIO");

        jLabel28.setBackground(new java.awt.Color(0, 0, 0));
        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("MES FIN");

        cbxEspecificacion.setBackground(new java.awt.Color(255, 255, 255));
        cbxEspecificacion.setForeground(new java.awt.Color(0, 0, 0));

        tblTablaPeriodo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tblTablaPeriodo);

        btnAgregarPeriodo.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregarPeriodo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregarPeriodo.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarPeriodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/crear.png"))); // NOI18N
        btnAgregarPeriodo.setText("AGREGAR");
        btnAgregarPeriodo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarPeriodo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAgregarPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPeriodoActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout panelPeriodoLayout = new org.jdesktop.layout.GroupLayout(panelPeriodo);
        panelPeriodo.setLayout(panelPeriodoLayout);
        panelPeriodoLayout.setHorizontalGroup(
            panelPeriodoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelPeriodoLayout.createSequentialGroup()
                .add(10, 10, 10)
                .add(panelPeriodoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(panelPeriodoLayout.createSequentialGroup()
                        .add(jLabel27)
                        .add(105, 105, 105)
                        .add(jLabel28))
                    .add(panelPeriodoLayout.createSequentialGroup()
                        .add(cbxMesInicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(50, 50, 50)
                        .add(cbxMesFin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jLabel26)
                    .add(panelPeriodoLayout.createSequentialGroup()
                        .add(cbxEspecificacion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(50, 50, 50)
                        .add(btnAgregarPeriodo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 140, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 330, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        panelPeriodoLayout.setVerticalGroup(
            panelPeriodoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelPeriodoLayout.createSequentialGroup()
                .add(10, 10, 10)
                .add(panelPeriodoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel27)
                    .add(jLabel28))
                .add(panelPeriodoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(cbxMesInicio, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(cbxMesFin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(20, 20, 20)
                .add(jLabel26)
                .add(panelPeriodoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(cbxEspecificacion, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnAgregarPeriodo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(60, 60, 60)
                .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 180, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        panelMatricula.setBackground(new java.awt.Color(255, 255, 102));
        panelMatricula.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAgregarMatricula.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregarMatricula.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregarMatricula.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/crear.png"))); // NOI18N
        btnAgregarMatricula.setText("AGREGAR");
        btnAgregarMatricula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarMatricula.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAgregarMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarMatriculaActionPerformed(evt);
            }
        });
        panelMatricula.add(btnAgregarMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 87, 140, 30));

        txtFechaEmision.setBackground(new java.awt.Color(0, 0, 0));
        txtFechaEmision.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtFechaEmision.setForeground(new java.awt.Color(0, 0, 0));
        panelMatricula.add(txtFechaEmision, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 36, 191, 30));

        jLabel30.setBackground(new java.awt.Color(0, 0, 0));
        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("PERIODO");
        panelMatricula.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        jLabel31.setBackground(new java.awt.Color(0, 0, 0));
        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("FECHA EMISION");
        panelMatricula.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        cbxPeriodo.setBackground(new java.awt.Color(255, 255, 255));
        cbxPeriodo.setForeground(new java.awt.Color(0, 0, 0));
        panelMatricula.add(cbxPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 32, 196, 34));

        tblTablaMatricula.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tblTablaMatricula);

        panelMatricula.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 129, 416, 140));

        panelAsignatura.setBackground(new java.awt.Color(255, 255, 102));
        panelAsignatura.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("PARALELO");
        panelAsignatura.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("UNIDAD");
        panelAsignatura.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("NRO. HORAS");
        panelAsignatura.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, -1, -1));

        cbxParalelo.setBackground(new java.awt.Color(255, 255, 255));
        cbxParalelo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxParalelo.setForeground(new java.awt.Color(0, 0, 0));
        cbxParalelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D", "E", "F", "G", "H" }));
        panelAsignatura.add(cbxParalelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 90, 30));

        txtNroHoras.setBackground(new java.awt.Color(255, 255, 255));
        panelAsignatura.add(txtNroHoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 90, 30));

        txtUnidad.setBackground(new java.awt.Color(255, 255, 255));
        panelAsignatura.add(txtUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 160, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("DOCENTE");
        panelAsignatura.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        cbxDocentes.setBackground(new java.awt.Color(255, 255, 255));
        cbxDocentes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxDocentes.setForeground(new java.awt.Color(0, 0, 0));
        panelAsignatura.add(cbxDocentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 210, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("ESTADO");
        panelAsignatura.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, -1, -1));

        cbxEstado.setBackground(new java.awt.Color(255, 255, 255));
        cbxEstado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxEstado.setForeground(new java.awt.Color(0, 0, 0));
        panelAsignatura.add(cbxEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 190, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("NOMBRE ASIGNATURA");
        panelAsignatura.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, -1));

        txtNombreAsignatura.setBackground(new java.awt.Color(255, 255, 255));
        panelAsignatura.add(txtNombreAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 220, 30));

        btnAgregarAsignatura.setBackground(new java.awt.Color(204, 204, 255));
        btnAgregarAsignatura.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregarAsignatura.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarAsignatura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/crear.png"))); // NOI18N
        btnAgregarAsignatura.setText("AGREGAR");
        btnAgregarAsignatura.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAgregarAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarAsignaturaActionPerformed(evt);
            }
        });
        panelAsignatura.add(btnAgregarAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 140, 40));

        tblTablaAsignatura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(tblTablaAsignatura);

        panelAsignatura.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 730, 140));

        panelMalla.setBackground(new java.awt.Color(255, 255, 102));
        panelMalla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblTablaMalla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(tblTablaMalla);

        panelMalla.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 420, 160));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("REGIMEN");
        panelMalla.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 140, -1));

        cbxListaCarreras.setBackground(new java.awt.Color(255, 255, 255));
        cbxListaCarreras.setForeground(new java.awt.Color(0, 0, 0));
        panelMalla.add(cbxListaCarreras, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, 30));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("LISTA DE CARRERAS");
        panelMalla.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, -1));

        txtRegimen.setBackground(new java.awt.Color(255, 255, 255));
        txtRegimen.setForeground(new java.awt.Color(0, 0, 0));
        panelMalla.add(txtRegimen, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 180, 30));

        btnAgregarMalla.setBackground(new java.awt.Color(204, 204, 255));
        btnAgregarMalla.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregarMalla.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarMalla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/crear.png"))); // NOI18N
        btnAgregarMalla.setText("AGREGAR");
        btnAgregarMalla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarMalla.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAgregarMalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarMallaActionPerformed(evt);
            }
        });
        panelMalla.add(btnAgregarMalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, -1));

        panelAlumno.setBackground(new java.awt.Color(255, 255, 153));
        panelAlumno.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("IDENTIFICACION");
        panelAlumno.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        txtApellidosAlumno.setBackground(new java.awt.Color(255, 255, 255));
        txtApellidosAlumno.setForeground(new java.awt.Color(0, 0, 0));
        panelAlumno.add(txtApellidosAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 170, 30));

        txtDireccionAlumno.setBackground(new java.awt.Color(255, 255, 255));
        txtDireccionAlumno.setForeground(new java.awt.Color(0, 0, 0));
        panelAlumno.add(txtDireccionAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, 200, 30));

        btnAgregarAlumno.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregarAlumno.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregarAlumno.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarAlumno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/crear.png"))); // NOI18N
        btnAgregarAlumno.setText("AGREGAR");
        btnAgregarAlumno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarAlumno.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAgregarAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarAlumnoActionPerformed(evt);
            }
        });
        panelAlumno.add(btnAgregarAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, 140, 40));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("DIRECCION");
        panelAlumno.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, -1, -1));

        jLabel33.setBackground(new java.awt.Color(0, 0, 0));
        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("MATRICULA");
        panelAlumno.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, -1, -1));

        txtNombreAlumno.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreAlumno.setForeground(new java.awt.Color(0, 0, 0));
        panelAlumno.add(txtNombreAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 170, 30));

        jLabel34.setBackground(new java.awt.Color(0, 0, 0));
        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("APELLIDOS");
        panelAlumno.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, -1));

        jLabel35.setBackground(new java.awt.Color(0, 0, 0));
        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("NOMBRES");
        panelAlumno.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        cbxMatricula.setBackground(new java.awt.Color(255, 255, 255));
        cbxMatricula.setForeground(new java.awt.Color(0, 0, 0));
        panelAlumno.add(cbxMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 190, 30));

        txtIdentificacion.setBackground(new java.awt.Color(255, 255, 255));
        txtIdentificacion.setForeground(new java.awt.Color(0, 0, 0));
        panelAlumno.add(txtIdentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 160, 30));

        jLabel37.setBackground(new java.awt.Color(0, 0, 0));
        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("GENERO");
        panelAlumno.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, -1, -1));

        txtNroTelefono.setBackground(new java.awt.Color(255, 255, 255));
        txtNroTelefono.setForeground(new java.awt.Color(0, 0, 0));
        panelAlumno.add(txtNroTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 160, 30));

        jLabel38.setBackground(new java.awt.Color(0, 0, 0));
        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("CIUDAD");
        panelAlumno.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, -1, -1));

        txtCiudadEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        txtCiudadEstudiante.setForeground(new java.awt.Color(0, 0, 0));
        panelAlumno.add(txtCiudadEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 200, 30));

        jLabel39.setBackground(new java.awt.Color(0, 0, 0));
        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("FECHA NACIMIENTO");
        panelAlumno.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, -1, -1));

        dateEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        dateEstudiante.setForeground(new java.awt.Color(0, 0, 0));
        panelAlumno.add(dateEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 200, 30));

        jLabel40.setBackground(new java.awt.Color(0, 0, 0));
        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("ASIGNATURA");
        panelAlumno.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        tblTablaAlumno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(tblTablaAlumno);

        panelAlumno.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 295, 650, 180));

        cbxGeneroAlumno.setBackground(new java.awt.Color(255, 255, 255));
        cbxGeneroAlumno.setForeground(new java.awt.Color(0, 0, 0));
        cbxGeneroAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxGeneroAlumnoActionPerformed(evt);
            }
        });
        panelAlumno.add(cbxGeneroAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 170, 30));

        btnActualizar.setBackground(new java.awt.Color(255, 255, 255));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(0, 0, 0));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/modificar.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        panelAlumno.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, -1, -1));

        btnBorrar.setBackground(new java.awt.Color(255, 255, 255));
        btnBorrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBorrar.setForeground(new java.awt.Color(0, 0, 0));
        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/borrar.png"))); // NOI18N
        btnBorrar.setText("BORRAR");
        btnBorrar.setAlignmentX(0.5F);
        btnBorrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBorrar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBorrar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        panelAlumno.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 250, -1, -1));

        jLabel43.setBackground(new java.awt.Color(0, 0, 0));
        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("TELEFONO");
        panelAlumno.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        panelAlumno.add(cbxAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 150, 30));

        panelCarrera.setBackground(new java.awt.Color(255, 255, 102));
        panelCarrera.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("SECCION");
        panelCarrera.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 80, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("NOMBRE");
        panelCarrera.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, -1));

        txtNombreCarrera.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreCarrera.setForeground(new java.awt.Color(0, 0, 0));
        panelCarrera.add(txtNombreCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 340, 30));

        cbxSeccion.setBackground(new java.awt.Color(255, 255, 255));
        cbxSeccion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxSeccion.setForeground(new java.awt.Color(0, 0, 0));
        cbxSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSeccionActionPerformed(evt);
            }
        });
        panelCarrera.add(cbxSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 290, 30));

        btnCrearCarrera.setBackground(new java.awt.Color(204, 204, 255));
        btnCrearCarrera.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCrearCarrera.setForeground(new java.awt.Color(0, 0, 0));
        btnCrearCarrera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/crear.png"))); // NOI18N
        btnCrearCarrera.setText("AGREGAR");
        btnCrearCarrera.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCrearCarrera.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCrearCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearCarreraActionPerformed(evt);
            }
        });
        panelCarrera.add(btnCrearCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("CICLOS");
        panelCarrera.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 80, -1));

        cbxCiclos.setBackground(new java.awt.Color(255, 255, 255));
        cbxCiclos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxCiclos.setForeground(new java.awt.Color(0, 0, 0));
        panelCarrera.add(cbxCiclos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 340, 30));

        tblTablaCarrera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblTablaCarrera);

        panelCarrera.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 680, 150));

        panelDocente.setBackground(new java.awt.Color(255, 255, 102));
        panelDocente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("GENERO");
        panelDocente.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 80, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("DIRECCION");
        panelDocente.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 80, -1));

        txtNombres.setBackground(new java.awt.Color(255, 255, 255));
        txtNombres.setForeground(new java.awt.Color(0, 0, 0));
        panelDocente.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 170, 30));

        cbxGenero.setBackground(new java.awt.Color(255, 255, 255));
        cbxGenero.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxGenero.setForeground(new java.awt.Color(0, 0, 0));
        panelDocente.add(cbxGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, 150, 30));

        btnAgreagarDocente.setBackground(new java.awt.Color(204, 204, 255));
        btnAgreagarDocente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgreagarDocente.setForeground(new java.awt.Color(0, 0, 0));
        btnAgreagarDocente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/crear.png"))); // NOI18N
        btnAgreagarDocente.setText("AGREGAR");
        btnAgreagarDocente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgreagarDocente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAgreagarDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgreagarDocenteActionPerformed(evt);
            }
        });
        panelDocente.add(btnAgreagarDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, 140, 30));

        tblDocente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblDocente);

        panelDocente.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 780, 160));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("NOMBRES");
        panelDocente.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, -1));

        txtApellidos.setBackground(new java.awt.Color(255, 255, 255));
        panelDocente.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 180, 30));

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("CEDULA");
        panelDocente.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, -1, -1));

        txtCedula.setBackground(new java.awt.Color(255, 255, 255));
        txtCedula.setForeground(new java.awt.Color(0, 0, 0));
        panelDocente.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, 190, 30));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("APELLIDOS");
        panelDocente.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 80, -1));

        txtDireccion.setBackground(new java.awt.Color(255, 255, 255));
        txtDireccion.setForeground(new java.awt.Color(0, 0, 0));
        panelDocente.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 180, 30));

        txtTelefono.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefono.setForeground(new java.awt.Color(0, 0, 0));
        panelDocente.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 140, 30));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("FECHA NACIMIENTO");
        panelDocente.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 70, 150, -1));

        txtCiudad.setBackground(new java.awt.Color(255, 255, 255));
        txtCiudad.setForeground(new java.awt.Color(0, 0, 0));
        panelDocente.add(txtCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 170, 30));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("TELEFONO");
        panelDocente.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, 80, -1));

        date.setBackground(new java.awt.Color(255, 255, 255));
        date.setForeground(new java.awt.Color(0, 0, 0));
        panelDocente.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, 190, 30));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("CIUDAD");
        panelDocente.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 90, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("TITULO 3ER. NIVEL");
        panelDocente.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 140, -1));

        txtTitulo3.setBackground(new java.awt.Color(255, 255, 255));
        txtTitulo3.setForeground(new java.awt.Color(0, 0, 0));
        panelDocente.add(txtTitulo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 170, 30));

        txtTitulo4.setBackground(new java.awt.Color(255, 255, 255));
        txtTitulo4.setForeground(new java.awt.Color(0, 0, 0));
        panelDocente.add(txtTitulo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 190, 30));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Exp. profesional");
        panelDocente.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 130, 140, -1));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("TITULO 4AR. NIVEL");
        panelDocente.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 140, -1));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Exp. laboral");
        panelDocente.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 140, -1));

        txtExpLaboral.setBackground(new java.awt.Color(255, 255, 255));
        txtExpLaboral.setForeground(new java.awt.Color(0, 0, 0));
        panelDocente.add(txtExpLaboral, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 140, 30));

        txtExpProfesional.setBackground(new java.awt.Color(255, 255, 255));
        txtExpProfesional.setForeground(new java.awt.Color(0, 0, 0));
        panelDocente.add(txtExpProfesional, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, 140, 30));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("REGISTRO");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));

        btnAtras.setBackground(new java.awt.Color(153, 204, 255));
        btnAtras.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAtras.setForeground(new java.awt.Color(255, 255, 255));
        btnAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/atras.png"))); // NOI18N
        btnAtras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        btnMalla.setBackground(new java.awt.Color(204, 204, 255));
        btnMalla.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMalla.setForeground(new java.awt.Color(0, 0, 0));
        btnMalla.setText("MALLA");
        btnMalla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMallaActionPerformed(evt);
            }
        });

        btnCarrera.setBackground(new java.awt.Color(204, 204, 255));
        btnCarrera.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCarrera.setForeground(new java.awt.Color(0, 0, 0));
        btnCarrera.setText("CARRERA");
        btnCarrera.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarreraActionPerformed(evt);
            }
        });

        btnAlumno.setBackground(new java.awt.Color(204, 204, 255));
        btnAlumno.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAlumno.setForeground(new java.awt.Color(0, 0, 0));
        btnAlumno.setText("ALUMNO");
        btnAlumno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlumnoActionPerformed(evt);
            }
        });

        btnAsignatura.setBackground(new java.awt.Color(204, 204, 255));
        btnAsignatura.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAsignatura.setForeground(new java.awt.Color(0, 0, 0));
        btnAsignatura.setText("ASIGNATURA");
        btnAsignatura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignaturaActionPerformed(evt);
            }
        });

        btnCiclo.setBackground(new java.awt.Color(204, 204, 255));
        btnCiclo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCiclo.setForeground(new java.awt.Color(0, 0, 0));
        btnCiclo.setText("CICLO");
        btnCiclo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCiclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCicloActionPerformed(evt);
            }
        });

        btnMatricula.setBackground(new java.awt.Color(204, 204, 255));
        btnMatricula.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnMatricula.setForeground(new java.awt.Color(0, 0, 0));
        btnMatricula.setText("MATRICULA");
        btnMatricula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMatriculaActionPerformed(evt);
            }
        });

        btnPeriodo.setBackground(new java.awt.Color(204, 204, 255));
        btnPeriodo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPeriodo.setForeground(new java.awt.Color(0, 0, 0));
        btnPeriodo.setText("PERIODO");
        btnPeriodo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPeriodoActionPerformed(evt);
            }
        });

        btnDocente.setBackground(new java.awt.Color(204, 204, 255));
        btnDocente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDocente.setForeground(new java.awt.Color(0, 0, 0));
        btnDocente.setText("DOCENTE");
        btnDocente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocenteActionPerformed(evt);
            }
        });

        panel.setLayout(new java.awt.CardLayout());

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(btnCarrera, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(720, 720, 720)
                        .add(btnAtras, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(btnDocente, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(btnMatricula, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(btnAsignatura)
                            .add(btnCiclo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(btnMalla, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(btnPeriodo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(btnAlumno, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(20, 20, 20)
                        .add(panel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 750, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(btnCarrera, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnAtras))
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(btnDocente, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(10, 10, 10)
                        .add(btnMatricula, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(10, 10, 10)
                        .add(btnAsignatura, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(10, 10, 10)
                        .add(btnCiclo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(10, 10, 10)
                        .add(btnMalla, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(10, 10, 10)
                        .add(btnPeriodo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(10, 10, 10)
                        .add(btnAlumno, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(panel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 420, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMatriculaActionPerformed
        pintarBotones(btnMatricula);
        panelMalla.setVisible(false);
        panelAsignatura.setVisible(false);
        panelCiclo.setVisible(false);
        panelPeriodo.setVisible(true);
        panelAlumno.setVisible(false);
        panelCarrera.setVisible(false);
        panelMatricula.setVisible(false);
        panel.add(panelMatricula);
        Utilidades.cargarPeriodos(cbxPeriodo);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtFechaEmision.setText(formato.format(new Date()));
            }
        });
        timer.start();
    }//GEN-LAST:event_btnMatriculaActionPerformed

    private void btnCicloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCicloActionPerformed
        pintarBotones(btnCiclo);
        btnAlumno.setBackground(Color.GRAY);
        panelMalla.setVisible(false);
        panelAsignatura.setVisible(false);
        panelCiclo.setVisible(true);
        panelPeriodo.setVisible(false);
        panelAlumno.setVisible(false);
        panelCarrera.setVisible(false);
        panelMatricula.setVisible(false);
        panel.add(panelCiclo);
        Utilidades.cargarAsignaturas(cbxAsignatura);
        cargarTablaCiclo();
    }//GEN-LAST:event_btnCicloActionPerformed

    private void btnAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignaturaActionPerformed
        pintarBotones(btnAsignatura);
        panelMalla.setVisible(false);
        panelAsignatura.setVisible(true);
        panelCiclo.setVisible(false);
        panelPeriodo.setVisible(false);
        panelAlumno.setVisible(false);
        panelCarrera.setVisible(false);
        panelMatricula.setVisible(false);
        Utilidades.cargarEstado(cbxEstado);
        Utilidades.cargarDocentes(cbxDocentes);
        panel.add(panelAsignatura);
        cargarTablaAsignatura();
    }//GEN-LAST:event_btnAsignaturaActionPerformed

    private void btnAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlumnoActionPerformed
        pintarBotones(btnAlumno);
        panel.add(panelAlumno);
        panelMalla.setVisible(false);
        panelAsignatura.setVisible(false);
        panelCiclo.setVisible(false);
        panelPeriodo.setVisible(false);
        panelAlumno.setVisible(true);
        panelCarrera.setVisible(false);
        panelMatricula.setVisible(false);
        Utilidades.cargarMatriculas(cbxMatricula);
        cargarTablaAlumno();
        Utilidades.cargarGeneros(cbxGeneroAlumno);
    }//GEN-LAST:event_btnAlumnoActionPerformed

    private void btnCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarreraActionPerformed
        pintarBotones(btnCarrera);
        panelCarrera.setVisible(false);
        panelMalla.setVisible(false);
        panelAsignatura.setVisible(false);
        panelCiclo.setVisible(false);
        panelPeriodo.setVisible(false);
        panelAlumno.setVisible(false);
        panelMatricula.setVisible(false);
        panel.add(panelCarrera);
        Utilidades.cargarCiclos(cbxCiclos);
        Utilidades.cargarSeccion(cbxSeccion);
        cargarTablaCarrera();
    }//GEN-LAST:event_btnCarreraActionPerformed

    private void btnMallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMallaActionPerformed
        pintarBotones(btnMalla);
        panelMalla.setVisible(false);
        panelAsignatura.setVisible(false);
        panelCiclo.setVisible(false);
        panelPeriodo.setVisible(false);
        panelAlumno.setVisible(false);
        panelCarrera.setVisible(false);
        panelMatricula.setVisible(false);
        panelDocente.setVisible(false);
        panel.add(panelMalla);
        cargarTablaMalla();
        Utilidades.cargarCarreras(cbxListaCarreras);
    }//GEN-LAST:event_btnMallaActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        new FrmLogin(null, false).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPeriodoActionPerformed
        panel.add(panelPeriodo);
        Utilidades.cargarComboMeses(cbxMesInicio);
        Utilidades.cargarComboMeses(cbxMesFin);
        Utilidades.cargarEspecificaciones(cbxEspecificacion);
        panelMalla.setVisible(false);
        panelAsignatura.setVisible(false);
        panelCiclo.setVisible(false);
        panelAlumno.setVisible(false);
        panelCarrera.setVisible(false);
        panelMatricula.setVisible(false);
        panelDocente.setVisible(false);
        cargarTablaPeriodo();
    }//GEN-LAST:event_btnPeriodoActionPerformed

    private void btnDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocenteActionPerformed
        panel.add(panelDocente);
        Utilidades.cargarGeneros(cbxGenero);
        panelMalla.setVisible(false);
        panelAsignatura.setVisible(false);
        panelCiclo.setVisible(false);
        panelAlumno.setVisible(false);
        panelCarrera.setVisible(false);
        panelMatricula.setVisible(false);
        panelDocente.setVisible(false);
        cargarTablaDocente();
    }//GEN-LAST:event_btnDocenteActionPerformed

    private void btnAgregarMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMatriculaActionPerformed
        guardarMatricula();
    }//GEN-LAST:event_btnAgregarMatriculaActionPerformed

    private void cbxGeneroAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxGeneroAlumnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxGeneroAlumnoActionPerformed

    private void btnAgregarAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarAlumnoActionPerformed
        guardarAlumno();
    }//GEN-LAST:event_btnAgregarAlumnoActionPerformed

    private void btnCrearCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearCarreraActionPerformed
        guardarCarrera();
    }//GEN-LAST:event_btnCrearCarreraActionPerformed

    private void cbxSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSeccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSeccionActionPerformed

    private void btnAgregarCiclosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCiclosActionPerformed
        guardarCiclo();
    }//GEN-LAST:event_btnAgregarCiclosActionPerformed

    private void btnAgregarAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarAsignaturaActionPerformed
        guardarAsignatura();
    }//GEN-LAST:event_btnAgregarAsignaturaActionPerformed

    private void btnAgreagarDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgreagarDocenteActionPerformed
        guardarDocente();
    }//GEN-LAST:event_btnAgreagarDocenteActionPerformed

    private void btnAgregarPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPeriodoActionPerformed
        guardarPeriodo();
    }//GEN-LAST:event_btnAgregarPeriodoActionPerformed

    private void cbxListaAsignaturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxListaAsignaturasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxListaAsignaturasActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            int fila = tblTablaAlumno.getSelectedRow();
            alumno.getLista().obtener(fila).setApellidos(txtApellidosAlumno.getText().trim());
            alumno.actualizar(alumno.getLista());
        } catch (PosicionNoEncontradaException | ListaNullException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        int fila = tblTablaAlumno.getSelectedRow();
        alumno.borrar(fila);
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnAgregarMallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMallaActionPerformed
        guardarMalla();
    }//GEN-LAST:event_btnAgregarMallaActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmRegistro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgreagarDocente;
    private javax.swing.JButton btnAgregarAlumno;
    private javax.swing.JButton btnAgregarAsignatura;
    private javax.swing.JButton btnAgregarCiclos;
    private javax.swing.JButton btnAgregarMalla;
    private javax.swing.JButton btnAgregarMatricula;
    private javax.swing.JButton btnAgregarPeriodo;
    private javax.swing.JButton btnAlumno;
    private javax.swing.JButton btnAsignatura;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCarrera;
    private javax.swing.JButton btnCiclo;
    private javax.swing.JButton btnCrearCarrera;
    private javax.swing.JButton btnDocente;
    private javax.swing.JButton btnMalla;
    private javax.swing.JButton btnMatricula;
    private javax.swing.JButton btnPeriodo;
    private javax.swing.JComboBox<String> cbxAsignatura;
    private javax.swing.JComboBox<String> cbxCiclos;
    private javax.swing.JComboBox<String> cbxDocentes;
    private javax.swing.JComboBox<String> cbxEspecificacion;
    private javax.swing.JComboBox<String> cbxEstado;
    private javax.swing.JComboBox<String> cbxGenero;
    private javax.swing.JComboBox<String> cbxGeneroAlumno;
    private javax.swing.JComboBox<String> cbxListaAsignaturas;
    private javax.swing.JComboBox<String> cbxListaCarreras;
    private javax.swing.JComboBox<String> cbxMatricula;
    private javax.swing.JComboBox<String> cbxMesFin;
    private javax.swing.JComboBox<String> cbxMesInicio;
    private javax.swing.JComboBox<String> cbxParalelo;
    private javax.swing.JComboBox<String> cbxPeriodo;
    private javax.swing.JComboBox<String> cbxSeccion;
    private com.toedter.calendar.JDateChooser date;
    private com.toedter.calendar.JDateChooser dateEstudiante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelAlumno;
    private javax.swing.JPanel panelAsignatura;
    private javax.swing.JPanel panelCarrera;
    private javax.swing.JPanel panelCiclo;
    private javax.swing.JPanel panelDocente;
    private javax.swing.JPanel panelMalla;
    private javax.swing.JPanel panelMatricula;
    private javax.swing.JPanel panelPeriodo;
    private javax.swing.JTable tblDocente;
    private javax.swing.JTable tblTablaAlumno;
    private javax.swing.JTable tblTablaAsignatura;
    private javax.swing.JTable tblTablaCarrera;
    private javax.swing.JTable tblTablaCiclo;
    private javax.swing.JTable tblTablaMalla;
    private javax.swing.JTable tblTablaMatricula;
    private javax.swing.JTable tblTablaPeriodo;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtApellidosAlumno;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtCiudadEstudiante;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDireccionAlumno;
    private javax.swing.JTextField txtExpLaboral;
    private javax.swing.JTextField txtExpProfesional;
    private javax.swing.JLabel txtFechaEmision;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNombreAlumno;
    private javax.swing.JTextField txtNombreAsignatura;
    private javax.swing.JTextField txtNombreCarrera;
    private javax.swing.JTextField txtNombreCiclo;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNroHoras;
    private javax.swing.JTextField txtNroTelefono;
    private javax.swing.JTextField txtRegimen;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTitulo3;
    private javax.swing.JTextField txtTitulo4;
    private javax.swing.JTextField txtUnidad;
    // End of variables declaration//GEN-END:variables

}
