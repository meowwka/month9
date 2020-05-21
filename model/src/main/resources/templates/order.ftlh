<#import "/spring.ftl" as spring>
<#import "partials/main.ftlh" as main />

<@main.renderWith title="Order">
    <h2><@spring.message "fill-data"/></h2>

    <form action="/order" class="needs-validation mx-auto" novalidate style="width: 40rem;" method="post">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <#if user??>
        <div class="form-row">
            <div class="col-md-4 mb-3">
                <label for="validationCustom01"><@spring.message "name"/></label>
                <input type="text" class="form-control" id="validationCustom01" placeholder="<@spring.message "name"/>" value="${user.name}" required>
                <div class="valid-feedback">
                    <@spring.message "looks-good"/>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <label for="validationCustom02"><@spring.message "your-email"/></label>
                <input type="email" name="email" class="form-control" id="validationCustom02" placeholder="<@spring.message "your-email"/>" value="${user.email}" required>
                <div class="valid-feedback">
                    <@spring.message "looks-good"/>
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <label for="validationCustomUsername"><@spring.message "number"/></label>
                <div class="input-group">
                    <input name="number" type="tel" class="form-control" id="validationCustomUsername" placeholder="+996-123-456-789" aria-describedby="inputGroupPrepend"  pattern="+[0-9]{3}-[0-9]{3}-[0-9]{3}-[0-9]{3}" required>
                    <div class="invalid-feedback">
                        <@spring.message "enter-number"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="inputAddress"><@spring.message "address"/></label>
            <input name="address" type="text" class="form-control" id="inputAddress" placeholder="<@spring.message "address"/>" required>
            <div class="invalid-feedback">
                <@spring.message "enter-address"/>
            </div>
        </div>
        <div class="form-row">
            <div class="col-md-6 mb-3">
                <label for="validationCustom03"> <@spring.message "city"/></label>
                <input name="city" type="text" class="form-control" id="validationCustom03" placeholder=" <@spring.message "city"/>" required>
                <div class="invalid-feedback">
                    <@spring.message "enter-city"/>
                </div>
            </div>
            <div class="col-md-3 mb-3">
                <label for="validationCustom04"> <@spring.message "region"/></label>
                <input  name="state" type="text" class="form-control" id="validationCustom04" placeholder=" <@spring.message "region"/>" required>
                <div class="invalid-feedback">
                    <@spring.message "enter-region"/>
                </div>
            </div>
            <div class="col-md-3 mb-3">
                <label for="validationCustom05"><@spring.message "zip"/></label>
                <input name="zip" type="text" class="form-control" id="validationCustom05" placeholder="Zip" required>
                <div class="invalid-feedback">
                    <@spring.message "enter-zip"/>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
                <label class="form-check-label" for="invalidCheck">
                    <@spring.message "agree"/>
                </label>
                <div class="invalid-feedback">
                    <@spring.message "agree-invalid"/>
                </div>
            </div>
        </div>
        <button class="btn btn-primary" type="submit"><@spring.message "toOrder"/></button>
        </#if>
    </form>
    <#if errors?? >
        <p><@spring.message "input-error"/></p>
        <#list errors as fieldError>
            <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
        </#list>
    </#if>

    <script>
        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function() {
            'use strict';
            window.addEventListener('load', function() {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation');
                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function(form) {
                    form.addEventListener('submit', function(event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>

</@main.renderWith>