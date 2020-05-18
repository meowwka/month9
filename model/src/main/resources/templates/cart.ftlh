<#import "partials/main.ftlh" as main />

<@main.renderWith title="Cart">

<div class="container">
    <h1>Ваша корзина</h1>

        <#list cartItems as item>
            <div class="card mb-3 mx-auto " style="max-width: 540px;">
                <div class="row no-gutters">
                    <div class="col-md-4">
                        <img src="images/${item.image}" class="card-img" alt="${item.name}">
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title">${item.name}</h5>
                            <p class="card-text">Price - ${item.price}</p>
                            <p class="card-text"><small class="text-muted">Type - ${item.type.name}</small></p>
                            <p class="card-text"><small class="text-muted">Place - ${item.place.name} </small></p>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
        <form action="/cart/empty" method="post">
            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>

            <button class="btn btn-outline-info">Clear cart</button>
                <#if cartItems??>
                    <a href="/order" class="btn btn-outline-info">To order</a>
                <#else>
                        <p>тут ничего нет!</p>
                    </#if>


            <br><br><br>
        </form>


</div>

</@main.renderWith>