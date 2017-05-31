<c:if test="${not empty dados_usuario}">
    <div class="container">
        <div class="row">
            <div class="col-md-4" style="border-right: #c8c8c8 2px solid;">
                <!-- dados pessoais do usuário -->
                <h3 style="text-align: center">Dados pessoais</h3>
                <ul class="demo-list-icon mdl-list">
                    <li class="mdl-list__item">
                        <span class="mdl-list__item-primary-content">
                            <i class="material-icons mdl-list__item-icon">lock</i>
                            Nome: ${dados_usuario.nome}
                        </span>
                    </li>
                    <li class="mdl-list__item">
                        <span class="mdl-list__item-primary-content">
                            <i class="material-icons mdl-list__item-icon">lock</i>
                            Sobrenome: ${dados_usuario.sobrenome}
                        </span>
                    </li>
                    <li class="mdl-list__item">
                        <span class="mdl-list__item-primary-content">
                            <i class="material-icons mdl-list__item-icon">lock</i>
                            CPF: ${dados_usuario.cpf}
                        </span>
                    </li>
                    <li class="mdl-list__item">
                        <span class="mdl-list__item-primary-content">
                            <i class="material-icons mdl-list__item-icon">lock</i>
                            RG: ${dados_usuario.rg}
                        </span>
                    </li>
                    <li class="mdl-list__item">
                        <span class="mdl-list__item-primary-content">
                            <i class="material-icons mdl-list__item-icon">lock</i>
                            Nacimento: ${dados_usuario.nascimento}
                        </span>
                    </li>
                </ul>
            </div>

            <div class="col-md-1">

            </div>

            <div class="col-md-6">
                <h3>Alterar dados de acesso</h3>
                <form method="post" action="altera_dados_">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" id="usuario" name="usuario" value="${dados_usuario.usuario}" style="text-align: center;" required>
                        <label class="mdl-textfield__label" for="usuario">NOME DE USUÁRIO DE ACESSO</label>
                        <div class="mdl-tooltip" data-mdl-for="usuario">Para alterar o usuário é necessário inserir a senha atual</div>
                    </div>

                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="email" name="email" id="email" value="${dados_usuario.email}" style="text-align: center;" required>
                        <label class="mdl-textfield__label" for="email">E-MAIL</label>
                        <div class="mdl-tooltip" data-mdl-for="email">Para alterar o e-mail é necessário inserir a senha atual</div>
                    </div>

                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="password" name="senha_nova" id="nova_senha" style="text-align: center;" >
                        <label class="mdl-textfield__label" for="nova_senha">NOVA SENHA</label>
                        <div class="mdl-tooltip" data-mdl-for="nova_senha">Para alterar a senha é necessário inserir a senha atual</div>
                    </div>    

                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="password" name="senha" id="atual_senha" style="text-align: center;" required>
                        <label class="mdl-textfield__label" for="atual_senha">INSIRA A SENHA ATUAL</label>
                        <div class="mdl-tooltip" data-mdl-for="atual_senha">Inserir a senha atual</div>
                    </div>  
                    <br>
                    <button type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">
                        ALTERAR E SALVAR
                    </button>
                </form>

            </div>
        </div>
    </div>
</c:if>