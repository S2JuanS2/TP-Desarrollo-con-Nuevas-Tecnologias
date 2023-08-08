package hungryfiuba

import grails.gorm.transactions.Transactional


class ArticuloService {
    @Transactional
    def aumentarStock(){
        Articulo.withTransaction{
            articulo.setStock(articulo.stock+1)
            articulo.save(flush:true)
        }
    }
}