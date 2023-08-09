package hungryfiuba

class UrlMappings {

    static mappings = {

        "/register"(controller: 'session', action: 'register')
        "/inicio"(controller: 'session', action: 'vistaInicio')
        "/mostrarArticulos"(controller: 'articulo', action: 'mostrarArticulos')
        "/registroExitoso"(view:'registroExitoso')
        "/session/registroFallido"(view:'registroFallido')
        "/mostrarCesta"(controller: "cesta", action: "mostrarCesta")
        "/pedidoCreado"(controller:"articulo", action:"mostrarArticulos")
        "/pedido/cestaVacia"(view:"cestaVacia")
        "/pedido/pedidoEnCurso"(view:"pedidoEnCurso")
        "/pedido/comedorCerrado"(view:"comedorCerrado")
        "/pedido/cuentaBloqueada"(view:"cuentaBloqueada")
        "/pagarDeuda"(controller:"cliente",action:"pagarDeuda")

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        
        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')

    }
}
