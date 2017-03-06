package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the department_tbl database table.
 * 
 */
@Entity
@Table(name="department_tbl")
public class DepartmentTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DEPARTMENT_TBL_ID_GENERATOR",sequenceName="department_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEPARTMENT_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=200)
	private String description;

	@Column(length=50)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(length=45)
	private StatusEnum status;


	//bi-directional many-to-one association to RoomTbl
	@OneToMany(mappedBy="departmentTbl")
	private List<RoomTbl> roomTbls;

	public DepartmentTbl() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RoomTbl> getRoomTbls() {
		return this.roomTbls;
	}

	public void setRoomTbls(List<RoomTbl> roomTbls) {
		this.roomTbls = roomTbls;
	}

	public RoomTbl addRoomTbl(RoomTbl roomTbl) {
		getRoomTbls().add(roomTbl);
		roomTbl.setDepartmentTbl(this);

		return roomTbl;
	}

	public RoomTbl removeRoomTbl(RoomTbl roomTbl) {
		getRoomTbls().remove(roomTbl);
		roomTbl.setDepartmentTbl(null);

		return roomTbl;
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