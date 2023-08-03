package hungryfiuba

class ArticuloController {

    static scaffold = Articulo

    //muestra la lista de artículos disponibles. Si un cliente está autenticado en la sesión y tiene un 
    //pedido en curso, muestra la información del pedido actual. Si no hay ningún pedido en curso para el 
    //cliente autenticado, muestra la lista de artículos disponibles para su compra. Si no hay ningún cliente
    //autenticado en la sesión, muestra una vista de "registro fallido" para indicar que el usuario no tiene acceso sin autenticación.
    def mostrarArticulos(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        def listaPedidos = Pedido.list()

        if (session.cliente) {
            if(cliente.clienteExisteEnPedidos(listaPedidos)){
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

    //permite al administrador agregar un nuevo artículo a la base de datos. Redirige al administrador de
    // vuelta a la vista de administración para que pueda seguir gestionando otros aspectos de la aplicación.
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

    //permite al administrador aumentar el stock de un artículo específico en la base de datos. 
    //Redirige al administrador de vuelta a la vista de administración para que pueda seguir gestionando 
    //otros aspectos de la aplicación. La función garantiza que la operación de aumento del stock se realice 
    //de manera segura dentro de una transacción para mantener la integridad de los datos en la base de datos.
    def aumentarStock(){

        Articulo.withTransaction{ 
            Articulo articulo = Articulo.get(params.articulo)
            articulo.stock++
            articulo.save(flush: true)
        }

        redirect(controller: "administrador", action: "vistaAdministrador")
    }

    //permite al administrador reducir el stock de un artículo específico en la base de datos. 
    //Antes de reducir el stock, verifica que el stock actual sea mayor que 0 para evitar valores negativos. 
    //Redirige al administrador de vuelta a la vista de administración para que pueda seguir gestionando 
    //otros aspectos de la aplicación.
    def reducirStock(){

        Articulo.withTransaction{

            Articulo articulo = Articulo.get(params.articulo)

            if(articulo.hayStock(articulo)){
                articulo.stock--
                articulo.save()
            }
        }
        redirect(controller: "administrador", action: "vistaAdministrador")
    }

}
