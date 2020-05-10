<#import "partials/main.ftlh" as main />

<@main.renderWith title="Login">
    <h2>Please Sign In</h2>
    <form action="/login" method="post">

        <div>
            <#if error!false>
                <p style="color: red"> Invalid data entry</p>
            </#if>
        </div>

        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>

        <div>
            <label class="flex flex-v-center ">
                <span class="label-input-text">Email:</span>
                <input type="text" name="username" placeholder="Your email"  value="${(user.email)!''}"  >
            </label>
        </div>
        <div>
            <label class="flex flex-v-center label-input-text">
                <span class="label-input-text">Password:</span>
                <input type="password" name="password" placeholder="password" value="" autofocus>
            </label>
        </div>
        <div>
            <button type="submit">Войти!</button>
        </div>

    </form>
</@main.renderWith>