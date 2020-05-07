<#import "partials/main.ftlh" as main />

<@main.renderWith title="Handmade">

    <a href="/registration" >Registration.</a>
    <h1>Содержимое страницы</h1>
    <form action="/" class="mb-24" method="post">
        <div  class="z-input z-input_size_normal">
            <div class="z-input__field-wrap">
                <label class="z-input__label">
                    Поиск по названию
                </label>
                <div class="z-input__group">
                    <input name="name" placeholder="" type="text" class="z-input__field">
                    <button type="submit" class="btn btn-light" style="">
                        Search
                    </button>
                </div>
            </div>
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
<#--                    <p class="card-text"><small class="text-muted">Place = ${pr.place}</small></p>-->
                </div>
            </div>
        </div>
    </div>

</#list>
    <#include "partials/pagination.ftlh">
</@main.renderWith>
