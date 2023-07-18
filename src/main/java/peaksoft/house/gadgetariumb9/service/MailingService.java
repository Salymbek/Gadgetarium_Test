package peaksoft.house.gadgetariumb9.service;

import org.springframework.stereotype.Service;
import peaksoft.house.gadgetariumb9.dto.request.MailingRequest;

@Service
public interface MailingService {
 // void sendSimpleMailMessage(String name, String to, String token);
  void sendHtmlEmail(MailingRequest mailingRequest);

}
