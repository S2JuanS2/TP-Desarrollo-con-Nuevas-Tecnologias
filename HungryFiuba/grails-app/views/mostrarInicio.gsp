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
      <h2>Iniciar Sesión</h2>
      <form action="/articulosDisponibles" method="post">
        <label for="identificador">Identificador:</label>
        <select id="identificador" name="identificador" required>
          <option value="">Seleccionar</option>
          <option value="padron">Padrón</option>
          <option value="dni">DNI</option>
        </select>
        
        <label for="valor">Valor:</label>
        <input type="number" id="valor" name="valor" required>
        
        <label for="contrasena">Contraseña:</label>
        <input type="password" id="contrasena" name="contrasena" required>
        
        <button type="submit">Ingresar</button>
      </form>
      <p>¿Aún no tienes una cuenta? <a href="/register">Regístrate aquí</a></p>
    </div>
  </div>
</body>
</html>
