package com.example.loanapp.Model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoanRequest {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId loanRequestId;
    @NonNull
    private ObjectId userId;
    @NonNull
    private float loanAmount;
    @NonNull
    private String reason;
}
