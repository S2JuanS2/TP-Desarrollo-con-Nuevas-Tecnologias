package hungryfiuba

class ClienteController {

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
    ).save(failOnError: true)

    def clientes = Cliente.list()
    def articulos = Articulo.list()
    
    render(view: '/registroExitoso')
   
    }
}