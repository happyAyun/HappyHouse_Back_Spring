package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.BikeDto;
import com.ssafy.happyhouse.model.CoronaInspectorDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseMapService;

@RestController
@RequestMapping("/map")
public class HappyHouseMapController {

	@Autowired
	private HouseMapService houseMapService;

	@GetMapping(value = "/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseMapService.getSido(), HttpStatus.OK);
	}

	@GetMapping(value = "/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(houseMapService.getGugunInSido(sido), HttpStatus.OK);
	}

	@GetMapping(value = "/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}

	@GetMapping(value = "/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(houseMapService.getAptInDong(dong), HttpStatus.OK);
	}

	@GetMapping(value = "/hangjung")
	public ResponseEntity<String> hangjung(@RequestParam("dong") String dong) throws Exception {

		return new ResponseEntity<String>(houseMapService.getHangjung(dong), HttpStatus.OK);
	}

	@GetMapping(value = "/bike")
	public ResponseEntity<List<BikeDto>> bike(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<BikeDto>>(houseMapService.getBike(gugun), HttpStatus.OK);
	}

	@GetMapping(value = "/inspector")
	public ResponseEntity<List<CoronaInspectorDto>> inspector(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<CoronaInspectorDto>>(houseMapService.getInspector(gugun), HttpStatus.OK);
	}
}
