<div>
    <#if hasPrev>
        <a id="loadPrev" class="btn btn-outline-info" href="${prevPageLink!''}">&lt;&lt;&lt;Previous page</a>
    <#else>
        <span class="btn btn-outline-info" id="loadPrev">Previous page</span>
    </#if>

    <#if hasNext>
        <a id="loadNext" class="btn btn-outline-info" href="${nextPageLink!''}" data-default-page-size="${defaultPageSize!5}">Next page&gt;&gt;&gt;</a>
    <#else>
        <span class="btn btn-outline-info">Next page</span>
    </#if>
       <div><a href="/" class="btn btn-outline-info ">Go to the main</a></div>
</div>