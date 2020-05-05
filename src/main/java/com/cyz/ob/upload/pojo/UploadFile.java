package com.cyz.ob.upload.pojo;

import com.cyz.basic.pojo.CreatorEntity;

public class UploadFile extends CreatorEntity<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1774234999397397801L;

	private String name; //图片名称
	
	private String path; //路径
	
	private Byte fileType; //文件类型   1-图片 2-视频
	
	private Byte relevance; // 1-admin  2-employee 3-novel 4-section
	
	private Long objId; //关联的表的记录的id
	
	private Long fileSize; // 文件大小
	
	public UploadFile() {
		super();
	}
	
	public UploadFile(byte relevance, long objId) {
		this.relevance = relevance;
		this.objId = objId;
	}

	public UploadFile(String path, Byte relevance, Long fileSize) {
		this.path = path;
		this.relevance = relevance;
		this.fileSize = fileSize;
	    setTheType();
	}
	
	public void setTheType () {
		if (path != null) {
			String type = path.substring(path.lastIndexOf(".") + 1);
			switch (type) {
			case "png":
			case "jpg":
				this.fileType = 1;
				break;
			default:
				break;
			}
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Byte getFileType() {
		return fileType;
	}

	public void setFileType(Byte fileType) {
		this.fileType = fileType;
	}

	public Byte getRelevance() {
		return relevance;
	}

	public void setRelevance(Byte relevance) {
		this.relevance = relevance;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getObjId() {
		return objId;
	}

	public void setObjId(Long objId) {
		this.objId = objId;
	}

	@Override
	public void acceptId(long id) {
		setId(id);
	}

}
