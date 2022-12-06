package fooddelivery.infra;

import fooddelivery.domain.*;
import fooddelivery.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DashboardViewHandler {

    @Autowired
    private DashboardRepository dashboardRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPayAccepted_then_CREATE_1 (@Payload PayAccepted payAccepted) {
        try {

            if (!payAccepted.validate()) return;

            // view 객체 생성
            Dashboard dashboard = new Dashboard();
            // view 객체에 이벤트의 Value 를 set 함
            dashboard.setOrderId(payAccepted.getOrderId());
            dashboard.setStatus("결제승인됨");
            // view 레파지 토리에 save
            dashboardRepository.save(dashboard);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderAccepted_then_UPDATE_1(@Payload OrderAccepted orderAccepted) {
        try {
            if (!orderAccepted.validate()) return;
                // view 객체 조회

                List<Dashboard> dashboardList = dashboardRepository.findByOrderId(orderAccepted.getOrderId());
                for(Dashboard dashboard : dashboardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    dashboard.setStatus("주문수락됨");
                // view 레파지 토리에 save
                dashboardRepository.save(dashboard);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderRejected_then_UPDATE_2(@Payload OrderRejected orderRejected) {
        try {
            if (!orderRejected.validate()) return;
                // view 객체 조회

                List<Dashboard> dashboardList = dashboardRepository.findByOrderId(orderRejected.getOrderId());
                for(Dashboard dashboard : dashboardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    dashboard.setStatus("주문거절됨");
                // view 레파지 토리에 save
                dashboardRepository.save(dashboard);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenCookingStarted_then_UPDATE_3(@Payload CookingStarted cookingStarted) {
        try {
            if (!cookingStarted.validate()) return;
                // view 객체 조회

                List<Dashboard> dashboardList = dashboardRepository.findByOrderId(cookingStarted.getOrderId());
                for(Dashboard dashboard : dashboardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    dashboard.setStatus("요리시작됨");
                // view 레파지 토리에 save
                dashboardRepository.save(dashboard);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenCookingFinished_then_UPDATE_4(@Payload CookingFinished cookingFinished) {
        try {
            if (!cookingFinished.validate()) return;
                // view 객체 조회

                List<Dashboard> dashboardList = dashboardRepository.findByOrderId(cookingFinished.getOrderId());
                for(Dashboard dashboard : dashboardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    dashboard.setStatus("요리완료됨");
                // view 레파지 토리에 save
                dashboardRepository.save(dashboard);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPayCancelled_then_DELETE_1(@Payload PayCancelled payCancelled) {
        try {
            if (!payCancelled.validate()) return;
            // view 레파지 토리에 삭제 쿼리
            dashboardRepository.deleteByOrderId(payCancelled.getOrderId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

