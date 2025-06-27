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

        Context context = new Context();
        context.setVariable("orderDTO", order);

        String tip = "Tip from DuckVestor: " + getRandomAdvice();
        context.setVariable("tip", tip);

        String htmlContent = templateEngine.process("Order", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(toEmail);
        helper.setSubject(order.getOrderType() + " Order Confirmation - " + order.getStock().getCompanyName());
        helper.setText(htmlContent, true); // true = HTML

        mailSender.send(message);
    }

    public void sendBankTransactionToMail(BankTransaction transaction) throws MessagingException {
        Investor investor = transaction.getToPortfolio().getInvestor();


        Context context = new Context();
        context.setVariable("transaction", transaction);
        context.setVariable("tip1", "Use it wisely!");
        context.setVariable("tip2", getRandomAdvice());

        String htmlContent = templateEngine.process("bank-transaction", context);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        try {
            helper.setTo(investor.getEmail());
            helper.setSubject("Bank Transaction Confirmation - " + transaction.getType());
            helper.setText(htmlContent, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
