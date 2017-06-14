/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.controler;

import br.dao.DaoSistema;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Diovane
 */
public class ImportDriver {

    private static final Logger logger = LoggerFactory
            .getLogger(ImportDriver.class);

    @RequestMapping(value = "/up_driver", method = RequestMethod.POST)
    public ModelAndView up_driver(HttpSession session, @RequestParam("arquivo") MultipartFile[] arquivo, @RequestParam("nomeD") String nomeD) {
        System.out.println("Entrou em adicionar/instalar driver...");
        
        /* verifica se usuario logado */
        if (session.getAttribute("usuarioLogado") == null) {
            System.out.println("sessão nula, retorna para página inicial...");
            ModelAndView mv = new ModelAndView("../../index");
            mv.addObject("notificacaoERRO", "Efetue o login. Obrigado!");
            return mv;
        }

        
        System.out.println("Nome do Driver: " + nomeD + "\nArquivo: " + arquivo);
        ModelAndView mv = new ModelAndView("sistema/adm_driver");

        if (!arquivo[0].isEmpty()) {
            try {
                /********Pega arquivo enviado e o grava *********/
                byte[] bytes_arquivo = arquivo[0].getBytes();
                System.out.println("Gravando arquivo em disco...");

                //cria pacote com o nome do driver digitado
                File criaPacote = new File(
                        "C:\\Users\\Diovane\\Documents\\NetBeansProjects\\sigasamiddleware\\src\\main\\java\\driver\\" + nomeD);
                criaPacote.mkdir();

                //grava o .zip dentro do pacote criado
                File zip = new File(
                        "C:\\Users\\Diovane\\Documents\\NetBeansProjects\\sigasamiddleware\\src\\main\\java\\driver\\" + nomeD + "\\arquivo.zip");

                System.out.println(new File(".").getAbsolutePath());
                System.out.println("Diretório: " + zip.getAbsolutePath());

                OutputStream fout = new FileOutputStream(zip);
                fout.write(bytes_arquivo);
                fout.close();
                System.out.println("Arquivo .zip gravado\nDescompactando classes do .zip");

                //ler o .zip e descompactar classes no pacote criado
                System.out.println("cria  buffer");
                byte[] buffer = new byte[1024];
                
                
                System.out.println("ok\n criando input .zip");
                //cria input do arquivo.zip
                ZipInputStream zipstream
                        = new ZipInputStream(new FileInputStream(zip.getAbsolutePath()));
                
                System.out.println("ok\n proxima entrada do arquivo");
                // Pega a proxima entrada do arquivo
                ZipEntry zentry = zipstream.getNextEntry();
                
                System.out.println("ok\n enquanto existir entrafas no zip");
                // Enquanto existir entradas no ZIP
                while (zentry != null) {
                    // Pega o nome da entrada
                    System.out.println("ok\n entrou while zentry");
                    String entryName = zentry.getName();
                    
                    System.out.println("zentry.getNmae: "+zentry.getName()+" cria output arquivo");
                    // Cria o output do arquivo , Sera extraido onde esta rodando a classe
                    //ageitar aqui
                   FileOutputStream outstream = new FileOutputStream("C:\\Users\\Diovane\\Documents\\NetBeansProjects\\sigasamiddleware\\src\\main\\java\\driver\\" + nomeD + "\\"+entryName);  
                   //System.out.println("Arquivos do pacote (cópia): C:\\Users\\Diovane\\Documents\\NetBeansProjects\\sigasamiddleware\\src\\main\\java\\driver\\" + nomeD + "\\"+entryName );
                    int n;
                    System.out.println("ok\nescreve arquivo");
                    // Escreve no arquivo
                    while ((n = zipstream.read(buffer)) > -1) {
                        System.out.println("while escreve >> "+entryName);
                        outstream.write(buffer, 0, n);
                    }

                    // Fecha arquivo
                    outstream.close();

                    // Fecha entrada e tenta pegar a proxima
                    zipstream.closeEntry();
                    zentry = zipstream.getNextEntry();
                }
                // Fecha o zip como um todo
                zipstream.close();
                
                //apaga o .zip deixando apenas os arquivos descompactados
                zip.delete();
                System.out.println("ZIP deletado");
                
                //listar todas classes do pacote criado com caminho absoluto
                File[] x = criaPacote.listFiles();
                
                for(int i=0 ; i < x.length ; i++){
                    
                System.out.println("Arquivos do pacote: " +x[i]);
                
                }
                
                
                System.out.println("classes inseridas com sucesso no pacote=" + nomeD);
                mv.addObject("notificacaoOK", "DRIVER INSTALADO COM SUCESSO");
            } catch (Exception e) {
                System.out.println("You failed to upload " + nomeD + " => " + e.getMessage());
                mv.addObject("notificacaoERRO", "FALHA NA INSTALAÇÃO DO DRIVER, < " + e.getMessage() + " > ERRO INTERNO");
            }
        } else {
            System.out.println("You failed to upload " + nomeD + " because the file was empty.");
            mv.addObject("notificacaoERRO", "FALHA NA INSTALAÇÃO DO DRIVER, O MESMO NÃO FOI SELECIONADO");
        }

        System.out.println("...Voltando para página...");

        mv.addObject("dados_usuario", new DaoSistema().dadosUsuario());
        mv.addObject("nome_pagina", "SIGASA > <span class=\"mdi-24px mdi mdi-watch-import\"></span> Administrar Drivers");
        return mv;
    }

}

