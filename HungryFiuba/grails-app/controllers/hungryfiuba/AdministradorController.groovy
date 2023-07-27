package hungryfiuba

class AdministradorController {

    static scaffold = Administrador

    def pedidoService

    def vistaInicio(){
        if(session.cliente){
            redirect(controller: "pedido", action: "comenzarPedido")
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
                render(view: "/bienvenida")
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

    def vistaAdministrador(){

        def pedidos = Pedido.list()
        def articulos = Articulo.list()
        render(view: "/administracion", model: [pedidos: pedidos, articulos: articulos])

    }

    def confirmarPedido(){

        def pedido = Pedido.get(params.pedido)
        pedidoService.confirmarPedido(pedido.id)

        def pedidos = Pedido.list()
        def articulos = Articulo.list()
        render(view: "/administracion", model: [pedidos: pedidos, articulos: articulos])
    }

    def cancelarPedido(){

        def pedido = Pedido.get(params.pedido)
        pedidoService.eliminarPedido(pedido.id)

        def pedidos = Pedido.list()
        def articulos = Articulo.list()
        render(view: "/administracion", model: [pedidos: pedidos, articulos: articulos])
    }
}