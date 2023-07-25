package hungryfiuba

class PedidoController {

    static scaffold = Pedido
    def comenzarPedido(){
        Cesta cesta = new Cesta()
        cesta.save(failOnError: true)
        Pedido pedido = new Pedido(session.cliente, cesta)
        pedido.save(failOnError: true)

        render(view: "/bienvenida") //llamar a una nueva vista
    }
    def pedidoCreado(){
        render(view: "/pedidoCreado")
    }
    
    def mostrarCesta() {
        if (session.cliente) {
            // Obtener la cesta del cliente - No es necesario ya esta en la sesión
            // Pasar el cliente y la cesta a la vista mostrarCesta.gsp
            render(view: "/mostrarCesta")
        } else {
            // Si no hay cliente autenticado, redirigir a la página de inicio de sesión
            render(view: "/registroFallido")
        }
    }
    
    def agregarArticulo(){

        Cliente cliente = session.cliente

        Articulo articulo = Articulo.findByNombre(params.nombre)

        if(cliente && articulo){
            pedido.cesta.agregarArticulo(articulo)
        }
        cliente.save(failOnError: true)

        render(view: "/mostrarArticulos")
    }

    
}
