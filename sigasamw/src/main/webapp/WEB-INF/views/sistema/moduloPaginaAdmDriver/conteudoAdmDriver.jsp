

<div style="margin-right: 5%;margin-left: 5%;margin-top: 1%;border-bottom: 4px #d3394c solid;border-top: 4px #d3394c solid;">
    <div class="row" style="">
        <h6>
            <span class="mdi mdi-24px mdi-comment-alert-outline"></span>
            QUER INSTALAR UM DRIVER / MÓDULO?<br>Selecione o "NOME_EXEMPLO_DRIVER.JAR", e logo após clique em INSTALAR
        </h6>
        <form method="POST" action="up_driver" enctype="multipart/form-data">
            <div class="col-md-8">
                <!-- accept=".jar" -->
                <input accept=".jar" style="display: none" type="file" name="arquivo" id="file" class="inputfile inputfile-6" required />
                <label for="file">
                    <span></span>
                    <strong>
                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="17" viewBox="0 0 20 17">
                            <path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"/>
                        </svg> Selecionar o Driver a ser Instalado&hellip;
                    </strong>
                </label>
            </div>
            <div class="col-md-3">
                <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">
                    <span class="mdi mdi-watch-import"></span>
                    Instalar
                </button>
            </div>
        </form>
    </div>
</div>
<br><br>
        <form method="post" action="ver_sensores_driver" id="ver"></form>
        <form method="post" action="atualizar_sensores_driver" id="atualizar"></form>
        <form method="post" action="deletar_driver" id="deletar"></form>
        <h4 style="text-align: center"><span class="mdi mdi-chemical-weapon"></span> Drivers Instalados <span class="mdl-badge" data-badge="${drivers_modulos.size()}"></span></h4>
        <br>
            <div class="">
                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-hover" >
                            <thead>
                                <tr>
                                    <th style="text-align: center">NOME DO DRIVER/MÓDULO</th>
                                    <th style="text-align: center">FABRICANTE</th>
                                    <th style="text-align: center">Pack/Versão</th>
                                    <th style="text-align: center">VER SENSORES</th>
                                    <th style="text-align: center">ATUALIZAR SENSORES</th>
                                    <th style="text-align: center">DELETAR</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="x" items="${drivers_modulos}">
                                    <tr>
                                        <!-- nome sensor -->
                                        <td style="cursor: default;text-align: center" id="descricao${x.ID}"><span class="mdi mdi-chemical-weapon"></span> ${x.nomeDriver}</td>
                                        <div class="mdl-tooltip" data-mdl-for="descricao${x.ID}">
                                           <p>Descrição: ${x.descricaoDriver}</p>
                                        </div>

                                        <!-- fabricante -->
                                        <td style="cursor: default;text-align: center">${x.fabricanteDriver}</td>

                                        <!-- pack/versao -->
                                        <td style="cursor: default;text-align: center">${x.packDriver}</td>

                                        <!-- ver sensores -->
                                        <td style="cursor: pointer;text-align: center">
                                            <button form="ver" name="ID" value="${x.ID}" id="sensores${x.ID}" style="background: #5bc11d;color: rgb(249, 248, 248);" class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-button--colored">
                                                <i class="material-icons">visibility</i>
                                            </button>
                                        </td>
                                        <div class="mdl-tooltip" data-mdl-for="sensores${x.ID}">
                                            <p>VISUALIZAR SENSORES IDENTIFICADOS NA REDE</p>
                                        </div>

                                        <!-- atualizar sensores -->
                                        <td style="cursor: pointer;text-align: center">
                                            <button form="atualizar" name="ID" value="${x.ID}" id="atualizar${x.ID}" style="background: #6f66b5;color: rgb(249, 248, 248);" class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-button--colored">
                                                <i class="material-icons">cached</i>
                                            </button>
                                        </td>
                                        <div class="mdl-tooltip" data-mdl-for="atualizar${x.ID}">
                                            <p>ATUALIZAR/BUSCAR SENSORES NA REDE!</p>
                                        </div>

                                        <!-- deletar driver/modulo -->
                                        <td style="cursor: pointer;text-align: center">
                                            <button form="deletar" name="ID" value="${x.ID}" id="deletar${x.ID}" style="background: #d3394c;color: rgb(249, 248, 248);" class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-button--colored">
                                                <i class="material-icons">delete</i>
                                            </button>
                                        </td>
                                        <div class="mdl-tooltip" data-mdl-for="deletar${x.ID}">
                                            <p>DELETAR ${x.nomeDriver} ?</p>
                                        </div>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>