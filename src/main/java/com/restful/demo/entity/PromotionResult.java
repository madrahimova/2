package com.restful.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "promotion_results")
public class PromotionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "participant_id", referencedColumnName = "id")
    private Participant winner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prize_id", referencedColumnName = "id")
    private Prize prize;

    public PromotionResult() {
    }

    public PromotionResult(Participant participant, Prize prize) {
        this.winner = participant;
        this.prize = prize;
    }

    public Participant getWinner() {
        return winner;
    }

    public void setWinner(Participant winner) {
        this.winner = winner;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }
}
