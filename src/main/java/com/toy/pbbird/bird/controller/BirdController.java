package com.toy.pbbird.bird.controller;

import com.toy.pbbird.bird.dto.BirdDto;
import com.toy.pbbird.bird.service.BirdService;
import com.toy.pbbird.config.security.SecurityService;
import com.toy.pbbird.postbox.PostBoxFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bird")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
@Api(tags = {"Bird API"})
@Validated
public class BirdController {

    private final BirdService birdService;
    private final PostBoxFeign postBoxFeign;

    @GetMapping("/name")
    @ApiOperation(value = "새이름 중복 검사")
    public ResponseEntity<Boolean> isBirdNameDuplicate(@RequestParam @Pattern(regexp = "^[가-힣ㄱ-ㅎa-zA-Z0-9]{2,10}",
                                                                   message = "2~10자의 한글, 영문, 숫자만 사용할 수 있습니다.") String birdName) {
        String uid = SecurityService.getUid();
        return ResponseEntity.ok(birdService.birdNameDuplication(uid, birdName));
    }

    @PostMapping("/name")
    @ApiOperation(value = "새이름 저장")
    public ResponseEntity<BirdDto.Res> saveBirdName(@Valid @RequestBody BirdDto.Req req) {
        String uid = SecurityService.getUid();
        return ResponseEntity.ok(birdService.saveBirdName(uid, req));
    }

    @DeleteMapping
    @ApiOperation(value = "새 전부 놓아주기")
    public ResponseEntity<?> deleteAllBird() {
        String uid = SecurityService.getUid();
        birdService.deleteAllBird(uid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    @ApiOperation(value = "새 조회")
    public ResponseEntity<List<BirdDto.Res>> getBirdList() {
        String uid = SecurityService.getUid();
        return ResponseEntity.ok(birdService.getBirdList(uid));
    }

}
