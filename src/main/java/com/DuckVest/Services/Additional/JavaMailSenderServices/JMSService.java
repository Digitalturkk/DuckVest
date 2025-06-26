package com.DuckVest.Services.Additional.JavaMailSenderServices;

import com.DuckVest.DTOs.OrderDTO;
import com.DuckVest.Models.BankTransaction;
import com.DuckVest.Models.Investor;
import com.DuckVest.Models.Orders;
import com.DuckVest.Services.OrdersServices.OrderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import static com.DuckVest.Services.Additional.DuckVestorServices.DuckVestorTipsService.getRandomAdvice;

@Service
public class JMSService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;
    @Autowired
    private JavaMailSenderImpl mailSender;

    public void sendOrderToMail(Orders order) throws MessagingException {
        Investor investor = order.getInvestor();
        String toEmail = investor.getEmail();

        // 1. Создаем Thymeleaf контекст
        Context context = new Context();
        context.setVariable("orderDTO", order);

        // 2. Передаём tip отдельно или внутрь orderDTO
        String tip = "Tip from DuckVestor: " + getRandomAdvice();
        context.setVariable("tip", tip);

        try {
            byte[] imageBytes = Files.readAllBytes(Paths.get("src/main/resources/static/images/logo1.jpeg"));
            String base64Logo = Base64.getEncoder().encodeToString(imageBytes);
            context.setVariable("logoBase64", base64Logo);
        } catch (IOException e) {
            e.printStackTrace(); // или логируй
            context.setVariable("logoBase64", ""); // подстраховка, чтобы не упало
        }

        // 3. Рендерим HTML-шаблон
        String htmlContent = templateEngine.process("Order", context);

        // 4. Отправляем как HTML-письмо
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(toEmail);
        helper.setSubject(order.getOrderType() + " Order Confirmation - " + order.getStock().getCompanyName());
        helper.setText(htmlContent, true); // true = HTML

        mailSender.send(message);
    }

    /*public void sendOrderToMail(Orders order) {
        Investor investor = order.getInvestor();

        String toMail = investor.getEmail();
        String subject = order.getOrderType() + " Order Confirmation - " + order.getStock().getCompanyName();
        String body = order.getOrderType() + " Order Confirmation\n" +
                "Order ID: " + order.getId() + "\n" +
                "Stock: " + order.getStock().getCompanyName() + "\n" +
                "Quantity: " + order.getQuantity() + "\n" +
                "Price per Stock: " + order.getStockPrice() + "\n" +
                "Total Price: " + order.getTotalPrice() + "\n" +
                "Broker Fee: " + order.getBrokerFee() + "\n" +
                "Order Status: " + order.getOrderStatus() + "\n" +
                "Order Message: " + order.getOrderMessage() + "\n" +
                "Tip from DuckVestor: " + getRandomAdvice();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toMail);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);

        javaMailSender.send(mailMessage);
    }*/

    public void sendBankTransactionToMail(BankTransaction bankTransaction) {
        Investor investor = bankTransaction.getToPortfolio().getInvestor();
        String toMail = investor.getEmail();
        String subject = "Bank Transaction Confirmation - " + bankTransaction.getType();
        String body = "Bank Transaction Confirmation\n" +
                "Transaction ID: " + bankTransaction.getId() + "\n" +
                "Type: " + bankTransaction.getType() + "\n" +
                "Amount: " + bankTransaction.getAmount() + "\n" +
                "Description: " + bankTransaction.getDescription() + "\n" +
                "Date: " + bankTransaction.getTimestamp();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toMail);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);

        javaMailSender.send(mailMessage);
    }
}
