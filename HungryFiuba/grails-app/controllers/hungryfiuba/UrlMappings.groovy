package hungryfiuba

class UrlMappings {

    static mappings = {

        "/register"(controller: 'cliente', action: 'register')
        "/inicio"(view: "/mostrarInicio")
        "/articulosDisponibles"(view: "/articulosDisponibles")

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        
    "/mostrarInicio"(controller: "mostrarInicio") {
        action = [POST: "autenticar"]
    }

        "/"(view:"/index")// algun dia le ponemos mostrarinicio
        "500"(view:'/error')
        "404"(view:'/notFound')

    }
}
