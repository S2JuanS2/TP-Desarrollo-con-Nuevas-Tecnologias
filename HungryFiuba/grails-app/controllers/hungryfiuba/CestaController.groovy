package hungryfiuba

class CestaController {

    static scaffold = Cesta

    def cestaService

    //muestra la cesta de compras del cliente. Si el cliente está autenticado y no tiene ningún pedido en curso,
    // muestra el contenido de la cesta. Si el cliente tiene un pedido en curso, muestra la información de ese pedido. 
    //Si no hay cliente autenticado, redirige al usuario a la vista de "registro fallido" para indicar que el usuario 
    //no tiene acceso sin autenticación.
    def mostrarCesta() {
        def cliente = Cliente.findById(session.clienteId)
        if (!cliente) {
            render(view: "/registroFallido")
            return
        }
        cliente = Cliente.get(cliente.id)
        
        def listaPedidos = Pedido.list()

        if(cliente.tieneUnPedido(listaPedidos)){
            def pedido = Pedido.findByCliente(cliente)
            render(view: "/pedidoEnCurso", model: [pedido: pedido, cliente: cliente])
        }else{
            def cesta = Cesta.get(cliente.id)
            render(view: '/mostrarCesta', model: [cesta: cesta, cliente: cliente])
        }
    }

    //permite al cliente agregar un artículo a su cesta de compras. Verifica si el cliente y el artículo existen, 
    //si el artículo está disponible en el stock y si el precio total actualizado de la cesta no excede los 5000.
    // Si se cumplen todas las condiciones, el artículo se agrega a la cesta y el usuario es redirigido a la lista 
    //de artículos disponibles. Si alguno de los requisitos no se cumple, el usuario es redirigido a la página de 
    //inicio de administración.
    def agregarArticulo(){
        def cliente = Cliente.findById(session.clienteId)
        def articuloId = params.articulo
        def articulo = Articulo.get(articuloId)
        
        if(!cliente || !articulo){
            throw new ObjetoNoExisteException("El cliente/articulo no existe")
        }
        def cesta = Cesta.get(cliente.id)
        
        cestaService.agregarACesta(articulo, cesta, cliente.id)
        redirect(controller:"articulo", action: "mostrarArticulos")
    }

    //permite al cliente eliminar un artículo específico de su cesta de compras. Verifica si el cliente existe,
    //obtiene el artículo correspondiente al identificador proporcionado y luego llama al servicio cestaService 
    //para eliminar el artículo de la cesta. Redirige al usuario a la vista de la cesta actualizada para que pueda 
    //ver los cambios realizados.
    def eliminarArticulo(){
        def cliente = Cliente.findById(session.clienteId)
        def articuloId = params.articulo
        def articulo = Articulo.get(articuloId)
        if(!cliente){
            throw new ObjetoNoExisteException("El cliente no existe")
        }
        if(!articulo){
            throw new ObjetoNoExisteException("El articulo no existe")
        }

        cestaService.eliminarArticuloACesta(articulo.id, cliente.id)

        redirect(controller: "cesta", action: "mostrarCesta")
    }
}
