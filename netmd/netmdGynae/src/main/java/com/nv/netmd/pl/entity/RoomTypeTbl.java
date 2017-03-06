package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the room_type_tbl database table.
 * 
 */
@Entity
@Table(name="room_type_tbl")
public class RoomTypeTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ROOM_TYPE_TBL_ID_GENERATOR",sequenceName="room_type_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROOM_TYPE_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	private Integer count=0;

	@Column(name="no_of_bed")
	private Integer noOfBed=0;

	
	private float rent;

	@Column(length=50)
	private String type;
	
	@Enumerated(EnumType.STRING)
	@Column(length=45)
	private StatusEnum status;
	
	//bi-directional many-to-one association to RoomTbl
	@OneToMany(mappedBy="roomTypeTbl")
	private List<RoomTbl> roomTbls;

	public RoomTypeTbl() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getNoOfBed() {
		return this.noOfBed;
	}

	public void setNoOfBed(Integer noOfBed) {
		this.noOfBed = noOfBed;
	}


	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<RoomTbl> getRoomTbls() {
		return this.roomTbls;
	}

	public void setRoomTbls(List<RoomTbl> roomTbls) {
		this.roomTbls = roomTbls;
	}

	public RoomTbl addRoomTbl(RoomTbl roomTbl) {
		getRoomTbls().add(roomTbl);
		roomTbl.setRoomTypeTbl(this);

		return roomTbl;
	}

	public RoomTbl removeRoomTbl(RoomTbl roomTbl) {
		getRoomTbls().remove(roomTbl);
		roomTbl.setRoomTypeTbl(null);

		return roomTbl;
	}

	/**
	 * @return the rent
	 */
	public float getRent() {
		return rent;
	}

	/**
	 * @param rent the rent to set
	 */
	public void setRent(float rent) {
		this.rent = rent;
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

	

}