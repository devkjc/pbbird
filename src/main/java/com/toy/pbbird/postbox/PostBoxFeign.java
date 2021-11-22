package com.toy.pbbird.postbox;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("pbpostbox")
public interface PostBoxFeign {

    @GetMapping("/api/v1/postbox")
    List<Object> getPostBox();

}
