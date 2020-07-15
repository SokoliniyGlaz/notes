
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Note editor
    </a>
    <div class="collapse <#if note??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control"
                           value="<#if note??>${note.title}</#if>" name="title" placeholder="Insert title">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control"
                           value="<#if note??>${note.text}</#if>" name="text" placeholder="Insert text" />
                </div>

                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="hidden" name="id" value="<#if note??>${note.id}</#if>" />
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Save note</button>
                </div>
            </form>
        </div>
    </div><br><br>

