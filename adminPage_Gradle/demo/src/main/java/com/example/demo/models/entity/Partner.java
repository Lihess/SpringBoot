package com.example.demo.models.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Builder // User.bulider().account(accuont).status(status).bulid() 와 같이 사용하여 객체생성! 이럴 경우, 생성자가 많아지는 문제를 해결할 수 있다.
@Accessors(chain = true) // user.setAccut().setStatus()..와 같이 한번에 데이터 변경 가능
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"itemList", "category"})
@EntityListeners(AuditingEntityListener.class)
public class Partner{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    private String status;

    private String address;

    private String callCenter;

    private String partnerNumber;

    private String businessNumber;

    private String ceoName;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    @CreatedDate
    private LocalDateTime createdAt;
    
    @CreatedBy
    private String createdBy;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
    
    @LastModifiedBy // 설정한 AdminServer로 반환됨
    private String updatedBy;

    @ManyToOne
    private Category category;

    //partner : item : 1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partner")
    private List<Item> itemList;
}