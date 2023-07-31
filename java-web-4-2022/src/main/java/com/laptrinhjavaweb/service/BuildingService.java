package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.repose.BuildingResponse;
import com.laptrinhjavaweb.dto.request.BuildingDelRequest;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequest;
import javassist.NotFoundException;

import java.util.List;
import java.util.Map;

public interface BuildingService {
    List<BuildingResponse> findAll(Map<String, Object> params, List<String> rentTypes);

    List<BuildingResponse> findAll(BuildingSearchRequest buildingSearchRequest);

    BuildingDTO findById(Long id);

    void assignmentBuilding(List<Long> staffIds, Long buildingID) throws NotFoundException;

    void deleteWithCascade(BuildingDelRequest buildingDelRequest);

    BuildingDTO save(BuildingDTO buildingDTO);
}
