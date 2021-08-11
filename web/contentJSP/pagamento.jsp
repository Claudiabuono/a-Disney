<%--
  Created by IntelliJ IDEA.
  User: anna
  Date: 07/08/21
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<!--CREDIT CART PAYMENT-->
<div class="panel panel-info">
    <div class="panel-heading">
							<span><i class='fa fa-credit-card'
                                     style='font-size: 24px'></i> Pagamento Sicuro
							</span>
    </div>
    <div class="panel-body">
        <div class="form-group">
            <div class="col-md-12">
                <strong>Tipo di Carta</strong>
            </div>
            <div class="col-md-12">
                <select id="CreditCardType" name="tipo_carta"
                        class="form-control">
                    <option value="5">Visa</option>
                    <option value="6">MasterCard</option>
                    <option value="7">American Express</option>
                    <option value="8">Discover</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-12">
                <strong>Numero della Carta di Credito</strong>
            </div>
            <div class="col-md-12">
                <input type="text" class="form-control" name="numero_carta"
                       value="" minlength="16" maxlength="16"  onkeypress="return soloNumeri(event);"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-12">
                <strong>CVV</strong>
            </div>
            <div class="col-md-12">
                <input type="text" class="form-control" name="ccv_carta"
                       value="" minlength="3" maxlength="3" onkeypress="return soloNumeri(event);"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-12">
                <strong>Data di Scadenza</strong>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <select class="form-control" name="mese_scadenza_carta" required>
                    <option value="">Mese</option>
                    <option value="01">01</option>
                    <option value="02">02</option>
                    <option value="03">03</option>
                    <option value="04">04</option>
                    <option value="05">05</option>
                    <option value="06">06</option>
                    <option value="07">07</option>
                    <option value="08">08</option>
                    <option value="09">09</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                </select>
            </div>

            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <select class="form-control" name="mese_scadenza_carta" required>
                    <option value="">Anno</option>
                    <option value="2021">2021</option>
                    <option value="2022">2022</option>
                    <option value="2023">2023</option>
                    <option value="2024">2024</option>
                    <option value="2025">2025</option>
                    <option value="2026">2026</option>
                    <option value="2027">2027</option>
                    <option value="2028">2028</option>
                </select>

            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function soloNumeri(evt) {
        var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
        return true;
    }
</script>
<!--CREDIT CART PAYMENT END-->

