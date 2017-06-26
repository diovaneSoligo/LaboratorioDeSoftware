<br><a href="administrar_driver"type="button" style="margin-left: 3%;" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect"><span class="mdi mdi-24px mdi-reply"></span> VOLTAR</a><br>
<h4 style="text-align: center"><span class="mdi mdi-chemical-weapon"></span> ${dados_driver.nomeDriver}. <span class="mdl-badge" data-badge="${lista_sensores.size()}"></span>Sensores reconhecidos</h4> 
<h6 style="text-align: center">Descrição: < ${dados_driver.descricaoDriver} ></h6>
<br>
<form method="post" action="invokeSensor" id="invoke"></form>
<div class="">
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" >
                <thead>
                    <tr>
                        <th style="text-align: center">ID DO SENSOR</th>
                        <th style="text-align: center">IP DO SENSOR NA REDE</th>
                        <th style="text-align: center">TESTAR - BUSCAR DADOS</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="x" items="${lista_sensores}">
                    <tr>
                        <td style="cursor: default;text-align: center">${x.idSensor}</td>

                        <td style="cursor: default;text-align: center">${x.ipSensor}</td>

                        <td style="cursor: pointer;text-align: center">
                            <button form="invoke" name="idSensor" value="${x.idSensor}" id="invoke${x.idSensor}" style="background: #d3ce39;;color: rgb(249, 248, 248);" class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-button--colored">
                                <span class="mdi mdi-chemical-weapon"></span>
                            </button>
                        </td>
                    <div class="mdl-tooltip" data-mdl-for="invoke${x.idSensor}">
                        <p> Invocar --> ${x.idSensor} <-- em ${x.ipSensor} e mostrar seu retorno</p>
                    </div>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

