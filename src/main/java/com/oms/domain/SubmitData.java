package com.oms.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Objects;

@Entity
public class SubmitData {

    @Id
    @NotNull
    @Size(max = 64)
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @NotNull
    @Size(max = 13)
    @Column(name = "mobile", nullable = false)
    private String mobile;

    @NotNull
    @Size(max = 64)
    @Column(name = "city", nullable = false)
    private String city;
    
    @NotNull
    @Column(name = "update_date", nullable = false)
    private Date update_date;
    
    @NotNull
    @Size(max = 32)
    @Column(name = "card_id", nullable = false)
    private String card_id;
    
    @NotNull
    @Size(max = 100)
    @Column(name = "user_name", nullable = false)
    private String user_name;
    
    @NotNull
    @Size(max = 200)
    @Column(name = "office_id", nullable = false)
    private String office_id;
    
    @Size(max = 1)
    @Column(name = "spec", nullable = false)
    private String spec;
    
    public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getOffice_id() {
		return office_id;
	}
	public void setOffice_id(String office_id) {
		this.office_id = office_id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private SubmitData() {
    }
    public String getId() {
        return id;
    }


    public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	@Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("mobile", mobile)
                .toString();
    }
}
