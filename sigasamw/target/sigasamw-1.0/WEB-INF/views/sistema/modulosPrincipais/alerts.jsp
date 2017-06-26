
<div class="">
    <div class="">
        
        <c:if test="${not empty notificacaoOK }">
            <div class="alert alert-success" style="padding: 5px;border: 0px solid transparent;text-align: center;margin-bottom: 0px;">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong></strong> ${notificacaoOK}
            </div>
        </c:if>
        <c:if test="${not empty notificacaoERRO }">
            <div class="alert alert-danger" style="padding: 5px;border: 0px solid transparent;text-align: center;margin-bottom: 0px;">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong> OPS!</strong> ${notificacaoERRO}
            </div>
        </c:if>   
            
    </div>
</div>
