package hungryfiuba

class UrlMappings {

    static mappings = {

        "/register"(controller: 'cliente', action: 'register')
        "/inicio"(controller: 'administrador', action: 'vistaInicio')
        "/mostrarArticulos"(controller: 'articulo', action: 'mostrarArticulos')
        "/registroExitoso"(view:'registroExitoso')
        "/mostrarClientes"(controller: 'cliente', action: 'mostrarClientes')
        "/administrador/registroFallido"(view:'registroFallido')
        "/mostrarCesta"(controller: "cesta", action: "mostrarCesta")
        "/pedidoCreado"(controller:"articulo", action:"mostrarArticulos")
        "/pedido/cestaVacia"(view:"cestaVacia")
        "/pedido/pedidoEnCurso"(view:"pedidoEnCurso")
        "/pedido/comedorCerrado"(view:"comedorCerrado")
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        
        "/"(view:"/index")// algun dia le ponemos mostrarinicio
        "500"(view:'/error')
        "404"(view:'/notFound')

    }
}
