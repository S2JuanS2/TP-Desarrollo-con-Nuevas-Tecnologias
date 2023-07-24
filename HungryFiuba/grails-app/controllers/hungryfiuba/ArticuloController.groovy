package hungryfiuba

class ArticuloController {

    static scaffold = Articulo

    def mostrarArticulos(){

        if (session.cliente) {

            def articulos = Articulo.list()
            render(view: '/mostrarArticulos', model: [articulos: articulos])
            
        } else {
            render(view: "/registroFallido")
        }
    }

}
