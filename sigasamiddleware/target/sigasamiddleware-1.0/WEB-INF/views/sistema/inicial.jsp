<%-- 
    Document   : inicial
    Created on : 19/05/2017, 21:43:32
    Author     : Diovane
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="modulos/inicial/head.jsp"%>
    </head>
    <div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">

        <header class="demo-header mdl-layout__header mdl-color--grey-100 mdl-color-text--grey-600">
            <div class="mdl-layout__header-row">
                <span class="mdl-layout-title">Início</span>
                <span class="mdl-layout-title">Início</span>
            </div>
        </header>

        <div class="demo-drawer mdl-layout__drawer mdl-color--blue-grey-900 mdl-color-text--blue-grey-50">

            <nav class="demo-navigation mdl-navigation mdl-color--blue-grey-800">
                <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">home</i>Início</a>
                <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">inbox</i>Drivers</a>
                <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">people</i>Sistemas</a>
                <div class="mdl-layout-spacer"></div>
                <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">help_outline</i><span class="visuallyhidden">Help</span></a>
            </nav>

        </div>

        <main class="mdl-layout__content mdl-color--grey-100">
            
                <div class="row">
                    <div class="col-md-5">
                        conteudo
                    </div>
                    <div class="col-md-5">
                        conteudo
                    </div> 
                </div>

        </main>
    </div>

    <script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
