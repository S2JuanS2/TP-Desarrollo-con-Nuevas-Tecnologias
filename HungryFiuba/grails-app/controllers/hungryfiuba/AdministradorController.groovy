package hungryfiuba

import java.time.LocalDateTime

class AdministradorController {
    static scaffold = Administrador
    def pedidoService
    def cestaService
    
    // maneja la autenticación del usuario y redireccionando a los usuarios que han iniciado sesión para 
    //comenzar un nuevo pedido, muestra la página de inicio a los usuarios que no han iniciado sesión o no
    // tienen información de sesión asociada.
    def vistaInicio(){
        if(session.cliente){
            redirect(controller: "pedido", action: "comenzarPedido")
        }
        render(view: "/inicio")
    }
    
    //proceso de autenticación de usuarios, si el cliente no existe se muestra la vista de registro fallido 
    //sino se procede a verificar si la contraseña proporcionada coincide con la contraseña almacenada para 
    //el cliente en caso de que no sea asi se muestra la vista de regristo fallido sino se almacena en la 
    //sesión del usuario el cliente, se muestra la vista /bienvenida, página de inicio o bienvenida para el
    // cliente autenticado.
    def autenticar() {        
        Cliente cliente = Cliente.findByIdentificadorValor(params.idValor)
        
        if(cliente && cliente.clienteCodigoCorrecto(params.contrasena)){
            session.cliente = cliente
            render(view: "/bienvenida")
        }else {
            render(view: "/registroFallido")
        } 
    }

    //cierra la sesión del cliente y lo redirije a la página de inicio de sesión, el estado de la sesión se elimina.
    def logout(){
        session.cliente = null
        render(view:"/inicio")
    }

    //muestra la vista de administración. Si no existe un administrador con el nombre "admin" en la base de datos,
    // crea uno nuevo y lo almacena. Obtiene listas de pedidos y artículos y muestra la vista  con estos datos y 
    //el objeto admin para la administración de la aplicación.
    def vistaAdministrador(){
        Administrador admin = Administrador.obtenerAdministrador()
        if(admin){
            def pedidos = Pedido.list()
            def articulos = Articulo.list()
            render(view: "/administracion", model: [pedidos: pedidos, articulos: articulos, admin: admin])
        }else{
            //exception
        }
    }

    //confirmar un pedido específico en la aplicación de administración. Encuentra el objeto Administrador,
    // llama a un método para confirmar el pedido con los parámetros proporcionados y luego redirige al 
    //administrador a la vista de administración para que pueda seguir gestionando otros aspectos de la aplicación.
    def confirmarPedido(){
        Pedido pedido = Pedido.get(params.pedido)
        if(pedido){
            pedidoService.confirmarPedido(pedido.id)
            redirect(controller: "administrador", action: "vistaAdministrador")
        }else{
            //exception
        }
    }
 
    //permite cancelar un pedido específico en la aplicación de administración. Realiza acciones como actualizar
    // el estado del cliente y vaciar la cesta, dependiendo del estado y el estado de pago del pedido. Elimina el
    // pedido y redirige al administrador a la vista de administración.
    def cancelarPedido(){
        Pedido pedido = Pedido.get(params.pedido)
        Cliente cliente = pedido.cliente

        if (pedido.puedeSerCancelado()) {
            if(!pedido.estaPago() && pedido.listoParaEntregar()) {
                cliente.penalizar()
            }
            if(pedido.fueEntregado()){
                cestaService.vaciarCestaDePedidoFinalizado(cliente.id)
            }else{
                cestaService.vaciarCesta(cliente.id)
            }
            pedidoService.eliminarPedido(pedido.id)
        }
        redirect(controller: "administrador", action: "vistaAdministrador")
    }
}