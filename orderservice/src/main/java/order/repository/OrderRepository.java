package order.repository;

import order.entity.TtOrder;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<TtOrder, String> {

}
