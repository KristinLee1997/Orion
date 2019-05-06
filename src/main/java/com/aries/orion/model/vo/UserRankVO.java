package com.aries.orion.model.vo;

import lombok.Data;

@Data
public class UserRankVO {
    private Long gaeaId;
    private String userName;
    private Long acTotal;
    private Integer registerDays;

    public UserRankVO() {
    }

    public UserRankVO(Long gaeaId, Long acTotal) {
        this.gaeaId = gaeaId;
        this.acTotal = acTotal;
    }
}
