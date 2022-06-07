package com.toy.pbbird.bird.repository;

import com.toy.pbbird.bird.domain.Bird;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BirdRepository extends JpaRepository<Bird, Long> {

    Long countByBirdNameAndUid(String birdName, String uId);

    void deleteByUid(String uid);

    List<Bird> findByUid(String uid);

    Optional<Bird> findByUidAndId(String uid, long birdId);

}
