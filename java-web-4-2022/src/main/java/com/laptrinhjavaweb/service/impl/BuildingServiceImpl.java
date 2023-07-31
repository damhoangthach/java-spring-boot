package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.repose.BuildingResponse;
import com.laptrinhjavaweb.dto.request.BuildingDelRequest;
import com.laptrinhjavaweb.dto.request.BuildingSearchRequest;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.exception.MyException;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.BuildingService;
import com.laptrinhjavaweb.utils.MapUtil;
import com.laptrinhjavaweb.utils.ParseIntUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<BuildingResponse> findAll(Map<String, Object> params, List<String> rentTypes) {

        List<BuildingResponse> buildingResponses = new ArrayList<>();
        BuildingSearchBuilder buildingSearchBuilder = toBuildingSearchBuilder(params, rentTypes);
        for (BuildingEntity item : buildingRepository.findAll(buildingSearchBuilder)) {
            buildingResponses.add(buildingConverter.toBuildingResponse(item));
        }
        return buildingResponses;
    }

    @Override
    public List<BuildingResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        if(SecurityUtils.getAuthorities().contains(SystemConstant.STAFF_ROLE)) {
            buildingSearchRequest.setStaffID(SecurityUtils.getPrincipal().getId());
        }
        List<BuildingResponse> buildingResponses = new ArrayList<>();
        BuildingSearchBuilder buildingSearchBuilder = toBuildingSearchBuilder(buildingSearchRequest);
        for (BuildingEntity item : buildingRepository.findAll(buildingSearchBuilder)) {
            buildingResponses.add(buildingConverter.toBuildingResponse(item));
        }
        return buildingResponses;
    }

    @Override
    public BuildingDTO findById(Long id) {
        return id != null ? buildingConverter.toBuildingDTO(buildingRepository.findById(id)) : new BuildingDTO();
    }

    @Override
    @Transactional
    public void assignmentBuilding(List<Long> staffIds, Long buildingID) throws NotFoundException {
        try {
            BuildingEntity buildingEntity = buildingRepository.findOne(buildingID);
            buildingEntity.setUserEntities(new ArrayList<>(Optional.ofNullable(userRepository.findAll(staffIds))
                    .orElseThrow(()->new NotFoundException(SystemConstant.NF_USER))));
            buildingRepository.save(buildingEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    @Transactional
    public void deleteWithCascade(BuildingDelRequest buildingDelRequest) {
        if(!buildingDelRequest.getBuildingIds().isEmpty()){
            buildingRepository.deleteByIdIn(buildingDelRequest.getBuildingIds());
        }
    }



    @Override
    @Transactional
    public BuildingDTO save(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = buildingConverter.toBuildingEntity(buildingDTO);
        if (buildingDTO.getId() != null) {
            BuildingEntity oldBuilding = buildingRepository.findOne(buildingDTO.getId());
            buildingEntity.setUserEntities(oldBuilding.getUserEntities());
        }
        return buildingConverter.toBuildingDTO( buildingRepository.save(buildingEntity));
    }

    private BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> rentTypes) {
        try {
            Map<String, Object> paramsPsd = toLowKey(params);
            BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                    .name((String) MapUtil.getValue(paramsPsd, "name"))
                    .floorArea(ParseIntUtil.getValue(MapUtil.getValue(paramsPsd, "floorarea")))
                    .district((String) MapUtil.getValue(paramsPsd, "districtcode"))
                    .ward((String) MapUtil.getValue(paramsPsd, "ward"))
                    .street((String) MapUtil.getValue(paramsPsd, "street"))
                    .numberOfBasement(ParseIntUtil.getValue(MapUtil.getValue(paramsPsd, "numberofbasement")))
                    .direction((String) MapUtil.getValue(paramsPsd, "direction"))
                    .level((String) MapUtil.getValue(paramsPsd, "level"))
                    .rentAreaFrom(ParseIntUtil.getValue(MapUtil.getValue(paramsPsd, "rentareafrom")))
                    .rentAreaTo(ParseIntUtil.getValue(MapUtil.getValue(paramsPsd, "rentareato")))
                    .rentPriceFrom(ParseIntUtil.getValue(MapUtil.getValue(paramsPsd, "rentpricefrom")))
                    .rentPriceTo(ParseIntUtil.getValue(MapUtil.getValue(paramsPsd, "rentpriceto")))
                    .managerName((String) MapUtil.getValue(paramsPsd, "managername"))
                    .managerPhone((String) MapUtil.getValue(paramsPsd, "managerphone"))
                    .staffID((Long) MapUtil.getValue(paramsPsd, "staffid")).rentTypes(rentTypes)
                    .build();
            return buildingSearchBuilder;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private BuildingSearchBuilder toBuildingSearchBuilder(BuildingSearchRequest buildingSearchRequest) {
        try {
            BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                    .name(buildingSearchRequest.getName())
                    .floorArea(buildingSearchRequest.getFloorArea())
                    .district(buildingSearchRequest.getDistrictCode())
                    .ward(buildingSearchRequest.getWard())
                    .street(buildingSearchRequest.getStreet())
                    .numberOfBasement(buildingSearchRequest.getNumberOfBasement())
                    .direction(buildingSearchRequest.getDirection())
                    .level(buildingSearchRequest.getLevel())
                    .rentAreaFrom(buildingSearchRequest.getRentAreaFrom())
                    .rentAreaTo(buildingSearchRequest.getRentAreaTo())
                    .rentPriceFrom(buildingSearchRequest.getRentPriceFrom())
                    .rentPriceTo(buildingSearchRequest.getRentPriceTo())
                    .managerName(buildingSearchRequest.getManagerName())
                    .managerPhone(buildingSearchRequest.getManagerPhone())
                    .staffID(buildingSearchRequest.getStaffID())
                    .rentTypes(buildingSearchRequest.getRentTypes())
                    .build();
            return buildingSearchBuilder;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Map<String, Object> toLowKey(Map<String, Object> params) {
        Map<String, Object> results = new HashMap<>();
        for (Map.Entry<String, Object> item : params.entrySet()) {
            results.put(item.getKey().toLowerCase(), item.getValue());
        }
        return results;
    }

    public void validate(int a) throws MyException {
        try {
            if (a == 1)
                throw new MyException("loi test");
        } catch (MyException e) {
            throw e;
        }

    }
}

