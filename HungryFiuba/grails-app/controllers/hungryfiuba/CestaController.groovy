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

        def articuloId = params.articulo

        def articulo = Articulo.get(articuloId)

        if(cliente && articulo){
            cestaService.agregarArticuloACesta(articulo.id, cliente.id)
            render(view: "/mostrarArticulos")
        }else{
            render(view: "/inicio")
        }

    }

}
