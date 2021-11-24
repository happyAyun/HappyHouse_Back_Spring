package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.BikeDto;
import com.ssafy.happyhouse.model.BusDto;
import com.ssafy.happyhouse.model.CoronaInspectorDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.SubwayDto;

public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws Exception;

	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;

	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;

	List<HouseInfoDto> getAptInDong(String dong) throws Exception;

	String getHangjung(String dong) throws Exception;

	List<BikeDto> getBike(Map<String, String> latlng) throws Exception;

	List<CoronaInspectorDto> getInspector(String gugun) throws Exception;

	List<HouseInfoDto> getPastAptList(String aptCode);

	List<BusDto> getBus(Map<String, String> latlng) throws Exception;

	List<SubwayDto> getSubway(Map<String, String> latlng) throws Exception;

	List<HouseInfoDto> getAptInRadius(Map<String, String> latlng);

	List<SubwayDto> getdSubway();
}
