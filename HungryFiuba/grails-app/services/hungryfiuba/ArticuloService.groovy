package hungryfiuba

import grails.gorm.transactions.Transactional


class ArticuloService {
    
    //aumenta el stock de un artículo en 1. 
    //La operación se realiza dentro de una transacción para garantizar la integridad de la base de datos.
    @Transactional
    def aumentarStock(Articulo articulo){
        Articulo.withTransaction{
            articulo.setStock(articulo.stock+1)
            articulo.save(flush:true)
        }
    }

    //reduce el stock de un artículo en 1 si hay suficiente stock disponible. 
    //La operación se realiza dentro de una transacción para garantizar la integridad de la base de datos.
    @Transactional
    def reducirStock(Articulo articulo){
        Articulo.withTransaction{
            if(articulo.hayStock()){
                articulo.stock--
                articulo.save(flush: true)
            }
        }
    }
}