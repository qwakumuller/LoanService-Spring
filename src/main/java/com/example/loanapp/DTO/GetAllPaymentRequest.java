package com.example.loanapp.DTO;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class GetAllPaymentRequest {
    private ObjectId userId;
}
