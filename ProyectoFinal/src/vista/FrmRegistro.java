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
import java.lang.reflect.InvocationTargetException;
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
        limpiarCiclo();
    }

    private void limpiarCiclo() {
        txtNombreCiclo.setText("");
        cbxListaAsignaturas.setSelectedIndex(-1);
        ciclo.setCiclo(null);
    }

    /**
     * Método para guardar una asignatura en un archivo json
     */
    private void guardarAsignatura() {
        if (txtNombreAsignatura.getText().trim().isEmpty() || txtNroHoras.getText().trim().isEmpty() || cbxDocentes.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            Integer id = asignatura.getLista().getTamanio() + 1;
            asignatura.getAsignatura().setId(id);
            asignatura.getAsignatura().setDocente(Utilidades.obtenerDocente(cbxDocentes));
            asignatura.getAsignatura().setNombreAsignatura(txtNombreAsignatura.getText().trim());
            asignatura.getAsignatura().setNumeroHoras(Integer.valueOf(txtNroHoras.getText().trim()));
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
        asignatura.setAsignatura(null);
    }

    /**
     * Método para guardar un nuevo docente en un archivo json
     */
    private void guardarDocente() {
        if (txtNombres.getText().trim().isEmpty() || txtApellidos.getText().trim().isEmpty() || cbxGenero.getSelectedIndex() == -1 || txtCedula.getText().trim().isEmpty()
                || txtCiudad.getText().trim().isEmpty() || txtDireccion.getText().trim().isEmpty() || txtTelefono.getText().trim().isEmpty() || date.getDate().toString().trim().isEmpty()
                || txtTitulo3.getText().trim().isEmpty() || txtTitulo4.getText().trim().isEmpty() || txtExpLaboral.getText().trim().isEmpty() || txtExpProfesional.getText().trim().isEmpty()||
                txtCorreoDocente.getText().trim().isEmpty()) {
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
            docente.getDocente().setCorreo(txtCorreoDocente.getText().trim());
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
        tblTablaDocente.setModel(mtd);
        tblTablaDocente.updateUI();
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
        txtCorreoDocente.setText("");
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
                || dateEstudiante.getDate().toString().trim().isEmpty() || cbxParalelo.getSelectedIndex() == -1 || cbxEstadoAlumno.getSelectedIndex() == -1
                || txtCorreo.getText().trim().isEmpty()) {
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
            alumno.getAlumno().setParalelo(cbxParalelo.getSelectedItem().toString().charAt(0));
            alumno.getAlumno().setEstado(Utilidades.obtenerEstado(cbxEstadoAlumno));
            alumno.getAlumno().setCorreo(txtCorreo.getText().trim());
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
        cbxParalelo.setSelectedIndex(-1);
        cbxEstadoAlumno.setSelectedIndex(-1);
        txtCorreo.setText("");
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
        panelAsignatura.setVisible(false);
        panelCiclo.setVisible(false);
        panelPeriodo.setVisible(false);
        panelAlumno.setVisible(false);
        panelCarrera.setVisible(false);
        panelMatricula.setVisible(false);
        panelDocente.setVisible(false);
    }

    private void cargarDatosDocente() {
        int fila = tblTablaDocente.getSelectedRow();
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
            txtCorreoDocente.setText(docente.listar().obtener(fila).getCorreo());
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
            cbxParalelo.setSelectedItem(alumno.listar().obtener(fila).getParalelo());
            cbxEstadoAlumno.setSelectedItem(alumno.listar().obtener(fila).getEstado());
            txtCorreo.setText(alumno.listar().obtener(fila).getCorreo());
        } catch (PosicionNoEncontradaException | ListaNullException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    private void cargarDatosAsignatura() {
        int fila = tblTablaAsignatura.getSelectedRow();
        try {
            txtNombreAsignatura.setText(asignatura.listar().obtener(fila).getNombreAsignatura());
            txtNroHoras.setText(asignatura.listar().obtener(fila).getNumeroHoras().toString());
            cbxDocentes.setSelectedItem(asignatura.listar().obtener(fila).getDocente());
        } catch (PosicionNoEncontradaException | ListaNullException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    private void cargarDatosCiclo() {
        int fila = tblTablaCiclo.getSelectedRow();
        try {
            txtNombreCiclo.setText(ciclo.listar().obtener(fila).getNombre());
            cbxListaAsignaturas.setSelectedItem(ciclo.listar().obtener(fila).getAsignaturas());
        } catch (PosicionNoEncontradaException | ListaNullException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    private void cargarDatosPeriodo() {
        int fila = tblTablaPeriodo.getSelectedRow();
        try {
            cbxMesInicio.setSelectedItem(periodo.listar().obtener(fila).getMesInicio());
            cbxMesFin.setSelectedItem(periodo.listar().obtener(fila).getMesFin());
            cbxEspecificacion.setSelectedItem(periodo.listar().obtener(fila).getEspecificacion());
        } catch (PosicionNoEncontradaException | ListaNullException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    private void cargarDatosMalla() {
        int fila = tblTablaMalla.getSelectedRow();
        try {
            cbxListaCarreras.setSelectedItem(malla.listar().obtener(fila).getCarreras());
            txtRegimen.setText(malla.listar().obtener(fila).getRegimen());
        } catch (PosicionNoEncontradaException | ListaNullException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    private void cargarDatosCarrera() {
        int fila = tblTablaCarrera.getSelectedRow();
        try {
            cbxSeccion.setSelectedItem(carrera.listar().obtener(fila).getSeccion());
            cbxCiclos.setSelectedItem(carrera.listar().obtener(fila).getCiclos());
            txtNombreCarrera.setText(carrera.listar().obtener(fila).getNombre());
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
        btnAgregarCiclo = new javax.swing.JButton();
        btnBorrarCiclo = new javax.swing.JButton();
        btnModificarCiclo = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        txtBuscarCiclo = new javax.swing.JTextField();
        cbxAtributoCiclo = new javax.swing.JComboBox<>();
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
        btnModificarPeriodo = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        txtBuscarPeriodo = new javax.swing.JTextField();
        cbxAtributoPeriodo = new javax.swing.JComboBox<>();
        panelMatricula = new javax.swing.JPanel();
        btnAgregarMatricula = new javax.swing.JButton();
        txtFechaEmision = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        cbxPeriodo = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblTablaMatricula = new javax.swing.JTable();
        btnBorrarMatricula = new javax.swing.JButton();
        cbxAtributoMatricula = new javax.swing.JComboBox<>();
        txtBuscarMatricula = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        panelAsignatura = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtNroHoras = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cbxDocentes = new javax.swing.JComboBox<>();
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
        btnModificarMalla = new javax.swing.JButton();
        cbxAtributoMalla = new javax.swing.JComboBox<>();
        txtBuscarMalla = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
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
        jLabel49 = new javax.swing.JLabel();
        cbxParalelo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbxEstadoAlumno = new javax.swing.JComboBox<>();
        jLabel51 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        panelCarrera = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombreCarrera = new javax.swing.JTextField();
        cbxSeccion = new javax.swing.JComboBox<>();
        btnAgregarCarrera = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cbxCiclos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTablaCarrera = new javax.swing.JTable();
        btnBorrarCarrera = new javax.swing.JButton();
        btnActualizarCarrera = new javax.swing.JButton();
        txtBuscarCarrera = new javax.swing.JTextField();
        cbxAtributoCarrera = new javax.swing.JComboBox<>();
        jLabel48 = new javax.swing.JLabel();
        panelDocente = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        cbxGenero = new javax.swing.JComboBox<>();
        btnAgregarDocente = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTablaDocente = new javax.swing.JTable();
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
        cbxAtributoDocente = new javax.swing.JComboBox<>();
        txtBuscarDocente = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCorreoDocente = new javax.swing.JTextField();
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
        panelCiclo.add(txtNombreCiclo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 160, 30));

        jLabel21.setBackground(new java.awt.Color(0, 0, 0));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("NOMBRE CICLO");
        panelCiclo.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        cbxListaAsignaturas.setBackground(new java.awt.Color(255, 255, 255));
        cbxListaAsignaturas.setForeground(new java.awt.Color(0, 0, 0));
        cbxListaAsignaturas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbxListaAsignaturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxListaAsignaturasActionPerformed(evt);
            }
        });
        panelCiclo.add(cbxListaAsignaturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 220, 30));

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

        panelCiclo.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 420, 150));

        btnAgregarCiclo.setBackground(new java.awt.Color(204, 204, 255));
        btnAgregarCiclo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregarCiclo.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarCiclo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/crear.png"))); // NOI18N
        btnAgregarCiclo.setText("AGREGAR");
        btnAgregarCiclo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarCiclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCicloActionPerformed(evt);
            }
        });
        panelCiclo.add(btnAgregarCiclo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

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
        panelCiclo.add(btnBorrarCiclo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, -1));

        btnModificarCiclo.setBackground(new java.awt.Color(204, 204, 255));
        btnModificarCiclo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnModificarCiclo.setForeground(new java.awt.Color(0, 0, 0));
        btnModificarCiclo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/actualizar.png"))); // NOI18N
        btnModificarCiclo.setText("MODIFICAR");
        btnModificarCiclo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificarCiclo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnModificarCiclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCicloActionPerformed(evt);
            }
        });
        panelCiclo.add(btnModificarCiclo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 140, 40));

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/buscar.png"))); // NOI18N
        panelCiclo.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 40));

        txtBuscarCiclo.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarCiclo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBuscarCiclo.setForeground(new java.awt.Color(0, 0, 0));
        txtBuscarCiclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarCicloActionPerformed(evt);
            }
        });
        txtBuscarCiclo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarCicloKeyReleased(evt);
            }
        });
        panelCiclo.add(txtBuscarCiclo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 210, 40));

        cbxAtributoCiclo.setBackground(new java.awt.Color(255, 255, 255));
        cbxAtributoCiclo.setForeground(new java.awt.Color(0, 0, 0));
        cbxAtributoCiclo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOMBRE", "ASIGNATURAS", "ID" }));
        cbxAtributoCiclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAtributoCicloActionPerformed(evt);
            }
        });
        panelCiclo.add(cbxAtributoCiclo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 210, 30));

        panelPeriodo.setBackground(new java.awt.Color(255, 255, 102));
        panelPeriodo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbxMesInicio.setBackground(new java.awt.Color(255, 255, 255));
        cbxMesInicio.setForeground(new java.awt.Color(0, 0, 0));
        panelPeriodo.add(cbxMesInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 160, 30));

        cbxMesFin.setBackground(new java.awt.Color(255, 255, 255));
        cbxMesFin.setForeground(new java.awt.Color(0, 0, 0));
        panelPeriodo.add(cbxMesFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 160, 30));

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
        panelPeriodo.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        cbxEspecificacion.setBackground(new java.awt.Color(255, 255, 255));
        cbxEspecificacion.setForeground(new java.awt.Color(0, 0, 0));
        panelPeriodo.add(cbxEspecificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 160, 30));

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

        panelPeriodo.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 370, 160));

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
        panelPeriodo.add(btnAgregarPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 140, 40));

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
        panelPeriodo.add(btnBorrarPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 140, 40));

        btnModificarPeriodo.setBackground(new java.awt.Color(204, 204, 255));
        btnModificarPeriodo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnModificarPeriodo.setForeground(new java.awt.Color(0, 0, 0));
        btnModificarPeriodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/actualizar.png"))); // NOI18N
        btnModificarPeriodo.setText("MODIFICAR");
        btnModificarPeriodo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificarPeriodo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnModificarPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarPeriodoActionPerformed(evt);
            }
        });
        panelPeriodo.add(btnModificarPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 140, 40));

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/buscar.png"))); // NOI18N
        panelPeriodo.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 40));

        txtBuscarPeriodo.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarPeriodo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBuscarPeriodo.setForeground(new java.awt.Color(0, 0, 0));
        txtBuscarPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarPeriodoActionPerformed(evt);
            }
        });
        txtBuscarPeriodo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarPeriodoKeyReleased(evt);
            }
        });
        panelPeriodo.add(txtBuscarPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 160, 40));

        cbxAtributoPeriodo.setBackground(new java.awt.Color(255, 255, 255));
        cbxAtributoPeriodo.setForeground(new java.awt.Color(0, 0, 0));
        cbxAtributoPeriodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MESINICIO", "MESFIN", "ESPECIFICACION", "IDPERIODO" }));
        cbxAtributoPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAtributoPeriodoActionPerformed(evt);
            }
        });
        panelPeriodo.add(cbxAtributoPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 160, 30));

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
        panelMatricula.add(btnAgregarMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, -1, 40));

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
        panelMatricula.add(cbxPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 32, 270, 34));

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

        panelMatricula.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 490, 140));

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
        panelMatricula.add(btnBorrarMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 130, 40));

        cbxAtributoMatricula.setBackground(new java.awt.Color(255, 255, 255));
        cbxAtributoMatricula.setForeground(new java.awt.Color(0, 0, 0));
        cbxAtributoMatricula.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IDMATRICULA", "FECHAEMISION", "PERIODO" }));
        cbxAtributoMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAtributoMatriculaActionPerformed(evt);
            }
        });
        panelMatricula.add(cbxAtributoMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 160, 30));

        txtBuscarMatricula.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarMatricula.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBuscarMatricula.setForeground(new java.awt.Color(0, 0, 0));
        txtBuscarMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarMatriculaActionPerformed(evt);
            }
        });
        txtBuscarMatricula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarMatriculaKeyReleased(evt);
            }
        });
        panelMatricula.add(txtBuscarMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 160, 40));

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/buscar.png"))); // NOI18N
        panelMatricula.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 40));

        panelAsignatura.setBackground(new java.awt.Color(255, 255, 102));
        panelAsignatura.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("NRO. HORAS");
        panelAsignatura.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, -1, 20));

        txtNroHoras.setBackground(new java.awt.Color(255, 255, 255));
        txtNroHoras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNroHorasKeyTyped(evt);
            }
        });
        panelAsignatura.add(txtNroHoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, 90, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("DOCENTE");
        panelAsignatura.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, -1));

        cbxDocentes.setBackground(new java.awt.Color(255, 255, 255));
        cbxDocentes.setForeground(new java.awt.Color(0, 0, 0));
        panelAsignatura.add(cbxDocentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 310, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("NOMBRE ASIGNATURA");
        panelAsignatura.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txtNombreAsignatura.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreAsignatura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreAsignaturaKeyTyped(evt);
            }
        });
        panelAsignatura.add(txtNombreAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 220, 30));

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
        panelAsignatura.add(btnAgregarAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 140, 40));

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

        panelAsignatura.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 720, 150));

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
        panelAsignatura.add(btnBorrarAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 130, 40));

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
        panelAsignatura.add(btnActualizarAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, -1, 40));

        txtBuscarAsignatura.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarAsignatura.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBuscarAsignatura.setForeground(new java.awt.Color(0, 0, 0));
        txtBuscarAsignatura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarAsignaturaKeyReleased(evt);
            }
        });
        panelAsignatura.add(txtBuscarAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 210, 40));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/buscar.png"))); // NOI18N
        panelAsignatura.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, 40));

        cbxAtributoAsignatura.setBackground(new java.awt.Color(255, 255, 255));
        cbxAtributoAsignatura.setForeground(new java.awt.Color(0, 0, 0));
        cbxAtributoAsignatura.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "NUMEROHORAS", "DOCENTE", "NOMBREASIGNATURA" }));
        cbxAtributoAsignatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAtributoAsignaturaActionPerformed(evt);
            }
        });
        panelAsignatura.add(cbxAtributoAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 210, 30));

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

        panelMalla.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 420, 150));

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
        panelMalla.add(btnAgregarMalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, -1, -1));

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
        panelMalla.add(btnBorrarMalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 130, -1));

        btnModificarMalla.setBackground(new java.awt.Color(204, 204, 255));
        btnModificarMalla.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnModificarMalla.setForeground(new java.awt.Color(0, 0, 0));
        btnModificarMalla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/actualizar.png"))); // NOI18N
        btnModificarMalla.setText("MODIFICAR");
        btnModificarMalla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificarMalla.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnModificarMalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarMallaActionPerformed(evt);
            }
        });
        panelMalla.add(btnModificarMalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 140, 40));

        cbxAtributoMalla.setBackground(new java.awt.Color(255, 255, 255));
        cbxAtributoMalla.setForeground(new java.awt.Color(0, 0, 0));
        cbxAtributoMalla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "REGIMEN", "CARRERAS" }));
        cbxAtributoMalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAtributoMallaActionPerformed(evt);
            }
        });
        panelMalla.add(cbxAtributoMalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 160, 30));

        txtBuscarMalla.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarMalla.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBuscarMalla.setForeground(new java.awt.Color(0, 0, 0));
        txtBuscarMalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarMallaActionPerformed(evt);
            }
        });
        txtBuscarMalla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarMallaKeyReleased(evt);
            }
        });
        panelMalla.add(txtBuscarMalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 160, 40));

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/buscar.png"))); // NOI18N
        panelMalla.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 40));

        panelAlumno.setBackground(new java.awt.Color(255, 255, 102));
        panelAlumno.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("IDENTIFICACION");
        panelAlumno.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        txtApellidosAlumno.setBackground(new java.awt.Color(255, 255, 255));
        txtApellidosAlumno.setForeground(new java.awt.Color(0, 0, 0));
        txtApellidosAlumno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidosAlumnoKeyTyped(evt);
            }
        });
        panelAlumno.add(txtApellidosAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 200, 30));

        txtDireccionAlumno.setBackground(new java.awt.Color(255, 255, 255));
        txtDireccionAlumno.setForeground(new java.awt.Color(0, 0, 0));
        panelAlumno.add(txtDireccionAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 250, 30));

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
        panelAlumno.add(btnAgregarAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 320, 140, 40));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("DIRECCION");
        panelAlumno.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, -1, -1));

        jLabel33.setBackground(new java.awt.Color(0, 0, 0));
        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("MATRICULA");
        panelAlumno.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, -1));

        txtNombreAlumno.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreAlumno.setForeground(new java.awt.Color(0, 0, 0));
        txtNombreAlumno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreAlumnoKeyTyped(evt);
            }
        });
        panelAlumno.add(txtNombreAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 170, 30));

        jLabel34.setBackground(new java.awt.Color(0, 0, 0));
        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("APELLIDOS");
        panelAlumno.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        jLabel35.setBackground(new java.awt.Color(0, 0, 0));
        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("NOMBRES");
        panelAlumno.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        cbxMatricula.setBackground(new java.awt.Color(255, 255, 255));
        cbxMatricula.setForeground(new java.awt.Color(0, 0, 0));
        panelAlumno.add(cbxMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 250, 30));

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
        panelAlumno.add(txtIdentificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 170, 30));

        jLabel37.setBackground(new java.awt.Color(0, 0, 0));
        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("GENERO");
        panelAlumno.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, -1, -1));

        txtNroTelefono.setBackground(new java.awt.Color(255, 255, 255));
        txtNroTelefono.setForeground(new java.awt.Color(0, 0, 0));
        txtNroTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNroTelefonoKeyTyped(evt);
            }
        });
        panelAlumno.add(txtNroTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 170, 30));

        jLabel38.setBackground(new java.awt.Color(0, 0, 0));
        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("CIUDAD");
        panelAlumno.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, -1, -1));

        txtCiudadEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        txtCiudadEstudiante.setForeground(new java.awt.Color(0, 0, 0));
        panelAlumno.add(txtCiudadEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 200, 30));

        jLabel39.setBackground(new java.awt.Color(0, 0, 0));
        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("FECHA NACIMIENTO");
        panelAlumno.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, -1, -1));

        dateEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        dateEstudiante.setForeground(new java.awt.Color(0, 0, 0));
        panelAlumno.add(dateEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 150, 250, 30));

        jLabel40.setBackground(new java.awt.Color(0, 0, 0));
        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("CORREO");
        panelAlumno.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

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

        panelAlumno.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 740, 130));

        cbxGeneroAlumno.setBackground(new java.awt.Color(255, 255, 255));
        cbxGeneroAlumno.setForeground(new java.awt.Color(0, 0, 0));
        cbxGeneroAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxGeneroAlumnoActionPerformed(evt);
            }
        });
        panelAlumno.add(cbxGeneroAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 200, 30));

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
        panelAlumno.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 320, -1, 40));

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
        panelAlumno.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 320, 130, 40));

        jLabel43.setBackground(new java.awt.Color(0, 0, 0));
        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("TELEFONO");
        panelAlumno.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        cbxAsignatura.setBackground(new java.awt.Color(255, 255, 255));
        panelAlumno.add(cbxAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 170, 30));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/buscar.png"))); // NOI18N
        panelAlumno.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, 40));

        txtBuscarAlumno.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarAlumno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBuscarAlumno.setForeground(new java.awt.Color(0, 0, 0));
        txtBuscarAlumno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarAlumnoKeyReleased(evt);
            }
        });
        panelAlumno.add(txtBuscarAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 210, 40));

        cbxAtributoAlumno.setBackground(new java.awt.Color(255, 255, 255));
        cbxAtributoAlumno.setForeground(new java.awt.Color(0, 0, 0));
        cbxAtributoAlumno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IDENTIFICACION", "GENERO", "APELLIDOS", "TELEFONO", "IDALUMNO", "DIRECCION", "CIUDAD", "FECHANACIMIENTO", "NOMBRES", "MATRICULA", "ASIGNATURA", "PARALELO", "CORREO" }));
        cbxAtributoAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAtributoAlumnoActionPerformed(evt);
            }
        });
        panelAlumno.add(cbxAtributoAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 210, 30));

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("PARALELO");
        panelAlumno.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, -1, -1));

        cbxParalelo.setBackground(new java.awt.Color(255, 255, 255));
        cbxParalelo.setForeground(new java.awt.Color(0, 0, 0));
        cbxParalelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" }));
        panelAlumno.add(cbxParalelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, 200, 30));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("ESTADO");
        panelAlumno.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 190, -1, -1));

        cbxEstadoAlumno.setBackground(new java.awt.Color(255, 255, 255));
        panelAlumno.add(cbxEstadoAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 210, 250, 30));

        jLabel51.setBackground(new java.awt.Color(0, 0, 0));
        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("ASIGNATURA");
        panelAlumno.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        txtCorreo.setBackground(new java.awt.Color(255, 255, 255));
        txtCorreo.setForeground(new java.awt.Color(0, 0, 0));
        panelAlumno.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 170, 30));

        panelCarrera.setBackground(new java.awt.Color(255, 255, 102));
        panelCarrera.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("SECCION");
        panelCarrera.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 80, -1));

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
        panelCarrera.add(txtNombreCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 300, 30));

        cbxSeccion.setBackground(new java.awt.Color(255, 255, 255));
        cbxSeccion.setForeground(new java.awt.Color(0, 0, 0));
        cbxSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSeccionActionPerformed(evt);
            }
        });
        panelCarrera.add(cbxSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 240, 30));

        btnAgregarCarrera.setBackground(new java.awt.Color(204, 204, 255));
        btnAgregarCarrera.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregarCarrera.setForeground(new java.awt.Color(0, 0, 0));
        btnAgregarCarrera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/crear.png"))); // NOI18N
        btnAgregarCarrera.setText("AGREGAR");
        btnAgregarCarrera.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarCarrera.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAgregarCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCarreraActionPerformed(evt);
            }
        });
        panelCarrera.add(btnAgregarCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 150, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("CICLOS");
        panelCarrera.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 80, -1));

        cbxCiclos.setBackground(new java.awt.Color(255, 255, 255));
        cbxCiclos.setForeground(new java.awt.Color(0, 0, 0));
        panelCarrera.add(cbxCiclos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 300, 30));

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

        panelCarrera.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 580, 130));

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
        panelCarrera.add(btnBorrarCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 150, -1));

        btnActualizarCarrera.setBackground(new java.awt.Color(204, 204, 255));
        btnActualizarCarrera.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnActualizarCarrera.setForeground(new java.awt.Color(0, 0, 0));
        btnActualizarCarrera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/actualizar.png"))); // NOI18N
        btnActualizarCarrera.setText("ACTUALIZAR");
        btnActualizarCarrera.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarCarreraActionPerformed(evt);
            }
        });
        panelCarrera.add(btnActualizarCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 180, -1, -1));

        txtBuscarCarrera.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarCarrera.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBuscarCarrera.setForeground(new java.awt.Color(0, 0, 0));
        txtBuscarCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarCarreraActionPerformed(evt);
            }
        });
        txtBuscarCarrera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarCarreraKeyReleased(evt);
            }
        });
        panelCarrera.add(txtBuscarCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 200, 40));

        cbxAtributoCarrera.setBackground(new java.awt.Color(255, 255, 255));
        cbxAtributoCarrera.setForeground(new java.awt.Color(0, 0, 0));
        cbxAtributoCarrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "NOMBRE", "SECCION", "CICLOS" }));
        cbxAtributoCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAtributoCarreraActionPerformed(evt);
            }
        });
        panelCarrera.add(cbxAtributoCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 200, 30));

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/buscar.png"))); // NOI18N
        panelCarrera.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 40));

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
        panelDocente.add(btnAgregarDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, 130, 40));

        tblTablaDocente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTablaDocente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTablaDocenteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblTablaDocente);

        panelDocente.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 780, 150));

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
        panelDocente.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 190, 30));

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
        panelDocente.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 190, 30));

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
        panelDocente.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 260, 140, 40));

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
        panelDocente.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, 130, 40));

        cbxAtributoDocente.setBackground(new java.awt.Color(255, 255, 255));
        cbxAtributoDocente.setForeground(new java.awt.Color(0, 0, 0));
        cbxAtributoDocente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IDENTIFICACION", "TELEFONO", "CIUDAD", "NOMBRES", "APELLIDOS", "GENERO", "IDDOCENTE", "FECHANACIMIENTO", "TITULOTERCERNIVEL", "TITULOCUARTONIVEL", "ANIOSEXPLABORAL", "ANIOSEXPDOCENTE", "DIRECCION", "CORREO" }));
        cbxAtributoDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAtributoDocenteActionPerformed(evt);
            }
        });
        panelDocente.add(cbxAtributoDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 210, 30));

        txtBuscarDocente.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarDocente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBuscarDocente.setForeground(new java.awt.Color(0, 0, 0));
        txtBuscarDocente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarDocenteKeyReleased(evt);
            }
        });
        panelDocente.add(txtBuscarDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 210, 40));

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/buscar.png"))); // NOI18N
        panelDocente.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, 40));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("CORREO");
        panelDocente.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        txtCorreoDocente.setBackground(new java.awt.Color(255, 255, 255));
        txtCorreoDocente.setForeground(new java.awt.Color(0, 0, 0));
        txtCorreoDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoDocenteActionPerformed(evt);
            }
        });
        panelDocente.add(txtCorreoDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 170, 30));

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
        jPanel1.add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 40, 30));

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
        jPanel1.add(btnMalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 120, 50));

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
        jPanel1.add(btnCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 120, 50));

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
        jPanel1.add(btnAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 120, 50));

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
        jPanel1.add(btnAsignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, 50));

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
        jPanel1.add(btnCiclo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 120, 50));

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
        jPanel1.add(btnMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 120, 50));

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
        jPanel1.add(btnPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 120, 50));

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
        jPanel1.add(btnDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 120, 50));

        panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.LINE_AXIS));
        jPanel1.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 800, 530));

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
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
        btnModificarCiclo.setEnabled(false);
    }//GEN-LAST:event_btnCicloActionPerformed

    private void btnAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignaturaActionPerformed
        despintarBotones();
        pintarBotones(btnAsignatura);
        panel.add(panelAsignatura);
        desabilitarPaneles();
        panelAsignatura.setVisible(true);
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
        Utilidades.cargarEstado(cbxEstadoAlumno);
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
        btnActualizarCarrera.setEnabled(false);
    }//GEN-LAST:event_btnCarreraActionPerformed

    private void btnMallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMallaActionPerformed
        despintarBotones();
        pintarBotones(btnMalla);
        panel.add(panelMalla);
        desabilitarPaneles();
        panelMalla.setVisible(true);
        cargarTablaMalla();
        Utilidades.cargarCarreras(cbxListaCarreras);
        btnModificarMalla.setEnabled(false);
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
        btnModificarPeriodo.setEnabled(false);
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

    private void btnAgregarCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCarreraActionPerformed
        guardarCarrera();
    }//GEN-LAST:event_btnAgregarCarreraActionPerformed

    private void cbxSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSeccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSeccionActionPerformed

    private void btnAgregarCicloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCicloActionPerformed
        guardarCiclo();
    }//GEN-LAST:event_btnAgregarCicloActionPerformed

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
                || cbxMatricula.getSelectedIndex() == -1 || cbxAsignatura.getSelectedIndex() == -1 || cbxParalelo.getSelectedIndex() == -1 || cbxEstadoAlumno.getSelectedIndex() == -1
                ||txtCorreo.getText().trim().isEmpty()) {
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
                alumno.getLista().obtener(fila).setParalelo(cbxParalelo.getSelectedItem().toString().charAt(0));
                alumno.getLista().obtener(fila).setEstado(Utilidades.obtenerEstado(cbxEstadoAlumno));
                alumno.getLista().obtener(fila).setCorreo(txtCorreo.getText().trim());
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

    private void tblTablaDocenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTablaDocenteMouseClicked
        if (evt.getClickCount() == 1) {
            btnEliminar.setEnabled(true);
        } else if (evt.getClickCount() == 2) {
            btnEliminar.setEnabled(false);
            btnModificar.setEnabled(true);
            cargarDatosDocente();
        }
    }//GEN-LAST:event_tblTablaDocenteMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if (txtNombres.getText().trim().isEmpty() || txtApellidos.getText().trim().isEmpty() || cbxGenero.getSelectedIndex() == -1 || txtCedula.getText().trim().isEmpty()
                || txtCiudad.getText().trim().isEmpty() || txtDireccion.getText().trim().isEmpty() || txtTelefono.getText().trim().isEmpty() || date.getDate().toString().trim().isEmpty()
                || txtTitulo3.getText().trim().isEmpty() || txtTitulo4.getText().trim().isEmpty() || txtExpLaboral.getText().trim().isEmpty() || txtExpProfesional.getText().trim().isEmpty()
                ||txtCorreoDocente.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            int fila = tblTablaDocente.getSelectedRow();
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
                docente.getLista().obtener(fila).setCorreo(txtCorreoDocente.getText().trim());
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

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (tblTablaDocente.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este dato?", "AVISO", JOptionPane.OK_OPTION);
            if (confirmar == 0) {
                if (docente.borrar(tblTablaDocente.getSelectedRow())) {
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
        } else if (evt.getClickCount() == 2) {
            btnModificarMalla.setEnabled(true);
            btnAgregarMalla.setEnabled(false);
            btnBorrarMalla.setEnabled(false);
            cargarDatosMalla();
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
        } else if (evt.getClickCount() == 2) {
            btnModificarPeriodo.setEnabled(true);
            btnAgregarPeriodo.setEnabled(false);
            btnBorrarPeriodo.setEnabled(false);
            cargarDatosPeriodo();
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
        } else if (evt.getClickCount() == 2) {
            btnBorrarCiclo.setEnabled(false);
            btnAgregarCiclo.setEnabled(false);
            btnModificarCiclo.setEnabled(true);
            cargarDatosCiclo();
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
        } else if (evt.getClickCount() == 2) {
            btnActualizarCarrera.setEnabled(true);
            btnAgregarCarrera.setEnabled(false);
            btnBorrarCarrera.setEnabled(false);
            cargarDatosCarrera();
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
        if (txtNombreAsignatura.getText().trim().isEmpty() || txtNroHoras.getText().trim().isEmpty() || cbxDocentes.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            int fila = tblTablaAsignatura.getSelectedRow();
            try {
                asignatura.getLista().obtener(fila).setDocente(Utilidades.obtenerDocente(cbxDocentes));
                asignatura.getLista().obtener(fila).setNombreAsignatura(txtNombreAsignatura.getText().trim());
                asignatura.getLista().obtener(fila).setNumeroHoras(Integer.valueOf(txtNroHoras.getText()));
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
            ListaEnlazada lista = alumno.getLista().secuencial(cbxAtributoAlumno.getSelectedItem().toString(), txtBuscarAlumno.getText());
            modeloTablaAlumno.setLista(lista);
            tblTablaAlumno.setModel(modeloTablaAlumno);
            tblTablaAlumno.updateUI();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_txtBuscarAlumnoKeyReleased

    private void cbxAtributoDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAtributoDocenteActionPerformed
        try {
            ListaEnlazada lista = docente.listar().quickSort(docente.listar(), cbxAtributoDocente.getSelectedItem().toString());
            mtd.setLista(lista);
            tblTablaDocente.setModel(mtd);
            tblTablaDocente.updateUI();
        } catch (PosicionNoEncontradaException | IllegalArgumentException | IllegalAccessException | AtributoException | NoSuchMethodException | InvocationTargetException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_cbxAtributoDocenteActionPerformed

    private void btnModificarCicloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCicloActionPerformed
        if (txtNombreCiclo.getText().trim().isEmpty() || cbxListaAsignaturas.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            int fila = tblTablaCiclo.getSelectedRow();
            try {
                ciclo.getLista().obtener(fila).setAsignaturas(Utilidades.obtenerAsignaturas(cbxListaAsignaturas));
                ciclo.getLista().obtener(fila).setNombre(txtNombreCiclo.getText().trim());
                if (ciclo.actualizar(ciclo.getLista())) {
                    JOptionPane.showMessageDialog(null, "MODIFICADO CON EXITO", "OK", JOptionPane.INFORMATION_MESSAGE);
                    btnModificarCiclo.setEnabled(false);
                    cargarTablaCiclo();
                    limpiarCiclo();
                    btnAgregarCiclo.setEnabled(true);
                }

            } catch (PosicionNoEncontradaException | ListaNullException ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnModificarCicloActionPerformed

    private void btnModificarPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarPeriodoActionPerformed
        if (cbxMesInicio.getSelectedIndex() == -1 || cbxMesFin.getSelectedIndex() == -1 || cbxEspecificacion.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            int fila = tblTablaPeriodo.getSelectedRow();
            try {
                periodo.getLista().obtener(fila).setEspecificacion(Utilidades.obtenerEspecificacion(cbxEspecificacion));
                periodo.getLista().obtener(fila).setMesFin(Utilidades.obtenerMes(cbxMesFin));
                periodo.getLista().obtener(fila).setMesInicio(Utilidades.obtenerMes(cbxMesInicio));
                if (periodo.actualizar(periodo.getLista())) {
                    JOptionPane.showMessageDialog(null, "MODIFICADO CON EXITO", "OK", JOptionPane.INFORMATION_MESSAGE);
                    btnModificarPeriodo.setEnabled(false);
                    cargarTablaPeriodo();
                    limpiarPeriodo();
                    btnAgregarPeriodo.setEnabled(true);
                }

            } catch (PosicionNoEncontradaException | ListaNullException ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnModificarPeriodoActionPerformed

    private void btnModificarMallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarMallaActionPerformed
        if (cbxListaCarreras.getSelectedIndex() == -1 || txtRegimen.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            int fila = tblTablaMalla.getSelectedRow();
            try {
                malla.getLista().obtener(fila).setCarreras(Utilidades.obtenerCarreras(cbxListaCarreras));
                malla.getLista().obtener(fila).setRegimen(txtRegimen.getText().trim());
                if (malla.actualizar(malla.getLista())) {
                    JOptionPane.showMessageDialog(null, "MODIFICADO CON EXITO", "OK", JOptionPane.INFORMATION_MESSAGE);
                    btnModificarMalla.setEnabled(false);
                    cargarTablaMalla();
                    limpiarMalla();
                    btnAgregarMalla.setEnabled(true);
                }

            } catch (PosicionNoEncontradaException | ListaNullException ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnModificarMallaActionPerformed

    private void btnActualizarCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarCarreraActionPerformed
        if (cbxSeccion.getSelectedIndex() == -1 || txtNombreCarrera.getText().trim().isEmpty() || cbxCiclos.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Campos vacios", "AVISO", JOptionPane.WARNING_MESSAGE);
        } else {
            int fila = tblTablaCarrera.getSelectedRow();
            try {
                carrera.getLista().obtener(fila).setCiclos(Utilidades.obtenerCiclos(cbxCiclos));
                carrera.getLista().obtener(fila).setSeccion(Utilidades.obtenerSeccion(cbxSeccion));
                carrera.getLista().obtener(fila).setNombre(txtNombreCarrera.getText().trim());
                if (carrera.actualizar(carrera.getLista())) {
                    JOptionPane.showMessageDialog(null, "MODIFICADO CON EXITO", "OK", JOptionPane.INFORMATION_MESSAGE);
                    btnActualizarCarrera.setEnabled(false);
                    cargarTablaCarrera();
                    limpiarCarrera();
                    btnAgregarCarrera.setEnabled(true);
                }

            } catch (PosicionNoEncontradaException | ListaNullException ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnActualizarCarreraActionPerformed

    private void txtBuscarCicloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCicloKeyReleased
        try {
            ListaEnlazada lista = ciclo.getLista().secuencial(cbxAtributoCiclo.getSelectedItem().toString(), txtBuscarCiclo.getText());
            modeloTablaCiclo.setLista(lista);
            tblTablaCiclo.setModel(modeloTablaCiclo);
            tblTablaCiclo.updateUI();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_txtBuscarCicloKeyReleased

    private void txtBuscarCicloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarCicloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarCicloActionPerformed

    private void txtBuscarPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarPeriodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarPeriodoActionPerformed

    private void txtBuscarPeriodoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPeriodoKeyReleased
        try {
            ListaEnlazada lista = periodo.getLista().secuencial(cbxAtributoPeriodo.getSelectedItem().toString(), txtBuscarPeriodo.getText());
            mtp.setLista(lista);
            tblTablaPeriodo.setModel(mtp);
            tblTablaPeriodo.updateUI();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_txtBuscarPeriodoKeyReleased

    private void txtBuscarMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarMatriculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarMatriculaActionPerformed

    private void txtBuscarMatriculaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMatriculaKeyReleased
        try {
            ListaEnlazada lista = matricula.getLista().secuencial(cbxAtributoMatricula.getSelectedItem().toString(), txtBuscarMatricula.getText());
            mtm.setLista(lista);
            tblTablaMatricula.setModel(mtm);
            tblTablaMatricula.updateUI();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_txtBuscarMatriculaKeyReleased

    private void txtBuscarMallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarMallaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarMallaActionPerformed

    private void txtBuscarMallaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMallaKeyReleased
        try {
            ListaEnlazada lista = malla.getLista().secuencial(cbxAtributoMalla.getSelectedItem().toString(), txtBuscarMalla.getText());
            modeloTablaMalla.setLista(lista);
            tblTablaMalla.setModel(modeloTablaMalla);
            tblTablaMalla.updateUI();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_txtBuscarMallaKeyReleased

    private void cbxAtributoMallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAtributoMallaActionPerformed
        try {
            ListaEnlazada lista = malla.listar().quickSort(malla.listar(), cbxAtributoMalla.getSelectedItem().toString());
            modeloTablaMalla.setLista(lista);
            tblTablaMalla.setModel(modeloTablaMalla);
            tblTablaMalla.updateUI();
        } catch (PosicionNoEncontradaException | IllegalArgumentException | IllegalAccessException | AtributoException | NoSuchMethodException | InvocationTargetException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_cbxAtributoMallaActionPerformed

    private void txtBuscarCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarCarreraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarCarreraActionPerformed

    private void txtBuscarCarreraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCarreraKeyReleased
        try {
            ListaEnlazada lista = carrera.getLista().secuencial(cbxAtributoCarrera.getSelectedItem().toString(), txtBuscarCarrera.getText());
            mtc.setLista(lista);
            tblTablaCarrera.setModel(mtc);
            tblTablaCarrera.updateUI();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_txtBuscarCarreraKeyReleased

    private void cbxAtributoCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAtributoCarreraActionPerformed
        try {
            ListaEnlazada lista = carrera.listar().quickSort(carrera.listar(), cbxAtributoCarrera.getSelectedItem().toString());
            mtc.setLista(lista);
            tblTablaCarrera.setModel(mtc);
            tblTablaCarrera.updateUI();
        } catch (PosicionNoEncontradaException | IllegalArgumentException | IllegalAccessException | AtributoException | NoSuchMethodException | InvocationTargetException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_cbxAtributoCarreraActionPerformed

    private void txtBuscarDocenteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarDocenteKeyReleased
        try {
            ListaEnlazada lista = docente.getLista().secuencial(cbxAtributoDocente.getSelectedItem().toString(), txtBuscarDocente.getText());
            mtd.setLista(lista);
            tblTablaDocente.setModel(mtd);
            tblTablaDocente.updateUI();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_txtBuscarDocenteKeyReleased

    private void cbxAtributoCicloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAtributoCicloActionPerformed
        try {
            ListaEnlazada lista = ciclo.listar().quickSort(ciclo.listar(), cbxAtributoCiclo.getSelectedItem().toString());
            modeloTablaCiclo.setLista(lista);
            tblTablaCiclo.setModel(modeloTablaCiclo);
            tblTablaCiclo.updateUI();
        } catch (PosicionNoEncontradaException | IllegalArgumentException | IllegalAccessException | AtributoException | NoSuchMethodException | InvocationTargetException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_cbxAtributoCicloActionPerformed

    private void cbxAtributoPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAtributoPeriodoActionPerformed
        try {
            ListaEnlazada lista = periodo.listar().quickSort(periodo.listar(), cbxAtributoPeriodo.getSelectedItem().toString());
            mtp.setLista(lista);
            tblTablaPeriodo.setModel(mtp);
            tblTablaPeriodo.updateUI();
        } catch (PosicionNoEncontradaException | IllegalArgumentException | IllegalAccessException | AtributoException | NoSuchMethodException | InvocationTargetException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_cbxAtributoPeriodoActionPerformed

    private void cbxAtributoMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAtributoMatriculaActionPerformed
        try {
            ListaEnlazada lista = matricula.listar().quickSort(matricula.listar(), cbxAtributoMatricula.getSelectedItem().toString());
            mtm.setLista(lista);
            tblTablaMatricula.setModel(mtm);
            tblTablaMatricula.updateUI();
        } catch (PosicionNoEncontradaException | IllegalArgumentException | IllegalAccessException | AtributoException | NoSuchMethodException | InvocationTargetException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_cbxAtributoMatriculaActionPerformed

    private void cbxAtributoAsignaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAtributoAsignaturaActionPerformed
        try {
            ListaEnlazada lista = asignatura.listar().quickSort(asignatura.listar(), cbxAtributoAsignatura.getSelectedItem().toString());
            mta.setLista(lista);
            tblTablaAsignatura.setModel(mta);
            tblTablaAsignatura.updateUI();
        } catch (PosicionNoEncontradaException | IllegalArgumentException | IllegalAccessException | AtributoException | NoSuchMethodException | InvocationTargetException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_cbxAtributoAsignaturaActionPerformed

    private void cbxAtributoAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAtributoAlumnoActionPerformed
        try {
            ListaEnlazada lista = alumno.listar().quickSort(alumno.listar(), cbxAtributoAlumno.getSelectedItem().toString());
            modeloTablaAlumno.setLista(lista);
            tblTablaAlumno.setModel(modeloTablaAlumno);
            tblTablaAlumno.updateUI();
        } catch (PosicionNoEncontradaException | IllegalArgumentException | IllegalAccessException | AtributoException | NoSuchMethodException | InvocationTargetException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }//GEN-LAST:event_cbxAtributoAlumnoActionPerformed

    private void txtCorreoDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoDocenteActionPerformed
        
    }//GEN-LAST:event_txtCorreoDocenteActionPerformed

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
    private javax.swing.JButton btnActualizarCarrera;
    private javax.swing.JButton btnAgregarAlumno;
    private javax.swing.JButton btnAgregarAsignatura;
    private javax.swing.JButton btnAgregarCarrera;
    private javax.swing.JButton btnAgregarCiclo;
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
    private javax.swing.JButton btnDocente;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnMalla;
    private javax.swing.JButton btnMatricula;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnModificarCiclo;
    private javax.swing.JButton btnModificarMalla;
    private javax.swing.JButton btnModificarPeriodo;
    private javax.swing.JButton btnPeriodo;
    private javax.swing.JComboBox<String> cbxAsignatura;
    private javax.swing.JComboBox<String> cbxAtributoAlumno;
    private javax.swing.JComboBox<String> cbxAtributoAsignatura;
    private javax.swing.JComboBox<String> cbxAtributoCarrera;
    private javax.swing.JComboBox<String> cbxAtributoCiclo;
    private javax.swing.JComboBox<String> cbxAtributoDocente;
    private javax.swing.JComboBox<String> cbxAtributoMalla;
    private javax.swing.JComboBox<String> cbxAtributoMatricula;
    private javax.swing.JComboBox<String> cbxAtributoPeriodo;
    private javax.swing.JComboBox<String> cbxCiclos;
    private javax.swing.JComboBox<String> cbxDocentes;
    private javax.swing.JComboBox<String> cbxEspecificacion;
    private javax.swing.JComboBox<String> cbxEstadoAlumno;
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
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
    private javax.swing.JTable tblTablaAlumno;
    private javax.swing.JTable tblTablaAsignatura;
    private javax.swing.JTable tblTablaCarrera;
    private javax.swing.JTable tblTablaCiclo;
    private javax.swing.JTable tblTablaDocente;
    private javax.swing.JTable tblTablaMalla;
    private javax.swing.JTable tblTablaMatricula;
    private javax.swing.JTable tblTablaPeriodo;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtApellidosAlumno;
    private javax.swing.JTextField txtBuscarAlumno;
    private javax.swing.JTextField txtBuscarAsignatura;
    private javax.swing.JTextField txtBuscarCarrera;
    private javax.swing.JTextField txtBuscarCiclo;
    private javax.swing.JTextField txtBuscarDocente;
    private javax.swing.JTextField txtBuscarMalla;
    private javax.swing.JTextField txtBuscarMatricula;
    private javax.swing.JTextField txtBuscarPeriodo;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtCiudadEstudiante;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtCorreoDocente;
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
