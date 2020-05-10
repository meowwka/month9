<#import "partials/main.ftlh" as main />

<@main.renderWith title="Handmade">
    <form action="/main" class="mb-24" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
                <div class="input-group mb-3 mt-3" >
                    <input name="name" placeholder="Search by name" type="text" class="form-control" style="margin-left: 280px;" aria-describedby="button-addon2">
                    <button type="submit" class="btn btn-outline-info" id="button-addon2" style=" margin-right: 210px;">
                        Search
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
                    <p class="card-text">Price - ${pr.price}</p>
                    <p class="card-text"><small class="text-muted">Type = ${pr.type.name}</small></p>
<#--                    <p class="card-text"><small class="text-muted">Place = ${pr.place.name}</small></p>-->
                </div>
            </div>
        </div>
    </div>
</#list>
    <#include "partials/pagination.ftlh">
</@main.renderWith>
