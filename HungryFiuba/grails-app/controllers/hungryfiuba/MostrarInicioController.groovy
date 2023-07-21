package HungryFiuba

class MostrarInicioController {

    def autenticar() {
        def identificadorValor = params.identificadorValor
        def contrasena = params.contrasena

        def cliente = Cliente.findByIdentificadorValor(identificadorValor)

        if (cliente && cliente.contrasena == contrasena) {
            flash.message = "¡Bienvenido, ${cliente.nombre}!"
            //redirect(action: 'index')
        } else {
            flash.message = "Credenciales inválidas. Inténtalo nuevamente."
            //redirect(action: 'index')
        }
    }
}
