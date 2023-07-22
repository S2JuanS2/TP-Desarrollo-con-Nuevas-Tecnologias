package hungryfiuba

class AdministradorController {

    static scaffold = Administrador

    def vistaInicio(){
        if(session.cliente){
            redirect(controller: "articulo", action: "mostrarArticulos")
        }
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
            if (cliente && cliente.contrasena == contrasena ) {    
                session.cliente = cliente
                redirect(controller: "articulo", action: "mostrarArticulos")
            } else {
                render(view: "/registroFallido")
            }
        } else {
            render(view: "/registroFallido")
            } 
    }
    def logout(){
        session.cliente = null
        render(view:"/inicio")
    }
}