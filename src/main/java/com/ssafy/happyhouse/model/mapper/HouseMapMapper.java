package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.BikeDto;
import com.ssafy.happyhouse.model.CoronaInspectorDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;

public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws Exception;

	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;

	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;

	List<HouseInfoDto> getAptInDong(String dong) throws Exception;

	String getHangjung(String dong) throws Exception;

	List<BikeDto> getBike(String gugun) throws Exception;

	List<CoronaInspectorDto> getInspector(String gugun) throws Exception;
}
