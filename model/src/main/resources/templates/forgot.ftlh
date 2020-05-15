<#import "partials/main.ftlh" as main />

<@main.renderWith title="Reser Password">
    <h2>Please enter email to reset password</h2>
    <div class="d-flex justify-content-center">
    <div class="mx-3">
        <form action="/forgot-password" method="post">

        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <#if errorText?? >
            <div>
                <p style="color: red">${errorText!'-- no message --'}</p>
            </div>
        </#if>

        <input type="email"  class="form-control mb-2"  name="email" placeholder="Your email" >
        <button type="submit" class="btn btn-outline-info btn-block">Отправить</button>
        <div>
            <#if errors?? >
                <p>Входные данные с ошибками!</p>
                <#list errors as fieldError>
                    <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                </#list>
            </#if>
        </div>
    </form>
    </div>
    </div>
</@main.renderWith>