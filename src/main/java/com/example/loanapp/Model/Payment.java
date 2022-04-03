package com.example.loanapp.Model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {
    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId paymentId;
    @NonNull
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId userId;
    @NonNull
    private float amountPaid;
    @NonNull
    private Date datePaid;

}
