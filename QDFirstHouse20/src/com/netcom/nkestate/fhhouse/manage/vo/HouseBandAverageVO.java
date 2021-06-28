package com.netcom.nkestate.fhhouse.manage.vo;

import com.netcom.nkestate.framework.AbstractBaseVO;
import com.netcom.nkestate.framework.dao.DBMeta;
import com.netcom.nkestate.framework.dao.DBModel;

@DBModel(tablename = "HOUSEBANDAVERAGE", sequence = "SEQ_NHBAID", id = DBModel.SequenceID)
public class HouseBandAverageVO extends AbstractBaseVO {

	private long nhbaID;//	主键ID
	private long nProjectID;//	项目ID
	private long nHouseID;//	房屋ID
	private long nHouseAverage;//	房屋基准价格
	private double nBandAverage;//	上幅度
	private double nBandAverageXia;//	下幅度
	private String scity;//	所在区市
	private double nactualPrices;//	NACTUALPRICES
	private String nactualAmplitude;//	NACTUALAMPLITUDE

	@DBMeta(column = "NhbaID", name = "主键ID", type = "long", primarykey = "true", can_update = "false")
	public long getNhbaID() {
		return nhbaID;
	}

	public void setNhbaID(long nhbaID) {
		this.nhbaID = nhbaID;
		this.settings.put("nhbaID", nhbaID);
	}

	@DBMeta(column = "NProjectID", name = "项目ID", type = "long")
	public long getNProjectID() {
		return nProjectID;
	}

	public void setNProjectID(long nProjectID) {
		this.nProjectID = nProjectID;
		this.settings.put("nProjectID", nProjectID);
	}

	@DBMeta(column = "NHouseID", name = "房屋ID", type = "long")
	public long getNHouseID() {
		return nHouseID;
	}

	public void setNHouseID(long nHouseID) {
		this.nHouseID = nHouseID;
		this.settings.put("nHouseID", nHouseID);
	}

	@DBMeta(column = "NHouseAverage", name = "房屋基准价格", type = "long")
	public long getNHouseAverage() {
		return nHouseAverage;
	}

	public void setNHouseAverage(long nHouseAverage) {
		this.nHouseAverage = nHouseAverage;
		this.settings.put("nHouseAverage", nHouseAverage);
	}

	@DBMeta(column = "NBandAverage", name = "上幅度", type = "double")
	public double getNBandAverage() {
		return nBandAverage;
	}

	public void setNBandAverage(double nBandAverage) {
		this.nBandAverage = nBandAverage;
		this.settings.put("nBandAverage", nBandAverage);
	}

	@DBMeta(column = "NBandAverageXia", name = "下幅度", type = "double")
	public double getNBandAverageXia() {
		return nBandAverageXia;
	}

	public void setNBandAverageXia(double nBandAverageXia) {
		this.nBandAverageXia = nBandAverageXia;
		this.settings.put("nBandAverageXia", nBandAverageXia);
	}

	@DBMeta(column = "Scity", name = "所在区市", type = "String")
	public String getScity() {
		return scity;
	}

	public void setScity(String scity) {
		this.scity = scity;
		this.settings.put("scity", scity);
	}

	@DBMeta(column = "NactualPrices", name = "NactualPrices", type = "double")
	public double getNactualPrices() {
		return nactualPrices;
	}

	public void setNactualPrices(double nactualPrices) {
		this.nactualPrices = nactualPrices;
		this.settings.put("nactualPrices", nactualPrices);
	}

	@DBMeta(column = "NactualAmplitude", name = "NactualAmplitude", type = "String")
	public String getNactualAmplitude() {
		return nactualAmplitude;
	}

	public void setNactualAmplitude(String nactualAmplitude) {
		this.nactualAmplitude = nactualAmplitude;
		this.settings.put("nactualAmplitude", nactualAmplitude);
	}


}
