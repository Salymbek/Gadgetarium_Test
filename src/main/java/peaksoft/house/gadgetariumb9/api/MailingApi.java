package peaksoft.house.gadgetariumb9.api;

import jakarta.mail.MessagingException;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.house.gadgetariumb9.dto.request.MailingRequest;
import peaksoft.house.gadgetariumb9.dto.response.SimpleResponse;
import peaksoft.house.gadgetariumb9.service.MailingService;
@RestController
@RequestMapping("/mailing")
public class MailingApi {

  private final MailingService mailingService;


  public MailingApi(MailingService mailingService) {
    this.mailingService = mailingService;
  }

//
//  @PostMapping("/sendEmail")
//  public void sendMailing(@RequestBody MailingRequest mailingRequest, Model model)throws MessagingException, IOException {
//    model.addAttribute("description",mailingRequest.getDescription());
//    model.addAttribute("image",mailingRequest.getImage());
//    model.addAttribute("finishDate",mailingRequest.getFinishDate());
//    mailingService.sendMailing(mailingRequest);
//  }


  @PostMapping("/send")
  public void sendMailing(@RequestBody MailingRequest mailingRequest)
      throws MessagingException, IOException {
//    try {
      mailingService.sendMailing(mailingRequest);
//      return ResponseEntity.ok("Mailing sent successfully");
//    } catch (MessagingException | IOException e) {
//      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send mailing");
//    }
  }





}
