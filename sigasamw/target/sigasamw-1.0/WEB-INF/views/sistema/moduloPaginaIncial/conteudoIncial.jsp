<div class="row">
    <div class="col-md-6">
        <h3 style="text-align: center"><span class="mdi mdi-flip-to-front"></span> SUAS APLICAÇÕES</h3>

    </div>
    <form method="post" action="ver_sensores_driver" id="ver"></form>
    <div class="col-md-6" style="margin-top: 2%;">
        <h4 style="text-align:center"><span class="mdi mdi-flip-to-back"></span><span class="mdl-badge" data-badge="${drivers_com_sensores_reconhecos.size()}">DRIVERS COM SENSORES RECONHECIDOS</span></h4>
        <table class="mdl-data-table  mdl-shadow--2dp">
            <thead>
                <tr>
                    <th style="text-align: center;">Nome do Driver</th>
                    <th style="text-align: center;">Sensores Reconhecidos</th>
                    <th style="text-align: center;">Visualizar Sensores</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="x" items="${drivers_com_sensores_reconhecos}">
                <tr>
                    <td style="text-align: center;">${x.nomeDriver}</td>

                    <td style="text-align: center;" id="sensores${x.ID}"><span class="mdl-badge mdl-badge--no-background" data-badge="${x.sensoresReconhecidos}">Sensores </span></td>
                <div class="mdl-tooltip" data-mdl-for="sensores${x.ID}">
                    ${x.sensoresReconhecidos} Sensore(s) reconhecido(s) referente a última atualização!
                </div>

                <td style="cursor: pointer;text-align: center">
                    <button form="ver" name="ID" value="${x.ID}" id="sensore${x.ID}" style="background: #5bc11d;color: rgb(249, 248, 248);" class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-button--colored">
                        <i class="material-icons">visibility</i>
                    </button>
                </td>
                <div class="mdl-tooltip" data-mdl-for="sensore${x.ID}">
                    VISUALIZAR OU ATUALIZAR SENSORES DE <br> < ${x.nomeDriver} >
                </div>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
