package peaksoft.house.gadgetariumb9.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.BodyPart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import peaksoft.house.gadgetariumb9.dto.request.MailingRequest;
import peaksoft.house.gadgetariumb9.entities.Mailing;
import peaksoft.house.gadgetariumb9.entities.User;
import peaksoft.house.gadgetariumb9.repository.MailingRepository;
import peaksoft.house.gadgetariumb9.repository.UserRepository;
import peaksoft.house.gadgetariumb9.service.MailingService;

@Service
@RequiredArgsConstructor
public class MailingPart2 implements MailingService {

  public static final String EMAIL_TEMPLATE = "emailtemplate";
  public static final String TEXT_HTML_ENCONDING = "text/html";
  private final JavaMailSender emailSender;
  private final UserRepository userRepository;
  private final MailingRepository mailingRepository;
  private final EntityManager entityManager;
  private final TemplateEngine templateEngine;
  public static final String UTF_8_ENCODING = "UTF-8";

  @Override
  @Async
  public void sendHtmlEmail(MailingRequest mailingRequest) {
    List<String>emails = getUsers();
    try {

      Mailing mailing = Mailing.builder()
          .title(mailingRequest.getName())
          .description(mailingRequest.getDescription())
          .startDate(mailingRequest.getStartDate())
          .finishDate(mailingRequest.getFinishDate())
          .image(mailingRequest.getImage())
          .build();

      mailingRepository.save(mailing);
      //
//      Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
//      configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates")); // Укажите путь к папке с шаблоном HTML-файла
//      Template template = configuration.getTemplate("emailtemplate.html");
//
//      Map<String, Object> data = new HashMap<>();
//      data.put("imageUrl", mailingRequest.getImage());
//
//      FileWriter fileWriter = new FileWriter("templates/emailtemplate.html"); // Укажите путь для сохранения HTML-файла
//      template.process(data, fileWriter);
//
//      fileWriter.close();
      //
      Context context = new Context();
      context.setVariable("description",mailingRequest.getDescription());
      context.setVariable("image",mailingRequest.getImage());
      context.setVariable("startDate",mailingRequest.getStartDate());
      context.setVariable("finishDate",mailingRequest.getFinishDate());
      String text = templateEngine.process(EMAIL_TEMPLATE, context);
      MimeMessage message = getMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
      helper.setPriority(1);
      helper.setSubject("NEWS");
      helper.setFrom("calumbekcaatbekov@gmail.com");
      for (String email : emails) {
        User user = getUserByEmail(email);
        if (user != null && user.isSubscription()) {
          helper.setTo(email);
        }
      }
      helper.setText(text, true);



      emailSender.send(message);

    } catch (Exception exception) {
      System.out.println(exception.getMessage());
      throw new RuntimeException(exception.getMessage());
    }
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

  private MimeMessage getMimeMessage() {
    return emailSender.createMimeMessage();
  }
}
