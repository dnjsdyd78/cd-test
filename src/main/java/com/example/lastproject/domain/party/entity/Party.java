package com.example.lastproject.domain.party.entity;

import com.example.lastproject.common.CustomException;
import com.example.lastproject.common.Timestamped;
import com.example.lastproject.common.enums.ErrorCode;
import com.example.lastproject.domain.item.entity.Item;
import com.example.lastproject.domain.party.enums.PartyStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "Party")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Party extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "market_name", nullable = false)
    private String marketName;

    @Column(name = "market_address", nullable = false)
    private String marketAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "item_unit", nullable = false)
    private String itemUnit;

    @Column(name = "item_count", nullable = false)
    private int itemCount;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(name = "members_count", nullable = false)
    private int membersCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PartyStatus partyStatus = PartyStatus.OPEN;

    public Party(String marketName, String marketAddress, Item item, String itemUnit, int itemCount, LocalDateTime startTime, LocalDateTime endTime, int membersCount) {
        this.marketName = marketName;
        this.marketAddress = marketAddress;
        this.item = item;
        this.itemUnit = itemUnit;
        this.itemCount = itemCount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.membersCount = membersCount;
        this.partyStatus = PartyStatus.OPEN;
    }

    // 장보기 완료
    public void completeParty() {
        this.partyStatus = PartyStatus.DONE;
    }

    // 파티 취소
    public void cancelParty() {
        this.partyStatus = PartyStatus.CANCELED;
    }

    // 상세 정보 업데이트 및 시간 검증 로직 추가
    public void updateDetails(Item item, String itemUnit, int itemCount, LocalDateTime startTime, LocalDateTime endTime, int membersCount) {
        this.item = item;
        this.itemUnit = itemUnit;
        this.itemCount = itemCount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.membersCount = membersCount;
    }

}
