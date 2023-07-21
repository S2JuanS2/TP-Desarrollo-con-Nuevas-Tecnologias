<html>
    <head>
        <title>Login</title>
        <asset:stylesheet src="register.css"/>
    </head>
    <body>
<div class="contenedor">
    <g:form action="crearCliente" method="POST">
        <label>Nombre:</label>
        <input type="text" name="nombre" pattern="[A-Za-z]+" required /> <br/>

        <label>Apellido:</label>
        <input type="text" name="apellido" pattern="[A-Za-z]+" required /> <br/>

        <label>Identificador tipo:</label>
        <select name="idTipo" required>
            <option value="padron">Padrón</option>
            <option value="dni">DNI</option>
        </select> <br/>

        <label>Identificador valor:</label>
        <input type="number" name="idValor" required /> <br/>

        <button type="submit">Register</button>
    </g:form>
</div>
        
    </body>


</html>