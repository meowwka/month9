<#import "/spring.ftl" as spring>
<#import "partials/main.ftlh" as main />

<@main.renderWith title="Handmade">
    <form action="/" class="mb-24" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
                <div class="input-group mb-3 mt-3" >
                    <input name="name" placeholder="<@spring.message "searchByName" />" type="text" class="form-control" style="margin-left: 280px;" aria-describedby="button-addon2">
                    <button type="submit" class="btn btn-outline-info" id="button-addon2" style=" margin-right: 210px;">
                        <@spring.message "search" />
                    </button>
                </div>
    </form>

<#list products as pr>
    <div class="card mb-3 mx-auto " style="max-width: 540px;">
        <div class="row no-gutters">
            <div class="col-md-4">
                <img src="images/${pr.image}" class="card-img" alt="${pr.name}">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title">${pr.name}</h5>
                    <p class="card-text"><@spring.message "price" /> - ${pr.price}</p>
                    <p class="card-text"><small class="text-muted"><@spring.message "Type" /> - ${pr.type.name}</small></p>
                    <p class="card-text"><small class="text-muted"><@spring.message "place" /> - ${pr.place.name}</small></p>
                    <form id="cart-form" action="/cart/add" method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                        </#if>
                        <input name="value" value="${pr.id}" hidden>
                        <button id="cart-add" type="submit" class="btn btn-outline-info"><@spring.message "addToCart" /></button>
                        <select name="amount">
                                <option value="one">1</option>
                                <option value="two">2</option>
                                <option value="three">3</option>
                                <option value="four">4</option>
                                <option value="five">5</option>
                            </select>
                    </form>
                </div>
            </div>
        </div>
    </div>
</#list>

    <#include "partials/pagination.ftlh">

    <script>
        'use strict';

        async function addToCart(e) {
            e.preventDefault();
            e.stopPropagation();

            const form = document.getElementById("cart-form");
            const options = {
                method : 'post',
                body: new FormData(form)
            };

            await fetch("/cart", options);
            alert("обновите страницу для просмотра данных из сессии, или перейдите в корзину");
        }

        const updateElement = document.getElementById("cart-add");
        updateElement.addEventListener('click', addToCart);
    </script>
</@main.renderWith>
