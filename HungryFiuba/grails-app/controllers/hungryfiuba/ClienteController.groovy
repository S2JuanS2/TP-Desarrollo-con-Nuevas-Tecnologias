package hungryfiuba
import java.time.LocalDateTime

class ClienteController {

    def clienteService
    def administradorService

    static scaffold = Cliente
    
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

    def register(){

        render(view: "/register")
    }
       
    def registrarDeuda(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarDeuda(cliente.id)
        render(view: "/deudaPaga", model: [cliente: cliente])
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
