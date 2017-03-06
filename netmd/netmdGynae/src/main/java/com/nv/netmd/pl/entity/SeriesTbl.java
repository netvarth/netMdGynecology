package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the series_tbl database table.
 * 
 */
@Entity
@Table(name="series_tbl")
public class SeriesTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SERIES_TBL_ID_GENERATOR",sequenceName="series_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SERIES_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="end_date")
	private Date endDate;

	@Column(name="ends_on")
	private Integer endsOn=0;
	
	@Column(name="global_id")
	private Integer globalId=0; 
	
	@Enumerated(EnumType.STRING)
	@Column(name="occurance_type", length=100)
	private OccuranceTypeEnum occuranceType;
	
	@Enumerated(EnumType.STRING)
	@Column(length=200)
	private RepeatEnum repeat;

	@Column(name="weekly_type", length=100)
	private String weeklyType;

	//bi-directional many-to-one association to DoctorScheduleTbl
	@OneToMany(mappedBy="seriesTbl")
	private Set<DoctorScheduleTbl> doctorScheduleTbls;

    public SeriesTbl() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public Integer getEndsOn() {
		return this.endsOn;
	}

	public void setEndsOn(Integer endsOn) {
		this.endsOn = endsOn;
	}


	public String getWeeklyType() {
		return this.weeklyType;
	}

	public void setWeeklyType(String weeklyType) {
		this.weeklyType = weeklyType;
	}

	public Set<DoctorScheduleTbl> getDoctorScheduleTbls() {
		return this.doctorScheduleTbls;
	}

	public void setDoctorScheduleTbls(Set<DoctorScheduleTbl> doctorScheduleTbls) {
		this.doctorScheduleTbls = doctorScheduleTbls;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the occuranceType
	 */
	public OccuranceTypeEnum getOccuranceType() {
		return occuranceType;
	}

	/**
	 * @param occuranceType the occuranceType to set
	 */
	public void setOccuranceType(OccuranceTypeEnum occuranceType) {
		this.occuranceType = occuranceType;
	}

	/**
	 * @return the repeat
	 */
	public RepeatEnum getRepeat() {
		return repeat;
	}

	/**
	 * @param repeat the repeat to set
	 */
	public void setRepeat(RepeatEnum repeat) {
		this.repeat = repeat;
	}

	/**
	 * @return the globalId
	 */
	public Integer getGlobalId() {
		return globalId;
	}

	/**
	 * @param globalId the globalId to set
	 */
	public void setGlobalId(Integer globalId) {
		this.globalId = globalId;
	}
	
}