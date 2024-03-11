package com.bank.bankservice.common.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GetTransactionListRequest {
    private String rib;
    private String dateTo;
    private String dateFrom;
}
