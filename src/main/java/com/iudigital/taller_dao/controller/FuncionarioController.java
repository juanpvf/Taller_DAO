/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iudigital.taller_dao.controller;

/**
 *
 * @author Juan Pablo
 */

import com.iudigital.taller_dao.dao.FuncionarioDAO;
import com.iudigital.taller_dao.model.Funcionario;
import com.iudigital.taller_dao.util.AlertaUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class FuncionarioController {

    @FXML
    private TextField tipoIdentificacionField;
    @FXML
    private TextField numeroIdentificacionField;
    @FXML
    private TextField nombresField;
    @FXML
    private TextField apellidosField;
    @FXML
    private ComboBox<String> estadoCivilComboBox;
    @FXML
    private ComboBox<String> sexoComboBox;
    @FXML
    private TextField direccionField;
    @FXML
    private TextField telefonoField;
    @FXML
    private DatePicker fechaNacimientoPicker;
    @FXML
    private TextField universidadField;
    @FXML
    private TextField nivelEstudioField;
    @FXML
    private TextField tituloEstudioField;

    @FXML
    private TableView<Funcionario> funcionariosTableView;
    @FXML
    private TableColumn<Funcionario, String> tipoIdentificacionColumn;
    @FXML
    private TableColumn<Funcionario, String> numeroIdentificacionColumn;
    @FXML
    private TableColumn<Funcionario, String> nombresColumn;
    @FXML
    private TableColumn<Funcionario, String> apellidosColumn;

    private FuncionarioDAO funcionarioDAO;
    private ObservableList<Funcionario> funcionarios;

    @FXML
    public void initialize() {
        funcionarioDAO = new FuncionarioDAO();
        funcionarios = FXCollections.observableArrayList();
        estadoCivilComboBox.setItems(FXCollections.observableArrayList("Soltero", "Casado", "Viudo", "Divorciado", "Unión Libre"));
        sexoComboBox.setItems(FXCollections.observableArrayList("M", "F"));

        // Configura las columnas
        tipoIdentificacionColumn.setCellValueFactory(new PropertyValueFactory<>("tipoIdentificacion"));
        numeroIdentificacionColumn.setCellValueFactory(new PropertyValueFactory<>("numeroIdentificacion"));
        nombresColumn.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        apellidosColumn.setCellValueFactory(new PropertyValueFactory<>("apellidos"));

        // Asigna la lista a la TableView
        funcionariosTableView.setItems(funcionarios);

        // Listener para seleccionar un funcionario de la tabla
        funcionariosTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                mostrarDetallesFuncionario(newSelection);
            } else {
                limpiarCampos();
            }
        });
    }

    @FXML
    private void listarFuncionarios() {
        cargarFuncionariosYRefrescarTabla();
    }

    private void cargarFuncionariosYRefrescarTabla() {
        try {
        List<Funcionario> nuevosFuncionarios = funcionarioDAO.obtenerTodos();
        ObservableList<Funcionario> nuevaLista = FXCollections.observableArrayList(nuevosFuncionarios);
        funcionariosTableView.setItems(nuevaLista);
    } catch (SQLException e) {
        AlertaUtil.mostrarAlertaError("Error al cargar funcionarios", e.getMessage());
    }
    }

    private void cargarFuncionarios() {
        try {
            List<Funcionario> nuevosFuncionarios = funcionarioDAO.obtenerTodos();
            funcionarios.clear();
            funcionarios.addAll(nuevosFuncionarios);
        } catch (SQLException e) {
            AlertaUtil.mostrarAlertaError("Error al cargar funcionarios", e.getMessage());
        }
    }

    private void mostrarDetallesFuncionario(Funcionario funcionario) {
        tipoIdentificacionField.setText(funcionario.getTipoIdentificacion());
        numeroIdentificacionField.setText(funcionario.getNumeroIdentificacion());
        nombresField.setText(funcionario.getNombres());
        apellidosField.setText(funcionario.getApellidos());
        estadoCivilComboBox.setValue(funcionario.getEstadoCivil());
        sexoComboBox.setValue(String.valueOf(funcionario.getSexo()));
        direccionField.setText(funcionario.getDireccion());
        telefonoField.setText(funcionario.getTelefono());
        if (funcionario.getFechaNacimiento() != null) {
            fechaNacimientoPicker.setValue(funcionario.getFechaNacimiento().toLocalDate());
        } else {
            fechaNacimientoPicker.setValue(null);
        }
        universidadField.setText(funcionario.getUniversidad());
        nivelEstudioField.setText(funcionario.getNivelEstudio());
        tituloEstudioField.setText(funcionario.getTituloEstudio());
    }
    
