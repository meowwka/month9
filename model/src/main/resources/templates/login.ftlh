<#import "partials/main.ftlh" as main />

<@main.renderWith title="Login">
    <h2>Please Sign In</h2>
    <div>
        <#if error!false>
            <p style="color: red"> Invalid data entry</p>
        </#if>
    </div>

    <form  class="form-inline"  id="login-form" action="/login" method="post" style=" margin-left: 15rem;">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <div class="form-group mb-2">
                <label for="staticEmail2" class="sr-only">Email</label>
                <input type="text" class="form-control" name="username" placeholder="Your email"  value="${(user.email)!''}" autofocus >
        </div>
        <div class="form-group mx-sm-3 mb-2">
            <label for="inputPassword2" class="sr-only">Password</label>
                <input type="password" class="form-control" name="password" placeholder="password" value="" autofocus>
        </div>
        <div>
            <button type="submit" class="btn btn-outline-info">Sign In</button>
            <a href="/"  class="btn btn-outline-info">Registration</a>
        </div>
    </form>
</@main.renderWith>