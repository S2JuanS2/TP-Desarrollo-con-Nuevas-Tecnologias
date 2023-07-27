package hungryfiuba

class ClienteController {
    def clienteService
    static scaffold = Cliente
    def register(){

        render(view: "/register")
    }
    
    def mostrarClientes() {
        def clientes = Cliente.list()

        render(view: '/mostrarClientes', model: [clientes: clientes])
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
        cliente.estado = EstadoCuenta.BLOQUEADA
        cliente.deuda = 1000
        //cliente.estado = EstadoCuenta.NO_BLOQUEADA
        cliente.save(failOnError: true)
        
        render(view: '/registroExitoso')
   
    }

    //ESTO YA NO SE USA MAS?? 
    def agregarArticulo(){

        Cliente cliente = session.cliente

        Articulo articulo = Articulo.findByNombre(params.nombre)

        if(cliente && articulo){
            //cliente.cesta.agregarArticulo(articulo)

        }
        cliente.save(flush: true)

        render(view: "/mostrarArticulos")
    }
    def registrarDeuda(){
        Cliente cliente = session.cliente
        cliente = Cliente.get(cliente.id)
        clienteService.actualizarDeuda(cliente.id)
        render(view: "/deudaPaga", model: [cliente: cliente])
    }

}