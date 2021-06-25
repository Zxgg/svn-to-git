package com.isstech.isstechadmin.domain.vo;

import com.isstech.isstechadmin.domain.BasePassPersonnel;
import lombok.Data;

@Data
public class PassPersonnelVo extends BasePassPersonnel {

    private String startMarkTime;

    private String endMarkTime;

    private String passType;

    private String careType;

}
