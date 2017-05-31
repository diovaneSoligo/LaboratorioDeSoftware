<!-- Div fecha em outro modulo -->
<div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
    <!-- Barra informa nome página -->
    <header class="demo-header mdl-layout__header mdl-color--grey-100 mdl-color-text--grey-600">
        <div class="mdl-layout__header-row">
            <span class="mdl-layout-title"><c:if test="${not empty nome_pagina }">${nome_pagina}</c:if></span>
            <span style="    position: fixed;right: 15px;top: -1px;"><c:if test="${not empty dados_usuario }">${dados_usuario.email}</c:if></span>
        </div>
        
    </header>
