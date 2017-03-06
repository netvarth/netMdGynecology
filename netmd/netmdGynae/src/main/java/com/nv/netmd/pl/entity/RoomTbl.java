package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the room_tbl database table.
 * 
 */
@Entity
@Table(name="room_tbl")
public class RoomTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ROOM_TBL_ID_GENERATOR",sequenceName="room_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROOM_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=200)
	private String description;

	@Column(length=15)
	private String prefix;

	
	@Column(name="room_number", length=30)
	private String roomNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(length=45)
	private StatusEnum status;
	
	//bi-directional many-to-one association to BedTbl
	@OneToMany(mappedBy="roomTbl")
	private List<BedTbl> bedTbls;

	//bi-directional many-to-one association to BlockTbl
	@ManyToOne
	@JoinColumn(name="block_id")
	private BlockTbl blockTbl;

	//bi-directional many-to-one association to DepartmentTbl
	@ManyToOne
	@JoinColumn(name="department_id")
	private DepartmentTbl departmentTbl;

	//bi-directional many-to-one association to RoomTypeTbl
	@ManyToOne
	@JoinColumn(name="room_type_id")
	private RoomTypeTbl roomTypeTbl;

	public RoomTbl() {
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

	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	
	public String getRoomNumber() {
		return this.roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public List<BedTbl> getBedTbls() {
		return this.bedTbls;
	}

	public void setBedTbls(List<BedTbl> bedTbls) {
		this.bedTbls = bedTbls;
	}

	public BedTbl addBedTbl(BedTbl bedTbl) {
		getBedTbls().add(bedTbl);
		bedTbl.setRoomTbl(this);

		return bedTbl;
	}

	public BedTbl removeBedTbl(BedTbl bedTbl) {
		getBedTbls().remove(bedTbl);
		bedTbl.setRoomTbl(null);

		return bedTbl;
	}

	public BlockTbl getBlockTbl() {
		return this.blockTbl;
	}

	public void setBlockTbl(BlockTbl blockTbl) {
		this.blockTbl = blockTbl;
	}

	public DepartmentTbl getDepartmentTbl() {
		return this.departmentTbl;
	}

	public void setDepartmentTbl(DepartmentTbl departmentTbl) {
		this.departmentTbl = departmentTbl;
	}

	public RoomTypeTbl getRoomTypeTbl() {
		return this.roomTypeTbl;
	}

	public void setRoomTypeTbl(RoomTypeTbl roomTypeTbl) {
		this.roomTypeTbl = roomTypeTbl;
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