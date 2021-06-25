package com.isstech.isstechadmin.domain.vo;

import com.isstech.isstechadmin.domain.BasePassVehicle;
import lombok.Data;

import java.util.Date;

@Data
public class PassVehicleVo extends BasePassVehicle {

    private String startPassTime;

    private String endPassTime;

}
