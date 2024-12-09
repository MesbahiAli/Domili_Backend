package com.vaucher.topup.datamodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.vaucher.budget.datamodel.Budget;
import com.vaucher.budget.datamodel.ENUM_OPERATION_STATUS;
import com.vaucher.product.datamodel.Product;
import com.vaucher.product.datamodel.Vaucher;

@Entity
public class Topup_Operations implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(length = 36)
	private String op_id;
	
	@ManyToOne
	@JoinColumn(name = "budget_id")
	private Budget budget;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "vaucher_id")
	private Vaucher vaucher;
	
	@ManyToOne
	@JoinColumn(name = "topup_product_id")
	private Topup_Product topupProduct;
	
	@Enumerated(EnumType.STRING)
	private ENUM_OPERATOR operator_code;
	
	private BigDecimal op_amount;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date op_date;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 15)
	private ENUM_OPERATION_STATUS op_status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date status_date;
	
	@Column(length = 36)
	private String topup_reference;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date complete_date;

	public String getOp_id() {
		return op_id;
	}

	public void setOp_id(String op_id) {
		this.op_id = op_id;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Vaucher getVaucher() {
		return vaucher;
	}

	public void setVaucher(Vaucher vaucher) {
		this.vaucher = vaucher;
	}

	public Topup_Product getTopupProduct() {
		return topupProduct;
	}

	public void setTopupProduct(Topup_Product topupProduct) {
		this.topupProduct = topupProduct;
	}

	public ENUM_OPERATOR getOperator_code() {
		return operator_code;
	}

	public void setOperator_code(ENUM_OPERATOR operator_code) {
		this.operator_code = operator_code;
	}

	public BigDecimal getOp_amount() {
		return op_amount;
	}

	public void setOp_amount(BigDecimal op_amount) {
		this.op_amount = op_amount;
	}

	public Date getOp_date() {
		return op_date;
	}

	public void setOp_date(Date op_date) {
		this.op_date = op_date;
	}

	public ENUM_OPERATION_STATUS getOp_status() {
		return op_status;
	}

	public void setOp_status(ENUM_OPERATION_STATUS op_status) {
		this.op_status = op_status;
	}

	public Date getStatus_date() {
		return status_date;
	}

	public void setStatus_date(Date status_date) {
		this.status_date = status_date;
	}

	public String getTopup_reference() {
		return topup_reference;
	}

	public void setTopup_reference(String topup_reference) {
		this.topup_reference = topup_reference;
	}

	public Date getComplete_date() {
		return complete_date;
	}

	public void setComplete_date(Date complete_date) {
		this.complete_date = complete_date;
	}

}
