package com.example.lastproject.domain.party.controller;

import com.example.lastproject.domain.auth.entity.AuthUser;
import com.example.lastproject.domain.party.dto.request.PartyCreateRequest;
import com.example.lastproject.domain.party.dto.request.PartyUpdateRequest;
import com.example.lastproject.domain.party.dto.response.PartyResponse;
import com.example.lastproject.domain.party.service.PartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parties")
public class PartyController {

    private final PartyService partyService;

    /**
     *파티 생성 메소드
     * @param authUser 파티 생성 요청을 한 사용자(파티장)
     * @param partyCreateRequest 파티 생성 시 필요한 json body
     *        (marketName: 마켓 이름, marketAddress: 마켓 주소, itemId: 거래 품목, itemUnit: 거래 단위,
     *         itemCount: 품목 개수, startTime: 장보기 시작 시간, endTime: 장보기 종료 시간, membersCount: 파티 인원)
     * @return ResponseEntity<PartyResponse>로 생성된 파티 정보 반환, HTTP 상태 코드 201 반환
     */
    @PostMapping
    public ResponseEntity<PartyResponse> createParty(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestBody PartyCreateRequest partyCreateRequest) {
        PartyResponse partyResponse = partyService.createParty(partyCreateRequest, authUser.getUserId());
        return new ResponseEntity<>(partyResponse, HttpStatus.CREATED);
    }

    // 파티 조회
    @GetMapping
    public ResponseEntity<List<PartyResponse>> getAllParties() {
        List<PartyResponse> parties = partyService.getAllActiveParties();
        return new ResponseEntity<>(parties, HttpStatus.OK);
    }

    // 파티 수정
    @PutMapping("/{partyId}")
    public ResponseEntity<PartyResponse> updateParty(
            @PathVariable Long partyId,
            @RequestBody PartyUpdateRequest partyUpdateRequest,
            @RequestHeader("userId") Long userId) {
        PartyResponse partyResponse = partyService.updateParty(partyId, partyUpdateRequest, userId);
        return new ResponseEntity<>(partyResponse, HttpStatus.OK);
    }

    // 파티 완료 처리(장보기 완료)
    @PutMapping("/{partyId}/complete")
    public ResponseEntity<Void> completeParty(
            @PathVariable Long partyId) {
        partyService.completeParty(partyId);
        return ResponseEntity.ok().build();
    }

    // 파티 취소 처리(파티 취소)
    @PutMapping("/{partyId}/cancel")
    public ResponseEntity<Void> cancelParty(
            @PathVariable Long partyId) {
        partyService.cancelParty(partyId);
        return ResponseEntity.ok().build();
    }

}
