/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iudigital.taller_dao.dao;

/**
 *
 * @author Juan Pablo
 */

import com.iudigital.taller_dao.model.Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    private Connection connection;

    public FuncionarioDAO() {
        connection = Conexion.obtenerConexion();
    }

    public void crear(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO FUNCIONARIO (tipo_identificacion, numero_identificacion, nombres, apellidos, estado_civil, sexo, direccion, telefono, fecha_nacimiento, universidad, nivel_estudio, titulo_estudio) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, funcionario.getTipoIdentificacion());
            pstmt.setString(2, funcionario.getNumeroIdentificacion());
            pstmt.setString(3, funcionario.getNombres());
            pstmt.setString(4, funcionario.getApellidos());
            pstmt.setString(5, funcionario.getEstadoCivil());
            pstmt.setString(6, String.valueOf(funcionario.getSexo()));
            pstmt.setString(7, funcionario.getDireccion());
            pstmt.setString(8, funcionario.getTelefono());
            pstmt.setDate(9, funcionario.getFechaNacimiento());
            pstmt.setString(10, funcionario.getUniversidad());
            pstmt.setString(11, funcionario.getNivelEstudio());
            pstmt.setString(12, funcionario.getTituloEstudio());
            pstmt.executeUpdate();
        }
    }

    public Funcionario obtener(String numeroIdentificacion) throws SQLException {
        String sql = "SELECT * FROM FUNCIONARIO WHERE numero_identificacion = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, numeroIdentificacion);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToFuncionario(rs);
            }
            return null;
        }
    }

    public List<Funcionario> obtenerTodos() throws SQLException {
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> funcionarios = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                funcionarios.add(mapResultSetToFuncionario(rs));
            }
        }
        return funcionarios;
    }

    public void actualizar(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE FUNCIONARIO SET tipo_identificacion = ?, nombres = ?, apellidos = ?, estado_civil = ?, sexo = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?, universidad = ?, nivel_estudio = ?, titulo_estudio = ? WHERE numero_identificacion = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, funcionario.getTipoIdentificacion());
            pstmt.setString(2, funcionario.getNombres());
            pstmt.setString(3, funcionario.getApellidos());
            pstmt.setString(4, funcionario.getEstadoCivil());
            pstmt.setString(5, String.valueOf(funcionario.getSexo()));
            pstmt.setString(6, funcionario.getDireccion());
            pstmt.setString(7, funcionario.getTelefono());
            pstmt.setDate(8, funcionario.getFechaNacimiento());
            pstmt.setString(9, funcionario.getUniversidad());
            pstmt.setString(10, funcionario.getNivelEstudio());
            pstmt.setString(11, funcionario.getTituloEstudio());
            pstmt.setString(12, funcionario.getNumeroIdentificacion());
            pstmt.executeUpdate();
        }
    }

    public void eliminar(String numeroIdentificacion) throws SQLException {
        String sql = "DELETE FROM FUNCIONARIO WHERE numero_identificacion = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, numeroIdentificacion);
            pstmt.executeUpdate();
        }
    }

    private Funcionario mapResultSetToFuncionario(ResultSet rs) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setTipoIdentificacion(rs.getString("tipo_identificacion"));
        funcionario.setNumeroIdentificacion(rs.getString("numero_identificacion"));
        funcionario.setNombres(rs.getString("nombres"));
        funcionario.setApellidos(rs.getString("apellidos"));
        funcionario.setEstadoCivil(rs.getString("estado_civil"));
        funcionario.setSexo(rs.getString("sexo").charAt(0));
        funcionario.setDireccion(rs.getString("direccion"));
        funcionario.setTelefono(rs.getString("telefono"));
        funcionario.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        funcionario.setUniversidad(rs.getString("universidad"));
        funcionario.setNivelEstudio(rs.getString("nivel_estudio"));
        funcionario.setTituloEstudio(rs.getString("titulo_estudio"));
        return funcionario;
    }
}
