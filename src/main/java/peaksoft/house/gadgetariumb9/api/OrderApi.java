package peaksoft.house.gadgetariumb9.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.house.gadgetariumb9.dto.response.InfographicsResponse;
import peaksoft.house.gadgetariumb9.service.OrderService;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderApi {

  private final OrderService orderService;

  @GetMapping("/info")
  public InfographicsResponse info (@RequestParam(defaultValue = "day") String period){
    try {
      return orderService.info(period);
    } catch (IllegalArgumentException e) {
      throw new IllegalStateException(e);
    }
  }
}
