package hungryfiuba

class SessionController {

    // maneja la autenticación del usuario y redireccionando a los usuarios que han iniciado sesión para 
    //comenzar un nuevo pedido, muestra la página de inicio a los usuarios que no han iniciado sesión o no
    // tienen información de sesión asociada.
    def vistaInicio(){
        if(session.cliente){
            redirect(controller: "pedido", action: "comenzarPedido")
        }
        render(view: "/inicio")
    }
    
    //muestra la vista de registro
    def register(){
        render(view: "/register")
    }
    
    //proceso de autenticación de usuarios, si el cliente no existe se muestra la vista de registro fallido 
    //sino se procede a verificar si la contraseña proporcionada coincide con la contraseña almacenada para 
    //el cliente en caso de que no sea asi se muestra la vista de regristo fallido sino se almacena en la 
    //sesión del usuario el cliente, se muestra la vista /bienvenida, página de inicio o bienvenida para el
    // cliente autenticado.
    def autenticar() {        
        Cliente cliente = Cliente.findByIdentificadorValor(params.idValor)
        
        if(cliente && cliente.contrasenaCorrecta(params.contrasena)){
            session.cliente = cliente
            render(view: "/bienvenida")
        }else {
            render(view: "/registroFallido")
        } 
    }

    //cierra la sesión del cliente y lo redirije a la página de inicio de sesión, el estado de la sesión se elimina.
    def logout(){
        session.cliente = null
        render(view:"/inicio")
    }


}
