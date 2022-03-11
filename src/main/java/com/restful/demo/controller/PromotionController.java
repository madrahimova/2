package com.restful.demo.controller;

import com.restful.demo.dto.PromotionDto;
import com.restful.demo.entity.Participant;
import com.restful.demo.entity.Prize;
import com.restful.demo.entity.Promotion;
import com.restful.demo.service.PromotionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/promo")
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping
    public List<PromotionDto> getPromotions() {
        return promotionService.getAllPromotions();
    }

    @GetMapping("/{id}")
    public Promotion getPromotionById(@PathVariable long id) {
        return promotionService.getPromotionById(id);
    }

    @PostMapping
    public ResponseEntity<Long> createPromotion(@RequestBody Promotion promotion) {
        if (promotion.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(promotionService.createPromotion(promotion), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePromotion(@RequestBody Promotion promotion,
                                           @PathVariable long id) {
        if (promotion.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        promotionService.updatePromotion(id, promotion);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePromotion(@PathVariable long id) {
        promotionService.deletePromotionById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/participant")
    public long addParticipant(@PathVariable long id, @RequestBody Participant participant) {
        return promotionService.addParticipant(id, participant);
    }

    @DeleteMapping("/{promoId}/participant/{participantId}")
    public ResponseEntity<String> deleteParticipant(@PathVariable long promoId, @PathVariable long participantId) {
        promotionService.deleteParticipant(promoId, participantId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/prize")
    public long addPrize(@PathVariable long id, @RequestBody Prize prize) {
        return promotionService.addPrize(id, prize);
    }

    @DeleteMapping("/{promoId}/prize/{prizeId}")
    public ResponseEntity<String> deletePrize(@PathVariable long promoId, @PathVariable long prizeId) {
        promotionService.deletePrize(promoId, prizeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/raffle")
    public @ResponseBody ResponseEntity<Object> makeRaffle(@PathVariable long id) {
        Promotion promotion = promotionService.getPromotionById(id);
        if (promotion.getPrizes().size() != promotion.getParticipants().size()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(promotionService.getPromotionResults(promotion), HttpStatus.OK);
    }
}
