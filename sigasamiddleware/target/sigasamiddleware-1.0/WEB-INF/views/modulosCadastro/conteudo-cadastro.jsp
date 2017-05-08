<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h1 style="color:red"><span class="glyphicon glyphicon-warning-sign"></span> Aten��o <small>cadastro �nico</small></h1>
            <blockquote class="blockquote">
                <p>
                    Voc� adquiriu nosso produto, ele ser� exclusivo a somente voc� usu�rio.<br>
                    Por esse motivo, <b>os dados pessoais uma vez cadastrados n�o poder�o ser alterados</b>.<br>
                    Os dados que voc� poder� aterar futuramente ser�o apenas, seu nome de usu�rio
                    ,sua senha, e seu e-mail, que servir� para recuperar seu dados de acesso caso os esque�a.<br>
                    Ao preencher todos os dados e clicar em CADASTRAR, essa p�gina n�o ser� mais exibida!<br>
                    Preencha corretamente todos os dados solicitados e desfrute da sua nova aquisi��o!
                </p>
                <footer>MiddleWare SIGASA<br>Sistema Intermedi�rio para Gerenciamento de Aplica��es Sensores e Atuadores</footer>
            </blockquote>
        </div>
        <div class="col-md-6"style="border-left: 2px #6da0fd solid;padding-left: 4%;">

            <form method="post" action="cadastro_unico" class="form-horizontal" style="padding-top: 5%" name="formCad">

                <div class="form-group">
                    <div class="col-sm-11">
                        <input type="text" class="form-control" placeholder="Nome" name="nome" style="text-align: center;font-size: x-large;" required>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-11">
                        <input type="text" class="form-control" placeholder="Sobrenome" name="sobrenome" style="text-align: center;font-size: x-large;" required>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-11">
                        <input type="email" class="form-control" placeholder="E-mail" name="email" style="text-align: center;font-size: x-large;" required>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-11">
                        <input type="text" class="form-control" placeholder="RG" id="RG" name="rg" style="text-align: center;font-size: x-large;" required>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-11">
                        <input type="text" class="form-control" placeholder="CPF" id="CPF" name="cpf" style="text-align: center;font-size: x-large;" required>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-11">
                        <input type="text" class="form-control" id="nascimento" placeholder="Data de nascimento" name="nascimento" style="text-align: center;font-size: x-large;" required>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-11">
                        <input type="text" class="form-control" placeholder="Nome de usu�rio" id="user" name="usuario" style="text-align: center;font-size: x-large;" required>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-11">
                        <input type="password" class="form-control" placeholder="Senha" name="senha" style="text-align: center;font-size: x-large;" required>
                    </div>
                </div>

                <div class="form-group"> 
                    <div class="col-sm-offset-7 col-sm-3">
                        <button type="submit" class="btn btn-default">CADASTRAR <span class="glyphicon glyphicon-lock"></span></button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
  jQuery(function($){
   $("#nascimento").mask("99/99/9999");
   $("#CPF").mask("999.999.999-99");
   $("#RG").mask("9999999999");
});
window.onload = validar();
function validar() {
    
    var nome = formCad.usuario.value;

    if (nome == "admin") {
    document.getElementById('user').value='';
    alert('ATEN��O!\n\n"admin" � um nome reservado do sistema, por isso escolha outro nome de usu�rio para acessar o sistema.\n\nObrigado!');
    }
setInterval(validar, 5);

}
</script>
