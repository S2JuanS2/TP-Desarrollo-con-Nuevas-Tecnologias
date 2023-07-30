package hungryfiuba

class ArticuloController {

    static scaffold = Articulo

    def mostrarArticulos(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        def listaPedidos = Pedido.list()
        boolean clienteExisteEnPedidos = listaPedidos.any { pedido -> pedido.cliente == cliente }

        if (session.cliente) {
            if(clienteExisteEnPedidos){
                def pedido = Pedido.findByCliente(cliente)
                render(view: "/pedidoEnCurso", model: [pedido: pedido])
            }else{
                def articulos = Articulo.list()
                render(view: '/mostrarArticulos', model: [articulos: articulos])
            }
            
        } else {
            render(view: "/registroFallido")
        }
    }

    def agregarArticuloAdministrador(){

        Articulo articulo = new Articulo(
        nombre: params.nombre,
        precio: params.precio,
        codigo: params.codigo,
        stock: params.stock,
        imagenUrl: params.imagenUrl,
        descripcion: params.descripcion
        )

        articulo.save()
        redirect(controller: "administrador", action: "vistaAdministrador")
    }   

    def aumentarStock(){

        Articulo.withTransaction{
            
            Articulo articulo = Articulo.get(params.articulo)

            articulo.stock++
            articulo.save(flush: true)
        }

        redirect(controller: "administrador", action: "vistaAdministrador")
    }

    def reducirStock(){

        Articulo.withTransaction{

            Articulo articulo = Articulo.get(params.articulo)

            if(articulo.stock > 0){
                articulo.stock--
                articulo.save()
            }
        }
        redirect(controller: "administrador", action: "vistaAdministrador")
    }

}
