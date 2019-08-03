package com.bhaikacms.scheduler.services.impl;

import com.bhaikacms.scheduler.entity.User;
import com.bhaikacms.scheduler.repository.DeleteEmailNotificationRepository;
import com.bhaikacms.scheduler.repository.DeleteProgramServiceRepository;
import com.bhaikacms.scheduler.services.DeleteProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class DeleteProgramServiceImpl implements DeleteProgramService {

    @Autowired
    DeleteProgramServiceRepository deleteProgramServiceRepository;

    @Autowired
    DeleteEmailNotificationRepository deleteEmailNotificationRepository;
    @Override
    public String deleteByExpDate() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);

        String msg = "Program data not available for deletion.";
        List<User> user = (List<User>) deleteEmailNotificationRepository.findByExpDate(timestamp);
        System.out.println("size  : "+user.size());
        if(user.size()>0){
            //send deleted record email notification email
            deleteProgramServiceRepository.deleteByExpDate(timestamp);
            System.out.println("****** : " + user.get(0).getEmail());
            System.out.println("****** : " + user.get(0).getpId());
            System.out.println("======= : " + user.get(0).getProgramName());
            System.out.println("======= : " + user.get(0).getExpDate());
            System.out.println("======= : " + user.get(0).getStartDate());
            msg = "Program data has been deleted successfully.";
        }
        return msg;
    }

    @Override
    public boolean sendEmail(List<User> data) {
        final String username = "devicemartnew@gmail.com";
        final String password = "newdevicemart1@";
        //  String fromEmailAddr = "cmpnvijay@gmail.com";
        String fromEmailAddr = "noreply@devicemart.com";
        Properties fMailServerConfig = new Properties();
        fMailServerConfig.put("mail.smtp.auth", "true");
        fMailServerConfig.put("mail.smtp.starttls.enable", "true");
        fMailServerConfig.put("mail.smtp.host", "smtp.gmail.com");
        fMailServerConfig.put("mail.smtp.port", "587");
        //fMailServerConfig.put("mail.smtp.user", "cmpnvijay@gmail.com");
        // fMailServerConfig.put("mail.smtp.password", "coviammac1234");

        Session session = Session.getInstance(fMailServerConfig,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        MimeMessage message = new MimeMessage(session);
        try {

            //=========================
            for(User user : data) {


                //=========================
                message.setFrom(new InternetAddress(fromEmailAddr));
                message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(user.getEmail()));

                sendEmailDTO.setOrderId(sendEmailDTO.getOrderId());

                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText("This is message body");

                // Create a multipar message
                Multipart multipart = new MimeMultipart();

                // Set text message part
                multipart.addBodyPart(messageBodyPart);

                // Part two is attachment
                messageBodyPart = new MimeBodyPart();

                //======================================
                //<a href="https://www.w3schools.com/html/">Visit our HTML tutorial</a></p>
                String body = "Hello Dear, <br><br> Thank you for your order. We’ll send a confirmation when your order ships."
                        + "If you would like to view the status of your order or make any changes to it,"
                        + "please visit Your Orders on <a href='http://172.16.20.83:8080/products/' >Devicemart.com</a> ";

                java.util.Date date = new java.util.Date();
                java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());

                body = body + "<br><p> <b><u>Order Item Details:</u></b>";

                // body = body+ "Order Id: "+ sendEmailDTO.getOrderId()+ " on " + timestamp + "";

                body = body + "<br>";


                int itemNumber = 1;

                String htmlCode = "<table border='1' width='100%'>";
                htmlCode = htmlCode + "<col style='width:10%'><col style='width:25%'><col style='width:25%'>"
                        + "<col style='width:15%'><col style='width:10%'><col style='width:15%'>";
                htmlCode = htmlCode
                        + "<thead> <tr style='font-weight:bold; color:blue ; align: middle'>"
                        + "<th ><input readonly='readonly' id='SNo' type='text' value='Serial No'></th>"
                        + "<th ><input readonly='readonly' width='230' height='200' id='Image' type='text' value='Product Image'></th>"
                        + "<th ><input readonly='readonly' id='ProductNumber' type='text' value='Product Name'></th>"
                        + "<th ><input readonly='readonly' id='UnitPrice' type='text' value='Unit Price'></th>"
                        + "<th ><input readonly='readonly' id='Quantity' type='text' value='Quantity'></th>"
                        + "<th ><input readonly='readonly' id='cost' type='text' value='Total Cost'></th>"
                        + "</tr></thead> <tbody>";

                OrderTable orderTable = orderRepository.findOne(sendEmailDTO.getOrderId());
                if (orderTable != null) {
                    List<OrderDetailsTable> orderDetailsTableList = orderDetailsRepository.findByOrderId(orderTable);
                    for (OrderDetailsTable orderDetailsTable1 : orderDetailsTableList) {
                        ProductDetailForCartDTO productDetailForCartDTO = new ProductDetailForCartDTO();
                        productDetailForCartDTO = restTemplate.getForObject(productServiceUrl + "/product/getProductDetailForCart/" + orderDetailsTable1.getProductId(), ProductDetailForCartDTO.class);

                        System.out.println("productServiceUrl : " + productServiceUrl + "/product/getProductDetailForCart/" + orderDetailsTable1.getProductId());

                        String productName = "" + productDetailForCartDTO.getProductName();
                        String unitPrice = "" + orderDetailsTable1.getOrderPrice();
                        String quantity = "" + orderDetailsTable1.getQuantity();
                        String price = "" + orderDetailsTable1.getTotalAmount();
                        String image_url = "" + productDetailForCartDTO.getProductImageURL();
                        String product_url = "http://172.16.20.83:8080" + "/products/getProductDetails/" + orderDetailsTable1.getProductId();
                        System.out.println("url : " + product_url);
                        //message = message + "\n" + itemNumber++ + ". " + "Product Name : " +productDetailForCartDTO.getProductName() + "\n";
                        //  message = message + "Unit Price :" +orderDetailsTable1.getOrderPrice() + "\n";
                        //  message = message + "Quantity :" +orderDetailsTable1.getQuantity() + "\n";
                        // message = message + "Price :"+orderDetailsTable1.getTotalAmount() + "\n";

                        htmlCode = htmlCode + "<tr><td><span readonly='readonly' id= type='text' value='" + (itemNumber) + "'>" + (itemNumber) + "</span></td>"
                                + "<td ><img src='" + image_url + "' width='230' height='200' id='img_" + itemNumber + "' value='" + productName + "'></img></td>"
                                + "<td><a href='" + product_url + "' id='product_" + itemNumber + "' value='" + productName + "'> " + productName + "</a></td>"
                                + "<td><span readonly='readonly' id='unitPrice_" + itemNumber + "' type='text' value='" + unitPrice + "'>" + unitPrice + " </span></td>"
                                + "<td><span readonly='readonly' id='quentity_" + itemNumber + "' type='text' value='" + quantity + "'>" + quantity + "</span></td>"
                                + "<td><span readonly='readonly' id='price_" + itemNumber + "' type='text' value='" + price + "'>" + price + "</span></td>"
                                + "</tr>";
                        itemNumber++;
                    }

                    htmlCode = htmlCode + "</tbody></table>";
                    body = body + "" + htmlCode;
                    body = body + "<br>";
                }

                body = body + "<br>Thank you for choosing Device Mart.";
                body = body + "<br><p> We hope to see you again soon.<p>";
                body = body + "<br><br><p>Thanks<br>-Team Device Mart</p>";
                //=====================================
                if (itemNumber > 1)
                    message.setSubject("Your DeviceMart.com order for Order Id: " + sendEmailDTO.getOrderId() + " and more(s) items successful.");
                else
                    message.setSubject("Your DeviceMart.com order for Order Id: " + sendEmailDTO.getOrderId() + " successful.");
                message.setText(body, "UTF-8", "html");
                Transport transport = session.getTransport("smtps");
                transport.send(message);
                System.out.println("Email sent to recipients. ");
            }

        } catch (MessagingException ex) {
            ex.printStackTrace();
            return false;
        }
        return  true;
    }

}
