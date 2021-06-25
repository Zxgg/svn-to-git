package com.isstech.web.controller.tool;

import com.isstech.isstechadmin.domain.*;
import com.isstech.isstechadmin.mapper.BaseCommunityHousesMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class TestDataController {

    @Autowired
    private BaseCommunityHousesMapper baseCommunityHousesMapper;

    private void createData(String floor,String houseNumber){
        //楼栋
        //BaseCommunityBuilding baseCommunityBuilding = new BaseCommunityBuilding();
        //房屋
        BaseCommunityHouses baseCommunityHouses = new BaseCommunityHouses();
        //人员
        BasePersonnelInformation basePersonnelInformation = new BasePersonnelInformation();
        //房屋人员管理
        BaseCommunityHousesPersonnel baseCommunityHousesPersonnel = new BaseCommunityHousesPersonnel();
        //车辆
        BaseVehicleInformation baseVehicleInformation = new BaseVehicleInformation();
        //车辆和社区房屋和人员关联
        BaseVehicleCommunityHousesPersonnel baseVehicleCommunityHousesPersonnel = new BaseVehicleCommunityHousesPersonnel();
        //社区id
        String communityId = "f2756e00-2a4f-11eb-8b0f-0242ac120002";
        //小区id
        String communityChildId = "11eacbf5-522a-11eb-8b0f-0242ac120002";
        //楼栋id
        String communityBuildingId = "652976c4-522a-11eb-8b0f-0242ac120002";
        baseCommunityHouses.setCommunityId(communityId);
        baseCommunityHouses.setCommunityChildId(communityChildId);
        baseCommunityHouses.setCommunityBuildingId(communityBuildingId);
        baseCommunityHouses.setId(baseCommunityHousesMapper.selectUuid());
        baseCommunityHouses.setArea("123");
        baseCommunityHouses.setBuildingArea("23");
        baseCommunityHouses.setBuildingName("1号楼");
        baseCommunityHouses.setConstructionTime("2018-12-12");
        baseCommunityHouses.setElevator("1");
        baseCommunityHouses.setFloor(floor);
        baseCommunityHouses.setHazardType("0");
        baseCommunityHouses.setHouseNumber(houseNumber);
        baseCommunityHouses.setHousePropertyRight("70");
        baseCommunityHouses.setHouseStructure("0");
        baseCommunityHouses.setName(houseNumber);
        baseCommunityHouses.setPhone("13485621859");
    }
}
