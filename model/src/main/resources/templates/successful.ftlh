<#import "partials/main.ftlh" as main />

<@main.renderWith title="Seccess!">

<#if user??>
        <h2>You have successfully registered! Welcome!</h2>
        <p>${(user.name)!'-- missing --'} -${user.login!'--login missing--'} - ${(user.email)!'-- email missing --'}</p>
<#--        <a href="/">Go to the main</a>-->
    <#else>
        <h1>Пользователь не найден!</h1>
    </#if>
</@main.renderWith>