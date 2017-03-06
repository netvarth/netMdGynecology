package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bed_tbl database table.
 * 
 */
@Entity
@Table(name="bed_tbl")
public class BedTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BED_TBL_ID_GENERATOR",sequenceName="bed_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BED_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length=45)
	private AvailabilityStatusEnum availability;

	@Column(name="bed_number", length=30)
	private String bedNumber;

	@Column(length=200)
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(length=45)
	private StatusEnum status;
	
	@Column(length=15)
	private String prefix;

	
	//bi-directional many-to-one association to BedTypeTbl
	@ManyToOne
	@JoinColumn(name="bed_type_id")
	private BedTypeTbl bedTypeTbl;

	//bi-directional many-to-one association to RoomTbl
	@ManyToOne
	@JoinColumn(name="room_id")
	private RoomTbl roomTbl;

	public BedTbl() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getBedNumber() {
		return this.bedNumber;
	}

	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	
	public BedTypeTbl getBedTypeTbl() {
		return this.bedTypeTbl;
	}

	public void setBedTypeTbl(BedTypeTbl bedTypeTbl) {
		this.bedTypeTbl = bedTypeTbl;
	}

	public RoomTbl getRoomTbl() {
		return this.roomTbl;
	}

	public void setRoomTbl(RoomTbl roomTbl) {
		this.roomTbl = roomTbl;
	}

	
	/**
	 * @return the status
	 */
	public StatusEnum getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	/**
	 * @return the availability
	 */
	public AvailabilityStatusEnum getAvailability() {
		return availability;
	}

	/**
	 * @param availability the availability to set
	 */
	public void setAvailability(AvailabilityStatusEnum availability) {
		this.availability = availability;
	}

	
}