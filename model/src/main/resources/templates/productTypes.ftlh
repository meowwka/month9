<#import "partials/main.ftlh" as main />

<@main.renderWith title="Product Types">
<ul>
    <#list productTypes as b>
        <li>
            <p>${b.id} - ${b.name} - ${b.icon}</p>
        </li>
    </#list>
</ul>
</@main.renderWith>