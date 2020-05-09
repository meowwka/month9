<#--
 объявляем макро-функцию используя которую мы создадим шаблон страницы
 https://freemarker.apache.org/docs/dgui_misc_userdefdir.html
 -->
<#macro renderWith title scripts=[] styles=[]>
    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <title>${title}</title>
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
            <a class="navbar-brand" href="/main">Handmade</a>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/registration">Registration<span class="sr-only">(current)</span></a>
                    </li>
                </ul>
        </nav>
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

    <#list scripts as scriptSource>
        <script src="${scriptSource}"></script>
    </#list>

    </body>
    </html>

</#macro>