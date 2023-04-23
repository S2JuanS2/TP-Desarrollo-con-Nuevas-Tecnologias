package comedor

class UrlMappings {

    static mappings = {
        "/mi-pagina-principal"(controller: 'prueba', action: 'index')

        "/ejemplo"(controller: 'prueba') {
            action = [
                'GET': 'metodoGet',
                'POST': 'metodoPost',
            ]
        }


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
