package hungryfiuba

import grails.gorm.transactions.Transactional


class CestaService {

    @Transactional
    def agregarArticuloACesta(long articuloId, long clienteId) {

        Cliente cliente = Cliente.get(clienteId)
        Articulo articulo = Articulo.get(articuloId)

        //si llamo a un metodo del dominio por alguna razón no persiste en la base de datos.
        cliente.cesta.cantidadDeArticulos++
        articulo.stock--
        cliente.cesta.montoTotal += articulo.precio
        cliente.cesta.articulos.add(articulo)


        cliente.cesta.save(flush: true)

    }

    @Transactional
    def eliminarArticuloACesta(long articuloId, long clienteId) {

        Cliente cliente = Cliente.get(clienteId)
        Articulo articulo = Articulo.get(articuloId)

        //si llamo a un metodo del dominio por alguna razón no persiste en la base de datos.
        cliente.cesta.cantidadDeArticulos--
        articulo.stock++
        cliente.cesta.montoTotal -= articulo.precio

        cliente.cesta.articulos.remove(articulo)


        cliente.cesta.save(flush: true)

    }
}
