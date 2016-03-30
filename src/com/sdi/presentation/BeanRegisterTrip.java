package com.sdi.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "registerTrip")
@SessionScoped
public class BeanRegisterTrip implements Serializable {
	private static final long serialVersionUID = 6L;
	private String adressFrom = "";
	private String cityFrom = "";
	private String provinceFrom = "";
	private String countryFrom = "";
	private String postalCodeFrom = "";
	
	private String adressTo = "";
	private String cityTo = "";
	private String provinceTo = "";
	private String countryTo = "";
	private String postalCodeTo = "";
	
	private String dateFrom = "";
	private String dateTo = "";
	private String dateLimit = "";
	private String priceTrip = "";
	private String commentTrip = "";
	private String maxSeats = "";
	private String availableSeats = "";
	
	private String result = "registerTrip_form_result_valid";
	
	public BeanRegisterTrip() {
		System.out.println("BeanRegisterTrip - No existia");
	}

	public String getAdressFrom() {
		return adressFrom;
	}

	public void setAdressFrom(String adressFrom) {
		this.adressFrom = adressFrom;
	}

	public String getCityFrom() {
		return cityFrom;
	}

	public void setCityFrom(String cityFrom) {
		this.cityFrom = cityFrom;
	}

	public String getProvinceFrom() {
		return provinceFrom;
	}

	public void setProvinceFrom(String provinceFrom) {
		this.provinceFrom = provinceFrom;
	}

	public String getCountryFrom() {
		return countryFrom;
	}

	public void setCountryFrom(String countryFrom) {
		this.countryFrom = countryFrom;
	}

	public String getPostalCodeFrom() {
		return postalCodeFrom;
	}

	public void setPostalCodeFrom(String postalCodeFrom) {
		this.postalCodeFrom = postalCodeFrom;
	}

	public String getAdressTo() {
		return adressTo;
	}

	public void setAdressTo(String adressTo) {
		this.adressTo = adressTo;
	}

	public String getCityTo() {
		return cityTo;
	}

	public void setCityTo(String cityTo) {
		this.cityTo = cityTo;
	}

	public String getProvinceTo() {
		return provinceTo;
	}

	public void setProvinceTo(String provinceTo) {
		this.provinceTo = provinceTo;
	}

	public String getCountryTo() {
		return countryTo;
	}

	public void setCountryTo(String countryTo) {
		this.countryTo = countryTo;
	}

	public String getPostalCodeTo() {
		return postalCodeTo;
	}

	public void setPostalCodeTo(String postalCodeTo) {
		this.postalCodeTo = postalCodeTo;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getDateLimit() {
		return dateLimit;
	}

	public void setDateLimit(String dateLimit) {
		this.dateLimit = dateLimit;
	}

	public String getPriceTrip() {
		return priceTrip;
	}

	public void setPriceTrip(String priceTrip) {
		this.priceTrip = priceTrip;
	}

	public String getCommentTrip() {
		return commentTrip;
	}

	public void setCommentTrip(String commentTrip) {
		this.commentTrip = commentTrip;
	}

	public String getMaxSeats() {
		return maxSeats;
	}

	public void setMaxSeats(String maxSeats) {
		this.maxSeats = maxSeats;
	}

	public String getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(String availableSeats) {
		this.availableSeats = availableSeats;
	}

	
}
