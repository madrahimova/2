package com.restful.demo.service;

import com.restful.demo.dao.ParticipantRepository;
import com.restful.demo.dao.PrizeRepository;
import com.restful.demo.dao.PromotionRepository;
import com.restful.demo.dto.PromotionDto;
import com.restful.demo.entity.Participant;
import com.restful.demo.entity.Prize;
import com.restful.demo.entity.Promotion;
import com.restful.demo.entity.PromotionResult;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final ParticipantRepository participantRepository;
    private final PrizeRepository prizeRepository;

    public PromotionServiceImpl(PromotionRepository promotionRepository, ParticipantRepository participantRepository, PrizeRepository prizeRepository) {
        this.promotionRepository = promotionRepository;
        this.participantRepository = participantRepository;
        this.prizeRepository = prizeRepository;
    }

    @Override
    public List<PromotionDto> getAllPromotions() {
        List<Promotion> promotions = promotionRepository.findAll();
        List<PromotionDto> promotionDtoList = new ArrayList<>();
        for (Promotion p : promotions) {
            PromotionDto promotionDto = new PromotionDto(p.getId(), p.getName(), p.getDescription());
            promotionDtoList.add(promotionDto);
        }

        return promotionDtoList;
    }

    @Override
    public Promotion getPromotionById(long id) {
        return promotionRepository.findById(id).get();
    }

    @Override
    public long createPromotion(Promotion promotion) {
        return promotionRepository.save(promotion).getId();
    }

    @Override
    public void updatePromotion(long id, Promotion promotion) {
        Promotion oldProduct = promotionRepository.findById(id).get();
        promotion.setId(oldProduct.getId());
        promotionRepository.save(promotion);
    }

    @Override
    public void deletePromotionById(long id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public long addParticipant(long promotionId, Participant participant) {

        Optional<Promotion> promotion = promotionRepository.findById(promotionId);

        if (promotion.isPresent()) {

            participant.setPromotion(promotion.get());
            participant.setPartPromotionId(promotionId);

            participantRepository.save(participant);
            return participant.getId();

        } else {
            throw new NotFoundException("Promotion not found");
        }
    }

    @Override
    public void deleteParticipant(long promotionId, long participantId) {
        participantRepository.deleteById(participantId);
    }

    @Override
    public long addPrize(long promotionId, Prize prize) {
        Optional<Promotion> promotion = promotionRepository.findById(promotionId);

        if (promotion.isPresent()) {

            prize.setPromotion(promotion.get());
            prize.setPrizePromotionId(promotionId);

            prizeRepository.save(prize);
            return prize.getId();

        } else {
            throw new NotFoundException("Prize not found");
        }
    }

    @Override
    public void deletePrize(long promotionId, long prizeId) {
        prizeRepository.deleteById(prizeId);
    }

    @Override
    public List<PromotionResult> getPromotionResults(Promotion promotion) {
        List<PromotionResult> results = new ArrayList<>();
        List<Participant> participants = promotion.getParticipants();
        int i = 0;

        for (Prize prize : promotion.getPrizes()) {
            results.add(new PromotionResult(participants.get(i), prize));
            i++;
        }
        return results;
    }
}
