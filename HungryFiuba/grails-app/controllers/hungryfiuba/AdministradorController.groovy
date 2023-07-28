package hungryfiuba

import java.time.LocalDateTime


class AdministradorController {

    static scaffold = Administrador

    def pedidoService

    def vistaInicio(){
        if(session.cliente){
            redirect(controller: "pedido", action: "comenzarPedido")
        }
        render(view: "/inicio")
    }

    def mostrarArticulos() {
        render(view: 'mostrarArticulos')
    }
    
    def autenticar() {
        def identificadorTipo = params.idTipo
        def identificadorValor = params.idValor
        def contrasena = params.contrasena
    
        def cliente = Cliente.findByIdentificadorValor(identificadorValor)

        if(cliente != null){
            if (cliente && cliente.contrasena == contrasena ) {    
                session.cliente = cliente
                render(view: "/bienvenida")
            } else {
                render(view: "/registroFallido")
            }
        } else {
            render(view: "/registroFallido")
            } 
    }
    def logout(){
        session.cliente = null
        render(view:"/inicio")
    }

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

    def confirmarPedido(){

        Pedido pedido = Pedido.get(params.pedido)
        pedidoService.confirmarPedido(pedido.id)

        def pedidos = Pedido.list()
        def articulos = Articulo.list()
        redirect(controller: "administrador", action: "vistaAdministrador")
    }

    def cancelarPedido(){

        Pedido pedido = Pedido.get(params.pedido)
        LocalDateTime ahora = LocalDateTime.now()
        //Cliente cliente = Pedido.cliente
        
        // Calcular la diferencia entre el LocalDateTime actual y el momento de creación en horas
       // long horasTranscurridas = pedido.momentoDeCreacion.until(ahora, ChronoUnit.HOURS)horasTranscurridas >= 1
// si no paso una hora el admin no peiude camcelar el pedio
        if (pedido.estado == EstadoPedido.LISTO_PARA_ENTREGAR) {
            if(pedido.estadoPago == EstadoDelPago.PENDIENTE_DE_PAGO) {
                if(pedido.cliente.strikes < 3){
                    pedido.cliente.strikes++
                } 
                if(pedido.cliente.strikes == 3){
                    pedido.cliente.estado = EstadoCuenta.BLOQUEADA
                } 
            }
        pedidoService.eliminarPedido(pedido.id)
        }
        def pedidos = Pedido.list()
        def articulos = Articulo.list()
        redirect(controller: "administrador", action: "vistaAdministrador")
    }
}