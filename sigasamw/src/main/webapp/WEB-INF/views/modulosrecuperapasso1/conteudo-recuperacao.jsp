<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h1 style="color:red"><span class="glyphicon glyphicon-warning-sign"></span> Atenção <small>Recuperar acesso ao sistema</small></h1>
            <blockquote class="blockquote">
                <p>
                    Você solicitou a recuperação de acesso ao sistema<br><br>
                    Ao ter clicado em RECUPERAR um código de verificação foi enviado ao seu e-mail cadastrado no sistema<br><br>
                    Insira o código e seu CPF e clique em VERIFICAR<br><br>
                    O código de verificação terá duração de 5 minutos, após este tempo será necessário refazer o RECUPERAR, e gerar um novo código!
                </p>
                <footer>MiddleWare SIGASA<br>Sistema Intermediário para Gerenciamento de Aplicações Sensores e Atuadores</footer>
            </blockquote>
        </div>
        <div class="col-md-6"style="border-left: 2px #6da0fd solid;padding-left: 4%;">

            <form method="post" action="recuperarConta" class="form-horizontal" style="padding-top: 5%" name="formCad">
                <p>Insira o código e seu CPF</p>
                <div class="form-group">
                    <div class="col-sm-11">
                        <input type="text" class="form-control" placeholder="Código de verificação" name="verificacaoString" style="text-align: center;" pattern="[0-9]+" required>
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
