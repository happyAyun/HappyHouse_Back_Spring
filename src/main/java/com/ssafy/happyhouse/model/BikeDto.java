package com.ssafy.happyhouse.model;

public class BikeDto {
	private String place;
	private String gugunname;
	private String placedetail;
	private String lat;
	private String lng;
	private int maxcount;
	private String btype;

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getGugunname() {
		return gugunname;
	}

	public void setGugunname(String gugunname) {
		this.gugunname = gugunname;
	}

	public String getPlacedetail() {
		return placedetail;
	}

	public void setPlacedetail(String placedetail) {
		this.placedetail = placedetail;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public int getMaxcount() {
		return maxcount;
	}

	public void setMaxcount(int maxcount) {
		this.maxcount = maxcount;
	}

	public String getBtype() {
		return btype;
	}

	public void setBtype(String btype) {
		this.btype = btype;
	}

}
