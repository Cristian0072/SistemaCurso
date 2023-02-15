package vista;

import controlador.ControladorAlumno;
import controlador.ControladorAsignatura;
import controlador.ControladorCarrera;
import controlador.ControladorCiclo;
import controlador.ControladorDocente;
import controlador.ControladorMalla;
import controlador.ControladorMatricula;
import controlador.ControladorPeriodo;
import controlador.listas.ListaEnlazada;
import controlador.listas.excepciones.AtributoException;
import controlador.listas.excepciones.ListaNullException;
import controlador.listas.excepciones.PosicionNoEncontradaException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
            JOptionPane.showMessageDialog(null, "Campos vacios", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            Integer id = carrera.getLista().getTamanio() + 1;
            carrera.getCarrera().setId(id);
            carrera.getCarrera().setCiclos(Utilidades.obtenerCiclos(cbxCiclos));
            carrera.getCarrera().setNombre(txtNombreCarrera.getText().trim());
            carrera.getCarrera().setSeccion(Utilidades.obtenerSeccion(cbxSeccion));
            carrera.getLista().insertar(carrera.getCarrera());
            if (carrera.guardar(carrera.getLista())) {
                JOptionPane.showMessageDialog(null, "Guardado correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Campos vacios", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            Integer id = ciclo.getLista().getTamanio() + 1;
            ciclo.getCiclo().setId(id);
            ciclo.getCiclo().setAsignaturas(Utilidades.obtenerAsignaturas(cbxListaAsignaturas));
            ciclo.getCiclo().setNombre(txtNombreCiclo.getText().trim());
            ciclo.getLista().insertar(ciclo.getCiclo());
            if (ciclo.guardar(ciclo.getLista())) {
                JOptionPane.showMessageDialog(null, "Guardado correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
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
        if (txtNombreAsignatura.getText().trim().isEmpty() || cbxParalelo.getSelectedIndex() == -1
                || txtNroHoras.getText().trim().isEmpty() || cbxDocentes.getSelectedIndex() == -1 || cbxEstado.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            Integer id = asignatura.getLista().getTamanio() + 1;
            asignatura.getAsignatura().setId(id);
            asignatura.getAsignatura().setDocente(Utilidades.obtenerDocente(cbxDocentes));
            asignatura.getAsignatura().setEstadoAsignatura(Utilidades.obtenerEstado(cbxEstado));
            asignatura.getAsignatura().setNombreAsignatura(txtNombreAsignatura.getText().trim());
            asignatura.getAsignatura().setNumeroHoras(Integer.valueOf(txtNroHoras.getText().trim()));
            asignatura.getAsignatura().setParalelo(cbxParalelo.getSelectedItem().toString().charAt(0));
            asignatura.getLista().insertar(asignatura.getAsignatura());
            if (asignatura.guardar(asignatura.getLista())) {
                JOptionPane.showMessageDialog(null, "Guardado correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
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
        cbxDocentes.setSelectedIndex(-1);
        cbxParalelo.setSelectedIndex(-1);
        cbxEstado.setSelectedIndex(-1);
        asignatura.setAsignatura(null);
    }

    /**
     * Método para guardar un nuevo docente en un archivo json
     */
    private void guardarDocente() {
        if (txtNombres.getText().trim().isEmpty() || txtApellidos.getText().trim().isEmpty() || cbxGenero.getSelectedIndex() == -1 || txtCedula.getText().trim().isEmpty()
                || txtCiudad.getText().trim().isEmpty() || txtDireccion.getText().trim().isEmpty() || txtTelefono.getText().trim().isEmpty() || date.getDate().toString().trim().isEmpty()
                || txtTitulo3.getText().trim().isEmpty() || txtTitulo4.getText().trim().isEmpty() || txtExpLaboral.getText().trim().isEmpty() || txtExpProfesional.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            int fila = docente.getLista().getTamanio() + 1;
            docente.getDocente().setIdDocente(fila);
            docente.getDocente().setApellidos(txtApellidos.getText().trim());
            docente.getDocente().setNombres(txtNombres.getText().trim());
            docente.getDocente().setAniosExpDocente(Integer.valueOf(txtExpProfesional.getText().trim()));
            docente.getDocente().setAniosExpLaboral(Integer.valueOf(txtExpLaboral.getText().trim()));
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            docente.getDocente().setFechaNacimiento(formato.format(date.getDate()));
            docente.getDocente().setCiudad(txtCiudad.getText().trim());
            docente.getDocente().setDireccion(txtDireccion.getText().trim());
            docente.getDocente().setGenero(Utilidades.obtenerGenero(cbxGenero));
            //   if (Utilidades.esCedulaValida(txtCedula.getText().trim())) {
            docente.getDocente().setIdentificacion(txtCedula.getText().trim());
            docente.getDocente().setTelefono(txtTelefono.getText().trim());
            docente.getDocente().setTituloCuartoNivel(txtTitulo4.getText().trim());
            docente.getDocente().setTituloTercerNivel(txtTitulo3.getText().trim());
            docente.getLista().insertar(docente.getDocente());
            if (docente.guardar(docente.getLista())) {
                JOptionPane.showMessageDialog(null, "Guardado correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
                btnAgregarDocente.setEnabled(false);
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
        date.setDate(null);
        txtCiudad.setText("");
        txtCedula.setText("");
        txtDireccion.setText("");
        cbxGenero.setSelectedIndex(-1);
        txtTelefono.setText("");
        txtExpLaboral.setText("");
        txtExpProfesional.setText("");
        docente.setDocente(null);
    }

    /**
     * Método para guardar un periodo en un archio json
     */
    private void guardarPeriodo() {
        if (cbxMesFin.getSelectedIndex() == -1 || cbxMesInicio.getSelectedIndex() == -1 || cbxEspecificacion.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "AVISO", JOptionPane.WARNING_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Campos vacios", "AVISO", JOptionPane.WARNING_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Campos vacios", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            int fila = alumno.getLista().getTamanio() + 1;
            alumno.getAlumno().setIdAlumno(fila);
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            alumno.getAlumno().setApellidos(txtApellidosAlumno.getText().trim());
            alumno.getAlumno().setCiudad(txtCiudadEstudiante.getText().trim());
            alumno.getAlumno().setDireccion(txtDireccionAlumno.getText().trim());
            alumno.getAlumno().setFechaNacimiento(formato.format(dateEstudiante.getDate()));
            alumno.getAlumno().setGenero(Utilidades.obtenerGenero(cbxGeneroAlumno));
            //    if (Utilidades.esCedulaValida(txtIdentificacion.getText().trim())) {
            alumno.getAlumno().setIdentificacion(txtIdentificacion.getText().trim());
            alumno.getAlumno().setMatricula(Utilidades.obtenerMatricula(cbxMatricula));
            alumno.getAlumno().setNombres(txtNombreAlumno.getText().trim());
            alumno.getAlumno().setTelefono(txtNroTelefono.getText().trim());
            alumno.getAlumno().setAsignatura(Utilidades.obtenerAsignatura(cbxAsignatura));
            alumno.getLista().insertar(alumno.getAlumno());
            if (alumno.guardar(alumno.getLista())) {
                JOptionPane.showMessageDialog(null, "Guardado correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
                btnAgregarAlumno.setEnabled(false);
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
        txtApellidosAlumno.setText("");
        txtCiudadEstudiante.setText("");
        txtDireccionAlumno.setText("");
        cbxMatricula.setSelectedIndex(-1);
        cbxAsignatura.setSelectedIndex(-1);
        txtNombreAlumno.setText("");
        txtIdentificacion.setText("");
        cbxGeneroAlumno.setSelectedIndex(-1);
        dateEstudiante.setDate(null);
        txtNroTelefono.setText("");
        alumno.setAlumno(null);
    }

    /**
     * Método para guardar una malla curricular en una archivo json
     */
    private void guardarMalla() {
        if (cbxListaCarreras.getSelectedIndex() == -1 || txtRegimen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            int fila = malla.getLista().getTamanio() + 1;
            malla.getMalla().setId(fila);
            malla.getMalla().setCarreras(Utilidades.obtenerCarreras(cbxListaCarreras));
            malla.getMalla().setRegimen(txtRegimen.getText().trim());
            malla.getLista().insertar(malla.getMalla());
            if (malla.guardar(malla.getLista())) {
                JOptionPane.showMessageDialog(null, "Guardado correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);
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

    private void despintarBotones() {
        btnAlumno.setBackground(Color.lightGray);
        btnAsignatura.setBackground(Color.lightGray);
        btnDocente.setBackground(Color.lightGray);
        btnCiclo.setBackground(Color.lightGray);
        btnCarrera.setBackground(Color.lightGray);
        btnMalla.setBackground(Color.lightGray);
        btnPeriodo.setBackground(Color.lightGray);
        btnMatricula.setBackground(Color.lightGray);
    }

    private void desabilitarPaneles() {
        panelMalla.setVisible(false);
        txtBuscar.setVisible(false);
        panelCiclo.setVisible(false);
        panelPeriodo.setVisible(false);
        panelAlumno.setVisible(false);
        panelCarrera.setVisible(false);
        panelMatricula.setVisible(false);
        panelDocente.setVisible(false);
    }

    private void cargarDatosDocente() {
        int fila = tblDocente.getSelectedRow();
        try {
            txtExpProfesional.setText(docente.listar().obtener(fila).getAniosExpDocente().toString());
            txtExpLaboral.setText(docente.listar().obtener(fila).getAniosExpLaboral().toString());
            txtCiudad.setText(docente.listar().obtener(fila).getCiudad());
            txtDireccion.setText(docente.listar().obtener(fila).getDireccion());
            txtNombres.setText(docente.listar().obtener(fila).getNombres());
            txtApellidos.setText(docente.listar().obtener(fila).getApellidos());
            cbxGenero.setSelectedItem(docente.listar().obtener(fila).getGenero());
            txtCedula.setText(docente.listar().obtener(fila).getIdentificacion());
            txtTelefono.setText(docente.listar().obtener(fila).getTelefono());
            txtTitulo3.setText(docente.listar().obtener(fila).getTituloTercerNivel());
            txtTitulo4.setText(docente.listar().obtener(fila).getTituloCuartoNivel());
            date.setDate(new Date(docente.listar().obtener(fila).getFechaNacimiento()));
            date.setEnabled(false);
        } catch (PosicionNoEncontradaException | ListaNullException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    private void cargarDatosAlumno() {
        int fila = tblTablaAlumno.getSelectedRow();
        try {
            txtCiudadEstudiante.setText(alumno.listar().obtener(fila).getCiudad());
            txtDireccionAlumno.setText(alumno.listar().obtener(fila).getDireccion());
            txtNombreAlumno.setText(alumno.listar().obtener(fila).getNombres());
            txtApellidosAlumno.setText(alumno.listar().obtener(fila).getApellidos());
            cbxGeneroAlumno.setSelectedItem(alumno.listar().obtener(fila).getGenero());
            txtIdentificacion.setText(alumno.listar().obtener(fila).getIdentificacion());
            txtNroTelefono.setText(alumno.listar().obtener(fila).getTelefono());
            dateEstudiante.setDate(new Date(alumno.listar().obtener(fila).getFechaNacimiento()));
            dateEstudiante.setEnabled(false);
            cbxAsignatura.setSelectedItem(alumno.listar().obtener(fila).getAsignatura());
            cbxMatricula.setSelectedItem(alumno.listar().obtener(fila).getMatricula());
        } catch (PosicionNoEncontradaException | ListaNullException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    private void cargarDatosAsignatura() {
        int fila = tblTablaAsignatura.getSelectedRow();
        try {
            txtNombreAsignatura.setText(asignatura.listar().obtener(fila).getNombreAsignatura());
            txtNroHoras.setText(asignatura.listar().obtener(fila).getNumeroHoras().toString());
            cbxEstado.setSelectedItem(asignatura.listar().obtener(fila).getEstadoAsignatura());
            cbxParalelo.setSelectedItem(asignatura.listar().obtener(fila).getParalelo());
            cbxDocentes.setSelectedItem(asignatura.listar().obtener(fila).getDocente());
        } catch (PosicionNoEncontradaException | ListaNullException ex) {
            System.out.println("Error " + ex.getMessage());
        }
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
        btnBorrarCiclo = new javax.swing.JButton();
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
        btnBorrarPeriodo = new javax.swing.JButton();
        panelMatricula = new javax.swing.JPanel();
        btnAgregarMatricula = new javax.swing.JButton();
        txtFechaEmision = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        cbxPeriodo = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblTablaMatricula = new javax.swing.JTable();
        btnBorrarMatricula = new javax.swing.JButton();
        txtBuscar = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbxParalelo = new javax.swing.JComboBox<>();
        txtNroHoras = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cbxDocentes = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cbxEstado = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtNombreAsignatura = new javax.swing.JTextField();
        btnAgregarAsignatura = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblTablaAsignatura = new javax.swing.JTable();
        btnBorrarAsignatura = new javax.swing.JButton();
        btnActualizarAsignatura = new javax.swing.JButton();
        txtBuscarAsignatura = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbxAtributoAsignatura = new javax.swing.JComboBox<>();
        panelMalla = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblTablaMalla = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        cbxListaCarreras = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        txtRegimen = new javax.swing.JTextField();
        btnAgregarMalla = new javax.swing.JButton();
        btnBorrarMalla = new javax.swing.JButton();
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
        jLabel36 = new javax.swing.JLabel();
        txtBuscarAlumno = new javax.swing.JTextField();
        cbxAtributoAlumno = new javax.swing.JComboBox<>();
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
        btnBorrarCarrera = new javax.swing.JButton();
        panelDocente = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        cbxGenero = new javax.swing.JComboBox<>();
        btnAgregarDocente = new javax.swing.JButton();
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
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        cbxOrdenacion = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbxAtributo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
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

        panelCiclo.setBackground(new java.awt.Color(255, 255, 102));
        panelCiclo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setBackground(new java.awt.Color(0, 0, 0));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("LISTA DE ASIGNATURAS");
        panelCiclo.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));

        txtNombreCiclo.setBackground(new java.awt.Color(255, 255, 255));
        panelCiclo.add(txtNombreCiclo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 140, 30));

        jLabel21.setBackground(new java.awt.Color(0, 0, 0));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("NOMBRE CICLO");
        panelCiclo.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        cbxListaAsignaturas.setBackground(new java.awt.Color(255, 255, 255));
        cbxListaAsignaturas.setForeground(new java.awt.Color(0, 0, 0));
        cbxListaAsignaturas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbxListaAsignaturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxListaAsignaturasActionPerformed(evt);
            }
        });
        panelCiclo.add(cbxListaAsignaturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 230, 30));

        tblTablaCiclo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTablaCiclo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTablaCicloMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblTablaCiclo);

        panelCiclo.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 430, 150));

        btnAgregarCiclos.setBackground(new java.awt.Color(204, 204, 255));
        btnAgregarCiclos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregarCiclos.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarCiclos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/crear.png"))); // NOI18N
        btnAgregarCiclos.setText("AGREGAR");
        btnAgregarCiclos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarCiclos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCiclosActionPerformed(evt);
            }
        });
        panelCiclo.add(btnAgregarCiclos, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, -1));

        btnBorrarCiclo.setBackground(new java.awt.Color(204, 204, 255));
        btnBorrarCiclo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBorrarCiclo.setForeground(new java.awt.Color(0, 0, 0));
        btnBorrarCiclo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/borrar.png"))); // NOI18N
        btnBorrarCiclo.setText("BORRAR");
        btnBorrarCiclo.setAlignmentX(0.5F);
        btnBorrarCiclo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBorrarCiclo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBorrarCiclo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBorrarCiclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarCicloActionPerformed(evt);
            }
        });
        panelCiclo.add(btnBorrarCiclo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 130, 40));

        panelPeriodo.setBackground(new java.awt.Color(255, 255, 102));
        panelPeriodo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxMesInicio.setBackground(new java.awt.Color(255, 255, 255));
        cbxMesInicio.setForeground(new java.awt.Color(0, 0, 0));
        panelPeriodo.add(cbxMesInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 130, 30));

        cbxMesFin.setBackground(new java.awt.Color(255, 255, 255));
        cbxMesFin.setForeground(new java.awt.Color(0, 0, 0));
        panelPeriodo.add(cbxMesFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 140, 30));

        jLabel26.setBackground(new java.awt.Color(0, 0, 0));
        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("ESPECIFICACION");
        panelPeriodo.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel27.setBackground(new java.awt.Color(0, 0, 0));
        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("MES INICIO");
        panelPeriodo.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel28.setBackground(new java.awt.Color(0, 0, 0));
        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("MES FIN");
        panelPeriodo.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        cbxEspecificacion.setBackground(new java.awt.Color(255, 255, 255));
        cbxEspecificacion.setForeground(new java.awt.Color(0, 0, 0));
        panelPeriodo.add(cbxEspecificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 130, 30));

        tblTablaPeriodo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTablaPeriodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTablaPeriodoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblTablaPeriodo);

        panelPeriodo.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 330, 180));

        btnAgregarPeriodo.setBackground(new java.awt.Color(204, 204, 255));
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
        panelPeriodo.add(btnAgregarPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 140, 40));

        btnBorrarPeriodo.setBackground(new java.awt.Color(204, 204, 255));
        btnBorrarPeriodo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBorrarPeriodo.setForeground(new java.awt.Color(0, 0, 0));
        btnBorrarPeriodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/borrar.png"))); // NOI18N
        btnBorrarPeriodo.setText("BORRAR");
        btnBorrarPeriodo.setAlignmentX(0.5F);
        btnBorrarPeriodo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBorrarPeriodo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBorrarPeriodo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBorrarPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarPeriodoActionPerformed(evt);
            }
        });
        panelPeriodo.add(btnBorrarPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 130, 40));

        panelMatricula.setBackground(new java.awt.Color(255, 255, 102));
        panelMatricula.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAgregarMatricula.setBackground(new java.awt.Color(204, 204, 255));
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
        panelMatricula.add(btnAgregarMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 140, 40));

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
        tblTablaMatricula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTablaMatriculaMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblTablaMatricula);

        panelMatricula.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 129, 416, 140));

        btnBorrarMatricula.setBackground(new java.awt.Color(204, 204, 255));
        btnBorrarMatricula.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBorrarMatricula.setForeground(new java.awt.Color(0, 0, 0));
        btnBorrarMatricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/borrar.png"))); // NOI18N
        btnBorrarMatricula.setText("BORRAR");
        btnBorrarMatricula.setAlignmentX(0.5F);
        btnBorrarMatricula.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBorrarMatricula.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBorrarMatricula.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBorrarMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarMatriculaActionPerformed(evt);
            }
        });
        panelMatricula.add(btnBorrarMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 130, 40));

        txtBuscar.setBackground(new java.awt.Color(255, 255, 102));
        txtBuscar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("PARALELO");
        txtBuscar.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("NRO. HORAS");
        txtBuscar.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, -1, -1));

        cbxParalelo.setBackground(new java.awt.Color(255, 255, 255));
        cbxParalelo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxParalelo.setForeground(new java.awt.Color(0, 0, 0));
        cbxParalelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D", "E", "F", "G", "H" }));
        txtBuscar.add(cbxParalelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 90, 30));

        txtNroHoras.setBackground(new java.awt.Color(255, 255, 255));
        txtNroHoras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNroHorasKeyTyped(evt);
            }
        });
        txtBuscar.add(txtNroHoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, 90, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("DOCENTE");
        txtBuscar.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        cbxDocentes.setBackground(new java.awt.Color(255, 255, 255));
        cbxDocentes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxDocentes.setForeground(new java.awt.Color(0, 0, 0));
        txtBuscar.add(cbxDocentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 210, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("ESTADO");
        txtBuscar.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

        cbxEstado.setBackground(new java.awt.Color(255, 255, 255));
        cbxEstado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxEstado.setForeground(new java.awt.Color(0, 0, 0));
        txtBuscar.add(cbxEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 190, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("NOMBRE ASIGNATURA");
        txtBuscar.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, -1));

        txtNombreAsignatura.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreAsignatura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreAsignaturaKeyTyped(evt);
            }
        });
        txtBuscar.add(txtNombreAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 220, 30));

        btnAgregarAsignatura.setBackground(new java.awt.Color(204, 204, 255));
        btnAgregarAsignatura.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregarAsignatura.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarAsignatura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/crear.png"))); // NOI18N
        btnAgregarAsignatura.setText("AGREGAR");
        btnAgregarAsignatura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarAsignatura.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAgregarAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarAsignaturaActionPerformed(evt);
            }
        });
        txtBuscar.add(btnAgregarAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, 140, 40));

        tblTablaAsignatura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTablaAsignatura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTablaAsignaturaMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblTablaAsignatura);

        txtBuscar.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 730, 150));

        btnBorrarAsignatura.setBackground(new java.awt.Color(204, 204, 255));
        btnBorrarAsignatura.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBorrarAsignatura.setForeground(new java.awt.Color(0, 0, 0));
        btnBorrarAsignatura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/borrar.png"))); // NOI18N
        btnBorrarAsignatura.setText("BORRAR");
        btnBorrarAsignatura.setAlignmentX(0.5F);
        btnBorrarAsignatura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBorrarAsignatura.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBorrarAsignatura.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBorrarAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarAsignaturaActionPerformed(evt);
            }
        });
        txtBuscar.add(btnBorrarAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, 130, 40));

        btnActualizarAsignatura.setBackground(new java.awt.Color(204, 204, 255));
        btnActualizarAsignatura.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnActualizarAsignatura.setForeground(new java.awt.Color(0, 0, 0));
        btnActualizarAsignatura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/actualizar.png"))); // NOI18N
        btnActualizarAsignatura.setText("ACTUALIZAR");
        btnActualizarAsignatura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarAsignaturaActionPerformed(evt);
            }
        });
        txtBuscar.add(btnActualizarAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, -1, 40));

        txtBuscarAsignatura.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarAsignatura.setForeground(new java.awt.Color(0, 0, 0));
        txtBuscarAsignatura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarAsignaturaKeyReleased(evt);
            }
        });
        txtBuscar.add(txtBuscarAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 210, 40));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/buscar.png"))); // NOI18N
        txtBuscar.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 40));

        cbxAtributoAsignatura.setBackground(new java.awt.Color(255, 255, 255));
        cbxAtributoAsignatura.setForeground(new java.awt.Color(0, 0, 0));
        cbxAtributoAsignatura.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ESTADOASIGNATURA", "NUMEROHORAS", "DOCENTE", "NOMBREASIGNATURA" }));
        txtBuscar.add(cbxAtributoAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 190, 30));

        panelMalla.setBackground(new java.awt.Color(255, 255, 102));
        panelMalla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblTablaMalla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTablaMalla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTablaMallaMouseClicked(evt);
            }
        });
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
        panelMalla.add(btnAgregarMalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, -1, -1));

        btnBorrarMalla.setBackground(new java.awt.Color(204, 204, 255));
        btnBorrarMalla.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBorrarMalla.setForeground(new java.awt.Color(0, 0, 0));
        btnBorrarMalla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/borrar.png"))); // NOI18N
        btnBorrarMalla.setText("BORRAR");
        btnBorrarMalla.setAlignmentX(0.5F);
        btnBorrarMalla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBorrarMalla.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBorrarMalla.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBorrarMalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarMallaActionPerformed(evt);
            }
        });
        panelMalla.add(btnBorrarMalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 130, 40));

        panelAlumno.setBackground(new java.awt.Color(255, 255, 102));
        panelAlumno.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("IDENTIFICACION");
        panelAlumno.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        txtApellidosAlumno.setBackground(new java.awt.Color(255, 255, 255));
        txtApellidosAlumno.setForeground(new java.awt.Color(0, 0, 0));
        txtApellidosAlumno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidosAlumnoKeyTyped(evt);
            }
        });
        panelAlumno.add(txtApellidosAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 200, 30));

        txtDireccionAlumno.setBackground(new java.awt.Color(255, 255, 255));
        txtDireccionAlumno.setForeground(new java.awt.Color(0, 0, 0));
        panelAlumno.add(txtDireccionAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 250, 30));

        btnAgregarAlumno.setBackground(new java.awt.Color(204, 204, 255));
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
        panelAlumno.add(btnAgregarAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, 140, 40));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("DIRECCION");
        panelAlumno.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, -1, -1));

        jLabel33.setBackground(new java.awt.Color(0, 0, 0));
        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("MATRICULA");
        panelAlumno.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, -1, -1));

        txtNombreAlumno.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreAlumno.setForeground(new java.awt.Color(0, 0, 0));
        txtNombreAlumno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreAlumnoKeyTyped(evt);
            }
        });
        panelAlumno.add(txtNombreAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 170, 30));

        jLabel34.setBackground(new java.awt.Color(0, 0, 0));
        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("APELLIDOS");
        panelAlumno.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, -1));

        jLabel35.setBackground(new java.awt.Color(0, 0, 0));
        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("NOMBRES");
        panelAlumno.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        cbxMatricula.setBackground(new java.awt.Color(255, 255, 255));
        cbxMatricula.setForeground(new java.awt.Color(0, 0, 0));
        panelAlumno.add(cbxMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 250, 30));

        txtIdentificacion.setBackground(new java.awt.Color(255, 255, 255));
        txtIdentificacion.setForeground(new java.awt.Color(0, 0, 0));
        txtIdentificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdentificacionActionPerformed(evt);
            }
        });
        txtIdentificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyTyped(evt);
            }
        });
        panelAlumno.add(txtIdentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 170, 30));

        jLabel37.setBackground(new java.awt.Color(0, 0, 0));
        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("GENERO");
        panelAlumno.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, -1, -1));

        txtNroTelefono.setBackground(new java.awt.Color(255, 255, 255));
        txtNroTelefono.setForeground(new java.awt.Color(0, 0, 0));
        txtNroTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNroTelefonoKeyTyped(evt);
            }
        });
        panelAlumno.add(txtNroTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 170, 30));

        jLabel38.setBackground(new java.awt.Color(0, 0, 0));
        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("CIUDAD");
        panelAlumno.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, -1, -1));

        txtCiudadEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        txtCiudadEstudiante.setForeground(new java.awt.Color(0, 0, 0));
        panelAlumno.add(txtCiudadEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 200, 30));

        jLabel39.setBackground(new java.awt.Color(0, 0, 0));
        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("FECHA NACIMIENTO");
        panelAlumno.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, -1, -1));

        dateEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        dateEstudiante.setForeground(new java.awt.Color(0, 0, 0));
        panelAlumno.add(dateEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, 220, 30));

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
        tblTablaAlumno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTablaAlumnoMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblTablaAlumno);

        panelAlumno.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 740, 160));

        cbxGeneroAlumno.setBackground(new java.awt.Color(255, 255, 255));
        cbxGeneroAlumno.setForeground(new java.awt.Color(0, 0, 0));
        cbxGeneroAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxGeneroAlumnoActionPerformed(evt);
            }
        });
        panelAlumno.add(cbxGeneroAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 200, 30));

        btnActualizar.setBackground(new java.awt.Color(204, 204, 255));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(0, 0, 0));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/actualizar.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        panelAlumno.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 250, -1, 40));

        btnBorrar.setBackground(new java.awt.Color(204, 204, 255));
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
        panelAlumno.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, 130, 40));

        jLabel43.setBackground(new java.awt.Color(0, 0, 0));
        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("TELEFONO");
        panelAlumno.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        cbxAsignatura.setBackground(new java.awt.Color(255, 255, 255));
        panelAlumno.add(cbxAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 210, 30));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/buscar.png"))); // NOI18N
        panelAlumno.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, 40));

        txtBuscarAlumno.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarAlumno.setForeground(new java.awt.Color(0, 0, 0));
        txtBuscarAlumno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarAlumnoKeyReleased(evt);
            }
        });
        panelAlumno.add(txtBuscarAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 210, 40));

        cbxAtributoAlumno.setBackground(new java.awt.Color(255, 255, 255));
        cbxAtributoAlumno.setForeground(new java.awt.Color(0, 0, 0));
        cbxAtributoAlumno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GENERO", "APELLIDOS", "IDENTIFICACION", "TELEFONO", "DIRECCION", "CIUDAD", "FECHANACIMIENTO", "NOMBRES", "MATRICULA", "ASIGNATURA" }));
        panelAlumno.add(cbxAtributoAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 210, 30));

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
        txtNombreCarrera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreCarreraKeyTyped(evt);
            }
        });
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
        tblTablaCarrera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTablaCarreraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTablaCarrera);

        panelCarrera.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 680, 150));

        btnBorrarCarrera.setBackground(new java.awt.Color(204, 204, 255));
        btnBorrarCarrera.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBorrarCarrera.setForeground(new java.awt.Color(0, 0, 0));
        btnBorrarCarrera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/borrar.png"))); // NOI18N
        btnBorrarCarrera.setText("BORRAR");
        btnBorrarCarrera.setAlignmentX(0.5F);
        btnBorrarCarrera.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBorrarCarrera.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBorrarCarrera.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBorrarCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarCarreraActionPerformed(evt);
            }
        });
        panelCarrera.add(btnBorrarCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, 130, 40));

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
        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombresKeyTyped(evt);
            }
        });
        panelDocente.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 170, 30));

        cbxGenero.setBackground(new java.awt.Color(255, 255, 255));
        cbxGenero.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbxGenero.setForeground(new java.awt.Color(0, 0, 0));
        panelDocente.add(cbxGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, 150, 30));

        btnAgregarDocente.setBackground(new java.awt.Color(204, 204, 255));
        btnAgregarDocente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAgregarDocente.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarDocente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/crear.png"))); // NOI18N
        btnAgregarDocente.setText("AGREGAR");
        btnAgregarDocente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarDocente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAgregarDocente.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAgregarDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDocenteActionPerformed(evt);
            }
        });
        panelDocente.add(btnAgregarDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, 130, 40));

        tblDocente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblDocente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDocenteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDocente);

        panelDocente.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 780, 160));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("NOMBRES");
        panelDocente.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, -1));

        txtApellidos.setBackground(new java.awt.Color(255, 255, 255));
        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidosKeyTyped(evt);
            }
        });
        panelDocente.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 180, 30));

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("CEDULA");
        panelDocente.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, -1, -1));

        txtCedula.setBackground(new java.awt.Color(255, 255, 255));
        txtCedula.setForeground(new java.awt.Color(0, 0, 0));
        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaKeyTyped(evt);
            }
        });
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
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
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
        txtExpLaboral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtExpLaboralKeyTyped(evt);
            }
        });
        panelDocente.add(txtExpLaboral, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 140, 30));

        txtExpProfesional.setBackground(new java.awt.Color(255, 255, 255));
        txtExpProfesional.setForeground(new java.awt.Color(0, 0, 0));
        txtExpProfesional.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtExpProfesionalKeyTyped(evt);
            }
        });
        panelDocente.add(txtExpProfesional, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, 140, 30));

        btnModificar.setBackground(new java.awt.Color(204, 204, 255));
        btnModificar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(0, 0, 0));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/actualizar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        panelDocente.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 200, 140, 40));

        btnEliminar.setBackground(new java.awt.Color(204, 204, 255));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/borrar.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        panelDocente.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 130, 40));

        cbxOrdenacion.setBackground(new java.awt.Color(255, 255, 255));
        cbxOrdenacion.setForeground(new java.awt.Color(0, 0, 0));
        cbxOrdenacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASCENDENTE", "DESCENDENTE" }));
        cbxOrdenacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxOrdenacionActionPerformed(evt);
            }
        });
        panelDocente.add(cbxOrdenacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 120, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("ATRIBUTO");
        panelDocente.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, -1, 20));

        cbxAtributo.setBackground(new java.awt.Color(255, 255, 255));
        cbxAtributo.setForeground(new java.awt.Color(0, 0, 0));
        cbxAtributo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IDENTIFICACION", "TELEFONO", "CIUDAD", "NOMBRES", "APELLIDOS", "GENERO", "FECHANACIMIENTO" }));
        panelDocente.add(cbxAtributo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 130, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("ORDENAR");
        panelDocente.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, 20));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("REGISTRO");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel1.add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 50, -1));

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
        jPanel1.add(btnMalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 120, 50));

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
        jPanel1.add(btnCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 50));

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
        jPanel1.add(btnAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 120, 50));

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
        jPanel1.add(btnAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, 50));

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
        jPanel1.add(btnCiclo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 120, 50));

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
        jPanel1.add(btnMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 120, 50));

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
        jPanel1.add(btnPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 120, 50));

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
        jPanel1.add(btnDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 120, 50));

        panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.LINE_AXIS));
        jPanel1.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 800, 420));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMatriculaActionPerformed
        despintarBotones();
        pintarBotones(btnMatricula);
        desabilitarPaneles();
        panel.add(panelMatricula);
        panelMatricula.setVisible(true);
        Utilidades.cargarPeriodos(cbxPeriodo);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtFechaEmision.setText(formato.format(new Date()));
            }
        });
        timer.start();
        cargarTablaMatricula();
        btnBorrarMatricula.setEnabled(false);
    }//GEN-LAST:event_btnMatriculaActionPerformed

    private void btnCicloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCicloActionPerformed
        despintarBotones();
        pintarBotones(btnCiclo);
        desabilitarPaneles();
        panel.add(panelCiclo);
        panelCiclo.setVisible(true);
        Utilidades.cargarAsignaturas(cbxListaAsignaturas);
        cargarTablaCiclo();
    }//GEN-LAST:event_btnCicloActionPerformed

    private void btnAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignaturaActionPerformed
        despintarBotones();
        pintarBotones(btnAsignatura);
        panel.add(txtBuscar);
        desabilitarPaneles();
        txtBuscar.setVisible(true);
        Utilidades.cargarEstado(cbxEstado);
        Utilidades.cargarDocentes(cbxDocentes);
        Utilidades.cargarAsignaturas(cbxAsignatura);
        cargarTablaAsignatura();
        btnBorrarAsignatura.setEnabled(false);
        btnActualizarAsignatura.setEnabled(false);
    }//GEN-LAST:event_btnAsignaturaActionPerformed

    private void btnAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlumnoActionPerformed
        despintarBotones();
        pintarBotones(btnAlumno);
        desabilitarPaneles();
        panel.add(panelAlumno);
        panelAlumno.setVisible(true);
        btnAgregarAlumno.setEnabled(false);
        Utilidades.cargarMatriculas(cbxMatricula);
        cargarTablaAlumno();
        Utilidades.cargarGeneros(cbxGeneroAlumno);
        Utilidades.cargarAsignaturas(cbxAsignatura);
        btnBorrar.setEnabled(false);
        btnActualizar.setEnabled(false);
    }//GEN-LAST:event_btnAlumnoActionPerformed

    private void btnCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarreraActionPerformed
        despintarBotones();
        pintarBotones(btnCarrera);
        desabilitarPaneles();
        panel.add(panelCarrera);
        panelCarrera.setVisible(true);
        Utilidades.cargarCiclos(cbxCiclos);
        Utilidades.cargarSeccion(cbxSeccion);
        cargarTablaCarrera();
    }//GEN-LAST:event_btnCarreraActionPerformed

    private void btnMallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMallaActionPerformed
        despintarBotones();
        pintarBotones(btnMalla);
        panel.add(panelMalla);
        desabilitarPaneles();
        panelMalla.setVisible(true);
        cargarTablaMalla();
        Utilidades.cargarCarreras(cbxListaCarreras);
        btnBorrarMalla.setEnabled(false);
    }//GEN-LAST:event_btnMallaActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        new FrmLogin(null, false).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPeriodoActionPerformed
        despintarBotones();
        pintarBotones(btnPeriodo);
        panel.add(panelPeriodo);
        Utilidades.cargarComboMeses(cbxMesInicio);
        Utilidades.cargarComboMeses(cbxMesFin);
        Utilidades.cargarEspecificaciones(cbxEspecificacion);
        desabilitarPaneles();
        panelPeriodo.setVisible(true);
        cargarTablaPeriodo();
    }//GEN-LAST:event_btnPeriodoActionPerformed

    private void btnDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocenteActionPerformed
        despintarBotones();
        pintarBotones(btnDocente);
        panel.add(panelDocente);
        Utilidades.cargarGeneros(cbxGenero);
        desabilitarPaneles();
        panelDocente.setVisible(true);
        btnAgregarDocente.setEnabled(false);
        cargarTablaDocente();
        btnEliminar.setEnabled(false);
        btnModificar.setEnabled(false);
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

    private void btnAgregarDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDocenteActionPerformed
        guardarDocente();
    }//GEN-LAST:event_btnAgregarDocenteActionPerformed

    private void btnAgregarPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPeriodoActionPerformed
        guardarPeriodo();
    }//GEN-LAST:event_btnAgregarPeriodoActionPerformed

    private void cbxListaAsignaturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxListaAsignaturasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxListaAsignaturasActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (txtNombreAlumno.getText().trim().isEmpty() || txtApellidosAlumno.getText().trim().isEmpty() || cbxGeneroAlumno.getSelectedIndex() == -1 || txtIdentificacion.getText().trim().isEmpty()
                || txtCiudadEstudiante.getText().trim().isEmpty() || txtDireccionAlumno.getText().trim().isEmpty() || txtNroTelefono.getText().trim().isEmpty() || dateEstudiante.getDate().toString().trim().isEmpty()
                || cbxMatricula.getSelectedIndex() == -1 || cbxAsignatura.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            int fila = tblTablaAlumno.getSelectedRow();
            try {
                alumno.getLista().obtener(fila).setApellidos(txtApellidosAlumno.getText().trim());
                alumno.getLista().obtener(fila).setNombres(txtNombreAlumno.getText().trim());
                alumno.getLista().obtener(fila).setCiudad(txtCiudadEstudiante.getText().trim());
                alumno.getLista().obtener(fila).setDireccion(txtDireccionAlumno.getText().trim());
                alumno.getLista().obtener(fila).setGenero(Utilidades.obtenerGenero(cbxGeneroAlumno));
                alumno.getLista().obtener(fila).setIdentificacion(txtIdentificacion.getText().trim());
                alumno.getLista().obtener(fila).setTelefono(txtNroTelefono.getText().trim());
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                alumno.getLista().obtener(fila).setFechaNacimiento(formato.format(dateEstudiante.getDate()));
                alumno.getLista().obtener(fila).setMatricula(Utilidades.obtenerMatricula(cbxMatricula));
                alumno.getLista().obtener(fila).setAsignatura(Utilidades.obtenerAsignatura(cbxAsignatura));
                if (alumno.actualizar(alumno.getLista())) {
                    dateEstudiante.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "MODIFICADO CON EXITO", "OK", JOptionPane.INFORMATION_MESSAGE);
                    btnActualizar.setEnabled(false);
                    cargarTablaAlumno();
                    limpiarAlumno();
                }

            } catch (PosicionNoEncontradaException | ListaNullException ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        int fila = tblTablaAlumno.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este dato?", "AVISO", JOptionPane.OK_OPTION);
            if (confirmar == 0) {
                if (alumno.borrar(fila)) {
                    JOptionPane.showMessageDialog(null, "ELIMINADO CON EXITO", "OK", JOptionPane.INFORMATION_MESSAGE);
                    btnBorrar.setEnabled(false);
                    cargarTablaAlumno();
                }
            }
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnAgregarMallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMallaActionPerformed
        guardarMalla();
    }//GEN-LAST:event_btnAgregarMallaActionPerformed

    private void txtCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyTyped
        Character caracter = evt.getKeyChar();
        if (!Character.isDigit(caracter)) {
            evt.consume();
        }
        if (txtCedula.getText().trim().length() >= 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCedulaKeyTyped

    private void txtNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyTyped
        Character caracter = evt.getKeyChar();
        if (!Character.isLetter(caracter) && caracter != KeyEvent.VK_SPACE) {
            evt.consume();
        }

    }//GEN-LAST:event_txtNombresKeyTyped

    private void txtApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosKeyTyped
        Character caracter = evt.getKeyChar();
        if (!Character.isLetter(caracter) && caracter != KeyEvent.VK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_txtApellidosKeyTyped

    private void txtExpLaboralKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExpLaboralKeyTyped
        Character caracter = evt.getKeyChar();
        if (!Character.isDigit(caracter)) {
            evt.consume();
        }
        if (txtExpLaboral.getText().trim().length() >= 2) {
            evt.consume();
        }
    }//GEN-LAST:event_txtExpLaboralKeyTyped

    private void txtExpProfesionalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExpProfesionalKeyTyped
        Character caracter = evt.getKeyChar();
        if (!Character.isDigit(caracter)) {
            evt.consume();
        }
        if (txtExpProfesional.getText().trim().length() >= 2) {
            evt.consume();
        }
    }//GEN-LAST:event_txtExpProfesionalKeyTyped

    private void txtNroHorasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroHorasKeyTyped
        Character caracter = evt.getKeyChar();
        if (!Character.isDigit(caracter)) {
            evt.consume();
        }
        if (txtNroHoras.getText().trim().length() >= 3) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNroHorasKeyTyped

    private void txtNombreAsignaturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreAsignaturaKeyTyped
        Character caracter = evt.getKeyChar();
        if (!Character.isLetter(caracter) && caracter != KeyEvent.VK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreAsignaturaKeyTyped

    private void txtNroTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroTelefonoKeyTyped
        Character caracter = evt.getKeyChar();
        if (!Character.isDigit(caracter)) {
            evt.consume();
        }
        if (txtNroTelefono.getText().trim().length() >= 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNroTelefonoKeyTyped

    private void txtIdentificacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyTyped
        Character caracter = evt.getKeyChar();
        if (!Character.isDigit(caracter)) {
            evt.consume();
        }
        if (txtIdentificacion.getText().trim().length() >= 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtIdentificacionKeyTyped

    private void txtNombreAlumnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreAlumnoKeyTyped
        Character caracter = evt.getKeyChar();
        if (!Character.isLetter(caracter) && caracter != KeyEvent.VK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreAlumnoKeyTyped

    private void txtApellidosAlumnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosAlumnoKeyTyped
        Character caracter = evt.getKeyChar();
        if (!Character.isLetter(caracter) && caracter != KeyEvent.VK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_txtApellidosAlumnoKeyTyped

    private void txtNombreCarreraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreCarreraKeyTyped
        Character caracter = evt.getKeyChar();
        if (!Character.isLetter(caracter) && caracter != KeyEvent.VK_SPACE) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreCarreraKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        Character caracter = evt.getKeyChar();
        if (!Character.isDigit(caracter)) {
            evt.consume();
        }
        if (txtTelefono.getText().trim().length() >= 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentificacionActionPerformed
        if (Utilidades.esCedulaValida(txtIdentificacion.getText().trim())) {
            btnAgregarAlumno.setEnabled(true);
        }
    }//GEN-LAST:event_txtIdentificacionActionPerformed

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed

    }//GEN-LAST:event_txtCedulaActionPerformed

    private void txtCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyReleased
        if (txtCedula.getText().trim().length() == 10) {
            if (Utilidades.esCedulaValida(txtCedula.getText().trim())) {
                btnAgregarDocente.setEnabled(true);
            }
        } else {
            btnAgregarDocente.setEnabled(false);
        }
    }//GEN-LAST:event_txtCedulaKeyReleased

    private void txtIdentificacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyReleased
        if (txtIdentificacion.getText().trim().length() == 10) {
            if (Utilidades.esCedulaValida(txtIdentificacion.getText().trim())) {
                btnAgregarAlumno.setEnabled(true);
            }
        } else {
            btnAgregarAlumno.setEnabled(false);
        }
    }//GEN-LAST:event_txtIdentificacionKeyReleased

    private void tblDocenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDocenteMouseClicked
        if (evt.getClickCount() == 1) {
            btnEliminar.setEnabled(true);
        } else if (evt.getClickCount() == 2) {
            btnEliminar.setEnabled(false);
            btnModificar.setEnabled(true);
            cargarDatosDocente();
        }
    }//GEN-LAST:event_tblDocenteMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (txtNombres.getText().trim().isEmpty() || txtApellidos.getText().trim().isEmpty() || cbxGenero.getSelectedIndex() == -1 || txtCedula.getText().trim().isEmpty()
                || txtCiudad.getText().trim().isEmpty() || txtDireccion.getText().trim().isEmpty() || txtTelefono.getText().trim().isEmpty() || date.getDate().toString().trim().isEmpty()
                || txtTitulo3.getText().trim().isEmpty() || txtTitulo4.getText().trim().isEmpty() || txtExpLaboral.getText().trim().isEmpty() || txtExpProfesional.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            int fila = tblDocente.getSelectedRow();
            try {
                docente.getLista().obtener(fila).setAniosExpDocente(Integer.valueOf(txtExpProfesional.getText().trim()));
                docente.getLista().obtener(fila).setAniosExpLaboral(Integer.valueOf(txtExpLaboral.getText().trim()));
                docente.getLista().obtener(fila).setApellidos(txtApellidos.getText().trim());
                docente.getLista().obtener(fila).setNombres(txtNombres.getText().trim());
                docente.getLista().obtener(fila).setCiudad(txtCiudad.getText().trim());
                docente.getLista().obtener(fila).setDireccion(txtDireccion.getText().trim());
                docente.getLista().obtener(fila).setGenero(Utilidades.obtenerGenero(cbxGenero));
                docente.getLista().obtener(fila).setIdentificacion(txtCedula.getText().trim());
                docente.getLista().obtener(fila).setTelefono(txtTelefono.getText().trim());
                docente.getLista().obtener(fila).setTituloCuartoNivel(txtTitulo4.getText().trim());
                docente.getLista().obtener(fila).setTituloTercerNivel(txtTitulo3.getText().trim());
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                docente.getLista().obtener(fila).setFechaNacimiento(formato.format(date.getDate()));
                if (docente.actualizar(docente.getLista())) {
                    date.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "MODIFICADO CON EXITO", "OK", JOptionPane.INFORMATION_MESSAGE);
                    btnModificar.setEnabled(false);
                    cargarTablaDocente();
                    limpiarDocente();
                }

            } catch (PosicionNoEncontradaException | ListaNullException ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void cbxOrdenacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxOrdenacionActionPerformed
        try {
            if (cbxOrdenacion.getSelectedIndex() == 0) {
                docente.listar().quickSort(docente.listar(), cbxAtributo.getSelectedItem().toString());
            } else {

            }
            cargarTablaDocente();
        } catch (PosicionNoEncontradaException | IllegalArgumentException | IllegalAccessException | AtributoException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_cbxOrdenacionActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (tblDocente.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este dato?", "AVISO", JOptionPane.OK_OPTION);
            if (confirmar == 0) {
                if (docente.borrar(tblDocente.getSelectedRow())) {
                    JOptionPane.showMessageDialog(null, "ELIMINADO CON EXITO", "OK", JOptionPane.INFORMATION_MESSAGE);
                    btnEliminar.setEnabled(false);
                    cargarTablaDocente();
                }
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tblTablaAlumnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTablaAlumnoMouseClicked
        if (evt.getClickCount() == 1) {
            btnBorrar.setEnabled(true);
            btnActualizar.setEnabled(false);
        } else if (evt.getClickCount() == 2) {
            btnBorrar.setEnabled(false);
            btnActualizar.setEnabled(true);
            cargarDatosAlumno();
        }
    }//GEN-LAST:event_tblTablaAlumnoMouseClicked

    private void btnBorrarMallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarMallaActionPerformed
        if (tblTablaMalla.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este dato?", "AVISO", JOptionPane.OK_OPTION);
            if (confirmar == 0) {
                if (malla.borrar(tblTablaMalla.getSelectedRow())) {
                    JOptionPane.showMessageDialog(null, "ELIMINADO CON EXITO", "OK", JOptionPane.INFORMATION_MESSAGE);
                    btnBorrarMalla.setEnabled(false);
                    cargarTablaMalla();
                }
            }
        }
    }//GEN-LAST:event_btnBorrarMallaActionPerformed

    private void tblTablaMallaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTablaMallaMouseClicked
        if (evt.getClickCount() == 1) {
            btnBorrarMalla.setEnabled(true);
        }
    }//GEN-LAST:event_tblTablaMallaMouseClicked

    private void btnBorrarMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarMatriculaActionPerformed
        if (tblTablaMatricula.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este dato?", "AVISO", JOptionPane.OK_OPTION);
            if (confirmar == 0) {
                if (matricula.borrar(tblTablaMatricula.getSelectedRow())) {
                    JOptionPane.showMessageDialog(null, "ELIMINADO CON EXITO", "OK", JOptionPane.INFORMATION_MESSAGE);
                    btnBorrarMatricula.setEnabled(false);
                    cargarTablaMatricula();
                }
            }
        }
    }//GEN-LAST:event_btnBorrarMatriculaActionPerformed

    private void tblTablaMatriculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTablaMatriculaMouseClicked
        if (evt.getClickCount() == 1) {
            btnBorrarMatricula.setEnabled(true);
        }
    }//GEN-LAST:event_tblTablaMatriculaMouseClicked

    private void tblTablaPeriodoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTablaPeriodoMouseClicked
        if (evt.getClickCount() == 1) {
            btnBorrarPeriodo.setEnabled(true);
        }
    }//GEN-LAST:event_tblTablaPeriodoMouseClicked

    private void btnBorrarPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarPeriodoActionPerformed
        if (tblTablaPeriodo.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este dato?", "AVISO", JOptionPane.OK_OPTION);
            if (confirmar == 0) {
                if (periodo.borrar(tblTablaPeriodo.getSelectedRow())) {
                    JOptionPane.showMessageDialog(null, "ELIMINADO CON EXITO", "OK", JOptionPane.INFORMATION_MESSAGE);
                    btnBorrarPeriodo.setEnabled(false);
                    cargarTablaPeriodo();
                }
            }
        }
    }//GEN-LAST:event_btnBorrarPeriodoActionPerformed

    private void btnBorrarCicloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarCicloActionPerformed
        if (tblTablaCiclo.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este dato?", "AVISO", JOptionPane.OK_OPTION);
            if (confirmar == 0) {
                if (ciclo.borrar(tblTablaCiclo.getSelectedRow())) {
                    JOptionPane.showMessageDialog(null, "ELIMINADO CON EXITO", "OK", JOptionPane.INFORMATION_MESSAGE);
                    btnBorrarCiclo.setEnabled(false);
                    cargarTablaCiclo();
                }
            }
        }
    }//GEN-LAST:event_btnBorrarCicloActionPerformed

    private void tblTablaCicloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTablaCicloMouseClicked
        if (evt.getClickCount() == 1) {
            btnBorrarCiclo.setEnabled(true);
        }
    }//GEN-LAST:event_tblTablaCicloMouseClicked

    private void btnBorrarCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarCarreraActionPerformed
        if (tblTablaCarrera.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este dato?", "AVISO", JOptionPane.OK_OPTION);
            if (confirmar == 0) {
                if (carrera.borrar(tblTablaCarrera.getSelectedRow())) {
                    JOptionPane.showMessageDialog(null, "ELIMINADO CON EXITO", "OK", JOptionPane.INFORMATION_MESSAGE);
                    btnBorrarCarrera.setEnabled(false);
                    cargarTablaCarrera();
                }
            }
        }
    }//GEN-LAST:event_btnBorrarCarreraActionPerformed

    private void tblTablaCarreraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTablaCarreraMouseClicked
        if (evt.getClickCount() == 1) {
            btnBorrarCarrera.setEnabled(true);
        }
    }//GEN-LAST:event_tblTablaCarreraMouseClicked

    private void btnBorrarAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarAsignaturaActionPerformed
        if (tblTablaAsignatura.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este dato?", "AVISO", JOptionPane.OK_OPTION);
            if (confirmar == 0) {
                if (asignatura.borrar(tblTablaAsignatura.getSelectedRow())) {
                    JOptionPane.showMessageDialog(null, "ELIMINADO CON EXITO", "OK", JOptionPane.INFORMATION_MESSAGE);
                    btnBorrarAsignatura.setEnabled(false);
                    cargarTablaAsignatura();
                }
            }
        }
    }//GEN-LAST:event_btnBorrarAsignaturaActionPerformed

    private void tblTablaAsignaturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTablaAsignaturaMouseClicked
        if (evt.getClickCount() == 1) {
            btnBorrarAsignatura.setEnabled(true);
        } else if (evt.getClickCount() == 2) {
            btnActualizarAsignatura.setEnabled(true);
            btnBorrarAsignatura.setEnabled(false);
            cargarDatosAsignatura();
            btnAgregarAsignatura.setEnabled(false);
        }
    }//GEN-LAST:event_tblTablaAsignaturaMouseClicked

    private void btnActualizarAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarAsignaturaActionPerformed
        if (txtNombreAsignatura.getText().trim().isEmpty() || txtNroHoras.getText().trim().isEmpty() || cbxDocentes.getSelectedIndex() == -1
                || cbxEstado.getSelectedIndex() == -1 || cbxParalelo.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            int fila = tblTablaAsignatura.getSelectedRow();
            try {
                asignatura.getLista().obtener(fila).setDocente(Utilidades.obtenerDocente(cbxDocentes));
                asignatura.getLista().obtener(fila).setEstadoAsignatura(Utilidades.obtenerEstado(cbxEstado));
                asignatura.getLista().obtener(fila).setNombreAsignatura(txtNombreAsignatura.getText().trim());
                asignatura.getLista().obtener(fila).setNumeroHoras(Integer.valueOf(txtNroHoras.getText()));
                asignatura.getLista().obtener(fila).setParalelo(cbxParalelo.getSelectedItem().toString().charAt(0));
                if (asignatura.actualizar(asignatura.getLista())) {
                    JOptionPane.showMessageDialog(null, "MODIFICADO CON EXITO", "OK", JOptionPane.INFORMATION_MESSAGE);
                    btnActualizarAsignatura.setEnabled(false);
                    cargarTablaAsignatura();
                    limpiar();
                    btnAgregarAsignatura.setEnabled(true);
                }

            } catch (PosicionNoEncontradaException | ListaNullException ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnActualizarAsignaturaActionPerformed

    private void txtBuscarAsignaturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarAsignaturaKeyReleased
        try {
            ListaEnlazada lista = asignatura.getLista().secuencial(cbxAtributoAsignatura.getSelectedItem().toString(), txtBuscarAsignatura.getText());
            mta.setLista(lista);
            tblTablaAsignatura.setModel(mta);
            tblTablaAsignatura.updateUI();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_txtBuscarAsignaturaKeyReleased

    private void txtBuscarAlumnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarAlumnoKeyReleased
        try {
            if (txtBuscarAlumno.getText().equals("")) {
                cargarTablaAlumno();
            } else {
                ListaEnlazada lista = alumno.getLista().secuencial(cbxAtributoAlumno.getSelectedItem().toString(), txtBuscarAlumno.getText());
                modeloTablaAlumno.setLista(lista);
                tblTablaAlumno.setModel(modeloTablaAlumno);
                tblTablaAlumno.updateUI();
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_txtBuscarAlumnoKeyReleased

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

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
    private javax.swing.JButton btnActualizarAsignatura;
    private javax.swing.JButton btnAgregarAlumno;
    private javax.swing.JButton btnAgregarAsignatura;
    private javax.swing.JButton btnAgregarCiclos;
    private javax.swing.JButton btnAgregarDocente;
    private javax.swing.JButton btnAgregarMalla;
    private javax.swing.JButton btnAgregarMatricula;
    private javax.swing.JButton btnAgregarPeriodo;
    private javax.swing.JButton btnAlumno;
    private javax.swing.JButton btnAsignatura;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnBorrarAsignatura;
    private javax.swing.JButton btnBorrarCarrera;
    private javax.swing.JButton btnBorrarCiclo;
    private javax.swing.JButton btnBorrarMalla;
    private javax.swing.JButton btnBorrarMatricula;
    private javax.swing.JButton btnBorrarPeriodo;
    private javax.swing.JButton btnCarrera;
    private javax.swing.JButton btnCiclo;
    private javax.swing.JButton btnCrearCarrera;
    private javax.swing.JButton btnDocente;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnMalla;
    private javax.swing.JButton btnMatricula;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnPeriodo;
    private javax.swing.JComboBox<String> cbxAsignatura;
    private javax.swing.JComboBox<String> cbxAtributo;
    private javax.swing.JComboBox<String> cbxAtributoAlumno;
    private javax.swing.JComboBox<String> cbxAtributoAsignatura;
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
    private javax.swing.JComboBox<String> cbxOrdenacion;
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
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
    private javax.swing.JPanel txtBuscar;
    private javax.swing.JTextField txtBuscarAlumno;
    private javax.swing.JTextField txtBuscarAsignatura;
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
    // End of variables declaration//GEN-END:variables

}
