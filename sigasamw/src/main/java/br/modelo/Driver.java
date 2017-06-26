/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.modelo;

import java.io.File;
import java.util.List;

/**
 *
 * @author Diovane
 */
public class Driver {
    private int ID;
    private File URIDriver;
    private String nomeJarDriver;
    private String pacoteDriver;
    private String classeDriver;
    private String nomeDriver;
    private String descricaoDriver;
    private String fabricanteDriver;
    private String packDriver;
    private List<String> sensores;//(ID) ; (IP)

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public File getURIDriver() {
        return URIDriver;
    }

    public void setURIDriver(File URIDriver) {
        this.URIDriver = URIDriver;
    }

    public String getNomeJarDriver() {
        return nomeJarDriver;
    }

    public void setNomeJarDriver(String nomeJarDriver) {
        this.nomeJarDriver = nomeJarDriver;
    }

    public String getPacoteDriver() {
        return pacoteDriver;
    }

    public void setPacoteDriver(String pacoteDriver) {
        this.pacoteDriver = pacoteDriver;
    }

    public String getClasseDriver() {
        return classeDriver;
    }

    public void setClasseDriver(String classeDriver) {
        this.classeDriver = classeDriver;
    }

    public String getNomeDriver() {
        return nomeDriver;
    }

    public void setNomeDriver(String nomeDriver) {
        this.nomeDriver = nomeDriver;
    }

    public String getDescricaoDriver() {
        return descricaoDriver;
    }

    public void setDescricaoDriver(String descricaoDriver) {
        this.descricaoDriver = descricaoDriver;
    }

    public String getFabricanteDriver() {
        return fabricanteDriver;
    }

    public void setFabricanteDriver(String fabricanteDriver) {
        this.fabricanteDriver = fabricanteDriver;
    }

    public String getPackDriver() {
        return packDriver;
    }

    public void setPackDriver(String packDriver) {
        this.packDriver = packDriver;
    }

    public List<String> getSensores() {
        return sensores;
    }

    public void setSensores(List<String> sensores) {
        this.sensores = sensores;
    }
}
