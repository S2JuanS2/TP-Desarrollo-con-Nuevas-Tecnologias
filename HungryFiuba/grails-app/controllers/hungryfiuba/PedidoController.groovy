package hungryfiuba

class PedidoController {

    static scaffold = Pedido

    def mostrarCesta() {
        def pedido = p
        if (pedido) {
            render(view: "mostrarCesta", model: [pedido: pedido])
        } else {
            // Manejar caso si no se encuentra el pedido o si el cliente no tiene cesta
            // Por ejemplo, redirigir a una página de error o inicio
        }
    }
}
