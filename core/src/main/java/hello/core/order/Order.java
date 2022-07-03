package hello.core.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {

  private Long MemberId;
  private String itemName;
  private int itemPrice;
  private int discountPrice;

  public int calculatePrice() {
    return itemPrice - discountPrice;
  }
}
