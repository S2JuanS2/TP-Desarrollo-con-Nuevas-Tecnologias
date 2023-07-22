package hungryfiuba

class AdministradorController {

    static scaffold = Administrador

    def vistaInicio(){
        render(view: "/inicio")
    }

    def mostrarArticulos() {
        render(view: 'mostrarArticulos')
    }
    
    def autenticar() {
        def identificadorTipo = params.idTipo
        def identificadorValor = params.idValor
        def contrasena = params.contrasena
    
        def cliente = Cliente.findByIdentificadorValor(identificadorValor)

        if(cliente != null){
            if (cliente && cliente.contrasena == contrasena /*&& cliente.identificadorTipo == identificadorTipo*/) {                
                
                redirect(controller: "articulo", action: "mostrarArticulos")
                
                //flash.message = "¡Bienvenido, ${cliente.nombre}!"   //aca tiene que redirigir a mostrar la lista de articulos.
                //redirect(action: 'index')
            } else {
                //flash.message = "Credenciales inválidas. ${cliente.nombre} Inténtalo nuevamente."
                //redirect(action: 'index')
            }
        } else {
                //flash.message = "Credenciales inválidas. Inténtalo nuevamente."
                //redirect(action: 'index')
            } 
    }
}
