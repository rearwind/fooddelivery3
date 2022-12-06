package fooddelivery.domain;

import fooddelivery.infra.AbstractEvent;
import lombok.Data;
import java.util.*;


@Data
public class PayCancelled extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String status;
}
