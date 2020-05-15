<#import "partials/main.ftlh" as main />

<@main.renderWith title="Registration">

  <div class="d-flex justify-content-center">
    <div class="mx-3">
      <form action="/registration" id="registration-form" method="post">
        <h3>Registration</h3>
          <#if _csrf??>
              <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
          </#if>
        <input type="email" name="email" class="form-control mb-1" placeholder="Email" value="${form.email!''}">
        <input type="text"  name="name" class="form-control mb-1" placeholder="Name" value="${form.name!''}" >
        <input type="text" name="login" class="form-control mb-1" placeholder="Login"  value="${form.login!''}">
        <input type="password" name="password" class="form-control mb-1" placeholder="Password"  value="">
        <button type="submit" class="btn btn-outline-info btn-block">Register</button>
          <a type="submit" href="/login" class="btn btn-outline-info btn-block">Sign In</a>

          <div>
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
      </form>

    </div>

  </div>

</@main.renderWith>
