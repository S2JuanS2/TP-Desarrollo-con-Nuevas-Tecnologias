package hungryfiuba

class ArticuloController {

    static scaffold = Articulo
    def articuloService
    //permite al administrador agregar un nuevo artículo a la base de datos. Redirige al administrador de
    // vuelta a la vista de administración para que pueda seguir gestionando otros aspectos de la aplicación.
    def crearArticulo(){

        Articulo articulo = new Articulo(
            nombre: params.nombre,
            precio: params.precio,
            codigo: params.codigo,
            stock: params.stock,
            imagenUrl: params.imagenUrl,
            descripcion: params.descripcion
        ).save()

        redirect(controller: "administrador", action: "vistaAdministrador")
    }   

    //muestra la lista de artículos disponibles. Si un cliente está autenticado en la sesión y tiene un 
    //pedido en curso, muestra la información del pedido actual. Si no hay ningún pedido en curso para el 
    //cliente autenticado, muestra la lista de artículos disponibles para su compra. Si no hay ningún cliente
    //autenticado en la sesión, muestra una vista de "registro fallido" para indicar que el usuario no tiene acceso sin autenticación.
    def mostrarArticulos(){
        def cliente = Cliente.findById(session.clienteId)
        if(!cliente){
            render(view: "/registroFallido")
            return
        }
        cliente = Cliente.get(cliente.id)
        
        def listaPedidos = Pedido.list()
        
        if(cliente.tieneCuentaBloqueada()){
            render(view: "/cuentaBloqueada", model:[cliente: cliente])
            return
        }
        if(cliente.tieneUnPedido(listaPedidos)){
            def pedido = Pedido.findByCliente(cliente)
            render(view: "/pedidoEnCurso", model: [pedido: pedido, cliente: cliente])
        }else{
            Cesta cesta = cliente.cesta
            def articulos = Articulo.list()
            render(view: '/mostrarArticulos', model: [articulos: articulos, cesta: cesta])
        } 
    }

    //permite al administrador aumentar el stock de un artículo específico en la base de datos. 
    //Redirige al administrador de vuelta a la vista de administración para que pueda seguir gestionando 
    //otros aspectos de la aplicación. La función garantiza que la operación de aumento del stock se realice 
    //de manera segura dentro de una transacción para mantener la integridad de los datos en la base de datos.
    def aumentarStock(){
        Articulo articulo = Articulo.get(params.articulo)
        if(!articulo){
            throw new ObjetoNoExisteException("El articulo no existe")
        }
        articuloService.aumentarStock(articulo)
        redirect(controller: "administrador", action: "vistaAdministrador")
    }

    //permite al administrador reducir el stock de un artículo específico en la base de datos. 
    //Antes de reducir el stock, verifica que el stock actual sea mayor que 0 para evitar valores negativos. 
    //Redirige al administrador de vuelta a la vista de administración para que pueda seguir gestionando 
    //otros aspectos de la aplicación.
    def reducirStock(){
            Articulo articulo = Articulo.get(params.articulo)
            if(!articulo){
                throw new ObjetoNoExisteException("El articulo no existe")
            }
        articuloService.reducirStock(articulo)
        redirect(controller: "administrador", action: "vistaAdministrador")
    }

}
