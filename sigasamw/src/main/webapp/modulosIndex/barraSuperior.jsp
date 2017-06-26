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
                Versão 1.0
            </div>
        </div>
    </div>
</div>  
