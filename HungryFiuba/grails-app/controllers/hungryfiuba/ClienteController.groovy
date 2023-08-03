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
        if(cliente.tieneCalifacionesPendientes()){
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
    def primerApecto(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarCalificacion(cliente.id,params.calif, 1)
        render(view:"/segundoAspecto")
    }

    //segundo aspecto
    def segundoApecto(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarCalificacion(cliente.id,params.calif, 2)
        render(view:"/tercerAspecto")
    }

    //tercer aspecto
    def tercerApecto(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarCalificacion(cliente.id,params.calif, 3)
        redirect(controller: "cliente", action: "finCalificacion")
    }

    def finCalificacion(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarCalificacion(cliente.id,null, 0)
        administradorService.calificaciones(cliente.id)
        render(view:"/resultados")
    }



}
