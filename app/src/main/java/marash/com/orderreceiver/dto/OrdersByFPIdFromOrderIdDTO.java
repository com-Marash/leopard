package marash.com.orderreceiver.dto;

import java.util.List;

import marash.com.orderreceiver.service.MizpizWebService;

/**
 * DTO of {@link MizpizWebService#getOrdersByFoodProviderIdFromOrderId}
 *
 * @author Arash Khosravi
 */

public class OrdersByFPIdFromOrderIdDTO
{
    private Long OrderID;
    private String Name;
    private String Tell;
    private String DateAndTime;
    private String Address;
    private String PostalCode;
    private String AptNo;
    private String Buzzer;
    private String OrderTiming;
    private String PaymentMethod;
    private Double Total;
    private List<OrderDetailDTO> OrderDetails;


}
