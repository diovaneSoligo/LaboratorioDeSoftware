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
public class RecuperaEmail {
    private boolean emailSucess;//se foi enviado ou nao
    private int verificacaoINT;//numero randomico para trocar acesso
    private String verificacaoString;//armazena o hash do numero randomico gerado
    private String cpf;//pega o cpf do usuario só pra fazer uma comparação com o que tem no banco

    public int getVerificacaoINT() {
        return verificacaoINT;
    }

    public void setVerificacaoINT(int verificacaoINT) {
        this.verificacaoINT = verificacaoINT;
    }

    public String getVerificacaoString() {
        return verificacaoString;
    }

    public void setVerificacaoString(String verificacaoString) {
        this.verificacaoString = verificacaoString;
    }
    private String rg;
    private String email;

    public boolean isEmailSucess() {
        return emailSucess;
    }

    public void setEmailSucess(boolean emailSucess) {
        this.emailSucess = emailSucess;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
