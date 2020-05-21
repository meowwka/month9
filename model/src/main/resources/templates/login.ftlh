<#import "/spring.ftl" as spring>
<#import "partials/main.ftlh" as main />

<@main.renderWith title="Login">
    <h2><@spring.message "sign-in"/></h2>
    <div>
        <#if error!false>
            <p style="color: red"> <@spring.message "invalid-data"/></p>
        </#if>
    </div>

    <div class="d-flex justify-content-center">
        <div class="mx-3">

            <form    id="login-form" action="/login" method="post" >
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                        <input type="text" class="form-control mb-2" name="username" placeholder="<@spring.message "your-email"/>"  value="${(user.email)!''}" autofocus >

                        <input type="password" class="form-control mb-2" name="password" placeholder="<@spring.message "your-password"/>" value="" autofocus>
                <div>
                    <button type="submit" class="btn btn-outline-info btn-block"><@spring.message "sign-in"/></button>
                    <a href="/registration"  class="btn btn-outline-info btn-block"><@spring.message "registration"/></a>
                    <a class="text-info" href="/forgot-password"><@spring.message "forgot-password"/></a>

                </div>
            </form>
        </div>
    </div>
</@main.renderWith>