package com.cmaotai.service.util;

import com.google.common.base.Splitter;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Slf4j
public class UriUtils {

    public static MultiValueMap<String, String> parse(String url) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        Splitter.on("&").splitToList(url).forEach(s -> {
            List<String> ss = Splitter.on("=").splitToList(s);
            map.add(ss.get(0), ss.get(1));
        });
        return map;
    }

}