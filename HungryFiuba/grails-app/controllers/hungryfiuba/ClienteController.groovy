package hungryfiuba
import java.time.LocalDateTime

class ClienteController {

    def clienteService
    def administradorService

    static scaffold = Cliente
    
    //muestra la vista de registro
    def register(){
        render(view: "/register")
    }
    
    //muestra la cesta de compras del cliente si el cliente está autenticado. 
    //Si no hay un cliente autenticado, redirige al usuario a la vista de "registro fallido"
    //para indicar que el usuario no tiene acceso a la cesta sin autenticación. 
    //La función permite al cliente ver los artículos agregados a la cesta y la información relacionada con la compra.
    def mostrarCesta() {
        if (session.cliente) {
            render(view: "/mostrarCesta")
        } else {
            render(view: "/registroFallido")
        }
    }
    
    //permite la creación de un nuevo cliente. Al completar el registro, se crea un nuevo objeto Cliente,
    // se le asigna una nueva cesta y se establece su estado de cuenta. Luego, se guarda el cliente en la
    // base de datos y se muestra la vista /registroExitoso para indicar que el registro se ha realizado con éxito.
    def crearCliente(){
        Cliente cliente = new Cliente(
            nombre: params.nombre,
            apellido: params.apellido,
            identificadorTipo: params.idTipo,
            identificadorValor: params.idValor,
            contrasena: params.contrasena
        )
        Cesta cesta = new Cesta()
        cliente.cesta = cesta
        cliente.estado = EstadoCuenta.NO_BLOQUEADA
        cliente.save(failOnError: true)
        
        render(view: '/registroExitoso')
    }
    
    //permite registrar una deuda para el cliente . El cliente debe estar autenticado para realizar esta acción.
    // Después de registrar la deuda, se actualiza el objeto Cliente en la base de datos y se muestra la vista 
    // /deudaPaga para mostrar un mensaje de confirmación o información relevante relacionada con la deuda registrada.
    def registrarDeuda(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarDeuda(cliente.id)
        render(view: "/deudaPaga", model: [cliente: cliente])
    }

    // penaliza a un cliente en función del tiempo transcurrido desde la creación de un pedido y su estado actual. 
    //Si un pedido ha estado en estado de preparación o listo para entregar durante al menos una hora, se cancela 
    //el pedido y se penaliza al cliente si su estado de pago es PENDIENTE_DE_PAGO. La penalización se realiza mediante 
    //el aumento del contador de strikes del cliente, y si el cliente alcanza los 3 strikes, su cuenta se bloquea cambiando 
    //el estado de la cuenta a BLOQUEADA. 
    def penalizarCliente() {
        def cliente = session.cliente
        Pedido pedido = Pedido.getByCliente(cliente)
       
        if (pedido.debeSerCancelado(cliente)) {
            pedido.estado = EstadoPedido.CANCELADO
            if(pedido.noFueAbonado(cliente)) {
                if(cliente.clienteConMenosDeTresStrikes(cliente)) cliente.strikes++
                if(cliente.clienteConTresStrikes(cliente)) cliente.estadoCuenta = EstadoCuenta.BLOQUEADA
            }
        }
    }

    //muestra al cliente una vista que contiene una lista de calificaciones pendientes. La vista /calificacionPendiente
    // muestra información sobre estas calificaciones pendientes. 
    def calificacionesPendientes(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        render(view:"/calificacionPendiente", model: [cliente: cliente])
    }

    //muestra al cliente una vista que le permite realizar calificaciones, siempre que tenga calificaciones pendientes
    // por realizar. Si el cliente tiene calificaciones pendientes, se muestra la vista /calificar para que pueda realizar
    // las calificaciones correspondientes. Si no hay calificaciones pendientes, se muestra la vista /ceroCalificacionesPendientes
    // para informar al cliente que no hay calificaciones por realizar en ese momento. 
    def calificar(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        if(cliente.califacionesPendientes(cliente)){
            render(view:"/calificar")
        }else{
            render(view:"/ceroCalificacionesPendientes")
        }
    }

    //muestra la vista del primer aspecto a calificar
    def comenzarACalificar(){
        render(view:"/primerAspecto")
    }

    //primer aspecto
    def primerApectoUnaEstrella(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarCalificacion(cliente.id,1, 1)
        render(view:"/segundoAspecto")
    }
    def primerApectoDosEstrellas(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarCalificacion(cliente.id,2, 1)
        render(view:"/segundoAspecto")
    }
    def primerApectoTresEstrellas(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarCalificacion(cliente.id,3, 1)
        render(view:"/segundoAspecto")
    }
    def primerApectoCuatroEstrellas(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarCalificacion(cliente.id,4, 1)
        render(view:"/segundoAspecto")
    }
    def primerApectoCincoEstrellas(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarCalificacion(cliente.id,5, 1)
        render(view:"/segundoAspecto")
    }
    //segundo aspecto
    def segundoApectoUnaEstrella(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarCalificacion(cliente.id,1, 2)
        render(view:"/tercerAspecto")
    }
    def segundoApectoDosEstrellas(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarCalificacion(cliente.id,2, 2)
        render(view:"/tercerAspecto")
    }
    def segundoApectoTresEstrellas(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarCalificacion(cliente.id,3, 2)
        render(view:"/tercerAspecto")
    }
    def segundoApectoCuatroEstrellas(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarCalificacion(cliente.id,4, 2)
        render(view:"/tercerAspecto")
    }
    def segundoApectoCincoEstrellas(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarCalificacion(cliente.id,5, 2)
        render(view:"/tercerAspecto")
    }
    //tercer aspecto
    def tercerApectoUnaEstrella(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarCalificacion(cliente.id,1, 3)
        redirect(controller: "cliente", action: "finCalificacion")
    }
    def tercerApectoDosEstrellas(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarCalificacion(cliente.id,2, 3)
        redirect(controller: "cliente", action: "finCalificacion")
    }
    def tercerApectoTresEstrellas(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarCalificacion(cliente.id,3, 3)
        redirect(controller: "cliente", action: "finCalificacion")
    }
    def tercerApectoCuatroEstrellas(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarCalificacion(cliente.id,4, 3)
        redirect(controller: "cliente", action: "finCalificacion")
    }
    def tercerApectoCincoEstrellas(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarCalificacion(cliente.id,5, 3)
        redirect(controller: "cliente", action: "finCalificacion")
    }
    def finCalificacion(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarCalificacion(cliente.id,0, 0)
        administradorService.calificaciones(cliente.id)
        render(view:"/resultados")
    }



}
