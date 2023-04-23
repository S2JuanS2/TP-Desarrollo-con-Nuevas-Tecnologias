package comedor

import grails.gorm.transactions.Transactional
import java.time.LocalDateTime
import java.time.LocalDate

@Transactional
class PedidoService {

    Pedido crearPedido(long clienteId, long articuloId, int cantidad) {
        Cliente cliente = Cliente.get(clienteId)
        Articulo articulo = Articulo.get(articuloId)
        
        Pedido p = new Pedido(cliente, articulo, cantidad, LocalDateTime.now())
        p.save(failOnError: true)
    }
}
