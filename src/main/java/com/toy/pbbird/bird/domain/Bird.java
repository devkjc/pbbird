package com.toy.pbbird.bird.domain;

import com.toy.pbbird.common.domain.BaseTimeEntity;
import com.toy.pbuser.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "bird")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bird extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "uid_fk")
    private User user;

    private String birdName;

    public void setBirdName(String birdName) {
        this.birdName = birdName;
    }
}
