<#import "partials/main.ftlh" as main />

<@main.renderWith title="Seccess!">

<#if user??>
        <h2>Your Profile</h2>
    <div class="card border-dark mb-3" style="max-width: 60rem;margin-left: 5rem;">
        <div class="card-header">
<#--            ${(user.name)!'-- missing --'}-->
        </div>
        <div class="card-body text-dark">
            <h5 class="card-title">Name: ${user.name!'--name missing--'}</h5>
            <h5 class="card-title">Login: ${user.login!'--login missing--'}</h5>
            <h5 class="card-title">Email: ${user.email!'--email missing--'}</h5>
            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            <form class="flex box-128" action="/logout" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <button class="btn btn-outline-info "> Выйти </button>
            </form>

        </div>
    </div>
    <#else>
        <h1>Пользователь не найден!</h1>
    </#if>
</@main.renderWith>