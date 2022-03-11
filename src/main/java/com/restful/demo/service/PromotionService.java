package com.restful.demo.service;

import com.restful.demo.dto.PromotionDto;
import com.restful.demo.entity.Participant;
import com.restful.demo.entity.Prize;
import com.restful.demo.entity.Promotion;
import com.restful.demo.entity.PromotionResult;

import java.util.List;

public interface PromotionService {
    List<PromotionDto> getAllPromotions();
    Promotion getPromotionById(long id);
    long createPromotion(Promotion promotion);
    void updatePromotion(long id, Promotion promotion);
    void deletePromotionById(long id);

    long addParticipant(long promotionId,Participant participant);
    void deleteParticipant(long promotionId, long participantId);

    long addPrize(long promotionId,Prize prize);
    void deletePrize(long promotionId, long prizeId);

    List<PromotionResult> getPromotionResults(Promotion promotion);
}
