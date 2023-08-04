package hungryfiuba
import java.time.LocalDateTime

class ClienteController {

    def clienteService
    def administradorService

    static scaffold = Cliente
          
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
        if(!cliente){
            throw new ObjetoNoExisteException("El cliente no existe")
        }
        clienteService.actualizarDeuda(cliente.id)
        render(view: "/deudaPaga", model: [cliente: cliente])
    }

    //muestra una vista que permite al cliente visualizar y pagar su deuda. Al obtener el cliente actualizado de la
    //base de datos, se asegura de que cualquier cambio en el saldo del cliente se refleje adecuadamente en la vista /pagarDeuda
    def pagarDeuda(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        render(view: "/pagarDeuda", model:[cliente: cliente])
    }

    //muestra al cliente una vista que contiene una lista de calificaciones pendientes. La vista /calificacionPendiente
    // muestra información sobre estas calificaciones pendientes. 
    def calificacionesPendientes(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)

        if(!cliente){
            throw new ObjetoNoExisteException("El cliente no existe")
        }
        render(view:"/calificacionPendiente", model: [cliente: cliente])
    }

    //muestra al cliente una vista que le permite realizar calificaciones, siempre que tenga calificaciones pendientes
    // por realizar. Si el cliente tiene calificaciones pendientes, se muestra la vista /calificar para que pueda realizar
    // las calificaciones correspondientes. Si no hay calificaciones pendientes, se muestra la vista /ceroCalificacionesPendientes
    // para informar al cliente que no hay calificaciones por realizar en ese momento. 
    def calificar(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)

        if(!cliente){
            throw new ObjetoNoExisteException("El cliente no existe")
        }
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

    //actualiza la calificación del cliente en el primer aspecto y luego redirige al cliente a la siguiente etapa de
    // calificación
    def primerApecto(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)

        if(!cliente){
            throw new ObjetoNoExisteException("El cliente no existe")
        }
        clienteService.actualizarCalificacion(cliente.id,params.calif, 1)
        render(view:"/segundoAspecto")
    }

    //actualiza la calificación del cliente en el segundo aspecto y luego redirige al cliente a la siguiente etapa de
    // calificación
    def segundoApecto(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)

        if(!cliente){
            throw new ObjetoNoExisteException("El cliente no existe")
        }
        clienteService.actualizarCalificacion(cliente.id,params.calif, 2)
        render(view:"/tercerAspecto")
    }

    //actualiza la calificación del cliente en el tercer aspecto y luego redirige al cliente a la siguiente etapa de
    // calificación
    def tercerApecto(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)

        if(!cliente){
            throw new ObjetoNoExisteException("El cliente no existe")
        }
        clienteService.actualizarCalificacion(cliente.id,params.calif, 3)
        redirect(controller: "cliente", action: "finCalificacion")
    }

    //finaliza el proceso de calificación de un cliente, realiza acciones adicionales basadas en las 
    //calificaciones proporcionadas y muestra los resultados finales y un mensaje de finalización del proceso de calificación.
    def finCalificacion(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        
        if(!cliente){
            throw new ObjetoNoExisteException("El cliente no existe")
        }
        clienteService.actualizarCalificacion(cliente.id,null, 0)
        administradorService.calificaciones(cliente.id)
        render(view:"/resultados")
    }



}
