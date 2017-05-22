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

            </form>
        </div>
    </div>
</div>