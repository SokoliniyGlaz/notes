<#import "common.ftl" as c>
<#include "security.ftl">
<@c.page>
<h1>Hello, <#if user??>${name}<#else>guest</#if></h1>
<h3><a href="/login">Login page</a></h3>
</@c.page>