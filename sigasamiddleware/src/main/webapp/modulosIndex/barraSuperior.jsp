<!-- barra superior da pagina inicial recuperar conta -->
<style type="text/css">
    @keyframes mexe{

        0%{
            transform:rotateZ(0);
        }
        25%{
            transform:rotateZ(1deg);
            //-webkit-transform: scale(1.3);
            //-ms-transform: scale(1.3);
            //transform: scale(1.3);
        }
        50%{
            transform:rotateZ(0);
        }
        75%{
            transform:rotateZ(-1deg);
        }
        100%{
            transform:rotateZ(0);
        }
    }
    .mexe{
        transition: .5s;
    }
    .mexe:hover{
        animation-name: mexe;
        animation-delay: .2s;
        animation-duration: .5s;
        animation-timing-function: linear;
        animation-iteration-count: infinite;

    }
    .estilo{
        background-image: linear-gradient(to right, #0048da, #6da0fd, #6da0fd, #00bcda, #0048da);
        color: white;
    }
    .imagemBarra{
        width: 10%;
        margin-top: -23px;
    }
</style>
<div class="estilo">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <h3 style="font-size: 60px;">
                    <img src="img/favicon2.png" alt="" class="imagemBarra"/>
                    SIGASA</h3>
            </div>
            <div class="col-md-6" style="text-align: center">
                <h6 style="">Esqueceu seus dados de acesso? 
                    <a data-toggle="modal" data-target="#myModal" style="cursor:pointer;color:white">
                        <div class="mexe"><span class="glyphicon glyphicon-hand-right"></span> CLIQUE AQUI</div>
                    </a>
                </h6>
            </div>
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

