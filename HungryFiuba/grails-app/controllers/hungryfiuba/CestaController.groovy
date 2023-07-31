package hungryfiuba

class CestaController {

    static scaffold = Cesta

    def cestaService

    def mostrarCesta() {

        if (session.cliente) {
            //Si hay autenticación
            
            Cliente cliente = session.cliente
            
            def cesta = Cesta.get(cliente.id)
            cliente = Cliente.get(cliente.id)
            def listaPedidos = Pedido.list()
            boolean clienteExisteEnPedidos = listaPedidos.any { pedido -> pedido.cliente == cliente }
            if(!clienteExisteEnPedidos){
                render(view: '/mostrarCesta', model: [cesta: cesta])
            }else{
                def pedido = Pedido.findByCliente(cliente)
                render(view: "/pedidoEnCurso", model: [pedido: pedido])
            }
        } else {
            // Si no hay cliente autenticado, redirigir a la página de inicio de sesión
            render(view: "/registroFallido")
        }
    }

    def agregarArticulo(){

        Cliente cliente = session.cliente
        Cliente clienteActual = Cliente.get(cliente.id)

        def articuloId = params.articulo
        def articulo = Articulo.get(articuloId)

        def cesta = Cesta.get(cliente.id)
        def precioArticulo= cesta.montoTotal + articulo.precio
        if(cliente && articulo ){
            if(articulo.stock > 0 && precioArticulo <= 5000  && clienteActual.estado == EstadoCuenta.NO_BLOQUEADA){
                cestaService.agregarArticuloACesta(articulo.id, cliente.id)
            }
                redirect(controller:"articulo", action: "mostrarArticulos")
            
        }else{
            redirect(controller:"administrador", action: "vistaInicio")
        }
    }

    def eliminarArticulo(){

        Cliente cliente = session.cliente
        def articuloId = params.articulo //es un string
        def articulo = Articulo.get(articuloId)

        cestaService.eliminarArticuloACesta(articulo.id, cliente.id)

        redirect(controller: "cesta", action: "mostrarCesta")
    }

}
