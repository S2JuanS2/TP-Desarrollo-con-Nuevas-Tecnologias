package hungryfiuba

import java.time.LocalDateTime

class AdministradorController {

    static scaffold = Administrador

    def pedidoService
    def cestaService
    
    //muestra la vista de administración. obtiene al administrador con el patrón singletón.
    //Obtiene listas de pedidos y artículos y muestra la vista  con estos datos y 
    //el objeto admin para la administración de la aplicación.
    def vistaAdministrador(){
        Administrador admin = Administrador.obtenerAdministrador()
        if(!admin){
            throw new ObjetoNoExisteException("El admin no existe")
        }
        def pedidos = Pedido.list()
        def articulos = Articulo.list()
        render(view: "/administracion", model: [pedidos: pedidos, articulos: articulos, admin: admin])
    }

    //confirmar un pedido específico en la aplicación de administración. Encuentra el objeto Administrador,
    // llama a un método para confirmar el pedido con los parámetros proporcionados y luego redirige al 
    //administrador a la vista de administración para que pueda seguir gestionando otros aspectos de la aplicación.
    def confirmarPedido(){
        Pedido pedido = Pedido.get(params.pedido)
        if(!pedido){
            throw new ObjetoNoExisteException("El pedido no existe")
        }
        pedidoService.confirmarPedido(pedido.id)
        redirect(controller: "administrador", action: "vistaAdministrador")
    }
    
 
    //permite cancelar un pedido específico en la aplicación de administración. Realiza acciones como actualizar
    // el estado del cliente y vaciar la cesta, dependiendo del estado y el estado de pago del pedido. Elimina el
    // pedido y redirige al administrador a la vista de administración.
    def cancelarPedido(){
        Pedido pedido = Pedido.get(params.pedido)
        Cliente cliente = pedido.cliente

        if(!pedido || !cliente){
            throw new ObjetoNoExisteException("El pedido/cliente no existe")
        }
        pedidoService.cancelarYActualizarPedido(pedido, cliente)
        redirect(controller: "administrador", action: "vistaAdministrador")
    }
}