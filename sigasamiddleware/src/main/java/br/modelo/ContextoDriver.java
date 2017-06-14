/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.modelo;

import java.sql.Array;

/**
 *
 * @author Diovane
 */
public class ContextoDriver {
    private String nome;
    private String desricao;
    private String fabricante;
    private String versao;
    
    private boolean r_d; //reconhece disp
    private boolean n_c_d_a; //necessita cadastrar disp atraves de app?
    private boolean busca_dados; //busca dodos de disp para app interresada?
    private boolean atua_disp; //tem atuação sobre o disp?
    private boolean teste; //possui teste sem ter app interresada?
    
    private Array sensor;//lista de sensores que driver coleta, se busca dados for true
    private Array atuador;//lista de atuadores que o drive executa
    
    private boolean run; //carregar classes com classLoader?

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesricao() {
        return desricao;
    }

    public void setDesricao(String desricao) {
        this.desricao = desricao;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public boolean isR_d() {
        return r_d;
    }

    public void setR_d(boolean r_d) {
        this.r_d = r_d;
    }

    public boolean isN_c_d_a() {
        return n_c_d_a;
    }

    public void setN_c_d_a(boolean n_c_d_a) {
        this.n_c_d_a = n_c_d_a;
    }

    public boolean isBusca_dados() {
        return busca_dados;
    }

    public void setBusca_dados(boolean busca_dados) {
        this.busca_dados = busca_dados;
    }

    public boolean isAtua_disp() {
        return atua_disp;
    }

    public void setAtua_disp(boolean atua_disp) {
        this.atua_disp = atua_disp;
    }

    public boolean isTeste() {
        return teste;
    }

    public void setTeste(boolean teste) {
        this.teste = teste;
    }

    public Array getSensor() {
        return sensor;
    }

    public void setSensor(Array sensor) {
        this.sensor = sensor;
    }

    public Array getAtuador() {
        return atuador;
    }

    public void setAtuador(Array atuador) {
        this.atuador = atuador;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }
    
    
    
}
