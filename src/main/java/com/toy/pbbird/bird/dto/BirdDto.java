package com.toy.pbbird.bird.dto;

import com.toy.pbuser.bird.domain.Bird;
import com.toy.pbuser.user.domain.User;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Pattern;

public class BirdDto {

    @Getter
    @ToString
    @ApiModel(value = "BirdDto.Req")
    public static class Req {

        @Pattern(regexp = "^[가-힣ㄱ-ㅎa-zA-Z0-9]{2,10}",
                message = "2~10자의 한글, 영문, 숫자만 사용할 수 있습니다.")
        private String birdName;

        public Bird toEntity(String uId) {
            return Bird.builder()
                    .uid(uId)
                    .birdName(birdName)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "BirdDto.Res")
    public static class Res {

        private Long id;
        private String uid;
        private String birdName;

        public static Res of(Bird bird) {

            return Res.builder()
                    .id(bird.getId())
                    .uid(bird.getUid())
                    .birdName(bird.getBirdName())
                    .build();
        }

    }

}
