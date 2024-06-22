package com.health.pramod.HealthCare.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "messages")
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @ManyToOne()
    @JoinColumn(name = "patient_id",nullable = false)
    private Patients patients;
    private Integer  recipient_id;

    private Integer timestamp;
    @Lob
    @Column(name = "large_text_field",columnDefinition = "TEXT")
    private String message_content;
    private String message_type;
    private boolean is_read;
    private Integer session_id;
}
