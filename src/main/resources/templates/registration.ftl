<#import "common.ftl" as c>
<#import "login1.ftl" as l>
<@c.page>
<h3>Add new user</h3>
    <#if message??>
        ${message}
    </#if>
<@l.login "/registration" true />
</@c.page>