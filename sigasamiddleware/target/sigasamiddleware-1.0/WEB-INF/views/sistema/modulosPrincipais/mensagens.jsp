
<c:if test="${not empty mensagem }">
    <div class="alert alert-success" style="border: 0px solid transparent;text-align: center;margin-bottom: 0px;">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong><span class="glyphicon glyphicon-thumbs-up"></span></strong> ${mensagem}
    </div>
</c:if>
<c:if test="${not empty erro }">
    <div class="alert alert-danger" style="border: 0px solid transparent;text-align: center;margin-bottom: 0px;">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong><span class="glyphicon glyphicon-alert"></span> OPS!</strong> ${erro}
    </div>
</c:if>   
