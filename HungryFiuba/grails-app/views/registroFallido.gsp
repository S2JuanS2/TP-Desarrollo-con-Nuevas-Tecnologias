<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>HungryFIUBA - inicio</title>
  <asset:stylesheet src="inicio.css"/>
</head>
<body>
  <div class="container">
    <div class="header">
      <h1>HungryFIUBA</h1>
      <asset:image src="logo.png" class="imagen" alt="Logo de la empresa"></asset:image>
    </div>
    <div class="description">
    </div>
    <div class="login-form">
      <h2>Iniciar Sesión</h2>
      <g:form action="autenticar" method="POST">
        <label for="identificador">Identificador:</label>
        <select id="identificador" name="identificador" required>
          <option value="padron">Padrón</option>
          <option value="dni">DNI</option>
        </select>
        <br/>
        <br/>
        <label for="valor">Valor:</label>
        <input type="number" id="valor" name="idValor" maxlength="6" required>
        
        <label for="contrasena">Contraseña:</label>
        <input type="password" id="contrasena" name="contrasena" maxlength="40" required>
        <p style="color: red; position: fixed; transform: translate(20px, -20px)">¡Credenciales Incorrectas!</p>
        
        <button type="submit">Ingresar</button>
      </g:form>
      <p>¿Aún no tienes una cuenta? <a href="/register">Regístrate aquí</a></p>
      <input type="text" id="inputCode" oninput="checkCode()" placeholder="clave admin"/>
      <g:link controller="Administrador" action="vistaAdministrador">
        <button id="myButton" disabled>Administrar</button>
      </g:link>
    </div>
  </div>

  <script>
    function checkCode(){
      const inputCode = document.getElementById('inputCode');
      const myButton = document.getElementById('myButton');

      if(inputCode.value == "0101"){
        myButton.disabled = false;
      }else{
        myButton.disabled = true;
      }

    }
    if(window.location.pathname.includes('/administrador/autenticar')){
    console.log("Ok")
    window.history.replaceState({},'','/inicio');
    }


  </script>
</body>
</html>