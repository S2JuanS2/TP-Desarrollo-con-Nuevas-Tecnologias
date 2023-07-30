package hungryfiuba
import java.time.LocalDateTime

class ClienteController {

    def clienteService
    def administradorService

    static scaffold = Cliente
    
    def register(){

        render(view: "/register")
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
    
    def crearCliente(){

        Cliente cliente = new Cliente(
            nombre: params.nombre,
            apellido: params.apellido,
            identificadorTipo: params.idTipo,
            identificadorValor: params.idValor,
            contrasena: params.contrasena
        )
        
        Cesta cesta = new Cesta()
        //aca accedo en memoria y no base de datos por eso no tira error proxy
        cliente.cesta = cesta
        cliente.estado = EstadoCuenta.NO_BLOQUEADA

        cliente.save(failOnError: true)
        
        render(view: '/registroExitoso')
   
    }
    
    def registrarDeuda(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarDeuda(cliente.id)
        render(view: "/deudaPaga", model: [cliente: cliente])
    }

    def penalizarCliente() {
        def cliente = session.cliente
        Pedido pedido = Pedido.getByCliente(cliente)
        LocalDateTime ahora = LocalDateTime.now()

        // Calcular la diferencia entre el LocalDateTime actual y el momento de creación en horas
        long horasTranscurridas = pedido.momentoDeCreacion.until(ahora, ChronoUnit.HOURS)
// si no paso una hora el admin no peiude camcelar el pedio
        if (horasTranscurridas >= 1 && (pedido.estado == EstadoPedido.EN_PREPARACION || pedido.estado == EstadoPedido.LISTO_PARA_ENTREGAR)) {
            pedido.estado = EstadoPedido.CANCELADO
            if(pedido.estadoPago == EstadoDelPago.PENDIENTE_DE_PAGO) {
                if(cliente.strikes < 3) cliente.strikes++
                if(clientes.strikes == 3) cliente.estadoCuenta = EstadoCuenta.BLOQUEADA
            }
        }
    }

    def calificacionesPendientes(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        render(view:"/calificacionPendiente", model: [cliente: cliente])
    }
    def calificar(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        if(cliente.calificacionesPendientes>0){
            render(view:"/calificar")
        }else{
            render(view:"/ceroCalificacionesPendientes")
        }
    }
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
