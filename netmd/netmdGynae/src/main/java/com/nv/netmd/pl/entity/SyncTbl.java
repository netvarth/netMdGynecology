package com.nv.netmd.pl.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the sync_tbl database table.
 * 
 */
@Entity
@Table(name="sync_tbl")
public class SyncTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SYNC_TBL_ID_GENERATOR",sequenceName="sync_seq", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYNC_TBL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="downloaded_time", length=100)
	private String downloadedTime;

	@Column(name="uploaded_time")
	private Date uploadedTime;

    public SyncTbl() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDownloadedTime() {
		return this.downloadedTime;
	}

	public void setDownloadedTime(String downloadedTime) {
		this.downloadedTime = downloadedTime;
	}

	/**
	 * @return the uploadedTime
	 */
	public Date getUploadedTime() {
		return uploadedTime;
	}

	/**
	 * @param uploadedTime the uploadedTime to set
	 */
	public void setUploadedTime(Date uploadedTime) {
		this.uploadedTime = uploadedTime;
	}

	
}