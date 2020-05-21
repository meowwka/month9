<#import "/spring.ftl" as spring>
<#import "partials/main.ftlh" as main />

<@main.renderWith title="Success">
    <h2><@spring.message "enter-token"/></h2>
    <#if errorText?? >
        <div>
            <p>${errorText!'-- no message --'}</p>
        </div>
    </#if>
    <div class="d-flex justify-content-center">
    <div class="mx-3">
        <form action="/reset-password" method="post">

        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>

        <div>
                <#if token?? >
                    <div>
                        <p>Token-${token.token!"-- no message --"}</p>
                    </div>
                </#if>
                <input type="text"  class="form-control mb-2" name="token" placeholder="Token" >
        </div>
        <div>
                <input type="password"  class="form-control mb-2" name="newPassword" placeholder="<@spring.message "new-password"/>" >
        </div>
        <div>
            <button type="submit" class="btn btn-outline-info btn-block"><@spring.message "send"/></button>
        </div>
    </form>
    </div>
    </div>

</@main.renderWith>