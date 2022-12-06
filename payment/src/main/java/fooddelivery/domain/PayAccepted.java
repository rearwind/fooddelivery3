package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class PayAccepted extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String status;
    private Long price;
    private String action;
    private Long foodId;
    private Integer qty;
    private String customerId;

    public PayAccepted(Payment aggregate){
        super(aggregate);
    }
    public PayAccepted(){
        super();
    }
}
