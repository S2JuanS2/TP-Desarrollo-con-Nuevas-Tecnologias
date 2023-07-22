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