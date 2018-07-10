package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="registers")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Register implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "cfp")
	private String cfp;

	@Column(name = "ftid")
	private String ftid;

	@Column(name = "jiratask")
	private String jiratask;
	
	@Column(name = "springt")
	private Integer springt;
	
	@Column(name = "hour")
	private Integer hour;
	
	@Column(name = "defination")
	private String defination;
	
	//@Temporal(value = TemporalType.TIMESTAMP) Tam zamanı istenirse
	
	@Temporal(TemporalType.DATE)
	@Column(name="startdate", nullable=false)
	private Date startdate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="finishdate")
	private Date finishdate;
	
	@Column(name = "tag")
	private String tag;
	
	@ManyToOne
	@JoinColumn(name="cost_id", nullable=false)
	private CostType costType;
	
	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="create_user_id", nullable=false)
	private User creater;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="update_user_id")
	private User updater;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "team_id")
	private UserTeam registerUserteam;
	
	@ManyToOne
	@JoinColumn(name="status_id")
	private StatusType statusType;
	

	public Register() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCfp() {
		return cfp;
	}

	public void setCfp(String cfp) {
		this.cfp = cfp;
	}

	public String getFtid() {
		return ftid;
	}

	public void setFtid(String ftid) {
		this.ftid = ftid;
	}

	public String getJiratask() {
		return jiratask;
	}


	public void setJiratask(String jiratask) {
		this.jiratask = jiratask;
	}


	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}


	public Date getFinishdate() {
		return finishdate;
	}


	public void setFinishdate(Date finishdate) {
		this.finishdate = finishdate;
	}


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}


	public CostType getCostType() {
		return costType;
	}


	public void setCostType(CostType costType) {
		this.costType = costType;
	}
	
	
	public User getCreater() {
		return creater;
	}


	public void setCreater(User creater) {
		this.creater = creater;
	}


	public User getUpdater() {
		return updater;
	}


	public void setUpdater(User updater) {
		this.updater = updater;
	}
	

	public UserTeam getRegisterUserteam() {
		return registerUserteam;
	}


	public void setRegisterUserteam(UserTeam registerUserteam) {
		this.registerUserteam = registerUserteam;
	}


	public Integer getSpringt() {
		return springt;
	}


	public void setSpringt(Integer springt) {
		this.springt = springt;
	}


	public Integer getHour() {
		return hour;
	}


	public void setHour(Integer hour) {
		this.hour = hour;
	}


	public String getDefination() {
		return defination;
	}


	public void setDefination(String defination) {
		this.defination = defination;
	}
	

	public StatusType getStatusType() {
		return statusType;
	}


	public void setStatusType(StatusType statusType) {
		this.statusType = statusType;
	}


	@Override
	public String toString() {
		return "Register [id=" + id + ", cfp=" + cfp + ", ftid=" + ftid + ", jiratask=" + jiratask + ", startdate="
				+ startdate + ", finishdate=" + finishdate + ", tag=" + tag + ", costType=" + costType + "]";
	}


	
	
}
