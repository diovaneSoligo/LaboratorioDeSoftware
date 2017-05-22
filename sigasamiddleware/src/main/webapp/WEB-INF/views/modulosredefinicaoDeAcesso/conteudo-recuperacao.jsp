<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h1 style="color:red"><span class="glyphicon glyphicon-warning-sign"></span> Aten��o <small>Redefinir o acesso a conta</small></h1>
            <blockquote class="blockquote">
                <p>
                    Para redefinir seus dados de acesso ao sistema coloque um novo nome de usu�rio e uma nova senha<br>
                    Ap�s isto clique em redefinir acesso<br>
                </p>
                <footer>MiddleWare SIGASA<br>Sistema Intermedi�rio para Gerenciamento de Aplica��es Sensores e Atuadores</footer>
            </blockquote>
        </div>
        <div class="col-md-6"style="border-left: 2px #6da0fd solid;padding-left: 4%;">

            <form method="post" action="rEdeFinic@o_de-C0nta" class="form-horizontal" style="padding-top: 5%" name="formCad">
                <p>Redefina seu acesso<br>Coloque um novo nome de usu�rio e uma nova senha</p>
                <div class="form-group">
                    <div class="col-sm-11">
                        <input type="text" class="form-control" id="user" placeholder="NOVO NOME DE USU�RIO" name="usuario" style="text-align: center;" required>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-11">
                        <input type="password" class="form-control" placeholder="NOVA SENHA" name="senha" style="text-align: center;" required>
                    </div>
                </div>

                <div class="form-group"> 
                    <div class="col-sm-offset-7 col-sm-3">
                        <button type="submit" class="btn btn-default">REDEFINIR ACESSO <span class="glyphicon glyphicon-floppy-disk"></span></button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
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
