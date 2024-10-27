package com.sendmail.controller;

import com.sendmail.request.CommissionMailRequest;
import com.sendmail.request.SendMailRequest;
import com.sendmail.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.context.Context;

@Controller
@AllArgsConstructor
public class CommissionController {

    private EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody CommissionMailRequest request) {

        Context context = getContext(request);

        // Gửi đến cho ai
        String[] to = new String[]{"nghhai1196@gmail.com"};
        String[] cc = new String[]{"hai.nh2@aseansc.com.vn"};

        // Tiêu đề mail
        String subject = "[ASEAN SECURITIES] THÔNG BÁO XÁC NHẬN DOANH SỐ VÀ HOA HỒNG";
        SendMailRequest sendMailRequest = SendMailRequest.builder()
                .to(to)
                .cc(cc)
                .subject(subject)
                .build();

        emailService.sendEmail(sendMailRequest, "confirm-commission", context);
        return ResponseEntity.ok("Success");
    }

    private static Context getContext(CommissionMailRequest request) {
        Context context = new Context();
        context.setVariable("month", request.getMonth());
        context.setVariable("year", request.getYear());
        context.setVariable("partner", request.getPartner());
        context.setVariable("code", request.getCode());
        context.setVariable("position", request.getPosition());
        context.setVariable("totalCommission", request.getTotalCommission());
        context.setVariable("personalIncomeTax", request.getPersonalIncomeTax());
        context.setVariable("totalCommissionAfterTax", request.getTotalCommissionAfterTax());
        return context;
    }
}
