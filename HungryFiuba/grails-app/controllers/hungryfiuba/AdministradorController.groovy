package hungryfiuba

class AdministradorController {

    static scaffold = Administrador

    def vistaInicio(){
        render(view: "/inicio")
    }
    
    def autenticar() {
        def identificadorTipo = params.idTipo
        def identificadorValor = params.idValor
        def contrasena = params.contrasena
    
        def cliente = Cliente.findByIdentificadorValor(identificadorValor)

        if(cliente != null){
            if (cliente && cliente.contrasena == contrasena /*&& cliente.identificadorTipo == identificadorTipo*/) {                
                
                redirect(controller: "articulo", action: "mostrarArticulos")
                
               
            } else {
               
                render(view: "/registroFallido")
            }
        } else {
            render(view: "/registroFallido")
            } 
    }
}
