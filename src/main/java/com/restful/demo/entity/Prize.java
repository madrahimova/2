package com.restful.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "prizes")
public class Prize {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "prize_promotion_id")
    @JsonIgnore
    private long prizePromotionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prize_promotion_id", insertable = false, updatable = false)
    @JsonIgnore
    private Promotion promotion;

    @OneToOne(mappedBy = "prize")
    @JsonIgnore
    private PromotionResult promotionResult;

    public Prize() {
    }

    public Prize(long id, String description) {
        this.id = id;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public long getPrizePromotionId() {
        return prizePromotionId;
    }

    public void setPrizePromotionId(long prizePromotionId) {
        this.prizePromotionId = prizePromotionId;
    }

    public PromotionResult getPromotionResult() {
        return promotionResult;
    }

    public void setPromotionResult(PromotionResult promotionResult) {
        this.promotionResult = promotionResult;
    }
}
