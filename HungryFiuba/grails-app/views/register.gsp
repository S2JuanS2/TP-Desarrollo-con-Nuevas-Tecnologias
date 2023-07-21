<!DOCTYPE html>
<html lang="es">
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página de Inicio - Mi Empresa</title>
    <asset:stylesheet src="inicio.css"/>
    </head>

    <body>

        <div class="container">
            <div class="header">
            <h1>Mi Empresa</h1>
            <img src="ruta_de_la_imagen.jpg" alt="Logo de la empresa">
            </div>
            <div class="description">
            <p>¡Bienvenido a Mi Empresa! Somos una compañía dedicada a...</p>
            </div>
            <div class="login-form">
            <h2>Registro</h2>
            <g:form id="formulario" action="crearCliente" method="POST">

                <label>Nombre:</label>
                <input type="text" name="nombre" id="nombre" pattern="[A-Za-z]+" required /> <br/>

                <label>Apellido:</label>
                <input type="text" name="apellido" pattern="[A-Za-z]+" required /> <br/>

                <label for="identificador">Identificador:</label>
                <select id="identificador" name="idTipo" required>
                <option value="">Seleccionar</option>
                <option value="padron">Padrón</option>
                <option value="dni">DNI</option>
                </select>
                
                <label for="valor">Identificador valor:</label>
                <input type="number" id="valor" name="idValor" required>
                
                <label for="contrasena">Contraseña:</label>
                <input type="password" id="contrasena" name="contrasena" required>
                
                <button type="submit">Registrarse</button>
            </g:form>
            </div>
        </div>

        <!--
        <script>

            document.getElementById("formulario").addEventListener("submit",function(event) {
                event.preventDefault();

                var nombreInput = document.getElementById("nombre").value.trim();

                var nombreFormat = /^[A-Za-z]+$/;

                if(!nombreFormat.test(nombreInput)){
                    alert("Solo letras mayúsculas y minúsculas.");
                    document.getElementById("nombre").focus();
                }
                    
                });

        </script>
        -->

    </body>
</html>