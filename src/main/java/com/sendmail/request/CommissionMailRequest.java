package com.sendmail.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommissionMailRequest {

    // Tháng
    private String month;

    // Năm
    private String year;

    // Tên cộng tác viên
    private String partner;

    // Mã ID
    private String code;

    // Chức vụ
    private String position;

    // Tổng hoa hồng bạn được nhận trong kỳ
    private String totalCommission;

    // Thuế TNCN (theo quy định)
    private String personalIncomeTax;

    // Tổng hoa hồng thực nhận sau thuế
    private String totalCommissionAfterTax;
}
