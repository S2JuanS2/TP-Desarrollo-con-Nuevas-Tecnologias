<!DOCTYPE html>
<html>
<head>
    <title>Pagar deuda</title>
    <asset:stylesheet src="registroExitoso.css"/>
</head>
<body>
    <h1>Pago de deuda</h1>
    <p>Usted tiene una deuda pendiente de: ${cliente.deuda}<br></p>
    <p>Ingrese su número de comprobante de pago:<br></p>
    <form>
        
        <input type="number" id="numComprobante" name="numComprobante" required>
        <br>
        
    </form>
    <g:link controller="Cliente" action="registrarDeuda">
        <button>Enviar comprobante</button>
    </g:link>
    <a href="/inicio">Volver a Inicio</a>
    <footer>
        <p>HungryFIUBA</p>
    </footer>
</body>
</html>