package hungryfiuba

class CestaController {

    static scaffold = Cesta

    def cestaService

    def mostrarCesta() {

        if (session.cliente) {
            //Si hay autenticación
            def cestas = Cesta.list()
            render(view: '/mostrarCesta', model: [cestas: cestas])
        } else {
            // Si no hay cliente autenticado, redirigir a la página de inicio de sesión
            render(view: "/registroFallido")
        }
    }

    def agregarArticulo(){

        Cliente cliente = session.cliente

        Articulo articulo = Articulo.findByNombre(params.articulo)

        if(cliente){
            cestaService.agregarArticuloACesta(articulo.id, cliente.id)
        }

        render(view: "/mostrarArticulos")
    }

}
