<#-- импортируем основной шаблон-контейнер который содержит в себе разметку для основной структуры страницы -->
<#import "partials/main.ftlh" as main />

<#-- обрабатываем его, и передаём ему список скриптов, которые необходимо подключить-->
<@main.renderWith title="Welcome">

    <div class="flex flex-justify-content-center">
        <a class="btn btn-outline-info" href="/registration"> Регистрация </a>
        <a class="btn btn-outline-info" href="/login"> Войти </a>
        <a class="btn btn-outline-info" href="/successful"> Профиль </a>

        <form class="flex box-128" action="/logout" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>
            <button class="btn btn-outline-info">Выйти </button>
        </form>

    </div>

</@main.renderWith>