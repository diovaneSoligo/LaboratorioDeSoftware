<div style="margin-right: 5%;margin-left: 5%;margin-top: 1%;border-bottom: 4px #d3394c solid;border-top: 4px #d3394c solid;">
    <div class="row" style="">
        <h6>
            <span class="mdi mdi-24px mdi-comment-alert-outline"></span>
            Selecione o driver "nome_do_driver.jar" e logo após clique em INSTALAR
        </h6>
        <form method="POST" action="" enctype="multipart/form-data">
            <div class="col-md-8">
                <input accept=".jar" style="display: none" type="file" name="file-7[]" id="file-7" class="inputfile inputfile-6" data-multiple-caption="{count} files selected" multiple />
                <label for="file-7">
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
        <h4 style="text-align: center">Drivers Instalados</h4>
        <br>
            <div class="">
                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-hover" >
                            <thead>
                                <tr>
                                    <th>STATUS</th>
                                    <th>NOME DO DRIVER</th>
                                    <th>FABRICANTE</th>
                                    <th style="text-align: center">Desativar/Ativar</th>
                                    <th style="text-align: center">DELETAR</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td id="status1"><span class="mdi mdi-24px mdi-sync-off"></span></td>
                                    <div class="mdl-tooltip" data-mdl-for="status1">
                                        Driver desativado :)
                                    </div>

                                    <td style="cursor: default" id="EE1">EFICIENCIA ENERGETICA</td>
                                    <div class="mdl-tooltip" data-mdl-for="EE1">
                                        Monitoramento de energia elétrica dos equipamentos presentes na residência
                                    </div>

                                    <td style="cursor: default" id="EEF1">UFSM-CPOL-DS</td>
                                    <div class="mdl-tooltip" data-mdl-for="EEF1">
                                        Fabricante:<br>Universidade Federal de Santa Maria - Colégio Politécnico - Diovane Soligo
                                    </div>

                                    <th>
                                        <label style="margin-left: 40%;" class="mdl-switch mdl-js-switch mdl-js-ripple-effect" for="switch-1">
                                            <input type="checkbox" id="switch-1" class="mdl-switch__input">
                                                <span class="mdl-switch__label"></span>
                                        </label>
                                    </th>

                                    <td style="cursor: pointer;text-align: center">
                                        <button  id="del1" style="background: #d3394c;color: rgb(249, 248, 248);" class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-button--colored">
                                            <i class="material-icons">delete</i>
                                        </button>
                                    </td>
                                    <div class="mdl-tooltip" data-mdl-for="del1">
                                        Deletar Driver EFICIENCIA ENERGETICA ?
                                    </div>
                                </tr>
                                <tr>
                                    <td id="status4"><span class="mdi mdi-spin mdi-24px mdi-sync"></span></td>
                                    <div class="mdl-tooltip" data-mdl-for="status4">
                                        <span class="mdi mdi-emoticon-cool"></span> Tudo OK
                                    </div>
                                    
                                    <td style="cursor: default" id="EE4">ALARME</td>
                                    <div class="mdl-tooltip" data-mdl-for="EE4">
                                        Monitoramento do alarme
                                    </div>

                                    <td style="cursor: default" id="EEF4">Alarmes e CIA</td>
                                    <div class="mdl-tooltip" data-mdl-for="EEF4">
                                        Fabricante:<br>Alarmes e CIA
                                    </div>
                                    
                                    <th>
                                        <label style="margin-left: 40%;" class="mdl-switch mdl-js-switch mdl-js-ripple-effect" for="switch-4">
                                            <input type="checkbox" id="switch-4" class="mdl-switch__input" checked>
                                                <span class="mdl-switch__label"></span>
                                        </label>
                                    </th>
                                    
                                    <td style="cursor: pointer;text-align: center">
                                        <button  id="del4" style="background: #d3394c;color: rgb(249, 248, 248);" class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-button--colored">
                                            <i class="material-icons">delete</i>
                                        </button>
                                    </td>
                                    <div class="mdl-tooltip" data-mdl-for="del4">
                                        Deletar Driver ALARME ?
                                    </div>
                                </tr>
                                <tr>
                                    <td id="status2"><span class="mdi mdi-24px mdi-sync-alert"></span></td>
                                    <div class="mdl-tooltip" data-mdl-for="status2">
                                        <span class="mdi mdi-alert"></span> Algo não está certo
                                    </div>
                                    
                                    <td style="cursor: default" id="EE2">TEMPERATURA RESIDENCIAL</td>
                                    <div class="mdl-tooltip" data-mdl-for="EE2">
                                        Monitoramento de Temperatura Residencial
                                    </div>

                                    <td style="cursor: default" id="EEF2">UFSM-CPOL-DS</td>
                                    <div class="mdl-tooltip" data-mdl-for="EEF2">
                                        Fabricante:<br>Universidade Federal de Santa Maria - Colégio Politécnico - Diovane Soligo
                                    </div>
                                    
                                    <th>
                                        <label style="margin-left: 40%;" class="mdl-switch mdl-js-switch mdl-js-ripple-effect" for="switch-2">
                                            <input type="checkbox" id="switch-2" class="mdl-switch__input" checked>
                                                <span class="mdl-switch__label"></span>
                                        </label>
                                    </th>
                                    
                                    <td style="cursor: pointer;text-align: center">
                                        <button  id="del2" style="background: #d3394c;color: rgb(249, 248, 248);" class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-button--colored">
                                            <i class="material-icons">delete</i>
                                        </button>
                                    </td>
                                    <div class="mdl-tooltip" data-mdl-for="del2">
                                        Deletar Driver TEMPERATURA RESIDENCIAL ?
                                    </div>
                                </tr>
                                <tr>
                                    <td id="status3"><span class="mdi mdi-spin mdi-24px mdi-sync"></span></td>
                                    <div class="mdl-tooltip" data-mdl-for="status3">
                                        <span class="mdi mdi-emoticon-cool"></span> Tudo OK
                                    </div>
                                    
                                    <td style="cursor: default" id="EE3">HUMIDADE</td>
                                    <div class="mdl-tooltip" data-mdl-for="EE3">
                                        Monitoramento da humidade residencial
                                    </div>

                                    <td style="cursor: default" id="EEF3">UFSM-CPOL-DS</td>
                                    <div class="mdl-tooltip" data-mdl-for="EEF3">
                                        Fabricante:<br>Universidade Federal de Santa Maria - Colégio Politécnico - Diovane Soligo
                                    </div>
                                    
                                    <th>
                                        <label style="margin-left: 40%;" class="mdl-switch mdl-js-switch mdl-js-ripple-effect" for="switch-3">
                                            <input type="checkbox" id="switch-3" class="mdl-switch__input" checked>
                                                <span class="mdl-switch__label"></span>
                                        </label>
                                    </th>
                                    
                                    <td style="cursor: pointer;text-align: center">
                                        <button  id="del3" style="background: #d3394c;color: rgb(249, 248, 248);" class="mdl-button mdl-js-button mdl-button--fab mdl-button--mini-fab mdl-button--colored">
                                            <i class="material-icons">delete</i>
                                        </button>
                                    </td>
                                    <div class="mdl-tooltip" data-mdl-for="del3">
                                        Deletar Driver HUMIDADE ?
                                    </div>
                                </tr>
                                
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>