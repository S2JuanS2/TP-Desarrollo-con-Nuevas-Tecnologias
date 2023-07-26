package hungryfiuba

import grails.gorm.transactions.Transactional


class CestaService {

    @Transactional
    def agregarArticuloACesta(long articuloId, long clienteId) {

        Cliente cliente = Cliente.get(clienteId)
        Articulo articulo = Articulo.get(articuloId)

        cliente.cesta.agregarArticulo(articulo)

        cliente.cesta.save(flush: true)

    }
}
