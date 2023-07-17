////package peaksoft.house.gadgetariumb9.service;
////
////import jakarta.mail.MessagingException;
////import jakarta.mail.internet.MimeMessage;
////import jakarta.persistence.EntityManager;
////import jakarta.persistence.TypedQuery;
////import java.io.IOException;
////import java.nio.charset.StandardCharsets;
////import java.nio.file.Files;
////import java.nio.file.Path;
////import java.time.ZonedDateTime;
////import java.util.List;
////import java.util.stream.Collectors;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.core.io.Resource;
////import org.springframework.core.io.ResourceLoader;
////import org.springframework.http.HttpStatus;
////import org.springframework.mail.javamail.JavaMailSender;
////import org.springframework.mail.javamail.MimeMessageHelper;
////import org.springframework.stereotype.Service;
////import org.thymeleaf.context.Context;
////import org.thymeleaf.spring6.SpringTemplateEngine;
////import peaksoft.house.gadgetariumb9.dto.request.MailingRequest;
////import peaksoft.house.gadgetariumb9.dto.response.SimpleResponse;
////import peaksoft.house.gadgetariumb9.entities.Mailing;
////import peaksoft.house.gadgetariumb9.entities.User;
////import peaksoft.house.gadgetariumb9.repository.MailingRepository;
////import peaksoft.house.gadgetariumb9.repository.UserRepository;
////
////@Service
////public class MailingService {
////  private final JavaMailSender mailSender;
////  private final UserRepository userRepository;
////  private final MailingRepository mailingRepository;
////  private final EntityManager entityManager;
////  private final ResourceLoader resourceLoader;
////  private final SpringTemplateEngine templateEngine;
////
////
////  @Autowired
////  public MailingService(JavaMailSender mailSender, UserRepository userRepository,
////      MailingRepository mailingRepository, EntityManager entityManager,
////      ResourceLoader resourceLoader, SpringTemplateEngine templateEngine) {
////    this.mailSender = mailSender;
////    this.userRepository = userRepository;
////    this.mailingRepository = mailingRepository;
////    this.entityManager = entityManager;
////    this.resourceLoader = resourceLoader;
////    this.templateEngine = templateEngine;
////  }
////
////  private String readFile(String filePath) {
////    try {
////      byte[] fileBytes = Files.readAllBytes(Path.of(filePath));
////      return new String(fileBytes, StandardCharsets.UTF_8);
////    } catch (IOException e) {
////      e.printStackTrace();
////    }
////    return null;
////  }
////
////
////  public void sendEmail(String to,MailingRequest mailingRequest)
////      throws MessagingException, IOException {
////
////    MimeMessage message = mailSender.createMimeMessage();
////    MimeMessageHelper helper = new MimeMessageHelper(message, true);
////    helper.setTo(to);
////    helper.setSubject(mailingRequest.getTitle());
////
////    Context context = new Context();
////    context.setVariable("description",mailingRequest.getDescription());
////    context.setVariable("image",mailingRequest.getImage());
////    context.setVariable("finishDate",mailingRequest.getImage());
////    Resource resource = resourceLoader.getResource("classpath:templates/email.html");
////
////
////
////    Path filePath = resource.getFile().toPath();
////    String htmlContent = readFile(String.valueOf(filePath));
////
////    String processedTemplate = templateEngine.process(htmlContent, context);
////
////    helper.setText(processedTemplate,true); // Set HTML content
////
////    mailSender.send(message);
////  }
////
////
////  public void sendMailing(MailingRequest mailingRequest)
////      throws MessagingException, IOException {
////    List<String> emails = getUsers();
////
////    Mailing mailing = new Mailing();
////    mailing.setTitle(mailingRequest.getTitle());
////    mailing.setDescription(mailingRequest.getDescription());
////    mailing.setStartDate(ZonedDateTime.now());
////    mailing.setFinishDate(mailingRequest.getFinishDate());
////    mailing.setImage(mailingRequest.getImage());
////    mailingRepository.save(mailing);
////
////      for (String email : emails) {
////        User user = getUserByEmail(email);
////        if (user!= null && user.isSubscription()){
////          sendEmail(email,mailingRequest);
////        }
////      }
////      System.out.println("Send Mailing");
////    SimpleResponse.builder()
////        .status(HttpStatus.OK)
////        .message("Successfully")
////        .build();
////  }
////  private List<String> getUsers() {
////    List<User> users = userRepository.findAll();
////    return users.stream()
////        .map(User::getEmail)
////        .collect(Collectors.toList());
////  }
////  public User getUserByEmail(String email) {
////    String jpql = "SELECT u FROM User u WHERE u.email = :email";
////    TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
////    query.setParameter("email", email);
////    List<User> users = query.getResultList();
////    return users.isEmpty() ? null : users.get(0);
////  }
////
////}
//package peaksoft.house.gadgetariumb9.service;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.TypedQuery;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.http.HttpStatus;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//import org.thymeleaf.context.Context;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import peaksoft.house.gadgetariumb9.dto.request.MailingRequest;
//import peaksoft.house.gadgetariumb9.dto.response.SimpleResponse;
//import peaksoft.house.gadgetariumb9.entities.Mailing;
//import peaksoft.house.gadgetariumb9.entities.User;
//import peaksoft.house.gadgetariumb9.repository.MailingRepository;
//import peaksoft.house.gadgetariumb9.repository.UserRepository;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.time.ZonedDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class MailingService {
//
//  private final JavaMailSender mailSender;
//  private final UserRepository userRepository;
//  private final MailingRepository mailingRepository;
//  private final EntityManager entityManager;
//  private final ResourceLoader resourceLoader;
//  private final SpringTemplateEngine templateEngine;
//
//  @Autowired
//  public MailingService(JavaMailSender mailSender, UserRepository userRepository,
//      MailingRepository mailingRepository, EntityManager entityManager,
//      ResourceLoader resourceLoader, SpringTemplateEngine templateEngine) {
//    this.mailSender = mailSender;
//    this.userRepository = userRepository;
//    this.mailingRepository = mailingRepository;
//    this.entityManager = entityManager;
//    this.resourceLoader = resourceLoader;
//    this.templateEngine = templateEngine;
//  }
//
//
//  private String readFile(String filePath) throws IOException {
//    byte[] fileBytes = Files.readAllBytes(Path.of(filePath));
//    return new String(fileBytes, StandardCharsets.UTF_8);
//  }
//
//  public SimpleResponse sendEmail(String to, MailingRequest mailingRequest)
//      throws IOException, MessagingException {
//    MimeMessage message = mailSender.createMimeMessage();
//    MimeMessageHelper helper = new MimeMessageHelper(message, true);
//    helper.setTo(to);
//    helper.setSubject(mailingRequest.getTitle());
//
//    Context context = new Context();
//    context.setVariable("description", mailingRequest.getDescription());
//    context.setVariable("image", mailingRequest.getImage());
//    context.setVariable("finishDate", mailingRequest.getFinishDate());
//
//    Resource resource = resourceLoader.getResource("classpath:templates/email.html");
//    String htmlContent = readFile(resource.getFile().getPath());
//    String processedTemplate = templateEngine.process(htmlContent, context);
//
//    helper.setText(processedTemplate, true); // Set HTML content
//    mailSender.send(message);
//
//    return SimpleResponse.builder()
//        .status(HttpStatus.OK)
//        .message("Successfully")
//        .build();
//  }
//
//  public SimpleResponse sendMailing(MailingRequest mailingRequest)
//      throws IOException, MessagingException {
//    List<String> emails = getUsers();
//
//    Mailing mailing = new Mailing();
//    mailing.setTitle(mailingRequest.getTitle());
//    mailing.setDescription(mailingRequest.getDescription());
//    mailing.setStartDate(ZonedDateTime.now());
//    mailing.setFinishDate(mailingRequest.getFinishDate());
//    mailing.setImage(mailingRequest.getImage());
//    mailingRepository.save(mailing);
//
//    for (String email : emails) {
//      User user = getUserByEmail(email);
//      if (user != null && user.isSubscription()) {
//        sendEmail(email, mailingRequest);
//      }
//    }
//
//    return SimpleResponse.builder()
//        .status(HttpStatus.OK)
//        .message("Successfully")
//        .build();
//  }
//
//  private List<String> getUsers() {
//    List<User> users = userRepository.findAll();
//    return users.stream()
//        .map(User::getEmail)
//        .collect(Collectors.toList());
//  }
//
//  public User getUserByEmail(String email) {
//    String jpql = "SELECT u FROM User u WHERE u.email = :email";
//    TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
//    query.setParameter("email", email);
//    List<User> users = query.getResultList();
//    return users.isEmpty() ? null : users.get(0);
//  }
//}






