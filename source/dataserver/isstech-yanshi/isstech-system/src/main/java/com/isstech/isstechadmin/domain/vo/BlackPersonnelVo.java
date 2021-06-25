package com.isstech.isstechadmin.domain.vo;

import lombok.Data;

@Data
public class BlackPersonnelVo{
    /** 图片 */
    private String imageUrl;

    /** 姓名 */
    private String name;

    /** 身份证号 */
    private String CardID;
}
