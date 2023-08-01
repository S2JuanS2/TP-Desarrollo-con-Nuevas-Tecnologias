package hungryfiuba

import java.time.LocalDateTime

class AdministradorController {

    static scaffold = Administrador

    def pedidoService
    def cestaService
    
    // maneja la autenticación del usuario y redireccionando a los usuarios que han iniciado sesión para comenzar un nuevo pedido, muestra la página de inicio a los usuarios que no han iniciado sesión o no tienen información de sesión asociada.
    def vistaInicio(){
        if(session.cliente){
            redirect(controller: "pedido", action: "comenzarPedido")
        }
        render(view: "/inicio")
    }

    //muestra la vista con los articulos que maneja el administrador
    def mostrarArticulos() {
        render(view: 'mostrarArticulos')
    }

    //proceso de autenticación de usuarios, si el cliente no existe se muestra la vista de registro fallido sino se procede a verificar si la contraseña proporcionada coincide con la contraseña almacenada para el cliente en caso de que no sea asi se muestra la vista de regristo fallido sino se almacena en la sesión del usuario el cliente, se muestra la vista /bienvenida, página de inicio o bienvenida para el cliente autenticado.
    def autenticar() {        
        Administrador admin = Administrador.findByNombre("admin")
        if(admin.clienteExiste(params.idValor)){
            if (admin.clienteCodigoCorrecto(params.contrasena, params.idValor)) {    
                def cliente = Cliente.findByIdentificadorValor(params.idValor)
                session.cliente = cliente
                render(view: "/bienvenida")
            } else {
                render(view: "/registroFallido")
            }
        } else {
            render(view: "/registroFallido")
            } 
    }

    //cierra la sesión del cliente y lo redirije a la página de inicio de sesión, el estado de la sesión se elimina.
    def logout(){
        session.cliente = null
        render(view:"/inicio")
    }

    //El admin es único, patrón singletón?
    //muestra la vista de administración. Si no existe un administrador con el nombre "admin" en la base de datos, crea uno nuevo y lo almacena. Obtiene listas de pedidos y artículos y muestra la vista  con estos datos y el objeto admin para la administración de la aplicación.
    def vistaAdministrador(){
        Administrador admin = Administrador.findByNombre("admin")
        if(!admin){
            Administrador administrador = new Administrador(
                nombre: "admin",
                cantidadCalificaciones: 0
            )
            administrador.save()
            admin = administrador
        }
        def pedidos = Pedido.list()
        def articulos = Articulo.list()
        render(view: "/administracion", model: [pedidos: pedidos, articulos: articulos, admin: admin])
    }

    //confirmar un pedido específico en la aplicación de administración. Encuentra el objeto Administrador, llama a un método para confirmar el pedido con los parámetros proporcionados y luego redirige al administrador a la vista de administración para que pueda seguir gestionando otros aspectos de la aplicación.
    def confirmarPedido(){
        Pedido pedido = Pedido.get(params.pedido)
        pedidoService.confirmarPedido(pedido.id)
        redirect(controller: "administrador", action: "vistaAdministrador")
    }
 
 // Calcular la diferencia entre el LocalDateTime actual y el momento de creación en horas
       // long horasTranscurridas = pedido.momentoDeCreacion.until(ahora, ChronoUnit.HOURS)horasTranscurridas >= 1
// si no paso una hora el admin no peiude camcelar el pedio

    //permite cancelar un pedido específico en la aplicación de administración. Realiza acciones como actualizar el estado del cliente y vaciar la cesta, dependiendo del estado y el estado de pago del pedido. Elimina el pedido y redirige al administrador a la vista de administración.
    def cancelarPedido(){
        Administrador admin = Administrador.findByNombre("admin")
        Pedido pedido = Pedido.get(params.pedido)
        //LocalDateTime ahora = LocalDateTime.now() no se esta usando hay q ver q hacemos 
        
        if (admin.pedidoEnEstadoParaCancelar(pedido)) {
            if(admin.pedidoEnEstadoNoPago(pedido)) {
                if(admin.pedidoConMenosDeTresStrikes(pedido)){
                    pedido.cliente.strikes++
                } 
                if(admin.pedidoConTresStrikes(pedido)){
                    pedido.cliente.estado = EstadoCuenta.BLOQUEADA
                } 
            }
            if(admin.pedidoEnEstadoParaVaciarCesta(pedido)){
                cestaService.vaciarCestaDePedidoFinalizado(pedido.cliente.id)
            }else{
                cestaService.vaciarCesta(pedido.cliente.id)
            }
            pedidoService.eliminarPedido(pedido.id)
        }
        redirect(controller: "administrador", action: "vistaAdministrador")
    }
}