//
//package peaksoft.house.gadgetariumb9.service;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.TypedQuery;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.time.ZonedDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.http.HttpStatus;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//import org.springframework.util.ResourceUtils;
//import org.thymeleaf.context.Context;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import peaksoft.house.gadgetariumb9.dto.request.MailingRequest;
//import peaksoft.house.gadgetariumb9.dto.response.SimpleResponse;
//import peaksoft.house.gadgetariumb9.entities.Mailing;
//import peaksoft.house.gadgetariumb9.entities.User;
//import peaksoft.house.gadgetariumb9.repository.MailingRepository;
//import peaksoft.house.gadgetariumb9.repository.UserRepository;
//
//@Service
//public class MailingService {
//  private final JavaMailSender mailSender;
//  private final UserRepository userRepository;
//  private final MailingRepository mailingRepository;
//  private final EntityManager entityManager;
//  private final ResourceLoader resourceLoader;
//  private final SpringTemplateEngine templateEngine;
//
//
//  @Autowired
//  public MailingService(JavaMailSender mailSender, UserRepository userRepository,
//      MailingRepository mailingRepository, EntityManager entityManager,
//      ResourceLoader resourceLoader, SpringTemplateEngine templateEngine) {
//    this.mailSender = mailSender;
//    this.userRepository = userRepository;
//    this.mailingRepository = mailingRepository;
//    this.entityManager = entityManager;
//    this.resourceLoader = resourceLoader;
//    this.templateEngine = templateEngine;
//  }
//
//  private String readFile(String filePath) {
//    try {
//      return Files.readString(Path.of(ResourceUtils.getFile(filePath).toURI()));
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    return null;
//  }
//
//
//  public void sendEmail(String to, MailingRequest mailingRequest)
//      throws MessagingException, IOException {
//
//    MimeMessage message = mailSender.createMimeMessage();
//    MimeMessageHelper helper = new MimeMessageHelper(message, true);
//    helper.setTo(to);
//    helper.setSubject(mailingRequest.getTitle());
//
//    Context context = new Context();
//    context.setVariable("description", mailingRequest.getDescription());
//    context.setVariable("image", mailingRequest.getImage());
//    context.setVariable("finishDate", mailingRequest.getFinishDate());
//    Resource resource = resourceLoader.getResource("classpath:templates/email.html");
//
//    Path filePath = ResourceUtils.getFile(resource.getURI()).toPath();
//    String htmlContent = readFile(String.valueOf(filePath));
//
//    String processedTemplate = templateEngine.process(htmlContent, context);
//
//    helper.setText(htmlContent, true); // Set HTML content
//
//    mailSender.send(message);
//  }
//
//
////
////
////  public void sendEmail(String to, MailingRequest mailingRequest) throws MessagingException, IOException {
////    MimeMessage message = mailSender.createMimeMessage();
////    MimeMessageHelper helper = new MimeMessageHelper(message, true);
////    helper.setTo(to);
////    helper.setSubject(mailingRequest.getTitle());
////
////    Context context = new Context();
////    context.setVariable("description", mailingRequest.getDescription());
////    context.setVariable("image", mailingRequest.getImage());
////    context.setVariable("finishDate", mailingRequest.getFinishDate());
////
////    Resource resource = resourceLoader.getResource("classpath:templates/email.html");
////    String htmlContent = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
////    String processedTemplate = templateEngine.process(htmlContent, context);
////
////    helper.setText(processedTemplate, true); // Set HTML content
////
////    mailSender.send(message);
////  }
//
//
//  public void sendMailing(MailingRequest mailingRequest)
//      throws MessagingException, IOException {
//    List<String> emails = getUsers();
//
//    Mailing mailing = new Mailing();
//    mailing.setTitle(mailingRequest.getTitle());
//    mailing.setDescription(mailingRequest.getDescription());
//    mailing.setStartDate(ZonedDateTime.now());
//    mailing.setFinishDate(mailingRequest.getFinishDate());
//    mailing.setImage(mailingRequest.getImage());
//    mailingRepository.save(mailing);
//
//    for (String email : emails) {
//      User user = getUserByEmail(email);
//      if (user != null && user.isSubscription()) {
//        sendEmail(email, mailingRequest);
//      }
//    }
//    System.out.println("Send Mailing");
//  }
//
//  private List<String> getUsers() {
//    List<User> users = userRepository.findAll();
//    return users.stream()
//        .map(User::getEmail)
//        .collect(Collectors.toList());
//  }
//
//  public User getUserByEmail(String email) {
//    String jpql = "SELECT u FROM User u WHERE u.email = :email";
//    TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
//    query.setParameter("email", email);
//    List<User> users = query.getResultList();
//    return users.isEmpty() ? null : users.get(0);
//  }
//}
















