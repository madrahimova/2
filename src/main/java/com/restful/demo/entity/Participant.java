package com.restful.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "participants")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "part_promotion_id")
    @JsonIgnore
    private long partPromotionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_promotion_id", insertable = false, updatable = false)
    @JsonIgnore
    private Promotion promotion;

    @OneToOne(mappedBy = "winner")
    @JsonIgnore
    private PromotionResult promotionResult;

    public Participant() {
    }

    public Participant(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public long getPartPromotionId() {
        return partPromotionId;
    }

    public void setPartPromotionId(long partPromotionId) {
        this.partPromotionId = partPromotionId;
    }

    public PromotionResult getPromotionResult() {
        return promotionResult;
    }

    public void setPromotionResult(PromotionResult promotionResult) {
        this.promotionResult = promotionResult;
    }
}
