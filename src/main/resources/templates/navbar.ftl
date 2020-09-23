<#include "security.ftl">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>



    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/main">Note</a>
                </li>
                <li class="nav-item">
                    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExamples" role="button" aria-expanded="false" aria-controls="collapseExample">
                        Import
                    </a>
                    <div class="collapse" id="collapseExamples">
                      <form action="/import" method="post" enctype="multipart/form-data">
                          <input type="hidden" name="_csrf" value="${_csrf.token}" />
                          <input type="file" name="file"/>
                          <button type="submit" class="btn btn-primary">Send</button>
                      </form>
                    </div>
                </li>
            </#if>
        </ul>

        <div class="navbar-text mr-3">${name}</div>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="btn btn-primary" type="submit">Sign Out</button>
        </form>
    </div>
</nav>