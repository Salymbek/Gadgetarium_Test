//package peaksoft.house.gadgetariumb9.api;
//
//import jakarta.mail.MessagingException;
//import java.io.IOException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import peaksoft.house.gadgetariumb9.dto.request.MailingRequest;
//import peaksoft.house.gadgetariumb9.service.MailingService;
//import peaksoft.house.gadgetariumb9.service.impl.MailingPart2;
//@RestController
//@RequestMapping("/mailing")
//public class MailingApi {
//
//  private final MailingPart2 mailingService;
//
//
//  @Autowired
//  public MailingApi(MailingPart2 mailingService) {
//    this.mailingService = mailingService;
//  }
//
////
////  @PostMapping("/sendEmail")
////  public void sendMailing(@RequestBody MailingRequest mailingRequest, Model model)throws MessagingException, IOException {
////    model.addAttribute("description",mailingRequest.getDescription());
////    model.addAttribute("image",mailingRequest.getImage());
////    model.addAttribute("finishDate",mailingRequest.getFinishDate());
////    mailingService.sendMailing(mailingRequest);
////  }
//
//
//  @PostMapping("/send")
//  public ResponseEntity<String> sendMailing(@RequestBody MailingRequest mailingRequest) {
//    mailingService.sendHtmlEmail(mailingRequest);
//    return ResponseEntity.ok("Mailing sent successfully");
//  }
//
//
//
//
//
//}