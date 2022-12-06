package fooddelivery.infra;

import javax.naming.NameParser;

import javax.naming.NameParser;
import javax.transaction.Transactional;

import fooddelivery.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import fooddelivery.domain.*;

@Service
@Transactional
public class PolicyHandler{
    @Autowired CookingRepository cookingRepository;
    @Autowired InventoryRepository inventoryRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='PayAccepted'")
    public void wheneverPayAccepted_CopyOrderInfo(@Payload PayAccepted payAccepted){

        PayAccepted event = payAccepted;
        System.out.println("\n\n##### listener CopyOrderInfo : " + payAccepted + "\n\n");


        

        // Sample Logic //
        Cooking.copyOrderInfo(event);
        

        

    }

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='PayCancelled'")
    public void wheneverPayCancelled_Cancel(@Payload PayCancelled payCancelled){

        PayCancelled event = payCancelled;
        System.out.println("\n\n##### listener Cancel : " + payCancelled + "\n\n");


        

        // Sample Logic //
        Cooking.cancel(event);
        

        

    }

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='PayAccepted'")
    public void wheneverPayAccepted_Decrease(@Payload PayAccepted payAccepted){

        PayAccepted event = payAccepted;
        System.out.println("\n\n##### listener Decrease : " + payAccepted + "\n\n");


        

        // Sample Logic //
        Inventory.decrease(event);
        

        

    }

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='PayCancelled'")
    public void wheneverPayCancelled_Increase(@Payload PayCancelled payCancelled){

        PayCancelled event = payCancelled;
        System.out.println("\n\n##### listener Increase : " + payCancelled + "\n\n");


        

        // Sample Logic //
        Inventory.increase(event);
        

        

    }

}


