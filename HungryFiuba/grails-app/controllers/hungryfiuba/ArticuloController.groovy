package hungryfiuba

class ArticuloController {

    static scaffold = Articulo

    def mostrarArticulos(){

        def articulos = Articulo.list()

        render(view: '/mostrarArticulos', model: [articulos: articulos])

    }

}
