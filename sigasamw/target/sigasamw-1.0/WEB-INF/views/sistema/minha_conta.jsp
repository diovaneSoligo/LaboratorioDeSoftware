<%-- 
    Document   : minha_conta
    Created on : 27/05/2017, 15:43:29
    Author     : Diovane
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="modulosPrincipais/head.jsp"%>
    </head>
    <body>
        <%@include file="modulosPrincipais/nome_pagina.jsp" %>

        <%@include file="modulosPrincipais/munu_lateral.jsp" %>   

        <%@include file="modulosPrincipais/mensagens.jsp" %>
        
        <%@include file="moduloPaginaMinhaConta/conteudoMinhaConta.jsp" %>

        <%@include file="modulosPrincipais/fecha_conteudo-Scripts.jsp" %>
    </body>
</html>
