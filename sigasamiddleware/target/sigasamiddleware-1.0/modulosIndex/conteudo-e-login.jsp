<div class="container">
    <div class="row">
        <div class="col-md-6">
            <!-- imagem logo -->
            <img src="img/logo.png" class="img-rounded" alt="Cinque Terre" width="100%" padding-top="10%;"style="">
        </div>
        <div class="col-md-6"style="border-left: 2px #6da0fd solid;padding-left: 4%;">

            <form method="post" action="paginaprincipal" class="form-horizontal"style="padding-bottom: 25%;">

                <div class="form-group" style="padding-top: 10%;">
                    <h2 style="text-align: center"><b>Entrar no sistema</b></h2>
                    <span class="mdi mdi-36px mdi-account"></span>
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label" style="width: 80%;">
                        <input class="mdl-textfield__input" type="text" id="sample3" name="usuario" required style="text-align: center;border-color: #0053da;">
                        <label class="mdl-textfield__label" for="sample3">Usuário</label>
                    </div>

                </div>

                <div class="form-group">
                    <span class="mdi mdi-36px mdi-rotate-270 mdi-key-variant"></span>
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label"style="width: 80%;">
                        <input class="mdl-textfield__input" type="password" id="sample3" name="senha" required style="text-align: center;border-color: #0053da;">
                        <label class="mdl-textfield__label" for="sample3">Senha</label>
                    </div>
                </div>

                <div class="form-group"> 
                    <div class="col-sm-offset-9 col-sm-3">
                        <button type="submit" class="btn btn-default">ENTRAR <span class="glyphicon glyphicon-log-in"></span></button>
                    </div>
                </div>
                
                <div class="col-md-12" style="text-align: center">
                    <h6 style="">Esqueceu seus dados de acesso? 
                        <a data-toggle="modal" data-target="#myModal" style="cursor:pointer;">
                            <div class="mexe"><span class="glyphicon glyphicon-hand-right"></span> CLIQUE AQUI</div>
                        </a>
                    </h6>
                </div>
            </form>

        </div>
    </div>
</div>
<!-- Modal para recuperação de conta-->
<div id="myModal" class="modal fade" role="dialog" style="    top: 10%;">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content modalCor" style="background-color: rgb(49, 58, 78);color: white;">
            <div class="modal-header">
                <h4 class="modal-title" style="text-align: center"><span class="glyphicon glyphicon-envelope"></span> Recuperação de acesso ao sistema SIGASA</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6">
                        <img src="img/favicon2.png" alt="" style="width: 85%;margin-left: 20px;margin-top: 15%;"/>
                    </div>
                    <div class="col-md-6">
                        <p align="justify">Olá, Caso você já tenha realizado o cadastro no sistema mas esqueceu
                            seus dados de acesso, poderá recuperar-los via e-mail, o mesmo no qual você cadastrou no sistema.</p>
                        <p align="justify">Ao clicar em RECUPERAR, um e-mail será enviado ao seu e-mail cadastrado contendo
                            os passos que você deverá seguir para recuperar seus dados de acesso!</p>
                        <hr>
                        <h6><span class="glyphicon glyphicon-question-sign"></span> Dúvidas: <a href="mailto:diovane.soligo92@gmail.com">suporte@sigasa.com</a></h6>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <form id="form" method="post" action="recuperarContaDeAcesso">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">CANCELAR</button>
                    <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-envelope"></span> RECUPERAR</button>
                </form>
            </div>
        </div>
    </div>
</div>

