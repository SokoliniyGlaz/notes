<#import "common.ftl" as c>
<#include "security.ftl">

<@c.page>

<#include "note-edit.ftl"><br><br>
    <h3>List of notes</h3><br>

    <div class="card-columns">
        <#list notes as note>
            <div class="card my-3">
                <div class="m-2">
                    <strong>${note.title}</strong>
                    <span>${note.text}</span>

                </div>
                <div class="card-footer text-muted">
                    <div><strong>Create on: </strong> ${note.getFormattedDate(note.createOn)}</div>
                    <div><strong>Update on: </strong>${note.getFormattedDate(note.updateOn)}</div>
                    <div><strong>Author: </strong>${note.authorName}</div>
                    <#if note.author??>
                    <#if note.author.id == currentUserId>
                        <a class="btn btn-primary" href="${'/main/' + note.id + '/edit'}">
                            Edit
                        </a>
                        <a class="btn btn-primary" href="${'/main/' + note.id + '/delete'}">
                            Delete
                        </a>
                        <a class="btn btn-primary" href="${'/main/' + note.id + '/history'}" >History</a>
                        <a class="btn btn-primary" href="${'/main/' + note.id + '/export'}" >Export</a>
                    </#if>
                    </#if>
                </div>
            </div>
        <#else>
            No message
        </#list>
    </div>
</@c.page>