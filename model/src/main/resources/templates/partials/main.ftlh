<#--
 объявляем макро-функцию используя которую мы создадим шаблон страницы
 https://freemarker.apache.org/docs/dgui_misc_userdefdir.html
 -->
<#import "/spring.ftl" as spring>
<#macro renderWith title scripts=[] styles=[] dir="ltr">
    <!doctype html>
    <html lang="${.locale}" dir="${dir}">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <title> ${title} <@spring.message "title" /></title>
        <link rel="stylesheet" href="/css/styles.css">
        <#list styles as styleSource>
            <link rel="stylesheet" href="${styleSource}">
        </#list>
    </head>
    <body>

    <#--
    например если бы у нашего сайта было какое-то верхнее меню,
    то мы могли бы его тут указать
    и оно появится сразу на всех страницах которые используют
    этот шаблон
    -->

    <header class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="/"> <@spring.message "main" /></a>
                <ul id="ulist" class="navbar-nav">
                    <#if user??>
                        <li class="nav-item">
                            <a class="nav-link" href="/successful"> <@spring.message "profile" /></a>
                        </li>
                    <#else>
                        <li class="nav-item">
                            <a class="nav-link" href="/login"> <@spring.message 'login' /></a>
                        </li>
                    </#if>
                    <li class="nav-item">
                        <a class="nav-link" href="/cart"> <@spring.message "cart" /></a>
                    </li>
                </ul>
        </nav>
            <div>
                <form action="" method="get">
                    <div  class="input-group">
                        <label for="lang_select" style="margin-top: 8px; margin-right: 15px;margin-left: -30px;">
                            <@spring.message "label.language" />: </label>
                        <select class="custom-select" name="lang" id="lang_select">
                            <option value="">Default</option>
                            <option value="" disabled>----------</option>
                            <option value="en">English</option>
                            <option value="ru">Русский</option>
                        </select>
                        <div class="input-group-append">
                            <button class="btn btn-outline-info" type="submit">
                            <@spring.message "button.language-change.text" />
                            </button>
                        </div>
                    </div>
                    <p><@spring.message "current-locale" /> : ${.locale}</p>
                </form>
            </div>
        </div>
    </header>



    <div class="container ">
        <#--
         элемент nested указывает что в этом место
         будет подставлено некоторое содержимое,
         которое передаётся как тело макро-функции при её вызове
        -->
        <#nested>

    </div>
    <script src="js/main.js"></script>

    <#list scripts as scriptSource>
        <script src="${scriptSource}"></script>
    </#list>

    </body>
    </html>

</#macro>