@FXML
    private void guardarFuncionario() {
        Funcionario funcionario = obtenerDatosFormulario();
        if (funcionario != null) {
            try {
                if (funcionarioDAO.obtener(funcionario.getNumeroIdentificacion()) == null) {
                    funcionarioDAO.crear(funcionario);
                    AlertaUtil.mostrarAlertaInformacion("Funcionario guardado", "El funcionario se ha guardado correctamente.");
                } else {
                    funcionarioDAO.actualizar(funcionario);
                    AlertaUtil.mostrarAlertaInformacion("Funcionario actualizado", "El funcionario se ha actualizado correctamente.");
                }
                cargarFuncionariosYRefrescarTabla();
                limpiarCampos();
            } catch (SQLException e) {
                AlertaUtil.mostrarAlertaError("Error al guardar/actualizar funcionario", e.getMessage());
            }
        }
    }

    @FXML
    private void eliminarFuncionario() {
        Funcionario funcionarioSeleccionado = funcionariosTableView.getSelectionModel().getSelectedItem();
        if (funcionarioSeleccionado != null) {
            if (AlertaUtil.mostrarAlertaConfirmacion("Eliminar funcionario", "¿Está seguro de eliminar a " + funcionarioSeleccionado.getNombres() + " " + funcionarioSeleccionado.getApellidos() + "?")) {
                try {
                    funcionarioDAO.eliminar(funcionarioSeleccionado.getNumeroIdentificacion());
                    cargarFuncionariosYRefrescarTabla();
                    limpiarCampos();
                    AlertaUtil.mostrarAlertaInformacion("Funcionario eliminado", "El funcionario se ha eliminado correctamente.");
                } catch (SQLException e) {
                    AlertaUtil.mostrarAlertaError("Error al eliminar funcionario", e.getMessage());
                }
            }
        } else {
            AlertaUtil.mostrarAlertaAdvertencia("Seleccionar funcionario", "Por favor, seleccione un funcionario de la tabla.");
        }
    }

    @FXML
    private void limpiarFormulario() {
        limpiarCampos();
        funcionariosTableView.getSelectionModel().clearSelection();
    }

    private Funcionario obtenerDatosFormulario() {
        String tipoIdentificacion = tipoIdentificacionField.getText();
        String numeroIdentificacion = numeroIdentificacionField.getText();
        String nombres = nombresField.getText();
        String apellidos = apellidosField.getText();
        String estadoCivil = estadoCivilComboBox.getValue();
        String sexoText = sexoComboBox.getValue();
        char sexo = (sexoText != null && !sexoText.isEmpty()) ? sexoText.charAt(0) : '\0';
        String direccion = direccionField.getText();
        String telefono = telefonoField.getText();
        LocalDate fechaNacimientoLocalDate = fechaNacimientoPicker.getValue();
        Date fechaNacimiento = (fechaNacimientoLocalDate != null) ? Date.valueOf(fechaNacimientoLocalDate) : null;
        String universidad = universidadField.getText();
        String nivelEstudio = nivelEstudioField.getText();
        String tituloEstudio = tituloEstudioField.getText();

        if (tipoIdentificacion.isEmpty() || numeroIdentificacion.isEmpty() || nombres.isEmpty() || apellidos.isEmpty()) {
            AlertaUtil.mostrarAlertaAdvertencia("Campos requeridos", "Los campos Tipo de Identificación, Número de Identificación, Nombres y Apellidos son obligatorios.");
            return null;
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setTipoIdentificacion(tipoIdentificacion);
        funcionario.setNumeroIdentificacion(numeroIdentificacion);
        funcionario.setNombres(nombres);
        funcionario.setApellidos(apellidos);
        funcionario.setEstadoCivil(estadoCivil);
        funcionario.setSexo(sexo);
        funcionario.setDireccion(direccion);
        funcionario.setTelefono(telefono);
        funcionario.setFechaNacimiento(fechaNacimiento);
        funcionario.setUniversidad(universidad);
        funcionario.setNivelEstudio(nivelEstudio);
        funcionario.setTituloEstudio(tituloEstudio);
        return funcionario;
    }

    private void limpiarCampos() {
        tipoIdentificacionField.clear();
        numeroIdentificacionField.clear();
        nombresField.clear();
        apellidosField.clear();
        estadoCivilComboBox.setValue(null);
        sexoComboBox.setValue(null);
        direccionField.clear();
        telefonoField.clear();
        fechaNacimientoPicker.setValue(null);
        universidadField.clear();
        nivelEstudioField.clear();
        tituloEstudioField.clear();
    }
}