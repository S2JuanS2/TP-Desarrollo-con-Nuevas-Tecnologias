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
      <img src="" alt="Logo de la empresa">
    </div>
    <div class="description">
      <p>¡Bienvenido!</p>
    </div>
    <div class="login-form">
      <h2>Iniciar Sesión</h2>
      <g:form action="autenticar" method="POST">
        <label for="identificador">Identificador:</label>
        <select id="identificador" name="identificador" required>
          <option value="">Seleccionar</option>
          <option value="padron">Padrón</option>
          <option value="dni">DNI</option>
        </select>
        <br/>
        <br/>
        <label for="valor">Valor:</label>
        <input type="number" id="valor" name="idValor" required>
        
        <label for="contrasena">Contraseña:</label>
        <input type="password" id="contrasena" name="contrasena" required>
        
        <button type="submit">Ingresar</button>
      </g:form>
      <p>¿Aún no tienes una cuenta? <a href="/register">Regístrate aquí</a></p>
    </div>
  </div>
</body>
</html>
