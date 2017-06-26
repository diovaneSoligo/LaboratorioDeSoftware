/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.modelo;

/**
 *
 * @author Diovane
 */
public class Sensor {
    private int ID;
    private String idSensor;
    private String ipSensor;
    private String retornoDados;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(String idSensor) {
        this.idSensor = idSensor;
    }

    public String getIpSensor() {
        return ipSensor;
    }

    public void setIpSensor(String ipSensor) {
        this.ipSensor = ipSensor;
    }

    public String getRetornoDados() {
        return retornoDados;
    }

    public void setRetornoDados(String retornoDados) {
        this.retornoDados = retornoDados;
    }
    
    
}
