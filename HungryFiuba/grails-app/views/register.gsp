<html>
    <head>
        <title>Login</title>
        <asset:stylesheet src="register.css"/>
    </head>
    <body>

        <div class="contenedor">
            <g:form action="crearCliente" method="POST">
            <label>nombre:</label> 
                <input type="text" name="nombre" /> <br/>
            <label>apellido:</label> 
                <input type="text" name="apellido" /> <br/>
            <label>identificador tipo:</label> 
                <input type="text" name="idTipo" /> <br/>
            <label>identificador valor:</label> 
                <input type="text" name="idValor" /> <br/>


            <button type="submit">Register</button>
            </g:form>
        </div>
        
    </body>


</html>