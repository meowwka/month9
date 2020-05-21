<#import "/spring.ftl" as spring>
<#import "partials/main.ftlh" as main />

<@main.renderWith title="Seccess!">

<#if user??>
        <h2><@spring.message "profile"/></h2>
    <div class="card border-dark mb-3" style="max-width: 60rem;margin-left: 5rem;">
        <div class="card-header">
<#--            ${(user.name)!'-- missing --'}-->
        </div>
        <div class="card-body text-dark">
            <h5 class="card-title"><@spring.message "name"/>: ${user.name!'--name missing--'}</h5>
            <h5 class="card-title"><@spring.message "login"/>: ${user.login!'--login missing--'}</h5>
            <h5 class="card-title"><@spring.message "your-email"/>: ${user.email!'--email missing--'}</h5>
            <p class="card-text"><@spring.message "test"/></p>
            <form class="flex box-128" action="/logout" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <button class="btn btn-outline-info "> <@spring.message "logout"/> </button>
            </form>

        </div>
    </div>
    <#else>
        <h1><@spring.message "user-not-found"/></h1>
    </#if>
</@main.renderWith>