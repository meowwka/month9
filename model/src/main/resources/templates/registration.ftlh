<#import "partials/main.ftlh" as main />

<@main.renderWith title="Registration">

  <div class="d-flex justify-content-center">
    <div class="mx-3">
      <form id="registration-form" method="post">
        <h3>Регистрация</h3>
        <input type="email" name="email" class="form-control mb-1" placeholder="Email" value="${form.email!''}">
        <input type="text"  name="name" class="form-control mb-1" placeholder="Полное имя" value="${form.name!''}" >
        <input type="text" name="login" class="form-control mb-1" placeholder="Логин"  value="${form.login!''}">
        <input type="password" name="password" class="form-control mb-1" placeholder="Пароль"  value="${form.password!''}">
        <button class="btn btn-outline-primary btn-block">Зарегистрироваться</button>
      </form>
        <#if errors?? >
            <p>Invalid fields!</p>
            <#list errors as fieldError>
                <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
            </#list>
        </#if>
        <#if user?? >
            <p>${user.name} - ${user.login}- ${user.email} already registered!</p>
        </#if>
    </div>

  </div>

</@main.renderWith>