package peaksoft.house.gadgetariumb9.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import peaksoft.house.gadgetariumb9.dto.request.MailingRequest;
import peaksoft.house.gadgetariumb9.dto.response.SimpleResponse;
import peaksoft.house.gadgetariumb9.entities.Mailing;
import peaksoft.house.gadgetariumb9.entities.User;
import peaksoft.house.gadgetariumb9.repository.MailingRepository;
import peaksoft.house.gadgetariumb9.repository.UserRepository;

@Service
public class MailingService {
  private final JavaMailSender mailSender;
  private final UserRepository userRepository;
  private final MailingRepository mailingRepository;
  private final EntityManager entityManager;
  private final ResourceLoader resourceLoader;
  private final SpringTemplateEngine templateEngine;


  @Autowired
  public MailingService(JavaMailSender mailSender, UserRepository userRepository,
      MailingRepository mailingRepository, EntityManager entityManager,
      ResourceLoader resourceLoader, SpringTemplateEngine templateEngine) {
    this.mailSender = mailSender;
    this.userRepository = userRepository;
    this.mailingRepository = mailingRepository;
    this.entityManager = entityManager;
    this.resourceLoader = resourceLoader;
    this.templateEngine = templateEngine;
  }

  private String readFile(String filePath) {
    try {
      return Files.readString(Path.of(ResourceUtils.getFile(filePath).toURI()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }


  public void sendEmail(String to, MailingRequest mailingRequest)
      throws MessagingException, IOException {

    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setTo(to);
    helper.setSubject(mailingRequest.getTitle());

    Context context = new Context();
    context.setVariable("description", mailingRequest.getDescription());
    context.setVariable("image", mailingRequest.getImage());
    context.setVariable("finishDate", mailingRequest.getFinishDate());
    Resource resource = resourceLoader.getResource("classpath:templates/email.html");

    Path filePath = ResourceUtils.getFile(resource.getURI()).toPath();
    String htmlContent = readFile(String.valueOf(filePath));

//    String processedTemplate = templateEngine.process(htmlContent, context);

    helper.setText(htmlContent, true); // Set HTML content

    mailSender.send(message);
  }


  public void sendMailing(MailingRequest mailingRequest)
      throws MessagingException, IOException {
    List<String> emails = getUsers();

    Mailing mailing = new Mailing();
    mailing.setTitle(mailingRequest.getTitle());
    mailing.setDescription(mailingRequest.getDescription());
    mailing.setStartDate(ZonedDateTime.now());
    mailing.setFinishDate(mailingRequest.getFinishDate());
    mailing.setImage(mailingRequest.getImage());
    mailingRepository.save(mailing);

    for (String email : emails) {
      User user = getUserByEmail(email);
      if (user != null && user.isSubscription()) {
        sendEmail(email, mailingRequest);
      }
    }
    System.out.println("Send Mailing");
  }



  private List<String> getUsers() {
    List<User> users = userRepository.findAll();
    return users.stream()
        .map(User::getEmail)
        .collect(Collectors.toList());
  }

  public User getUserByEmail(String email) {
    String jpql = "SELECT u FROM User u WHERE u.email = :email";
    TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
    query.setParameter("email", email);
    List<User> users = query.getResultList();
    return users.isEmpty() ? null : users.get(0);
  }

}
