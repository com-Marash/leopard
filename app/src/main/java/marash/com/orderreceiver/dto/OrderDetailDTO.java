package marash.com.orderreceiver.dto;

import java.util.List;

/**
 * OrderDetails DTO
 *
 * @author Arash Khosravi
 */

public class OrderDetailDTO {
    private String FoodName;
    private Double Price;
    private Integer Quantity;
    private String SpecialInstruction;
    private List<OrderDetailQADTO> OrderDetailQAs;
}
