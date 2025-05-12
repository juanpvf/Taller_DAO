/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iudigital.taller_dao.model;

/**
 *
 * @author Juan Pablo
 */

import java.sql.Date;

public class GrupoFamiliar {
    private int idGrupoFamiliar;
    private String numeroIdentificacionFuncionario;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String rol;

    public GrupoFamiliar() {
    }

    public int getIdGrupoFamiliar() {
        return idGrupoFamiliar;
    }

    public void setIdGrupoFamiliar(int idGrupoFamiliar) {
        this.idGrupoFamiliar = idGrupoFamiliar;
    }

    public String getNumeroIdentificacionFuncionario() {
        return numeroIdentificacionFuncionario;
    }

    public void setNumeroIdentificacionFuncionario(String numeroIdentificacionFuncionario) {
        this.numeroIdentificacionFuncionario = numeroIdentificacionFuncionario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
