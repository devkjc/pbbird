package com.toy.pbbird.bird.service;

import com.toy.pbbird.bird.dto.BirdDto;
import com.toy.pbbird.bird.repository.BirdRepository;
import com.toy.pbbird.common.exception.ProcessException;
import com.toy.pbbird.common.service.UserFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BirdService {

    private final BirdRepository birdRepository;

    public Boolean birdNameDuplication(String birdName) {
        return birdRepository.countByBirdName(birdName) < 1;
    }

    @Transactional
    public BirdDto.Res saveBirdName(String uid, BirdDto.Req req) {
        if (birdNameDuplication(req.getBirdName())) {
            return BirdDto.Res.of(birdRepository.save(req.toEntity(uid)));
        }else {
            throw new ProcessException("중복된 새이름 입니다.");
        }
    }

}
