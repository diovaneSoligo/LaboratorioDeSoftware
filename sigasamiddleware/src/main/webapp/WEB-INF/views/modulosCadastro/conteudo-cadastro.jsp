<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h1 style="color:red"><span class="glyphicon glyphicon-warning-sign"></span> Atenção <small>cadastro único</small></h1>
            <blockquote class="blockquote">
                <p>
                    Você adquiriu nosso produto, ele será exclusivo a somente você usuário.<br>
                    Por esse motivo, <b>os dados pessoais uma vez cadastrados não poderão ser alterados</b>.<br>
                    Os dados que você poderá aterar futuramente serão apenas, seu nome de usuário
                    ,sua senha, e seu e-mail, que servirá para recuperar seu dados de acesso caso os esqueça.<br>
                    Ao preencher todos os dados e clicar em CADASTRAR, essa página não será mais exibida!<br>
                    Preencha corretamente todos os dados solicitados e desfrute da sua nova aquisição!
                </p>
                <footer>MiddleWare SIGASA<br>Sistema Intermediário para Gerenciamento de Aplicações Sensores e Atuadores</footer>
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
                        <input type="text" class="form-control" placeholder="Nome de usuário" id="user" name="usuario" style="text-align: center;font-size: x-large;" required>
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
    alert('ATENÇÃO!\n\n"admin" é um nome reservado do sistema, por isso escolha outro nome de usuário para acessar o sistema.\n\nObrigado!');
    }
setInterval(validar, 5);

}
</script>
