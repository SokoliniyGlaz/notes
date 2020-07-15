<#import "common.ftl" as c>
<@c.page>
    <#list changes as change>
        ${change}<br>
    </#list>
     <br>
    <p><a class="btn btn-primary" href="/main">Back</a></p>
</@c.page>