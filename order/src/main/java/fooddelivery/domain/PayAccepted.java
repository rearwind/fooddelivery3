package fooddelivery.domain;

import fooddelivery.infra.AbstractEvent;
import lombok.Data;
import java.util.*;


@Data
public class PayAccepted extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String status;
    private Long price;
    private String action;
    private Long foodId;
    private Integer qty;
    private String customerId;
}
