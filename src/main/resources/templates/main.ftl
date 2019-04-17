<#assign base=request.contextPath />


<h3>

    尊敬的<#if Session.user.nikeName ?exists>
           ${Session.user.nikeName }

    </#if>欢迎再次回来!


</h3>
