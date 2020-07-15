<#import "common.ftl" as c>
<#include "security.ftl">
<@c.page>
<h1>Hello, <#if user??>${name}<#else>User</#if></h1>
<h3><a href="/login">Login page</a></h3>
    <a href="/login?lang=en">En</a>
    <a href="/login?lang=ru">Ru</a>
</@c.page>