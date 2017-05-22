<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h1 style="color:red"><span class="glyphicon glyphicon-warning-sign"></span> Aten��o <small>Recuperar acesso ao sistema</small></h1>
            <blockquote class="blockquote">
                <p>
                    Voc� solicitou a recupera��o de acesso ao sistema<br><br>
                    Ao ter clicado em RECUPERAR um c�digo de verifica��o foi enviado ao seu e-mail cadastrado no sistema<br><br>
                    Insira o c�digo e seu CPF e clique em VERIFICAR<br><br>
                    O c�digo de verifica��o ter� dura��o de 5 minutos, ap�s este tempo ser� necess�rio refazer o RECUPERAR, e gerar um novo c�digo!
                </p>
                <footer>MiddleWare SIGASA<br>Sistema Intermedi�rio para Gerenciamento de Aplica��es Sensores e Atuadores</footer>
            </blockquote>
        </div>
        <div class="col-md-6"style="border-left: 2px #6da0fd solid;padding-left: 4%;">

            <form method="post" action="recuperarConta" class="form-horizontal" style="padding-top: 5%" name="formCad">
                <p>Insira o c�digo e seu CPF</p>
                <div class="form-group">
                    <div class="col-sm-11">
                        <input type="text" class="form-control" placeholder="C�digo de verifica��o" name="verificacaoString" style="text-align: center;" pattern="[0-9]+" required>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-11">
                        <input type="text" class="form-control" placeholder="CPF" id="CPF" name="cpf" style="text-align: center;" required>
                    </div>
                </div>

                <div class="form-group"> 
                    <div class="col-sm-offset-7 col-sm-3">
                        <button type="submit" class="btn btn-default">VERIFICAR <span class="glyphicon glyphicon-random"></span></button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
  jQuery(function($){
   $("#CPF").mask("999.999.999-99");
});

</script>
