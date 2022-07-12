package com.co.algomoko.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
public class AdminVO {
    // 검색 필터
    private String type;
    private String keyword;
}
