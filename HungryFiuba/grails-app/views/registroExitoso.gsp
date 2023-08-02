<!DOCTYPE html>
<html>
<head>
    <title>Registro exitoso</title>
    <asset:stylesheet src="registroExitoso.css"/>
</head>
<body>
    <div class="conteiner">
        <h1>¡Registrado con éxito!</h1>
        <p id="redirect">Será redireccionado a la página de inicio en 5seg.</p>
    </div>

    <script>
    setTimeout(function(){
        window.location.href='/inicio';
    }, 5000);
    setTimeout(function(){
        const redirect = document.getElementById('redirect')
        redirect.textContent = 'Será redireccionado a la página de inicio en 4seg.';
    }, 1000);
        setTimeout(function(){
        const redirect = document.getElementById('redirect')
        redirect.textContent = 'Será redireccionado a la página de inicio en 3seg.';
    }, 2000);
        setTimeout(function(){
        const redirect = document.getElementById('redirect')
        redirect.textContent = 'Será redireccionado a la página de inicio en 2seg.';
    }, 3000);
        setTimeout(function(){
        const redirect = document.getElementById('redirect')
        redirect.textContent = 'Redireccionando...';
    }, 4000);
    </script>
</body>
</html>