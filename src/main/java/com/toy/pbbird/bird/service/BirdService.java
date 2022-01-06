package com.toy.pbbird.bird.service;

import com.toy.pbbird.bird.dto.BirdDto;
import com.toy.pbbird.bird.repository.BirdRepository;
import com.toy.pbbird.common.exception.ProcessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BirdService {

    private final BirdRepository birdRepository;

    public Boolean birdNameDuplication(String uid, String birdName) {
        return birdRepository.countByBirdNameAndUid(birdName, uid) < 1;
    }

    @Transactional
    public BirdDto.Res saveBirdName(String uid, BirdDto.Req req) {
        if (birdNameDuplication(uid, req.getBirdName())) {
            return BirdDto.Res.of(birdRepository.save(req.toEntity(uid)));
        }else {
            throw new ProcessException("중복된 새이름 입니다.");
        }
    }

    @Transactional
    public void deleteAllBird(String uid) {
        birdRepository.deleteByUid(uid);
    }


    public List<BirdDto.Res> getBirdList(String uid) {
        return birdRepository.findByUid(uid).stream().map(BirdDto.Res::of).collect(Collectors.toList());
    }
}
