package com.vaucher.topup.datamodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.vaucher.budget.datamodel.ENUM_STATUS;

@Entity
public class Topup_Product implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(length = 12)
	private String product_code;
	
	@Column(length = 30)
	private String product_title;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 15)
	private ENUM_STATUS product_status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date status_date;
	
	@Enumerated(EnumType.STRING)
	private ENUM_OPERATOR operator_code;
	
	private BigDecimal min_amount;
	
	private BigDecimal max_amount;

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getProduct_title() {
		return product_title;
	}

	public void setProduct_title(String product_title) {
		this.product_title = product_title;
	}

	public ENUM_STATUS getProduct_status() {
		return product_status;
	}

	public void setProduct_status(ENUM_STATUS product_status) {
		this.product_status = product_status;
	}

	public Date getStatus_date() {
		return status_date;
	}

	public void setStatus_date(Date status_date) {
		this.status_date = status_date;
	}

	public ENUM_OPERATOR getOperator_code() {
		return operator_code;
	}

	public void setOperator_code(ENUM_OPERATOR operator_code) {
		this.operator_code = operator_code;
	}

	public BigDecimal getMin_amount() {
		return min_amount;
	}

	public void setMin_amount(BigDecimal min_amount) {
		this.min_amount = min_amount;
	}

	public BigDecimal getMax_amount() {
		return max_amount;
	}

	public void setMax_amount(BigDecimal max_amount) {
		this.max_amount = max_amount;
	}
	
	
	
}
