/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iudigital.taller_dao.model;

/**
 *
 * @author Juan Pablo
 */

public class InformacionAcademica {
    private int idInformacionAcademica;
    private String numeroIdentificacionFuncionario;
    private String universidad;
    private String nivelEstudio;
    private String tituloEstudio;

    public InformacionAcademica() {
    }

    public int getIdInformacionAcademica() {
        return idInformacionAcademica;
    }

    public void setIdInformacionAcademica(int idInformacionAcademica) {
        this.idInformacionAcademica = idInformacionAcademica;
    }

    public String getNumeroIdentificacionFuncionario() {
        return numeroIdentificacionFuncionario;
    }

    public void setNumeroIdentificacionFuncionario(String numeroIdentificacionFuncionario) {
        this.numeroIdentificacionFuncionario = numeroIdentificacionFuncionario;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public String getNivelEstudio() {
        return nivelEstudio;
    }

    public void setNivelEstudio(String nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }

    public String getTituloEstudio() {
        return tituloEstudio;
    }

    public void setTituloEstudio(String tituloEstudio) {
        this.tituloEstudio = tituloEstudio;
    }
}
