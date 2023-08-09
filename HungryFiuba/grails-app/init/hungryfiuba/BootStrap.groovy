package hungryfiuba

class BootStrap {

    def init = { servletContext ->
        if (!Administrador.exists()) {
            // Crea un nuevo administrador y guárdalo en la base de datos
            Administrador administrador = new Administrador("admin", 0);
            administrador.save()
        }
        

        Articulo articulo = new Articulo("Pepsi", 320, 13, 20,"https://tusuper.com.ar/image/cache/catalog/P2020/Bebidas/Pepsi%201,5l-800x800.jpg", "750ml")
        articulo.save()
        articulo = new Articulo("Coca", 400, 14, 5,"http://casadebebidas.com.ar/277-large_default/coca-15-sin-azucar.jpg", "750ml")
        articulo.save()
        articulo = new Articulo("Agua", 250, 7, 10,"http://casadebebidas.com.ar/1335-large_default/bonaqua-agua-con-gas.jpg", "750ml")
        articulo.save()
        articulo = new Articulo("Alfajor Oreo", 270, 3, 8,"https://http2.mlstatic.com/D_NQ_NP_753966-MLA47778214655_102021-O.webp", "750ml")
        articulo.save()
        articulo = new Articulo("Dientes Mogul", 720, 9, 5,"https://statics.dinoonline.com.ar/imagenes/large_460x460/2182476_l.jpg", "150g")
        articulo.save()
        articulo = new Articulo("Tostado", 430, 8, 30,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVgIooDqHXtnnGiMyZ5K0Yz_zyU-BN5EklbA&usqp=CAU", "J&Q")
        articulo.save()
        articulo = new Articulo("Café", 80, 6, 500,"https://img.freepik.com/vector-gratis/taza-realista-cafe-negro-elaborado-ilustracion-vector-platillo_1284-66002.jpg?w=2000", "J&Q")
        articulo.save()
        
    }
    def destroy = {
    }
}
