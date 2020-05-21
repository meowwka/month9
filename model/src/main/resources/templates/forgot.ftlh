<#import "/spring.ftl" as spring>
<#import "partials/main.ftlh" as main />

<@main.renderWith title="Reser Password">
    <h2><@spring.message "forgot-password-enter"/></h2>
    <div class="d-flex justify-content-center">
    <div class="mx-3">
        <form action="/forgot-password" method="post">

        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <#if errorText?? >
            <div>
                <p style="color: red"><@spring.message "error-email" /></p>
            </div>
        </#if>

        <input type="email"  class="form-control mb-2"  name="email" placeholder="<@spring.message "your-email"/>" >
        <button type="submit" class="btn btn-outline-info btn-block"><@spring.message "send"/> </button>
        <div>
            <#if errors?? >
                <p><@spring.message "input-error"/></p>
                <#list errors as fieldError>
                    <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                </#list>
            </#if>
        </div>
    </form>
    </div>
    </div>
</@main.renderWith>