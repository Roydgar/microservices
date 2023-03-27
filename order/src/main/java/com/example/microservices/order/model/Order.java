package com.example.microservices.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "customer_order")
public class Order {

    @Id
    @Column("id")
    private UUID id;
    @Column("customer_id")
    private UUID customerId;
    @Column("created_at")
    @CreatedDate
    private Instant createdAt;
    @Column("updated_at")
    @LastModifiedDate
    private Instant updatedAt;
    @Column("version")
    @Version
    private int version;
    @Column("status")
    private Status status;
    @Column("cancel_reason")
    private CancelReason cancelReason;

    public enum Status {
        CREATED,
        CONFIRMED,
        CANCELLED
    }

    public enum CancelReason {
        CUSTOMER_VERIFICATION_FAILED
    }
}
