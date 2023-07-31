package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;

import java.util.List;

public interface RentAreaRepositoryCustom {
    void saveAllByBuilding(List<RentAreaEntity> rentAreaEntities, BuildingEntity buildingEntity);
}
