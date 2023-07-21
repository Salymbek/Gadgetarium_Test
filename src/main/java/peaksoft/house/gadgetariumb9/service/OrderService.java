package peaksoft.house.gadgetariumb9.service;

import org.springframework.stereotype.Service;
import peaksoft.house.gadgetariumb9.dto.response.InfographicsResponse;

public interface OrderService {

  InfographicsResponse info (String period);

}
