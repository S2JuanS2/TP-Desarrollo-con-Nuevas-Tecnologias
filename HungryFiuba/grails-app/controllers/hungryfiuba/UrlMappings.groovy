package hungryfiuba

class UrlMappings {

    static mappings = {

        "/register"(controller: 'cliente', action: 'register')
        "/inicio"(controller: 'administrador', action: 'vistaInicio')
        "/mostrarArticulos"(controller: 'articulo', action: 'mostrarArticulos')
        "/registroExitoso"(view:'registroExitoso')
        "/mostrarClientes"(controller: 'cliente', action: 'mostrarClientes')
        "/administrador/registroFallido"(view:'registroFallido')

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
